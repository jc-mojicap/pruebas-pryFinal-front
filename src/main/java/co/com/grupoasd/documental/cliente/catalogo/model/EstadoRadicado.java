 /* Archivo: EstadoRadicado.java
 * Fecha creacion: 14/03/2017
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
package co.com.grupoasd.documental.cliente.catalogo.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Modelo de dominio EstadoRadicado.
 * 
 * @author cestrada
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class EstadoRadicado implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
   
    /**
     * Identificador del estado radicado.
     */
    private Integer estadoRadId;
    
    /**
     * Nombre del estado radicado.
     */
    private String nombre;

    /**
     * Constructor por defecto.
     */
    public EstadoRadicado() {
        
    }
    
    /**
     * Constructor.
     * @param nombre Nombre de la empresa.
     */
    public EstadoRadicado(final String nombre) {
        this.nombre = nombre;
    }

    /**
     * getEstadoRadId.
     * @return Integer.
     */
    public Integer getEstadoRadId() {
        return estadoRadId;
    }

    /**
     * setEstadoRadId.
     * @param estadoRadId Identitificador del Estado Radicado.
     */
    public void setEstadoRadId(final Integer estadoRadId) {
        this.estadoRadId = estadoRadId;
    }

    /**
     * getNombre.
     * @return String.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * setNombre.
     * @param nombre Nombre del Estado Radicado.
     */
    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

}
