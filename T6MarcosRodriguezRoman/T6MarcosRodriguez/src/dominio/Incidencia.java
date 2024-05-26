package dominio;


import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;


/**		Incidencias <br>
 * 		Pertenece al paquete dominio: <br>
 * 
 * 		Clase que contiene un grupo de atributos <br>
 * 		y metodos que conforman la gran parte de los usos del programa. <br>
 * 		<br>	
 * 
 * 		@author Marcos Rodriguez
 * 
 */


@XmlRootElement(name = "Incidencia")
@XmlType (propOrder = {"codigoString", "estado", "puesto", "problema", "codigoAlternativoString", "segundaDescripcion"})
@XmlAccessorType (XmlAccessType.FIELD)
public class Incidencia {

	@XmlTransient
	private LocalDateTime codigo; 
	
	private String codigoString;
	
	private String estado = "Pendiente";
	
	private int puesto;
		
	private String problema;
	
	@XmlTransient	
	private LocalDateTime codigoAlternativo; 
	
	private String codigoAlternativoString;
	
	private String segundaDescripcion;			
	
	
	
	/* Constructores */

	public Incidencia() {}

	
	/* Getter y setters */




	public LocalDateTime getCodigo() {return codigo;}
	public void setCodigo(LocalDateTime codigo) {this.codigo = codigo;}
	
	
	public String getCodigoString() {return codigoString;}
	public void setCodigoString(String codigoString) {this.codigoString = codigoString;}

	
	public String getEstado() {return estado;}
	public void setEstado(String estado) {this.estado = estado;}
	

	public int getPuesto() {return puesto;}
	public void setPuesto(int puesto) {this.puesto = puesto;}
	

	public String getProblema() {return problema;}
	public void setProblema(String problema) {this.problema = problema;}
	
	
	public LocalDateTime getCodigoAlternativo() {return codigoAlternativo;}
	public void setCodigoAlternativo(LocalDateTime codigoAlternativo) {this.codigoAlternativo = codigoAlternativo;}
	
	
	public String getCodigoAlternativoString() {return codigoAlternativoString;}
	public void setCodigoAlternativoString(String codigoAlternativoString) {
		this.codigoAlternativoString = codigoAlternativoString;}
	
	
	
	public String getSegundaDescripcion() {return segundaDescripcion;}
	public void setSegundaDescripcion(String segundaDescripcion) {this.segundaDescripcion = segundaDescripcion;}

	
	
	/* Lista de metodos */
	
	
/**		Crear Incidencia <br>	
 * 		Metodo que nos permite crear un objeto incidencia <br>
 * 		con valores que el usuario le introduzca. <br>		
 * 
 * 		Se inicializa previamente un objeto de tipo Incidencia <br>
 * 		al cual se le dará mediante {@link Scanner} unos valores <br>
 * 		en un formato especifico <br>
 * 
 * 		@param nuevaIncidencia objeto (normalmente nulo) que recibira los valores
 * 		@return una incidencia con valores en sus atributos principales
 */
	
	
	 public Incidencia crearIncidencia(Incidencia nuevaIncidencia) {
	       Scanner sc = new Scanner(System.in);

	       System.out.println("Introduce el codigo en este formato:");
	       System.out.println("dd/MM/yyyy-HH:mm");

	       String codigo = sc.next();
	       nuevaIncidencia.setCodigo(nuevaIncidencia.formatearCodigo(codigo));
	     

	       sc.nextLine(); // Consumir salto de línea

	       
	       nuevaIncidencia.setCodigoString(nuevaIncidencia.formatearAString(nuevaIncidencia.getCodigo()));
	       System.out.println("Define la descripcion de la incidencia: ");
	       nuevaIncidencia.setProblema(sc.nextLine());
	       
	       
	       do {
	    	   try {
	               	System.out.println("Puesto (número) del que sale la incidencia: ");
	               	nuevaIncidencia.setPuesto(sc.nextInt());
	            } catch (InputMismatchException e) {
	                	sc.nextLine();
	                	nuevaIncidencia.setPuesto(-1);
	            }
	        } while (nuevaIncidencia.getPuesto() == -1);

	        System.out.println(nuevaIncidencia);

	        return nuevaIncidencia;
	    }
	
	
/**		Formatear codigo <br>	
 * 		Método por el cual una cadena de texto <br>
 * 		pasa a ser un {@link LocalDateTime} con un formato <br>
 * 		impuesto por {@link DateTimeFormatter}	<br>	
 * 
 * 		Si el formato no es el especificado se vuelve a <br>
 * 		solicitar al usuario escribirlo, al igual que si se <br>
 * 		introduce un valor no permitido. <br>
 * 
 * 		@param cadena La cadena de texto que debe cumplir el formato
 * 		@return Un {@link LocalDateTime} con un formato.
 */
	

	  public LocalDateTime formatearCodigo(String cadena) {
	        Scanner sc = new Scanner(System.in);

	        try {
	            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm");
	            return LocalDateTime.parse(cadena, formato);

	        } catch (DateTimeParseException e) {
	            System.out.println("Formato o valor incorrecto");
	            System.out.println("Inténtelo de nuevo.");
	            return formatearCodigo(sc.next());
	        }
	    }
    
	    public String formatearAString(LocalDateTime fechaHora) {
	        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm");
	        return fechaHora.format(formato);
	    }
    
    
    
        
/**		toString (sobreescrito) <br>
 *     	Metodo que nos devuelve una cadena <br>
 *     	con los atributos de la incidencia <br>
 *     	
 *     	El metodo tambien mira el estado de la <br>
 *     	incidencia para mostrar ciertos datos o no. <br>
 */		
    
    
     
	@Override
	public String toString() {
	    if (estado.equals("Pendiente")) {
	        return "\n" + "============================" + "\n" +
	        		"Codigo: " + getCodigoString() + "\n" +
	                "Estado: " + getEstado() + "\n" +
	                "Puesto: " + getPuesto() + "\n" +
	                "Problema: " + getProblema() + "\n";
	    } else if (estado.equals("Resuelto")) {
	        return "\n" + "============================" + "\n" +
	        		"Codigo:"  + getCodigoString() + "\n" +
	                "Estado: " + getEstado() + "\n" +
	                "Puesto: " + getPuesto() + "\n" +
	                "Problema: " + getProblema() + "\n" +
	                "Solucion: " + getSegundaDescripcion();
	    } else if (estado.equals("Eliminado")) {
	        return "\n" + "============================" + "\n" +
	        		"Codigo:"  + getCodigoString() + "\n" +
	                "Estado: " + getEstado() + "\n" +
	                "Puesto: " + getPuesto() + "\n" +
	                "Problema: " + getProblema() + "\n" +
	                "Codigo Eliminación: "  + getCodigoAlternativoString() + "\n" +
	                "Motivo eliminación: " + getSegundaDescripcion();
	    } else {
	        return null;
	    }
	}
	
	

	
}

