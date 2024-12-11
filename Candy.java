
import java.util.ArrayList;

public final class Candy {
    private String name;
    private int healValue;
    private int indexOfCandy;
    // private int rarity; if wanted
    // private int size; if wanted
    public static ArrayList<Candy> candies = new ArrayList<Candy>();
    Candy candy = new Candy("pure sugar");
    Candy candy2 = new Candy("pure sugar1");
    Candy candy3 = new Candy("pure sugar2");
    Candy candy4 = new Candy("pure sugar3");
    Candy candy5 = new Candy("pure sugar4");
    
    public Candy(String name){
        this(name, 0);
    }
    public Candy(String name, int healValue) {
        this.name = name;
        this.healValue = healValue;

        candies.add(this);
        indexOfCandy = candies.size()-1;
    }

    public String candyName() {
        return name;
    }
    public int candyHP() {
        return healValue;
    }
    public int indexOfCandy() {
        return indexOfCandy;
    }
    
    public static String candyName(int i) {
        return candies.get(i).candyName();
    }
    public static int candyHP(int i) {
        return candies.get(i).candyHP();
    }

    public static Candy returnCandy(int i) {
        return candies.get(i);
    }
    public static int candyTypes() {
        return candies.size();
    }
}
