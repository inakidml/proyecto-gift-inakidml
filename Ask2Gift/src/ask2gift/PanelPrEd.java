/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ask2gift;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author inaki
 */
public class PanelPrEd extends javax.swing.JPanel {

    private VEditarPr v = null;
    private List<Categoria> categorias;
    Map<String, Pregunta> preguntas;
    private PanelCatExp panelcatexp = null;

    /**
     * Creates new form PanelCatExp
     */
    public PanelPrEd(VEditarPr v, List<Categoria> categorias) {
        initComponents();
        this.v = v;
        this.categorias = categorias;

    }

    public Pregunta getPreguntaCombo() {
        Pregunta p = preguntas.get("" + jComboBox1.getSelectedItem());
        if (p != null) {
            String texto = p.getTexto_pr();
        }
        return p;
    }

    public void rellenarComboPr(Categoria cat) {

        DefaultComboBoxModel modelo = new DefaultComboBoxModel();//Rellenar JComboBox
        preguntas = new Hashtable<>();
        for (Pregunta pregunta : cat.getPreguntas()) {
            modelo.addElement(pregunta.getId_pr());

            preguntas.put(("" + pregunta.getId_pr()), pregunta);
            //System.out.println(pregunta);
        }

        jComboBox1.setModel(modelo);

        rellenarAreaPr();
    }

    public String getTextoEdit() {
        return jTextArea1.getText();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        rellenarAreaPr();
        v.preguntaSelec(getPreguntaCombo());
    }//GEN-LAST:event_jComboBox1ActionPerformed
    public void rellenarAreaPr() {
       
        if (preguntas.size() > 0) {
            Pregunta p = preguntas.get("" + jComboBox1.getSelectedItem());
          
            String texto = p.getTexto_pr();

            jTextArea1.setText(texto);
            
        } else {
            jTextArea1.setText("");

        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the panelcatexp
     */
    public PanelCatExp getPanelcatexp() {
        return panelcatexp;
    }

    /**
     * @param panelcatexp the panelcatexp to set
     */
    public void setPanelcatexp(PanelCatExp panelcatexp) {
        this.panelcatexp = panelcatexp;
    }
}
