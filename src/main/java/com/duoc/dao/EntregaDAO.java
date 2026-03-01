package com.duoc.dao;

import com.duoc.modelo.Entrega;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EntregaDAO implements IEntregaDAO {

    @Override
    public boolean create(Entrega entrega) {

        String sql = "INSERT INTO entregas (id_pedido, id_repartidor, fecha, hora) VALUES (?,?,?,?)";

        try (Connection conexion = ConexionDB.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, entrega.getIdPedido());
            ps.setInt(2, entrega.getIdRepartidor());
            ps.setDate(3, Date.valueOf(entrega.getFecha()));
            ps.setTime(4, Time.valueOf(entrega.getHora()));

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al crear entrega: " + e.getMessage());
        }

        return false;
    }

    @Override
    public List<Entrega> readAll() {

        List<Entrega> lista = new ArrayList<>();
        String sql = "SELECT * FROM entregas";

        try (Connection conexion = ConexionDB.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Entrega entrega = new Entrega(
                        rs.getInt("id"),
                        rs.getInt("id_pedido"),
                        rs.getInt("id_repartidor"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getTime("hora").toLocalTime()
                );

                lista.add(entrega);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar entregas: " + e.getMessage());
        }

        return lista;
    }

    @Override
    public boolean update(Entrega entrega) {

        String sql = "UPDATE entregas SET id_pedido=?, id_repartidor=?, fecha=?, hora=? WHERE id=?";

        try (Connection conexion = ConexionDB.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, entrega.getIdPedido());
            ps.setInt(2, entrega.getIdRepartidor());
            ps.setDate(3, Date.valueOf(entrega.getFecha()));
            ps.setTime(4, Time.valueOf(entrega.getHora()));
            ps.setInt(5, entrega.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar entrega: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean delete(int id) {

        String sql = "DELETE FROM entregas WHERE id=?";

        try (Connection conexion = ConexionDB.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar entrega: " + e.getMessage());
        }

        return false;
    }
}
