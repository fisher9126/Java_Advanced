package bt6;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class ProfileService {

    public User updateProfile(User existingUser, UserProfile newProfile, List<User> allUsers) {
        Objects.requireNonNull(existingUser, "Existing user must not be null");
        Objects.requireNonNull(newProfile, "New profile must not be null");

        LocalDate birthDate = newProfile.getBirthDate();
        if (birthDate.isAfter(LocalDate.now())) {
            return null;
        }

        String requestedEmail = newProfile.getEmail().trim();
        if (!requestedEmail.equalsIgnoreCase(existingUser.getEmail())) {
            if (allUsers != null) {
                for (User other : allUsers) {
                    if (other == null || other == existingUser) {
                        continue;
                    }
                    if (requestedEmail.equalsIgnoreCase(other.getEmail())) {
                        return null;
                    }
                }
            }
        }

        existingUser.setEmail(requestedEmail);
        existingUser.setBirthDate(birthDate);
        existingUser.setDisplayName(newProfile.getDisplayName());
        return existingUser;
    }
}
