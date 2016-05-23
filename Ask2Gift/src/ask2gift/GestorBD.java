/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ask2gift;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author 7fprog02
 */
public class GestorBD {

    private Connection conn;
    private Vprincipal vprincipal;

    public void conectar() {
        try {
            // Cargar el driver correspondiente
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            // Cadena de conexión: driver@machineName:port:SID, userid, password
            conn = DriverManager.getConnection("jdbc:oracle:thin:@10.10.10.9:1521:db12102", "system", "oracle");
            System.out.println("INFO: Conexión abierta");
        } catch (SQLException ex) {
            Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void desconectar() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("INFO: Conexión cerrada.");
            } catch (SQLException ex) {
                Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void cargarListaCat() { //llamo en el constructor de vprincipal, en editarCat y borrarCat
        conectar();
        // Llamada a procedimiento almacenado
        // Creamos el statement
        String sql = "{ call selects.select_categorias(?) }";
        try {
            vprincipal.getCategorias().removeAll(vprincipal.getCategorias());
            CallableStatement cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.execute();
            try (ResultSet rs = (ResultSet) cs.getObject(1)) {
                while (rs.next()) {
                    Categoria c = new Categoria();
                    c.setId(rs.getInt(1));
                    c.setNombre(rs.getString(2));
                    vprincipal.getCategorias().add(c);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        desconectar();
    }

    public int insertarCategoria(String nombre) {
        int id = -1;
        conectar();
        // Llamada a procedimiento almacenado
        // Creamos el statement
        String sql = "{ call inserts.insertar_categoria(?,?) }";
        try {
            CallableStatement cs = conn.prepareCall(sql);
            // Cargamos los parametros de entrada IN
            cs.setString(1, nombre);//como primer valor, mando el nombre de la cat
            cs.registerOutParameter(2, OracleTypes.INTEGER); //como segundo valor recibo un integer con el ID generado
            cs.execute();
            id = cs.getInt(2);//paso a una variable el integer
            //System.out.println(id + " " + nombre);
        } catch (SQLException ex) {
            Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        desconectar();
        return id;
    }

    public void borrarCat(String nombre) {
        conectar();
        // Llamada a procedimiento almacenado
        // Creamos el statement
        String sql = "{ call deletes.delete_categoria(?) }";
        try {
            CallableStatement cs = conn.prepareCall(sql);
            // Cargamos los parametros de entrada IN
            cs.setString(1, nombre);//como primer valor, mando el nombre de la cat
            cs.execute();

        } catch (SQLException ex) {
            Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        desconectar();
        cargarListaCat();
    }

    public void editarCat(String oldNombre, String newNombre) {
        conectar();
        // Llamada a procedimiento almacenado
        // Creamos el statement
        String sql = "{ call updates.update_categoria(?,?) }";
        try {
            CallableStatement cs = conn.prepareCall(sql);
            // Cargamos los parametros de entrada IN
            cs.setString(1, oldNombre);//como primer valor, mando el nombre de la cat
            cs.setString(2, newNombre);// segundo el nuevo nombre
            cs.execute();

        } catch (SQLException ex) {
            Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        desconectar();
        cargarListaCat();

    }

    public void cargarListaPr(Categoria c) {  //llamo en el constructor de vprincipal
        conectar();
        // Llamada a procedimiento almacenado
        // Creamos el statement
        List<Pregunta> prs = c.getPreguntas(); // temporal
        String sql = "{ call selects.select_preguntas(?,?) }";
        try {
            prs.removeAll(prs);
            CallableStatement cs = conn.prepareCall(sql);
            cs.setInt(1, c.getId());
            cs.registerOutParameter(2, OracleTypes.CURSOR);
            cs.execute();
            try (ResultSet rs = (ResultSet) cs.getObject(2)) {
                while (rs.next()) {
                    Pregunta p = new Pregunta();
                    p.setId_pr(rs.getInt(1));
                    p.setTexto_pr(rs.getString(2));
                    p.setId_cat_pr(rs.getInt(3));
                    prs.add(p);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        desconectar();
    }

    public int insertarPregunta(String texto, String cat) {
        int id = -1;
        conectar();
        // Llamada a procedimiento almacenado
        // Creamos el statement
        String sql = "{ call inserts.insertar_pregunta(?,?,?) }";
        try {
            CallableStatement cs = conn.prepareCall(sql);
            // Cargamos los parametros de entrada IN
            cs.setString(1, texto);//como primer valor, mando el texto de la pr
            cs.setString(2, cat);
            cs.registerOutParameter(3, OracleTypes.INTEGER); //como tercer valor recibo un integer con el ID generado
            cs.execute();
            id = cs.getInt(3);//paso a una variable el integer
            //System.out.println(id + " " + nombre);
        } catch (SQLException ex) {
            Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        desconectar();
        return id;
    }

    public void cargarListaRp(Pregunta p) {   //llamo en el constructor de vprincipal
        conectar();
        // Llamada a procedimiento almacenado
        // Creamos el statement
        String sql = "{ call selects.select_respuestas(?,?) }";
        try {
            List<Respuesta> rps = p.getRespuestas();
            rps.removeAll(rps);
            CallableStatement cs = conn.prepareCall(sql);
            cs.setInt(1, p.getId_pr());
            cs.registerOutParameter(2, OracleTypes.CURSOR);
            cs.execute();
            try (ResultSet rs = (ResultSet) cs.getObject(2)) {
                while (rs.next()) {
                    Respuesta r = new Respuesta();
                    r.setId_resp(rs.getInt(1));
                    r.setTexto_rp(rs.getString(2));
                    r.setValor(rs.getInt(3));
                    r.setId_pr_rp(rs.getInt(4));
                    rps.add(r);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        desconectar();
    }

    public void borrarPr(Pregunta p) {
        conectar();
        // Llamada a procedimiento almacenado
        // Creamos el statement
        String sql = "{ call deletes.delete_pregunta(?) }";
        try {
            CallableStatement cs = conn.prepareCall(sql);
            // Cargamos los parametros de entrada IN
            cs.setInt(1, p.getId_pr());//como primer valor, mando el id de la pr
            cs.execute();

        } catch (SQLException ex) {
            Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        desconectar();

    }

    public void modificarPr(Pregunta p) {
conectar();
        // Llamada a procedimiento almacenado
        // Creamos el statement
        String sql = "{ call UPDATES.UPDATE_PREGUNTA(?,?,?) }";
        try {
            CallableStatement cs = conn.prepareCall(sql);
            // Cargamos los parametros de entrada IN
            cs.setInt(1, p.getId_pr());
            cs.setString(2, p.getTexto_pr());
            cs.setInt(3, p.getId_cat_pr()); 
            cs.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        desconectar();
    }

    public void modificarRP(Respuesta r) {
        
        conectar();
        // Llamada a procedimiento almacenado
        // Creamos el statement
        String sql = "{ call UPDATES.UPDATE_RESPUESTA(?,?,?) }";
        try {
            CallableStatement cs = conn.prepareCall(sql);
            // Cargamos los parametros de entrada IN
            cs.setInt(1, r.getId_resp());
            cs.setString(2, r.getTexto_rp());
            cs.setInt(3, r.getValor()); 
            cs.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        desconectar();
    }

    public int insertarRespuesta(String texto, int valor, int id_pr) {
        int id = -1;
        conectar();
        // Llamada a procedimiento almacenado
        // Creamos el statement
        String sql = "{ call inserts.insertar_respuesta(?,?,?,?) }";
        try {
            CallableStatement cs = conn.prepareCall(sql);
            // Cargamos los parametros de entrada IN
            cs.setString(1, texto);//como primer valor, mando el texto de la pr
            cs.setInt(2, valor);
            cs.setInt(3, id_pr);
            cs.registerOutParameter(4, OracleTypes.INTEGER); //como segundo valor recibo un integer con el ID generado
            cs.execute();
            id = cs.getInt(4);//paso a una variable el integer
            //System.out.println(id + " " + nombre);

        } catch (SQLException ex) {
            Logger.getLogger(GestorBD.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        desconectar();
        return id;
    }

    /**
     * @param vprincipal the vprincipal to set
     */
    public void setVprincipal(Vprincipal vprincipal) {
        this.vprincipal = vprincipal;
    }
}

/* 
 * Más detalles:
 * http://blog.vortexbird.com/2011/09/28/llamar-procedimiento-almacenado-en-oracle-desde-jdbc/
 * 
 */
