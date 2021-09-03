package com.crud.finalbackend.domain;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="USERS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class User {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min=2, max=20, message="Surname must be 2-20 characters long")
    private String name;

    @NotNull
    @Size(min=2, max=25, message="Surname must be 2-25 characters long")
    private String surname;

    @NotNull
    @Pattern(regexp = ".{3,}@.{2,}\\..{2,3}", message = "email format is not proper")
    @Column(unique=true)
    private String email;

    @NotNull
    private String securePassword;

    @NotNull
    private LocalDate registered;

    @NotNull
    @Column(name="NOTIFICATION_PREFERENCES")
    @OneToMany(
            targetEntity = NotificationPreference.class,
            mappedBy = "user",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private Set<NotificationPreference> notificationPreferences;

    public void addPreference(NotificationPreference preference) {
        notificationPreferences.add(preference);
    }

    public void removePreference(NotificationPreference preference) {
        notificationPreferences.remove(preference);
    }

    public void removeAllPreferences() {
        notificationPreferences.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) && Objects.equals(getName(), user.getName()) && Objects.equals(getSurname(), user.getSurname()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getSecurePassword(), user.getSecurePassword()) && Objects.equals(getRegistered(), user.getRegistered()) && Objects.equals(getNotificationPreferences(), user.getNotificationPreferences());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSurname(), getEmail(), getSecurePassword(), getRegistered(), getNotificationPreferences());
    }
}
