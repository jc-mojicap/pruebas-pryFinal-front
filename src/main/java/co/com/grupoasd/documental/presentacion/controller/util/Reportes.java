/*
* Archivo: Reportes.java
* Fecha creacion = 07/04/2017
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
package co.com.grupoasd.documental.presentacion.controller.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Clase para la manipulación de reportes
 * @author JuanMojica
 *
 */
public class Reportes {

    /**
     * Compila un reporte.
     * @param reporte Reporte en formato JRXML.
     * @return reporte en formato JASPER.
     * @throws JRException
     */
    public static JasperReport compilarReporte(InputStream reporte) throws JRException{
        return JasperCompileManager.compileReport(reporte);
    }
    
    /**
     * Llena un reporte.
     * @param report Reporte en formato JasperReport.
     * @param params <code>Map</code> con los parametros que se pasan al reporte.
     * @param dataSource Fuente de datos para el reporte
     * @return Objeto JasperPrint con el reporte generado.
     * @throws JRException
     */
    public static JasperPrint llenarReporte(JasperReport report, Map<String, Object> params, JRBeanCollectionDataSource dataSource) throws JRException{
        return JasperFillManager.fillReport(report, params, dataSource);
    }
    
    /**
     * Llena un reporte.
     * @param report Reporte en formato InputStream.
     * @param <code>Map</code> con los parametros que se pasan al reporte.
     * @param dataSource Fuente de datos para el reporte
     * @return Objeto JasperPrint con el reporte generado.
     * @throws JRException
     */
    public static JasperPrint llenarReporte(InputStream report, Map<String, Object> params, JRBeanCollectionDataSource dataSource) throws JRException{
        return JasperFillManager.fillReport(report, params, dataSource);
    }
    
    /**
     * Recibe un pdf y retorna una representación de dicho archivo en imagen.
     * @param pdf InputStream con el contenido del pdf.
     * @return <code>BufferedImaged</code> con la representación del archivo.
     * @throws Exception
     */
    public static BufferedImage convertirPdfAImagen(InputStream pdf) throws Exception{
        return PDFUtils.PDFToImage(pdf);
    }
    
    /**
     * Genera un reporte en formato Pdf y lo retorna en un byteArrayOutputStream
     * @param jasperPrint Objeto <code>JasperPrint</code> con el reporte generado.
     * @param outputStream Objeto <code>ByteArrayOutputStream</code> que es el 
     * mismo en el que se cargue el archivo. 
     * @return El mismo objeto <code>ByteArrayOutputStream</code> con el archivo cargado. 
     * @throws JRException
     */
    public static ByteArrayOutputStream generarReportePdf(JasperPrint jasperPrint, ByteArrayOutputStream outputStream) throws JRException{
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        return outputStream;
    }
    
}
