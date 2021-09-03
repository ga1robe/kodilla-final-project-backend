package com.crud.finalbackend.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name="CHARGES")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Charge {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ChargeStatus status;

    @NotNull
    private BigDecimal value;

    @Null
    private LocalDate chargeDate;

    public boolean isChargeDate() {
        return !(chargeDate==null);
    }

    @Override
    public String toString() {
        return "Charge{" +
                "id=" + id +
                ", status=" + status +
                ", value=" + value +
                ", paymentDate=" + chargeDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Charge)) return false;
        Charge charge = (Charge) o;
        return Objects.equals(getId(), charge.getId()) && Objects.equals(getStatus(), charge.getStatus()) && Objects.equals(getValue(), charge.getValue()) && Objects.equals(getChargeDate(), charge.getChargeDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStatus(), getValue(), getChargeDate());
    }
}
