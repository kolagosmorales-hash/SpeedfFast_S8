package com.duoc.vista;

import javax.swing.*;

public class MainView extends JFrame {

    public MainView(){

        setTitle("SpeedFast - Gestión");
        setSize(900,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Repartidores", new RepartidorPanel());
        tabs.addTab("Pedidos", new PedidoPanel());
        tabs.addTab("Entregas", new EntregaPanel());

        add(tabs);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainView().setVisible(true);
        });
    }
}