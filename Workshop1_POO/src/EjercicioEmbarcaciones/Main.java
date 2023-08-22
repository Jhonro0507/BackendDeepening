package EjercicioEmbarcaciones;

import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static Scanner scanner = new Scanner(System.in);
    //Creamos dos ArrayList de yates y veleros que almacenaran todos las embarcaciones
    public static ArrayList<Velero> veleros = new ArrayList<>();
    public static ArrayList<Yate> yates = new ArrayList<>();
    public static ArrayList<Capitan> capitanes = new ArrayList<>();

    public static void agregarYate(Yate yate) {
        yates.add(yate);
    } //metodo para agregar yates al arraylist

    public static void agregarVelero(Velero velero) {
        veleros.add(velero);
    } //metodo para agregar veleros al arraylist
    public static void agregarCapitan(Capitan capitan) {
        capitanes.add(capitan);
    } //metodo para agregar veleros al arraylist

    public static void informe() { //Metodo para mostrar todos los veleros y yates disponibles
        System.out.printf("%20s","Capitanes");
        System.out.println();
        System.out.printf("%15s","Nombre");
        System.out.printf("%15s","Apellido");
        System.out.printf("%15s","Matricula");
        for (Capitan capitan : capitanes) {
            System.out.println();
            System.out.printf("%15s",capitan.getNombre());
            System.out.printf("%15s",capitan.getApellido());
            System.out.printf("%15s",capitan.getMatriculaNavegacion());
        }
        System.out.println();
        System.out.println();


        System.out.printf("%35s","Yates");
        System.out.println();
        System.out.printf("%15s","Capitán");
        System.out.printf("%15s","Precio Base");
        System.out.printf("%15s","Monto alquiler");
        System.out.printf("%15s","A la venta");
        System.out.printf("%15s","Lujo");
        for (Yate yate : yates) {
            System.out.println();
            System.out.printf("%15s",yate.getCapitan().getNombre());
            System.out.printf("%15.0f",yate.getPrecioBase());
            System.out.printf("%15.0f",yate.calcularMonto());
            System.out.printf("%15s",yate.comprar());
            System.out.printf("%15s",yate.tamano());
        }
        System.out.println(" ");
        System.out.println(" ");




        System.out.printf("%34s","Veleros");
        System.out.println();
        System.out.printf("%15s","Capitán");
        System.out.printf("%15s","Precio Base");
        System.out.printf("%15s","Monto alquiler");
        System.out.printf("%15s","A la venta");
        System.out.printf("%15s","Tamaño");
        for (Velero velero : veleros) {
            System.out.println();
            System.out.printf("%15s",velero.getCapitan().getNombre());
            System.out.printf("%15.0f",velero.getPrecioBase());
            System.out.printf("%15.0f",velero.calcularMonto());
            System.out.printf("%15s",velero.comprar());
            System.out.printf("%15s",velero.tamano());
        }
        System.out.println(" ");
        System.out.println(" ");
    }

    public static void main(String[] args) {
        // Cantidad de capitanes
        System.out.println("Ingrese la cantidad de capitanes en la flota");
        int cantidadCapitanes = scanner.nextInt();
        boolean validadorCapitan = true;
        while (validadorCapitan) {
            if (cantidadCapitanes<1){
                System.out.println("La cantidad de capitanes debe ser mayor a cero");
                System.out.println("Ingrese la cantidad de capitanes en la flota");
                cantidadCapitanes = scanner.nextInt();
                } else {
                validadorCapitan = false;
            }
        }
        scanner.nextLine();
        int totalVeleros = 0;
        int totalYates = 0;
        // Para los capitanes
        for(int i = 1; i <= cantidadCapitanes; i = i + 1){
            System.out.println("Nombre del capitan " +i);
            String nombre = scanner.nextLine();
            System.out.println("Apellido del capitan " +i);
            String apellido = scanner.nextLine();
            System.out.println("Matrícula de navegación del capitan " +i);
            String matricula = scanner.nextLine();
            Capitan capitan = new Capitan(nombre,apellido, matricula);
            agregarCapitan(capitan);
            // Embarcaciones
            System.out.println("Ingrese la cantidad de veleros a cargo del capitan " +nombre);
            int cantidadVeleros = scanner.nextInt();
            System.out.println("Ingrese la cantidad de yates a cargo del capitan " +nombre);
            int cantidadYates = scanner.nextInt();
            boolean validadorEmbarcaciones = true;
            while(validadorEmbarcaciones){
                if(cantidadYates<0|cantidadVeleros<0|(cantidadVeleros==0 && cantidadYates==0)){
                    if(cantidadYates<0){
                        System.out.println("La cantidad de yates no puede ser menor a cero");
                        System.out.println("Ingrese la cantidad de yates a cargo del capitan " +nombre);
                        cantidadYates = scanner.nextInt();
                    } else if (cantidadVeleros<0) {
                        System.out.println("La cantidad de veleros no puede ser menor a cero");
                        System.out.println("Ingrese la cantidad de veleros a cargo del capitan " +nombre);
                        cantidadVeleros = scanner.nextInt();
                    } else {
                        System.out.println("El capitán " +nombre+ " debe tener por lo menos una embarcación a su cargo");
                        System.out.println("Ingrese la cantidad de veleros a cargo del capitan " +nombre);
                        cantidadVeleros = scanner.nextInt();
                        System.out.println("Ingrese la cantidad de yates a cargo del capitan " +nombre);
                        cantidadYates = scanner.nextInt();
                    }
                } else {
                    validadorEmbarcaciones = false;
                }
            }
            totalVeleros = totalVeleros + cantidadVeleros;
            totalYates = totalYates + cantidadYates;
            // Veleros
            for(int j = 1; j <= cantidadVeleros; j = j + 1){
                System.out.println("Datos del velero "+j+" a cargo del capitan "+nombre);
                System.out.println("Ingrese el año de fabricación");
                int anoFabricacion = scanner.nextInt();
                System.out.println("Ingrese la eslora (longitd) en metros");
                int eslora = scanner.nextInt();
                System.out.println("ingrese el precio base en pesos");
                double precioBase = scanner.nextDouble();
                System.out.println("Ingrese la cantidad de mástiles");
                int cantidadMastiles = scanner.nextInt();
                Velero velero = new Velero(capitan,anoFabricacion,eslora,precioBase,cantidadMastiles);
                agregarVelero(velero);
            }
            // Yates
            for(int j = 1; j <= cantidadYates; j = j + 1){
                System.out.println("Datos del yate "+j+" a cargo del capitan "+nombre);
                System.out.println("Ingrese el año de fabricación");
                int anoFabricacion = scanner.nextInt();
                System.out.println("Ingrese la eslora (longitd) en metros");
                int eslora = scanner.nextInt();
                System.out.println("ingrese el precio base");
                double precioBase = scanner.nextDouble();
                System.out.println("Ingrese la cantidad de camarotes");
                int cantidadCamarotes = scanner.nextInt();
                Yate yate = new Yate(capitan,anoFabricacion,eslora,precioBase,cantidadCamarotes);
                agregarYate(yate);
            }
            scanner.nextLine();
        }
        System.out.println();
        System.out.println();
        System.out.println("La flota está compuesta de "+totalVeleros+" veleros y "+totalYates+" yates," +
                " para un total de "+(totalVeleros+totalYates)+" embarcaciones." );
        System.out.println();
        System.out.println();
        informe();
    }
}

