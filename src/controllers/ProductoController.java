package controllers;

import Database.DatabaseConnection;
import model.Producto;
import view.ProductoView;
import java.sql.*;
import java.util.ArrayList;

public class ProductoController {
    private ProductoView productoView;

    public ProductoController() {
        this.productoView = new ProductoView();
    }

    public void addProducto(String nombre, double precio, String descripcion, int stock) {
        String sql = "INSERT INTO Producto (nombre, precio, descripcion, stock) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setDouble(2, precio);
            pstmt.setString(3, descripcion);
            pstmt.setInt(4, stock);
            pstmt.executeUpdate();
            productoView.printMessage("Producto agregado");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayProductos() {
        String sql = "SELECT * FROM Producto";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            ArrayList<Producto> productos = new ArrayList<>();
            while (rs.next()) {
                Producto producto = new Producto(rs.getInt("id"), rs.getString("nombre"), rs.getDouble("precio"), rs.getInt("stock"));
                productos.add(producto);
            }
            productoView.printProductos(productos);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}