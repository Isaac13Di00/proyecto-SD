import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ProductTableModel extends AbstractTableModel {
    private List<Producto> productos;
    private String[] columnNames = {"Nombre", "Precio"};

    public ProductTableModel() {
        productos = new ArrayList<>();
        // Aquí puedes agregar algunos productos de ejemplo
        productos.add(new Producto("Producto 1", 10.0));
        productos.add(new Producto("Producto 2", 15.0));
        productos.add(new Producto("Producto 3", 50.0));
        productos.add(new Producto("Producto 4", 200.0));
    }

    @Override
    public int getRowCount() {
        return productos.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Producto producto = productos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return producto.getNombre();
            case 1:
                return producto.getPrecio();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void editarProducto(int fila, String nombre, double precio) {
        Producto producto = productos.get(fila);
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        fireTableDataChanged();
    }

    public Producto getProducto(int fila) {
        return productos.get(fila);
    }
}
