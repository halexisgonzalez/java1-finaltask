import java.util.List;
import java.util.ArrayList;

public class Usuario{
	private String nombre;
	private int nroID;
	private List<Libro> librosPrestados;
	
	public Usuario(String nombre, int nroID) {
		this.nombre = nombre;
		this.nroID = nroID;
		this.librosPrestados = new ArrayList<>();
	}
	
	public Usuario() {
		this.librosPrestados = new ArrayList<>();
	};
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getNroID() {
		return nroID;
	}
	
	public void setNroID(int nroID) {
		this.nroID = nroID;
	}
	
	public List<Libro> getLibrosPrestados(){
		return librosPrestados;
	}
	
	public void agregarLibroPrestado(Libro libro) {
		if(librosPrestados != null) {
			librosPrestados.add(libro);
			if(libro.isDisponible()) {
				libro.setDisponible(false);
			}
		}
		else {
			System.out.println("ERROR: ES NULL ------------------------");
		}
				
	}
	
	public void eliminarLibroPrestado(Libro libro) {
		librosPrestados.remove(libro);
		if(!libro.isDisponible()) {
			libro.setDisponible(true);
		}	
	}
	
	 public String toString() {
        return "Usuario{" +
                "nombre='" + nombre +
                ", nroID=" + nroID +
                ", \nlibrosPrestados=" + librosPrestados +
                '}';
    }
}