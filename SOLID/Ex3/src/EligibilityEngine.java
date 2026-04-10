import java.util.*;

public class EligibilityEngine {
    private final EligibilityStore store;
    private final List<EligibilityRule> rules;
    private final ReportPrinter printer;

    public EligibilityEngine(EligibilityStore store) {
        this(store, defaultRules(), new ReportPrinter());
    }

    public EligibilityEngine(EligibilityStore store, List<EligibilityRule> rules, ReportPrinter printer) {
        this.store = store;
        this.rules = rules;
        this.printer = printer;
    }

    public static List<EligibilityRule> defaultRules() {
        return List.of(
            new DisciplinaryRule(),
            new CgrRule(8.0),
            new AttendanceRule(75),
            new CreditsRule(20)
        );
    }

    public void runAndPrint(StudentProfile s) {
        EligibilityEngineResult r = evaluate(s);
        printer.print(s, r);
        store.save(s.rollNo, r.status);
    }

    public EligibilityEngineResult evaluate(StudentProfile s) {
        List<String> reasons = new ArrayList<>();
        String status = "ELIGIBLE";
        for (EligibilityRule rule : rules) {
            Optional<String> reason = rule.check(s);
            if (reason.isPresent()) {
                status = "NOT_ELIGIBLE";
                reasons.add(reason.get());
                break;
            }
        }
        return new EligibilityEngineResult(status, reasons);
    }
}

class EligibilityEngineResult {
    public final String status;
    public final List<String> reasons;
    public EligibilityEngineResult(String status, List<String> reasons) {
        this.status = status;
        this.reasons = reasons;
    }
}
