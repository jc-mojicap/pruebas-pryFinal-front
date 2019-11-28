/*
 * Archivo: MdtMunicipioDto.java
 * Fecha creacion: 16/03/2017
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

/**
 * Entidad MdtMunicipioDto.
 * @author cestrada
 */
public class MdtMunicipio implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Código dane.
     */
    private String codigoDane;
    /**
     * Nombre del municipio.
     */
    private String nombre;
    /**
     * Nombre del departamento.
     */
    private String codigoDepartamento;
    /**
     * Nombre del departamento.
     */
    private String nombreDepartamento;

    /**
     * Constructor.
     */
    public MdtMunicipio() {
    
    }

    /**
     * getCodigoDane.
     * @return String
     */
    public String getCodigoDane() {
        return codigoDane;
    }

    /**
     * setCodigoDane.
     * @param codigoDane
     */
    public void setCodigoDane(String codigoDane) {
        this.codigoDane = codigoDane;
    }

    /**
     * getNombre.
     * @return String
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * setNombre.
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * getCodigoDepartamento.
     * @return String.
     */
    public String getCodigoDepartamento() {
        return codigoDepartamento;
    }

    /**
     * setCodigoDepartamento.
     * @param codigoDepartamento
     */
    public void setCodigoDepartamento(String codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    /**
     * getNombreDepartamento.
     * @return String
     */
    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    /**
     * setNombreDepartamento.
     * @param nombreDepartamento
     */
    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }
    
    /**
     * getMunicipioDepartamento.
     * @return String
     */
    public String getMunicipioDepartamento(){
        return nombre+" ("+nombreDepartamento+")";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigoDane == null) ? 0 : codigoDane.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MdtMunicipio other = (MdtMunicipio) obj;
        if (codigoDane == null) {
            if (other.codigoDane != null)
                return false;
        } else if (!codigoDane.equals(other.codigoDane))
            return false;
        return true;
    }

   

}
