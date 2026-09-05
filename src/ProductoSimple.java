public class ProductoSimple extends Producto {

    private int calorias;

    public ProductoSimple(String nombre, double precioBase, int calorias) {
        super(nombre, precioBase);
        this.calorias = calorias;
    }

    public int getCalorias() {
        return calorias;
    }

    @Override
    public double calcularPrecioFinal() {

        return getPrecioBase();
    }


    public double calcularValorNutricionalPorQuetzal() {
        double precio = calcularPrecioFinal();
        if (precio == 0) return 0;
        return calorias / precio;
    }
}
