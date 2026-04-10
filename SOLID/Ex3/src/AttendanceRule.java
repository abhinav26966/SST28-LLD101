import java.util.Optional;

public class AttendanceRule implements EligibilityRule {
    private final int min;
    public AttendanceRule(int min) { this.min = min; }
    public Optional<String> check(StudentProfile s) {
        if (s.attendancePct < min) return Optional.of("attendance below " + min);
        return Optional.empty();
    }
}
