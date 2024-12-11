import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Dictionary;

public class House{

    private boolean isDecorated;
    private ArrayList<Integer> candyInventory;
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

    public Dictionary<Integer, Candy> getCandy(int amt){
        list = new Dictionary<Integer, Candy>();
    }

}