package Modelo;

import Conexion.Conexion;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class Registro_Pelicula{
    
    Conexion c;
    
    public Registro_Pelicula(){
        c= new Conexion();
    }
    
    public boolean agregarCategoriaPelicula(Pelicula peli) {
        try {
            String query = "INSERT INTO videobuster.categoria(id_categoria, descripcion) VALUES(?,?)";
            PreparedStatement ps = c.getConexion().prepareStatement(query);
            ps.setInt(1, peli.getId_categoria());
            ps.setString(2, peli.getDescripcion());
            
            if(ps.executeUpdate()>0){
                return true;
            }
            c.close(ps);
        } catch (Exception e) {
            System.out.println("Error al agregar " + e.getMessage());
        }
        return false;
    }
    
    public boolean agregarPelicula(Pelicula p) {
        try {
            String query = "INSERT INTO videobuster.pelicula(codigo_pelicula, precio, id_categoria, formato4k, nombre) VALUES(?,?,?,?,?)";
            PreparedStatement ins = c.getConexion().prepareStatement(query);
            ins.setInt(1, p.getCodigo_pelicula());
            ins.setInt(2, p.getPrecio());
            ins.setInt(3, p.getId_categoria());
            ins.setString(4, p.getFormato4k());
            ins.setString(5, p.getNombre());
            
            if(ins.executeUpdate()>0){
                return true;
            }
            c.close(ins);
        } catch (Exception e) {
            System.out.println("Error al agregar " + e.getMessage());
        }
        return false;
    }
    
    public boolean agregarPeliculaDrama() {
        try {
            String query = "INSERT INTO videobuster.pelicula(codigo_pelicula, precio, id_categoria, formato4k, nombre) VALUES(18000,2500,6,'S','ALberto Enamorado')";
            PreparedStatement ins = c.getConexion().prepareStatement(query);
            ins.executeUpdate();
            c.close(ins);
        } catch (Exception e) {
            System.out.println("Error al agregar " + e.getMessage());
        }
        return false;
    }
    
    public boolean agregarPeliculaComedia() {
        try {
            String query = "INSERT INTO videobuster.pelicula(codigo_pelicula, precio, id_categoria, formato4k, nombre) VALUES(21500,3990,2,'N','Pancho del Sur')";
            PreparedStatement ins = c.getConexion().prepareStatement(query);
            ins.executeUpdate();
            c.close(ins);
        } catch (Exception e) {
            System.out.println("Error al agregar " + e.getMessage());
        }
        return false;
    }
    
    public boolean eliminarPeliculaporCodigo(int codigo) {
        try {
            String query = "DELETE FROM videobuster.pelicula WHERE codigo_pelicula=?";
            PreparedStatement eli = c.getConexion().prepareStatement(query);
            eli.setInt(1, codigo);

            if (eli.executeUpdate() > 0) {
                return true;
            }
            c.close(eli);
        } catch (Exception e) {
            System.out.println("Error al eliminar " + e.getMessage());
        }
        return false;
    }
    
    public boolean eliminarPelicula2000() {
        try {
            String query = "DELETE FROM videobuster.pelicula WHERE precio > 2000";
            PreparedStatement eli = c.getConexion().prepareStatement(query);
            eli.executeUpdate();
            c.close(eli);
        } catch (Exception e) {
            System.out.println("Error al eliminar " + e.getMessage());
        }
        return false;
    }
    
    public boolean modificarPeliculaporCodigo(Pelicula peli) {
        try {
            String query = "UPDATE videobuster.pelicula SET precio=?, id_categoria=?, formato4k=?, nombre=? WHERE codigo_pelicula=?";
            PreparedStatement mod = c.getConexion().prepareStatement(query);
            mod.setInt(1, peli.getPrecio());
            mod.setInt(2, peli.getId_categoria());
            mod.setString(3, peli.getFormato4k());
            mod.setString(4, peli.getNombre());
            mod.setInt(5, peli.getCodigo_pelicula());

            if (mod.executeUpdate() > 0) {
                return true;
            }
            c.close(mod);
        } catch (Exception e) {
            System.out.println("Error al modificar " + e.getMessage());
        }
        return false;
    }
    
    public boolean modificarPeliculaconP() {
        try {
            String query = "UPDATE videobuster.pelicula SET nombre = concat('P',nombre);";
            PreparedStatement mod = c.getConexion().prepareStatement(query);
            mod.executeUpdate();
            c.close(mod);
        } catch (Exception e) {
            System.out.println("Error al modificar " + e.getMessage());
        }
        return false;
    }
    
    public Pelicula buscarPeliculaporCodigo(int codigo) {
        Pelicula peli = null;
        try {
            String query = "SELECT * FROM videobuster.pelicula WHERE codigo_pelicula=?";
            PreparedStatement buscar = c.getConexion().prepareStatement(query);
            buscar.setInt(1, codigo);
            //LLAMADO executeQuery
            ResultSet rs = buscar.executeQuery();
            while (rs.next()) {
                peli = new Pelicula();
                peli.setCodigo_pelicula(rs.getInt("codigo_pelicula"));
                peli.setPrecio(rs.getInt("precio"));
                peli.setId_categoria(rs.getInt("id_categoria"));
                peli.setFormato4k(rs.getString("formato4k"));
                peli.setNombre(rs.getString("nombre"));
            }
            c.close(buscar);
        } catch (Exception e) {
            System.out.println("Error la buscar por codigo " + e.getMessage());
        }
        return peli;
    }
    
    public DefaultTableModel Mostrar() throws ClassNotFoundException, SQLException {
        
        DefaultTableModel tablemodel = new DefaultTableModel();
        int registros = 0;
        String[] columNames = {"CODIGO", "PRECIO", "CATEGORIA", "¿ULTRA HD?", "NOMBRE"};
        
        try {
            PreparedStatement pstm = c.getConexion().prepareStatement("SELECT count(*) as total FROM videobuster.pelicula");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        Object[][] data = new String[registros][5];
        try {
            PreparedStatement pstm = c.getConexion().prepareStatement("select p.codigo_pelicula, p.precio, c.descripcion, p.formato4k, p.nombre from videobuster.pelicula as p LEFT join categoria as c ON p.Id_categoria=c.Id order by p.codigo_pelicula ASC");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while (res.next()) {
                data[i][0] = res.getString("codigo_pelicula");
                data[i][1] = res.getString("precio");
                data[i][2] = res.getString("c.descripcion");
                data[i][3] = res.getString("formato4k");
                data[i][4] = res.getString("nombre");
                i++;
            }
            res.close();
            tablemodel.setDataVector(data, columNames);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return tablemodel;
    }
    
    public DefaultTableModel MostrarRomance() throws ClassNotFoundException, SQLException {
        
        DefaultTableModel tablemodel = new DefaultTableModel();
        int registros = 0;
        String[] columNames = {"CODIGO", "PRECIO", "CATEGORIA", "¿ULTRA HD?", "NOMBRE"};
        
        try {
            PreparedStatement pstm = c.getConexion().prepareStatement("SELECT count(*) as total FROM videobuster.pelicula where id_categoria=4");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        Object[][] data = new String[registros][5];
        try {
            PreparedStatement pstm = c.getConexion().prepareStatement("select p.codigo_pelicula, p.precio, c.descripcion, p.formato4k, p.nombre from videobuster.pelicula as p LEFT join categoria as c ON p.Id_categoria=c.Id where p.id_categoria=4 order by p.codigo_pelicula ASC");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while (res.next()) {
                data[i][0] = res.getString("codigo_pelicula");
                data[i][1] = res.getString("precio");
                data[i][2] = res.getString("c.descripcion");
                data[i][3] = res.getString("formato4k");
                data[i][4] = res.getString("nombre");
                i++;
            }
            res.close();
            tablemodel.setDataVector(data, columNames);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return tablemodel;
    }
    
}
