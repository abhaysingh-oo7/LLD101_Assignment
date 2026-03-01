public class DefaultTaxPolicy implements TaxPolicy {

    @Override
    public double taxPercent(String customerType) {

        if (customerType == null) {
            return 8.0;
        }

        String type = customerType.trim().toLowerCase();

        switch (type) {
            case "student":
                return 5.0;
            case "staff":
                return 2.0;
            default:
                return 8.0;
        }
    }
}