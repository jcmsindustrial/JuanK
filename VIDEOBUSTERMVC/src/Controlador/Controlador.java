package Controlador;


import Modelo.*;
import Vista.*;
import java.awt.Color;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Controlador implements ActionListener, MouseListener, WindowListener, FocusListener{
    
    private final Menu_Principal menu;
    private final Eliminar eli;
    private final Modificar modi;
    private final ListarPeliculas listar;
    private final Agregar agre;
    
    Pelicula peli;
    Registro_Pelicula registro;
    
    public static void main (String [] args){
        Controlador c= new Controlador();
    }
    
    public Controlador(){
        
        registro = new Registro_Pelicula();

        //Seteo de la ventana Menu Principal
        menu = new Menu_Principal();
        menu.setLocationRelativeTo(null);
        menu.setResizable(false);
        menu.setTitle("VIDEOBUSTER, lo mejor en peliculas");
        menu.setBackground(Color.WHITE);
        
        //Ventana de inicio la hago visible
        menu.setVisible(true); 
        
        //Seteo de la ventana Eliminar
        eli = new Eliminar();
        eli.setLocationRelativeTo(null);
        eli.setResizable(false);
        eli.setTitle("VIDEOBUSTER ---> ELIMINAR");
        //Validación de campos en ventana Eliminar
        validarSoloNumeros(eli.txt_codigoeliminar);
        validarSoloNumeros(eli.txt_precioeliminar);
        LimitarCaracteres(eli.txt_codigoeliminar, 5);
        LimitarCaracteres(eli.txt_precioeliminar, 6);
       
        
        //Seteo de la ventana Modificar
        modi = new Modificar();
        modi.setLocationRelativeTo(null);
        modi.setResizable(false);
        modi.setTitle("VIDEOBUSTER ---> MODIFICAR");
        //Validación de campos en ventana Modificar
        validarSoloNumeros(modi.txt_codigomodificar);
        validarSoloNumeros(modi.txt_preciomodificar);
        LimitarCaracteres(modi.txt_codigomodificar, 5);
        LimitarCaracteres(modi.txt_preciomodificar, 6);
        
        //Seteo de la ventana Listar
        listar = new ListarPeliculas();
        listar.setLocationRelativeTo(null);
        listar.setResizable(false);
        listar.setTitle("VIDEOBUSTER ---> CATÁLOGO");
        actualizarMostrar();
        
        //Seteo de la ventana Agregar
        agre = new Agregar();
        agre.setLocationRelativeTo(null);
        agre.setResizable(false);
        agre.setTitle("VIDEOBUSTER ---> AGREGAR");
        //Validación de campos en ventana Agregar
        validarSoloNumeros(agre.txt_codigo);
        validarSoloNumeros(agre.txt_precio);
        LimitarCaracteres(agre.txt_codigo, 5);
        LimitarCaracteres(agre.txt_precio, 6);
        
        //Escuchas de las ventanas (Eventos de Ventana)
        menu.addWindowListener(this);
        eli.addWindowListener(this);
        modi.addWindowListener(this);
        listar.addWindowListener(this);
        listar.addFocusListener(this);//-->Evento de Foco
        listar.addMouseListener(this);//-->Evento de Mouse
        agre.addWindowListener(this);
        
        //Escuchas de la vista principal (de los menús) (Eventos de Acción)
        menu.menu_agregar.addActionListener(this);
        menu.menu_eliminar.addActionListener(this);
        menu.menu_modificar.addActionListener(this);
        menu.menu_salir.addActionListener(this);
        menu.menu_listar.addActionListener(this);
        menu.btn_vistaagregar.addActionListener(this);
        menu.btn_vistaeliminar.addActionListener(this);
        menu.btn_vistamodificar.addActionListener(this);
        menu.btn_vistalistar.addActionListener(this);
        
        //Escuchas de la vista eliminar (de los menús)(Eventos de Acción)
        eli.menu_agregar.addActionListener(this);
        eli.menu_eliminar.addActionListener(this);
        eli.menu_modificar.addActionListener(this);
        eli.menu_salir.addActionListener(this);
        eli.menu_listar.addActionListener(this);
        eli.btn_buscarcodigoeliminar.addActionListener(this);
        eli.btn_eliminar.addActionListener(this);
        eli.btn_volver_eliminar.addActionListener(this);
        
        //Escuchas de la vista Modificar (de los menús)(Eventos de Acción)
        modi.menu_agregar.addActionListener(this);
        modi.menu_eliminar.addActionListener(this);
        modi.menu_modificar.addActionListener(this);
        modi.menu_salir.addActionListener(this);
        modi.menu_listar.addActionListener(this);
        modi.btn_buscarcodigomodificar.addActionListener(this);
        modi.btn_modificar.addActionListener(this);
        modi.btn_volver_modificar.addActionListener(this);
        
        //Escuchas de la vista Listar (de los menús)(Eventos de Acción)
        listar.menu_agregar.addActionListener(this);
        listar.menu_eliminar.addActionListener(this);
        listar.menu_modificar.addActionListener(this);
        listar.menu_salir.addActionListener(this);
        listar.menu_listar.addActionListener(this);
        listar.btn_volver_listar.addActionListener(this);
        listar.tbl_mostrar.addMouseListener(this);//-->Evento de Mouse
        listar.tbl_mostrar.addFocusListener(this);//-->Evento de Foco
        listar.btn_agregar_categoria_drama.addActionListener(this);
        listar.btn_agregar_categoria_comedia.addActionListener(this);
        
        //Escuchas de la vista Agregar (de los menús)
        agre.menu_agregar.addActionListener(this);
        agre.menu_eliminar.addActionListener(this);
        agre.menu_modificar.addActionListener(this);
        agre.menu_salir.addActionListener(this);
        agre.menu_listar.addActionListener(this);
        agre.btn_agregar.addActionListener(this);
        agre.btn_volver_agregar.addActionListener(this);
       
    }
    
       
    @Override
    public void actionPerformed(ActionEvent e){
        //Eventos de los nuevos botones
        if(e.getSource()==listar.btn_agregar_categoria_drama){
            registro.agregarPeliculaDrama();
        }
        if(e.getSource()==listar.btn_agregar_categoria_comedia){
            registro.agregarPeliculaComedia();
        }
        //Action Event de Ventana Menu Principal (eventos de los menús)
        if(e.getSource()==menu.menu_agregar){
            menu.setVisible(false);
            agre.setVisible(true);
        }else if(e.getSource()==menu.menu_eliminar){
            menu.setVisible(false);
            eli.setVisible(true);
        }else if(e.getSource()==menu.menu_modificar){
            menu.setVisible(false);
            modi.setVisible(true);
        }else if(e.getSource()==menu.menu_salir){
            int msje=JOptionPane.showConfirmDialog(menu,"¿DESEA SALIR DE LA APLICACIÓN?", "SALIR", JOptionPane.YES_NO_OPTION);
            if(msje==JOptionPane.YES_OPTION){
            System.exit(0);
            }
        }else if(e.getSource()==menu.menu_listar){
            menu.setVisible(false);
            listar.setVisible(true);
        }
        
        //Action Event de Ventana Eliminar
        if(e.getSource()==eli.menu_agregar){
            eli.setVisible(false);
            agre.setVisible(true);
        }else if(e.getSource()==eli.menu_eliminar){
            
        }else if(e.getSource()==eli.menu_modificar){
            eli.setVisible(false);
            modi.setVisible(true);
        }else if(e.getSource()==eli.menu_salir){
            int msje=JOptionPane.showConfirmDialog(menu,"¿DESEA SALIR DE LA APLICACIÓN?", "SALIR", JOptionPane.YES_NO_OPTION);
            if(msje==JOptionPane.YES_OPTION){
            System.exit(0);
            }
        }else if(e.getSource()==eli.menu_listar){
            eli.setVisible(false);
            listar.setVisible(true);
        }
        
        //Action Event de Ventana Modificar
        if(e.getSource()==modi.menu_agregar){
            modi.setVisible(false);
            agre.setVisible(true);
        }else if(e.getSource()==modi.menu_eliminar){
            modi.setVisible(false);
            eli.setVisible(true);
        }else if(e.getSource()==modi.menu_modificar){
            
        }else if(e.getSource()==modi.menu_salir){
            int msje=JOptionPane.showConfirmDialog(menu,"¿DESEA SALIR DE LA APLICACIÓN?", "SALIR", JOptionPane.YES_NO_OPTION);
            if(msje==JOptionPane.YES_OPTION){
            System.exit(0);
            }
        }else if(e.getSource()==modi.menu_listar){
            modi.setVisible(false);
            listar.setVisible(true);
        }
        
        //Action Event de Ventana Listar
        if(e.getSource()==listar.menu_agregar){
            listar.setVisible(false);
            agre.setVisible(true);
        }else if(e.getSource()==listar.menu_eliminar){
            listar.setVisible(false);
            eli.setVisible(true);
        }else if(e.getSource()==listar.menu_modificar){
            listar.setVisible(false);
            modi.setVisible(true);
        }else if(e.getSource()==listar.menu_salir){
            int msje=JOptionPane.showConfirmDialog(menu,"¿DESEA SALIR DE LA APLICACIÓN?", "SALIR", JOptionPane.YES_NO_OPTION);
            if(msje==JOptionPane.YES_OPTION){
            System.exit(0);
            }
        }else if(e.getSource()==listar.menu_listar){
            
        }
        
        //Action Event de Ventana Agregar
        if(e.getSource()==agre.menu_agregar){
            
        }else if(e.getSource()==agre.menu_eliminar){
            agre.setVisible(false);
            eli.setVisible(true);
        }else if(e.getSource()==agre.menu_modificar){
            agre.setVisible(false);
            modi.setVisible(true);
        }else if(e.getSource()==agre.menu_salir){
            int msje=JOptionPane.showConfirmDialog(menu,"¿DESEA SALIR DE LA APLICACIÓN?", "SALIR", JOptionPane.YES_NO_OPTION);
            if(msje==JOptionPane.YES_OPTION){
            System.exit(0);
            }
        }else if(e.getSource()==agre.menu_listar){
            agre.setVisible(false);
            listar.setVisible(true);
        }
        
        //Action Event de los botones del Menu Principal
        if(e.getSource()==menu.btn_vistaagregar){
            menu.setVisible(false);
            agre.setVisible(true);
        }else if(e.getSource()==menu.btn_vistaeliminar){
            menu.setVisible(false);
            eli.setVisible(true);
        }else if(e.getSource()==menu.btn_vistamodificar){
            menu.setVisible(false);
            modi.setVisible(true);
        }else if(e.getSource()==menu.btn_vistalistar){
            menu.setVisible(false);
            listar.setVisible(true);
        }
        
        //Action Event de los botones volver de todas las ventanas
        if(e.getSource()==eli.btn_volver_eliminar){
            menu.setVisible(true);
            eli.setVisible(false);
            limpiarcamposeliminar();
        }else if(e.getSource()==modi.btn_volver_modificar){
            menu.setVisible(true);
            modi.setVisible(false);
            limpiarcamposmodificar();
        }else if(e.getSource()==listar.btn_volver_listar){
            menu.setVisible(true);
            listar.setVisible(false);
        }else if(e.getSource()==agre.btn_volver_agregar){
            menu.setVisible(true);
            agre.setVisible(false);
            limpiarcamposagregar();
        }
        
        //Action Event del botón buscar código para eliminar
        if(e.getSource()==eli.btn_buscarcodigoeliminar){
            try {
                System.out.println("Buscar codigo a eliminar");
                int codigo =Integer.parseInt(eli.txt_codigoeliminar.getText().trim());

                Pelicula peli = registro.buscarPeliculaporCodigo(codigo);

                if(peli==null){
                    JOptionPane.showMessageDialog(eli, "NO HAY REGISTROS PARA EL CODIGO "+codigo, "ERROR", JOptionPane.WARNING_MESSAGE);
                //limpiarPestañabuscar();
                }else{
                
                    eli.txt_precioeliminar.setText(String.valueOf(peli.getPrecio()));
                    eli.cb_categoriaeliminar.setSelectedItem(String.valueOf(peli.getId_categoria()));
                    eli.cb_4keliminar.setSelectedItem(peli.getFormato4k());
                    eli.txt_nombreeliminar.setText(peli.getNombre());
                
                }  
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(modi, "DEBE INGRESAR EL CÓDIGO A ELIMINAR", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        //Action Event del botón eliminar
        if(e.getSource()==eli.btn_eliminar){
            try {
                System.out.println("Eliminar");
                int codigo = Integer.parseInt(eli.txt_codigoeliminar.getText());
        
                if(registro.eliminarPeliculaporCodigo(codigo)){
                    JOptionPane.showMessageDialog(eli, "REGISTRO ELIMINADO EXITOSAMENTE", "ELIMINAR", JOptionPane.INFORMATION_MESSAGE);
                }else if (registro.buscarPeliculaporCodigo(codigo) == null){
                    JOptionPane.showMessageDialog(eli, "CODIGO " + codigo + " NO EXISTE", "ERROR", JOptionPane.ERROR_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(eli, "NO SE HA PODIDO ELIMINAR", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(eli, "NO HAY NADA SELECCIONADO PARA ELIMINAR", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            limpiarcamposeliminar();
        }
        
        //Action Event del botón buscar código para modificar
        if(e.getSource()==modi.btn_buscarcodigomodificar){  
            try {
                System.out.println("Buscar codigo a modificar");
                int codigo =Integer.parseInt(modi.txt_codigomodificar.getText().trim());

                Pelicula peli = registro.buscarPeliculaporCodigo(codigo);

                if(peli==null){
                    JOptionPane.showMessageDialog(modi, "NO HAY REGISTROS PARA EL CODIGO "+codigo, "ERROR", JOptionPane.WARNING_MESSAGE);
                limpiarcamposmodificar();
                }else{
                
                    modi.txt_preciomodificar.setText(String.valueOf(peli.getPrecio()));
                    modi.cb_categoriamodificar.setSelectedItem(String.valueOf(peli.getId_categoria()));
                    modi.cb_4kmodificar.setSelectedItem(peli.getFormato4k());
                    modi.txt_nombremodificar.setText(peli.getNombre());
                
                }  
            } catch (Exception ex) {
                    JOptionPane.showMessageDialog(modi, "DEBE INGRESAR EL CÓDIGO A MODIFICAR", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        //Action Event del botón Modificar
        if(e.getSource()==modi.btn_modificar){
            if(modi.txt_codigomodificar.getText().length()==0){
                JOptionPane.showMessageDialog(modi, "CÓDIGO no puede quedar vacío", "ERROR", JOptionPane.WARNING_MESSAGE);
            }else if(Integer.parseInt(modi.txt_codigomodificar.getText().trim())<10000){
                JOptionPane.showMessageDialog(modi, "CÓDIGO debe ser IGUAL O MAYOR a 10000", "ERROR", JOptionPane.WARNING_MESSAGE);
            }else if(Integer.parseInt(modi.txt_codigomodificar.getText().trim())>99999){
                JOptionPane.showMessageDialog(modi, "CÓDIGO debe ser IGUAL O MENOR a 99999", "ERROR", JOptionPane.WARNING_MESSAGE);
            }else if(modi.txt_nombremodificar.getText().length()==0){
                JOptionPane.showMessageDialog(modi, "NOMBRE no puede quedar vacío", "ERROR", JOptionPane.WARNING_MESSAGE);
            }else if(modi.txt_nombremodificar.getText().length()<3){
                JOptionPane.showMessageDialog(modi, "NOMBRE no puede tener menos de 3 letras", "ERROR", JOptionPane.WARNING_MESSAGE);
            }else if(modi.txt_preciomodificar.getText().length()==0){
                JOptionPane.showMessageDialog(modi, "PRECIO no puede quedar vacío", "ERROR", JOptionPane.WARNING_MESSAGE);
            }else if(Integer.parseInt(modi.txt_preciomodificar.getText().trim())<=1000){
                JOptionPane.showMessageDialog(modi, "PRECIO debe ser MAYOR a $1.000.-", "ERROR", JOptionPane.WARNING_MESSAGE);
            }else if(modi.cb_categoriamodificar.getSelectedIndex() == 0){
                JOptionPane.showMessageDialog(modi, "CATEGORÍA no puede ir vacía", "ERROR", JOptionPane.WARNING_MESSAGE);
            }else if(modi.cb_4kmodificar.getSelectedIndex() == 0){
                JOptionPane.showMessageDialog(modi, "Debe especificar si pelicula POSEE O NO 4K. (S= Sí / N=No)", "ERROR", JOptionPane.WARNING_MESSAGE);
            }else {
                try {
                    System.out.println("modificar");
                    Pelicula peli = new Pelicula();
                    peli.setCodigo_pelicula(Integer.parseInt(modi.txt_codigomodificar.getText().trim()));
                    peli.setPrecio(Integer.parseInt(modi.txt_preciomodificar.getText().trim()));
                    peli.setId_categoria(Integer.parseInt(modi.cb_categoriamodificar.getSelectedItem().toString().trim()));
                    peli.setFormato4k(modi.cb_4kmodificar.getSelectedItem().toString());
                    peli.setNombre(modi.txt_nombremodificar.getText().trim());

                    if(registro.modificarPeliculaporCodigo(peli)){
                        JOptionPane.showMessageDialog(modi, "REGISTRO MODIFICADO", "AGREGAR PELICULA", JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(modi, "NO SE PUDO MODIFICAR", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(modi, "AÚN NO SE HA MODIFICADO NADA", "Error", JOptionPane.ERROR_MESSAGE);
                }
                limpiarcamposmodificar();
            }
        }
        
        //Action Event del botón Agregar
        if(e.getSource()==agre.btn_agregar){
            if(agre.txt_codigo.getText().length()==0){
                JOptionPane.showMessageDialog(agre, "CÓDIGO no puede quedar vacío", "ERROR", JOptionPane.WARNING_MESSAGE);
            }else if(Integer.parseInt(agre.txt_codigo.getText().trim())<10000){
                JOptionPane.showMessageDialog(agre, "CÓDIGO debe ser IGUAL O MAYOR a 10000", "ERROR", JOptionPane.WARNING_MESSAGE);
            }else if(Integer.parseInt(agre.txt_codigo.getText().trim())>99999){
                JOptionPane.showMessageDialog(agre, "CÓDIGO debe ser IGUAL O MENOR a 99999", "ERROR", JOptionPane.WARNING_MESSAGE);
            }else if(agre.txt_nombre.getText().length()==0){
                JOptionPane.showMessageDialog(agre, "NOMBRE no puede quedar vacío", "ERROR", JOptionPane.WARNING_MESSAGE);
            }else if(agre.txt_nombre.getText().length()<3){
                JOptionPane.showMessageDialog(agre, "NOMBRE no puede tener menos de 3 letras", "ERROR", JOptionPane.WARNING_MESSAGE);
            }else if(agre.txt_precio.getText().length()==0){
                JOptionPane.showMessageDialog(agre, "PRECIO no puede quedar vacío", "ERROR", JOptionPane.WARNING_MESSAGE);
            }else if(Integer.parseInt(agre.txt_precio.getText().trim())<=1000){
                JOptionPane.showMessageDialog(agre, "PRECIO debe ser MAYOR a $1.000.-", "ERROR", JOptionPane.WARNING_MESSAGE);
            }else if(agre.cb_categoria.getSelectedIndex() == 0){
                JOptionPane.showMessageDialog(agre, "CATEGORÍA no puede ir vacía", "ERROR", JOptionPane.WARNING_MESSAGE);
            }else if(agre.cb_4k.getSelectedIndex() == 0){
                JOptionPane.showMessageDialog(agre, "Debe especificar si pelicula POSEE O NO 4K. (S= Sí / N=No)", "ERROR", JOptionPane.WARNING_MESSAGE);
            }else{
                try {
                    System.out.println("agregar");
                    peli = new Pelicula();
                    peli.setCodigo_pelicula(Integer.parseInt(agre.txt_codigo.getText().trim()));
                    peli.setPrecio(Integer.parseInt(agre.txt_precio.getText().trim()));
                    peli.setId_categoria(Integer.parseInt(agre.cb_categoria.getSelectedItem().toString().trim()));
                    peli.setFormato4k(agre.cb_4k.getSelectedItem().toString().trim());
                    peli.setNombre(agre.txt_nombre.getText().trim());
                    if(registro.agregarPelicula(peli)){
                        JOptionPane.showMessageDialog(agre, "REGISTRO AGREGADO EXITOSAMENTE", "AGREGAR PELICULA", JOptionPane.INFORMATION_MESSAGE);
                    }else if(registro.agregarPelicula(peli) == registro.agregarPelicula(peli)){
                        JOptionPane.showMessageDialog(agre, "CODIGO " + (Integer.parseInt(agre.txt_codigo.getText().trim())) + " YA HA SIDO INGRESADO, PRUEBE CON OTRO", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(agre, "NO SE PUDO AGREGAR EL PRODUCTO", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(agre, "AUN FALTAN DATOS POR INGRESAR", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                limpiarcamposagregar();    
            }
        }
        
    }

    @Override
    public void mouseClicked(MouseEvent me) {    
    }

    @Override
    public void mousePressed(MouseEvent me) {        
    }

    @Override
    public void mouseReleased(MouseEvent me) {       
    }

    @Override//Mouse Event para ACTUALIZAR y mostrar datos de películas actualizadas
    public void mouseEntered(MouseEvent me) {
        if(me.getSource()==listar.tbl_mostrar){
            actualizarMostrar();
        }  
    }

    @Override
    public void mouseExited(MouseEvent me) {       
    }

    @Override
    public void windowOpened(WindowEvent we) {       
    }

    @Override//Eventos de Ventana para preguntar por el cierre de todas las ventanas disponibles
    public void windowClosing(WindowEvent we) {
        if(we.getSource()== menu){
            int msje=JOptionPane.showConfirmDialog(menu,"¿DESEA SALIR DE LA APLICACIÓN?", "SALIR", JOptionPane.YES_NO_OPTION);
            if(msje==JOptionPane.YES_OPTION){
            System.exit(0);
            }
        }
        if(we.getSource()== agre){
            int msje=JOptionPane.showConfirmDialog(menu,"¿DESEA SALIR DE LA APLICACIÓN?", "SALIR", JOptionPane.YES_NO_OPTION);
            if(msje==JOptionPane.YES_OPTION){
            System.exit(0);
            }
        }
        if(we.getSource()== modi){
            int msje=JOptionPane.showConfirmDialog(menu,"¿DESEA SALIR DE LA APLICACIÓN?", "SALIR", JOptionPane.YES_NO_OPTION);
            if(msje==JOptionPane.YES_OPTION){
            System.exit(0);
            }
        }
        if(we.getSource()== eli){
            int msje=JOptionPane.showConfirmDialog(menu,"¿DESEA SALIR DE LA APLICACIÓN?", "SALIR", JOptionPane.YES_NO_OPTION);
            if(msje==JOptionPane.YES_OPTION){
            System.exit(0);
            }
        }
        if(we.getSource()== listar){
            int msje=JOptionPane.showConfirmDialog(menu,"¿DESEA SALIR DE LA APLICACIÓN?", "SALIR", JOptionPane.YES_NO_OPTION);
            if(msje==JOptionPane.YES_OPTION){
            System.exit(0);
            }
        }
    }
    
    @Override
    public void windowClosed(WindowEvent we) {    
    }

    @Override
    public void windowIconified(WindowEvent we) {   
    }

    @Override
    public void windowDeiconified(WindowEvent we) { 
    }

    @Override
    public void windowActivated(WindowEvent we) {    
    }

    @Override
    public void windowDeactivated(WindowEvent we) {
    }

    @Override
    public void focusGained(FocusEvent fe) {
    }

    @Override
    public void focusLost(FocusEvent fe) {
    }
    
    //Métodos Para limpiar Pestañas
    private void limpiarcamposagregar(){
        agre.txt_codigo.setText("");
        agre.txt_precio.setText("");
        agre.cb_categoria.setSelectedIndex(0);
        agre.cb_4k.setSelectedIndex(0);
        agre.txt_nombre.setText("");
    }
    
    private void limpiarcamposeliminar(){
        eli.txt_codigoeliminar.setText("");
        eli.txt_precioeliminar.setText("");
        eli.cb_categoriaeliminar.setSelectedIndex(0);
        eli.cb_4keliminar.setSelectedIndex(0);
        eli.txt_nombreeliminar.setText("");
    }
    
    private void limpiarcamposmodificar(){
        modi.txt_codigomodificar.setText("");
        modi.txt_preciomodificar.setText("");
        modi.cb_categoriamodificar.setSelectedIndex(0);
        modi.cb_4kmodificar.setSelectedIndex(0);
        modi.txt_nombremodificar.setText("");
    }
    
    //Método para actualizar datos en jTable
    public void actualizarMostrar() {
        try {
            listar.getTbl_mostrar().setModel(registro.Mostrar());
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    //MÉTODO PARA INGRESAR SÓLO LETRAS Y QUE SE BLOQUEEN LOS NÚMEROS
    public void validarSoloLetras(JTextField campo){
        campo.addKeyListener(new KeyAdapter() {
            
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                int k = (int)e.getKeyChar();
                if(Character.isDigit(c) || k=='/' || k=='*' || k=='-' || k=='+'){
                    e.consume();
                }
            }
        });
    }
    
    //MÉTODO PARA INGRESAR SÓLO NÚMEROS Y QUE SE BLOQUEEN LAS LETRAS
    public void validarSoloNumeros(JTextField campo){
        campo.addKeyListener(new KeyAdapter() {
            
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                if(!Character.isDigit(c)){
                    e.consume();
                }
            }
        });
    }
    
    //CON ESTE METODO LIMITO LA CANTIDAD DE CARACTERES A UN JTEXTFIELD PARA NO EXCEDERME Y GENERAR PROBLEMAS CON LA BBDD
    public void LimitarCaracteres(JTextField campo, int cantidad){
        campo.addKeyListener(new KeyAdapter() {
            
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                int tam = campo.getText().length();
                if(tam>=cantidad){
                    e.consume();
                }
            }
        });
    }
      
}
