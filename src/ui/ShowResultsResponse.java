/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.Items;

/**
 *
 * @author miller.barrera Clase Encargada de mostrar en una interfaz gráfica los
 * resultado de la búsqueda
 *
 */
public final class ShowResultsResponse extends javax.swing.JFrame implements ListSelectionListener {

    /**
     * Creates new form ShowResultsResponse
     */
    private Vector<Items> mVectorItems;
    private DefaultListModel mListModel;

    /**
     * @see ShowResultsResponse Clase que muestra en una interfaz gráfica los
     * resultados obtenidos en la búsqueda
     *
     * @param items Vector contenedor de los items encontrados
     *
     *
     */
    public ShowResultsResponse(Vector<Items> items) {
        initComponents();
        mVectorItems = items;
        mListModel = new DefaultListModel();

        fillList();

        listItems.addListSelectionListener(this);
    }

    /**
     * Método que llena la lista con los resultados obtenidos en la búsqueda
     *
     *
     */
    public void fillList() {
        if (!mVectorItems.isEmpty()) { //persona es tu arraylist o list

            for (Items items : mVectorItems) {
                mListModel.addElement("Nombre: " + items.getName() + " -Precio: " + items.getPrice());

            }
            listItems.setModel(mListModel);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        listItems = new javax.swing.JList();
        btnOk = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Su dinero le alcanza para comprar");

        listItems.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(listItems);

        btnOk.setText("OK");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("uno de   los siguientes articulos:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(203, 203, 203)
                .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 159, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(138, 138, 138))
                    .addComponent(jSeparator1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        try {
            // TODO add your handling code here:
            this.dispose();
        } catch (Throwable ex) {
            Logger.getLogger(ShowResultsResponse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnOkActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JList listItems;
    // End of variables declaration//GEN-END:variables

    /**
     * Listener de la selección de la lista, se invoca cada vez que se
     * da click a un elemento de la lista de items para
     * mostrar detalles de cada item en un Frame independiente
     **/
    @Override
    public void valueChanged(ListSelectionEvent e) {
        boolean adjust = e.getValueIsAdjusting();
        System.out.println(", Adjusting? " + adjust);
        if (!adjust) {
            JList list = (JList) e.getSource();
            int selections[] = list.getSelectedIndices();
            List ll = list.getSelectedValuesList();
            for (int i = 0; i < selections.length; i++) {
                System.out.println("SELECTION: " + ll.get(i));
                BuyItemDescription buyItem = new BuyItemDescription(ll.get(i).toString());
                buyItem.setVisible(true);
                
            }

        }

    }
}
