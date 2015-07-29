/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces;

import java.util.Vector;
import model.Items;

/**
 *
 * @author miller.barrera
 */
public interface DisplayResults {

    /**
     *
     * @param items Vector con los elementos obtenidos en la búsqueda
     *              notificados a travéz de la interfaz
     */
    void showSearchResult(Vector<Items> items);
    
}
