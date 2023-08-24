package EjercicioParqueadero;

public class Parqueadero {
    private Carro[][] lugares;
    private double tarifaHora;

    public Parqueadero(double tarifaHora) {
        this.lugares = new Carro[5][10];
        this.tarifaHora = tarifaHora;
    }
    public boolean parquearCarro(Carro carro, int fila, int columna) {
        if (fila > 0 && fila < 5 && columna > 0 && columna < 10) {
            if (lugares[fila - 1][columna - 1] == null) {
                lugares[fila - 1][columna - 1] = carro;
                System.out.println("El carro con placa " + carro.getPlaca() + " ha sido estacionado en la fila " + fila + " y columna " + columna);
                return true;
            } else {
                System.out.println("El lugar en la fila " + (fila) + ", y columna " + (columna) + ", está ocupado.");
                return false;
            }
        } else {
            System.out.println("Se debe parquear en una fila entre la 1 y 5, y en una columna entre la 1 y 10");
            return false;
        }
    }

    public double cobrarPorTiempo(Carro carro, int horas){
        System.out.println("El carro con placa " + carro.getPlaca() + " debe pagar " + (tarifaHora * horas));
        return tarifaHora * horas;
    }
    public void mostrarParqueadero() {
        System.out.print(" ");
        for (int i = 1; i <= 10; i++) {
            System.out.print((i));
        }
        System.out.println();
        for (int i = 0; i < 5; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < 10; j++) {
                if (lugares[i][j] != null) {
                    System.out.print("x");
                } else {
                    System.out.print(" ");
                }
            }
            for (int j = 0; j < 10; j++) {
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
