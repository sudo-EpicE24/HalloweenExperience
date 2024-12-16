public class House{

    public final static int MAX_TOTAL_CANDY = 300;
    public final static int MAX_CANDY_PER_TYPE = 50;

    private boolean isDecorated;
    private Candy[] candyInventory;
    private double investmentLevel;
    private double generosity;
    private String address;
    private int numOfNonEmptyCandies = 0;

    public House(){

        this.investmentLevel = Math.random();
        this.generosity = Math.random();
        this.candyInventory = Candy.generateRandomCandyList(MAX_TOTAL_CANDY, MAX_CANDY_PER_TYPE, investmentLevel);

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

        for(Candy c : candyInventory){
            if (c.getCandyCount() > 0){
                return (this.generosity  > 0.3);
            }
        }

        return false;
    }

    public void printInfo(){

        System.out.println();

        System.out.println("Decorated: " + this.isDecorated);
        System.out.println("Investment Level: " + app.toPercent(investmentLevel, 2));
        System.out.println("Genorosity: " + app.toPercent(investmentLevel, 2));
        System.out.println("Address: " + this.address + "\n");


        System.out.println("Candy Inventory:\n");

        int maxCandyNameLength = 0;
        for (Candy candy : candyInventory) {
            if (candy.getCandyName().length() > maxCandyNameLength) {
                maxCandyNameLength = candy.getCandyName().length();
            }
        }

        
        System.out.println("-".repeat((( maxCandyNameLength + 4) * 2) + 2));
        System.out.println( 
            "| " + String.format("%-"+( maxCandyNameLength + 2 )+"s","Candy") +
            "| " + String.format("%-"+( maxCandyNameLength + 2 )+"s","Count") + " |"
        );

        System.out.println("-".repeat((( maxCandyNameLength + 4) * 2) + 2));
        for (Candy candy : candyInventory) {
            System.out.println(
                "| " + String.format("%-"+( maxCandyNameLength + 2 )+"s", candy.getCandyName()) + 
                "| " + String.format("%-"+( maxCandyNameLength + 2 )+"s", candy.getCandyCount()) + " |"
                );
        }
        System.out.println("-".repeat((( maxCandyNameLength + 4) * 2) + 2));
        System.out.println();
    }

    public void getTricked(){
        this.generosity += 0.25 * Math.random();
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
        this.generosity -= 0.25 * Math.random();
        return list;
    }

}