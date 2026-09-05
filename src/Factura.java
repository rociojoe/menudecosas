public class Factura {

    private static int contadorFacturas = 1000;

    private final int numeroFactura;
    private final Orden orden;
    private final MetodoPago metodoPago;

    public Factura(Orden orden, MetodoPago metodoPago) {
        this.numeroFactura = ++contadorFacturas;
        this.orden = orden;
        this.metodoPago = metodoPago;
    }

    public int getNumeroFactura() {
        return numeroFactura;
    }


    public double calcularTotalFinal() {
        double total = orden.calcularTotal();
        if (metodoPago instanceof PagoTarjeta tarjeta) {
            return total + tarjeta.calcularComision(total);
        }
        return total;
    }

    public double calcularTotalConPropina(double porcentajePropina) {
        double propina = orden.calcularSubtotal() * porcentajePropina;
        return calcularTotalFinal() + propina;
    }

    public Orden getOrden() {
        return orden;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }
}
