import java.util.List;
import java.util.Scanner;


class Menu {

    private final Scanner sc = new Scanner(System.in);
    private final Cocina cocina = new Cocina();
    private final Imprimir impresora = new Imprimir();
    private List<ProductoSimple> catalogoProductos;
    private List<Combo> catalogoCombos;

    public void iniciarCatalogo() {
        ProductoSimple hamburguesa = new ProductoSimple("Hamburguesa Clasica", 28.0, 550);
        ProductoSimple papas = new ProductoSimple("Papas Fritas", 15.0, 320);
        ProductoSimple bebida = new ProductoSimple("Gaseosa 16oz", 10.0, 180);
        ProductoSimple pollo = new ProductoSimple("Pollo Frito 2pz", 32.0, 610);

        catalogoProductos = List.of(hamburguesa, papas, bebida, pollo);

        Combo comboFamiliar = new Combo("Combo Familiar", 0.10);
        comboFamiliar.agregarProducto(hamburguesa);
        comboFamiliar.agregarProducto(papas);
        comboFamiliar.agregarProducto(bebida);

        Combo comboPollo = new Combo("Combo Pollo", 0.08);
        comboPollo.agregarProducto(pollo);
        comboPollo.agregarProducto(papas);
        comboPollo.agregarProducto(bebida);

        catalogoCombos = List.of(comboFamiliar, comboPollo);
    }


    public void mostrarMenu() {
        System.out.println("1. Seleccionar tipo de orden");
        System.out.println("2. Salir");
    }

    public String seleccionarTipoOrden() {
        System.out.println("\n¿Tipo de orden?");
        System.out.println("1. Para comer aqui");
        System.out.println("2. Para llevar");
        int op = leerEntero();
        return (op == 1) ? "Para comer aqui" : "Para llevar";
    }

    public Cliente capturarCliente() {
        System.out.print("\nNombre del cliente: ");
        String nombre = sc.nextLine();
        System.out.print("NIT (o C/F): ");
        String nit = sc.nextLine();
        Cliente cliente = new Cliente(nombre, nit);
        cliente.registrarVisita();
        return cliente;
    }

    public void mostrarCombos() {
        System.out.println("\n-- COMBOS --");
        for (int i = 0; i < catalogoCombos.size(); i++) {
            Combo c = catalogoCombos.get(i);
            System.out.printf("%d. %s -> Q%.2f (ahorras Q%.2f)%n",
                    i + 1, c.getNombre(), c.calcularPrecioFinal(), c.calcularAhorro());
        }
    }

    public void mostrarProductos() {
        System.out.println("\n-- PRODUCTOS --");
        for (int i = 0; i < catalogoProductos.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, catalogoProductos.get(i));
        }
    }

    public Combo seleccionarCombo() {
        mostrarCombos();
        System.out.print("Seleccione un combo (numero): ");
        int op = leerEntero();
        return catalogoCombos.get(op - 1);
    }

    public ProductoSimple seleccionarProducto() {
        mostrarProductos();
        System.out.print("Seleccione un producto (numero): ");
        int op = leerEntero();
        return catalogoProductos.get(op - 1);
    }

    public MetodoPago seleccionarMetodoPago(double totalAPagar) {
        System.out.println("\n-- METODO DE PAGO --");
        System.out.println("1. Efectivo");
        System.out.println("2. Tarjeta");
        int op = leerEntero();
        if (op == 1) {
            System.out.print("Monto recibido en efectivo: Q");
            double monto = leerDouble();
            return new PagoEfectivo(monto);
        } else {
            System.out.print("Numero de tarjeta: ");
            String num = sc.nextLine();
            return new PagoTarjeta(num);
        }
    }

    public void confirmarPedido(Orden orden) {
        System.out.println("\nPedido confirmado. Total a pagar: Q"
                + String.format("%.2f", orden.calcularTotal()));
    }

    public void enviarACocina(Orden orden) {
        cocina.recibirPedido(orden);
        int minutos = cocina.calcularTiempoPreparacion(orden);
        System.out.println("Pedido enviado a cocina. Tiempo estimado: " + minutos + " min.");
    }

    public void imprimir(Factura factura) {
        System.out.println(impresora.imprimirFactura(factura));
    }

    private int leerEntero() {
        while (!sc.hasNextInt()) {
            System.out.print("Ingrese un numero valido: ");
            sc.next();
        }
        int val = sc.nextInt();
        sc.nextLine();
        return val;
    }

    private double leerDouble() {
        while (!sc.hasNextDouble()) {
            System.out.print("Ingrese un monto valido: ");
            sc.next();
        }
        double val = sc.nextDouble();
        sc.nextLine();
        return val;
    }


    public void ejecutar() {
        iniciarCatalogo();
        boolean continuar = true;
        while (continuar) {
            mostrarMenu();
            System.out.print("Opcion: ");
            int op = leerEntero();
            if (op == 2) {
                continuar = false;
                continue;
            }

            String tipoOrden = seleccionarTipoOrden();
            Cliente cliente = capturarCliente();
            Orden orden = new Orden(cliente, tipoOrden);

            boolean agregarMas = true;
            while (agregarMas) {
                System.out.println("\n1. Agregar producto  2. Agregar combo  3. Terminar seleccion");
                int accion = leerEntero();
                if (accion == 1) {
                    orden.agregarProducto(seleccionarProducto());
                } else if (accion == 2) {
                    orden.agregarProducto(seleccionarCombo());
                } else {
                    agregarMas = false;
                }
            }

            confirmarPedido(orden);
            MetodoPago metodoPago = seleccionarMetodoPago(orden.calcularTotal());
            double resultadoPago = metodoPago.procesarPago(orden.calcularTotal());

            if (metodoPago instanceof PagoEfectivo && resultadoPago < 0) {
                System.out.println("Monto insuficiente. Se cancela el pago.");
                continue;
            } else if (metodoPago instanceof PagoEfectivo) {
                System.out.printf("Cambio a devolver: Q%.2f%n", resultadoPago);
            } else {
                System.out.printf("Comision de tarjeta aplicada: Q%.2f%n",
                        ((PagoTarjeta) metodoPago).calcularComision(orden.calcularTotal()));
            }

            enviarACocina(orden);
            Factura factura = new Factura(orden, metodoPago);
            imprimir(factura);
        }
        System.out.println("bye.");
    }

    public static void main(String[] args) {
        new Menu().ejecutar();
    }
}
