package controllers;

import java.sql.*;
import Database.DatabaseConnection;
import model.Carrito;
import model.Producto;
import view.CarritoView;

public class CarritoController {
    private Carrito carrito;
    private CarritoView carritoView;

    public CarritoController() {
        this.carrito = new Carrito();
        this.carritoView = new CarritoView();
    }

    public void agregarProducto(Producto producto) {
        carrito.agregarProducto(producto);
        carritoView.printMessage("Producto agregado al carrito");
    }

    public void eliminarProducto(int index) {
        carrito.eliminarProducto(index);
        carritoView.printMessage("Producto eliminado del carrito");
    }

    public void vaciarCarrito() {
        carrito.eliminarProductos();
        carritoView.printMessage("Carrito vaciado");
    }

    public void mostrarCarrito() {
        carritoView.printCarrito(carrito);
    }
    public void verCarrito(int usuarioId) {
        String sql = "SELECT p.id, p.nombre, p.precio, c.cantidad FROM Producto p JOIN Carrito c ON p.id = c.producto_id WHERE c.usuario_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, usuarioId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                double precio = rs.getDouble("precio");
                int cantidad = rs.getInt("cantidad");
                carritoView.printProducto(new Producto(id, nombre, precio, cantidad));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void agregarProductoAlCarrito(int usuarioId, int productoId, int cantidad) {
    String checkStockSql = "SELECT stock FROM Producto WHERE id = ?";
    String checkCarritoSql = "SELECT cantidad FROM Carrito WHERE usuario_id = ? AND producto_id = ?";
    String updateCarritoSql = "UPDATE Carrito SET cantidad = cantidad + ? WHERE usuario_id = ? AND producto_id = ?";
    String insertCarritoSql = "INSERT INTO Carrito (usuario_id, producto_id, cantidad) VALUES (?, ?, ?)";

    try (Connection conn = DatabaseConnection.getConnection()) {
        try (PreparedStatement checkStockStmt = conn.prepareStatement(checkStockSql)) {
            checkStockStmt.setInt(1, productoId);
            try (ResultSet rs = checkStockStmt.executeQuery()) {
                if (rs.next()) {
                    int stockDisponible = rs.getInt("stock");
                    // Verificar si el stock es suficiente
                    if (stockDisponible < cantidad) {
                        carritoView.printMessage("Stock insuficiente para el producto");
                        return;
                    }
                } else {
                    carritoView.printMessage("Producto no encontrado");
                    return;
                }
            }
        }

        // Verificar si el producto ya está en el carrito
        try (PreparedStatement checkCarritoStmt = conn.prepareStatement(checkCarritoSql)) {
            checkCarritoStmt.setInt(1, usuarioId);
            checkCarritoStmt.setInt(2, productoId);
            try (ResultSet rs = checkCarritoStmt.executeQuery()) {
                if (rs.next()) {
                    // Si el producto ya está en el carrito, actualizar la cantidad
                    try (PreparedStatement updateCarritoStmt = conn.prepareStatement(updateCarritoSql)) {
                        updateCarritoStmt.setInt(1, cantidad);
                        updateCarritoStmt.setInt(2, usuarioId);
                        updateCarritoStmt.setInt(3, productoId);
                        updateCarritoStmt.executeUpdate();
                        carritoView.printMessage("Cantidad del producto actualizada en el carrito");
                    }
                } else {
                    // Si el producto no está en el carrito
                    try (PreparedStatement insertCarritoStmt = conn.prepareStatement(insertCarritoSql)) {
                        insertCarritoStmt.setInt(1, usuarioId);
                        insertCarritoStmt.setInt(2, productoId);
                        insertCarritoStmt.setInt(3, cantidad);
                        insertCarritoStmt.executeUpdate();
                        carritoView.printMessage("Producto agregado al carrito");
                    }
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        carritoView.printMessage("Error al agregar el producto al carrito");
    }
}

    public void removeProductoFromCarrito(int productoId) {
    String sql = "DELETE FROM Carrito WHERE producto_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, productoId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                carritoView.printMessage("Producto eliminado del carrito");
            } else {
                carritoView.printMessage("Producto no encontrado en el carrito");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Carrito getCarrito() {
        return carrito;
    }
}
