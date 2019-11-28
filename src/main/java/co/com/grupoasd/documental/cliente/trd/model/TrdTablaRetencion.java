/**
 * 
 */
package co.com.grupoasd.documental.cliente.trd.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import co.com.grupoasd.documental.cliente.catalogo.model.Empresa;

/**
 * Dto TrdTablaRetencionDto
 * @author alopez
 * @since Junio 20 de 2017
 */
public class TrdTablaRetencion implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer trdId;
   
    private String nombre;
   
    private String codigo;
  
    private boolean vigente;
  
    private Date fechaCreacion;
   
    private Date fechaGeneracion;
   
    private int usuarioModifica;
   
    private Date fechaModifica;
      
    private Empresa empresaId;
   
    private TrdEstado estado;
    
    private List<TrdArea> areaList;
    
    private int totalExpendientes; 

    /**
     * Constructor
     */
	public TrdTablaRetencion() {

    }

    public TrdTablaRetencion(Integer trdId) {
        this.trdId = trdId;
    }

    public TrdTablaRetencion(Integer trdId, String nombre, boolean vigente, int usuarioModifica, Date fechaModifica) {
        this.trdId = trdId;
        this.nombre = nombre;
        this.vigente = vigente;
        this.usuarioModifica = usuarioModifica;
        this.fechaModifica = fechaModifica;
    }

    public Integer getTrdId() {
        return trdId;
    }

    public void setTrdId(Integer trdId) {
        this.trdId = trdId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public boolean getVigente() {
        return vigente;
    }

    public void setVigente(boolean vigente) {
        this.vigente = vigente;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(Date fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public int getUsuarioModifica() {
        return usuarioModifica;
    }

    public void setUsuarioModifica(int usuarioModifica) {
        this.usuarioModifica = usuarioModifica;
    }

    public Date getFechaModifica() {
        return fechaModifica;
    }

    public void setFechaModifica(Date fechaModifica) {
        this.fechaModifica = fechaModifica;
    }

    public Empresa getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Empresa empresaId) {
        this.empresaId = empresaId;
    }

    public TrdEstado getEstado() {
        return estado;
    }

    public void setEstado(TrdEstado estado) {
        this.estado = estado;
    }

    /**
   	 * @return the totalExpendientes
   	 */
   	public int getTotalExpendientes() {
   		return totalExpendientes;
   	}

   	/**
   	 * @param totalExpendientes the totalExpendientes to set
   	 */
   	public void setTotalExpendientes(int totalExpendientes) {
   		this.totalExpendientes = totalExpendientes;
   	}

	/**
	 * @return the areaList
	 */
	public List<TrdArea> getAreaList() {
		return areaList;
	}

	/**
	 * @param areaList the areaList to set
	 */
	public void setAreaList(List<TrdArea> areaList) {
		this.areaList = areaList;
	}
}
