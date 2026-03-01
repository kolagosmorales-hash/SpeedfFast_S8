package com.duoc.vista;

import com.duoc.controller.RepartidorController;
import com.duoc.modelo.Repartidor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class RepartidorPanel extends JPanel {

    private JTextField txtNombre;
    private JTable tabla;
    private DefaultTableModel modelo;

    private RepartidorController controller = new RepartidorController();
    private int idSeleccionado = -1;

    public RepartidorPanel() {

        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(2,2,10,10));

        form.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        form.add(txtNombre);

        JButton btnGuardar = new JButton("Guardar / Actualizar");
        JButton btnEliminar = new JButton("Eliminar");

        form.add(btnGuardar);
        form.add(btnEliminar);

        add(form, BorderLayout.NORTH);

        modelo = new DefaultTableModel(new String[]{"ID","Nombre"},0);
        tabla = new JTable(modelo);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        btnGuardar.addActionListener(e -> guardar());
        btnEliminar.addActionListener(e -> eliminar());

        tabla.getSelectionModel().addListSelectionListener(e -> {
            if(!e.getValueIsAdjusting() && tabla.getSelectedRow() != -1){
                idSeleccionado = (int) modelo.getValueAt(tabla.getSelectedRow(),0);
                txtNombre.setText((String) modelo.getValueAt(tabla.getSelectedRow(),1));
            }
        });

        cargarTabla();
    }

    private void guardar(){

        boolean exito;

        if(idSeleccionado == -1){
            exito = controller.registrar(txtNombre.getText());
        } else {
            exito = controller.actualizar(idSeleccionado, txtNombre.getText());
        }

        if(exito){
            JOptionPane.showMessageDialog(this,"Operación exitosa");
            txtNombre.setText("");
            idSeleccionado = -1;
            cargarTabla();
        }
    }

    private void eliminar(){
        if(idSeleccionado != -1 && controller.eliminar(idSeleccionado)){
            JOptionPane.showMessageDialog(this,"Eliminado");
            txtNombre.setText("");
            idSeleccionado = -1;
            cargarTabla();
        }
    }

    private void cargarTabla(){
        modelo.setRowCount(0);
        for(Repartidor r : controller.listar()){
            modelo.addRow(new Object[]{r.getId(), r.getNombre()});
        }
    }
}