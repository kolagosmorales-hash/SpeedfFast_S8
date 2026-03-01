package com.duoc.modelo;

import com.duoc.enumeraciones.TipoPedido;
import com.duoc.enumeraciones.EstadoPedido;

public class Pedido {

    private int id;
    private String direccion;
    private TipoPedido tipo;
    private EstadoPedido estado;

    public Pedido(int id, String direccion, TipoPedido tipo, EstadoPedido estado) {
        this.id = id;
        this.direccion = direccion;
        this.tipo = tipo;
        this.estado = estado;
    }

    public Pedido(String direccion, TipoPedido tipo, EstadoPedido estado) {
        this.direccion = direccion;
        this.tipo = tipo;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public String getDireccion() {
        return direccion;
    }

    public TipoPedido getTipo() {
        return tipo;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTipo(TipoPedido tipo) {
        this.tipo = tipo;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return id + " - " + direccion + " (" + tipo + " - " + estado + ")";
    }
}