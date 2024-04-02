import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Biblioteca{
	
	private List<Libro> libros;
	private List<Usuario> usuarios;
	Scanner scanner = new Scanner(System.in);
	
	public Biblioteca() {
		this.libros = new ArrayList<>();
		this.usuarios = new ArrayList<>();
	}
	
	public void registrarLibro() {
		String nombreLibro, autorLibro, generoLibro;
		int isbnLibro;
		Libro libro = new Libro();
				
		System.out.println("\nIngresar nombre del libro: ");
		nombreLibro = scanner.nextLine();	
		nombreLibro = nombreLibro.isEmpty() ? nombreLibro = "El Principito" : nombreLibro;
		libro.setTitulo(nombreLibro);
		
		System.out.println("\nIngresar autor del libro: ");
		autorLibro = scanner.nextLine();	
		autorLibro = autorLibro.isEmpty() ? autorLibro = "Antoine de Saint-Exupéry" : autorLibro;
		libro.setAutor(autorLibro);				
		
		System.out.println("\nIngresar genero del libro: ");
		generoLibro = scanner.nextLine();	
		generoLibro = generoLibro.isEmpty() ? generoLibro = "Fantasía" : generoLibro;
		libro.setGenero(generoLibro);
		
		System.out.println("\nIngresar ISBN del libro: ");
		isbnLibro = scanner.nextInt();	
		//isbnLibro = 123456;
		libro.setIsbn(isbnLibro);		
		
		//Libro libro = new Libro("El Principito", "Antoine de Saint-Exupéry", 123456, "Fantasía");
		agregarLibro(libro);
		System.out.println("Libro -> " + libro.toString() + " - Fue Registrado Exitosamente");
	}	
	
	public void agregarLibro(Libro libro) {
		libros.add(libro);
	}
	
	public void registrarUsuario() {
		String nombreUsuario;
		int nroID;
		Usuario usuario = new Usuario();
		
		System.out.println("\nIngresar nombre del usuario: ");
		nombreUsuario = scanner.nextLine();	
		nombreUsuario = nombreUsuario.isEmpty() ? nombreUsuario = "Juan Pérez" : nombreUsuario;
		usuario.setNombre(nombreUsuario);
		
		System.out.println("\nIngresar nroID del usuario: ");
		nroID = scanner.nextInt();	
		//nroID = 13131313;
		usuario.setNroID(nroID);
		
		//Usuario usuario = new Usuario("Juan Pérez", 123456789);
		agregarUsuario(usuario);
		System.out.println("Usuario -> " + usuario.toString() + " - Fue Registrado Exitosamente");
	}
	
	public void agregarUsuario(Usuario usuario) {
		usuarios.add(usuario);
	}
	
	public void listarLibros() {
		if(!libros.isEmpty()) {
			System.out.println("\nLIBROS REGISTRADOS: ");
			for(int i = 0; i < libros.size(); i++) {
				System.out.println(i + ") " + libros.get(i));
			}
		}
		else {
			System.out.println("No existen libros registrados");
		}
	}
	
	public void listarLibrosPrestados(Usuario usuario) {
		System.out.println("\nLIBROS ASIGNADOS: ");
		for(int i = 0; i < usuario.getLibrosPrestados().size(); i++) {
			System.out.println(i + ") " + usuario.getLibrosPrestados().get(i));
		}
	}
	
	public void listarUsuarios() {
		if(!usuarios.isEmpty()) {
			System.out.println("\nUSUARIOS REGISTRADOS: ");
			for(int i = 0; i < usuarios.size(); i++) {
				System.out.println(i + ") " + usuarios.get(i));
			}
		}
		else {
			System.out.println("No existen usuarios registrados");
		}
	}
	
	public void asignarLibro() {
		if(!libros.isEmpty()) {
			if(!usuarios.isEmpty()) {
				boolean disponibilidad = false;
				for(Libro libro : libros) {
					if(libro.isDisponible()) {
						disponibilidad = true;
						break;
					}
				}
				if(disponibilidad) {
					int indexLibro;	
					boolean isDisponible;
					do {
						isDisponible = true;
						listarLibros();
						System.out.println("\nIngresar indice del libro a asignar: ");
						indexLibro = scanner.nextInt();
												
						if(!(indexLibro < 0 || indexLibro >= libros.size())) {
							if(!libros.get(indexLibro).isDisponible()) {
								System.out.println("El libro no se encuentra disponible para asignar");
								isDisponible = false;
							}							
						}
						else {
							System.out.println("El indice se encuentra fuera de rango");
						}
					} while(indexLibro < 0 || indexLibro >= libros.size() || !isDisponible);
					
					int indexUsuario;
					do{
						listarUsuarios();
						System.out.println("\nIngresar indice del usuario a asignar: ");
						indexUsuario = scanner.nextInt();
						
						if(indexUsuario < 0 || indexUsuario >= usuarios.size()) {
							System.out.println("El indice se encuentra fuera de rango");
						}
						
					} while(indexUsuario < 0 || indexUsuario >= usuarios.size());
						
					usuarios.get(indexUsuario).agregarLibroPrestado(libros.get(indexLibro));
					System.out.println("El libro " + 
										libros.get(indexLibro).getTitulo() + 
										" fue asignado al usuario " +
										usuarios.get(indexUsuario).getNombre() 
										);
				}
				else {
					System.out.println("No existen libros disponibles para asignar");
				}
			}
			else {
				System.out.println("No existen usuarios registrados");
			}
		}
		else {
			System.out.println("No existen libros registrados");
		}
		
	}
	
	public void devolverLibro() {
		int indexUsuario;
		int indexLibro = 0;	
		do{
			listarUsuarios();
			System.out.println("\nIngresar indice del usuario que devuelve: ");
			indexUsuario = scanner.nextInt();
			
			if(indexUsuario < 0 || indexUsuario >= usuarios.size()) {
				System.out.println("El indice se encuentra fuera de rango");
			}
			
		} while(indexUsuario < 0 || indexUsuario >= usuarios.size());
		
		if(usuarios.get(indexUsuario).getLibrosPrestados().size() > 1) {
			do {				
				listarLibrosPrestados(usuarios.get(indexUsuario));
				System.out.println("ºnIngresar indice del libro a devolver: ");
				indexLibro = scanner.nextInt();
										
				if(indexLibro < 0 || indexLibro >= libros.size()) {
					System.out.println("El indice se encuentra fuera de rango");					
				}
			} while(indexLibro < 0 || indexLibro >= libros.size());
		}
		
		usuarios.get(indexUsuario).eliminarLibroPrestado(libros.get(indexLibro));
		System.out.println("El libro " + 
							libros.get(indexLibro).getTitulo() + 
							" fue devuelto por el usuario " +
							usuarios.get(indexUsuario).getNombre() 
							);
	}
	
	public void eliminarLibro() {
		if(!libros.isEmpty()) {
			boolean disponibilidad = false;
			for(Libro libro : libros) {
				if(libro.isDisponible()) {
					disponibilidad = true;
					break;
				}
			}
			if(disponibilidad) {
				int indexLibro;	
				boolean isDisponible;
				do {
					isDisponible = true;
					listarLibros();
					System.out.println("\nIngresar indice del libro a eliminar: ");
					indexLibro = scanner.nextInt();
											
					if(!(indexLibro < 0 || indexLibro >= libros.size())) {
						if(!libros.get(indexLibro).isDisponible()) {
							System.out.println("El libro no se encuentra disponible para eliminar");
							isDisponible = false;
						}							
					}
					else {
						System.out.println("El indice se encuentra fuera de rango");
					}
				} while(indexLibro < 0 || indexLibro >= libros.size() || !isDisponible);
								
				System.out.println("El libro " + 
						libros.get(indexLibro).getTitulo() + 
						" fue eliminado de la biblioteca");
				libros.remove(indexLibro);
			}
			else {
				System.out.println("No existen libros disponibles para eliminar");
			}
		}
		else {
			System.out.println("No existen libros registrados");
		}
	}
	
	public void eliminarUsuario() {
		if(!usuarios.isEmpty()) {
			int indexUsuario;
			do{
				listarUsuarios();
				System.out.println("\nIngresar indice del usuario a eliminar: ");
				indexUsuario = scanner.nextInt();
				
				if(indexUsuario < 0 || indexUsuario >= usuarios.size()) {
					System.out.println("El indice se encuentra fuera de rango");
				}
				
			} while(indexUsuario < 0 || indexUsuario >= usuarios.size());
			
			if(usuarios.get(indexUsuario).getLibrosPrestados().size() == 0) {
				System.out.println("El usuario " + 
						usuarios.get(indexUsuario).getNombre() + 
						" fue eliminado de los registros");
				usuarios.remove(indexUsuario);
			}
			else {
				System.out.println("El usuario " + 
						usuarios.get(indexUsuario).getNombre() + 
						" no puede ser eliminado. Posee libros a entregar.");
			}
			
		}
		else {
			System.out.println("No existen usuarios registrados");
		}

	}
	
	public void buscarLibro() {
		boolean volver = false;
		do {
        	System.out.println("\n------------- BÚSQUEDA DE LIBROS -------------");
        	System.out.println("1) Buscar por nombre de libro");
        	System.out.println("2) Buscar por autor de libro");
        	System.out.println("3) Buscar por genero de libro");        	
        	System.out.println("4) Buscar por isbn de libro");
        	System.out.println("\n0) Volver");
        	
        	int tipoBusqueda = scanner.nextInt();
        	
        	switch(tipoBusqueda) {
        	case 1:
        		buscarPorNombre();
        		break;
        	case 2:
        		buscarPorAutor();
        		break;
        	case 3:
        		buscarPorGenero();
        		break;
        	case 4:
        		buscarPorIsbn();
        		break;
        	case 0:
        		volver = true;
        		break;
        	}
		} while(!volver);
	}
	
	public void buscarPorNombre() {
		System.out.println("\n------------- BÚSQUEDA POR NOMBRE -------------");
    	System.out.println("Ingrese el nombre del libro que desea buscar:");
    	List<Libro> librosEncontrados = new ArrayList<>();
    	String nombreLibro = scanner.next();
    	for (Libro libro : libros) {
    		if(libro.getTitulo().contains(nombreLibro)) {
    			librosEncontrados.add(libro);
    		}    		
    	}
    	if (librosEncontrados.isEmpty()) {
    	    System.out.println("No se encontraron libros con el nombre \"" + nombreLibro + "\".");
    	} 
    	else {
    		imprimirBusqueda(librosEncontrados);
    	}
    	
    	if(librosEncontrados.size() == 1 && !usuarios.isEmpty()) {
    		System.out.println("\nDesea asignar este libro a un usuario? (1 = Si | 0 = No)");
    		int asignar = scanner.nextInt();
    		if(asignar == 1) {
    			asignarLibroEncontrado(librosEncontrados.get(0));
    		}
    		else {
    			return;
    		}
    		
    	}
	}
	
	public void buscarPorAutor() {
		System.out.println("\n------------- BÚSQUEDA POR AUTOR -------------");
    	System.out.println("Ingrese el nombre del autor que desea buscar:");
    	List<Libro> librosEncontrados = new ArrayList<>();
    	String autorLibro = scanner.next();
    	for (Libro libro : libros) {
    		if(libro.getAutor().contains(autorLibro)) {
    			librosEncontrados.add(libro);
    		}    		
    	}
    	if (librosEncontrados.isEmpty()) {
    	    System.out.println("No se encontraron libros con el autor \"" + autorLibro + "\".");
    	} 
    	else {
    		imprimirBusqueda(librosEncontrados);
    	}
    	
    	if(librosEncontrados.size() == 1 && !usuarios.isEmpty()) {
    		System.out.println("\nDesea asignar este libro a un usuario? (1 = Si | 0 = No)");
    		int asignar = scanner.nextInt();
    		if(asignar == 1) {
    			asignarLibroEncontrado(librosEncontrados.get(0));
    		}
    		else {
    			return;
    		}
    		
    	}
	}
	
	public void buscarPorGenero() {
		System.out.println("\n------------- BÚSQUEDA POR GENERO -------------");
    	System.out.println("Ingrese el género que desea buscar:");
    	List<Libro> librosEncontrados = new ArrayList<>();
    	String generoLibro = scanner.next();
    	for (Libro libro : libros) {
    		if(libro.getGenero().contains(generoLibro)) {
    			librosEncontrados.add(libro);
    		}    		
    	}
    	if (librosEncontrados.isEmpty()) {
    	    System.out.println("No se encontraron libros con el genero \"" + generoLibro + "\".");
    	} 
    	else {
    		imprimirBusqueda(librosEncontrados);
    	}
    	
    	if(librosEncontrados.size() == 1 && !usuarios.isEmpty()) {
    		System.out.println("\nDesea asignar este libro a un usuario? (1 = Si | 0 = No)");
    		int asignar = scanner.nextInt();
    		if(asignar == 1) {
    			asignarLibroEncontrado(librosEncontrados.get(0));
    		}
    		else {
    			return;
    		}
    		
    	}
	}
	
	public void buscarPorIsbn() {
		System.out.println("\n------------- BÚSQUEDA POR ISBN -------------");
    	System.out.println("Ingrese el isbn que desea buscar:");
    	List<Libro> librosEncontrados = new ArrayList<>();
    	int isbnLibro = scanner.nextInt();
    	for (Libro libro : libros) {
    		if(libro.getIsbn() == isbnLibro) {
    			librosEncontrados.add(libro);
    		}    		
    	}
    	if (librosEncontrados.isEmpty()) {
    	    System.out.println("No se encontraron libros con el isbn \"" + isbnLibro + "\".");
    	} 
    	else {
    		imprimirBusqueda(librosEncontrados);
    	}   
    	
    	if(librosEncontrados.size() == 1 && !usuarios.isEmpty()) {
    		System.out.println("\nDesea asignar este libro a un usuario? (1 = Si | 0 = No)");
    		int asignar = scanner.nextInt();
    		if(asignar == 1) {
    			asignarLibroEncontrado(librosEncontrados.get(0));
    		}
    		else {
    			return;
    		}
    		
    	}
	}
	
	public void asignarLibroEncontrado(Libro libroEncontrado) {
		if(!libros.isEmpty()) {
			if(!usuarios.isEmpty()) {			
				if(libroEncontrado.isDisponible()) {										
					int indexUsuario;
					do{
						listarUsuarios();
						System.out.println("\nIngresar indice del usuario a asignar: ");
						indexUsuario = scanner.nextInt();
						
						if(indexUsuario < 0 || indexUsuario >= usuarios.size()) {
							System.out.println("El indice se encuentra fuera de rango");
						}
						
					} while(indexUsuario < 0 || indexUsuario >= usuarios.size());
						
					usuarios.get(indexUsuario).agregarLibroPrestado(libroEncontrado);
					System.out.println("El libro " + 
										libroEncontrado.getTitulo() + 
										" fue asignado al usuario " +
										usuarios.get(indexUsuario).getNombre() 
										);
				}
				else {
					System.out.println("No existen libros disponibles para asignar");
				}
			}
			else {
				System.out.println("No existen usuarios registrados");
			}
		}
		else {
			System.out.println("No existen libros registrados");
		}
	}
	
	public void imprimirBusqueda(List<Libro> librosEncontrados) {
		System.out.println("ISBN   | Título                         | Autor          				| Disponible |");
        System.out.println("-------|--------------------------------|-----------------------------------------------|------------|");
		for(Libro libro : librosEncontrados) {
			 String disponible = libro.isDisponible() ? "Sí" : "No";
	         System.out.println(libro.getIsbn() + " | " + libro.getTitulo() + "\t\t\t|" + libro.getAutor() + "\t\t\t|" + disponible);
		}
	}
	
	public void imprimirEstado() {
		if(!libros.isEmpty()) {
			System.out.println("\n=============================================");
	        System.out.println("          Estado de Libros de la Biblioteca");
	        System.out.println("=============================================");
	        System.out.println("ISBN   | Título                         | Autor          				| Disponible |");
	        System.out.println("-------|--------------------------------|-----------------------------------------------|------------|");
	
	        for (Libro libro : libros) {
	            String disponible = libro.isDisponible() ? "Sí" : "No";
	            System.out.println(libro.getIsbn() + " | " + libro.getTitulo() + "\t\t\t|" + libro.getAutor() + "\t\t\t|" + disponible);
	        }
	        System.out.println("=============================================");
		}
		else {
			System.out.println("No existen registros de libros en biblioteca");
		}
	}
}