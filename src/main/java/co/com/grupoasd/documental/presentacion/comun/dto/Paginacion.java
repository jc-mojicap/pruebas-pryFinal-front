/*
 * Archivo: Paginacion.java
 * Fecha creacion: 18/04/2017
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
package co.com.grupoasd.documental.presentacion.comun.dto;

import java.io.Serializable;

/**
 * DTO para la paginación del resultado de una consulta.
 * @author jmaldonado
 * @author cestrada
 */
public class Paginacion implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * firstResult.
     */
    private Integer firstResult;
    /**
     * maxResults.
     */
    private Integer maxResults;
    /**
     * orderBy.
     */
    private Integer[] orderBy;
    /**
     * strOrderBy.
     */
    private String[] strOrderBy;
    /**
     * orderDesc.
     */
    private boolean orderDesc;
    
    /**
     * Constructor.
     */
    public Paginacion(){
        
    } 
    
    /**
     * Constructor.
     * @param firstResult.
     * @param maxResults
     * @param orderBy.
     * @param orderDesc.
     */
    public Paginacion(Integer firstResult, Integer maxResults,  Integer[] orderBy , Boolean orderDesc){
        this.firstResult = firstResult;
        this.maxResults = maxResults;
        this.orderBy = orderBy;
        this.orderDesc = orderDesc;
    }
    
    /**
     * Constructor.
     * @param firstResult.
     * @param maxResults
     * @param strOrderBy.
     * @param orderDesc.
     */
    public Paginacion(Integer firstResult, Integer maxResults,  String[] strOrderBy , Boolean orderDesc){
        this.firstResult = firstResult;
        this.maxResults = maxResults;
        this.strOrderBy = strOrderBy;
        this.orderDesc = orderDesc;
    } 



    /**
     * @return the firstResult
     */
    public Integer getFirstResult() {
        return firstResult;
    }

    /**
     * @param firstResult the firstResult to set
     */
    public void setFirstResult(Integer firstResult) {
        this.firstResult = firstResult;
    }

    /**
     * @return the maxResults
     */
    public Integer getMaxResults() {
        return maxResults;
    }

    /**
     * @param maxResults the maxResults to set
     */
    public void setMaxResults(Integer maxResults) {
        this.maxResults = maxResults;
    }

    /**
     * @return the orderBy
     */
    public Integer[] getOrderBy() {
        return orderBy;
    }

    /**
     * @param orderBy the orderBy to set
     */
    public void setOrderBy(Integer... orderBy) {
        this.orderBy = orderBy;
    }
    /**
     * @return the getStrOrderBy.
     */
    public String[] getStrOrderBy() {
        return strOrderBy;
    }
    /**
     * @param strOrderBy the strOrderBy to set.
     */
    public void setStrOrderBy(String... strOrderBy) {
        this.strOrderBy = strOrderBy;
    }
    
    /**
     * @return the orderDesc
     */
    public boolean isOrderDesc() {
        return orderDesc;
    }

    /**
     * @param orderDesc the orderDesc to set
     */
    public void setOrderDesc(boolean orderDesc) {
        this.orderDesc = orderDesc;
    }
}