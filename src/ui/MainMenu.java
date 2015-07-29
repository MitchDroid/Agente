package ui;

import DataBaseHandler.ConnectionHandler;
import agent.SearchAgent;
import jade.core.Agent;
import jade.core.MicroRuntime;
import jade.util.leap.Properties;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Inet4Address;
import java.sql.Connection;
import javax.swing.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jorge Clase encargada de mostrar la interfaz de Usuario
 *
 */
public class MainMenu extends JFrame implements ActionListener {

    public JLabel lPrin, lPri, lBuscar, lTipo, lDinero;
    public JComboBox cbArticle, cbItem, cbDinero;
    public JButton bBuscar, btnGuardar;
    public MenuBar mbOpciones;
    public MenuItem miAcerca, miSalir;

    private ConnectionHandler mConnectionHandler;
    private Connection mConnection = null;
    private boolean isConnection;
    static JProgressBar progressBar;

    private static String JADE_PLATFORM_PORT_KEY = "1099";
    private static String JADE_PLATFORM_LOCAL_HOST_IP_ADDRESS = "";

    /**
     * Agentes
     */
    private Agent mAgent;
    private SearchAgent mSearchAgent;

    private void initDataBaseHandler() {
        mConnectionHandler = new ConnectionHandler();
        mConnection = mConnectionHandler.createConnection();

    }

    public MainMenu(Agent agent) {
        super("Agente de Busqueda");

        try {

            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        this.mAgent = agent;
        mSearchAgent = new SearchAgent();
        initDataBaseHandler();

        this.setBounds(400, 150, 500, 500);

        Menu file = new Menu("Opciones");

        mbOpciones = new MenuBar();
        miAcerca = new MenuItem("Acerca de");
        miSalir = new MenuItem("Salir");

        lPrin = new JLabel("Ayudante de Busqueda");
        lPri = new JLabel("<html>El ayudante de  busqueda  le permitira encontrar en internet exactamente lo que necesite de acuerdo a unas opciones<br> escogidas por usted </html>");
        lBuscar = new JLabel("Que desea buscar?");
        bBuscar = new JButton("Buscar");

        lTipo = new JLabel("Tipo o marca");
        lDinero = new JLabel("Precio");
        cbArticle = new JComboBox();
        cbArticle.addItem("Seleccione opcion");
        cbArticle.addItem("Celulares");
        cbArticle.addItem("Motos");
        cbArticle.addItem("Computadores");
        cbItem = new JComboBox();
        cbItem.addItem("Seleccione Opcion");
        cbDinero = new JComboBox();
        cbDinero.addItem("Seleccione precio");

        lPrin.setBounds(160, 10, 300, 30);
        lPri.setBounds(10, 50, 400, 60);
        lBuscar.setBounds(170, 110, 200, 30);
        cbArticle.setBounds(160, 160, 170, 30);
        lTipo.setBounds(190, 190, 170, 30);
        cbItem.setBounds(160, 220, 170, 30);
        lDinero.setBounds(210, 260, 170, 30);
        cbDinero.setBounds(160, 290, 170, 30);
        bBuscar.setBounds(this.getWidth() / 4, 350, 100, 30);

        progressBar = new JProgressBar(0, 40);
        progressBar.setVisible(false);
        progressBar.setBorderPainted(true);
        progressBar.setString("Buscando...");
        progressBar.setBounds((this.getWidth() / 4), 400, 200, 30);

        file.add(miAcerca);
        file.add(miSalir);
        mbOpciones.add(file);
        setMenuBar(mbOpciones);
        add(lPrin);
        add(lPri);
        add(lBuscar);
        add(cbArticle);
        add(cbItem);
        add(lTipo);
        add(lDinero);
        add(cbDinero);
        add(bBuscar);
        add(progressBar);

        cbArticle.addActionListener(this);
        bBuscar.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cbArticle) {
            rellenaCombo2((String) cbArticle.getSelectedItem());
        } else if (e.getSource() == bBuscar) {

            progressBar.setVisible(true);
            progressBar.setStringPainted(true);
            new Thread(new startProgressBar()).start();
            String[] params = new String[3];
            String itemId = String.valueOf(cbArticle.getSelectedIndex());
            String item = cbItem.getSelectedItem().toString();
            String price = cbDinero.getSelectedItem().toString();
            params[0] = itemId;
            params[1] = item;
            params[2] = price;
            initSearchAgent(params);

        }

    }

    /**
     * Método que inicia el agente de búsqueda
     *
     * @param params arreglo de strings con los parámetros de búsqueda
     *
     *
     */
    public void initSearchAgent(String[] params) {
        try {
            String packaClassName = SearchAgent.class.getName();
            Properties pp = new jade.util.leap.Properties();
            JADE_PLATFORM_LOCAL_HOST_IP_ADDRESS = Inet4Address.getLocalHost().getHostAddress();
            pp.setProperty(MicroRuntime.HOST_KEY, JADE_PLATFORM_LOCAL_HOST_IP_ADDRESS);
            pp.setProperty(MicroRuntime.PORT_KEY, JADE_PLATFORM_PORT_KEY);

            MicroRuntime.startJADE(pp, new Runnable() {
                public void run() {
                }
            });

            if (MicroRuntime.isRunning()) {
                MicroRuntime.startAgent("Buscador", packaClassName, params);
            } else {
                System.err.println("Error iniciando JADE Agente Buscador");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Metodo para setear valores al Combobox según el criterio de búsqueda
     **/
    public void rellenaCombo2(String seleccionEnCombo1) {

        if (cbItem.getItemCount() > 0) {
            cbItem.removeAllItems();
        }
        if (cbDinero.getItemCount() > 0) {
            cbDinero.removeAllItems();
        }
        if (seleccionEnCombo1.equals("Celulares")) {
            cbItem.addItem("Seleccione Opcion");
            cbItem.addItem("Motorola");
            cbItem.addItem("Samsung");
            cbItem.addItem("Nokia");

            cbDinero.addItem("Seleccione Opcion");
            cbDinero.addItem("0-250000");
            cbDinero.addItem("250000-550000");
            cbDinero.addItem("300000-600000");
            cbDinero.addItem("600000-1000000");

        }
        if (seleccionEnCombo1.equals("Motos")) {
            cbItem.addItem("Seleccione Opcion");
            cbItem.addItem("susuki");
            cbItem.addItem("honda");
            cbItem.addItem("bajaj");

            cbDinero.addItem("Seleccione Opcion");
            cbDinero.addItem("0-4000000");
            cbDinero.addItem("4000000-10000000");
        }
        if (seleccionEnCombo1.equals("Computadores")) {
            cbItem.addItem("Seleccione Opcion");
            cbItem.addItem("HP");
            cbItem.addItem("DELL");
            cbItem.addItem("Compaq");

            cbDinero.addItem("Seleccione Opcion");
            cbDinero.addItem("0-40000");
            cbDinero.addItem("300000-950000");
            cbDinero.addItem("600000-1000000");

        }
    }

    /**
     * Este Método se encarga de lanzar el hilo para visualizar la barra de
     * progreso cada vez que se ejecuta una búsqeuda
     *
     */
    public static class startProgressBar implements Runnable {

        public void run() {
            for (int i = 0; i <= 40; i++) {
                progressBar.setValue(i);
                progressBar.repaint();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException err) {
                    err.printStackTrace();
                }

            }

        }

    }

}
