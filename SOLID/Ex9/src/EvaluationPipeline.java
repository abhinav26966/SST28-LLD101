public class EvaluationPipeline {
    private final PlagiarismCheck plagiarismCheck;
    private final CodeGrading grader;
    private final ReportWriting writer;
    private final Rubric rubric;

    public EvaluationPipeline() {
        this(new PlagiarismChecker(), new CodeGrader(), new ReportWriter(), new Rubric());
    }

    public EvaluationPipeline(PlagiarismCheck plagiarismCheck, CodeGrading grader,
                              ReportWriting writer, Rubric rubric) {
        this.plagiarismCheck = plagiarismCheck;
        this.grader = grader;
        this.writer = writer;
        this.rubric = rubric;
    }

    public void evaluate(Submission sub) {
        int plag = plagiarismCheck.check(sub);
        System.out.println("PlagiarismScore=" + plag);

        int code = grader.grade(sub, rubric);
        System.out.println("CodeScore=" + code);

        String reportName = writer.write(sub, plag, code);
        System.out.println("Report written: " + reportName);

        int total = plag + code;
        String result = (total >= 90) ? "PASS" : "FAIL";
        System.out.println("FINAL: " + result + " (total=" + total + ")");
    }
}
