public class CgrRule implements EligibilityRule {

    @Override
    public boolean isSatisfied(StudentProfile s) {
        return s.cgr >= 8.0;
    }

    @Override
    public String getFailureReason() {
        return "CGR below 8.0";
    }
}