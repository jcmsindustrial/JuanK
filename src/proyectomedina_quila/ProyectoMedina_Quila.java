
package proyectomedina_quila;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Duoc UC
 */
public class ProyectoMedina_Quila {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (InstantiationException ex) {
            Logger.getLogger(ProyectoMedina_Quila.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ProyectoMedina_Quila.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ProyectoMedina_Quila.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
