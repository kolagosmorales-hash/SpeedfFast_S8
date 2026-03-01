package com.duoc.dao;

import com.duoc.modelo.Entrega;
import java.util.List;

public interface IEntregaDAO {

    boolean create(Entrega entrega);

    List<Entrega> readAll();

    boolean update(Entrega entrega);

    boolean delete(int id);
}