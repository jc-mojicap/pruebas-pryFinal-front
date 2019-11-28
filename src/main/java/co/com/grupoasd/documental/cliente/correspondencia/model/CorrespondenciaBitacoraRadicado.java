/*
* Archivo: CorrespondenciaBitacoraRadicado.java
* Fecha creacion = 10/05/2017
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
import java.util.Date;

import co.com.grupoasd.documental.cliente.catalogo.model.Usuario;

public class CorrespondenciaBitacoraRadicado implements Serializable{

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * Identificador del radicado asociado.
     */
    private Long idRadicado;
    /**
     * Identificador del usuario.
     */
    private Integer idUsuario;
    /**
     * Usuario.
     */
    private Usuario usuario;
    /**
     * Fecha.
     */
    private Date fecha;
    /**
     * Campo afectado.
     */
    private String campo;
    /**
     * Valor anterior del campo.
     */
    private String valorAnterior;
    /**
     * Valor nuevo del campo.
     */
    private String valorNuevo;
    /**
     * Identificador del comentario asociado.
     */
    private Long idComentario;
    
    /**
     * Constructor.
     */
    public CorrespondenciaBitacoraRadicado() {
        
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the idRadicado
     */
    public Long getIdRadicado() {
        return idRadicado;
    }

    /**
     * @param idRadicado the idRadicado to set
     */
    public void setIdRadicado(Long idRadicado) {
        this.idRadicado = idRadicado;
    }

    /**
     * @return the idUsuario
     */
    public Integer getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the campo
     */
    public String getCampo() {
        return campo;
    }

    /**
     * @param campo the campo to set
     */
    public void setCampo(String campo) {
        this.campo = campo;
    }

    /**
     * @return the valorAnterior
     */
    public String getValorAnterior() {
        return valorAnterior;
    }

    /**
     * @param valorAnterior the valorAnterior to set
     */
    public void setValorAnterior(String valorAnterior) {
        this.valorAnterior = valorAnterior;
    }

    /**
     * @return the valorNuevo
     */
    public String getValorNuevo() {
        return valorNuevo;
    }

    /**
     * @param valorNuevo the valorNuevo to set
     */
    public void setValorNuevo(String valorNuevo) {
        this.valorNuevo = valorNuevo;
    }

    /**
     * @return the idComentario
     */
    public Long getIdComentario() {
        return idComentario;
    }

    /**
     * @param idComentario the idComentario to set
     */
    public void setIdComentario(Long idComentario) {
        this.idComentario = idComentario;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        CorrespondenciaBitacoraRadicado other = (CorrespondenciaBitacoraRadicado) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "CorrespondenciaBitacoraRadicado [" + (id != null ? "id=" + id + ", " : "")
                + (idRadicado != null ? "idRadicado=" + idRadicado + ", " : "")
                + (idUsuario != null ? "idUsuario=" + idUsuario + ", " : "")
                + (fecha != null ? "fecha=" + fecha + ", " : "") + (campo != null ? "campo=" + campo + ", " : "")
                + (valorAnterior != null ? "valorAnterior=" + valorAnterior + ", " : "")
                + (valorNuevo != null ? "valorNuevo=" + valorNuevo + ", " : "")
                + (idComentario != null ? "idComentario=" + idComentario : "") + "]";
    }
}
