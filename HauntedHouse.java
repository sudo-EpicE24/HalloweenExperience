
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class HauntedHouse {
    private final static String[] roomTypes = {
      "the library", "the nursery", "the ballroom", "the scary room", "a room solely containing a small replica of the haunted house"
    };

    private ArrayList<Person> scaryPeople;
    private String[] roomOrder;
    private final String address;
    private int scaryValue;
    private int roomCount;

    public HauntedHouse() {
        roomOrder = roomTypes.clone();
        Collections.shuffle(Arrays.asList(roomOrder));
        scaryPeople = new ArrayList<>(Arrays.asList(new Person()));
        scaryValue = 1;
        roomCount = 5;
        address = "123 Main St.";
    }
    public HauntedHouse(int rooms, int howScary, ArrayList<Person> people, String address) {
        roomOrder = roomTypes.clone();
        Collections.shuffle(Arrays.asList(roomOrder));
        roomCount = rooms;
        scaryValue = howScary;
        scaryPeople = people;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public boolean enterHauntedHouse(Person person) throws InterruptedException {
        System.out.println(person.getName()+" entered the haunted house at "+address+".");
        TimeUnit.MILLISECONDS.sleep(1500);
        for (int i = 0; i < roomCount && person.getCurrentHP() > 0; i++) {
            int j = i%roomOrder.length;
            Person scaryPerson = scaryPeople.get((int) (Math.random()*scaryPeople.size()));
            String roomEntered = roomOrder[j];

            System.out.println(person.getName() + " entered " + roomEntered + " and saw a " + scaryPerson.getCostume());
            TimeUnit.MILLISECONDS.sleep(1500);
            double howScared = person.howScared(scaryPerson.getScaryValue());
            if (howScared > 0) {
                int damageDone = Math.max(1, (int) Math.round(howScared*scaryValue));
                System.out.println("They got scared and took "+damageDone+" damage");
                TimeUnit.SECONDS.sleep(1);
                if (person.takeDamage(damageDone, this)) {
                    TimeUnit.SECONDS.sleep(2);
                    return true;
                }
            } else {
                person.becomeScarier(scaryPerson.getScaryValue()/100);
                System.out.println("They did not get scared and laughed at the actor "+scaryPerson.getName());
                System.out.println("This made them "+app.toPercent(scaryPerson.getScaryValue()/100, 2)+" scarier");
            }
            TimeUnit.SECONDS.sleep(2);
        }
        return false;
    }

    public void acquirePerson(Person person) {
        person.becomeScarier(scaryValue/100.0);
        roomCount++;
        scaryValue++;
        scaryPeople.add(person);
    }
}
