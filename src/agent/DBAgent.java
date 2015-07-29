/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agent;

import controllers.DataBaseController;
import jade.core.Agent;
import jade.core.MicroRuntime;
import jade.core.NotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author miller.barrera
 */
public class DBAgent extends Agent {

    private String mItemName = "";
    private String mItemPrice = "";
    private DataBaseController mDataBasController;

    @Override
    protected void setup() {
        System.out.println("Hola soy el agente Encargado de la Base de Datos ");
        System.out.println("Mi nombre es " + getLocalName());

        mDataBasController = DataBaseController.getInstance();
        Object[] arg = getArguments();
        if (arg != null) {
            mItemName = arg[0].toString();
            mItemPrice = arg[1].toString();
        }

        saveItemsInDataBase();

    }

    /**
     * Método para invocar la función para insertar nuevos registros de
     * articulos en la base de datos.
     *
     * @return 1 si el registro fué insertado correctamente
     */
    public void saveItemsInDataBase() {
        int queryResult = mDataBasController.sqlItemInsert(mItemName, mItemPrice);

        if (queryResult == 1) {
            JOptionPane.showConfirmDialog(null, "Reporte Agente: " + getLocalName() + "!! - Registro insertado correctamente!", "Mensaje", JOptionPane.OK_OPTION);
        } else {
            JOptionPane.showConfirmDialog(null, "Reporte Agente: " + getLocalName() + "!! - Error Insertando Registro!", "Mensaje", JOptionPane.OK_OPTION);
        }
        
        killAgent();

    }

    /**
     * Método para detener el Agente después de cada proceso
     * 
     */
    public void killAgent() {
        try {
            MicroRuntime.killAgent("AgenteBD");
        } catch (NotFoundException ex) {
            Logger.getLogger(SearchAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void clean(boolean ok) {
        super.clean(ok); //To change body of generated methods, choose Tools | Templates.
    }

}
