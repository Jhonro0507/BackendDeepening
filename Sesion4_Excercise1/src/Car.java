public class Car {
    // ATTRIBUTES
    private Motor motor;
    private Rim[] rim;
    private int rimCounter = 0;

    // BUILDER
    public Car(Motor motor){
        this.motor = motor;
        this.rim = new Rim[4];
    }
    // METHODS
    public void addRims(Rim rim){
        this.rim[rimCounter] = rim;
        this.rimCounter++;
    }
    // SETTER AND GETTER
    public Motor getMotor(){
        return this.motor;
    }

}
