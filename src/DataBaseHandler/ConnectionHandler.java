/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBaseHandler;

import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author miller.barrera
 */
public class ConnectionHandler {

    private Connection mConnection = null;
    public static final String ROOT_NAME = "root";
    public static final String ROOT_PASSWORD = "123456789";


    public ConnectionHandler() {
    }


    /**
     * Método utilizado para establecer la conexión con la base de datos
     *
     * @return Connection regresa el estado de la conexión, Connection si se estableció la
     * conexión, null en caso contrario
     */
    public Connection createConnection() {
        try {
           
            Class.forName("com.mysql.jdbc.Driver");
            mConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_agent", ROOT_NAME, ROOT_PASSWORD);
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } catch (ClassNotFoundException cne) {
            System.out.println(cne.getMessage());

        }catch(Exception exx){
            exx.printStackTrace();
        }

        return mConnection;
    }

}
