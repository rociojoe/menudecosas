import java.util.ArrayList;
import java.util.List;

public class Orden {

    private static int contadorOrdenes = 0;

    private final int numeroOrden;
    private final Cliente cliente;
    private final String tipoOrden; // "Para llevar" o "Para comer aquí"
    private final List<Producto> productos = new ArrayList<>();

    public Orden(Cliente cliente, String tipoOrden) {
        this.numeroOrden = ++contadorOrdenes;
        this.cliente = cliente;
        this.tipoOrden = tipoOrden;
    }

    public int getNumeroOrden() {
        return numeroOrden;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getTipoOrden() {
        return tipoOrden;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }


    public double calcularSubtotal() {
        double subtotal = 0;
        for (Producto p : productos) {
            subtotal += p.calcularPrecioFinal(); // POLIMORFISMO
        }
        return subtotal;
    }


    public double calcularImpuestoTotal() {
        return calcularSubtotal() * Producto.PORCENTAJE_IVA;
    }


    public double calcularTotal() {
        double subtotal = calcularSubtotal();
        double impuesto = calcularImpuestoTotal();
        double descuento = subtotal * cliente.calcularDescuentoFidelidad();
        return subtotal + impuesto - descuento;
    }

    public int calcularCantidadProductos() {
        return productos.size();
    }
}
