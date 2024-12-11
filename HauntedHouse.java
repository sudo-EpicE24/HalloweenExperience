
import java.util.ArrayList;

public class HauntedHouse {
    private final static String[] roomTypes = {
      "Library", "Nursery", "Ballroom", "The scary room", "A room solely containing a small replica of the haunted house"
    };
    private ArrayList<Person> scaryPeople = new ArrayList<>();
    private int roomCount;

    public HauntedHouse() {
        roomCount = 5;
        scaryPeople.add(new Person());
    }

    public void enterHouse(Person person) {
        
    }

    public void acquirePerson(Person person) {
        scaryPeople.add(person);
    }
}
