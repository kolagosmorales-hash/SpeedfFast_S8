package com.duoc.vista;

import com.duoc.controller.PedidoController;
import com.duoc.enumeraciones.EstadoPedido;
import com.duoc.enumeraciones.TipoPedido;
import com.duoc.modelo.Pedido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PedidoPanel extends JPanel {

    private JTextField txtDireccion;
    private JComboBox<TipoPedido> cbTipo;
    private JComboBox<EstadoPedido> cbEstado;

    private JTable tabla;
    private DefaultTableModel modelo;

    private PedidoController controller = new PedidoController();
    private int idSeleccionado = -1;

    public PedidoPanel(){

        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(4,2,10,10));

        form.add(new JLabel("Dirección:"));
        txtDireccion = new JTextField();
        form.add(txtDireccion);

        form.add(new JLabel("Tipo:"));
        cbTipo = new JComboBox<>(TipoPedido.values());
        form.add(cbTipo);

        form.add(new JLabel("Estado:"));
        cbEstado = new JComboBox<>(EstadoPedido.values());
        form.add(cbEstado);

        JButton btnGuardar = new JButton("Guardar / Actualizar");
        JButton btnEliminar = new JButton("Eliminar");

        form.add(btnGuardar);
        form.add(btnEliminar);

        add(form, BorderLayout.NORTH);

        modelo = new DefaultTableModel(
                new String[]{"ID","Dirección","Tipo","Estado"},0);

        tabla = new JTable(modelo);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        btnGuardar.addActionListener(e -> guardar());
        btnEliminar.addActionListener(e -> eliminar());

        tabla.getSelectionModel().addListSelectionListener(e -> {
            if(!e.getValueIsAdjusting() && tabla.getSelectedRow()!=-1){
                idSeleccionado = (int) modelo.getValueAt(tabla.getSelectedRow(),0);
                txtDireccion.setText((String) modelo.getValueAt(tabla.getSelectedRow(),1));
                cbTipo.setSelectedItem(modelo.getValueAt(tabla.getSelectedRow(),2));
                cbEstado.setSelectedItem(modelo.getValueAt(tabla.getSelectedRow(),3));
            }
        });

        cargarTabla();
    }

    private void guardar(){

        boolean exito;

        if(idSeleccionado == -1){
            exito = controller.registrar(
                    txtDireccion.getText(),
                    (TipoPedido) cbTipo.getSelectedItem(),
                    (EstadoPedido) cbEstado.getSelectedItem()
            );
        } else {
            exito = controller.actualizar(
                    idSeleccionado,
                    txtDireccion.getText(),
                    (TipoPedido) cbTipo.getSelectedItem(),
                    (EstadoPedido) cbEstado.getSelectedItem()
            );
        }

        if(exito){
            JOptionPane.showMessageDialog(this,"Operación exitosa");
            txtDireccion.setText("");
            idSeleccionado = -1;
            cargarTabla();
        }
    }

    private void eliminar(){
        if(idSeleccionado != -1 && controller.eliminar(idSeleccionado)){
            JOptionPane.showMessageDialog(this,"Eliminado");
            txtDireccion.setText("");
            idSeleccionado = -1;
            cargarTabla();
        }
    }

    private void cargarTabla(){
        modelo.setRowCount(0);
        for(Pedido p : controller.listar()){
            modelo.addRow(new Object[]{
                    p.getId(),
                    p.getDireccion(),
                    p.getTipo(),
                    p.getEstado()
            });
        }
    }
}