/*
 * Archivo: MunicipioMapper.java
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
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Modelo de dominio Area.
 *
 * @author cestrada
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class Area implements Serializable {

	/**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Identificador del area.
     */
    private Integer areaId;
    /**
     * Nombre del area.
     */
    private String nombre;
    /**
     * Area inactiva.
     */
    private boolean inactivo;
    /**
     * Usuario modifica.
     */
    private int usuarioModifica;
    /**
     * Fecha modifica.
     */
    private Date fechaModifica;
    /**
     * Identificador de la empresa.
     */
    private Integer empresaId;
    /**
     * Nombre de la empresa.
     */
    private String empresaNombre;
    
    /**
     * Listado de series
     */
    private List<Serie> series;
    
    /**
     * Constructor
     */
    public Area() {

    }

    /**
     * getAreaId.
     * @return Integer
     */
    public Integer getAreaId() {
        return areaId;
    }

    /**
     * setAreaId.
     * @param setAreaId Identificador del area.
     */
    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
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
     * @param nombre Nombre del area.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * getInactivo.
     * @return boolean
     */
    public boolean getInactivo() {
        return inactivo;
    }

    /**
     * setInactivo.
     * @param inactivo Area inactiva.
     */
    public void setInactivo(boolean inactivo) {
        this.inactivo = inactivo;
    }

    /**
     * getUsuarioModifica.
     * @return int
     */
    public int getUsuarioModifica() {
        return usuarioModifica;
    }

    /**
     * setUsuarioModifica.
     * @param usuarioModifica Usuario modificacion area.
     */
    public void setUsuarioModifica(int usuarioModifica) {
        this.usuarioModifica = usuarioModifica;
    }

    /**
     * getFechaModifica.
     * @return Date
     */
    public Date getFechaModifica() {
        return fechaModifica;
    }

    /**
     * setFechaModifica.
     * @param fechaModifica Fecha modificacion area.
     */
    public void setFechaModifica(Date fechaModifica) {
        this.fechaModifica = fechaModifica;
    }

    /**
     * getEmpresaId.
     * @return
     */
    public Integer getEmpresaId() {
        return empresaId;
    }

    /**
     * setEmpresaId.
     * @param empresaId Indentificador de la empresa.
     */
    public void setEmpresaId(Integer empresaId) {
        this.empresaId = empresaId;
    }
    
    /**
     * getEmpresaNombre.
     * @return String
     */
    public String getEmpresaNombre() {
        return empresaNombre;
    }

    /**
     * setEmpresaNombre.
     * @param empesaNombre Nombre de la empresa.
     */
    public void setEmpresaNombre(String empresaNombre) {
        this.empresaNombre = empresaNombre;
    }

    /**
	 * @return the series
	 */
	public List<Serie> getSeries() {
		return series;
	}

	/**
	 * @param series the series to set
	 */
	public void setSeries(List<Serie> series) {
		this.series = series;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (areaId != null ? areaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Area)) {
            return false;
        }
        Area other = (Area) object;
        if ((this.areaId == null && other.areaId != null) || (this.areaId != null && !this.areaId.equals(other.areaId))) {
            return false;
        }
        return true;
    }


}
