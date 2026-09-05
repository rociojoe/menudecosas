public abstract class Producto {

    private static int contadorCodigos = 100; // control interno de códigos

    private final String codigo;
    private String nombre;
    private double precioBase;

    public static final double PORCENTAJE_IVA = 0.12; // IVA Guatemala 12%

    public Producto(String nombre, double precioBase) {
        this.codigo = "P-" + (++contadorCodigos);
        this.nombre = nombre;
        this.precioBase = precioBase;
    }


    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        if (precioBase >= 0) {
            this.precioBase = precioBase;
        }
    }


    public abstract double calcularPrecioFinal();


    public double calcularImpuesto() {
        return calcularPrecioFinal() * PORCENTAJE_IVA;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - Q%.2f", nombre, codigo, calcularPrecioFinal());
    }
}
