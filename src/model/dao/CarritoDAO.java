package model.dao;

import model.Carrito;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Database.DatabaseConnection;

public class CarritoDAO {
    public void addProducto(Carrito carrito) {
        String sql = "INSERT INTO Carrito (usuario_id, producto_id, cantidad) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, carrito.getUsuarioId());
            pstmt.setInt(2, carrito.getProductoId());
            pstmt.setInt(3, carrito.getCantidad());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeProducto(int usuarioId, int productoId) {
        String sql = "DELETE FROM Carrito WHERE usuario_id = ? AND producto_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, usuarioId);
            pstmt.setInt(2, productoId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Carrito> getCarritoByUsuario(int usuarioId) {
        List<Carrito> carrito = new ArrayList<>();
        String sql = "SELECT * FROM Carrito WHERE usuario_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, usuarioId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Carrito item = new Carrito(rs.getInt("usuario_id"), rs.getInt("producto_id"), rs.getInt("cantidad"));
                carrito.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carrito;
    }
}