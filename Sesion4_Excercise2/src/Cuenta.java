import java.util.Random;

public class Cuenta {
    // Atributos
    private double saldoDeCuenta;
    private String nombreDelTitular;
    private long numeroDeCuenta;
    // Constructor
    public Cuenta(String nombreDelTitular,double saldoDeCuenta){
        this.nombreDelTitular = nombreDelTitular;
        this.saldoDeCuenta =  saldoDeCuenta;
        this.numeroDeCuenta = new Random().nextLong();
    }
    // Métodos
    public void setIngreso(double ingreso){
        this.saldoDeCuenta = this.saldoDeCuenta+ingreso;
    }
    public void setRetiro(double retiro){
        this.saldoDeCuenta = this.saldoDeCuenta-retiro;
    }

    public double getSaldoDeCuenta() {
        return saldoDeCuenta;
    }
    public long getDatosCuenta(){
        return numeroDeCuenta;
    }
}
