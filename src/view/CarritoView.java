package view;

import model.Carrito;
import java.util.List;

public class CarritoView {
    public void printCarrito(List<Carrito> carrito) {
        for (Carrito item : carrito) {
            System.out.println("Producto ID: " + item.getProductoId() + ", Cantidad: " + item.getCantidad());
        }
    }
    public void printMessage(String message) {
        System.out.println(message);
    }
}
