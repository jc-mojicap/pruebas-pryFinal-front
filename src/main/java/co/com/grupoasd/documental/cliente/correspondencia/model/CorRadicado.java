/*
 * Archivo: CorRadicado.java
 * Fecha creacion: 24/03/2017
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
package co.com.grupoasd.documental.cliente.correspondencia.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import co.com.grupoasd.documental.cliente.catalogo.model.Area;
import co.com.grupoasd.documental.cliente.catalogo.model.Serie;
import co.com.grupoasd.documental.cliente.catalogo.model.Subserie;
import co.com.grupoasd.documental.cliente.catalogo.model.TipoDocumental;
import co.com.grupoasd.documental.cliente.catalogo.model.Usuario;

/**
 * Modelo de dominio radicado de correspondencia.
 * 
 * @author cestrada
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class CorRadicado implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Identificador del cor radicado.
     */
    private Long radicadoId;
    /**
     * Tipo radicación.
     */
    private Character tipoRadicacion;
    /**
     * Secuencia.
     */
    private Long secuencia;
    /**
     * Radicado.
     */
    private String radicado;
    /**
     * Fecha de radicación.
     */
    private Date fechaRadicacion;
    /**
     * Cantidad de folios.
     */
    private int cantidadFolios;
    /**
     * Asunto.
     */
    private String asunto;
    /**
     * Nombre del funcionario.
     */
    private String nombreFuncionario;
    /**
     * Número identificación del funcionario.
     */
    private String nroIdentificacionFunc;
    /**
     * Radicado externo.
     */
    private String radicadoExterno;
    /**
     * Número de guía.
     */
    private String numeroGuia;
    /**
     * Fecha de operación.
     */
    private Date fechaOperacion;
    /**
     * Contenido.
     */
    private int contenido;
    /**
     * Cantidad de embalaje.
     */
    private int cantidadEmbalaje;
    /**
     * Requiere respuesta.
     */
    private boolean requiereRespuesta;
    /**
     * Tiene anexos.
     */
    private boolean anexos;
    /**
     * Fecha de vencimiento de la respuesta.
     */
    private Date fechaVencimientoRta;

    /**
     * Identificador de la subserie.
     */
    private Integer subserieId;
    /**
     * Nombre de la subserie.
     */
    private String nombreSubserie;
    /**
     * Identificador de la serie.
     */
    private Integer serieId;
    /**
     * Nombre de la serie.
     */
    private String nombreSerie;
    /**
     * Identificador del área.
     */
    private Integer areaId;
    /**
     * Nombre del área.
     */
    private String nombreArea;
    /**
     * Identificador del usuario modifica.
     */
    private Integer usuarioModifica;
    /**
     * Fecha modifica.
     */
    private Date fechaModifica;
    /**
     * Identificador del canal.
     */
    private Integer canalId;
    /**
     * Nombre del canal.
     */
    private String nombreCanal;
    /**
     * Identificador del radicado asociado.
     */
    private Long radicadoAsociadoId;
    /**
     * Radicado asociado.
     */
    private String radicadoAsociado;
    /**
     * Identificador del tipo de embalaje.
     */
    private Integer tipoEmbalajeId;
    /**
     * Nombre del tipo de embalaje.
     */
    private String nombreTipoEmbalaje;
    /**
     * Identificador del estado del radicado.
     */
    private Integer estadoId;
    /**
     * Nombre del estado.
     */
    private String nombreEstado;
    /**
     * Identificador de la transportadora.
     */
    private Integer transportadoraId;
    /**
     * Nombre de la transportadora.
     */
    private String nombreTransportadora;
    /**
     * Identificador de usuario radicado.
     */
    private Integer usuarioRadicacionId;
    /**
     * Nombre del usuario radicado.
     */
    private String nombreUsuarioRadicacion;
    /**
     * Comentario del radicado.
     */
    private String comentario;
    /**
     * Lista de comentarios sobre el radicado.
     */
    private List<CorComentario> comentarios;
    /**
     * Remitente tercero.
     */
    private CorTercero terceroRemitente;
    /**
     * Remitente usuario.
     */
    private Usuario usuarioRemitente;
    /**
     * Lista de destinatarios si el radicado es de salida.
     */
    private List<CorTercero> tercerosDestinatarios;
    /**
     * Destinatario principal si el radicado es de salida.
     */
    private CorTercero terceroPrincipal;
    /**
     * Lista de destinatarios si el radicado es interno o de entrada.
     */
    private List<Usuario> usuariosDestinatarios;
    /**
     * Destinatario principal si el radicado es interno o de entrada.
     */
    private Usuario usuarioPrincipal;

    /**
     * Identificador de la empresa.
     */
    private Integer empresaId;

    /**
     * Adjuntos relacionadaos al radicado
     */
    private List<CorAdjunto> corAdjuntos;

    /**
     * Radicado asociado
     */
    private CorRadicado corRadicadoAsociado;

    /**
     * Objeto Area
     */
    private Area area;

    /**
     * Objeto Serie
     */
    private Serie serie;

    /**
     * Objeto SubSerie
     */
    private Subserie subserie;

    /**
     * Tipos documentales
     */
    private List<TipoDocumental> tipoDocumentals;

    /**
     * Constructor
     */
    public CorRadicado() {

    }

    /**
     * getRadicadoId.
     * 
     * @return Long
     */
    public Long getRadicadoId() {
        return radicadoId;
    }

    /**
     * setRadicadoId.
     * 
     * @param setRadicadoId
     *            Identificador del radicado.
     */
    public void setRadicadoId(Long radicadoId) {
        this.radicadoId = radicadoId;
    }

    /**
     * getTipoRadicacion.
     * 
     * @return Character
     */
    public Character getTipoRadicacion() {
        return tipoRadicacion;
    }

    /**
     * setTipoRadicacion.
     * 
     * @param tipoRadicacion
     *            Tipo de radicado.
     */
    public void setTipoRadicacion(Character tipoRadicacion) {
        this.tipoRadicacion = tipoRadicacion;
    }

    /**
     * getSecuencia.
     * 
     * @return Long
     */
    public Long getSecuencia() {
        return secuencia;
    }

    /**
     * setSecuencia.
     * 
     * @param secuencia
     *            Secuencia.
     */
    public void setSecuencia(Long secuencia) {
        this.secuencia = secuencia;
    }

    /**
     * getRadicado.
     * 
     * @return String
     */
    public String getRadicado() {
        return radicado;
    }

    /**
     * setRadicado.
     * 
     * @param radicado
     *            Radicado.
     */
    public void setRadicado(String radicado) {
        this.radicado = radicado;
    }

    /**
     * getFechaRadicacion.
     * 
     * @return Date
     */
    public Date getFechaRadicacion() {
        return fechaRadicacion;
    }

    /**
     * setFechaRadicacion.
     * 
     * @param fechaRadicacion
     *            Fecha de radicación.
     */
    public void setFechaRadicacion(Date fechaRadicacion) {
        this.fechaRadicacion = fechaRadicacion;
    }

    /**
     * getCantidadFolios.
     * 
     * @return int
     */
    public int getCantidadFolios() {
        return cantidadFolios;
    }

    /**
     * setCantidadFolios.
     * 
     * @param cantidadFolios
     *            Cantidad de folios.
     */
    public void setCantidadFolios(int cantidadFolios) {
        this.cantidadFolios = cantidadFolios;
    }

    /**
     * getAsunto.
     * 
     * @return String
     */
    public String getAsunto() {
        return asunto;
    }

    /**
     * setAsunto.
     * 
     * @param asunto
     *            Asunto.
     */
    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    /**
     * getNombreFuncionario.
     * 
     * @return String
     */
    public String getNombreFuncionario() {
        return nombreFuncionario;
    }

    /**
     * setNombreFuncionario.
     * 
     * @param nombreFuncionario
     *            Nombre del funcionario.
     */
    public void setNombreFuncionario(String nombreFuncionario) {
        this.nombreFuncionario = nombreFuncionario;
    }

    /**
     * getNroIdentificacionFunc.
     * 
     * @return String
     */
    public String getNroIdentificacionFunc() {
        return nroIdentificacionFunc;
    }

    /**
     * setNroIdentificacionFunc.
     * 
     * @param nroIdentificacionFunc
     *            Número de identificación del funcionario.
     */
    public void setNroIdentificacionFunc(String nroIdentificacionFunc) {
        this.nroIdentificacionFunc = nroIdentificacionFunc;
    }

    /**
     * getRadicadoExterno.
     * 
     * @return String
     */
    public String getRadicadoExterno() {
        return radicadoExterno;
    }

    /**
     * setRadicadoExterno.
     * 
     * @param radicadoExterno
     *            Radicado externo.
     */
    public void setRadicadoExterno(String radicadoExterno) {
        this.radicadoExterno = radicadoExterno;
    }

    /**
     * getNumeroGuia.
     * 
     * @return BigInteger
     */
    public String getNumeroGuia() {
        return numeroGuia;
    }

    /**
     * setNumeroGuia.
     * 
     * @param numeroGuia
     *            Número de guía.
     */
    public void setNumeroGuia(String numeroGuia) {
        this.numeroGuia = numeroGuia;
    }

    /**
     * getFechaOperacion.
     * 
     * @return Date
     */
    public Date getFechaOperacion() {
        return fechaOperacion;
    }

    /**
     * setFechaOperacion.
     * 
     * @param fechaOperacion
     *            Fecha de operación.
     */
    public void setFechaOperacion(Date fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }

    /**
     * getContenido
     * 
     * @return int
     */
    public int getContenido() {
        return contenido;
    }

    /**
     * setContenido.
     * 
     * @param contenido
     *            Contenido.
     */
    public void setContenido(int contenido) {
        this.contenido = contenido;
    }

    /**
     * getCantidadEmbalaje.
     * 
     * @return int
     */
    public int getCantidadEmbalaje() {
        return cantidadEmbalaje;
    }

    /**
     * setCantidadEmbalaje.
     * 
     * @param cantidadEmbalaje
     *            Cantidad de embalaje.
     */
    public void setCantidadEmbalaje(int cantidadEmbalaje) {
        this.cantidadEmbalaje = cantidadEmbalaje;
    }

    /**
     * isRequiereRespuesta.
     * 
     * @return boolean
     */
    public boolean isRequiereRespuesta() {
        return requiereRespuesta;
    }

    /**
     * setRequiereRespuesta.
     * 
     * @param requiereRespuesta
     *            Requiere respuesta.
     */
    public void setRequiereRespuesta(boolean requiereRespuesta) {
        this.requiereRespuesta = requiereRespuesta;
    }

    /**
     * @return the anexos
     */
    public boolean isAnexos() {
        return anexos;
    }

    /**
     * @param anexos the anexos to set
     */
    public void setAnexos(boolean anexos) {
        this.anexos = anexos;
    }

    /**
     * getFechaVencimientoRta.
     * 
     * @return Date
     */
    public Date getFechaVencimientoRta() {
        return fechaVencimientoRta;
    }

    /**
     * setFechaVencimientoRta.
     * 
     * @param fechaVencimientoRta
     *            Fecha vencimiento de la respuesta.
     */
    public void setFechaVencimientoRta(Date fechaVencimientoRta) {
        this.fechaVencimientoRta = fechaVencimientoRta;
    }

    /**
     * @return the subserieId
     */
    public Integer getSubserieId() {
        return subserieId;
    }

    /**
     * @param subserieId
     *            the subserieId to set
     */
    public void setSubserieId(Integer subserieId) {
        this.subserieId = subserieId;
    }

    /**
     * @return the nombreSubserie
     */
    public String getNombreSubserie() {
        return nombreSubserie;
    }

    /**
     * @param nombreSubserie
     *            the nombreSubserie to set
     */
    public void setNombreSubserie(String nombreSubserie) {
        this.nombreSubserie = nombreSubserie;
    }

    /**
     * @return the serieId
     */
    public Integer getSerieId() {
        return serieId;
    }

    /**
     * @param serieId
     *            the serieId to set
     */
    public void setSerieId(Integer serieId) {
        this.serieId = serieId;
    }
    
    /**
     * @return the nombreSerie
     */
    public String getNombreSerie() {
        return nombreSerie;
    }

    /**
     * @param nombreSerie
     *            the nombreSerie to set
     */
    public void setNombreSerie(String nombreSerie) {
        this.nombreSerie = nombreSerie;
    }

    /**
     * @return the areaId
     */
    public Integer getAreaId() {
        return areaId;
    }

    /**
     * @param areaId
     *            the areaId to set
     */
    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }
    
    /**
     * @return the nombreArea
     */
    public String getNombreArea() {
        return nombreArea;
    }

    /**
     * @param nombreArea
     *            the nombreArea to set
     */
    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }

    /**
<<<<<<< HEAD
     * getUsuarioModifica. 
     * 
     * @return Integer 
=======
     * getUsuarioModifica.
     * 
     * @return Integer
>>>>>>> 2346a8a99e77b23afd5e041727f320766028c220
     */
    public Integer getUsuarioModifica() {
        return usuarioModifica;
    }

    /**
<<<<<<< HEAD
     * setUsuarioModifica.
     * 
     * @param usuarioModifica
     *            Usuario que modifica.
=======
     * @param usuarioModifica
     *            the usuarioModifica to set
>>>>>>> 2346a8a99e77b23afd5e041727f320766028c220
     */
    public void setUsuarioModifica(Integer usuarioModifica) {
        this.usuarioModifica = usuarioModifica;
    }

    /**
     * getFechaModifica.
     * 
     * @return Date
     */
    public Date getFechaModifica() {
        return fechaModifica;
    }

    /**
<<<<<<< HEAD
     * setFechaModifica.
     * 
     * @param fechaModifica
     *            Fecha de la modificación.
=======
     * @param fechaModifica
     *            the fechaModifica to set
>>>>>>> 2346a8a99e77b23afd5e041727f320766028c220
     */
    public void setFechaModifica(Date fechaModifica) {
        this.fechaModifica = fechaModifica;
    }

    /**
     * getCanalId. 
     * 
     * @return Integer 
     */
    public Integer getCanalId() {
        return canalId;
    }

    /**
<<<<<<< HEAD
     * setCanalId.
     * 
     * @param canalId
     *            Identificador del canal.
=======
     * @param canalId
     *            the canalId to set
>>>>>>> 2346a8a99e77b23afd5e041727f320766028c220
     */
    public void setCanalId(Integer canalId) {
        this.canalId = canalId;
    }

    /**
     * getNombreCanal.
     * 
     * @return String
     */
    public String getNombreCanal() {
        return nombreCanal;
    }

    /**
<<<<<<< HEAD
     * setNombreCanal.
     * 
     * @param nombreCanal
     *            Nombre del canal.
=======
     * @param nombreCanal
     *            the nombreCanal to set
>>>>>>> 2346a8a99e77b23afd5e041727f320766028c220
     */
    public void setNombreCanal(String nombreCanal) {
        this.nombreCanal = nombreCanal;
    }

    /**
     * getRadicadoAsociadoId.
     * 
     * @return Long
     */
    public Long getRadicadoAsociadoId() {
        return radicadoAsociadoId;
    }

    /**
<<<<<<< HEAD
     * setRadicadoAsociadoId.
     * 
     * @param radicadoAsociadoId
     *            Identificador del radicado asociado.
=======
     * @param radicadoAsociadoId
     *            the radicadoAsociadoId to set
>>>>>>> 2346a8a99e77b23afd5e041727f320766028c220
     */
    public void setRadicadoAsociadoId(Long radicadoAsociadoId) {
        this.radicadoAsociadoId = radicadoAsociadoId;
    }

    /**
     * getRadicadoAsociado
     * 
     * @return String
     */
    public String getRadicadoAsociado() {
        return radicadoAsociado;
    }

    /**
<<<<<<< HEAD
     * setRadicadoAsociado.
     * 
     * @param radicadoAsociado
     *            Radicado asociado.
=======
     * @param radicadoAsociado
     *            the radicadoAsociado to set
>>>>>>> 2346a8a99e77b23afd5e041727f320766028c220
     */
    public void setRadicadoAsociado(String radicadoAsociado) {
        this.radicadoAsociado = radicadoAsociado;
    }

    /**
     * getTipoEmbalajeId.
     * 
     * @return Integer
     */
    public Integer getTipoEmbalajeId() {
        return tipoEmbalajeId;
    }

    /**
<<<<<<< HEAD
     * setTipoEmbalajeId.
     * 
     * @param tipoEmbalajeId
     *            Identificador del tipo de embalaje.
=======
     * @param tipoEmbalajeId
     *            the tipoEmbalajeId to set
>>>>>>> 2346a8a99e77b23afd5e041727f320766028c220
     */
    public void setTipoEmbalajeId(Integer tipoEmbalajeId) {
        this.tipoEmbalajeId = tipoEmbalajeId;
    }

    /**
     * getNombreTipoEmbalaje.
     * 
     * @return String
     */
    public String getNombreTipoEmbalaje() {
        return nombreTipoEmbalaje;
    }

    /**
<<<<<<< HEAD
     * setNombreTipoEmbalaje.
     * 
     * @param nombreTipoEmbalaje
     *            Nombre del tipo de embalaje.
=======
     * @param nombreTipoEmbalaje
     *            the nombreTipoEmbalaje to set
>>>>>>> 2346a8a99e77b23afd5e041727f320766028c220
     */
    public void setNombreTipoEmbalaje(String nombreTipoEmbalaje) {
        this.nombreTipoEmbalaje = nombreTipoEmbalaje;
    }

    /**
     * getEstadoId.
     * 
     * @return Integer
     */
    public Integer getEstadoId() {
        return estadoId;
    }

    /**
<<<<<<< HEAD
     * setEstadoId.
     * 
     * @param estadoId
     *            Identificador del estado radicado.
=======
     * @param estadoId
     *            the estadoId to set
>>>>>>> 2346a8a99e77b23afd5e041727f320766028c220
     */
    public void setEstadoId(Integer estadoId) {
        this.estadoId = estadoId;
    }

    /**
     * getNombreEstado.
     * 
     * @return String
     */
    public String getNombreEstado() {
        return nombreEstado;
    }

    /**
<<<<<<< HEAD
     * setNombreEstado.
     * 
     * @param nombreEstado
     *            Nombre del estado radicado.
=======
     * @param nombreEstado
     *            the nombreEstado to set
>>>>>>> 2346a8a99e77b23afd5e041727f320766028c220
     */
    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    /**
     * getTransportadoraId.
     * 
     * @return Integer
     */
    public Integer getTransportadoraId() {
        return transportadoraId;
    }

    /**
<<<<<<< HEAD
     * setTransportadoraId.
     * 
     * @param transportadoraId
     *            Identificador de la transportadora.
=======
     * @param transportadoraId
     *            the transportadoraId to set
>>>>>>> 2346a8a99e77b23afd5e041727f320766028c220
     */
    public void setTransportadoraId(Integer transportadoraId) {
        this.transportadoraId = transportadoraId;
    }

    /**
     * getNombreTransportadora
     * 
     * @return String.
     */
    public String getNombreTransportadora() {
        return nombreTransportadora;
    }

    /**
<<<<<<< HEAD
     * setNombreTransportadora.
     * 
     * @param nombreTransportadora
     *            Nombre de la transportadora.
=======
     * @param nombreTransportadora
     *            the nombreTransportadora to set
>>>>>>> 2346a8a99e77b23afd5e041727f320766028c220
     */
    public void setNombreTransportadora(String nombreTransportadora) {
        this.nombreTransportadora = nombreTransportadora;
    }

    /**
     * getUsuarioRadicacionId.
     * 
     * @return Long
     */
    public Integer getUsuarioRadicacionId() {
        return usuarioRadicacionId;
    }

    /**
<<<<<<< HEAD
     * setUsuarioRadicacionId.
     * 
     * @param usuarioRadicacionId
     *            Identificador del usuario radicado.
=======
     * @param usuarioRadicacionId
     *            the usuarioRadicacionId to set
>>>>>>> 2346a8a99e77b23afd5e041727f320766028c220
     */
    public void setUsuarioRadicacionId(Integer usuarioRadicacionId) {
        this.usuarioRadicacionId = usuarioRadicacionId;
    }

    /**
     * getNombreUsuarioRadicacion.
     * 
     * @return String
     */
    public String getNombreUsuarioRadicacion() {
        return nombreUsuarioRadicacion;
    }

    /**
<<<<<<< HEAD
     * setNombreUsuarioRadicacion.
     * 
     * @param nombreUsuarioRadicacion
     *            Nombre del usuario radicado.
=======
     * @param nombreUsuarioRadicacion
     *            the nombreUsuarioRadicacion to set
>>>>>>> 2346a8a99e77b23afd5e041727f320766028c220
     */
    public void setNombreUsuarioRadicacion(String nombreUsuarioRadicacion) {
        this.nombreUsuarioRadicacion = nombreUsuarioRadicacion;
    }

    /**
     * @return the comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * @param comentario
     *            the comentario to set
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**
     * @return the comentarios
     */
    public List<CorComentario> getComentarios() {
        return comentarios;
    }

    /**
     * @param comentarios
     *            the comentarios to set
     */
    public void setComentarios(List<CorComentario> comentarios) {
        this.comentarios = comentarios;
    }

    /**
     * @return the terceroRemitente
     */
    public CorTercero getTerceroRemitente() {
        return terceroRemitente;
    }

    /**
     * @param terceroRemitente
     *            the terceroRemitente to set
     */
    public void setTerceroRemitente(CorTercero terceroRemitente) {
        this.terceroRemitente = terceroRemitente;
    }

    /**
     * @return the usuarioRemitente
     */
    public Usuario getUsuarioRemitente() {
        return usuarioRemitente;
    }

    /**
     * @param usuarioRemitente
     *            the usuarioRemitente to set
     */
    public void setUsuarioRemitente(Usuario usuarioRemitente) {
        this.usuarioRemitente = usuarioRemitente;
    }

    /**
     * @return the terceros
     */
    public List<CorTercero> getTercerosDestinatarios() {
        return tercerosDestinatarios;
    }

    /**
<<<<<<< HEAD
     * @param terceros
     *            the terceros to set
=======
     * @param tercerosDestinatarios
     *            the tercerosDestinatarios to set
>>>>>>> 2346a8a99e77b23afd5e041727f320766028c220
     */
    public void setTercerosDestinatarios(List<CorTercero> tercerosDestinatarios) {
        this.tercerosDestinatarios = tercerosDestinatarios;
    }

    /**
     * @return the terceroPrincipal
     */
    public CorTercero getTerceroPrincipal() {
        return terceroPrincipal;
    }

    /**
     * @param terceroPrincipal
     *            the terceroPrincipal to set
     */
    public void setTerceroPrincipal(CorTercero terceroPrincipal) {
        this.terceroPrincipal = terceroPrincipal;
    }

    /**
     * @return the usuariosDestinatarios
     */
    public List<Usuario> getUsuariosDestinatarios() {
        return usuariosDestinatarios;
    }

    /**
     * @param usuariosDestinatarios
     *            the usuariosDestinatarios to set
     */
    public void setUsuariosDestinatarios(List<Usuario> usuariosDestinatarios) {
        this.usuariosDestinatarios = usuariosDestinatarios;
    }

    /**
     * @return the usuarioPrincipal
     */
    public Usuario getUsuarioPrincipal() {
        return usuarioPrincipal;
    }

    /**
     * @param usuarioPrincipal
     *            the usuarioPrincipal to set
     */
    public void setUsuarioPrincipal(Usuario usuarioPrincipal) {
        this.usuarioPrincipal = usuarioPrincipal;
    }

    /**
     * @return the empresaId
     */
    public Integer getEmpresaId() {
        return empresaId;
    }

    /**
     * @param empresaId
     *            the empresaId to set
     */
    public void setEmpresaId(Integer empresaId) {
        this.empresaId = empresaId;
    }

    /**
     * Obtiene la lista de los adjuntos por radicado
     * 
     * @return
     */
    public List<CorAdjunto> getCorAdjuntos() {
        return this.corAdjuntos;
    }

    /**
     * Setea el valor de los ajuntos a la variable de objeto
     * 
     * @param corAdjuntos
     *            Lista de CorAdjuntosDto
     */
    public void setCorAdjuntos(List<CorAdjunto> corAdjuntos) {
        this.corAdjuntos = corAdjuntos;
    }

    /**
     * Obtiene el radicado asociado
     * 
     * @return
     */
    public CorRadicado getCorRadicadoAsociado() {
        return this.corRadicadoAsociado;
    }

    /**
     * Setea el valor de CorRadicado Asociado
     * 
     * @param corAdjuntos
     *            Lista de CorAdjuntosDto
     */
    public void setCorRadicadoAsociado(CorRadicado corRadicadoAsociado) {
        this.corRadicadoAsociado = corRadicadoAsociado;
    }
    
    /**
     * getAreaDto
     * 
     * @return AreaDto
     */
    public Area getArea() {
        return area;
    }

    /**
     * setAreaDto
     * 
     * @param areaDto
     *            Objeto AreaDto
     */
    public void setArea(Area area) {
        this.area = area;
    }

    /**
     * getSerieDto
     * 
     * @return SerieDto
     */
    public Serie getSerie() {
        return serie;
    }

    /**
     * setSerieDto
     * 
     * @param serieDto
     *            Objeto SerieDto
     */
    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    /**
     * getSubserieDto
     * 
     * @return SubserieDto
     */
    public Subserie getSubserie() {
        return subserie;
    }

    /**
     * setSubserieDto
     * 
     * @param subserieDto
     *            Objeto SubserieDto
     */
    public void setSubserie(Subserie subserie) {
        this.subserie = subserie;
    }

    /**
     * getTipoDocumentalDtos
     * 
     * @return Lista de TipoDocumentalDto
     */
    public List<TipoDocumental> getTipoDocumentals() {
        return tipoDocumentals;
    }

    /**
     * setTipoDocumentalDtos
     * 
     * @param tipoDocumentalDtos
     *            Lista de TipoDocumentalDto
     */
    public void setTipoDocumentals(List<TipoDocumental> tipoDocumentals) {
        this.tipoDocumentals = tipoDocumentals;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (radicadoId != null ? radicadoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are
        // not set
        if (!(object instanceof CorRadicado)) {
            return false;
        }
        CorRadicado other = (CorRadicado) object;
        if ((this.radicadoId == null && other.radicadoId != null)
                || (this.radicadoId != null && !this.radicadoId.equals(other.radicadoId))) {
            return false;
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "CorRadicado [" + (radicadoId != null ? "radicadoId=" + radicadoId + ", " : "")
                + (tipoRadicacion != null ? "tipoRadicacion=" + tipoRadicacion + ", " : "")
                + (asunto != null ? "asunto=" + asunto + ", " : "")
                + (nombreCanal != null ? "nombreCanal=" + nombreCanal + ", " : "")
                + (nombreEstado != null ? "nombreEstado=" + nombreEstado + ", " : "")
                + (usuarioRadicacionId != null ? "usuarioRadicacionId=" + usuarioRadicacionId + ", " : "")
                + (nombreUsuarioRadicacion != null ? "nombreUsuarioRadicacion=" + nombreUsuarioRadicacion : "") + "]";
    }

}
