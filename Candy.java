
import java.util.ArrayList;

public class Candy {
    private String name;
    private int healValue;
    // private int rarity; if wanted
    // private int size; if wanted
    private static ArrayList<Candy> candies;
    
    public Candy(String name){
        this(name, 0);
    }
    public Candy(String name, int healValue) {
        this.name = name;
        this.healValue = healValue;

        candies.add(this);
    }

    public String candyName() {
        return name;
    }
    public int candyHP() {
        return healValue;
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
