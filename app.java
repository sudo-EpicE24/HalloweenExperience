
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class app {
    public static final Scanner scan = new Scanner(System.in);

    public static final House[] houses = {
        new House("House on House St"),
        new House("1600 Pennsylvania Avenue"),
        new House("1600 City Park Esplanade"),
        new House("10 Downing St"),
        new House("202 Garstang St"),
        new House("Delaware"),
        new House("89 E 42nd St"),
        new House("Your house"),
        new House("My house"),
        new House("221B Baker Street"),
        new House("The North Pole"),
        new House("Gettysburg"),
        new House("Bag End"),
        new House("234 Second Ave")
    };
    public static final ArrayList<Person> nonPlayerCharacters = new ArrayList<>(Arrays.asList(
        new Person("Courtney", "Corn being", 0.8, 20),
        new Person("Link", "Bone man", 0.8, 15),
        new Person("Xander", "Pumpkin guy", 0.7, 30),
        new Person("The Queen of England", "Evil James Bond", 0.5, Integer.MAX_VALUE-1),
        new Person("The Queen of England (Evil Mode)", "James Bond", 0.95, Integer.MAX_VALUE),
        new Person("The King of England", "The Queen of England", 1, 1),
        new Person("Joe Biden", "The Crown Jewels", 0, 1),
        new Person("Nameless Child", "Named child"),
        new Person("John", "Johns Clothes")
    ));
    public static final HauntedHouse ScaryHouse = new HauntedHouse(5, 10, nonPlayerCharacters, "London EC3N 4AB, UK");
    public static final double TRICK_OR_TREAT_TIME = 30/60.0;
    public static final double HAUNTED_HOUSE_TIME = 60/60.0;
    public static final double EAT_CANDY_TIME = 5/60.0;
    
    public static void main(String[] args) throws Exception {
        while (true) {
            Person playerCharacter = Person.newPlayerCharacter();
            playerCharacter.personInfo();

            System.out.print("Should there be others trick or treating? (y/n)\n>");
            if (scan.next().equalsIgnoreCase("y")) {
                for (Person person : nonPlayerCharacters) {
                    goTrickOrTreating(person, false);
                    person.personInfo();
                    TimeUnit.SECONDS.sleep(10);
                }
            }

            
            goTrickOrTreating(playerCharacter, true);
            playerCharacter.personInfo();
            ScaryHouse.acquirePerson(playerCharacter);

            System.out.print("Would you like to trick or treat again? (y/n)\n>");
            if (!scan.next().equalsIgnoreCase("y")) {
                break;
            }
        }
        
    }

    public static void goTrickOrTreating(Person person, boolean isPlayer) throws InterruptedException {
        ArrayList<Integer> housesGoneTo = new ArrayList<>();
        double time = 5;

        if(!isPlayer){
            double returnTime = (Math.round(Math.random() * 10) / 2.0) + 6.5;

            while(time < returnTime){
                if(person.getCurrentHP() < person.getMaxHP()){
                    //EAT CANDY
                    ArrayList<Integer> candiesHad = person.hasWhichCandies();
                    if(!candiesHad.isEmpty()){
                        person.eatCandy(candiesHad.get((int) (Math.random() * candiesHad.size())), 0);
                        time += EAT_CANDY_TIME;
                        continue;
                    }
                }

               if(Math.random() < 0.15){
                //HAUNTED HOUSE
                ScaryHouse.enterHauntedHouse(person);
                time += HAUNTED_HOUSE_TIME;
               }else{
                //TRICK OR TREAT
                goToHouse(person, randomHouse(housesGoneTo));
                time += TRICK_OR_TREAT_TIME;
               }
               System.out.println();
            }

            return;
        }
        

        System.out.println("What time will you go home? (5.5pm-12pm)\n>");
        double returnTime = Math.max(5.5, Math.min(12, scan.nextDouble()));

        while (time < returnTime) {
            System.out.println("What do you want to do? You are at "+person.getCurrentHP()+"/"+person.getMaxHP()+" HP.");
            System.out.println("It is "+toTimePM(time));
            System.out.println("1: Trick or treat");
            System.out.println("2: Go to a haunted house");
            System.out.println("3: Eat some candy");
            System.out.print(">");

            int choice = scan.nextInt();
            System.out.println();

            switch (choice) {
                case 1: // TRICK OR TREAT
                    goToHouse(person, randomHouse(housesGoneTo));
                    time += TRICK_OR_TREAT_TIME;
                case 2: //HAUNTED HOUSE
                    ScaryHouse.enterHauntedHouse(person);
                    time += HAUNTED_HOUSE_TIME;
                case 3: //EAT CANDY
                    System.out.println("What candy do you want to eat? Your options are: ");
                    for (int i = 0; i < Candy.getCandyTypes(); i++) {
                        if (person.getCandy(i).getCandyCount() > 0) {
                            System.out.println(i+": "+person.getCandy(i).getCandyCount()+" "+person.getCandy(i).getCandyName());
                        }
                    }
                    System.out.print(">");
                    int candyToEat = scan.nextInt();

                    System.out.println("How much do you want to eat?\n>");
                    int amount = scan.nextInt();

                    person.eatCandy(candyToEat, amount);
                    time += EAT_CANDY_TIME;
                default:
            }
            TimeUnit.SECONDS.sleep(1);
            System.out.println();
        }
    }


    public static void goToHouse(Person person, House house) {
        person.trickOrTreat(house.trickOrTreat(), house);
    }
    public static House randomHouse(ArrayList<Integer> visitedHouses) {

        if (visitedHouses.size() >= houses.length) {
            visitedHouses.clear();
            return new House("The overflow house on Bepis lane");
        }

        int randIndex = (int) (Math.random() * houses.length);
        while (visitedHouses.contains(randIndex)) {
            randIndex = (int) (Math.random() * houses.length);
        }
        visitedHouses.add(randIndex);
        return houses[randIndex];
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
