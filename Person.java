
import java.util.ArrayList;
import java.util.Scanner;


public class Person {
    private static String[] tricks = {
        "TPed the house", "Politely placed a raw egg on the porch", "Rang the doorbell and left",
        "Released evidence of tax fraud by the owner", "Renovated the house with asbestos"
    };
    private int tricksPerformed;
    private int treatsReceived;
    private ArrayList<String> trickOrTreatList;

    private String name;
    private String costume;
    private double scaryValue;
    private int maxHP;
    private int HP;
    private Candy[] candyBag;

    public Person() {
        treatsReceived = 0;
        tricksPerformed = 0;
        trickOrTreatList = new ArrayList<>();
        name = "Child";
        costume = "Human Garb";
        scaryValue = Math.random();
        maxHP = 1;
        HP = maxHP;
        candyBag = Candy.generateEmptyCandyList();
    }
    public Person(String name, String costume, double scaryValue, int maxHP) {
        this();
        this.name = name;
        this.costume = costume;
        this.scaryValue = scaryValue;
        this.maxHP = maxHP;
        HP = maxHP;
    }

    public void personInfo() {
        System.out.println(name+" is a wearing a "+costume+" costume that has "+app.toPercent(scaryValue, 2)+" scare power");
        System.out.println("They are at "+HP+"/"+maxHP+" HP");
        System.out.print("They have ");
        boolean noCandy = true;
        for (int i = 0; i < candyBag.length; i++) {
            if (candyBag[i].getCandyCount() > 0) {
                noCandy = false;
                System.out.print(candyBag[i].getCandyCount()+" "+Candy.getCandyName(i)+", ");
            }
        }
        if (noCandy) {
            System.out.print("no candy :(");
        }
        System.out.println();
    }
    public void trickOrTreatList() {
        System.out.println(name+" got candy at "+treatsReceived+" houses and performed "+tricksPerformed+" tricks:");
        for (String action: trickOrTreatList) {
            System.out.println(action);
        }
    }
    public double getScaryValue() {
        return scaryValue;
    }
    public int getCurrentHP() {
        return HP;
    }
    public String getName() {
        return name;
    }
    public String getCostume() {
        return costume;
    }

    public static Person newPlayerCharacter() {
        Scanner scan = new Scanner(System.in);

        System.out.print("What is your name? ");
        String userName = scan.nextLine();

        System.out.print("What are you dressed as? ");
        String userCostume = scan.nextLine();

        System.out.print("How scary are you on a scale of 0 to 100? ");
        double userScaryValue = scan.nextInt()/100;

        System.out.print("What is your HP? ");
        int userHP = scan.nextInt();

        return new Person(userName, userCostume, userScaryValue, userHP);
    }

    public void trickOrTreat(boolean treat, House house) {
        if (treat) {
            // treat(house, house.getCandy());
            treat(house.getAddress(), house.giveCandy());
        } else {
            trick(house);
        }
    }

    public void treat(String address, Candy[] candyList) {
        int candyGiven = 0;
        for (int i = 0; i < candyList.length; i++) {
            candyBag[i].addCandy(candyList[i].getCandyCount());
            candyGiven += candyList[i].getCandyCount();
        }

        treatsReceived++;
        trickOrTreatList.add(name+" received "+candyGiven + " treats from " + address);
    }

    public void trick(House house) {
        String trick = tricks[(int) (Math.random()*tricks.length)];
        tricksPerformed++;

        trickOrTreatList.add(trick+" at " + house.getAddress());
    }


    public void giveCandy(int i, int amount) {
        candyBag[i].addCandy(amount);
    }
    public void eatCandy(int i, int amount) {
        amount = Math.min(amount, candyBag[i].getCandyCount());
        int newHP = Math.min(HP+(candyBag[i].getCandyHP()*amount), maxHP);
        System.out.println("You healed "+(newHP-HP)+" HP");
        System.out.println("You are now at "+newHP+"/"+maxHP+" HP");
        HP = newHP;
        candyBag[i].addCandy(-amount);
    }

    public void takeDamage(int damage, HauntedHouse damageLocation) {
        HP = Math.max(0, HP-damage);
        if (HP <= 0) {
            System.out.println("They got so scared they passed out");
            System.out.println("when they woke up they had become part of the haunted house at "+damageLocation.getAddress());
            damageLocation.acquirePerson(this);
            HP = maxHP;
        }
    }
    public double howScared(double scaredByScaryValue) {
        double scaryChance = (scaryValue - scaredByScaryValue + 1) / 2;
        return Math.max(Math.random()-scaryChance,0);
    }
    public void becomeScarier(double increase) {
        scaryValue = Math.min(1, scaryValue+increase);
    }

}
