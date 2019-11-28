/*
 * Archivo: MdtMunicipioProxyService.java
 * Fecha creacion: 12/05/2017
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
package co.com.grupoasd.documental.cliente.catalogo;

import java.util.List;
import co.com.grupoasd.documental.cliente.catalogo.iface.MdtMunicipioIface;
import co.com.grupoasd.documental.cliente.catalogo.model.MdtMunicipio;
import co.com.grupoasd.documental.cliente.comun.ServiciosEndpoint;

/**
 * Proxy que permite la invocacion remota de los servicios del recurso municipio.
 * 
 * @author cestrada
 *
 */
public class MdtMunicipioProxyService {
    
    /**
     * Constructor privado. Esta clase no debe instanciarse.
     */
    private MdtMunicipioProxyService(){
        
    }
    
    /**
     * Lista todos los municipios.
     * 
     * @return Lista todos los municipios.
     */
    public static List<MdtMunicipio> listar() {
        return ServiciosEndpoint.get().proxy(MdtMunicipioIface.class).listar();
    }

    /**
     * Lista todos los municipios por código dane del departamento.
     * 
     * @param codigoDane Filtro por código dane del departamento.
     * @return Lista todos los municipios.
     */
    public static List<MdtMunicipio> listarPorDepartamento(String codigoDane) {
        return ServiciosEndpoint.get().proxy(MdtMunicipioIface.class).listarPorDepartamento(codigoDane);
    }

}
