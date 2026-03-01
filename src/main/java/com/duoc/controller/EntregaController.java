package com.duoc.controller;

import com.duoc.dao.IEntregaDAO;
import com.duoc.dao.EntregaDAO;
import com.duoc.modelo.Entrega;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class EntregaController {

    private IEntregaDAO dao = new EntregaDAO();

    public boolean registrar(int idPedido, int idRepartidor, LocalDate fecha, LocalTime hora) {
        return dao.create(new Entrega(0, idPedido, idRepartidor, fecha, hora));
    }

    public List<Entrega> listar() {
        return dao.readAll();
    }

    public boolean actualizar(int id, int idPedido, int idRepartidor, LocalDate fecha, LocalTime hora) {
        return dao.update(new Entrega(id, idPedido, idRepartidor, fecha, hora));
    }

    public boolean eliminar(int id) {
        return dao.delete(id);
    }
}