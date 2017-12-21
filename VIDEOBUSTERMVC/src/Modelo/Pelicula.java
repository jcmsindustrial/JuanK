package Modelo;

public class Pelicula extends Categoria {
    
    private int codigo_pelicula;
    private int precio;
    private String formato4k;
    private String nombre;

    public Pelicula() {
    }

    public Pelicula(int id_categoria, String descripcion) {
        super(id_categoria, descripcion);
    }
    
    public Pelicula(int codigo_pelicula, int precio, String formato4k, String nombre) {
        this.codigo_pelicula = codigo_pelicula;
        this.precio = precio;
        this.formato4k = formato4k;
        this.nombre = nombre;
    }

    public Pelicula(int codigo_pelicula, int precio, String formato4k, String nombre, int id_categoria, String descripcion) {
        super(id_categoria, descripcion);
        this.codigo_pelicula = codigo_pelicula;
        this.precio = precio;
        this.formato4k = formato4k;
        this.nombre = nombre;
    }

    public int getCodigo_pelicula() {
        return codigo_pelicula;
    }

    public void setCodigo_pelicula(int codigo_pelicula) {
        this.codigo_pelicula = codigo_pelicula;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getFormato4k() {
        return formato4k;
    }

    public void setFormato4k(String formato4k) {
        this.formato4k = formato4k;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Pelicula{" + "codigo_pelicula=" + codigo_pelicula + ", precio=" + precio + ", formato4k=" + formato4k + ", nombre=" + nombre + '}';
    }    
}
