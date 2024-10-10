package model.dao;

import model.Resena;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Database.DatabaseConnection;

public class ResenaDAO {
    public void addResena(Resena resena) {
        String sql = "INSERT INTO Resena (usuario_id, producto_id, comentario, calificacion) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, resena.getUsuarioId());
            pstmt.setInt(2, resena.getProductoId());
            pstmt.setString(3, resena.getComentario());
            pstmt.setInt(4, resena.getCalificacion());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeResena(int usuarioId, int productoId) {
        String sql = "DELETE FROM Resena WHERE usuario_id = ? AND producto_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, usuarioId);
            pstmt.setInt(2, productoId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Resena> getResenasByProducto(int productoId) {
        List<Resena> resenas = new ArrayList<>();
        String sql = "SELECT * FROM Resena WHERE producto_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, productoId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Resena resena = new Resena(rs.getInt("usuario_id"), rs.getInt("producto_id"), rs.getString("comentario"), rs.getInt("calificacion"));
                resenas.add(resena);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resenas;
    }
}