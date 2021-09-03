package com.crud.finalbackend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name="APPLICATION_STARTUP_ARGS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StartupArgs {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private LocalDateTime startupTime;

    private String[] args;

    public StartupArgs(LocalDateTime time, String[] args) {
        this.startupTime = time;
        this.args = args;
    }

    @Override
    public String toString() {
        return "StartupArgs{" +
                "id=" + id +
                ", startupTime=" + startupTime +
                ", args=" + Arrays.toString(args) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StartupArgs)) return false;
        StartupArgs that = (StartupArgs) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getStartupTime(), that.getStartupTime()) && Arrays.equals(getArgs(), that.getArgs());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getId(), getStartupTime());
        result = 31 * result + Arrays.hashCode(getArgs());
        return result;
    }
}
