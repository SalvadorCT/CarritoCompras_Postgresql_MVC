package controllers;

import model.dao.ResenaDAO;
import model.Resena;
import view.ResenaView;
import java.util.List;

public class ResenaController {
    private ResenaView resenaView;
    private ResenaDAO resenaDAO;

    public ResenaController() {
        this.resenaView = new ResenaView();
        this.resenaDAO = new ResenaDAO();
    }

    public void addResena(int usuarioId, int productoId, String comentario, int calificacion) {
        Resena resena = new Resena(usuarioId, productoId, comentario, calificacion);
        resenaDAO.addResena(resena);
        resenaView.printMessage("Reseña agregada");
    }

    public void removeResena(int usuarioId, int productoId) {
        resenaDAO.removeResena(usuarioId, productoId);
        resenaView.printMessage("Reseña eliminada");
    }

    public void displayResenasByProducto(int productoId) {
        List<Resena> resenas = resenaDAO.getResenasByProducto(productoId);
        resenaView.printResenas(resenas);
    }
}
