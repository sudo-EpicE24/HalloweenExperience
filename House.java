import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class House{

    public static int maxCandyPerType = 50;
    public static int maxCandyToGive = 15;

    private boolean isDecorated;
    private Map<Candy,Integer> candyInventory;
    private double investmentLevel;
    private double generosity;
    private String address;

    public House(){
        this.candyInventory = new Hashtable<>();
        this.investmentLevel = Math.random();
        this.generosity = Math.random();

        int amtOfCandyTypes = (int) investmentLevel * Candy.candyTypes();

        //select (amtOfCandyTypes) amount of candies from candyList and add their index in candyList to (candyIndexs)
        for(int i = 0; i < amtOfCandyTypes ; i ++){
            int randIndex = (int) (Math.random() * Candy.candyTypes());
            while(candyInventory.get(Candy.returnCandy(randIndex)) != null){
                randIndex = (int) (Math.random() * Candy.candyTypes());
            }
            this.candyInventory.put(Candy.returnCandy(randIndex), (int) (investmentLevel * maxCandyPerType));
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
    
    //Returns a sum of candy as <Candy, amount>
    public Map<Candy, Integer> getCandy(){

        Map<Candy, Integer> list = new HashMap<Candy, Integer>();

        int totalCandy = (int) (generosity * maxCandyToGive);
        int counter = 0;

        for(Candy key : candyInventory.keySet()){
            //amt of candy this house has of this type
            int allowedCandy = candyInventory.get(key);

            int amt = (int) Math.min(Math.random() * allowedCandy / 10, totalCandy - counter);
            list.put(key, amt);
            counter += amt;
            candyInventory.replace(key, candyInventory.get(key) - amt);
        };

        this.generosity -= 0.05;
        return list;
    }

}