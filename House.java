import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class House{

    public static int maxCandy = 10;

    private boolean isDecorated;
    private ArrayList<Candy> candyInventory;
    private double investmentLevel;
    private double generosity;

    public House(){

        this.candyInventory = new ArrayList<Candy>();
        this.investmentLevel = Math.random();
        this.generosity = Math.random();

        int amtOfCandyTypes = (int) investmentLevel * Candy.candyTypes();

        //select (amtOfCandyTypes) amount of candies from candyList and add their index in candyList to (candyIndexs)
        for(int i = 0; i < amtOfCandyTypes ; i ++){
            int randIndex = (int) Math.random() * Candy.candyTypes();
            while(candyInventory.contains(Candy.returnCandy(randIndex))){
                randIndex = (int) Math.random() * Candy.candyTypes();
            }
            this.candyInventory.add(Candy.returnCandy(randIndex));
        }

        this.isDecorated = (investmentLevel  > 0.6) ? true : false;
    }

    //returns true for treat, false for trick
    public boolean trickOrTreat(){
        return (generosity  > 0.3) ? true : false;
    }

    public void getTricked(){
        generosity += 0.05;
    }
    //Returns a sum of candy as <Candy, amount>
    public Dictionary<Candy, Integer> getCandy(){

        Dictionary<Candy, Integer> list = new Hashtable<>();

        int totalCandy = (int) (this.generosity * maxCandy);
        int counter = 0;

        for(int i=0; i<candyInventory.size(); i++){
            int amt = (int) Math.min(Math.random() * ((totalCandy * 1.0 )/3) , 1.0 * (totalCandy - counter));
            list.put(candyInventory.get(i), amt);
            counter += amt;
        };

        generosity -= 0.05;
        return list;
    }

}