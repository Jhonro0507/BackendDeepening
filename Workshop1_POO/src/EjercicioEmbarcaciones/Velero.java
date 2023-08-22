package EjercicioEmbarcaciones;

public class Velero extends Embarcacion {
    private int cantidadMastiles;

    public Velero(Capitan capitan, int anoFabricacion, int eslora, double precioBase, int cantidadMastiles) {
        super(capitan, anoFabricacion, eslora, precioBase);
        this.cantidadMastiles = cantidadMastiles;
    }
    @Override
    public String tamano() {
        if (cantidadMastiles > 4) {
            return "Grande";
        } else {
            return "";
        }
    }
    public int getCantidadMastiles() {
        return cantidadMastiles;
    }

}

