public class Cliente {

    private String nombre;
    private String nit;
    private int visitasRegistradas;

    public Cliente(String nombre, String nit) {
        this.nombre = nombre;
        this.nit = nit;
        this.visitasRegistradas = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNit() {
        return nit;
    }

    public int getVisitasRegistradas() {
        return visitasRegistradas;
    }


    public void registrarVisita() {
        this.visitasRegistradas++;
    }


    public double calcularDescuentoFidelidad() {
        if (visitasRegistradas >= 10) {
            return 0.10;
        } else if (visitasRegistradas >= 5) {
            return 0.05;
        }
        return 0.0;
    }
}
