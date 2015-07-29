/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agent;

import interfaces.DisplayResults;
import jade.core.Agent;
import jade.core.MessageQueue;
import jade.core.MicroRuntime;
import jade.core.NotFoundException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Items;
import provider.SearchAgentProvider;
import ui.ShowResultsResponse;

/**
 *
 * @author miller.barrera
 */
public class SearchAgent extends Agent implements DisplayResults {

    private String mItemId = "";
    private String mItem = "";
    private String mPrice = "";

    @Override
    protected void setup() {

        System.out.println("Hola soy el agente de Búsqueda ");
        System.out.println("Mi nombre es " + getLocalName());

        Object[] arg = getArguments();
        if (arg != null) {
            mItemId = arg[0].toString();
            mItem = arg[1].toString();
            mPrice = arg[2].toString();
        }

        /**
         * Launch Search request*
         */
        SearchAgentProvider provider = new SearchAgentProvider(this);
        provider.findURL(Integer.parseInt(mItemId), mItem, mPrice);

    }

    @Override
    public void clean(boolean ok) {
        super.clean(ok); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected MessageQueue createMessageQueue() {
        return super.createMessageQueue(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doSuspend() {
        super.doSuspend(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void showSearchResult(Vector<Items> items) {

        new ShowResultsResponse(items).setVisible(true);

        killAgent();

    }

    /**
     * Método para detener el Agente después de cada proceso
     * 
     */
    public void killAgent() {
        try {
            MicroRuntime.killAgent("Buscador");
        } catch (NotFoundException ex) {
            Logger.getLogger(SearchAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
