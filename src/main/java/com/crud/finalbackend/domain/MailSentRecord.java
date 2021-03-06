package com.crud.finalbackend.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name="SENT_EMAILS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MailSentRecord {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private LocalDateTime sendTime;

    @NotNull
    private String recipientEmail;

    public MailSentRecord(LocalDateTime time, String email) {
        this.sendTime = time;
        this.recipientEmail = email;
    }

    @Override
    public String toString() {
        return "MailSentRecord{" +
                "id=" + id +
                ", sendTime=" + sendTime +
                ", recipientEmail='" + recipientEmail + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MailSentRecord)) return false;
        MailSentRecord that = (MailSentRecord) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getSendTime(), that.getSendTime()) && Objects.equals(getRecipientEmail(), that.getRecipientEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSendTime(), getRecipientEmail());
    }
}
