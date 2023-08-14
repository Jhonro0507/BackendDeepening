import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // tipoDato nombre = asignación
        int precioUnitario = 40000;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese la cantidad de sillas");
        int cantidadSillas = scanner.nextInt();
        double descuento = 0.0;

        if (cantidadSillas > 5 && cantidadSillas <= 12){
            descuento = 0.1;
        } else if (cantidadSillas > 12 && cantidadSillas <= 40) {
            descuento = 0.2;
        } else if (cantidadSillas > 40) {
            descuento = 0.3;
        }

        double precioConDescuento = precioUnitario * cantidadSillas * descuento;
        System.out.println("El descuento total de la compra es : " + precioConDescuento);

    }
}