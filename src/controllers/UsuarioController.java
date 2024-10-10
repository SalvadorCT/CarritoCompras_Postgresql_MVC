package controllers;

import Database.DatabaseConnection;
import model.Usuario;
import view.UsuarioView;

import java.sql.*;
import java.util.ArrayList;

public class UsuarioController{
    private static UsuarioView usuarioView;

    public UsuarioController() {
        this.usuarioView = new UsuarioView();
    }

    public static void addUsuario(String nombre, String correo, String contrasena) {
        String sql = "INSERT INTO Usuario (nombre, email, password) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, correo);
            pstmt.setString(3, contrasena);
            pstmt.executeUpdate();
            usuarioView.printMessage("Usuario agregado");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void displayUsuarios() {
        String sql = "SELECT * FROM Usuario";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            ArrayList<Usuario> usuarios = new ArrayList<>();
            while (rs.next()) {
                Usuario usuario = new Usuario(rs.getString("nombre"), rs.getString("email"), rs.getString("password"));
                usuarios.add(usuario);
            }
            usuarioView.printUsuarios(usuarios);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int login(String correo, String contrasena) {
        String sql = "SELECT * FROM Usuario WHERE email = ? AND password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, correo);
            pstmt.setString(2, contrasena);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}