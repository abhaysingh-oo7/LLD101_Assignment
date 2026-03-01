public class AttendanceRule implements EligibilityRule {

    @Override
    public boolean isSatisfied(StudentProfile s) {
        return s.attendancePct >= 75;
    }

    @Override
    public String getFailureReason() {
        return "attendance below 75";
    }
}