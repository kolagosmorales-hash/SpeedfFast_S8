package com.duoc.dao;

import com.duoc.modelo.Repartidor;
import java.util.List;

public interface IRepartidorDAO {

    boolean create(Repartidor repartidor);
    List<Repartidor> readAll();
    boolean update(Repartidor repartidor);
    boolean delete(int id);
}