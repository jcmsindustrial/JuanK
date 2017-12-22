package Vista;

public class Menu_Principal extends javax.swing.JFrame {

    public Menu_Principal() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_vistaagregar = new javax.swing.JButton();
        btn_vistaeliminar = new javax.swing.JButton();
        btn_vistamodificar = new javax.swing.JButton();
        btn_vistalistar = new javax.swing.JButton();
        btn_romance = new javax.swing.JButton();
        MenuPrincipal = new javax.swing.JMenuBar();
        menu_opciones = new javax.swing.JMenu();
        menu_agregar = new javax.swing.JMenuItem();
        menu_eliminar = new javax.swing.JMenuItem();
        menu_modificar = new javax.swing.JMenuItem();
        menu_salir = new javax.swing.JMenuItem();
        menu_catalogo = new javax.swing.JMenu();
        menu_listar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        btn_vistaagregar.setText("AGREGAR");

        btn_vistaeliminar.setText("ELIMINAR");

        btn_vistamodificar.setText("MODIFICAR");

        btn_vistalistar.setText("LISTAR");

        btn_romance.setText("LISTAR ROMANCE");

        menu_opciones.setText("OPCIONES");

        menu_agregar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        menu_agregar.setText("Agregar Película");
        menu_opciones.add(menu_agregar);

        menu_eliminar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        menu_eliminar.setText("Eliminar Película");
        menu_opciones.add(menu_eliminar);

        menu_modificar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        menu_modificar.setText("Modificar Película");
        menu_opciones.add(menu_modificar);

        menu_salir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        menu_salir.setText("Salir");
        menu_opciones.add(menu_salir);

        MenuPrincipal.add(menu_opciones);

        menu_catalogo.setText("CATÁLOGO");

        menu_listar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        menu_listar.setText("Listar Catálogo");
        menu_catalogo.add(menu_listar);

        MenuPrincipal.add(menu_catalogo);

        setJMenuBar(MenuPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(189, 189, 189)
                .addComponent(btn_romance)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_vistaagregar, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btn_vistalistar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(btn_vistamodificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(btn_vistaeliminar)
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(63, Short.MAX_VALUE)
                .addComponent(btn_romance)
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_vistaagregar)
                    .addComponent(btn_vistalistar)
                    .addComponent(btn_vistamodificar)
                    .addComponent(btn_vistaeliminar))
                .addGap(69, 69, 69))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu_Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JMenuBar MenuPrincipal;
    public javax.swing.JButton btn_romance;
    public javax.swing.JButton btn_vistaagregar;
    public javax.swing.JButton btn_vistaeliminar;
    public javax.swing.JButton btn_vistalistar;
    public javax.swing.JButton btn_vistamodificar;
    public javax.swing.JMenuItem menu_agregar;
    public javax.swing.JMenu menu_catalogo;
    public javax.swing.JMenuItem menu_eliminar;
    public javax.swing.JMenuItem menu_listar;
    public javax.swing.JMenuItem menu_modificar;
    public javax.swing.JMenu menu_opciones;
    public javax.swing.JMenuItem menu_salir;
    // End of variables declaration//GEN-END:variables
}
