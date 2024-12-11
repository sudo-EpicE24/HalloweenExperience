import java.util.Map;

public class Person {
    private static String[] tricks = {
        "TPed the house", "Politely placed a raw egg on the porch", "Rang the doorbell and left",
        "Released evidence of tax fraud by the owner", "Renovated the house with asbestos"
    };
    private int tricksPerformed = 0;
    private int treatsReceived = 0;
    private String trickOrTreatList = "";

    private String name;
    private String costume;
    private int age;
    private int maxHP;
    private int HP;
    private int candyCount[] = new int[Candy.candyTypes()];

    public Person() {
        name = "Child";
        costume = "Human Garb";
        age = 0;
        maxHP = 1;
        HP = maxHP;
        for (int i = 0; i < candyCount.length; i++) {
            candyCount[i] = 0;
        }
    }
    public Person(String name, String costume, int age, int maxHP) {
        this.name = name;
        this.costume = costume;
        this.age = age;
        this.maxHP = maxHP;
        HP = maxHP;
    }

    public void personInfo() {
        System.out.println(name+" is a "+age+" year old wearing "+costume);
        System.out.println("They are at "+HP+"/"+maxHP+" HP");
        System.out.print("They have ");
        boolean noCandy = true;
        for (int i = 0; i < candyCount.length; i++) {
            if (candyCount[i] > 0) {
                noCandy = false;
                System.out.print(candyCount[i]+" "+Candy.candyName(i)+", ");
            }
        }
        if (noCandy) {
            System.out.println("no candy :(");
        }
    }
    public void trickOrTreatList() {
        System.out.println(name+" got candy at "+treatsReceived+" houses and performed "+tricksPerformed+" tricks:");
        System.out.println(trickOrTreatList);
    }

    public void trickOrTreat(boolean treat, House house) {
        if (treat) {
            treat(house, house.getCandy());
        } else {
            trick(house);
        }
    }
    public void treat(House house, Map<Candy, Integer> candyGiven) {
            for (Map.Entry<Candy, Integer> candies : candyGiven.entrySet()) {
                candyCount[candies.getKey().indexOfCandy()] += candies.getValue();
            }
            treatsReceived++;

            trickOrTreatList += candyGiven.size()+" treats received from " + house.getAddress() + ", \n";
    }
    public void trick(House house) {
        String trick = tricks[(int) (Math.random()*tricks.length)];
        tricksPerformed++;

        trickOrTreatList += trick+" performed at " + house.getAddress() + ", \n";
    }


    public void giveCandy(int i, int amount) {
        candyCount[i] += amount;
    }
    public void eatCandy(int i, int amount) {
        eatCandy(Candy.returnCandy(i), amount);
    }
    public void eatCandy(Candy candy, int amount) {
        HP = Math.min(HP+(candy.candyHP()*amount), maxHP);
    }

    public void takeDamage(int damage) {
        HP = Math.max(0, HP-damage);
        if (HP == 0) {
            faint();
        }
    }
    
    private void faint() {
        //TODO: implement
    }

}
