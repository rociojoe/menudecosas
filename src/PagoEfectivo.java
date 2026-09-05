public class PagoEfectivo extends MetodoPago {

    private double montoRecibido;

    public PagoEfectivo(double montoRecibido) {
        super("Efectivo");
        this.montoRecibido = montoRecibido;
    }

    public boolean validarMontoSuficiente(double monto) {
        return montoRecibido >= monto;
    }


    @Override
    public double procesarPago(double monto) {
        if (!validarMontoSuficiente(monto)) {
            return -1; // monto insuficiente
        }
        return montoRecibido - monto;
    }
}
