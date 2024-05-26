package persistencia;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import dominio.Incidencia;
public class ExportarXML {

	
	public static void exportarIncidencia(Incidencia lista, String ruta) {
        try {
          
            JAXBContext context = JAXBContext.newInstance(Incidencia.class);
            
            Marshaller marshaller = context.createMarshaller();
          
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
           
            
            marshaller.marshal(lista, new File(ruta));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
	
	
	
	
	
	
	
}
