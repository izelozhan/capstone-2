package Old.Enums;

public enum BreadType {
    WHITE(5.50, 7.00, 8.50),
    WHEAT(5.50, 7.00, 8.50),
    RYE(5.50, 7.00, 8.50),
    WRAP(5.50, 7.00, 8.50);

    private final double priceFor4;
    private final double priceFor8;
    private final double priceFor12;

    BreadType(double priceFor4, double priceFor8, double priceFor12) {
        this.priceFor4 = priceFor4;
        this.priceFor8 = priceFor8;
        this.priceFor12 = priceFor12;
    }

    public double getBasePrice(Size size){
        return switch (size){
            case FOUR_INCH -> priceFor4;
            case EIGHT_INCH -> priceFor8;
            case TWELVE_INCH -> priceFor12;
        };
    }

}

// https://www.baeldung.com/java-enum-values

