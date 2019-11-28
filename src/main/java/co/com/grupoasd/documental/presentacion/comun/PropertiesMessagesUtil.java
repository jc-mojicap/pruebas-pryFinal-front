/*
 * Archivo: PropertiesMessagesUtil.java
 * Fecha creacion: 19/04/2017
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
package co.com.grupoasd.documental.presentacion.comun;

import java.io.IOException;

import org.zkoss.util.resource.Labels;

/**
 * Clase singleton que permite cargar los atributos de mensajes teniendo en
 * cuenta el siguiente escenario: <br>
 * 1. Carga los atributos del archivo messages.properties que se cuentran en
 * resources.<br>
 * 
 * @author cestrada
 *
 */
public final class PropertiesMessagesUtil {

    /**
     * PropertiesMessagesUtil Instancia singleton.
     */
    private static PropertiesMessagesUtil instance;

    /**
     * Constructor privado.
     * 
     * @param nombreArchivo
     *            Nombre del archivo .properties.
     * @throws IOException
     *             Si se genera excepcion cargando el archivo.
     */
    private PropertiesMessagesUtil() {

    }

    /**
     * Permite obtener la instancia actual de la aplicacion. Este metodo depende
     * de que la aplicacion en el momento de inicializarse ya haya utilizado el
     * metodo init
     *
     * @return Instancia actual
     * @throws IllegalStateException
     *             Si no se ha inicializado correctamente la instancia en la
     *             aplicacion
     */
    public static PropertiesMessagesUtil instance() {
        if (instance == null) {
            instance = new PropertiesMessagesUtil();
        }
        return instance;
    }

    /**
     * Retorna el valor de una llave configurada.
     * 
     * @param llave
     *            Nombre de la llave
     * @return Contenido de la llave. Si la llave no existe retorna la llave.
     */
    public String get(final String llave) {
        return Labels.getLabel(llave);
    }

    /**
     * Retorna el valor de una llave configurada y sus parametros.
     * 
     * @param llave
     *            Nombre de la llave.
     * @param params
     *            Parametros.
     * @return Contenido de la llave. Si la llave no existe retorna la llave.
     */
    @SuppressWarnings("unchecked")
    public <T> String get(String llave, T... params) {
        return Labels.getLabel(llave, params);
    }
}
