/*
 * Archivo: AccionApp.java
 * Fecha creacion: 11/04/2017
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
package co.com.grupoasd.documental.cliente.autorizacion;

/**
 * Enum de acciones de la aplicación.
 * @author cestrada
 *
 */
public enum AccionApp {

    RADICAR_CORRESPONDENCIA(1500,""),
    ADMINISTRAR_TERCEROS(1510,""),
    CONSULTAR_RADICADO(1520,""),
    
    ACTUALIZAR_RADICADO(1530, "Actualizar Radicado"),
    EDITAR_REMITENTE_CORRESPONDENCIA(1532, ""),
    OPCIONES_RADICADO_CORRESPONDENCIA(1533, "" ),
    EDITAR_DESTINATARIOS_CORRESPONDENCIA( 1534, ""),
    ADJUNTAR_ARCHIVOS_CORRESPONDENCIA(1535, ""),
    AGREGAR_COMENTARIO_CORRESPONDENCIA(1536, ""),
    
    ASIGNAR_RADICADO(1540, "Asignar Radicado"),
    GESTIONAR_RADICADO(1550, "Gestionar Radicado"),
    CONSULTAR_CORRESPONDENCIA_DE_DIFERENTES_USUARIOS(1560,"Consultar correspondencia de diferentes usuarios"),
    VER_DETALLE_RADICADO(1570, "Ver Detalle Radicado"),
    
    //TRD
    ADMINISTRAR_METADATOS(1000, ""),
    CONFIGURAR_PARAMETROS(1010, ""),
    ;
    
    /**
     * id.
     */
    private int id;
    /**
     * etiqueta.
     */
    private String etiqueta;    
    
    /**
     * Constructor.
     * @param id
     * @param etiqueta
     */
    private AccionApp(int id, String etiqueta){
        this.id = id;
        this.etiqueta = etiqueta;
    }
    
    /**
     * getId.
     * @return int
     */
    public int getId(){
        return id;
    }
    
    /**
     * getEtiqueta.
     * @return String
     */
    public String getEtiqueta(){
        return etiqueta;
    }
    
    /**
     * getAccion.
     * @param idAccion
     * @return AccionApp
     */
    public AccionApp getAccion(int idAccion){
        for(AccionApp accion : AccionApp.values()){
            if(accion.getId() == idAccion){
                return accion;
            }
        }
        return null;
    }
    

}
