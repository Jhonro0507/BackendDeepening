import java.util.Scanner;

public class Banco {
    Scanner sc = new Scanner(System.in);
    // Atributos
    private Cuenta cuenta1;
    private Cuenta cuenta2;
    // Constructor
     public Banco(Cuenta cuenta1, Cuenta cuenta2){
         this.cuenta1 = cuenta1;
         this.cuenta2 = cuenta2;
     }
    // Métodos
    public void realizarTransferenciaEntreCuentas(Cuenta cuentaRetiro, Cuenta cuentaIngreso, double transferencia){
         cuentaRetiro.setRetiro(transferencia);
         cuentaIngreso.setIngreso(transferencia);
    }
    public void imprimirNumeroCuentas(){
         System.out.println("El número de la cuenta 1 es " + this.cuenta1.getDatosCuenta());
         System.out.println("y el número de la cuenta 2 es " + this.cuenta2.getDatosCuenta());
    }
}
