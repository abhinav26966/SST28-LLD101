import java.util.List;

public class OnboardingService {
    private final StudentStore store;
    private final RawInputParser parser;
    private final StudentValidator validator;
    private final OnboardingPrinter printer;

    public OnboardingService(StudentStore store) {
        this(store, new RawInputParser(), StudentValidator.defaults(), new OnboardingPrinter());
    }

    public OnboardingService(StudentStore store, RawInputParser parser,
                             StudentValidator validator, OnboardingPrinter printer) {
        this.store = store;
        this.parser = parser;
        this.validator = validator;
        this.printer = printer;
    }

    public void registerFromRawInput(String raw) {
        printer.printInput(raw);

        StudentDraft draft = parser.parse(raw);
        List<String> errors = validator.validate(draft);
        if (!errors.isEmpty()) {
            printer.printErrors(errors);
            return;
        }

        String id = IdUtil.nextStudentId(store.count());
        StudentRecord rec = new StudentRecord(id, draft.name, draft.email, draft.phone, draft.program);
        store.save(rec);

        printer.printSuccess(rec, store.count());
    }
}
