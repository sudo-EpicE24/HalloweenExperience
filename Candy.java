



public final class Candy {
    private String name;
    private int healValue;
    private int candyCount;
    // private int rarity; if wanted
    // private int size; if wanted
    private final static Candy[] CANDY_LIST = {
        new Candy("M&Ms", 0), new Candy("Candy Corn", 0),
        new Candy("Pure sugar", 1), new Candy("Skittle", 1),
        new Candy("Brown sugar", 2), new Candy("Starburst", 2),
        new Candy("DumDum", 5), new Candy("Smarties", 5),
        new Candy("Chocolate", 10), new Candy("Glazed Apple", 10)
    };

    public Candy(String name, int healValue) {
        this.name = name;
        this.healValue = healValue;

        candyCount = 0;
    }

    public static Candy[] generateRandomCandyList(int maxCandy, int maxCandyPerType, double candyMultiplier) {
        Candy[] list = CANDY_LIST;
        int candyGiven = 0;
        maxCandy *= candyMultiplier;
        while (candyGiven < maxCandy) {
            int randIndex = (int) (Math.random() * getCandyTypes());
            int toGive = Math.min(maxCandy-candyGiven, (int) Math.round(maxCandyPerType * candyMultiplier));
            list[randIndex].addCandy(toGive);
            candyGiven += toGive;
        }
        return list;
    }
    public static Candy[] generateEmptyCandyList() {
        return CANDY_LIST;
    }

    public String getCandyName() {
        return name;
    }
    public int getCandyHP() {
        return healValue;
    }
    public int getCandyCount() {
        return candyCount;
    }

    public void setCount(int newCount) {
        candyCount = newCount;
    }
    public void addCandy(int change) {
        candyCount = Math.max(0, candyCount+change);
    }
    
    public static String getCandyName(int i) {
        return CANDY_LIST[i].getCandyName();
    }
    public static int getCandyHP(int i) {
        return CANDY_LIST[i].getCandyHP();
    }

    public static Candy getCandy(int i) {
        return CANDY_LIST[i];
    }
    public static int getCandyTypes() {
        return CANDY_LIST.length;
    }


}
