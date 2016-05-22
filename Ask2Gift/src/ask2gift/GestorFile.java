/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ask2gift;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author inaki
 */
public class GestorFile {

    public GestorFile(String cadena, String filePath) {

        FileOutputStream f0 = null;
        byte buffer[] = cadena.getBytes();

        try {
            f0 = new FileOutputStream(filePath);

            // Escritura de f1
            for (int i = 0; i < buffer.length; i += 2) {
                f0.write(buffer[i]);
            }

            // Escritura de f2
            //  f0.write(buffer);
        } catch (IOException e) {
            System.out.println("ERROR: Error de E/S");
        } finally {
            // Hay que cerrar los streams a mano
            try {
                if (f0 != null) {
                    f0.close();
                }
            } catch (IOException e) {
                System.out.println("ERROR: No se puede cerrar f0.txt");
            }

        }

    }
}
