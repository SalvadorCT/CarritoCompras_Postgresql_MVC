package view;

import model.Producto;
import java.util.List;

public class ProductoView {
    public void printMessage(String message) {
        System.out.println(message);
    }
    public void printProductos(List<Producto> productos) {
        for (Producto producto : productos) {
            System.out.println("ID: " + producto.getId() + ", Nombre: " + producto.getNombre() + ", Precio: " + producto.getPrecio());
        }
    }
}
