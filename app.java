
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class app {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        ArrayList<Person> scareActors = new ArrayList<>();
        scareActors.addAll(Arrays.asList(
            new Person("Courtney", "Corn being", 0.8, 20),
            new Person("Link", "Bone man", 0.8, 15),
            new Person("Xander", "Pumpkin guy", 0.7, 30),
            new Person("The Queen of England", "Evil James Bond", 0.5, Integer.MAX_VALUE-1),
            new Person("The Queen of England (Evil Mode)", "James Bond", 0.95, Integer.MAX_VALUE),
            new Person("The King of England", "The Queen of England", 1, 1),
            new Person("Joe Biden", "The Crown Jewels", 0, 1)
        ));
        House house1 = new House("House on House St");
        House house2 = new House("1600 Pennsylvania Avenue");
        House house3 = new House("1600 City Park Esplanade");
        House house4 = new House("10 Downing St");
        House house5 = new House("202 Garstang St");
        House house6 = new House("Delaware");
        House house7 = new House("89 E 42nd St");
        House house8 = new House("Your house");
        HauntedHouse ScaryHouse = new HauntedHouse(5, 10, scareActors, "London EC3N 4AB, UK");

        Person playerCharacter = Person.newPlayerCharacter();
        playerCharacter.personInfo();



    }

    public static void goToHouse(Person person, House house) {
        person.trickOrTreat(house.trickOrTreat(), house);
    }

    public static String toPercent(double convert, int decimalPlaces) {
        String extra0 = "";
        for (int i = decimalPlaces; i>0; i--) {
            if (convert*Math.pow(10, i) % 10 == 0) {
                extra0 += "0";
            } else {
                break;
            }
        }
        return (((Math.round(convert*Math.pow(10, decimalPlaces+2)))/Math.pow(10, decimalPlaces)))+extra0+"%";
    }
}
