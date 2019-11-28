/*
* Archivo: CorComentario.java
* Fecha creacion = 31/03/2017
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

public class CorComentario implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Identificador del comentario.
     */
    private Long comentarioId;
    
    /**
     * Fecha del comentario.
     */
    private Date fecha;
    
    /**
     * Contenido del comentario.
     */
    private String comentario;
    
    /**
     * Identificador del estado del comentario.
     */
    private Integer estadoRadicadoId;
    
    /**
     * Nombre del estado del comentario.
     */
    private String estadoRadicadoNombre;
    
    /**
     * Identificador del radicado al cual está asociado el comentario.
     */
    private Long radicadoId;
    
    /**
     * Identificador del usuario.
     */
    private Integer usuarioId;

    /**
     * primer nombre usuario.
     */
    private String primerNombreUsuario;

    /**
     * segundo nombre usuario.
     */
    private String segundoNombreUsuario;

    /**
     * primer apellido usuario.
     */
    private String primerApellidoUsuario;

    /**
     * segundo apellido usuario.
     */
    private String segundoApellidoUsuario;

    /**
     * @return the comentarioId
     */
    public Long getComentarioId() {
        return comentarioId;
    }

    /**
     * @param comentarioId the comentarioId to set
     */
    public void setComentarioId(Long comentarioId) {
        this.comentarioId = comentarioId;
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
     * @return the comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * @param comentario the comentario to set
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**
     * @return the estadoRadicadoId
     */
    public Integer getEstadoRadicadoId() {
        return estadoRadicadoId;
    }

    /**
     * @param estadoRadicadoId the estadoRadicadoId to set
     */
    public void setEstadoRadicadoId(Integer estadoRadicadoId) {
        this.estadoRadicadoId = estadoRadicadoId;
    }

    /**
     * @return the estadoRadicadoNombre
     */
    public String getEstadoRadicadoNombre() {
        return estadoRadicadoNombre;
    }

    /**
     * @param estadoRadicadoNombre the estadoRadicadoNombre to set
     */
    public void setEstadoRadicadoNombre(String estadoRadicadoNombre) {
        this.estadoRadicadoNombre = estadoRadicadoNombre;
    }

    /**
     * @return the radicadoId
     */
    public Long getRadicadoId() {
        return radicadoId;
    }

    /**
     * @param radicadoId the radicadoId to set
     */
    public void setRadicadoId(Long radicadoId) {
        this.radicadoId = radicadoId;
    }

    /**
     * @return the usuarioId
     */
    public Integer getUsuarioId() {
        return usuarioId;
    }

    /**
     * @param usuarioId the usuarioId to set
     */
    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }
    
    /**
     * @return the primerNombreUsuario
     */
    public String getPrimerNombreUsuario() {
        return primerNombreUsuario;
    }

    /**
     * @param primerNombreUsuario the primerNombreUsuario to set
     */
    public void setPrimerNombreUsuario(String primerNombreUsuario) {
        this.primerNombreUsuario = primerNombreUsuario;
    }

    /**
     * @return the segundoNombreUsuario
     */
    public String getSegundoNombreUsuario() {
        return segundoNombreUsuario;
    }

    /**
     * @param segundoNombreUsuario the segundoNombreUsuario to set
     */
    public void setSegundoNombreUsuario(String segundoNombreUsuario) {
        this.segundoNombreUsuario = segundoNombreUsuario;
    }

    /**
     * @return the primerApellidoUsuario
     */
    public String getPrimerApellidoUsuario() {
        return primerApellidoUsuario;
    }

    /**
     * @param primerApellidoUsuario the primerApellidoUsuario to set
     */
    public void setPrimerApellidoUsuario(String primerApellidoUsuario) {
        this.primerApellidoUsuario = primerApellidoUsuario;
    }

    /**
     * @return the segundoApellidoUsuario
     */
    public String getSegundoApellidoUsuario() {
        return segundoApellidoUsuario;
    }

    /**
     * @param segundoApellidoUsuario the segundoApellidoUsuario to set
     */
    public void setSegundoApellidoUsuario(String segundoApellidoUsuario) {
        this.segundoApellidoUsuario = segundoApellidoUsuario;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((comentarioId == null) ? 0 : comentarioId.hashCode());
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
        CorComentario other = (CorComentario) obj;
        if (comentarioId == null) {
            if (other.comentarioId != null)
                return false;
        } else if (!comentarioId.equals(other.comentarioId))
            return false;
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "CorComentario [" + (comentarioId != null ? "comentarioId=" + comentarioId + ", " : "")
                + (comentario != null ? "comentario=" + comentario + ", " : "")
                + (radicadoId != null ? "radicadoId=" + radicadoId : "") + "]";
    }
    
}
