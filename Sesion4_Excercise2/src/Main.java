// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        /*
        Cuenta cuenta1 = new Cuenta("Pepito", 100);
        System.out.println("Saldo inicial: " + cuenta1.getSaldoDeCuenta());
        cuenta1.setIngreso(50);
        System.out.println("Nuevo saldo: " + cuenta1.getSaldoDeCuenta());
        cuenta1.setRetiro(100);
        System.out.println("Nuevo saldo: " + cuenta1.getSaldoDeCuenta());
        System.out.println("El número de la cuenta es: " + cuenta1.getDatosCuenta());
        */
        Cuenta cuenta1 = new Cuenta("Pepito", 100);
        Cuenta cuenta2 = new Cuenta("Ana", 500);

        Banco banco = new Banco(cuenta1, cuenta2);

        banco.imprimirNumeroCuentas();
        System.out.println("El saldo de la cuenta " + cuenta1.getDatosCuenta() + " antes de la" +
                " transacción es " + cuenta1.getSaldoDeCuenta());
        banco.realizarTransferenciaEntreCuentas(cuenta2, cuenta1, 200);
        System.out.println("El saldo de la cuenta " + cuenta1.getDatosCuenta() + " después de la" +
                " transacción es " + cuenta1.getSaldoDeCuenta());
    }
}