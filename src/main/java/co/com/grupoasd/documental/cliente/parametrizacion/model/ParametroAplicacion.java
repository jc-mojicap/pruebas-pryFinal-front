/*
* Archivo: ParametroAplicacion.java
* Fecha creacion = 07/06/2017
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
package co.com.grupoasd.documental.cliente.parametrizacion.model;

import java.io.Serializable;

public class ParametroAplicacion implements Serializable{

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    
    private String parametroAppId;

    private int empresaId;
    
    private String valor;

    private String descripcion;
    

    public ParametroAplicacion() {

    }

    public ParametroAplicacion(String parametroAppId, int empresaId) {
        this.parametroAppId = parametroAppId;
        this.empresaId = empresaId;
    }
    
    public ParametroAplicacion(String parametroAppId, int empresaId, String valor, String descripcion) {
        this.parametroAppId = parametroAppId;
        this.empresaId = empresaId;
        this.valor = valor;
        this.descripcion = descripcion;
    }

    /**
     * @return the parametroAppId
     */
    public String getParametroAppId() {
        return parametroAppId;
    }

    /**
     * @param parametroAppId the parametroAppId to set
     */
    public void setParametroAppId(String parametroAppId) {
        this.parametroAppId = parametroAppId;
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
     * @return the valor
     */
    public String getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(String valor) {
        this.valor = valor;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((parametroAppId == null) ? 0 : parametroAppId.hashCode());
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
        ParametroAplicacion other = (ParametroAplicacion) obj;
        if (parametroAppId == null) {
            if (other.parametroAppId != null)
                return false;
        } else if (!parametroAppId.equals(other.parametroAppId))
            return false;
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ParametroAplicacion [" + (parametroAppId != null ? "parametroAppId=" + parametroAppId + ", " : "")
                + (valor != null ? "valor=" + valor + ", " : "")
                + (descripcion != null ? "descripcion=" + descripcion : "") + "]";
    }
    
}
