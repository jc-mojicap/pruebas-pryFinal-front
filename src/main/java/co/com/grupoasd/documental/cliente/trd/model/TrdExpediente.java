/**
 * 
 */
package co.com.grupoasd.documental.cliente.trd.model;

import java.util.Date;

/**
 * Dto TrdDocumento
 * @author LuisaHernandez
 * @since Junio 28 de 2017
 */
public class TrdExpediente {
	


	  
    private Integer expedienteId;
  
    private String nombre;
 
    private Date fechaCrea;
   
    private int estado;
   
    private int usuarioModifica;
  
    private Date fechaModifica;
    
    private Integer trdSubserieId;
    

    private Integer subserieId;
    private String subserieNombre;
    
    private Integer trdSerieId;
    private Integer serieId;
    private String serieNombre;
    
    private Integer trdAreaId;
    private Integer areaId;
    private String areaNombre;
    
    private Integer trdId;
    private String trdNombre;
    
    private String estadoNombre;
    
    
    public TrdExpediente() {
    }

    public TrdExpediente(Integer expedienteId) {
        this.expedienteId = expedienteId;
    }

    public TrdExpediente(Integer expedienteId, String nombre, Date fechaCrea, int estado, int usuarioModifica, Date fechaModifica) {
        this.expedienteId = expedienteId;
        this.nombre = nombre;
        this.fechaCrea = fechaCrea;
        this.estado = estado;
        this.usuarioModifica = usuarioModifica;
        this.fechaModifica = fechaModifica;
    }

    public Integer getExpedienteId() {
        return expedienteId;
    }

    public void setExpedienteId(Integer expedienteId) {
        this.expedienteId = expedienteId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaCrea() {
        return fechaCrea;
    }

    public void setFechaCrea(Date fechaCrea) {
        this.fechaCrea = fechaCrea;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
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

	public Integer getTrdSubserieId() {
		return trdSubserieId;
	}

	public void setTrdSubserieId(Integer trdSubserieId) {
		this.trdSubserieId = trdSubserieId;
	}

	public Integer getSubserieId() {
		return subserieId;
	}

	public void setSubserieId(Integer subserieId) {
		this.subserieId = subserieId;
	}

	public String getSubserieNombre() {
		return subserieNombre;
	}

	public void setSubserieNombre(String subserieNombre) {
		this.subserieNombre = subserieNombre;
	}

	public Integer getTrdSerieId() {
		return trdSerieId;
	}

	public void setTrdSerieId(Integer trdSerieId) {
		this.trdSerieId = trdSerieId;
	}

	public Integer getSerieId() {
		return serieId;
	}

	public void setSerieId(Integer serieId) {
		this.serieId = serieId;
	}

	public String getSerieNombre() {
		return serieNombre;
	}

	public void setSerieNombre(String serieNombre) {
		this.serieNombre = serieNombre;
	}

	public Integer getTrdAreaId() {
		return trdAreaId;
	}

	public void setTrdAreaId(Integer trdAreaId) {
		this.trdAreaId = trdAreaId;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getAreaNombre() {
		return areaNombre;
	}

	public void setAreaNombre(String areaNombre) {
		this.areaNombre = areaNombre;
	}

	public Integer getTrdId() {
		return trdId;
	}

	public void setTrdId(Integer trdId) {
		this.trdId = trdId;
	}

	public String getTrdNombre() {
		return trdNombre;
	}

	public void setTrdNombre(String trdNombre) {
		this.trdNombre = trdNombre;
	}

	public String getEstadoNombre() {
		return estadoNombre;
	}

	public void setEstadoNombre(String estadoNombre) {
		this.estadoNombre = estadoNombre;
	}
	
	
    
	
}