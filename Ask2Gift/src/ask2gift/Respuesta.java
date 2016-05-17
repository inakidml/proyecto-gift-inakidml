/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ask2gift;

/**
 *
 * @author 7fprog02
 */
public class Respuesta {
    private int id_resp;
    private String texto_rp;
    private int valor; // 0 ó 1
    private int id_pr_rp;
    private Pregunta pregunta;

    /**
     * @return the id_resp
     */
    public int getId_resp() {
        return id_resp;
    }

    /**
     * @param id_resp the id_resp to set
     */
    public void setId_resp(int id_resp) {
        this.id_resp = id_resp;
    }

    /**
     * @return the texto_rp
     */
    public String getTexto_rp() {
        return texto_rp;
    }

    /**
     * @param texto_rp the texto_rp to set
     */
    public void setTexto_rp(String texto_rp) {
        this.texto_rp = texto_rp;
    }

    /**
     * @return the valor
     */
    public int getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(int valor) {
        this.valor = valor;
    }

    /**
     * @return the id_pr_rp
     */
    public int getId_pr_rp() {
        return id_pr_rp;
    }

    /**
     * @param id_pr_rp the id_pr_rp to set
     */
    public void setId_pr_rp(int id_pr_rp) {
        this.id_pr_rp = id_pr_rp;
    }

    /**
     * @return the pregunta
     */
    public Pregunta getPregunta() {
        return pregunta;
    }

    /**
     * @param pregunta the pregunta to set
     */
    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }
    
}
