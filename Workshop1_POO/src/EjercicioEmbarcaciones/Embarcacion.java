package EjercicioEmbarcaciones;

public class Embarcacion {
    private Capitan capitan;
    private double precioBase;
    private int anoFabricacion;
    private int eslora;
    public Embarcacion(Capitan capitan, int anoFabricacion, int eslora, double precioBase) {
        this.capitan = capitan;
        this.anoFabricacion = anoFabricacion;
        this.eslora = eslora;
        this.precioBase = precioBase;
    }
    public double calcularMonto() {
        if (anoFabricacion > 2020) {
            return precioBase = precioBase + 20000;
        } else {
            return precioBase;
        }
    }
    public String comprar(){
        return null;
    }
    public String tamano(){
        return "";
    }
    public int getEslora() {
        return eslora;
    }
    public int getAnoFabricacion() {
        return anoFabricacion;
    }

    public Capitan getCapitan() {
        return capitan;
    }

    public double getPrecioBase() {
        return precioBase;
    }
}
