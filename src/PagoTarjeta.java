
public class PagoTarjeta extends MetodoPago {

    private static final double COMISION = 0.03;
    private String numeroTarjetaEnmascarado;

    public PagoTarjeta(String numeroTarjeta) {
        super("Tarjeta");
        this.numeroTarjetaEnmascarado = "**** **** **** " +
                numeroTarjeta.substring(Math.max(0, numeroTarjeta.length() - 4));
    }

    public double calcularComision(double monto) {
        return monto * COMISION;
    }


    @Override
    public double procesarPago(double monto) {
        return monto + calcularComision(monto);
    }

    public String getNumeroTarjetaEnmascarado() {
        return numeroTarjetaEnmascarado;
    }
}
