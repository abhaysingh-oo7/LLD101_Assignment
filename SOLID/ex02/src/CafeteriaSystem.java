import java.util.*;

public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    // private final FileStore store = new FileStore();
    private int invoiceSeq = 1000;
    
    // *********
    private final PricingCalculator pricing = new PricingCalculator();
    private final TaxPolicy taxPolicy = new DefaultTaxPolicy();
    private final DiscountPolicy discountPolicy = new DefaultDiscountPolicy();
    private final InvoiceFormatter formatter = new InvoiceFormatter();
    private final InvoiceRepository store;
    // *********
 
    public CafeteriaSystem(InvoiceRepository store) {
        this.store = store;
    }
    
    public void addToMenu(MenuItem i) { menu.put(i.id, i); }
    // Intentionally SRP-violating: menu mgmt + tax + discount + format + persistence.
    public void checkout(String customerType, List<OrderLine> lines) {
        String invId = "INV-" + (++invoiceSeq);
        // StringBuilder out = new StringBuilder();
        // out.append("Invoice# ").append(invId).append("\n");

        double subtotal = 0.0;
        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            // double lineTotal = item.price * l.qty;
            double lineTotal = pricing.lineTotal(item, l.qty);
            subtotal += lineTotal;
            // out.append(String.format("- %s x%d = %.2f\n", item.name, l.qty, lineTotal));
        }

        double taxPct = taxPolicy.taxPercent(customerType);

        double tax = subtotal * (taxPct / 100.0);

        double discount = discountPolicy.discountAmount(customerType, subtotal, lines.size());

        double total = subtotal + tax - discount;

        // out.append(String.format("Subtotal: %.2f\n", subtotal));
        // out.append(String.format("Tax(%.0f%%): %.2f\n", taxPct, tax));
        // out.append(String.format("Discount: -%.2f\n", discount));
        // out.append(String.format("TOTAL: %.2f\n", total));
        String printable = formatter.format(
            invId,
            menu,
            lines,
            subtotal,
            taxPct,
            tax,
            discount,
            total
        );
        // String printable = InvoiceFormatter.identityFormat(out.toString());
        System.out.print(printable);

        store.save(invId, printable);
        System.out.println("Saved invoice: " + invId + " (lines=" + store.countLines(invId) + ")");
    }
}
