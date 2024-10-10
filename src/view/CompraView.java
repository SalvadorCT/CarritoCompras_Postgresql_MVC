package view;

import model.Compra;
import java.util.List;

public class CompraView {
    public void printCompras(List<Compra> compras) {
        for (Compra compra : compras) {
            System.out.println(compra);
        }
    }
    public void printMessage(String message) {
        System.out.println(message);
    }
}