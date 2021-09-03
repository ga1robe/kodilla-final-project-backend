package com.crud.finalbackend.domain.dto;

import com.crud.finalbackend.domain.ChargeStatus;
import lombok.*;

import java.math.BigDecimal;
import java.util.regex.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ChargeDto {
    private Long id;
    private ChargeStatus status;
    private BigDecimal value;
    private String chargeDate;

    public boolean hasValidDate() {
        Pattern datePattern = Pattern.compile("^(" +
                "(2[0-9])[0-9]{2})" + "-" + /* year of 2xxx */
                "(0[1-9]|1[012])" + "-" + /* month from 01 to 12 */
                "(0[1-9]|[12][0-9]|3[01]" + /* day from 01 to 30|31 */
                ")$");
        return datePattern.matcher(chargeDate).matches();
    }
}
