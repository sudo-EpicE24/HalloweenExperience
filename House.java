


public class House{

    public final static int maxTotalCandy = 300;
    public final static int maxCandyPerType = 50;
    public final static int maxCandyToGive = 15;

    private boolean isDecorated;
    private Candy[] candyInventory;
    private double investmentLevel;
    private double generosity;
    private String address;

    public House(){

        this.investmentLevel = Math.random();
        this.generosity = Math.random();
        this.candyInventory = Candy.generateRandomCandyList(maxTotalCandy, maxCandyPerType, investmentLevel);

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

    public Candy[] giveCandy() {
        Candy[] list = Candy.generateEmptyCandyList();

        System.out.println();
        System.out.println();

        for(int i = 0; i < candyInventory.length; i++) {
            System.out.println(candyInventory[i].getCandyName() + " : " +  candyInventory[i].getCandyCount());

            int amt = Math.min( ((int) (maxCandyToGive / candyInventory.length * generosity)),  candyInventory[i].getCandyCount()); //Amount of this candy to give 

            candyInventory[i].addCandy(-amt);
            list[i].addCandy(amt);

            // TODO: improve randomization
        }
        return list;
    }

}