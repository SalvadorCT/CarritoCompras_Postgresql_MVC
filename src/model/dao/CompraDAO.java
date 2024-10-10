package model.dao;

import Database.DatabaseConnection;
import model.Compra;
import model.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompraDAO {
    public void addCompra(Compra compra, int usuarioId) {
        String insertCompraSql = "INSERT INTO Compra (usuario_id, total) VALUES (?, ?)";
        String insertCompraProductoSql = "INSERT INTO compraProducto (compra_id, producto_id, cantidad) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement pstmt = conn.prepareStatement(insertCompraSql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                pstmt.setInt(1, usuarioId);
                pstmt.setDouble(2, compra.getTotal());
                pstmt.executeUpdate();

                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    int compraId = rs.getInt(1);

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

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            /*try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }*/
        }
    }

    public List<Compra> getHistorialCompras(int usuarioId) {
        List<Compra> compras = new ArrayList<>();
        String sql = "SELECT * FROM Compra WHERE usuario_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, usuarioId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Compra compra = new Compra(rs.getInt("id")); // Proveer un id válido
                compra.setTotal(rs.getDouble("total"));
                compras.add(compra);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return compras;
    }
}