package view;

import model.Compra;
import java.util.ArrayList;

public class CompraView {
    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printCompras(ArrayList<Compra> compras) {
        for (Compra compra : compras) {
            System.out.println("Compra ID: " + compra.getId() + ", Total: " + compra.getTotal());
        }
    }
}
