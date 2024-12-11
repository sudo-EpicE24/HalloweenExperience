
public class Person {
    private static String[] tricks = {
        "TP house", "Place raw egg on porch", "Ring doorbell and leave"
    };

    private String name;
    private String costume;
    private int age;
    private int maxHP;
    private int HP;
    private int candyCount[] = new int[Candy.candyTypes()];

    public Person() {
        name = "Child";
        costume = "Regular Garbs";
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
