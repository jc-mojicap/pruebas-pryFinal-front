/*
* Archivo: ImprimirRotulosController.java
* Fecha creacion = 15/03/2017
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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.util.media.AMedia;
import org.zkoss.util.media.Media;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Window;

import co.com.grupoasd.documental.cliente.correspondencia.model.CorRadicado;
import co.com.grupoasd.documental.cliente.correspondencia.model.RotuloInfo;
import co.com.grupoasd.documental.cliente.parametrizacion.model.ParametroAplicacion;
import co.com.grupoasd.documental.presentacion.controller.util.Reportes;
import co.com.grupoasd.documental.presentacion.service.parametrizacion.ParametroAplicacionServiceFactory;
import co.com.grupoasd.documental.presentacion.service.parametrizacion.iface.ParametroAplicacionService;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Controlador de la interfaz de imprimir rótulos.
 * @author JuanMojica
 *
 */
public class ImprimirRotulosController {
    
    @Wire
    private Label lblRadicado;
    @Wire
    private Spinner spinCantidad;
    @Wire
    private Window windowImprimirRotulos;
    
    private CorRadicado radicado;
    
    private String remitente;
    
    private boolean isCreacion;
    
    private static final String ID_LOGO_EMPRESA = "logoEmpresa";
    
    private ParametroAplicacionService parametroAplicacionService;
    
    private ParametroAplicacion logoEmpresa;
    
    /**
     * Inicializacion del controller.
     */
    @Init
    public void init(@ExecutionArgParam("radicado") CorRadicado radicado, @ExecutionArgParam("remitente") String remitente, @ExecutionArgParam("isCreacion") boolean isCreacion) {
        this.radicado = radicado;
        this.remitente = remitente;
        this.isCreacion = isCreacion;
        
        this.parametroAplicacionService = ParametroAplicacionServiceFactory.getParametroAplicacionService();
        
    }
    
    /**
     * Metodo que se ejecuta despues de que ZK crea los componentes de la vista.
     * 
     * @param view
     */
    @AfterCompose
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
        
        lblRadicado.setValue(radicado.getRadicado());
    }
    
    /**
     * Lógica para preguntar al usuario si desea mantener los datos del remitente
     * al cerrar la ventana.
     * @param event
     */
    @Command
    public void cerrarVentana(@BindingParam("arg1") Event event){
        event.stopPropagation();
        if (isCreacion) {
            String preguntaConfirmacion = Labels.getLabel("correspondencia.commons.mensaje.conservarRemitente");
            String titulo = Labels.getLabel("commons.titulo.confirmacion");
            Messagebox.show(preguntaConfirmacion, titulo, Messagebox.YES | Messagebox.NO, Messagebox.QUESTION,
                    new EventListener<Event>() {
                        @Override
                        public void onEvent(Event evnt) throws Exception {
                            Map<String, Object> params = new HashMap<>();
                            if (Messagebox.ON_YES.equals(evnt.getName())) {
                                params.put("conservar", true);
                            } else {
                                params.put("conservar", false);
                            }
                            BindUtils.postGlobalCommand(null, null, "limpiarVentana", params);
                            windowImprimirRotulos.detach();
                        }
                    });
        } else {
            windowImprimirRotulos.detach();
        }
    }
    
    /**
     * Crea el reporte y lo carga en una ventana.
     */
    @Command
    public void imprimirReporte(){
        
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            
            RotuloInfo rotuloInfo = new RotuloInfo();
            rotuloInfo.setRadicado(lblRadicado.getValue());
            rotuloInfo.setCodigoBarras(lblRadicado.getValue().substring(2));//Se borra el prefijo y el guión
            rotuloInfo.setFecha(MessageFormat.format("{0,date,"+Labels.getLabel("commons.config.dateTimeFormat")+"}", getRadicado().getFechaRadicacion()));
            rotuloInfo.setRemitente(getRemitente());
            
            List<RotuloInfo> rotulos = new ArrayList<>();

            for (int i = 0; i < spinCantidad.getValue(); i++) {
                rotulos.add(rotuloInfo);
            }

            Map<String, Object> params = new HashMap<>();
            logoEmpresa = parametroAplicacionService.obtenerPorIdYEmpresa(ID_LOGO_EMPRESA, radicado.getEmpresaId());
            
            if (logoEmpresa != null){
                byte[] logoBase64 = Base64.getDecoder().decode(logoEmpresa.getValor());
                params.put("logo", ImageIO.read(new ByteArrayInputStream(logoBase64)));
                
            } else {
                params.put("logo", ImageIO.read(getClass().getResource("/images/logo.png")));
            }
            JasperPrint jasperPrint;
            InputStream report = getClass().getResourceAsStream("/reports/rotulo.jasper");
            jasperPrint = Reportes.llenarReporte(report, params, new JRBeanCollectionDataSource(rotulos));

            Reportes.generarReportePdf(jasperPrint, out);
                
            String nombre = lblRadicado.getValue();
            String tituloVentana = Labels.getLabel("commons.titulo.soporteEntrega");
            
            final Media media = new AMedia(nombre+".pdf", "pdf", "application/pdf", out.toByteArray());
            
            Window window = new Window(tituloVentana, "normal", true); //titulo, borde, closable
            window.setHeight("90%");
            window.setWidth("90%");
            window.setStyle("max-height:100%:max-width:100%;margin: auto;text-align: center;");
            window.setParent(windowImprimirRotulos);
            window.doModal();
            window.setSizable(true);
            
            Iframe iframe = new Iframe();
            iframe.setHflex("true");
            iframe.setVflex("true");
            iframe.setStyle("max-height:100%:max-width:100%;margin: auto;text-align: center;");
            iframe.setParent(window);
            iframe.setContent(media);
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
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
     * @return the remitente
     */
    public String getRemitente() {
        return remitente;
    }

    /**
     * @param remitente the remitente to set
     */
    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    /**
     * @return the isCreacion
     */
    public boolean isCreacion() {
        return isCreacion;
    }

    /**
     * @param isCreacion the isCreacion to set
     */
    public void setCreacion(boolean isCreacion) {
        this.isCreacion = isCreacion;
    }
    
}
