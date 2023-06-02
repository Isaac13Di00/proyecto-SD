package SocketsProyect;
import java.util.*;

import javax.swing.table.AbstractTableModel;

public class ProductTableModel extends AbstractTableModel {
	private List<Producto> productos;
	private String[] columnNames = { "Id", "Nombre", "Precio", "Descripcion", "Ultima actualización" };

	public ProductTableModel() {
		ConexionMySQL conexion = new ConexionMySQL();
		List<String> ids = conexion.consultarPersonal("SELECT id FROM product;","id");
		List<String> names = conexion.consultarPersonal("SELECT name FROM product;","name");
		List<String> prices = conexion.consultarPersonal("SELECT price FROM product;","price");
		List<String> descriptions = conexion.consultarPersonal("SELECT description FROM product;","description");
		List<String> updated_ats = conexion.consultarPersonal("SELECT updated_at FROM product;","updated_at");
//		Por eso me la paso jugando albion xd
		productos = new ArrayList<>();
		// Aqu� puedes agregar algunos productos de ejemplo
		for (int i = 0; i < ids.size(); i++) {
			productos.add(new Producto(Integer.parseInt(ids.get(i)), names.get(i), Float.parseFloat(prices.get(i)), descriptions.get(i),updated_ats.get(i)));
			System.out.println(productos.get(i).toString());
		}
//		pero para que lo quieres local??
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
		case 2:
			return producto.get
		default:
			return null;
		}
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	public void editarProducto(int fila, String nombre, float precio) {
		Producto producto = productos.get(fila);
		producto.setNombre(nombre);
		producto.setPrecio(precio);
		fireTableDataChanged();
	}

	public Producto getProducto(int fila) {
		return productos.get(fila);
	}
}
