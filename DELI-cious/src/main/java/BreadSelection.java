public class BreadSelection {
    String breadType;
    SizePrice[] pricing;

    public BreadSelection( String breadType, SizePrice[] pricing) {
        this.pricing = pricing;
        this.breadType = breadType;
    }
}
