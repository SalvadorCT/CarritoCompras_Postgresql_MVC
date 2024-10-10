package view;

import model.Resena;
import java.util.ArrayList;

public class ResenaView {
    public void printMessage(String message) {
        System.out.println(message);
    }
    public void printResenas(ArrayList<Resena> resenas) {
        for (Resena resena : resenas) {
            System.out.println(resena.getComentario() + " - " + resena.getCalificacion());
        }
    }
}