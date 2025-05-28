import java.util.Arrays;
import java.util.List;

public class BreadSelection {
    String breadType;
    List<SizePrice> pricing;

    public BreadSelection(String breadType, SizePrice[] pricing) {
        this.pricing = Arrays.stream(pricing).toList();
        this.breadType = breadType;
    }
}
