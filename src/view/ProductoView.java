package view;

import model.Producto;
import java.util.ArrayList;

public class ProductoView {
    public void printMessage(String message) {
        System.out.println(message);
    }
    public void printProductos(ArrayList<Producto> productos) {
        for (Producto producto : productos) {
            System.out.println("Producto: " + producto.getNombre() + ", Precio: " + producto.getPrecio() + ", Cantidad: " + producto.getCantidad());
        }
    }
}
