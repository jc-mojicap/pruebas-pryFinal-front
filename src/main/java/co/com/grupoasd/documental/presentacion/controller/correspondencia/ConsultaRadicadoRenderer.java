/*
 * Archivo: ConsultaRadicadoRenderer.java
 * Fecha creacion: 06/04/2017
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
package co.com.grupoasd.documental.presentacion.controller.correspondencia;

import java.util.logging.Level;

import org.apache.commons.lang3.StringUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Div;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Hlayout;
import org.zkoss.zul.Html;
import org.zkoss.zul.Label;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;

import co.com.grupoasd.documental.cliente.autorizacion.AccionApp;
import co.com.grupoasd.documental.presentacion.comun.PropertiesMessagesUtil;
import co.com.grupoasd.documental.presentacion.comun.exception.GeneralException;
import co.com.grupoasd.documental.presentacion.comun.vo.EstadosRadicado;
import co.com.grupoasd.documental.presentacion.comun.vo.TipoRadicado;
import co.com.grupoasd.documental.presentacion.controller.util.GenericController;
import co.com.grupoasd.documental.presentacion.controller.util.SessionUtil;
import co.com.grupoasd.documental.presentacion.controller.util.UiUtils;
import co.com.grupoasd.documental.presentacion.service.correspondencia.dto.RadicadoDto;

public class ConsultaRadicadoRenderer extends GenericController<Component> implements RowRenderer<RadicadoDto> {

    /**
     * Renderer para el llenado de la grilla de consulta de radicados.
     * 
     * @author cestrada
     */
    private static final long serialVersionUID = 1L;

    @Override
    public void render(Row row, final RadicadoDto data, int index) {
        try {

            row.appendChild(new Label(data.getRadicadoId().toString()));
            row.appendChild(new Label(data.getRadicado()));
            row.appendChild(new Label(dateFormat(data.getFechaRadicado())));
            row.appendChild(new Label(data.getNombreTipoRadicacion()));
            row.appendChild(new Label(data.getRemitenteRadicado()));
            row.appendChild(new Label(data.getNombreEstado()));
            row.appendChild(new Label(data.getNombreArea()));
            row.appendChild(new Label(data.getNombreSerie()));
            row.appendChild(new Label(data.getNombreSubserie()));
            row.appendChild(new Label(String.valueOf(data.getCantidadFolios())));
            row.appendChild(new Label(booleanToString(data.isAnexos())));
            row.appendChild(new Label(data.getRadicadoAsociado()));
            row.appendChild(new Label(data.getRadicadoExterno()));
            row.appendChild(new Label(data.getNombreCanal()));
            row.appendChild(new Label(data.getAsunto()));
            row.appendChild(new Label(data.getAsignados()));
            row.appendChild(new Label(dateFormat(data.getFechaVencimiento())));

            if (data.getDestinatarios() != null && !data.getDestinatarios().isEmpty()) {
                String[] destinatarios = data.getDestinatarios().split(";");
                String htmlDestinatarios = "";
                String primerDestinatario = "";
                if (destinatarios.length > 1) {
                    htmlDestinatarios = "<ul>";
                    for (String each : destinatarios) {
                        if (primerDestinatario.isEmpty()) {
                            primerDestinatario = each;
                            continue;
                        }
                        htmlDestinatarios += "<li>";
                        htmlDestinatarios += each;
                        htmlDestinatarios += "</li>";
                    }
                    htmlDestinatarios += "</ul>";
                    final Div divDetinatarios = new Div();
                    Html destHTML = new Html("<div style=\"overflow: auto;\" >" + htmlDestinatarios + "</div>");
                    final Popup destPop = new Popup();
                    destPop.setParent(divDetinatarios);
                    destPop.setAction("show: slideDown({duration:100}); hide: slideUp({duration:100})");
                    Hlayout group = new Hlayout();
                    group.appendChild(destHTML);
                    group.setHflex("1");
                    group.setVflex("1");
                    destPop.appendChild(group);
                    destPop.setStyle("max-width: 500px; max-height: 400px; overflow: auto;");
                    final Label destLbl = new Label(StringUtils.abbreviate(primerDestinatario, 0, 100));
                    destLbl.setTooltip("destPop" + primerDestinatario + ", start_before");
                    Hbox hlDest = new Hbox();
                    hlDest.appendChild(destLbl);
                    hlDest.setParent(divDetinatarios);
                    Toolbarbutton btnDest = new Toolbarbutton("+" + (destinatarios.length - 1),
                            "/recursos/icons/right-arrow.png");
                    btnDest.setTooltip(destPop);
                    hlDest.appendChild(btnDest);
                    btnDest.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
                        @Override
                        public void onEvent(Event event) throws Exception {
                            destPop.open(destLbl);
                        }
                    });

                    if (StringUtils.isNotBlank(destLbl.getValue())) {
                        row.appendChild(divDetinatarios);
                    }
                } else if (destinatarios.length == 1) {
                    primerDestinatario = destinatarios[0];
                    row.appendChild(new Label(primerDestinatario));
                }
            } else {
                row.appendChild(new Label());
            }

            final Div divOpciones = new Div();
            if (SessionUtil.accionPermitida(AccionApp.ACTUALIZAR_RADICADO.getId()) && !data.isAnulado()) {
                Toolbarbutton btnActualizar = new Toolbarbutton("", "/recursos/icons/edit.png");
                btnActualizar.setTooltiptext("Actualizar radicado");
                btnActualizar.setParent(divOpciones);
                btnActualizar.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
                    public void onEvent(Event event) throws Exception {
                        args.put("radicadoId", data.getRadicadoId());
                        @SuppressWarnings("unused")
                        Window win = UiUtils.setModalWindow("/correspondencia/actualizar/actualizar_radicado.zul", self,
                                args, false, true);
                    }
                });

            }
            if (SessionUtil.accionPermitida(AccionApp.ASIGNAR_RADICADO.getId())
                    && data.getEstadoId() == EstadosRadicado.RADICADO.getId()
                    && (data.getTipoRadicacion() == TipoRadicado.ENTRADA.getId()
                            || data.getTipoRadicacion() == TipoRadicado.INTERNA.getId())) {
                Toolbarbutton btnAsignar = new Toolbarbutton("", "/recursos/icons/assign.png");
                btnAsignar.setTooltiptext("Asignar radicado");
                btnAsignar.setParent(divOpciones);
                btnAsignar.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
                    public void onEvent(Event event) throws Exception {
                        args.put("radicadoId", data.getRadicadoId());
                        @SuppressWarnings("unused")
                        Window win = UiUtils.setModalWindow("/correspondencia/actualizar/asignar_radicado.zul", self,
                                args, false, true);
                    }
                });
            }
            if (SessionUtil.accionPermitida(AccionApp.GESTIONAR_RADICADO.getId())
                    && (data.getEstadoId() == EstadosRadicado.ASIGNADO.getId()
                            || data.getEstadoId() == EstadosRadicado.INFORMADO.getId())) {
                Toolbarbutton btnGestionar = new Toolbarbutton("", "/recursos/icons/manage.png");
                btnGestionar.setTooltiptext("Gestionar radicado");
                btnGestionar.setParent(divOpciones);
                btnGestionar.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
                    public void onEvent(Event event) throws Exception {
                        args.put("radicadoId", data.getRadicadoId());
                        @SuppressWarnings("unused")
                        Window win = UiUtils.setModalWindow("/correspondencia/actualizar/gestionar_radicado.zul", self,
                                args, false, true);
                    }
                });
            }
            if (SessionUtil.accionPermitida(AccionApp.VER_DETALLE_RADICADO.getId())) {
                Toolbarbutton btnVerDetalle = new Toolbarbutton("", "/recursos/icons/search_file.png");
                btnVerDetalle.setTooltiptext("Ver detalle");
                btnVerDetalle.setParent(divOpciones);
                btnVerDetalle.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
                    public void onEvent(Event event) throws Exception {
                        args.put("radicadoId", data.getRadicadoId());
                        @SuppressWarnings("unused")
                        Window win = UiUtils.setModalWindow("/correspondencia/actualizar/detalle_radicado.zul", self,
                                args, false, true);
                    }
                });

            }
            row.appendChild(divOpciones);
        } catch (GeneralException ex) {
            UiUtils.mostrarError(PropertiesMessagesUtil.instance().get(
                    ((GeneralException) ex).getExceptionType().toString().toLowerCase(), ex.getLocalizedMessage()));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            UiUtils.mostrarError(PropertiesMessagesUtil.instance().get("no_identificado",
                    new String[] { ex.getLocalizedMessage() }));
        }
    }

    @Override
    public void onCreate(Event event) {
    }
}