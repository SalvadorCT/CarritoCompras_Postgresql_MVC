package view;

import model.Resena;
import java.util.List;

public class ResenaView {
    public void printResenas(List<Resena> resenas) {
        for (Resena resena : resenas) {
            System.out.println("Usuario ID: " + resena.getUsuarioId() + ", Producto ID: " + resena.getProductoId() + ", Comentario: " + resena.getComentario() + ", Calificación: " + resena.getCalificacion());
        }
    }
    public void printMessage(String message) {
        System.out.println(message);
    }
}