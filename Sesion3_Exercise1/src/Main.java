public class Main {
    public static void main(String[] args) {
        Person juan = new Person("Juan", 17, "CC123");
        boolean adult = juan.adult();
        juan.showInformation();
        juan.setName("Mateo");
        String name = juan.getName();
    }
}