package controllers;

import model.dao.CarritoDAO;
import model.Carrito;
import view.CarritoView;
import java.util.List;

public class CarritoController {
    private CarritoView carritoView;
    private CarritoDAO carritoDAO;

    public CarritoController() {
        this.carritoView = new CarritoView();
        this.carritoDAO = new CarritoDAO();
    }

    public void agregarProductoAlCarrito(int usuarioId, int productoId, int cantidad) {
        Carrito carrito = new Carrito(usuarioId, productoId, cantidad);
        carritoDAO.addProducto(carrito);
        carritoView.printMessage("Producto agregado al carrito");
    }

    public void removeProductoFromCarrito(int usuarioId, int productoId) {
        carritoDAO.removeProducto(usuarioId, productoId);
        carritoView.printMessage("Producto eliminado del carrito");
    }

    public void verCarrito(int usuarioId) {
        List<Carrito> carrito = carritoDAO.getCarritoByUsuario(usuarioId);
        carritoView.printCarrito(carrito);
    }
}
