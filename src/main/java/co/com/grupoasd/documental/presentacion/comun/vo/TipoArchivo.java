/*
 * Archivo: TipoArchivo.java
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
package co.com.grupoasd.documental.presentacion.comun.vo;

/**
 * VO con los valores de tipos de archivos.
 * 
 * @author cestrada
 *
 */
public class TipoArchivo {

    /**
     * PDF.
     */
    public static final String PDF = "pdf";
    /**
     * TXT.
     */
    public static final String TXT = "txt";
    /**
     * CSV.
     */
    public static final String CSV = "csv";
    /**
     * XLS.
     */
    public static final String XLS = "xls";
    
    /**
     * getTiposArchivo.
     * @return Arreglo de los tipos de archivo.
     */
    public static String[] getTiposArchivo(){
        String[] tipos = {PDF, TXT, CSV, XLS};
        return tipos;
    }

}
