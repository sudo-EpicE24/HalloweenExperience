

public final class Candy {
    private String name;
    private int healValue;
    private int candyCount;
    // private int rarity; if wanted
    // private int size; if wanted
    private final static Candy[] CANDY_LIST = {
        new Candy("s", 1)
    };

    public Candy(String name, int healValue) {
        this.name = name;
        this.healValue = healValue;

        candyCount = 0;
    }

    public static Candy[] generateRandomCandyList(int maxCandyPerType, double candyMultiplier) {
        Candy[] list = CANDY_LIST;
        for(int i = 0; i < (int) (candyMultiplier * getCandyTypes()) ; i ++){
            int randIndex = (int) (Math.random() * getCandyTypes());
            while(list[i] != null){
                randIndex = (int) (Math.random() * getCandyTypes());
            }
            list[randIndex].setCount((int) (maxCandyPerType*candyMultiplier));
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
