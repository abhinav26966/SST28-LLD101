import java.util.*;

public class StudentValidator {
    private final Set<String> allowedPrograms;

    public StudentValidator(Set<String> allowedPrograms) {
        this.allowedPrograms = allowedPrograms;
    }

    public static StudentValidator defaults() {
        return new StudentValidator(new LinkedHashSet<>(Arrays.asList("CSE", "AI", "SWE")));
    }

    public List<String> validate(StudentDraft d) {
        List<String> errors = new ArrayList<>();
        if (d.name.isBlank()) errors.add("name is required");
        if (d.email.isBlank() || !d.email.contains("@")) errors.add("email is invalid");
        if (d.phone.isBlank() || !d.phone.chars().allMatch(Character::isDigit)) errors.add("phone is invalid");
        if (!allowedPrograms.contains(d.program)) errors.add("program is invalid");
        return errors;
    }
}
