package controllers;

import Database.DatabaseConnection;
import model.Compra;
import model.Producto;
import view.CompraView;

import java.sql.*;
import java.util.ArrayList;

public class CompraController {
    private CompraView compraView;

    public CompraController() {
        this.compraView = new CompraView();
    }

    public void addCompra(Compra compra, int usuarioId) {
        String insertCompraSql = "INSERT INTO Compra (usuario_id, total) VALUES (?, ?)";
        String insertCompraProductoSql = "INSERT INTO compraProducto (compra_id, producto_id, cantidad) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false); // Start transaction

            // Insert into Compra table
            try (PreparedStatement pstmt = conn.prepareStatement(insertCompraSql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                pstmt.setInt(1, usuarioId);
                pstmt.setDouble(2, compra.getTotal());
                pstmt.executeUpdate();

                // Get generated compra_id
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    int compraId = rs.getInt(1);

                    // Insert into compraProducto table
                    try (PreparedStatement pstmt2 = conn.prepareStatement(insertCompraProductoSql)) {
                        for (Producto producto : compra.getProductos()) {
                            pstmt2.setInt(1, compraId);
                            pstmt2.setInt(2, producto.getId());
                            pstmt2.setInt(3, producto.getCantidad());
                            pstmt2.addBatch();
                        }
                        pstmt2.executeBatch();
                    }
                }
            }

            conn.commit(); // Commit transaction
            compraView.printMessage("Compra realizada con éxito");
        } catch (SQLException e) {
            /*e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }*/
            compraView.printMessage("Error al realizar la compra");
        }
    }

    public void DisplayHistorialCompras(int usuarioId) {
        String sql = "SELECT * FROM Compra WHERE usuario_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, usuarioId);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Compra> compras = new ArrayList<>();
            while (rs.next()) {
                Compra compra = new Compra();
                compra.setId(rs.getInt("id"));
                compra.setTotal(rs.getDouble("total"));
                compras.add(compra);
            }
            compraView.printCompras(compras);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}