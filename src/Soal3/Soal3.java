package Soal3;

public class Soal3 {
    public static void main(String[] args) {
        Food food1 = new Food("Beef Rendang", 15000);
        RegularMenu food2 = new RegularMenu("Chicken Ramen", 20000);
        SpecialMenu food3 = new SpecialMenu("Fiery Fried Rice", 80000);
        food1.getInfo();
        food2.getInfo();
        food3.getInfo();
    }
}
class Food {
    protected String name;
    protected int basePrice;
    protected final int labourCost = 5000;
    public Food(String name, int basePrice) {
        if (basePrice >= 1000000) {
            throw new IllegalArgumentException("Error: Base price must be below 1,000,000");
        }
        this.name = name;
        this.basePrice = basePrice;
    }
    public int calcPrice() {
        return basePrice + labourCost;
    }
    public void getInfo() {
        System.out.println("Name: " + name);
        System.out.println("Price: " + calcPrice());
        System.out.println();
    }
}
class RegularMenu extends Food {
    private final int regularTax = 10000;
    public RegularMenu(String name, int basePrice) {
        super(name, basePrice);
    }
    @Override
    public int calcPrice() {
        return super.calcPrice() + regularTax;
    }
}
class SpecialMenu extends Food {
    private final int specialTax = 20000;
    public SpecialMenu(String name, int basePrice) {
        super(name, basePrice);
    }
    @Override
    public int calcPrice() {
        return super.calcPrice() + specialTax;
    }
}