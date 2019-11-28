/*
 * Archivo: DownloadController.java
 * Fecha creacion: 19/04/2017
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
package co.com.grupoasd.documental.presentacion.controller.comun;

import co.com.grupoasd.documental.presentacion.comun.PropertiesMessagesUtil;
import co.com.grupoasd.documental.presentacion.comun.dto.InfoMedia;
import co.com.grupoasd.documental.presentacion.comun.exception.GeneralException;
import co.com.grupoasd.documental.presentacion.comun.vo.ConstantAttributes;
import co.com.grupoasd.documental.presentacion.controller.util.UiUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Timer;
import org.zkoss.zul.Window;

/**
 * @author jmaldonado
 */
public class DownloadController extends GenericForwardComposer<Window> {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 6414916100550837025L;
	private static final Logger LOG = Logger.getLogger(DownloadController.class.getName());
	boolean primera = true;

	public void onCreate(Event event) {
		try {
			Object object = session.getAttribute(ConstantAttributes.DOWNLOAD);
			if (object != null) {
				if (object instanceof Media) {
					Filedownload.save((Media) object);
				} else if (object instanceof File) {
					Filedownload.save((File) object, null);
				} else if (object instanceof byte[]) {
					Filedownload.save((byte[]) object, null,
							(String) session.getAttribute(ConstantAttributes.FILE_NAME));
				} else if (object instanceof String) {
					Filedownload.save((String) object, null);
					// Executions.sendRedirect((String) object);
				} else if (object instanceof List) {
					@SuppressWarnings("unchecked")
					List<Object> list = (List<Object>) object;
					for (Object obj : list) {
						if (obj instanceof InfoMedia) {
							InfoMedia infoMedia = (InfoMedia) object;
							if (infoMedia.getBytesArchivo() != null) {
								Filedownload.save(infoMedia.getBytesArchivo(), null, infoMedia.getNombreArchivo());
							}
						} else {
							ByteArrayOutputStream out = new ByteArrayOutputStream();
							ObjectOutputStream os = new ObjectOutputStream(out);
							os.writeObject(obj);
							Filedownload.save(out.toByteArray(), null, null);
						}
					}
				} else {
					ByteArrayOutputStream out = new ByteArrayOutputStream();
					ObjectOutputStream os = new ObjectOutputStream(out);
					os.writeObject(object);
					Filedownload.save(out.toByteArray(), null, null);
				}
			}

			Timer timer = new Timer(60000);
			timer.setParent(self);
			timer.addEventListener(Events.ON_TIMER, new EventListener<Event>() {
				@Override
				public void onEvent(Event event) throws Exception {
					// if (!primera) {
					Clients.evalJavaScript("self.close()");
					// }
					// primera = false;
				}
			});
			timer.start();

			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(60000);
					} catch (InterruptedException ex) {
						LOG.log(Level.SEVERE, null, ex);
					} finally {
						if (session.hasAttribute(ConstantAttributes.DOWNLOAD)) {
							Object object = session.getAttribute(ConstantAttributes.DOWNLOAD);
							if (object instanceof File) {
								FileUtils.deleteQuietly((File) object);
							}
							session.removeAttribute(ConstantAttributes.DOWNLOAD);
						}
					}
				}
			});
			t.start();

			self.addEventListener(Events.ON_CLICK, new EventListener<MouseEvent>() {
				@Override
				public void onEvent(MouseEvent event) throws Exception {
					Clients.evalJavaScript("self.close()");
				}
			});
		} catch (Exception ex) {
            if (ex instanceof GeneralException) {
                UiUtils.mostrarError(PropertiesMessagesUtil.instance()
                        .get(((GeneralException) ex).getExceptionType().name().toLowerCase()));
            } else {
                LOG.log(Level.SEVERE, null, ex);
                UiUtils.mostrarError(
                        PropertiesMessagesUtil.instance().get("no_identificado", ex.getLocalizedMessage()));
            }
		}
	}

	public void onClick$btnCerrar() {
		Clients.evalJavaScript("self.close()");
	}

	public void onOK$btnCerrar() {
		Clients.evalJavaScript("self.close()");
	}
}
