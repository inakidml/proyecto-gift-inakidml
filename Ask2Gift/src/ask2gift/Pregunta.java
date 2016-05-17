/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ask2gift;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 7fprog02
 */
public class Pregunta {
    private int id_pr;
    private String texto_pr;
    private int id_cat_pr;
    private List<Respuesta> respuestas=new ArrayList<>();
    private Categoria categoria;

    /**
     * @return the id_pr
     */
    public int getId_pr() {
        return id_pr;
    }

    /**
     * @param id_pr the id_pr to set
     */
    public void setId_pr(int id_pr) {
        this.id_pr = id_pr;
    }

    /**
     * @return the texto_pr
     */
    public String getTexto_pr() {
        return texto_pr;
    }

    /**
     * @param texto_pr the texto_pr to set
     */
    public void setTexto_pr(String texto_pr) {
        this.texto_pr = texto_pr;
    }

    /**
     * @return the id_cat_pr
     */
    public int getId_cat_pr() {
        return id_cat_pr;
    }

    /**
     * @param id_cat_pr the id_cat_pr to set
     */
    public void setId_cat_pr(int id_cat_pr) {
        this.id_cat_pr = id_cat_pr;
    }

    /**
     * @return the respuestas
     */
    public List<Respuesta> getRespuestas() {
        return respuestas;
    }

    /**
     * @return the categoria
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
}
