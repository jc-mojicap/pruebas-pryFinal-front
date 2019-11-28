/*
* Archivo: GestionarRadicadoController.java
* Fecha creacion = 04/05/2017
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
package co.com.grupoasd.documental.presentacion.controller.correspondencia;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.ServerErrorException;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.media.AMedia;
import org.zkoss.util.media.Media;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import co.com.grupoasd.documental.cliente.catalogo.model.TipoDocumental;
import co.com.grupoasd.documental.cliente.catalogo.model.Usuario;
import co.com.grupoasd.documental.cliente.correspondencia.model.CorAdjunto;
import co.com.grupoasd.documental.cliente.correspondencia.model.CorComentario;
import co.com.grupoasd.documental.cliente.correspondencia.model.CorRadicado;
import co.com.grupoasd.documental.cliente.correspondencia.model.CorrespondenciaBitacoraRadicado;
import co.com.grupoasd.documental.cliente.correspondencia.model.FormatosPermitidosArchivo;
import co.com.grupoasd.documental.presentacion.comun.vo.ConstantAttributes;
import co.com.grupoasd.documental.presentacion.comun.vo.autenticacion.SesionVo;
import co.com.grupoasd.documental.presentacion.controller.util.SessionUtil;
import co.com.grupoasd.documental.presentacion.controller.util.Utils;
import co.com.grupoasd.documental.presentacion.service.catalogo.CatalogoServiceFactory;
import co.com.grupoasd.documental.presentacion.service.catalogo.iface.TipoDocumentalService;
import co.com.grupoasd.documental.presentacion.service.catalogo.iface.UsuarioService;
import co.com.grupoasd.documental.presentacion.service.correspondencia.CorrespondenciaServiceFactory;
import co.com.grupoasd.documental.presentacion.service.correspondencia.iface.CorAdjuntoService;
import co.com.grupoasd.documental.presentacion.service.correspondencia.iface.CorRadicadoService;
import co.com.grupoasd.documental.presentacion.service.correspondencia.iface.CorrespondenciaBitacoraRadicadoService;
import co.com.grupoasd.documental.presentacion.service.correspondencia.iface.FormatosPermitidosArchivoService;

public class GestionarRadicadoController {

    @Wire
    private Label lblFechaRadicado;
    @Wire
    private Label lblTipoRadicado;
    @Wire
    private Label lblFechaLimiteRta;
    @Wire
    private Label lblUsuarioRadicacion;
    @Wire
    private Textbox txtComentario;
    @Wire
    private Window windowGestionarRadicado;
    @Wire
    private Listbox lstFormatos;
    @Wire
    private Listbox lstArchAdjuntos;
    @Wire
    private Listbox lstComentarios;

    //Servicios
    private CorRadicadoService corRadicadoService;
    private TipoDocumentalService tipoDocumentalService;
    private CorrespondenciaBitacoraRadicadoService bitacoraService;
    private UsuarioService usuarioService;
    private FormatosPermitidosArchivoService formatosPermitidosArchivoService;
    private CorAdjuntoService corAdjuntoService;
    
    private CorRadicado radicado;
    private ListModelList<TipoDocumental> tiposDocumentales;
    private TipoDocumental tipoDocumentalSeleccionado;
    private List<CorrespondenciaBitacoraRadicado> bitacora;
    private List<FormatosPermitidosArchivo> formatosPermitidos;
    private List<CorAdjunto> adjuntos;
    
    private boolean cambiosRealizados;
    private boolean cambiosAdjunto;
    private boolean comentarioAgregado;
    private Integer idUsuario;
    private Integer idEmpresa;
    
    private final String[] formatosVisualizables = {"pdf", "jpg", "bmp", "gif", "png"};
    
    /**
     * Inicialización del controller.
     */
    @Init
    public void init(@ExecutionArgParam("radicadoId") Long radicadoId) {
        try {
	        this.idUsuario = ((SesionVo)Sessions.getCurrent().getAttribute("usrAuth")).getUsuarioId();
	        this.idEmpresa = ((SesionVo)Sessions.getCurrent().getAttribute("usrAuth")).getEmpresaId();
	        
	        // Iniciar todos los servicios
	        this.corRadicadoService = CorrespondenciaServiceFactory.getCorRadicadoService();
	        this.tipoDocumentalService = CatalogoServiceFactory.getTipoDocumentalService();
	        this.bitacoraService = CorrespondenciaServiceFactory.getCorrespondenciaBitacoraRadicadoService();
	        this.usuarioService = CatalogoServiceFactory.getUsuarioService();
	        this.formatosPermitidosArchivoService =  CorrespondenciaServiceFactory.getFormatosPermitidosArchivoService();
	        this.corAdjuntoService = CorrespondenciaServiceFactory.getCorAdjuntoService();
	        
	        radicado = corRadicadoService.obtenerPorId(radicadoId);
	        
	        adjuntos = new ArrayList<>();
	        
	        if (radicado != null && radicado.getCorAdjuntos() != null) {
	        	 adjuntos.addAll(radicado.getCorAdjuntos());
	        }
	       
	        formatosPermitidos = formatosPermitidosArchivoService.listarTodos();
        } catch (NotFoundException e) {
        	Messagebox.show(Labels.getLabel("commons.mensaje.mensajeErrorGeneral"),
					Labels.getLabel("commons.titulo.warning"), Messagebox.OK, Messagebox.ERROR);
        	windowGestionarRadicado.detach();
        } catch (Exception ex) {
        	Messagebox.show(Labels.getLabel("commons.mensaje.mensajeErrorGeneral"),
					Labels.getLabel("commons.titulo.warning"), Messagebox.OK, Messagebox.ERROR);
        	windowGestionarRadicado.detach();
        }
    }
    
    /**
     * Método que se ejecuta después de que ZK crea los componentes de la vista.
     * 
     * @param view
     */
    @AfterCompose
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
        cargarDatosIniciales();
    }
    
    /**
     * Método que carga los datos iniciales de la pantalla.
     * @author JuanMojica
     */
    @NotifyChange({ "tiposDocumentales", "tipoDocumentalSeleccionado" })
    private void cargarDatosIniciales(){
        lblTipoRadicado.setValue(radicado.getTipoRadicacion() == 'S' ? "SALIDA" : radicado.getTipoRadicacion() == 'E' ? "ENTRADA" : "INTERNA");
        lblFechaLimiteRta.setValue(radicado.getFechaVencimientoRta() != null ? MessageFormat.format("{0,date," + Labels.getLabel("commons.config.dateFormat") + "}", radicado.getFechaVencimientoRta()) : "");
        Usuario userRadicacion = usuarioService.obtenerPorId(radicado.getUsuarioRadicacionId());
        lblUsuarioRadicacion.setValue(userRadicacion.getNombreCompleto());
        cargarBitacora();
        ListModelList<TipoDocumental> tipoDocTmp = new ListModelList<>(tipoDocumentalService.listar(radicado.getSubserieId(), false));
        setTiposDocumentales(tipoDocTmp);
        
        listarFormatosPermitidos();
           
    }

    /**
     * Carga la información de la bitacora del radicado.
     */
    private void cargarBitacora() {
        setBitacora(bitacoraService.listarPorRadicadoId(radicado.getRadicadoId()));
        BindUtils.postNotifyChange(null, null, GestionarRadicadoController.this, "*");
    }

    /**
     * Agrega a un listbox los formatos de archivo permitidos.
     */
    private void listarFormatosPermitidos() {
        Listitem item = new Listitem();
        item.setParent(lstFormatos);
        for (int i = 0; i < formatosPermitidos.size(); i++) {
            if(i%3 == 0){
                item = new Listitem();
                item.setParent(lstFormatos);
            }
            item.appendChild(new Listcell("."+formatosPermitidos.get(i).getExtension(), "/recursos/icons/dot.png"));
        }
    }

    /**
     * Método que agrega los comentarios a la lista de comentarios.
     * @author JuanMojica
     */
    @NotifyChange("*")
    @Command
    public void agregarComentario(){

        if (txtComentario.getValue().trim() != null && txtComentario.getValue().trim() != ""){
            CorComentario corComentario = new CorComentario();
            corComentario.setRadicadoId(radicado.getRadicadoId());
            corComentario.setEstadoRadicadoId(radicado.getEstadoId());
            corComentario.setComentario(txtComentario.getValue().trim());
            corComentario.setFecha(new Date());
            corComentario.setUsuarioId(((SesionVo)Sessions.getCurrent().getAttribute("usrAuth")).getUsuarioId());
            corComentario.setPrimerNombreUsuario(((SesionVo)Sessions.getCurrent().getAttribute("usrAuth")).getPrimerNombre());
            corComentario.setSegundoNombreUsuario(((SesionVo)Sessions.getCurrent().getAttribute("usrAuth")).getSegundoNombre());
            corComentario.setPrimerApellidoUsuario(((SesionVo)Sessions.getCurrent().getAttribute("usrAuth")).getPrimerApellido());
            corComentario.setSegundoApellidoUsuario(((SesionVo)Sessions.getCurrent().getAttribute("usrAuth")).getSegundoApellido());
            
            radicado.getComentarios().add(0, corComentario);
            txtComentario.setRawValue(null);
            
            comentarioAgregado = true;
            cambiosRealizados = true;
            
            lstComentarios.invalidate();
        } else {
            throw new WrongValueException(txtComentario, Labels.getLabel("commons.tooltip.campoRequerido"));
        }
        
    }
    
    /**
     * Método que es llamado cuando desde la ventana presionan el botón de rechazar radicado.
     * @author JuanMojica
     */
    @NotifyChange("*")
    @Command
    public void rechazarRadicado(){
        if (comentarioAgregado || !txtComentario.getValue().trim().isEmpty()){
            if (!txtComentario.getValue().trim().isEmpty()){
                CorComentario ultimoComentario = new CorComentario();
                ultimoComentario.setRadicadoId(radicado.getRadicadoId());
                ultimoComentario.setEstadoRadicadoId(radicado.getEstadoId());
                ultimoComentario.setComentario(txtComentario.getValue().trim());
                ultimoComentario.setFecha(new Date());
                ultimoComentario.setUsuarioId(((SesionVo)Sessions.getCurrent().getAttribute("usrAuth")).getUsuarioId());
                ultimoComentario.setPrimerNombreUsuario(((SesionVo)Sessions.getCurrent().getAttribute("usrAuth")).getPrimerNombre());
                ultimoComentario.setSegundoNombreUsuario(((SesionVo)Sessions.getCurrent().getAttribute("usrAuth")).getSegundoNombre());
                ultimoComentario.setPrimerApellidoUsuario(((SesionVo)Sessions.getCurrent().getAttribute("usrAuth")).getPrimerApellido());
                ultimoComentario.setSegundoApellidoUsuario(((SesionVo)Sessions.getCurrent().getAttribute("usrAuth")).getSegundoApellido());
                txtComentario.setRawValue(null);
                radicado.getComentarios().add(0, ultimoComentario);
                comentarioAgregado = true;
            }
            String preguntaConfirmacion = Labels.getLabel("correspondencia.commons.mensaje.rechazarRadicado").replaceAll("%d", radicado.getRadicado());
            String titulo = Labels.getLabel("commons.titulo.confirmacion");
            Messagebox.show(preguntaConfirmacion, titulo,
                    Messagebox.YES | Messagebox.NO, Messagebox.QUESTION,
                    new EventListener<Event>() {
                
                @Override
                public void onEvent(Event evnt) throws Exception {
                    if (Messagebox.ON_YES.equals(evnt.getName())) {
                        confirmarRechazo();
                    }
                }
            });
        } else {
            throw new WrongValueException(txtComentario, Labels.getLabel("commons.tooltip.campoRequerido"));
        }
    }
    
    /**
     * Método que verifica si hay cambios realizados en el radicado, en caso de que así sea
     * solicita confirmación de la acción debido a que los cambios sin guardar se perderan
     * al rechazar el radicado.
     * @author JuanMojica
     */
    public void confirmarRechazo(){
        if (comentarioAgregado || !txtComentario.getValue().trim().isEmpty()){
            if (cambiosAdjunto){
                String preguntaConfirmacion = Labels.getLabel("correspondencia.commons.mensaje.rechazarRadicadoCambios").replaceAll("%n", "\n");
                String titulo = Labels.getLabel("commons.titulo.confirmacion");
                Messagebox.show(preguntaConfirmacion, titulo,
                        Messagebox.YES | Messagebox.NO, Messagebox.QUESTION,
                        new EventListener<Event>() {
                    
                    @Override
                    public void onEvent(Event evnt) throws Exception {
                        if (Messagebox.ON_YES.equals(evnt.getName())) {
                            procesarRechazo();
                        }
                    }
                });
            } else {
                procesarRechazo();
            }
        } else {
            Clients.scrollIntoView(txtComentario);
            throw new WrongValueException(txtComentario, Labels.getLabel("commons.tooltip.campoRequerido"));
        }
    }
    
    /**
     * Método que cambia el estado del radicado y envía la solicitud de actualización.
     * @author JuanMojica
     */
    public void procesarRechazo(){
        radicado.setEstadoId(co.com.grupoasd.documental.presentacion.comun.vo.EstadosRadicado.RADICADO.getId());
        radicado.setUsuarioModifica(idUsuario);
        radicado.setFechaModifica(new Date());
        radicado = corRadicadoService.actualizarRadicado(SessionUtil.getAuthToken(Sessions.getCurrent()), radicado);
        String mensaje = Labels.getLabel("correspondencia.commons.mensaje.radicadoRechazado");
        String tipo = "info";
        Clients.showNotification(String.format(mensaje, radicado.getRadicado()), tipo, windowGestionarRadicado, "middle_center", 6000, true);
        
        windowGestionarRadicado.detach();
    }
    
    /**
     * Método para finalizar la gestión
     */
    @NotifyChange("*")
    @Command
    public void finalizarGestion(){
        
        if (comentarioAgregado || !txtComentario.getValue().trim().isEmpty()){
            if (!txtComentario.getValue().trim().isEmpty()){
                CorComentario ultimoComentario = new CorComentario();
                ultimoComentario.setRadicadoId(radicado.getRadicadoId());
                ultimoComentario.setEstadoRadicadoId(radicado.getEstadoId());
                ultimoComentario.setComentario(txtComentario.getValue().trim());
                ultimoComentario.setFecha(new Date());
                ultimoComentario.setUsuarioId(((SesionVo)Sessions.getCurrent().getAttribute("usrAuth")).getUsuarioId());
                ultimoComentario.setPrimerNombreUsuario(((SesionVo)Sessions.getCurrent().getAttribute("usrAuth")).getPrimerNombre());
                ultimoComentario.setSegundoNombreUsuario(((SesionVo)Sessions.getCurrent().getAttribute("usrAuth")).getSegundoNombre());
                ultimoComentario.setPrimerApellidoUsuario(((SesionVo)Sessions.getCurrent().getAttribute("usrAuth")).getPrimerApellido());
                ultimoComentario.setSegundoApellidoUsuario(((SesionVo)Sessions.getCurrent().getAttribute("usrAuth")).getSegundoApellido());
                txtComentario.setRawValue(null);
                radicado.getComentarios().add(0, ultimoComentario);
                comentarioAgregado = true;
            }
            String preguntaConfirmacion = Labels.getLabel("correspondencia.commons.mensaje.finalizarGestion");
            String titulo = Labels.getLabel("commons.titulo.confirmacion");
            Messagebox.show(preguntaConfirmacion, titulo,
                    Messagebox.YES | Messagebox.NO, Messagebox.QUESTION,
                    new EventListener<Event>() {
                
                @Override
                public void onEvent(Event evnt) throws Exception {
                    if (Messagebox.ON_YES.equals(evnt.getName())) {
                        procesarFinalGestion();
                    }
                }
            });
        } else {
            throw new WrongValueException(txtComentario, Labels.getLabel("commons.tooltip.campoRequerido"));
        }
    }
    
    /**
     * Procesa la finalización de la gestión.
     */
    public void procesarFinalGestion(){
        radicado.setEstadoId(co.com.grupoasd.documental.presentacion.comun.vo.EstadosRadicado.GESTIONADO.getId());
        radicado.setUsuarioModifica(idUsuario);
        radicado.setFechaModifica(new Date());
        radicado = corRadicadoService.actualizarRadicado(SessionUtil.getAuthToken(Sessions.getCurrent()), radicado);
        
        windowGestionarRadicado.detach();
    }
    
    /**
     * Método que procesa el archivo que se carga.
     * @param event
     */
    @NotifyChange("adjuntos")
    @Command
    public void cargarArchivo(@BindingParam("arg1") UploadEvent event){
        Media media = event.getMedia();
        boolean isExisteAdjunto = false;
        
        if (Integer.valueOf(media.getName().lastIndexOf("."))>0){
            if (verificarTipoArchivo(media.getName().substring(media.getName().lastIndexOf(".")+1))){
                CorAdjunto corAdjunto = new CorAdjunto();
                corAdjunto.setRuta(media.getName());
                corAdjunto.setNombreUsuario(((SesionVo)Sessions.getCurrent().getAttribute("usrAuth")).getNombreCompletoUsuario());
                corAdjunto.setUsuarioId(idUsuario);
                corAdjunto.setBytesAdjuntos(media.getByteData());
                corAdjunto.setFecha(new Date());
                corAdjunto.setNombreTipoDocumental(tipoDocumentalSeleccionado.getNombre());
                corAdjunto.setTipoDocumentalId(tipoDocumentalSeleccionado.getTipoDocumentalId());
                
                if (adjuntos != null){
                    for (CorAdjunto adjunto : adjuntos) {
                        
                        if(adjunto.getRuta().equalsIgnoreCase(corAdjunto.getRuta()) && adjunto.getTipoDocumentalId() == corAdjunto.getTipoDocumentalId()){
                            Clients.showNotification(String.format(Labels.getLabel("correspondencia.commons.mensaje.existeAdjunto"), 
                                    adjunto.getRuta(), adjunto.getNombreTipoDocumental()), "warning", windowGestionarRadicado, "middle_center", 6000, true);
                            
                            isExisteAdjunto = true;
                            break;
                        }
                    }
                } else {
                    adjuntos = new ArrayList<>();
                }
                
               if (!isExisteAdjunto) {
                   adjuntos.add(0, corAdjunto);
                   cambiosRealizados = true;
                   cambiosAdjunto = true;
               }
            } else {
                String mensaje = Labels.getLabel("commons.mensaje.archivoNoValido");
                String tipo = "error";
                Clients.showNotification(mensaje, tipo, null, "middle_center", 6000, true);
            }
        } else {
            String mensaje = Labels.getLabel("commons.mensaje.archivoNoValido");
            String tipo = "error";
            Clients.showNotification(mensaje, tipo, null, "middle_center", 6000, true);
        }
        
        lstArchAdjuntos.invalidate();
        
    }
    
    /**
     * Verifica que el tipo de archivo (extensión) corresponda a los permitidos.
     * @param formato formato (extensión) del archivo.
     * @return<code>true</code> si el archivo es válido, <code>false</code> en caso contrario.
     */
    private boolean verificarTipoArchivo(String formato){
        for (FormatosPermitidosArchivo FormatosPermitidosArchivo : formatosPermitidos) {
            if (FormatosPermitidosArchivo.getExtension().equalsIgnoreCase(formato)){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Verifica si el tipo de archivo (extensión) puede ser mostrada en una ventana de vista previa.
     * @param formato formato (extensión) del archivo.
     * @return<code>true</code> si el archivo es válido, <code>false</code> en caso contrario.
     */
    private boolean verificarArchivoVisualizable(String formato){
        for (String item : formatosVisualizables) {
            if (item.equalsIgnoreCase(formato)){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Muestra la vista previa de los archivos.
     * @param adjunto
     */
    @Command
    public void mostrarAdjunto(@BindingParam("arg1") CorAdjunto adjunto){
        byte[] bytes;
        
        try{
            if (adjunto.getAdjuntoId() != null){
                bytes = corAdjuntoService.obtenerBytesAdjunto(radicado.getRadicadoId(), adjunto.getAdjuntoId());
            } else {
                bytes = adjunto.getBytesAdjuntos();
            }
            
            if (verificarArchivoVisualizable(adjunto.getRuta().substring(adjunto.getRuta().lastIndexOf(".")+1))){
                try {
                    Utils.mostrarArchivoAdjunto(adjunto.getRuta(), bytes, windowGestionarRadicado);
                } catch (ServerErrorException ex) {
                    if (ex.getResponse() != null && ex.getResponse().getHeaders() != null) {
                        List<Object> myResponses = ex.getResponse().getHeaders().get(Utils.LLAVE_CODIGOS_ERROR_SERVER);
                        
                        if (myResponses != null && myResponses.size() > 0) {
                            if (myResponses.contains(Utils.CODIGO_ERROR_FILE_NOT_FOUND_EXCEPTION)) {
                                Messagebox.show("El documento solicitado no existe, Por favor Verifique que el documento exista.", 
                                        Labels.getLabel("commons.titulo.warning"), Messagebox.OK, Messagebox.ERROR);
                            } else {
                                Messagebox.show("No se puede visualizar el documento, Por favor intente de nuevo.", 
                                        Labels.getLabel("commons.titulo.warning"), Messagebox.OK, Messagebox.ERROR);
                            }
                        }
                    }
                } catch (Exception ex) {
                    String mensaje = Labels.getLabel("commons.mensaje.errorMostrandoAdjunto");
                    String tipo = "error";
                    Clients.showNotification(mensaje, tipo, null, "middle_center", 6000, true);
                    ex.printStackTrace();
                }
            } else {
                // Si el archivo no es del tipo permitido para visualización, se descarga.
                final AMedia amedia = new AMedia(adjunto.getRuta(), null, null, bytes);
                //Filedownload.save(amedia);
                Sessions.getCurrent().setAttribute(ConstantAttributes.DOWNLOAD, amedia);
                Executions.getCurrent().sendRedirect(ConstantAttributes.DOWNLOAD_PAGE, "other");
            }
        } catch (ServerErrorException ex) {
            if (ex.getResponse() != null && ex.getResponse().getHeaders() != null) {
                List<Object> myResponses = ex.getResponse().getHeaders().get(Utils.LLAVE_CODIGOS_ERROR_SERVER);

                if (myResponses != null && myResponses.size() > 0) {
                    if (myResponses.contains(Utils.CODIGO_ERROR_FILE_NOT_FOUND_EXCEPTION)) {
                        String mensaje = Labels.getLabel("commons.mensaje.archivoNoExiste");
                        String tipo = "error";
                        Clients.showNotification(mensaje, tipo, null, "middle_center", 6000, true);
                    } else {
                        String mensaje = Labels.getLabel("commons.mensaje.archivoNoPuedeMostrar");
                        String tipo = "error";
                        Clients.showNotification(mensaje, tipo, null, "middle_center", 6000, true);
                    }
                }
            }
        } catch (Exception ex) {
            String mensaje = Labels.getLabel("commons.mensaje.archivoNoPuedeMostrar");
            String tipo = "error";
            Clients.showNotification(mensaje, tipo, null, "middle_center", 6000, true);
        }
    }    
    
    /**
     * Elimina el archivo adjunto sobre el cual se hace clic.
     * @param adjunto
     */
    @Command
    public void eliminarAdjunto(@BindingParam("arg1") CorAdjunto adjunto){
        String preguntaConfirmacion = String.format(Labels.getLabel("correspondencia.commons.mensaje.eliminarAdjunto"), adjunto.getRuta().contains("/") ? adjunto.getRuta().substring(adjunto.getRuta().lastIndexOf("/")+1, adjunto.getRuta().length()) : adjunto.getRuta());
        String titulo = Labels.getLabel("commons.titulo.confirmacion");
        Messagebox.show(preguntaConfirmacion, titulo,
                Messagebox.YES | Messagebox.NO, Messagebox.QUESTION,
                new EventListener<Event>() {
            
            @Override
            public void onEvent(Event evnt) throws Exception {
                if (Messagebox.ON_YES.equals(evnt.getName())) {
                    adjuntos.remove(adjunto);
                    BindUtils.postNotifyChange(null, null, GestionarRadicadoController.this, "adjuntos");
                    lstArchAdjuntos.invalidate();
                    cambiosRealizados = true;
                    cambiosAdjunto = true;
                }
            }
        });
    }
    
    /**
     * Método que se ejecuta al hacer clic sobre el botón de actualizar archivos.
     */
    @Command
    public void actualizarArchivos(){
        try{
            if (comentarioAgregado || !txtComentario.getValue().trim().isEmpty()){
                if (!txtComentario.getValue().trim().isEmpty()){
                    CorComentario ultimoComentario = new CorComentario();
                    ultimoComentario.setRadicadoId(radicado.getRadicadoId());
                    ultimoComentario.setEstadoRadicadoId(radicado.getEstadoId());
                    ultimoComentario.setComentario(txtComentario.getValue().trim());
                    ultimoComentario.setFecha(new Date());
                    ultimoComentario.setUsuarioId(((SesionVo)Sessions.getCurrent().getAttribute("usrAuth")).getUsuarioId());
                    ultimoComentario.setPrimerNombreUsuario(((SesionVo)Sessions.getCurrent().getAttribute("usrAuth")).getPrimerNombre());
                    ultimoComentario.setSegundoNombreUsuario(((SesionVo)Sessions.getCurrent().getAttribute("usrAuth")).getSegundoNombre());
                    ultimoComentario.setPrimerApellidoUsuario(((SesionVo)Sessions.getCurrent().getAttribute("usrAuth")).getPrimerApellido());
                    ultimoComentario.setSegundoApellidoUsuario(((SesionVo)Sessions.getCurrent().getAttribute("usrAuth")).getSegundoApellido());
                    txtComentario.setRawValue(null);
                    radicado.getComentarios().add(0, ultimoComentario);
                    radicado.setFechaModifica(new Date());
                    comentarioAgregado = true;
                }
                radicado.setCorAdjuntos(adjuntos);
                actualizarRadicado(radicado);
                cargarBitacora();

                String mensaje = Labels.getLabel("correspondencia.commons.mensaje.archivosActualizados");
                String tipo = "info";
                Clients.showNotification(mensaje, tipo, windowGestionarRadicado, "middle_center", 6000, true);
                
            } else {
                throw new WrongValueException(txtComentario, Labels.getLabel("commons.tooltip.campoRequerido"));
            }
        } catch (WrongValueException e) {
            throw e;
        } catch (Exception e) {
            if (e instanceof ServerErrorException) {
                ServerErrorException ex = (ServerErrorException) e;
                if (ex.getResponse() != null && ex.getResponse().getHeaders() != null) {
                    List<Object> myResponses = ex.getResponse().getHeaders().get("errorCode");
                    if (myResponses != null && myResponses.size() > 0) {
                        if(myResponses.contains(Utils.CODIGO_ERROR_IO_EXCEPTION)){
                            Messagebox.show(Labels.getLabel("correspondencia.commons.mensaje.errorArchivo"),
                                    Labels.getLabel("commons.titulo.warning"), Messagebox.OK, Messagebox.ERROR);
                        }
                    }
                }
            }
        }
    }
    
    public void actualizarRadicado(CorRadicado radicado){
        radicado = corRadicadoService.actualizarRadicado(SessionUtil.getAuthToken(Sessions.getCurrent()), radicado);
    }
    
    /**
     * Control para cerrar ventana de radicación.
     * @param event
     */
    @Command
    public void cerrarVentana(@BindingParam("arg1") Event event) {
        event.stopPropagation();
        if (cambiosRealizados){
            String preguntaConfirmacion = Labels.getLabel("commons.mensaje.salirSinGuardar");
            String titulo = Labels.getLabel("commons.titulo.confirmacion");
            Messagebox.show(preguntaConfirmacion, titulo,
                    Messagebox.YES | Messagebox.NO, Messagebox.QUESTION,
                    new EventListener<Event>() {
                
                @Override
                public void onEvent(Event evnt) throws Exception {
                    if (Messagebox.ON_YES.equals(evnt.getName())) {
                        windowGestionarRadicado.detach();
                    }
                }
            });
        } else {
            windowGestionarRadicado.detach();
        }
    }
    
    /**
     * @return the radicado
     */
    public CorRadicado getRadicado() {
        return radicado;
    }

    /**
     * @param radicado the radicado to set
     */
    public void setRadicado(CorRadicado radicado) {
        this.radicado = radicado;
    }

    /**
     * @return the tiposDocumentales
     */
    public ListModelList<TipoDocumental> getTiposDocumentales() {
        return tiposDocumentales;
    }

    /**
     * @param tiposDocumentales the tiposDocumentales to set
     */
    public void setTiposDocumentales(ListModelList<TipoDocumental> tiposDocumentales) {
        this.tiposDocumentales = tiposDocumentales;
    }

    /**
     * @return the tipoDocumentalSeleccionado
     */
    public TipoDocumental getTipoDocumentalSeleccionado() {
        return tipoDocumentalSeleccionado;
    }

    /**
     * @param tipoDocumentalSeleccionado the tipoDocumentalSeleccionado to set
     */
    public void setTipoDocumentalSeleccionado(TipoDocumental tipoDocumentalSeleccionado) {
        this.tipoDocumentalSeleccionado = tipoDocumentalSeleccionado;
    }

    /**
     * @return the bitacora
     */
    public List<CorrespondenciaBitacoraRadicado> getBitacora() {
        return bitacora;
    }

    /**
     * @param bitacora the bitacora to set
     */
    public void setBitacora(List<CorrespondenciaBitacoraRadicado> bitacora) {
        this.bitacora = bitacora;
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
     * @return the idEmpresa
     */
    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    /**
     * @param idEmpresa the idEmpresa to set
     */
    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    /**
     * @return the adjuntos
     */
    public List<CorAdjunto> getAdjuntos() {
        return adjuntos;
    }

    /**
     * @param adjuntos the adjuntos to set
     */
    public void setAdjuntos(List<CorAdjunto> adjuntos) {
        this.adjuntos = adjuntos;
    }
    
}
