public class app {
    public static void main(String[] args) throws Exception {
        Person john = new Person();
        House house = new House();
        goToHouse(john, house);
        john.personInfo();

    }

    public static void goToHouse(Person person, House house) {
        person.trickOrTreat(house.trickOrTreat(), house);
    }
}
