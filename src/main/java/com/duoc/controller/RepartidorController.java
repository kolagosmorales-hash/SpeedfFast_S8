package com.duoc.controller;

import com.duoc.dao.IRepartidorDAO;
import com.duoc.dao.RepartidorDAO;
import com.duoc.modelo.Repartidor;

import java.util.List;

public class RepartidorController {

    private IRepartidorDAO dao = new RepartidorDAO();

    public boolean registrar(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return false;
        }
        return dao.create(new Repartidor(0, nombre));
    }

    public List<Repartidor> listar() {
        return dao.readAll();
    }

    public boolean actualizar(int id, String nombre) {
        return dao.update(new Repartidor(id, nombre));
    }

    public boolean eliminar(int id) {
        return dao.delete(id);
    }
}