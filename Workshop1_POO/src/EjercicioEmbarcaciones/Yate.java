package EjercicioEmbarcaciones;

import EjercicioEmbarcaciones.Embarcacion;

public class Yate extends Embarcacion {
    private int cantidadCamarotes;

    public Yate(Capitan capitan, int anoFabricacion, int eslora, double precioBase, int cantidadCamarotes) {
        super(capitan, anoFabricacion, eslora, precioBase);
        this.cantidadCamarotes = cantidadCamarotes;
    }
    @Override
    public String comprar(){
        return "Sí";
    }
    @Override
    public String tamano(){
        if (cantidadCamarotes > 7) {
            return "Mayor lujo";
        } else {
            return "";
        }
    }
    public int getCantidadCamarotes() {
        return cantidadCamarotes;
    }

}
