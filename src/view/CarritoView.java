package view;

import model.Carrito;
import model.Producto;

public class CarritoView {
    public void printMessage(String message) {
        System.out.println(message);
    }
    public void printProducto(Producto producto) {
        System.out.println("Producto: " + producto.getNombre() + ", Precio: " + producto.getPrecio());
    }
    public void printCarrito(Carrito carrito) {
        System.out.println("Productos en el carrito:");
        for (Producto producto : carrito.getProductos()) {
            System.out.println("Producto: " + producto.getNombre() + ", Precio: " + producto.getPrecio());
        }
        System.out.println("Total: " + carrito.getTotal());
    }
}
