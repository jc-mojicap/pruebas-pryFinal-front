/*
* Archivo: CorSecuencia.java
* Fecha creacion = 03/04/2017
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
package co.com.grupoasd.documental.cliente.correspondencia.model;

import java.io.Serializable;

public class CorSecuencia implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Identificador de la secuencia.
     */
    private Integer secuenciaId;
    
    /**
     * Valor de la secuencia.
     */
    private long valor;
    
    /**
     * Prefijo del radicado.
     */
    private String prefijoRadicado;
    
    /**
     * Sufijo del radicado.
     */
    private String sufijoRadicado;
    
    /**
     * Identificador de la empresa a la que pertenece la secuencia.
     */
    private Integer empresaId;

    /**
     * @return the secuenciaId
     */
    public Integer getSecuenciaId() {
        return secuenciaId;
    }

    /**
     * @param secuenciaId the secuenciaId to set
     */
    public void setSecuenciaId(Integer secuenciaId) {
        this.secuenciaId = secuenciaId;
    }

    /**
     * @return the valor
     */
    public long getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(long valor) {
        this.valor = valor;
    }

    /**
     * @return the prefijoRadicado
     */
    public String getPrefijoRadicado() {
        return prefijoRadicado;
    }

    /**
     * @param prefijoRadicado the prefijoRadicado to set
     */
    public void setPrefijoRadicado(String prefijoRadicado) {
        this.prefijoRadicado = prefijoRadicado;
    }

    /**
     * @return the sufijoRadicado
     */
    public String getSufijoRadicado() {
        return sufijoRadicado;
    }

    /**
     * @param sufijoRadicado the sufijoRadicado to set
     */
    public void setSufijoRadicado(String sufijoRadicado) {
        this.sufijoRadicado = sufijoRadicado;
    }

    /**
     * @return the empresaId
     */
    public Integer getEmpresaId() {
        return empresaId;
    }

    /**
     * @param empresaId the empresaId to set
     */
    public void setEmpresaId(Integer empresaId) {
        this.empresaId = empresaId;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((secuenciaId == null) ? 0 : secuenciaId.hashCode());
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
        CorSecuencia other = (CorSecuencia) obj;
        if (secuenciaId == null) {
            if (other.secuenciaId != null)
                return false;
        } else if (!secuenciaId.equals(other.secuenciaId))
            return false;
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "CorSecuencia [" + (secuenciaId != null ? "secuenciaId=" + secuenciaId + ", " : "") + "valor=" + valor
                + ", " + (empresaId != null ? "empresaId=" + empresaId : "") + "]";
    }
    
}
