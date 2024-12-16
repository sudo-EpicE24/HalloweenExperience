
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
    public static ArrayList<Person> nonPlayerCharacters = new ArrayList<>(Arrays.asList(
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
    public static final double TRICK_OR_TREAT_TIME = 20/60.0; //hours
    public static final double HAUNTED_HOUSE_TIME = 90/60.0; //hours
    public static final double EAT_CANDY_TIME = 5/60.0; //hours
    
    public static void main(String[] args) throws Exception {
        while (true) {
            Person playerCharacter = Person.newPlayerCharacter();
            playerCharacter.personInfo();
            TimeUnit.SECONDS.sleep(1);

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
            TimeUnit.SECONDS.sleep(10);
            ScaryHouse.acquirePerson(playerCharacter);
            
            System.out.print("Would you like to trick or treat again? (y/n)\n>");
            if (!scan.next().equalsIgnoreCase("y")) {
                break;
            }
            
        }
        for (House house : houses) {
            house.printInfo();
            TimeUnit.SECONDS.sleep(10);
        }
        for (Person person : nonPlayerCharacters) {
            person.personInfo();
            TimeUnit.SECONDS.sleep(10);
        }
        
    }

    public static void goTrickOrTreating(Person person, boolean isPlayer) throws InterruptedException {
        ArrayList<Integer> housesGoneTo = new ArrayList<>();
        double time = 5;
        boolean fainted = false;

        if(!isPlayer){
            double returnTime = (Math.round(Math.random() * 10) / 2.0) + 6.5;

            while(time < returnTime && !fainted){
                if(person.getCurrentHP() < person.getMaxHP()){
                    //EAT CANDY
                    ArrayList<Integer> candiesHad = person.hasWhichCandies();
                    if(!candiesHad.isEmpty()){
                        int candyToEat = (int) (Math.random() * candiesHad.size());
                        int amount = (int) (Math.random() * person.getCandy(candyToEat).getCandyCount() + 1);
                        person.eatCandy(candiesHad.get(candyToEat), amount);
                        time += amount * EAT_CANDY_TIME;
                        continue;
                    }
                }

               if(Math.random() < 0.15){
                //HAUNTED HOUSE
                fainted = ScaryHouse.enterHauntedHouse(person);
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
        

        System.out.print("What time will you go home? (5.5pm-12pm)\n>");
        double returnTime = Math.max(5.5, Math.min(12, scan.nextDouble()));

        while (time < returnTime && !fainted) {
            System.out.println("What do you want to do? You are at "+person.getCurrentHP()+"/"+person.getMaxHP()+" HP.");
            System.out.println("It is "+toTimePM(time)+", you have "+toHrsMins(returnTime-time)+" remaining");

            System.out.println("1: Trick or treat ("+toHrsMins(TRICK_OR_TREAT_TIME)+")");
            System.out.println("2: Go to a haunted house ("+toHrsMins(HAUNTED_HOUSE_TIME)+")");
            System.out.println("3: Eat some candy ("+toHrsMins(EAT_CANDY_TIME)+")");
            System.out.println("4: Check info");
            System.out.print(">");

            int choice = scan.nextInt();
            System.out.println();

            switch (choice) {
                case 1 -> { // TRICK OR TREAT
                    goToHouse(person, randomHouse(housesGoneTo));
                    time += TRICK_OR_TREAT_TIME;
                }
                case 2 -> { //HAUNTED HOUSE
                    fainted = ScaryHouse.enterHauntedHouse(person);
                    time += HAUNTED_HOUSE_TIME;
                }
                case 3 -> { //EAT CANDY
                    if (!person.hasCandy()) {
                        System.out.println("You don't have any candy :(");
                        TimeUnit.SECONDS.sleep(1);
                        continue;
                    }
                    System.out.println("What candy do you want to eat? Your options are: ");
                    for (int i = 0; i < Candy.getCandyTypes(); i++) {
                        if (person.getCandy(i).getCandyCount() > 0) {
                            System.out.println(i+": "+person.getCandy(i).getCandyCount()+" "+person.getCandy(i).getCandyName()+
                                ", which will heal you for "+person.getCandy(i).getCandyHP()+" HP each");
                        }
                    }
                    System.out.print(">");
                    int candyToEat = scan.nextInt();

                    System.out.print("How much do you want to eat?\n>");
                    int amount = Math.min(scan.nextInt(), person.getCandy(candyToEat).getCandyCount());

                    person.eatCandy(candyToEat, amount);
                    time += amount * EAT_CANDY_TIME;
                }
                case 4 -> {
                    person.personInfo();
                }
                default -> {}
            }
            TimeUnit.SECONDS.sleep(1);
            System.out.println();
        }
        if (!fainted) {
            System.out.println("Its "+toTimePM(time)+", time to go home!");
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
        String newTime = ((int) Math.floor(time))+":";
        if (Math.round((time % 1) * 60) < 10 && Math.round((time % 1) * 60) > 0) {
            newTime += "0";
        }
        newTime += Math.round((time % 1) * 60);
        if (Math.round((time % 1) * 60) == 0) {
            newTime += "0";
        }
        return newTime + "pm";
    }
    public static String toHrsMins(double time) {
        String output = "";
        if ((int) Math.floor(time) > 0) {
            output += ((int) Math.floor(time)) + " hour";
            if ((int) Math.floor(time) != 1) {
                output += "s";
            }
        }
        if ((int) Math.floor(time) > 0 && (int) Math.round((time % 1) * 60) > 0) {
            output += " ";
        }
        if ((int) Math.round((time % 1) * 60) > 0) {
            output += ((int) Math.round((time % 1) * 60)) + " minute";
            if ((int) Math.round((time % 1) * 60) != 1) {
                output += "s";
            }
        }
        return output;
    }

    public static String toPercent(double convert, int decimalPlaces) {
        int extra0s = 0;
        for (int i = decimalPlaces; i > 1; i--) {
            if (convert*Math.pow(10, i+4) % 10 == 0) {
                extra0s ++;
            } else {
                break;
            }
        }
        if ((convert*100) % 1 != 0) {
            extra0s--;
        }
        if ((convert*1000) % 1 == 0 && (convert*100) % 1 != 0) {
            extra0s++;
        }
        // its not the prettiest but it works
        return (((Math.round(convert*Math.pow(10, decimalPlaces+2)))/Math.pow(10, decimalPlaces)))+"0".repeat(Math.max(extra0s, 0))+"%";
    }
}
