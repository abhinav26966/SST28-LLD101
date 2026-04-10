import java.util.Optional;

public class CreditsRule implements EligibilityRule {
    private final int min;
    public CreditsRule(int min) { this.min = min; }
    public Optional<String> check(StudentProfile s) {
        if (s.earnedCredits < min) return Optional.of("credits below " + min);
        return Optional.empty();
    }
}
