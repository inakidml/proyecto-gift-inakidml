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
import java.sql.Statement;
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

    public void cargarListaCat() {
        conectar();
        // Llamada a procedimiento almacenado
        // Creamos el statement
        String sql = "{ call selects.select_categoria(?) }";
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

    public void oraclesqlejemplo() throws SQLException {
        /*     try {
         // Cargamos los parametros de entrada IN
         cs.setString(1, "nuevo");
         cs.setString(2, "Viteiz");

         // Ejecutamos la llamada
         cs.execute();

         System.out.println("INFO: Procedimiento ejecutado");

         // Llamada a procedimiento almacenado
         // Creamos el statement
         String sql2 = "{ call gest_depart.visualizar_lista_depart(?) }";
         CallableStatement cs2 = conn.prepareCall(sql2);

         // Cargamos los parametros de entrada OUT
         cs2.registerOutParameter(1, OracleTypes.CURSOR);

         // Ejecutamos la llamada
         cs2.execute();

         ResultSet rs = (ResultSet) cs2.getObject(1);

         while (rs.next()) {
         System.out.println(rs.getString("LOC"));
         }
         rs.close();

         System.out.println("INFO: Procedimiento ejecutado");

         } catch (SQLException ex) {
         System.out.println("ERROR: No se ha podido ejecutar la consulta");
         Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
         }
         */
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
/*
 // Consulta simple
 Statement stmt = conn.createStatement();
 ResultSet rset = stmt.executeQuery("select * from SYS.V_$VERSION");
 while (rset.next()) {
 System.out.println(rset.getString(1));
 }
 stmt.close();
 */
