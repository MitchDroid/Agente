/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 *
 */
package controllers;

import DataBaseHandler.ConnectionHandler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.User;

/**
 *
 * @author miller.barrera
 *
 */
public class DataBaseController {

    private ConnectionHandler mConnectionHandler;
    private Connection mConnection = null;
    private static DataBaseController mController;

    public static DataBaseController getInstance() {
        if (mController == null) {
            mController = new DataBaseController();
        }
        return mController;
    }

    /**
     * Esta clase es la interfaz entre la base de datos y las vistas
     */
    public DataBaseController() {
        mConnectionHandler = new ConnectionHandler();
        mConnection = mConnectionHandler.createConnection();

    }

    /**
     * Método para recuperar el formato de URL según los criterios de búsqueda.
     *
     * @param id identificación de la URL solicitada
     * @return String con la URL Formateada
     */
    public String getURLTypeToSearch(int id) {
        String URL = "";

        if (mConnection != null) {
            try {
                String SQL_INSERT_QUERY = "SELECT * FROM tb_url_store WHERE url_id = " + id;
                PreparedStatement pst = mConnection.prepareStatement(SQL_INSERT_QUERY);
                ResultSet rs = pst.executeQuery();

                while (rs.next() == true) {
                    URL = rs.getString("url");

                }

                pst.close();

            } catch (SQLException e) {
                e.printStackTrace();

            }

        }

        return URL;
    }

    /**
     * Método para insertar nuevos registros de usuarios en la base de datos.
     *
     * @return 1 si el registro fué insertado correctamente
     */
    public int sqlItemInsert(String itemName, String itemPrice) {

        int queryResult = 0;
        if (mConnection != null) {
            try {
                String SQL_INSERT_QUERY = "INSERT INTO tb_saved_items (item_name,item_price) VALUES (?,?)";
                PreparedStatement pst = mConnection.prepareStatement(SQL_INSERT_QUERY);

                pst.setString(1, itemName);
                pst.setString(2, itemPrice);

                /**
                 * Execute QUERY*
                 */
                queryResult = pst.executeUpdate();
                

                if (pst != null) {
                    pst.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();

            }
        }
        return queryResult;
    }

}
