


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
        this.candyInventory = Candy.generateRandomCandyList(maxCandyToGive, maxCandyPerType, investmentLevel);

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
        int totalCandy = (int) (generosity * maxCandyToGive);
        int candyCounter = 0;

        for(int i = 0; i < candyInventory.length; i++) {
            if (candyInventory[i].getCandyCount() != 0) {
                int amt = (int) (Math.min(
                    Math.random() * totalCandy / 10, 
                    Math.min(totalCandy - candyCounter, candyInventory[i].getCandyCount()))); // this ensures that we never go below 0
                System.out.println(amt);
                candyCounter += amt;
                candyInventory[i].addCandy(-amt);
                list[i].addCandy(amt);

                // TODO: improve randomization

            }
        }
        return list;
    }

}