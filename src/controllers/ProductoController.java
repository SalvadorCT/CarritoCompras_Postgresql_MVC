package controllers;

import model.dao.ProductoDAO;
import model.Producto;
import view.ProductoView;
import java.util.List;

public class ProductoController {
    private ProductoView productoView;
    private ProductoDAO productoDAO;

    public ProductoController() {
        this.productoView = new ProductoView();
        this.productoDAO = new ProductoDAO();
    }

    public void displayProductos() {
        List<Producto> productos = productoDAO.getAllProductos();
        productoView.printProductos(productos);
    }
}