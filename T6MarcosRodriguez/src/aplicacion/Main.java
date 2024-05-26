package aplicacion;
import java.util.Scanner;
import persistencia.*;
import presentacion.*;
import dominio.*;


/** 	Clase main del proyecto. <br>
 *  	Pertenece al paquete aplicacion: <br>
 *  
 *  	Punto inicial del programa <br>
 *  	aqui se cargan los datos iniciales {@link Persistencia} <br>
 *  	se inicializan las listas de incidencias {@link ListaIncidencias} <br>
 *  	Se van mostrando los metodos graficos {@link InterfazUsuario } <br>
 *  	y ejecutando las respectivas acciones {@link Logica} <br>
 *  		
 * 		@author Marcos Rodriguez
 */


public class Main {

	
	public static void main(String[] args) {
		
	IncidenciaDAO dao = new IncidenciaDAO();
	
		
	int opcion;	
	
	do {
		Scanner sc = new Scanner(System.in);
			
		InterfazUsuario.imprimirMenu();
		opcion = sc.nextInt();
		Logica.opcionesDelPrograma(opcion, dao);
		
	} while (opcion != 12);
		
		
	
		
	

		
	}
	
}
