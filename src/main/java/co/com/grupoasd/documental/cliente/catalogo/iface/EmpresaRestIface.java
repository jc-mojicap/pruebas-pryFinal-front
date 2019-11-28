/*
 * Archivo: EmpresaRestIface.java
 * Fecha creacion: 28/02/2017
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
package co.com.grupoasd.documental.cliente.catalogo.iface;

import co.com.grupoasd.documental.cliente.catalogo.model.Empresa;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Interface recurso empresa.
 * @author Juan Carlos Castellanos
 */
@Path("/catalogo")
public interface EmpresaRestIface {
    /**
     * Lista las empresas activas e inactivas.
     * @param inactivo Filtro para aplicar.
     * @return Listado con empresas. Si no existen retorna vacio.     
     */
	@GET
    @Path("/empresa")
    @Produces({ MediaType.APPLICATION_JSON })
    List<Empresa> listar(@QueryParam("inactivo") Boolean inactivo);
    
    /**
     * Crea una nueva empresa.     
     * @param empresa Empresa
     * @return Empresa creado con identificador asignado.     
     */
    @POST
    @Path("/empresa")
    @Consumes({ MediaType.APPLICATION_JSON })
    Empresa crear(Empresa empresa);
        
}
