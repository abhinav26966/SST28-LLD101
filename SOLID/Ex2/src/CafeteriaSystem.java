import java.util.*;

public class CafeteriaSystem {
    private final Menu menu = new Menu();
    private final OrderPricer pricer = new OrderPricer(menu);
    private final TaxPolicy taxPolicy;
    private final DiscountPolicy discountPolicy;
    private final InvoiceFormatter formatter;
    private final InvoiceStore store;
    private int invoiceSeq = 1000;

    public CafeteriaSystem() {
        this(new TaxRules(), new DiscountRules(), new InvoiceFormatter(), new FileStore());
    }

    public CafeteriaSystem(TaxPolicy taxPolicy, DiscountPolicy discountPolicy,
                           InvoiceFormatter formatter, InvoiceStore store) {
        this.taxPolicy = taxPolicy;
        this.discountPolicy = discountPolicy;
        this.formatter = formatter;
        this.store = store;
    }

    public void addToMenu(MenuItem i) { menu.add(i); }

    public void checkout(String customerType, List<OrderLine> lines) {
        String invId = "INV-" + (++invoiceSeq);

        List<PricedLine> priced = pricer.price(lines);
        double subtotal = pricer.subtotal(priced);
        double taxPct = taxPolicy.taxPercent(customerType);
        double tax = subtotal * (taxPct / 100.0);
        double discount = discountPolicy.discountAmount(customerType, subtotal, lines.size());
        double total = subtotal + tax - discount;

        Invoice inv = new Invoice(invId, priced, subtotal, taxPct, tax, discount, total);
        String printable = formatter.format(inv);
        System.out.print(printable);

        store.save(invId, printable);
        System.out.println("Saved invoice: " + invId + " (lines=" + store.countLines(invId) + ")");
    }
}
