public class Person {
    //ATTRIBUTES: accessModifier dataType attributeName
    private String name;
    private int age;
    private String identification;

    //BUILDER: accessModifier ClassName(arguments){assignations}
    public Person(String name, int age, String identification){
        this.name = name;
        this.age = age;
        this.identification = identification;
    }

    //METHODS: accessModifier outType name(arguments){operation}
    public boolean adult(){
        if (this.age>18){
            return true;
        } else {
            return false;
        }
    }

    public void showInformation(){
        System.out.println("Mi nombre es " + this.name + " mi edad es " + this.age + " y mi identificación es " + this.identification);
    }

    // GETTER AND SETTER
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

}
