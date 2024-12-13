




public class House{

    public final static int maxTotalCandy = 300;
    public final static int maxCandyPerType = 50;

    private boolean isDecorated;
    private Candy[] candyInventory;
    private double investmentLevel;
    private double generosity;
    private String address;
    private int numOfNonEmptyCandies = 0;


    public House(){

        this.investmentLevel = Math.random();
        this.generosity = Math.random();
        this.candyInventory = Candy.generateRandomCandyList(maxTotalCandy, maxCandyPerType, investmentLevel);

        for (Candy candy : candyInventory) {
            if (candy.getCandyCount() > 0) {
                this.numOfNonEmptyCandies ++;
            }
        }

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

        for(int i = 0; i < this.candyInventory.length; i++) {
            int amt = Math.min( 
                ((int) (this.numOfNonEmptyCandies * this.generosity * Math.random())),  
                this.candyInventory[i].getCandyCount()
            ); //Amount of this candy to give 

            this.candyInventory[i].addCandy(-amt);
            list[i].addCandy(amt);

        }

        return list;
    }

}