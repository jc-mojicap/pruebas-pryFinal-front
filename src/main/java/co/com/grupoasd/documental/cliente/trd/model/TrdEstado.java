/**
 * 
 */
package co.com.grupoasd.documental.cliente.trd.model;

/**
 * Dto TrdEstado
 * 
 * @author alopez
 * @since Junio 20 de 2017
 */
public class TrdEstado {

	private Integer estadoId;

	private String nombre;

	public TrdEstado() {
	}

	public TrdEstado(Integer estadoId) {
		this.estadoId = estadoId;
	}

	public TrdEstado(Integer estadoId, String nombre) {
		this.estadoId = estadoId;
		this.nombre = nombre;
	}

	public Integer getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(Integer estadoId) {
		this.estadoId = estadoId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
