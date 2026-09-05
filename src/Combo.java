import java.util.ArrayList;
import java.util.List;

public class Combo extends Producto {

    private final List<ProductoSimple> productos = new ArrayList<>();
    private double porcentajeDescuento;

    public Combo(String nombre, double porcentajeDescuento) {

        super(nombre, 0);
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public void agregarProducto(ProductoSimple producto) {
        productos.add(producto);
    }

    public List<ProductoSimple> getProductos() {
        return productos;
    }

    @Override
    public double calcularPrecioFinal() {
        double subtotal = 0;
        for (ProductoSimple p : productos) {
            subtotal += p.calcularPrecioFinal();
        }
        return subtotal * (1 - porcentajeDescuento);
    }


    public double calcularAhorro() {
        double sinDescuento = 0;
        for (ProductoSimple p : productos) {
            sinDescuento += p.calcularPrecioFinal();
        }
        return sinDescuento - calcularPrecioFinal();
    }
}
