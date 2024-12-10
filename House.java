import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Dictionary;

public class House{

    public static String[] candyList = {"Twix","Skittles","Snickers","Almond Joy"};

    private boolean isDecorated;
    private ArrayList<Integer> candyIndexs;

    public House(){

        this.candyIndexs = new ArrayList<Integer>();
        int amtOfCandyTypes = (int) Math.random() * candyList.length;


        //select (amtOfCandyTypes) amount of candies from candyList and add their index in candyList to (candyIndexs)
        for(int i = 0; i < amtOfCandyTypes ; i ++){
            int randIndex = (int) Math.random() * candyList.length;
            while(candyIndexs.contains(randIndex)){
                randIndex = (int) Math.random() * candyList.length;
            }
            this.candyIndexs.add(randIndex);
        }

        this.isDecorated = (Math.random() > 0.5) ? true : false;
    }

    public Dictionary<Integer, Candy> getCandy(int amt){
        return Dictionary<>
    }

}