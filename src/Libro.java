public class Libro {
    private static int nextId = 1;
    private int id;
    private String nombre;
    private int numPaginas;

    public Libro(String nombre, int numPaginas) {
        this.id = nextId++;
        this.nombre = nombre;
        this.numPaginas = numPaginas;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNumPaginas() {
        return numPaginas;
    }
}





