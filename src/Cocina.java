public class Cocina {

    private int pedidosEnCola;

    public Cocina() {
        this.pedidosEnCola = 0;
    }


    public void recibirPedido(Orden orden) {
        pedidosEnCola++;
    }


    public int calcularTiempoPreparacion(Orden orden) {
        int base = 5;
        int minutosPorProducto = 3;
        int minutosPorColaExistente = 2;
        return base + (orden.calcularCantidadProductos() * minutosPorProducto)
                + (pedidosEnCola * minutosPorColaExistente);
    }

    public int getPedidosEnCola() {
        return pedidosEnCola;
    }
}
