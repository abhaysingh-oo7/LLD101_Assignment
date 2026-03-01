public interface EligibilityRule {
    boolean isSatisfied(StudentProfile s);
    String getFailureReason();
}