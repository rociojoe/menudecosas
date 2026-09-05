public class Imprimir {

    private int contadorPapelUtilizadoCm;

    public Imprimir() {
        this.contadorPapelUtilizadoCm = 0;
    }


    public double calcularPapelUtilizado(int cantidadLineas) {
        double cm = cantidadLineas * 0.6;
        contadorPapelUtilizadoCm += cm;
        return cm;
    }


    public String imprimirFactura(Factura factura) {
        Orden orden = factura.getOrden();
        int lineas = 6 + orden.calcularCantidadProductos();
        double papelUsado = calcularPapelUtilizado(lineas);

        StringBuilder sb = new StringBuilder();
        sb.append("Menu\n");
        sb.append("Factura No: ").append(factura.getNumeroFactura()).append("\n");
        sb.append("Orden No: ").append(orden.getNumeroOrden())
                .append("Tipo: ").append(orden.getTipoOrden()).append("\n");
        sb.append("Cliente: ").append(orden.getCliente().getNombre()).append("\n");
        sb.append("----------------------------------\n");
        for (Producto p : orden.getProductos()) {
            sb.append(" - ").append(p).append("\n");
        }
        sb.append("----------------------------------\n");
        sb.append(String.format("Subtotal:  Q%.2f%n", orden.calcularSubtotal()));
        sb.append(String.format("IVA:       Q%.2f%n", orden.calcularImpuestoTotal()));
        sb.append(String.format("TOTAL:     Q%.2f%n", factura.calcularTotalFinal()));
        sb.append("Metodo de pago: ").append(factura.getMetodoPago().getNombreMetodo()).append("\n");
        sb.append(String.format("(papel utilizado: %.1f cm)%n", papelUsado));
        sb.append("==================================\n");
        return sb.toString();
    }

    public int getContadorPapelUtilizadoCm() {
        return contadorPapelUtilizadoCm;
    }
}
