/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package provider;

import agent.SearchAgent;
import controllers.DataBaseController;
import interfaces.DisplayResults;
import jade.core.MicroRuntime;
import jade.core.NotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Items;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author miller.barrera 
 * Clase invocada por el Agente de búsqueda y es la
 * encargada de realizar la búsqueda en la WEB
 */
public class SearchAgentProvider {

    String[] params;
    private String mItemName;
    private String mPriceRange;
    private DataBaseController mDataBasController;
    private DisplayResults mDisplayResult;
    private Vector<Items> mVectorFinalListItems;
    private Items mItems;

    public SearchAgentProvider(DisplayResults displayResult) {
        this.params = params;
        mDataBasController = DataBaseController.getInstance();
        mDisplayResult = displayResult;
        mVectorFinalListItems = new Vector<>();

    }

    /**
     * Método que realiza la petición HTTP con la URL de búsqueda respectiva
     *
     * @param itemId Identificación del Item a buscar item Nombre del Item a
     * buscar price Precio del Item a buscar
     *
     */
    public void findURL(int itemId, String item, String price) {

        try {

            if (mVectorFinalListItems != null && mVectorFinalListItems.size() > 0) {
                mVectorFinalListItems.clear();
            }
            mItemName = item;
            mPriceRange = price;
            String URL_FROM_DATABASE = getURLType(itemId);
            String formated_url = getFormatedURLType(itemId, URL_FROM_DATABASE);
            
            System.out.println("URL: " + formated_url);

            //Hacer llamado a la URL y hacer el parseo de la respuesta HTML
            Document docc = Jsoup.connect(formated_url).get();
            Elements elementPrice = docc.getElementsByClass("ch-price");
            Elements elementName = docc.getElementsByTag("ol");
            Elements href = elementName.select("a[href]");

            ArrayList<String> elmPrice = new ArrayList<>();
            ArrayList<String> elmItemName = new ArrayList<>();

            //ciclo que recupera y guarda en un array el valor de los items
            for (org.jsoup.nodes.Element e : elementPrice) {
                if (e.ownText().length() > 0) {
                    System.out.format("Precio Item: %s \n", e.ownText());
                    elmPrice.add(e.ownText());
                }

            }
            //ciclo que recupera el nombre de los items y los guarda en un array 
            for (org.jsoup.nodes.Element name : href) {
                if (name.ownText().length() > 0 && (!name.ownText().contains("Inicio"))) {
                    System.out.format("Nombre Item: %s \n", name.ownText());
                    elmItemName.add(name.ownText());
                }

            }
            //Aqui se valida el valor ingresado con el valor del precio del item se imprime los resultados
            for (int i = 0; i < elmPrice.size(); i++) {
                try {
                    mItems = new Items();
                    String itemName = elmItemName.get(i);
                    String unformatPrice = elmPrice.get(i);
                    String formatedPrice = unformatPrice.replace("$", "").replace(".", "").replaceAll(" ", "");
                    String tt = formatedPrice.trim();

                    if (Character.isSpaceChar(tt.charAt(0))) {
                        System.out.println("PRIMER ELEMENTOS ES CARACTER EN BLANCO ");
                        tt = tt.substring(1, tt.length());
                    }

                    //Se parsean los String a Enteros para poder hacer las respectivas validaciones                    
                    int formatItemPrice = Integer.parseInt(tt);
                    int myInitPrice = Integer.parseInt(mPriceRange.substring(0, mPriceRange.indexOf("-")));
                    int myEndPrice = Integer.parseInt(mPriceRange.substring(mPriceRange.indexOf("-") + 1, mPriceRange.length()));

                    if ((formatItemPrice > myInitPrice) && (formatItemPrice < myEndPrice)) {
                        System.out.format("Puede Comprar un/a : %s \n", itemName + "- precio " + formatItemPrice);
                        mItems.setName(itemName);
                        mItems.setPrice(unformatPrice);
                        //Agregar items encontrados al Vector
                        mVectorFinalListItems.add(mItems);
                    }

                } catch (NumberFormatException ne) {
                    ne.printStackTrace();
                }

            }
            //Notificar al agente de busqueda  a travez de la Interfaz que se encontraron elementos
            mDisplayResult.showSearchResult(mVectorFinalListItems);

        } catch (IOException ex) {
            Logger.getLogger(SearchAgentProvider.class.getName()).log(Level.SEVERE, null, ex);
            try {
                MicroRuntime.killAgent("Buscador");
            } catch (NotFoundException exx) {
                Logger.getLogger(SearchAgent.class.getName()).log(Level.SEVERE, null, exx);
            }
        }

    }

    /**
     * Método recupera de la Base de datos la URL Correspondiente según la
     * búsqueda solicitada
     *
     * @param id identificación de la URL a buscar
     *
     * @return String URL Solicitada para hacer la búsqueda
     *
     */
    public String getURLType(int id) {
        String URL = mDataBasController.getURLTypeToSearch(id);
        return URL;
    }

    /**
     * Método que formatea la URL y le asigna los parámetros correspondientes
     * para hacer la búsqueda
     *
     * @param id identificación de la búsqueda _url URL sin parámetros
     * @return String URL Formateada con sus respectivos parámetros
     *
     */
    public String getFormatedURLType(int id, String _url) {
        String url = "";
        switch (id) {
            case 1:
                //http://celulares.mercadolibre.com.co/samsung/_PriceRange_350000-700000
                url = _url + mItemName + "/_PriceRange_" + mPriceRange;
                break;
            case 2:
                //http://motos.mercadolibre.com.co/honda/_PriceRange_3500000-9000000
                url = _url + mItemName + "/_PriceRange_" + mPriceRange;
                break;
            case 3:
                //http://computacion.mercadolibre.com.co/computadores/hp/_PriceRange_250000-600000
                url = _url + "/computadores/" + mItemName + "_PriceRange_" + mPriceRange;
                break;
            default:
                url = "Nothing";
        }

        return url;
    }

}
