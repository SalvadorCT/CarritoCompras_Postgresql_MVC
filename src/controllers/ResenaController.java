package controllers;

import Database.DatabaseConnection;
import model.Resena;
import view.ResenaView;
import java.sql.*;
import java.util.ArrayList;

public class ResenaController {
    private ResenaView resenaView;

    public ResenaController() {
        this.resenaView = new ResenaView();
    }

    public void addResena(int usuarioId, int productoId, String comentario, int calificacion) {
        String sql = "INSERT INTO Resena (usuario_id, producto_id, comentario, calificacion) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, usuarioId);
            pstmt.setInt(2, productoId);
            pstmt.setString(3, comentario);
            pstmt.setInt(4, calificacion);
            pstmt.executeUpdate();
            resenaView.printMessage("Reseña agregada");
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
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                resenaView.printMessage("Reseña eliminada");
            } else {
                resenaView.printMessage("Reseña no encontrada");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateResena(int usuarioId, int productoId, String comentario, int calificacion) {
        String sql = "UPDATE Resena SET comentario = ?, calificacion = ? WHERE usuario_id = ? AND producto_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, comentario);
            pstmt.setInt(2, calificacion);
            pstmt.setInt(3, usuarioId);
            pstmt.setInt(4, productoId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                resenaView.printMessage("Reseña actualizada");
            } else {
                resenaView.printMessage("Reseña no encontrada");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayResenas() {
        String sql = "SELECT * FROM Resena";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            ArrayList<Resena> resenas = new ArrayList<>();
            while (rs.next()) {
                Resena resena = new Resena(rs.getString("comentario"), rs.getInt("calificacion"));
                resenas.add(resena);
            }
            resenaView.printResenas(resenas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
