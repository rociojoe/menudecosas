public abstract class MetodoPago {

    protected String nombreMetodo;

    public MetodoPago(String nombreMetodo) {
        this.nombreMetodo = nombreMetodo;
    }

    public String getNombreMetodo() {
        return nombreMetodo;
    }


    public abstract double procesarPago(double monto);
}
