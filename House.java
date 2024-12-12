


public class House{

    public static int maxCandyPerType = 50;
    public static int maxCandyToGive = 15;

    private boolean isDecorated;
    // private Map<Candy,Integer> candyInventory;
    private Candy[] candyInventory;
    private double investmentLevel;
    private double generosity;
    private String address;

    public House(){
        // this.candyInventory = new Hashtable<>();

        this.investmentLevel = Math.random();
        this.generosity = Math.random();
        this.candyInventory = Candy.generateRandomCandyList(maxCandyPerType, investmentLevel);

        //select (amtOfCandyTypes) amount of candies from candyList and add their index in candyList to (candyIndexs)
        // for(int i = 0; i < amtOfCandyTypes ; i ++){
        //     int randIndex = (int) (Math.random() * Candy.candyTypes());
        //     while(candyInventory.get(Candy.returnCandy(randIndex)) != null){
        //         randIndex = (int) (Math.random() * Candy.candyTypes());
        //     }
        //     this.candyInventory.put(Candy.returnCandy(randIndex), (int) (investmentLevel * maxCandyPerType));
        // }
        // this has been moved to the candy class

        this.isDecorated = (investmentLevel  > 0.6);
        this.address = "123 Main St.";
    }
    public House(String address){
        this();
        this.address = address;
    }

    public String getAddress(){
        return this.address;
    }

    //returns true for treat, false for trick
    public boolean trickOrTreat(){
        return (this.generosity  > 0.3);
    }

    //lowers generosity value
    public void getTricked(){
        this.generosity += 0.05;
    }
    
    //Returns a sum of candy as <Candy, amount>
    // public Map<Candy, Integer> getCandy(){

    //     Map<Candy, Integer> list = new HashMap<Candy, Integer>();

    //     int totalCandy = (int) (generosity * maxCandyToGive);
    //     int counter = 0;

    //     for(Candy key : candyInventory.keySet()){
    //         //amt of candy this house has of this type
    //         int allowedCandy = candyInventory.get(key);

    //         int amt = (int) Math.min(Math.random() * allowedCandy / 10, totalCandy - counter);
    //         list.put(key, amt);
    //         counter += amt;
    //         candyInventory.replace(key, candyInventory.get(key) - amt);
    //     }

    //     this.generosity -= 0.05;
    //     return list;
    // }
    // replacing with a differant system

    public Candy[] giveCandy() {
        Candy[] list = Candy.generateEmptyCandyList();
        int totalCandy = (int) (generosity * maxCandyToGive);
        int candyCounter = 0;

        for(int i = 0; i < candyInventory.length; i++) {
            if (candyInventory[i].getCandyCount() != 0) {
                int amt = (int) (Math.min(
                    Math.random() * totalCandy /* im not sure what the actual variable was */ / 10, 
                    Math.min(totalCandy - candyCounter, candyInventory[i].getCandyCount()))); // this ensures that we never go below 0

                candyCounter += amt;
                candyInventory[i].addCandy(-amt);
                list[i].addCandy(amt);

                // TODO: improve randomization

            }
        }
        return list;
    }

}