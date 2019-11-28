package co.com.grupoasd.documental.presentacion.listener;

import java.util.Map;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.util.Initiator;

import co.com.grupoasd.documental.presentacion.controller.util.SessionUtil;

public class ValidarSesion implements Initiator {

        @SuppressWarnings("rawtypes")
        @Override
        public void doInit(Page arg0, Map arg1) throws Exception {
            if (SessionUtil.getCurrentSession() == null) {
                Executions.sendRedirect("/identificacion/autenticacion.zul");
            }
        }

        public void doAfterCompose(Page arg0) throws Exception {
            // TODO ValidarLogin#doAfterCompose 
        }

        public boolean doCatch(Throwable arg0) throws Exception {
            // TODO ValidarLogin#doCatch
            return false;
        }

        public void doFinally() throws Exception {
            // TODO ValidarLogin#doFinally
        }
    }
