package EjercicioParqueadero;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Parqueadero parqueadero = new Parqueadero(4500);
        boolean validadorParquear = true;
        while (validadorParquear){
            System.out.println("¿Desea parquear un carro? Ingrese 1 si sí, y cualquier otro número si no");
            int respuesta = scanner.nextInt();
            if (respuesta == 1){
                scanner.nextLine();
                System.out.println("Ingrese la placa del carro");
                String placa = scanner.nextLine();
                System.out.println("Ingrese la marca del carro");
                String marca = scanner.nextLine();
                System.out.println("Ingrese el modelo del carro");
                String modelo = scanner.nextLine();
                Carro carro = new Carro(placa,marca,modelo);
                System.out.println("Ingrese el tiempo que lo desea parquear");
                int tiempo = scanner.nextInt();
                parqueadero.cobrarPorTiempo(carro,tiempo);
                System.out.println("Ingrese la fila donde lo desea parquear");
                int fila = scanner.nextInt();
                System.out.println("Ingrese la columna donde lo desea parquear");
                int columna = scanner.nextInt();
                parqueadero.parquearCarro(carro,fila,columna);
            } else {
                validadorParquear = false;
            }
        }
        parqueadero.mostrarParqueadero();
    }
}
