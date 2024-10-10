package model;
import java.util.ArrayList;

public class Carrito {
    private ArrayList<Producto> productos;
    private double total;

    public Carrito() {
        productos = new ArrayList<>();
        total = 0;
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
        total += producto.getPrecio();
    }

    public void eliminarProducto(int index) {
        total -= productos.get(index).getPrecio();
        productos.remove(index);
    }

    public void eliminarProductos() {
        productos.clear();
        total = 0;
    }

    public Producto obtenerProducto(int index) {
        return productos.get(index);
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public double getTotal() {
        return total;
    }
    public void setTotal(double total){this.total = total;}
}
