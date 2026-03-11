package bt6;

import java.time.LocalDate;

public class User {

    private final String id;
    private String email;
    private LocalDate birthDate;
    private String displayName;

    public User(String id, String email, LocalDate birthDate, String displayName) {
        this.id = id;
        this.email = email;
        this.birthDate = birthDate;
        this.displayName = displayName;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
