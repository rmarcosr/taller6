package presentacion;

import java.util.Scanner;



/** 	Clase de interfaz del proyecto. <br>
 *  	Pertenece al paquete presentencion: <br>
 *  
 *  	Desde aqui se llaman a los metodos que actuan para guiar <br>
 *  	al usuario por el programa. <br>
 * 		
 * 		@author Marcos Rodriguez
 */

public class InterfazUsuario {
	
/** 	Menu basico del programa <br>
 * 
 * 		Este metodo imprime un menu de las  <br>
 * 		principales acciones para el usuario. <br>
 */
	
	public static void imprimirMenu() {
			System.out.println("\n \n \n \n \n \n \n \n");
			System.out.println("1.  Registrar incidencia.");				
			System.out.println("2.  Buscar incidencia.");				
			System.out.println("3.  Modificar incidencia.");				
			System.out.println("4.  Elimina una incidencia.");			
			System.out.println("5.  Resolver incidencia.");				
			System.out.println("6.  Modificar incidencia resuelta.");	
			System.out.println("7.  Devolver incidencia resuelta.");			
			System.out.println("8.  Mostrar incidencia pendiente.");		
			System.out.println("9.  Mostrar incidencias resueltas.");	
			System.out.println("10. Mostrar incidencias eliminadas."); 
			System.out.println("11. Exportar XML");
			System.out.println("12. Salir");		
	}

	
/**	 	Menu XML <br>
* 		
* 		Este metodo imprime un menu de <br>
* 		exportarcion de datos mediante xml. <br>
* 	
*/	
	
	public static void XMLmenu() {
			
		System.out.println("1. Exportar pendientes.");
		System.out.println("2. Exportar resueltas.");	
		System.out.println("3. Exportar eliminadas.");		
	}
	
	
	
	
/** 	Esperar Intro <br>
*
* 		Este metodo imprime un aviso y  <br>
* 		consume un salto de linea. <br>
*/	
	public static void esperarIntro() {
		Scanner sc = new Scanner(System.in);
			
			System.out.println("Pulsa enter para continuar");
			sc.nextLine();
	}
}
