/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package agent;

import jade.core.Agent;
import ui.MainMenu;

/**
 *
 * @author miller.barrera
 */
public class UserAgent extends Agent {
    
    private MainMenu mMainMenu;

    @Override
    protected void setup() {
        mMainMenu = new MainMenu(this);
        mMainMenu.setVisible(true);
        
         System.out.println("Hola soy el Agente encargado de iniciar la Interfaz de Agentes.");
        System.out.println("Mi nombre es " + getLocalName());
    }
    
}
