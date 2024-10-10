package model;

import java.util.ArrayList;

public class Carrito {
    private int usuarioId;
    private int productoId;
    private int cantidad;
    private ArrayList<Producto> productos;
    private double total;

    public Carrito(int usuarioId, int productoId, int cantidad) {
        this.usuarioId = usuarioId;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.productos = new ArrayList<>();
        this.total = 0;
    }
    public int getUsuarioId() {return usuarioId;}
    public void setUsuarioId(int usuarioId) {this.usuarioId = usuarioId;}
    public int getProductoId() {return productoId;}
    public void setProductoId(int productoId) {this.productoId = productoId;}
    public int getCantidad() {return cantidad;}
    public void setCantidad(int cantidad) {this.cantidad = cantidad;}

    public ArrayList<Producto> getProductos() {
        return productos;
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
        productos.clear();
        total = 0;
    }

    public Producto obtenerProducto(int index) {
        return productos.get(index);
    }
}