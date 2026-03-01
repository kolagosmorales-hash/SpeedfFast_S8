package com.duoc.dao;
import com.duoc.modelo.Pedido;
import com.duoc.enumeraciones.TipoPedido;
import com.duoc.enumeraciones.EstadoPedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO implements IPedidoDAO {

    @Override
    public boolean create(Pedido pedido) {

        String sql = "INSERT INTO pedidos (direccion, tipo, estado) VALUES (?,?,?)";

        try (Connection conexion = ConexionDB.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, pedido.getDireccion());
            ps.setString(2, pedido.getTipo().name());
            ps.setString(3, pedido.getEstado().name());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al crear pedido: " + e.getMessage());
        }

        return false;
    }

    @Override
    public List<Pedido> readAll() {

        List<Pedido> lista = new ArrayList<>();
        String sql = "SELECT * FROM pedidos";

        try (Connection conexion = ConexionDB.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Pedido pedido = new Pedido(
                        rs.getInt("id"),
                        rs.getString("direccion"),
                        TipoPedido.valueOf(rs.getString("tipo")),
                        EstadoPedido.valueOf(rs.getString("estado"))
                );

                lista.add(pedido);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar pedidos: " + e.getMessage());
        }

        return lista;
    }

    @Override
    public boolean update(Pedido pedido) {

        String sql = "UPDATE pedidos SET direccion=?, tipo=?, estado=? WHERE id=?";

        try (Connection conexion = ConexionDB.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, pedido.getDireccion());
            ps.setString(2, pedido.getTipo().name());
            ps.setString(3, pedido.getEstado().name());
            ps.setInt(4, pedido.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar pedido: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean delete(int id) {

        String sql = "DELETE FROM pedidos WHERE id=?";

        try (Connection conexion = ConexionDB.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar pedido: " + e.getMessage());
        }

        return false;
    }
}

