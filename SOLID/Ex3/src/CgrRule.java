import java.util.Optional;

public class CgrRule implements EligibilityRule {
    private final double min;
    public CgrRule(double min) { this.min = min; }
    public Optional<String> check(StudentProfile s) {
        if (s.cgr < min) return Optional.of("CGR below " + formatMin());
        return Optional.empty();
    }
    private String formatMin() {
        if (min == (int) min) return String.format("%.1f", min);
        return String.valueOf(min);
    }
}
