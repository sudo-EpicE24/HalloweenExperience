
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class app {
    public static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        ArrayList<Person> nonPlayerCharacters = new ArrayList<>();
        nonPlayerCharacters.addAll(Arrays.asList(
            new Person("Courtney", "Corn being", 0.8, 20),
            new Person("Link", "Bone man", 0.8, 15),
            new Person("Xander", "Pumpkin guy", 0.7, 30),
            new Person("The Queen of England", "Evil James Bond", 0.5, Integer.MAX_VALUE-1),
            new Person("The Queen of England (Evil Mode)", "James Bond", 0.95, Integer.MAX_VALUE),
            new Person("The King of England", "The Queen of England", 1, 1),
            new Person("Joe Biden", "The Crown Jewels", 0, 1)
        ));
        House[] houses = {
            new House("House on House St"),
            new House("1600 Pennsylvania Avenue"),
            new House("1600 City Park Esplanade"),
            new House("10 Downing St"),
            new House("202 Garstang St"),
            new House("Delaware"),
            new House("89 E 42nd St"),
            new House("Your house"),
            new House("Your house"),
            new House("Your house"),
            new House("Your house"),
            new House("Your house"),
            new House("Your house"),
            new House("Your house")
        };
        HauntedHouse ScaryHouse = new HauntedHouse(5, 10, nonPlayerCharacters, "London EC3N 4AB, UK");

        Person playerCharacter = Person.newPlayerCharacter();
        playerCharacter.personInfo();

    }

    public static void goTrickOrTreating(Person person, boolean isPlayer) {
        System.out.println("What time will you go home? (5.5pm-12pm) ");
        double returnTime = Math.max(5.5, Math.min(12, scan.nextDouble()));
        double time = 5;

        while (time < returnTime) {
            System.out.println("What do you want to do? You are at "+person.getCurrentHP()+"/"+person.getMaxHP()+" HP.");
            System.out.println("It is "+toTimePM(time)+". You are going home at "+toTimePM(returnTime));
            System.out.println("1: trick or treat");
            System.out.println("2: go to a haunted house");
            System.out.println("3: eat some candy");
            System.out.print(">");

            int choice = scan.nextInt();

            if (choice == 1) {

            } else if (choice == 2) {

            } else if (choice == 3) {
                System.out.println("What candy do you want to eat? Your options are: ");
                for (int i = 0; i < Candy.getCandyTypes(); i++) {
                    if (person.getCandy(i).getCandyCount() > 0) {
                        System.out.println(i+": "+person.getCandy(i).getCandyCount()+" "+person.getCandy(i).getCandyName());
                    }
                }
                int candyToEat = scan.nextInt();

                System.out.println("How much do you want to eat? ");
                int amount = scan.nextInt();

                person.eatCandy(candyToEat, amount);
            }
        }
    }


    public static void goToHouse(Person person, House house) {
        person.trickOrTreat(house.trickOrTreat(), house);
    }

    public static String toTimePM(double time) {
        String newTime = Math.floor(time)+":";
        newTime += (time % 1) * 60;
        return newTime + "pm";
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
