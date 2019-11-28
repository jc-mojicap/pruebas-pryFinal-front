/*
 * Archivo: EmpresaService.java
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
package co.com.grupoasd.documental.presentacion.service.catalogo.iface;

import co.com.grupoasd.documental.cliente.catalogo.model.Empresa;
import co.com.grupoasd.documental.cliente.comun.Token;
import java.util.List;

/**
 * Servicios del recurso empresa. 
 */
public interface EmpresaService {
    /**
     * Lista las empresas activas e inactivas.
     * @param inactivo Filtro para aplicar.
     * @return Listado con empresas. Si no existen retorna vacio.
     * @author Juan Carlos Castellanos
     */
    List<Empresa> listar(Boolean inactivo);
    /**
     * Crea una nueva empresa.
     * @param token Token con identificacion del usuario.
     * @param empresa Objeto Empresa, unicamente es obligatorio que se asignen 
     * los atributos nombre e inactivo del objeto.
     * @return Objeto Empresa creado con identificador asignado.
     * @author Juan Carlos Castellanos
     */
    Empresa crear(Token token, Empresa empresa);
}