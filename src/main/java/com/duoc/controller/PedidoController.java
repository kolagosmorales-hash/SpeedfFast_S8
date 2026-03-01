package com.duoc.controller;

import com.duoc.dao.IPedidoDAO;
import com.duoc.dao.PedidoDAO;
import com.duoc.enumeraciones.EstadoPedido;
import com.duoc.enumeraciones.TipoPedido;
import com.duoc.modelo.Pedido;

import java.util.List;

public class PedidoController {

    private IPedidoDAO dao = new PedidoDAO();

    public boolean registrar(String direccion, TipoPedido tipo, EstadoPedido estado) {
        return dao.create(new Pedido(0, direccion, tipo, estado));
    }

    public List<Pedido> listar() {
        return dao.readAll();
    }

    public boolean actualizar(int id, String direccion, TipoPedido tipo, EstadoPedido estado) {
        return dao.update(new Pedido(id, direccion, tipo, estado));
    }

    public boolean eliminar(int id) {
        return dao.delete(id);
    }
}