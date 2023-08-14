public class Main {
    public static void main(String[] args){
        // Motor creation
        Motor carMotor = new Motor("China", "China23");
        // Rim creation
        Rim rim1 = new Rim("Rim brand", "1");
        Rim rim2 = new Rim("Rim brand", "2");
        Rim rim3 = new Rim("Rim brand", "3");
        Rim rim4 = new Rim("Rim brand", "4");
        // Car creation
        Car mazdaCar = new Car(carMotor);
        mazdaCar.addRims(rim1);
        mazdaCar.addRims(rim2);
        mazdaCar.addRims(rim3);
        mazdaCar.addRims(rim4);
        // Get principal class relations which is car
        System.out.println(mazdaCar.getMotor());
    }
}
