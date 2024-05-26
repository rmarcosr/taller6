package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import dominio.Incidencia;







public class IncidenciaDAO {
	
	private Connection conexion;
	private final String USUARIO="marcos";
	private final String PASSWORD="12345";
	private final String MAQUINA="localhost";
	private final String BD = "gestorIncidencias";


	public IncidenciaDAO() {
		conexion = conectarConBD();
	}
	
	
	private Connection conectarConBD() {
		Connection con = null;
		
		String url = "jdbc:mysql://"+ MAQUINA + "/" + BD;
		try {
			con = DriverManager.getConnection(url, USUARIO, PASSWORD);
			System.out.println("Se ha establecido conexion");
			
		} catch (SQLException e) {
			System.out.println("Error al conectar con la BD");
		}
		
		return con;
	}
	
	
	public void create(Incidencia nuevaIncidencia) {
		
		String sql = "INSERT INTO incidencias (codigo, estado, puesto, descripcion) VALUES (?, ?, ?, ?)";
		
			try {
				PreparedStatement sentencia = conexion.prepareStatement(sql);
				sentencia.setString(1, nuevaIncidencia.getCodigoString());
				sentencia.setString(2, nuevaIncidencia.getEstado());
				sentencia.setInt(3, nuevaIncidencia.getPuesto());
				sentencia.setString ( 4, nuevaIncidencia.getProblema());
				
				sentencia.executeUpdate();
			
			} catch (SQLException e) {
				e.printStackTrace();
				e.getErrorCode();
			}
		}
	
	
	public void create(Incidencia incidenciaSolucionada, String estado) {
		
		String sql = "INSERT INTO incidencias (codigo, estado, puesto, descripcion, segundo_codigo, segunda_descripcion ) VALUES (?, ?, ?, ?, ?, ?)";
		
			try {
				PreparedStatement sentencia = conexion.prepareStatement(sql);
				sentencia.setString(1, incidenciaSolucionada.getCodigoString());
				sentencia.setString(2, estado);
				sentencia.setInt(3, incidenciaSolucionada.getPuesto());
				sentencia.setString ( 4, incidenciaSolucionada.getProblema());
				sentencia.setString ( 5, incidenciaSolucionada.getCodigoAlternativoString());
				sentencia.setString ( 6, incidenciaSolucionada.getSegundaDescripcion());
				
				sentencia.executeUpdate();
			
			} catch (SQLException e) {
				e.printStackTrace();
				e.getErrorCode();
			}
		}

	
	public Incidencia read (String codigo) {
		Incidencia incidenciaAbuscar =  new Incidencia();
		String sql = "SELECT * FROM incidencias WHERE codigo = ?";
				
		try {
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			
			sentencia.setString(1, codigo);
			
			
			ResultSet rs = sentencia.executeQuery();
			
			if (rs.next()) {
				String codigoBD = rs.getString("codigo");
				String estado = rs.getString("estado");
				int puesto = rs.getInt("puesto");
				String descripcion = rs.getString("descripcion");
				String segundoCodigo = rs.getString("segundo_codigo");
				String segundaDescripcion = rs.getString("segunda_descripcion");
				
				incidenciaAbuscar.setCodigoString(codigoBD);
				incidenciaAbuscar.setEstado(estado);
				incidenciaAbuscar.setPuesto(puesto);
				incidenciaAbuscar.setProblema(descripcion);
				incidenciaAbuscar.setCodigoAlternativoString(segundoCodigo);
				incidenciaAbuscar.setSegundaDescripcion(segundaDescripcion);
					
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			e.getErrorCode();
		}		
		return incidenciaAbuscar;
	}
	
	
	public void update(Incidencia incidencia) {
		

			String sql = "UPDATE incidencias SET puesto=?, descripcion=? WHERE codigo=?";
			
			try {	
				PreparedStatement sentencia = conexion.prepareStatement(sql);
				
				sentencia.setInt(1, incidencia.getPuesto());
				sentencia.setString(2,incidencia.getProblema());
				sentencia.setString(3, incidencia.getCodigoString());

				
				sentencia.executeUpdate();			
				
			} catch (SQLException e) {
				e.printStackTrace();
				e.getErrorCode();
			}
		}
			
			
	public void update(Incidencia incidencia, String estado) {
				
	
		String sql = "UPDATE incidencias SET segundo_codigo=?, segunda_descripcion=?, estado='"+estado+"' WHERE codigo=? AND estado='Pendiente'";
			
			
		try {	
			PreparedStatement sentencia = conexion.prepareStatement(sql);
				
			incidencia.setEstado(estado);	
			
			
			sentencia.setString(1, incidencia.getCodigoAlternativoString());					
			sentencia.setString(2, incidencia.getSegundaDescripcion());
			sentencia.setString(3, incidencia.getCodigoString());		
			sentencia.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
				e.getErrorCode();
			}
		}
	
	
	public void delete(String codigo) {
		String sql = "DELETE FROM incidencias WHERE codigo = ?";
		
		try {
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			sentencia.setString(1, codigo);
			sentencia.executeUpdate();		
		} catch (SQLException e) {
			e.printStackTrace();
			e.getErrorCode();
		}
	}
		

	public List<Incidencia> listarIncidencias(String estado){
		
		List<Incidencia> listaIncidencias = new LinkedList<>();
		
		Incidencia incidencia = new Incidencia();
		
		String sql = "SELECT * FROM incidencias WHERE estado = ?";
		
		
		try {
			PreparedStatement sentencia = conexion.prepareStatement(sql);	
			sentencia.setString(1, estado);
			
			
			ResultSet rs = sentencia.executeQuery();
			
			while (rs.next()) {
				String codigo = rs.getString("codigo");
				String estadoBD = rs.getString("estado");
				int puesto = rs.getInt("puesto");
				String descripcion = rs.getString("descripcion");
				String segundoCodigo = rs.getString("segundo_codigo");
				String segundaDescripcion = rs.getString("segunda_descripcion");
				
				incidencia.setCodigoString(codigo);
				incidencia.setEstado(estadoBD);
				incidencia.setPuesto(puesto);
				incidencia.setProblema(descripcion);
				incidencia.setCodigoAlternativoString(segundoCodigo);
				incidencia.setSegundaDescripcion(segundaDescripcion);	
				
				
				listaIncidencias.add(incidencia);	
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			e.getErrorCode();
		}
		
		return listaIncidencias;
		
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	





