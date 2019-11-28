/*
 * Archivo: PropertiesUtil.java
 * Fecha creacion: 28/02/2017
 * Todos los derechos de propiedad intelectual e industrial sobre esta
 * aplicacion son de propiedad exclusiva del GRUPO ASESORIA EN
 * SISTEMATIZACION DE DATOS SOCIEDAD POR ACCIONES SIMPLIFICADA – GRUPO ASD S.A.S.
 * Su uso, alteracion, reproduccion o modificacion sin la debida
 * consentimiento por escrito de GRUPO ASD S.A.S.
 * autorizacion por parte de su autor quedan totalmente prohibidos.
 * 
 * Este programa se encuentra protegido por las disposiciones de la
 * Ley 23 de 1982 y demas normas concordantes sobre derechos de autor y
 * propiedad intelectual. Su uso no autorizado dara lugar a las sanciones
 * previstas en la Ley.
 */
package co.com.grupoasd.documental.cliente.comun;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Clase singleton que permite cargar los atributos de configuracion teniendo en
 * cuenta el siguiente escenario: <br>
 * 1. Carga los atributos del archivo application.properties que se cuentran en
 * resources.<br>
 * 2. Carga los atributos del archivo application-ext.properties que se debe
 * encontrar ubicado en el home del usuario que esta ejecutando el servidor de
 * aplicacion.
 *
 * @author Juan Carlos Castellanos
 */
public final class PropertiesUtil {

    /**
     * PropertiesUtil Instancia singleton.
     */
    private static PropertiesUtil instance;
    /**
     * Properties.
     */
    private final Properties properties = new Properties();
    /**
     * Nombre del archivo de configuracion.
     */
    private static final String ARCHIVO_PROPERTIES = "application";
    /**
     * Sufijo del archivo de configuracion a leer en el home del usuario
     * para sobrecarga de configuraciones.
     */
    private static final String EXT = "ext";
    /**
     * Extension del archivo de configuracion.
     */
    private static final String EXTENSION = "properties";

    /**
     * Constructor privado.
     * @param nombreArchivo Nombre del archivo .properties.
     * @throws IOException Si se genera excepcion cargando el archivo.
     */
    private PropertiesUtil(final String nombreArchivo) throws IOException {
        if (nombreArchivo == null || nombreArchivo.trim().isEmpty()) {
            throw new IllegalArgumentException("nombreArchivo no puede ser null ni vacio");
        }
        if (nombreArchivo.trim().contains(" ")) {
            throw new IllegalArgumentException("nombreArchivo no puede tener espacios en "
                    + "blanco ya que es un archivo del sistema");
        }
        // Escenario 1: Se carga el properties por defecto que se encuentra en el 
        // paquete raiz del proyecto
        final String defaultProperties = String.format("%s.%s", nombreArchivo.trim(), EXTENSION);
        try (InputStream input = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("/" + defaultProperties)) {
            properties.load(input);
        }
        // Escenario 2: Si existe, se sobrecarga con los properties del home del usuario 
        final String extProperties = String.format("%s-%s.%s", nombreArchivo.trim(), 
                EXT, EXTENSION);
        final Path pathExt = Paths.get(System.getProperty("user.home"), 
                nombreArchivo, extProperties);
        if (Files.exists(pathExt)) {
            try (InputStream input = Files.newInputStream(pathExt)) {
                properties.load(input);
            }
        }
    }

    /**
     * Permite inicializar la clase para que pueda ser utilizada en el contexto
     * de toda la aplicacion.    
     * @return Nueva instancia de tipo Singleton
     */
    private static PropertiesUtil init() {
        if (instance == null) {
            try {
                instance = new PropertiesUtil(ARCHIVO_PROPERTIES);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }

    /**
     * Permite obtener la instancia actual de la aplicacion. Este metodo depende
     * de que la aplicacion en el momento de inicializarse ya haya utilizado el
     * metodo init
     *
     * @return Instancia actual
     * @throws IllegalStateException Si no se ha inicializado correctamente la
     * instancia en la aplicacion
     */
    public static PropertiesUtil instance() {
        if (instance == null) {
            init();
        }
        return instance;
    }

    /**
     * Retorna el valor de una llave configurada.     
     * @param llave Nombre de la llave
     * @return Contenido de la llave. Si la llave no existe retorna null
     */
    public String get(final String llave) {
        return this.properties.getProperty(llave);
    }
}
