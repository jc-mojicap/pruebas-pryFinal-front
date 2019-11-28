/*
* Archivo: CorSecuenciaServiceImpl.java
* Fecha creacion = 03/04/2017
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
package co.com.grupoasd.documental.presentacion.service.correspondencia.impl;

import co.com.grupoasd.documental.cliente.comun.Token;
import co.com.grupoasd.documental.cliente.correspondencia.CorSecuenciaProxyService;
import co.com.grupoasd.documental.cliente.correspondencia.model.CorSecuencia;
import co.com.grupoasd.documental.presentacion.service.correspondencia.iface.CorSecuenciaService;

public class CorSecuenciaServiceImpl implements CorSecuenciaService {

    @Override
    public CorSecuencia obtenerPorId(Integer id) {
        return CorSecuenciaProxyService.obtenerPorId(id);
    }

    @Override
    public CorSecuencia obtenerPorEmpresa(Integer id) {
        return CorSecuenciaProxyService.obtenerPorEmpresa(id);
    }

    @Override
    public CorSecuencia crear(Token token, CorSecuencia corSecuencia) {
        return CorSecuenciaProxyService.crear(token, corSecuencia);
    }

}
