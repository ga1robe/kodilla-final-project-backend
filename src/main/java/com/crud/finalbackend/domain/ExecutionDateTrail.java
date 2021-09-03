package com.crud.finalbackend.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name="EXECUTION_DATES")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder(toBuilder = true)
public class ExecutionDateTrail {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private LocalDateTime dateExecuted;

    @Override
    public String toString() {
        return "ExecutionDateTrail{" +
                "id=" + id +
                ", whenExecuted=" + dateExecuted +
                '}';
    }
}
