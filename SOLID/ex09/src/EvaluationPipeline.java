public class EvaluationPipeline {
    // DIP violation: high-level module constructs concretes directly
    
    private final PlagiarismService plagiarismService;
    private final CodeGradingService gradingService;
    private final ReportService reportService;
    private final Rubric rubric;
    
    public EvaluationPipeline(
            PlagiarismService plagiarismService,
            CodeGradingService gradingService,
            ReportService reportService,
            Rubric rubric) {

        this.plagiarismService = plagiarismService;
        this.gradingService = gradingService;
        this.reportService = reportService;
        this.rubric = rubric;
    }


    public void evaluate(Submission sub) {
        // Rubric rubric = new Rubric();
        // PlagiarismChecker pc = new PlagiarismChecker();
        // CodeGrader grader = new CodeGrader();
        // ReportWriter writer = new ReportWriter();

        // int plag = pc.check(sub);
        
        int plag = plagiarismService.check(sub);
        System.out.println("PlagiarismScore=" + plag);

        // int code = grader.grade(sub, rubric);

        int code = gradingService.grade(sub, rubric);
        System.out.println("CodeScore=" + code);

        // String reportName = writer.write(sub, plag, code);
        
        String reportName = reportService.write(sub, plag, code);
        System.out.println("Report written: " + reportName);

        
        int total = plag + code;
        String result = (total >= 90) ? "PASS" : "FAIL";
        System.out.println("FINAL: " + result + " (total=" + total + ")");
    }
}
