package bt6;

import java.time.LocalDate;

public class UserProfile {

    private final String email;
    private final LocalDate birthDate;
    private final String displayName;

    public UserProfile(String email, LocalDate birthDate, String displayName) {
        this.email = email;
        this.birthDate = birthDate;
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getDisplayName() {
        return displayName;
    }
}
