/*
* Archivo: TrdCodificacion.java
* Fecha creacion = 06/06/2017
* Todos los derechos de propiedad intelectual e industrial sobre esta
* aplicacion son de propiedad exclusiva del GRUPO ASESORIA EN
* SISTEMATIZACION DE DATOS SOCIEDAD POR ACCIONES SIMPLIFICADA – GRUPO ASD S.A.S.
* Su uso, alteracion, reproduccion o modificacion sin el debido
* consentimiento por escrito del GRUPO ASD S.A.S.
* autorizacion por parte de su autor quedan totalmente prohibidos.
* 
* Este programa se encuentra protegido por las disposiciones de la
* Ley 23 de 1982 y demas normas concordantes sobre derechos de autor y
* propiedad intelectual. Su uso no autorizado dara lugar a las sanciones
* previstas en la Ley.
*/
package co.com.grupoasd.documental.cliente.trd.model;

import java.io.Serializable;

public class TrdCodificacion implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * empresaId.
     */
    private int empresaId;
    /**
     * estructuraId.
     */
    private int estructuraId;
    /**
     * estructuraNombre.
     */
    private String estructuraNombre;
    /**
     * tipoDatoId.
     */
    private Integer tipoDatoId;
    /**
     * tipoDatoNombre.
     */
    private String tipoDatoNombre;
    
    /**
     * aplicar.
     */
    private boolean aplicar;
    
    /**
     * longitud.
     */
    private Integer longitud;
    
    public TrdCodificacion() {

    }
    
    public TrdCodificacion(int empresaId, int estructuraId) {
        super();
        this.empresaId = empresaId;
        this.estructuraId = estructuraId;
    }
    
    public TrdCodificacion(int empresaId, int estructuraId, Integer tipoDatoId, String tipoDatoNombre) {
        super();
        this.empresaId = empresaId;
        this.estructuraId = estructuraId;
        this.tipoDatoId = tipoDatoId;
        this.tipoDatoNombre = tipoDatoNombre;
    }
    
    /**
     * @return the estructuraNombre
     */
    public String getEstructuraNombre() {
        return estructuraNombre;
    }

    /**
     * @param estructuraNombre the estructuraNombre to set
     */
    public void setEstructuraNombre(String estructuraNombre) {
        this.estructuraNombre = estructuraNombre;
    }

    /**
     * @return the aplicar
     */
    public boolean isAplicar() {
        return aplicar;
    }

    /**
     * @param aplicar the aplicar to set
     */
    public void setAplicar(boolean aplicar) {
        this.aplicar = aplicar;
    }

    /**
     * @return the longitud
     */
    public Integer getLongitud() {
        return longitud;
    }

    /**
     * @param longitud the longitud to set
     */
    public void setLongitud(Integer longitud) {
        this.longitud = longitud;
    }

    /**
     * @return the empresaId
     */
    public int getEmpresaId() {
        return empresaId;
    }

    /**
     * @param empresaId the empresaId to set
     */
    public void setEmpresaId(int empresaId) {
        this.empresaId = empresaId;
    }

    /**
     * @return the estructuraId
     */
    public int getEstructuraId() {
        return estructuraId;
    }

    /**
     * @param estructuraId the estructuraId to set
     */
    public void setEstructuraId(int estructuraId) {
        this.estructuraId = estructuraId;
    }

    /**
     * @return the tipoDatoId
     */
    public Integer getTipoDatoId() {
        return tipoDatoId;
    }

    /**
     * @param tipoDatoId the tipoDatoId to set
     */
    public void setTipoDatoId(Integer tipoDatoId) {
        this.tipoDatoId = tipoDatoId;
    }

    /**
     * @return the tipoDatoNombre
     */
    public String getTipoDatoNombre() {
        return tipoDatoNombre;
    }

    /**
     * @param tipoDatoNombre the tipoDatoNombre to set
     */
    public void setTipoDatoNombre(String tipoDatoNombre) {
        this.tipoDatoNombre = tipoDatoNombre;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + empresaId;
        result = prime * result + estructuraId;
        return result;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TrdCodificacion other = (TrdCodificacion) obj;
        if (empresaId != other.empresaId)
            return false;
        if (estructuraId != other.estructuraId)
            return false;
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "TrdCodificacion [empresaId=" + empresaId + ", estructuraId=" + estructuraId + ", "
                + (estructuraNombre != null ? "estructuraNombre=" + estructuraNombre + ", " : "")
                + (tipoDatoId != null ? "tipoDatoId=" + tipoDatoId + ", " : "")
                + (tipoDatoNombre != null ? "tipoDatoNombre=" + tipoDatoNombre + ", " : "") + "aplicar=" + aplicar
                + ", " + (longitud != null ? "longitud=" + longitud : "") + "]";
    }
        
}
