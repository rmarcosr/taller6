package aplicacion;

import dominio.Incidencia;
import dominio.ListaIncidencias;
import persistencia.ExportarXML;
import persistencia.IncidenciaDAO;
import presentacion.InterfazUsuario;
import persistencia.IncidenciaDAO;


import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;



/**		Clase logica <br>
 * 		Pertenece al paquete aplicacion:  <br>
 * 		
 * 		Convocado por main, desde esta clase se usan <br>
 * 		casi todos los métodos publicos, y se gestionan <br>
 * 		las listas de incidencias cargadas antes en main {@link Main} <br>
 *  
 * 		@author Marcos Rodriguez
 */



public class Logica {

	

	
	public static void opcionesDelPrograma(int opcion, IncidenciaDAO dao){
		
		switch (opcion) {
		
		case 1 -> {	
			
			Incidencia nuevaIncidencia = new Incidencia();
		
			nuevaIncidencia.crearIncidencia(nuevaIncidencia);
		
			dao.create(nuevaIncidencia);
		
		InterfazUsuario.esperarIntro();
		}
		
		
		
		case 2 -> {
			Scanner sc = new Scanner(System.in);
			Incidencia buscarIncidencia = new Incidencia();
		
			System.out.println("Introduce el codigo en este formato:");
		    System.out.println("dd/MM/yyyy-HH:mm");
			
			String codigo = sc.next();
			buscarIncidencia.setCodigo(buscarIncidencia.formatearCodigo(codigo));
			buscarIncidencia.setCodigoString(buscarIncidencia.formatearAString(buscarIncidencia.getCodigo()));
			
			buscarIncidencia = dao.read(buscarIncidencia.getCodigoString());
			
			if (buscarIncidencia.getProblema() == null) {
				System.out.println("No se ha encontrado incidencia");
			} else {
				System.out.println(buscarIncidencia);
			}
		
		InterfazUsuario.esperarIntro();
		}
		
		case 3 -> {
			Scanner sc = new Scanner(System.in);
			Incidencia incidenciaAmodificar = new Incidencia();
		
			System.out.println("Introduce el codigo en este formato:");
		    System.out.println("dd/MM/yyyy-HH:mm");
			
			String codigo = sc.next();
			incidenciaAmodificar.setCodigo(incidenciaAmodificar.formatearCodigo(codigo));
			incidenciaAmodificar.setCodigoString(incidenciaAmodificar.formatearAString(incidenciaAmodificar.getCodigo()));
			
			incidenciaAmodificar = dao.read(incidenciaAmodificar.getCodigoString());
			
			if (incidenciaAmodificar.getEstado().equals("Pendiente")){
	
				System.out.println("Nuevo puesto");
				incidenciaAmodificar.setPuesto(sc.nextInt());
				
				sc.nextLine();
				
				System.out.println("Nuevo problema");
				incidenciaAmodificar.setSegundaDescripcion(sc.next());
				
				dao.update(incidenciaAmodificar);
				incidenciaAmodificar = dao.read(incidenciaAmodificar.getCodigoString());
				
				System.out.println(incidenciaAmodificar);
				
			} else {
				System.out.println("Solo puedes poder modificar incidencias pendientes");
			}
			
			
			
		
		InterfazUsuario.esperarIntro();
		}
		
		
		
		case 4 -> {
			Scanner sc = new Scanner(System.in);
			Incidencia incidenciaEliminada = new Incidencia();
		
			System.out.println("Introduce el codigo en este formato:");
		    System.out.println("dd/MM/yyyy-HH:mm");
			
			String codigo = sc.next();
			incidenciaEliminada.setCodigo(incidenciaEliminada.formatearCodigo(codigo));
			incidenciaEliminada.setCodigoString(incidenciaEliminada.formatearAString(incidenciaEliminada.getCodigo()));
			
			incidenciaEliminada = dao.read(incidenciaEliminada.getCodigoString());
			
			if (incidenciaEliminada.getEstado().equals("Resuelto") ||incidenciaEliminada.getEstado().equals("Eliminado") ) {
				System.out.println("No se pueden eliminar incidencias no pendientes");
			} else { 
				
				System.out.println("Motivo elimininacion");
				incidenciaEliminada.setSegundaDescripcion(sc.next());
				
				System.out.println("Codigo solucion:");
				System.out.println("dd/MM/yyyy-HH:mm");
				String codigoAlternativo = sc.next();
				incidenciaEliminada.setCodigoAlternativo(incidenciaEliminada.formatearCodigo(codigoAlternativo));
						
						
				incidenciaEliminada.setCodigoAlternativoString(incidenciaEliminada.formatearAString(incidenciaEliminada.getCodigoAlternativo()));
				
				
				
				dao.update(incidenciaEliminada, "Eliminado");
				incidenciaEliminada = dao.read(incidenciaEliminada.getCodigoString());
			}	
		
		InterfazUsuario.esperarIntro();
		}
		
		
		
		
		case 5 -> {
			Scanner sc = new Scanner(System.in);
			Incidencia incidenciaResuelta = new Incidencia();
		
			System.out.println("Introduce el codigo en este formato:");
		    System.out.println("dd/MM/yyyy-HH:mm");
			
			String codigo = sc.next();
			incidenciaResuelta.setCodigo(incidenciaResuelta.formatearCodigo(codigo));
			incidenciaResuelta.setCodigoString(incidenciaResuelta.formatearAString(incidenciaResuelta.getCodigo()));
			
			incidenciaResuelta = dao.read(incidenciaResuelta.getCodigoString());
			
			if (incidenciaResuelta.getEstado().equals("Resuelto") ||incidenciaResuelta.getEstado().equals("Eliminado") ) {
				System.out.println("No se pueden solucionar incidencias no pendientes");
			} else {
				
				System.out.println("Solucion aplicada");
				incidenciaResuelta.setSegundaDescripcion(sc.next());
				
				System.out.println("Codigo solucion:");
				System.out.println("dd/MM/yyyy-HH:mm");
				String codigoAlternativo = sc.next();
				incidenciaResuelta.setCodigoAlternativo(incidenciaResuelta.formatearCodigo(codigoAlternativo));
						
						
				incidenciaResuelta.setCodigoAlternativoString(incidenciaResuelta.formatearAString(incidenciaResuelta.getCodigoAlternativo()));
				
				
				dao.update(incidenciaResuelta, "Resuelto");
				
				incidenciaResuelta = dao.read(incidenciaResuelta.getCodigoString());
				
			}

		InterfazUsuario.esperarIntro();
		}
		
		
		case 6 ->{ 
			
			Scanner sc = new Scanner(System.in);
			Incidencia incidenciaAmodificar = new Incidencia();
		
			System.out.println("Introduce el codigo en este formato:");
		    System.out.println("dd/MM/yyyy-HH:mm");
			
			String codigo = sc.next();
			incidenciaAmodificar.setCodigo(incidenciaAmodificar.formatearCodigo(codigo));
			incidenciaAmodificar.setCodigoString(incidenciaAmodificar.formatearAString(incidenciaAmodificar.getCodigo()));
			
			incidenciaAmodificar = dao.read(incidenciaAmodificar.getCodigoString());
			
			if (incidenciaAmodificar.getEstado().equals("Resuelto")){
	
				incidenciaAmodificar = dao.read(incidenciaAmodificar.getCodigoString());
				
				
				System.out.println("Nueva Solucion ");
				sc.nextLine();
				incidenciaAmodificar.setSegundaDescripcion(sc.nextLine());
				
				System.out.println("Nuevo codigo solucion:");
				
				String codigoNuevo = sc.next();
				
				incidenciaAmodificar.setCodigoAlternativo(incidenciaAmodificar.formatearCodigo(codigoNuevo));
				incidenciaAmodificar.setCodigoAlternativoString(incidenciaAmodificar.formatearAString(incidenciaAmodificar.getCodigoAlternativo()));
				
				dao.delete(codigo);
				
				dao.create(incidenciaAmodificar, incidenciaAmodificar.getEstado());
				
				
				System.out.println(incidenciaAmodificar);
				
			} else {
				System.out.println("Solo puedes poder modificar incidencias resueltas");
			}		
				
		InterfazUsuario.esperarIntro();
		} 
		
	
		case 7 -> {
			Scanner sc = new Scanner(System.in);
			Incidencia incidenciaResuelta = new Incidencia();
		
			System.out.println("Introduce el codigo en este formato:");
		    System.out.println("dd/MM/yyyy-HH:mm");
			
			String codigo = sc.next();
			incidenciaResuelta.setCodigo(incidenciaResuelta.formatearCodigo(codigo));
			incidenciaResuelta.setCodigoString(incidenciaResuelta.formatearAString(incidenciaResuelta.getCodigo()));
			
			incidenciaResuelta = dao.read(incidenciaResuelta.getCodigoString());
			
			if (incidenciaResuelta.getEstado().equals("Resuelto")) {
				incidenciaResuelta.setEstado("Pendiente");
				incidenciaResuelta.setCodigoAlternativo(null);
				incidenciaResuelta.setCodigoAlternativoString(null);
				incidenciaResuelta.setSegundaDescripcion(null);
				
				dao.delete(codigo);
				
				dao.create(incidenciaResuelta);
				
				System.out.println(incidenciaResuelta);
			} else {
				System.out.println("No se puede devolver una incidencia no resuelta");
			}
			
			
			
		InterfazUsuario.esperarIntro();		
		}
		
		case 8 -> {		
			
			List<Incidencia> incidenciasPendientes = dao.listarIncidencias("Pendiente");
			System.out.println(incidenciasPendientes);		
			
		InterfazUsuario.esperarIntro();		
		}
		
		
		case 9 -> {
			List<Incidencia> incidenciasResueltas = dao.listarIncidencias("Resuelto");
			System.out.println(incidenciasResueltas);		
			
		InterfazUsuario.esperarIntro();	
		}
		
		
		case 10 -> {
			List<Incidencia> incidenciasEliminadas = dao.listarIncidencias("Eliminado");
			System.out.println(incidenciasEliminadas);		
			
		InterfazUsuario.esperarIntro();	
		}
		
		
		case 11 -> {
			Scanner sc = new Scanner(System.in);
			InterfazUsuario.XMLmenu();
			opcion = sc.nextInt();
			
			switch (opcion) {
			case 1 -> {
				
				Incidencia incidenciaXML = new Incidencia();
				List<Incidencia> incidenciasPendientes = dao.listarIncidencias("Pendiente");
				
				for (int i = 0; i < incidenciasPendientes.size(); i++){
					incidenciaXML = incidenciasPendientes.get(i);	
					String nombreArchivo = "pendientes"+i+".xml";
					ExportarXML.exportarIncidencia(incidenciaXML, "archivosXML/pendientes/"+nombreArchivo);
				}
				
				
			}
			
			
			case 2 -> {
				Incidencia incidenciaXML = new Incidencia();
				List<Incidencia> incidenciasResueltas = dao.listarIncidencias("Resuelto");
				
				for (int i = 0; i < incidenciasResueltas.size(); i++){
					incidenciaXML = incidenciasResueltas.get(i);	
					String nombreArchivo = "resueltos"+i+".xml";
					ExportarXML.exportarIncidencia(incidenciaXML, "archivosXML/resueltos/"+nombreArchivo);
				}
			}
			
			case 3 -> {
				Incidencia incidenciaXML = new Incidencia();
				List<Incidencia> incidenciasEliminadas = dao.listarIncidencias("Eliminado");
				
				for (int i = 0; i < incidenciasEliminadas.size(); i++){
					incidenciaXML = incidenciasEliminadas.get(i);	
					String nombreArchivo = "eliminados"+i+".xml";
					ExportarXML.exportarIncidencia(incidenciaXML, "archivosXML/eliminados/"+nombreArchivo);
				}
			}
			
			
			default -> {
				System.out.println("Opcion no valida");
			}
			
			}
			
				
			
		InterfazUsuario.esperarIntro();	;
		}
		
		
		
		case 12 -> {
			System.out.println("Adios, buen día.");
		}
		
		
		default -> System.out.println("Opcion no valida");
		}	
	}
}
