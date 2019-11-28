/*
* Archivo: DateUtils.java
* Fecha creacion = 21/03/2017
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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Clase de utilidades para manejo de fechas.
 * @author JuanMojica
 *
 */
public class DateUtils {
    
    private static final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000;
   // private static final long MILLSECS_PER_HOUR = 60 * 60 * 1000;
   // private static final long MILLSECS_PER_MINUTE = 60 * 1000;
	
	/**
	 * Metodo que recibe una fecha de tipo <code>Date</code> y la convierte a un <code>String</code> con el formato <code>dd/MM/yyyy HH:mm:ss</code> 
	 * @param date La fecha a convertir
	 * @return La representacion en <code>String</code> de la fecha
	 */
	public static String dateToString(Date date){
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return formatter.format(date);
	}
	
	/**
	 * Metodo que recibe una fecha de tipo <code>Date</code> y un formato de fecha (de tipo <code>String</code>) y convierte la fecha recibida en una
	 * expresion <code>String</code> con el formato recibido 
	 * @param date La fecha a convertir 
	 * @param formato El formato en el que se desea que la fecha sea representada
	 * @return La representacion en <code>String</code> de la fecha
	 */
	public static String dateToString(Date date, String formato){
		
		SimpleDateFormat formatter = new SimpleDateFormat(formato);
		return formatter.format(date);
	}
	
	/**
	 * Metodo que recibe una fecha de tipo <code>Date</code> y un formato de fecha (de tipo <code>SimpleDateFormat</code>) y convierte la fecha recibida en una
	 * expresion <code>String</code> con el formato recibido 
	 * @param date La fecha a convertir 
	 * @param formato El formato en el que se desea que la fecha sea representada
	 * @return La representacion en <code>String</code> de la fecha
	 */
	public static String dateToString(Date date, SimpleDateFormat formato){;
	
		return formato.format(date);
	}
	
	/**
	 * Toma una fecha que viene de tipo <code>String</code> en formato <code>dd/MM/yyyy</code> y la convierte a tipo <code>Date</code>
	 * @param fecha fecha en formato <code>dd/MM/yyyy</code> a ser convertida
	 * @return La fecha en tipo de dato <code>Date</code>
	 * @throws Exception Si la fecha no esta en formato <code>dd/MM/yyyy</code>
	 */
	public static Date stringToDate(String fecha) throws Exception{
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = formatter.parse(fecha);
		return date;
	}
	
	/**
	 * Recibe una fecha que viene de tipo <code>String</code> y el formato de la fecha (tambien tipo <code>String</code>) y la convierte a tipo <code>Date</code> 
	 * @param fecha fecha a ser convertida
	 * @param formatoFecha formato en el que viene la fecha
	 * @return La fecha en tipo de dato <code>Date</code>
	 * @throws Exception Si el formato de la fecha y la fecha recibidos no se corresponden 
	 */
	public static Date stringToDate(String fecha, String formatoFecha) throws Exception{
		
		SimpleDateFormat formatter = new SimpleDateFormat(formatoFecha);
		Date date = formatter.parse(fecha);
		return date;
	}
	
	/**
	 * Recibe una fecha que viene de tipo <code>String</code> y el formato de la fecha de tipo <code>SimpleDateFormat</code> y la convierte a tipo <code>Date</code>
	 * @param fecha fecha a ser convertida
	 * @param formatoFecha formato de la fecha recibida
	 * @return La fecha en tipo de dato <code>Date</code>
	 * @throws Exception Si el formato de la fecha y la fecha recibidos no se corresponden
	 */
	public static Date stringToDate(String fecha, SimpleDateFormat formatoFecha) throws Exception{
		
		Date date = formatoFecha.parse(fecha);
		return date;
	}
	
    /**
     * Obtiene la diferencia de días entre 2 fechas
     *
     * @param fecha1
     * @param fecha2
     * @return La cantidad de días que hay desde la fecha inicial hasta la fecha
     * final. Si la fecha final es menor a la inicial, se devuelve la cantidad
     * de días en negativo
     */
    public static int obtenerDiferenciaDias(Date fecha1, Date fecha2) {
        if (fecha1 == null) {
            fecha1 = new Date();
            fecha1.setTime(0);
        }
        if (fecha2 == null) {
            fecha2 = new Date();
            fecha1.setTime(0);
        }
        double diferencia = (fecha2.getTime() - fecha1.getTime()) / MILLSECS_PER_DAY;
        return new BigDecimal(diferencia).setScale(0, RoundingMode.DOWN).intValue();
    }

    /**
     * Obtiene la diferencia de días entre 2 fechas
     *
     * @param fecha1
     * @param fecha2
     * @return La cantidad de días que hay desde la fecha inicial hasta la fecha
     * final. Si la fecha final es menor a la inicial, se devuelve la cantidad
     * de días en negativo
     */
    public static int obtenerDiferenciaDiasSinHora(Date fecha1, Date fecha2) {
        if (fecha1 == null) {
            fecha1 = new Date();
            fecha1.setTime(0);
        }
        if (fecha2 == null) {
            fecha2 = new Date();
            fecha1.setTime(0);
        }

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(fecha1);
        cal1.set(Calendar.HOUR_OF_DAY, 0);
        cal1.set(Calendar.MINUTE, 0);
        cal1.set(Calendar.SECOND, 0);
        cal1.set(Calendar.MILLISECOND, 0);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(fecha2);
        cal2.set(Calendar.HOUR_OF_DAY, 0);
        cal2.set(Calendar.MINUTE, 0);
        cal2.set(Calendar.SECOND, 0);
        cal2.set(Calendar.MILLISECOND, 0);

        double diferencia = (cal2.getTime().getTime() - cal1.getTime().getTime()) / MILLSECS_PER_DAY;
        return new BigDecimal(diferencia).setScale(0, RoundingMode.DOWN).intValue();
    }
	
}
