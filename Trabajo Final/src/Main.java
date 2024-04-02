import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
    	
    	boolean salir = false;
    	boolean volver;
    	Scanner scanner = new Scanner(System.in);
    	
    	Biblioteca inventario = new Biblioteca();
    	
        do {
        	System.out.println("\n------------- BIBLIOTECA REGISTROS -------------");
        	System.out.println("1) Registrar Libro");
        	System.out.println("2) Registrar Usuario");
        	System.out.println("3) Buscar Libro");        	
        	System.out.println("4) Asignar Libro a Usuario");
        	System.out.println("5) Devolución de Libro");
        	
        	System.out.println("\n8) Configuraciones Extras");
        	System.out.println("9) Imprimir Estado Completo Biblioteca");
        	System.out.println("0) Salir");
        	
        	int opcion = scanner.nextInt();
        	volver = false;
        	
        	switch(opcion) {
        	case 1: 
        		inventario.registrarLibro();
        		break;
        	case 2: 
        		inventario.registrarUsuario();
        		break;
        	case 3: 
        		inventario.buscarLibro();
        		break;
        	case 4:                
        		inventario.asignarLibro();
        		break;
        	case 5:
        		inventario.devolverLibro();
        		break;        		
        	case 8:
        		do {
                	System.out.println("\n------------- COFIGURACIONES EXTRAS -------------");
                	System.out.println("1) Lista de Libros");
                	System.out.println("2) Lista de Usuarios");
                	System.out.println("3) Eliminar Libro");
                	System.out.println("4) Eliminar Usuario");
                	
                	System.out.println("\n0) Volver");
                	
                	int config = scanner.nextInt();
                	
                	switch(config) {
                	case 1:
                		inventario.listarLibros();
                		break;
	            	case 2:
	            		inventario.listarUsuarios();
	            		break;
	            	case 3:
	            		inventario.eliminarLibro();
	            		break;
	            	case 4:
	            		inventario.eliminarUsuario();
	            		break;
	            	case 0:
	            		volver = true;
	            		break;
                	}
        		} while(!volver);
        		
        		break;
        	case 9: 
        		inventario.imprimirEstado(); 
        		break;
        	case 0: 
        		salir = true; 
        		break;
        	default: System.out.println("Opción inválida\n");
        	}
        	
        } while(!salir);
    }
}