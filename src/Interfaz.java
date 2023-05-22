import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interfaz extends JFrame {
    private Biblioteca biblioteca;
    private JTextField nombreField;
    private JTextField idField;
    private JTextField paginasField;
    private JTextArea resultadoArea;
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton agregarLibroButton;
    private JButton eliminarLibroButton;
    private JButton buscarLibroButton;
    private JButton totalDePaginasButton;
    private JButton agregar4LibrosButton;
    private JTextArea textArea1;

    public Interfaz() {
        biblioteca = new Biblioteca();

        setTitle("Biblioteca");
        setLayout(new BorderLayout());

        // Crear campos de texto y etiquetas
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new GridLayout(3, 2));

        panelSuperior.add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        panelSuperior.add(nombreField);

        panelSuperior.add(new JLabel("ID:"));
        idField = new JTextField();
        panelSuperior.add(idField);

        panelSuperior.add(new JLabel("Páginas:"));
        paginasField = new JTextField();
        panelSuperior.add(paginasField);

        add(panelSuperior, BorderLayout.NORTH);

        // Crear botones y asignar acciones
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new GridLayout(1, 5));

        JButton agregarBtn = new JButton("Agregar");
        agregarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                int numPaginas = Integer.parseInt(paginasField.getText());
                if (biblioteca.agregarLibro(nombre, numPaginas)) {
                    resultadoArea.setText("Libro agregado.");
                } else {
                    resultadoArea.setText("El libro ya existe.");
                }
                limpiarCampos();
            }
        });
        panelCentral.add(agregarBtn);

        JButton eliminarBtn = new JButton("Eliminar");
        eliminarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                int id = Integer.parseInt(idField.getText());
                if (biblioteca.eliminarLibro(nombre, id)) {
                    resultadoArea.setText("Libro eliminado.");
                } else {
                    resultadoArea.setText("Libro no encontrado.");
                }
                limpiarCampos();
            }
        });
        panelCentral.add(eliminarBtn);

        JButton buscarBtn = new JButton("Buscar");
        buscarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                int id = Integer.parseInt(idField.getText());
                Libro libro;
                if (!nombre.isEmpty()) {
                    libro = biblioteca.buscarLibroPorNombre(nombre);
                } else {
                    libro = biblioteca.buscarLibroPorId(id);
                }
                if (libro != null) {
                    resultadoArea.setText("Libro encontrado: ID " + libro.getId() + ", Nombre " + libro.getNombre() + ", Páginas " + libro.getNumPaginas());
                } else {
                    resultadoArea.setText("Libro no encontrado.");
                }
                limpiarCampos();
            }
        });
        panelCentral.add(buscarBtn);

        JButton totalPaginasBtn = new JButton("Total Páginas");
        totalPaginasBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int totalPaginas = biblioteca.calcularTotalPaginas();
                resultadoArea.setText("Total de páginas en la biblioteca: " + totalPaginas);
                limpiarCampos();
            }
        });
        panelCentral.add(totalPaginasBtn);

        JButton agregarCuatroBtn = new JButton("Agregar 4 libros");
        agregarCuatroBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                biblioteca.agregarLibro("Maze Runner", 450);
                biblioteca.agregarLibro("Cien Años de Soledad", 380);
                biblioteca.agregarLibro("La Iliada", 250);
                biblioteca.agregarLibro("La Odisea", 380);
                resultadoArea.setText("Se han añadido los 4 libros:\n\n");
                for (Libro libro : biblioteca.getLibros()) {
                    resultadoArea.append("ID: " + libro.getId() + ", Nombre: " + libro.getNombre() + ", Páginas: " + libro.getNumPaginas() + "\n");
                }
                limpiarCampos();
            }
        });
        panelCentral.add(agregarCuatroBtn);

        add(panelCentral, BorderLayout.CENTER);

        resultadoArea = new JTextArea();
        resultadoArea.setRows(10);
        add(resultadoArea, BorderLayout.SOUTH);
    }

    private void limpiarCampos() {
        nombreField.setText("");
        idField.setText("");
        paginasField.setText("");
    }
}



