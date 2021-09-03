package com.crud.finalbackend.scheduler;

import com.crud.finalbackend.config.AdminConfig;
import com.crud.finalbackend.domain.*;
import com.crud.finalbackend.service.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
@Slf4j
public class NotificationScheduler {
    private final SimpleEmailService simpleEmailService;
    private final AdminConfig adminConfig;
    private final SeasonalTrailOffersService offersCreator;
    private final MailSentRecordService mailRecordService;

    private static final String SUBJECT = "New hiking trails matching your preferences";

    private  Map<String, List<String>> groupEmailAndOffers( Map<Preference, TrailOffer> preferencesAndOffers ) {
        Map<String, List<String>> result = new HashMap<>();

        for( Map.Entry<Preference, TrailOffer> entry : preferencesAndOffers.entrySet() ) {
            String email = entry.getKey().getUser().getEmail();
            String offer = entry.getValue().toString();

            if( result.containsKey(email) ) {
                result.get(email).add(offer);
            } else {
                result.put(email, Arrays.asList(offer));
            }
        }

        return result;
    }

    private Map<String, String> getEmailAndOfferMessage( Map<Preference, TrailOffer> preferencesAndOffers ) {
        Map<String, List<String>> emailAndOffers = groupEmailAndOffers(preferencesAndOffers);
        Map<String, String> result = new HashMap<>();

        for( Map.Entry<String, List<String>> entry : emailAndOffers.entrySet() ) {
            StringBuilder builder = new StringBuilder();
            builder.append("Here are offers that match your requested trail preferences: ");
            entry.getValue().forEach( e -> builder.append(e + "\r\n") );

            result.put(entry.getKey(), builder.toString() );
        }

        return result;
    }

    /**
     * Every 6 hours checks for offers mathcing preferences and sends emails if finds ones
     */
    @Scheduled(cron = "0 0 */6 * * *")
    public void notifyAboutOffers(){
        Map<String, String> emailAndOfferMessage = getEmailAndOfferMessage( offersCreator.getPreferencesAndOffers() );

        for( Map.Entry<String, String> entry : emailAndOfferMessage.entrySet()) {
            simpleEmailService.send(EmailCreatorService.MAIL_TYPE_USER_CUSTOM_NOTIFICATIONS, Mail.builder()
                    .mailTo(entry.getKey())
                    .toCc(adminConfig.getApplicationEmail())
                    .subject(SUBJECT)
                    .message(entry.getValue())
                    .build()
            );

            MailSentRecord record = new MailSentRecord(LocalDateTime.now(), entry.getKey());
            mailRecordService.addRecord(record);
        }
    }
}
