/*
* Archivo: PDFUtils.java
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
import java.io.InputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

/**
 * Clase de utilidades para manejo de PDFs.
 * @author JuanMojica
 *
 */
public class PDFUtils {
    
    /**
     * Convierte la primera página de un documento pdf en una imagen. 
     * @param inputStream Representación del documento pdf en InputStream.
     * @return BufferedImage con la imagen generada.
     * @throws Exception
     */
    public static BufferedImage PDFToImage(InputStream inputStream) throws Exception {
        
        int resolution = 300;
        
        PDDocument document = PDDocument.load(inputStream);
        PDPage page = (PDPage) document.getDocumentCatalog().getAllPages().get(0);
        BufferedImage image = page.convertToImage(BufferedImage.TYPE_INT_RGB, resolution);  

        return image;
        
    }
    
}
