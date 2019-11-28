/*
 * Archivo: RadicadoDto.java
 * Fecha creacion: 29/03/2017
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
package co.com.grupoasd.documental.presentacion.service.correspondencia.dto;

import java.io.Serializable;
import java.util.Date;

import co.com.grupoasd.documental.presentacion.comun.dto.Paginacion;

/**
 * DTO Consulta radicados
 * 
 * @author cestrada
 *
 */
public class RadicadoDto extends Paginacion implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Identificador del radicado.
     */
    private Long radicadoId;

    /**
     * Radicado.
     */
    private String radicado;
    /**
     * Tipo radicado.
     */
    private Character tipoRadicacion;
    /**
     * Nombre del tipo radicado.
     */
    private String nombreTipoRadicacion;
    /**
     * Identificador del estado.
     */
    private Integer estadoId;
    /**
     * Nombre del estado.
     */
    private String nombreEstado;
    /**
     * Identificador del área.
     */
    private Integer areaId;
    /**
     * Nombre del área.
     */
    private String nombreArea;
    /**
     * Identificador de la serie.
     */
    private Integer serieId;
    /**
     * Nombre de la serie.
     */
    private String nombreSerie;
    /**
     * Identificador de la subserie.
     */
    private Integer subserieId;
    /**
     * Nombre de la subserie.
     */
    private String nombreSubserie;
    /**
     * Identificador del tipo documental.
     */
    private Integer tipoDocumentalId;
//    /**
//     * Nombre del tipo documental.
//     */
//    private String nombreTipoDocumental;
    /**
     * Identificador del radicado asociado.
     */
    private Long radicadoAsociadoId;
    /**
     * Radicado asociado.
     */
    private String radicadoAsociado;
    /**
     * Radicado externo.
     */
    private String radicadoExterno;
    /**
     * Identificador del usuario asignado.
     */
    private Integer asignado;
    /**
     * Fechan inicial del radicado.
     */
    private Date fechaRadicado;
    /**
     * Fecha final del radicado.
     */
    private Date fechaFinal;
    /**
     * Fecha vencimiento del radicado.
     */
    private Date fechaVencimiento;
    /**
     * Identificador del canal.
     */
    private Integer canalId;
    /**
     * Nombre del canal.
     */
    private String nombreCanal;
    /**
     * Identificador del tercero.
     */
    private Integer remitente;
    /**
     * Radicados anulados.
     */
    private boolean anulado;
    /**
     * Radicados requiere respuesta.
     */
    private boolean requiereRespuesta;

    /**
     * Asunto.
     */
    private String asunto;

    /**
     * Cantidad de folios.
     */
    private int cantidadFolios;

    /**
     * Cantidad de anexos
     */
    private boolean anexos;

    /**
     * asignados.
     */
    private String asignados;

    /**
     * destinatarios.
     */
    private String destinatarios;

    /**
     * remitenteRadicado.
     */
    private String remitenteRadicado;

    /**
     * Constructor.
     */
    public RadicadoDto() {

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
     * @param radicadoId
     *            Identificador del radicado.
     */
    public void setRadicadoId(Long radicadoId) {
        this.radicadoId = radicadoId;
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
     * @param tipoRadicado
     *            Tipo radicado.
     */
    public void setTipoRadicacion(Character tipoRadicacion) {
        this.tipoRadicacion = tipoRadicacion;
    }

    /**
     * getNombreTipoRadicacion.
     * 
     * @return String
     */
    public String getNombreTipoRadicacion() {
        return nombreTipoRadicacion;
    }

    /**
     * setNombreTipoRadicacion.
     * 
     * @param nombreTipoRadicacion
     *            Nombre del tipo de radicación.
     */
    public void setNombreTipoRadicacion(String nombreTipoRadicacion) {
        this.nombreTipoRadicacion = nombreTipoRadicacion;
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
     * setEstadoId.
     * 
     * @param estadoId
     *            Identificador del estado.
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
     * setNombreEstado.
     * 
     * @param nombreEstado
     *            Nombre del estado.
     */
    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    /**
     * getAreaId.
     * 
     * @return Integer
     */
    public Integer getAreaId() {
        return areaId;
    }

    /**
     * setAreaId.
     * 
     * @param areaId
     *            Identificador del área.
     */
    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    /**
     * getNombreArea.
     * 
     * @return String
     */
    public String getNombreArea() {
        return nombreArea;
    }

    /**
     * setNombreArea.
     * 
     * @param nombreArea
     *            Nombre del área.
     */
    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }

    /**
     * getSerieId.
     * 
     * @return Integer
     */
    public Integer getSerieId() {
        return serieId;
    }

    /**
     * setSerieId.
     * 
     * @param serieId
     *            Identificador de la serie.
     */
    public void setSerieId(Integer serieId) {
        this.serieId = serieId;
    }

    /**
     * getNombreSerie.
     * 
     * @return String
     */
    public String getNombreSerie() {
        return nombreSerie;
    }

    /**
     * setNombreSerie.
     * 
     * @param nombreSerie
     *            Nombre de la serie.
     */
    public void setNombreSerie(String nombreSerie) {
        this.nombreSerie = nombreSerie;
    }

    /**
     * getSubserieId.
     * 
     * @return Integer
     */
    public Integer getSubserieId() {
        return subserieId;
    }

    /**
     * setSubserieId.
     * 
     * @param subserieId
     *            Identificador de la subserie.
     */
    public void setSubserieId(Integer subserieId) {
        this.subserieId = subserieId;
    }

    /**
     * getNombreSubserie.
     * 
     * @return String
     */
    public String getNombreSubserie() {
        return nombreSubserie;
    }

    /**
     * setNombreSubserie.
     * 
     * @param nombreSubserie
     *            Nombre de la subserie.
     */
    public void setNombreSubserie(String nombreSubserie) {
        this.nombreSubserie = nombreSubserie;
    }

    /**
     * getTipoDocumentalId.
     * 
     * @return Integer
     */
    public Integer getTipoDocumentalId() {
        return tipoDocumentalId;
    }

    /**
     * setTipoDocumentalId.
     * 
     * @param tipoDocumentalId
     *            Identificador del tipo documental.
     */
    public void setTipoDocumentalId(Integer tipoDocumentalId) {
        this.tipoDocumentalId = tipoDocumentalId;
    }

//    /**
//     * getNombreTipoDocumental.
//     * 
//     * @return String
//     */
//    public String getNombreTipoDocumental() {
//        return nombreTipoDocumental;
//    }
//
//    /**
//     * setNombreTipoDocumental.
//     * 
//     * @param nombreTipoDocumental
//     *            Nombre del tipo documental.
//     */
//    public void setNombreTipoDocumental(String nombreTipoDocumental) {
//        this.nombreTipoDocumental = nombreTipoDocumental;
//    }

    /**
     * getRadicadoAsociadoId.
     * 
     * @return radicadoAsociadoId
     */
    public Long getRadicadoAsociadoId() {
        return radicadoAsociadoId;
    }

    /**
     * setRadicadoAsociadoId.
     * 
     * @param radicadoAsociadoId
     *            Identificador del radicado asociado.
     */
    public void setRadicadoAsociadoId(Long radicadoAsociadoId) {
        this.radicadoAsociadoId = radicadoAsociadoId;
    }

    /**
     * getRadicadoAsociado.
     * 
     * @return String
     */
    public String getRadicadoAsociado() {
        return radicadoAsociado;
    }

    /**
     * setRadicadoAsociado.
     * 
     * @param radicadoAsociado
     *            Radicado asociado.
     */
    public void setRadicadoAsociado(String radicadoAsociado) {
        this.radicadoAsociado = radicadoAsociado;
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
     * getAsignado.
     * 
     * @return Integer
     */
    public Integer getAsignado() {
        return asignado;
    }

    /**
     * setAsignado.
     * 
     * @param asignado
     *            Identificador del usuario asignado.
     */
    public void setAsignado(Integer asignado) {
        this.asignado = asignado;
    }

    /**
     * getFechaRadicado.
     * 
     * @return Date
     */
    public Date getFechaRadicado() {
        return fechaRadicado;
    }

    /**
     * setFechaRadicado.
     * 
     * @param fechaRadicado
     *            Fecha del radicado y fecha inicial para la consulta.
     */
    public void setFechaRadicado(Date fechaRadicado) {
        this.fechaRadicado = fechaRadicado;
    }

    /**
     * getFechaFinal.
     * 
     * @return Date
     */
    public Date getFechaFinal() {
        return fechaFinal;
    }

    /**
     * setFechaFinal.
     * 
     * @param fechaFinal
     *            Fecha final del radicado.
     */
    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    /**
     * getFechaVencimiento.
     * 
     * @return Date
     */
    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * setFechaVencimiento.
     * 
     * @param fechaVencimiento
     *            Fecha vencimiento del radicado.
     */
    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
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
     * setCanalId.
     * 
     * @param canalId
     *            Identificador del canal.
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
     * setNombreCanal.
     * 
     * @param nombreCanal
     *            Nombre del canal.
     */
    public void setNombreCanal(String nombreCanal) {
        this.nombreCanal = nombreCanal;
    }

    /**
     * getRemitente.
     * 
     * @return Integer
     */
    public Integer getRemitente() {
        return remitente;
    }

    /**
     * setRemitente.
     * 
     * @param remitente
     *            Identificador del tercero.
     */
    public void setRemitente(Integer remitente) {
        this.remitente = remitente;
    }

    /**
     * isAnulado.
     * 
     * @return boolean
     */
    public boolean isAnulado() {
        return anulado;
    }

    /**
     * setAnulados.
     * 
     * @param anulado
     *            Radicados anulado.
     */
    public void setAnulado(boolean anulado) {
        this.anulado = anulado;
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
     * @param requiereRta
     *            Radicados requiere respuesta.
     */
    public void setRequiereRespuesta(boolean requiereRespuesta) {
        this.requiereRespuesta = requiereRespuesta;
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
     *            Asunto del radicado.
     */
    public void setAsunto(String asunto) {
        this.asunto = asunto;
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
     */
    public void setCantidadFolios(int cantidadFolios) {
        this.cantidadFolios = cantidadFolios;
    }

    /**
     * getAnexos.
     * 
     * @return boolean
     */
    public boolean isAnexos() {
        return anexos;
    }

    /**
     * setAnexos.
     * 
     * @param anexos
     */
    public void setAnexos(boolean anexos) {
        this.anexos = anexos;
    }

    /**
     * getAsignados.
     * 
     * @return String
     */
    public String getAsignados() {
        return asignados;
    }

    /**
     * setAsignados.
     * 
     * @param asignados
     */
    public void setAsignados(String asignados) {
        this.asignados = asignados;
    }

    /**
     * getDestinatarios.
     * 
     * @return String
     */
    public String getDestinatarios() {
        return destinatarios;
    }

    /**
     * setDestinatarios.
     * 
     * @param destinatarios
     */
    public void setDestinatarios(String destinatarios) {
        this.destinatarios = destinatarios;
    }

    /**
     * getRemitenteRadicado.
     * 
     * @return String
     */
    public String getRemitenteRadicado() {
        return remitenteRadicado;
    }

    /**
     * setRemitenteRadicado.
     * 
     * @param remitenteRadicado
     */
    public void setRemitenteRadicado(String remitenteRadicado) {
        this.remitenteRadicado = remitenteRadicado;
    }

}