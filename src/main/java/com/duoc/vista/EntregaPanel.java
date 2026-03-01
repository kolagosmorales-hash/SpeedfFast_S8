package com.duoc.vista;

import com.duoc.controller.EntregaController;
import com.duoc.controller.PedidoController;
import com.duoc.controller.RepartidorController;
import com.duoc.modelo.Entrega;
import com.duoc.modelo.Pedido;
import com.duoc.modelo.Repartidor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class EntregaPanel extends JPanel {

    private JComboBox<Pedido> cbPedido;
    private JComboBox<Repartidor> cbRepartidor;
    private JTextField txtFecha;
    private JTextField txtHora;

    private JTable tabla;
    private DefaultTableModel modelo;

    private EntregaController controller = new EntregaController();
    private PedidoController pedidoController = new PedidoController();
    private RepartidorController repartidorController = new RepartidorController();

    private int idSeleccionado = -1;

    public EntregaPanel() {

        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(5,2,10,10));

        form.add(new JLabel("Pedido:"));
        cbPedido = new JComboBox<>();
        form.add(cbPedido);

        form.add(new JLabel("Repartidor:"));
        cbRepartidor = new JComboBox<>();
        form.add(cbRepartidor);

        form.add(new JLabel("Fecha (YYYY-MM-DD):"));
        txtFecha = new JTextField();
        form.add(txtFecha);

        form.add(new JLabel("Hora (HH:MM):"));
        txtHora = new JTextField();
        form.add(txtHora);

        JButton btnGuardar = new JButton("Guardar / Actualizar");
        JButton btnEliminar = new JButton("Eliminar");

        form.add(btnGuardar);
        form.add(btnEliminar);

        add(form, BorderLayout.NORTH);

        modelo = new DefaultTableModel(
                new String[]{"ID","Pedido","Repartidor","Fecha","Hora"},0);
        tabla = new JTable(modelo);

        add(new JScrollPane(tabla), BorderLayout.CENTER);

        btnGuardar.addActionListener(e -> guardar());
        btnEliminar.addActionListener(e -> eliminar());

        tabla.getSelectionModel().addListSelectionListener(e -> {
            if(!e.getValueIsAdjusting() && tabla.getSelectedRow() != -1){

                idSeleccionado = (int) modelo.getValueAt(tabla.getSelectedRow(),0);
                txtFecha.setText(modelo.getValueAt(tabla.getSelectedRow(),3).toString());
                txtHora.setText(modelo.getValueAt(tabla.getSelectedRow(),4).toString());
            }
        });

        cargarCombos();
        cargarTabla();
    }

    private void cargarCombos(){
        cbPedido.removeAllItems();
        for(Pedido p : pedidoController.listar()){
            cbPedido.addItem(p);
        }

        cbRepartidor.removeAllItems();
        for(Repartidor r : repartidorController.listar()){
            cbRepartidor.addItem(r);
        }
    }

    private void guardar(){

        try {

            Pedido pedido = (Pedido) cbPedido.getSelectedItem();
            Repartidor repartidor = (Repartidor) cbRepartidor.getSelectedItem();

            LocalDate fecha = LocalDate.parse(txtFecha.getText());
            LocalTime hora = LocalTime.parse(txtHora.getText());

            boolean exito;

            if(idSeleccionado == -1){
                exito = controller.registrar(
                        pedido.getId(),
                        repartidor.getId(),
                        fecha,
                        hora
                );
            } else {
                exito = controller.actualizar(
                        idSeleccionado,
                        pedido.getId(),
                        repartidor.getId(),
                        fecha,
                        hora
                );
            }

            if(exito){
                JOptionPane.showMessageDialog(this,"Operación exitosa");
                limpiar();
                cargarTabla();
            }

        } catch (Exception ex){
            JOptionPane.showMessageDialog(this,"Error en fecha u hora");
        }
    }

    private void eliminar(){
        if(idSeleccionado != -1 && controller.eliminar(idSeleccionado)){
            JOptionPane.showMessageDialog(this,"Eliminado");
            limpiar();
            cargarTabla();
        }
    }

    private void limpiar(){
        txtFecha.setText("");
        txtHora.setText("");
        idSeleccionado = -1;
    }

    private void cargarTabla(){
        modelo.setRowCount(0);
        for(Entrega e : controller.listar()){
            modelo.addRow(new Object[]{
                    e.getId(),
                    e.getIdPedido(),
                    e.getIdRepartidor(),
                    e.getFecha(),
                    e.getHora()
            });
        }
    }
}