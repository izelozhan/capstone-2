public class Topping {
    String name;
    SizePrice[] pricing;

    public Topping(String name, SizePrice[] pricing) {
        this.name = name;
        this.pricing = pricing;
    }

    public boolean isPremium(){
        return pricing.length > 0;
    }
}
