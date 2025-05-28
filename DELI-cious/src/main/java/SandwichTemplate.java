import java.util.List;

public class SandwichTemplate extends Sandwich{
    String name;

    public SandwichTemplate(BreadSelection bread, List<Topping> toppingList, List<Sauce> sauceList, boolean isToasted) {
        super(bread, toppingList, sauceList, isToasted);
    }
}
