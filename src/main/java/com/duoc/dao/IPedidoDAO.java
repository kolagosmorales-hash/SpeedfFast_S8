package com.duoc.dao;

import com.duoc.modelo.Pedido;
import java.util.List;

public interface IPedidoDAO {

    boolean create(Pedido pedido);

    List<Pedido> readAll();

    boolean update(Pedido pedido);

    boolean delete(int id);
}