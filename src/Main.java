public class Main {

    public static void main(String[] args) {
        Calculator  newCal = new Calculator();
        System.out.println(newCal.plus(4,8)+"\n"+newCal.min(8,4));

        CalDrob newCalDrob = new CalDrob();
        System.out.println("\n"+newCalDrob.plus(4,5)+"\n"+newCalDrob.min(4,5));
    }
}
