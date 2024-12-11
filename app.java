public class app {
    public static void main(String[] args) throws Exception {

        Person john = new Person();
        House house = new House();
        goToHouse(john, house);
        john.personInfo();
        john.trickOrTreatList();

    }

    public static void goToHouse(Person person, House house) {
        person.trickOrTreat(house.trickOrTreat(), house);
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
