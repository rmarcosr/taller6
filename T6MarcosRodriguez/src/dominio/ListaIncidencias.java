package dominio;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**		Lista Incidencias <br>
 * 		Pertenece al paquete dominio: <br>
 * 		Una clase que contiene un {@link ArrayList} de {@link Incidencias} <br>
 * 
 * 		Esta clase contiene una lista de métodos <br>
 *  	Que actuan sobre las distintas listas de incidencias que existen <br>
 *  	dentro del programa. <br>
 *  
 *  	E implementa {@link Serializable}
 *  	
 *  	@author Marcos Rodríguez
 */


public class ListaIncidencias {
	
	private ArrayList <Incidencia> listaIncidencias = new ArrayList<>();
	
	
	/* Constructores */
	
	public ListaIncidencias() {}


	/* Getter y setters */

	
	public ArrayList <Incidencia> getListaIncidencias() {return listaIncidencias;}

	
	public void setListaIncidencias(ArrayList <Incidencia> listaIncidencias) {
	this.listaIncidencias = listaIncidencias;}

	

		 
}
	 
	 

	
	

	
	



	
	
	

