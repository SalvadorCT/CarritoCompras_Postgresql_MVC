package controllers;

import model.dao.CompraDAO;
import model.Compra;
import view.CompraView;
import java.util.List;

public class CompraController {
    private CompraView compraView;
    private CompraDAO compraDAO;

    public CompraController() {
        this.compraView = new CompraView();
        this.compraDAO = new CompraDAO();
    }

    public void addCompra(Compra compra, int usuarioId) {
        compraDAO.addCompra(compra, usuarioId);
        compraView.printMessage("Compra realizada con éxito");
    }

    public void displayHistorialCompras(int usuarioId) {
        List<Compra> compras = compraDAO.getHistorialCompras(usuarioId);
        compraView.printCompras(compras);
    }
}