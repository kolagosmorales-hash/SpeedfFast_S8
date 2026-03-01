package com.duoc.dao;

import com.duoc.modelo.Repartidor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepartidorDAO implements IRepartidorDAO {
    @Override
    public boolean create(Repartidor repartidor) {
        String sql = "INSERT INTO repartidores (nombre) VALUES (?)";

        try (Connection conexion = ConexionDB.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, repartidor.getNombre());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al crear repartidor: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<Repartidor> readAll() {
        List<Repartidor> lista = new ArrayList<>();
        String sql = "SELECT * FROM repartidores";

        try (Connection conexion = ConexionDB.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Repartidor(
                        rs.getInt("id"),
                        rs.getString("nombre")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Error al listar repartidores: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean update(Repartidor repartidor) {
        String sql = "UPDATE repartidores SET nombre = ? WHERE id = ?";

        try (Connection conexion = ConexionDB.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, repartidor.getNombre());
            ps.setInt(2, repartidor.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar repartidor: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM repartidores WHERE id = ?";

        try (Connection conexion = ConexionDB.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar repartidor: " + e.getMessage());
        }
        return false;
    }
}