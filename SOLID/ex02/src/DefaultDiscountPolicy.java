public class DefaultDiscountPolicy implements DiscountPolicy {

    @Override
    public double discountAmount(String customerType, double subtotal, int distinctLines) {

        if (customerType == null) return 0.0;

        String type = customerType.trim().toLowerCase();

        switch (type) {
            case "student":
                if (subtotal >= 180.0) return 10.0;
                return 0.0;

            case "staff":
                if (distinctLines >= 3) return 15.0;
                return 5.0;

            default:
                return 0.0;
        }
    }
}