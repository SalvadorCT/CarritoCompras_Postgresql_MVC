package model;

import java.util.ArrayList;
import java.util.List;

public class Compra {
    private int id;
    private ArrayList<Producto> productos;
    private double total;

    public Compra(int id) {
        this.id = id;
        this.productos = new ArrayList<>();
        this.total = 0;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public ArrayList<Producto> getProductos() {
        return productos;
    }
    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
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
        productos.clear();total = 0;
    }
    public Producto obtenerProducto(int index) {return productos.get(index);}
}
