
public class Person {
    private static String[] tricks = {
        "TP house", "Place raw egg on porch", "Ring doorbell and leave"
    };

    private String name;
    private String costume;
    private int age;
    private int candyCount[] = new int[House.candyList.length];

    public Person() {
        name = "John Doe";
        costume = "Regular cloths";
        age = 0;
        for (int i = 0; i < candyCount.length; i++) {
            candyCount[i] = 0;
        }
    }

    public void personInfo() {
        System.out.println(name+" is a "+age+" year old wearing "+costume);
        System.out.print("They have ");
        for (int i = 0; i < candyCount.length; i++) {
            if (candyCount[i] > 0) {
                System.out.println(candyCount[i]+" "+House.candyList[i]+", ");
            }
        }

    } 

}
