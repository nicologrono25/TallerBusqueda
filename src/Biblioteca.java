import java.util.ArrayList;
import java.util.Comparator;

public class Biblioteca {
    private ArrayList<Libro> libros;

    public Biblioteca() {
        libros = new ArrayList<>();
    }

    public ArrayList<Libro> getLibros() {
        return libros;
    }

    public boolean agregarLibro(String nombre, int numPaginas) {
        for (Libro libro : libros) {
            if (libro.getNombre().equals(nombre)) {
                return false;
            }
        }
        libros.add(new Libro(nombre, numPaginas));
        return true;
    }

    public boolean eliminarLibro(String nombre, int id) {
        if (!nombre.isEmpty()) {
            return libros.removeIf(libro -> libro.getNombre().equals(nombre));
        } else {
            return libros.removeIf(libro -> libro.getId() == id);
        }
    }

    public Libro buscarLibroPorNombre(String nombre) {
        for (Libro libro : libros) {
            if (libro.getNombre().equals(nombre)) {
                return libro;
            }
        }
        return null;
    }

    public Libro buscarLibroPorId(int id) {
        libros.sort(Comparator.comparingInt(Libro::getId));
        int index = busquedaBinaria(id, 0, libros.size() - 1);
        if (index != -1) {
            return libros.get(index);
        }
        return null;
    }

    private int busquedaBinaria(int id, int inicio, int fin) {
        if (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            if (libros.get(medio).getId() == id) {
                return medio;
            } else if (libros.get(medio).getId() < id) {
                return busquedaBinaria(id, medio + 1, fin);
            } else {
                return busquedaBinaria(id, inicio, medio - 1);
            }
        }
        return -1;
    }

    public int calcularTotalPaginas() {
        return calcularTotalPaginasRecursivo(0, 0);
    }

    private int calcularTotalPaginasRecursivo(int index, int suma) {
        if (index < libros.size()) {
            return calcularTotalPaginasRecursivo(index + 1, suma + libros.get(index).getNumPaginas());
        } else {
            return suma;
        }
    }
}



