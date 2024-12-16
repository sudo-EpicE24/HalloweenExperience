
import java.util.ArrayList;

public class HauntedHouse {
    private final static String[] roomTypes = {
      "the library", "the nursery", "the ballroom", "the scary room", "a room solely containing a small replica of the haunted house"
    };
    private ArrayList<Person> scaryPeople;
    private String address;
    private int scaryValue;
    private int roomCount;

    public HauntedHouse() {
        scaryPeople = new ArrayList<>();
        scaryPeople.add(new Person());
        scaryValue = 1;
        roomCount = 5;
        address = "123 Main St.";
    }
    public HauntedHouse(int rooms, int howScary, ArrayList<Person> people, String address) {
        roomCount = rooms;
        scaryValue = howScary;
        scaryPeople = people;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void enterHauntedHouse(Person person) {
        for (int i = 0; i < roomCount && person.getCurrentHP() > 0; i++) {
            int j = i%roomTypes.length;
            Person scaryPerson = scaryPeople.get((int) (Math.random()*scaryPeople.size()));
            String roomEntered = roomTypes[j];

            System.out.println(person.getName() + "entered " + roomEntered + " and saw a " + scaryPerson.getCostume());
            double howScared = person.howScared(scaryPerson.getScaryValue());
            if (howScared > 0) {
                int damageDone = Math.max(1, (int) Math.round(howScared*scaryValue));
                System.out.println("They got scared and took "+damageDone+" damage");
                person.takeDamage(damageDone, this);
            } else {
                person.becomeScarier(scaryPerson.getScaryValue()/100);
                System.out.println("They did not get scared and laughed at the actor "+scaryPerson.getName());
                System.out.println("this made them "+app.toPercent(scaryPerson.getScaryValue()/100, 2)+" scarier");
            }
        }
    }

    public void acquirePerson(Person person) {
        person.becomeScarier(scaryValue/10);
        roomCount++;
        scaryPeople.add(person);
    }
}
