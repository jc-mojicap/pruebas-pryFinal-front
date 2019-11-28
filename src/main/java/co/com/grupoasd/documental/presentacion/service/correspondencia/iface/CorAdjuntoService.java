/**
 * 
 */
package co.com.grupoasd.documental.presentacion.service.correspondencia.iface;

/**
 * Service Adjunto
 * @author alopez
 * @since Junio 16 de 2017
 */
public interface CorAdjuntoService {

	/**
	 * Obtiene los byte del adjunto
	 * @param radicadoId id radicado
	 * @param adjuntoId id adjunto
	 * @return byte[}
	 */
	byte[] obtenerBytesAdjunto(Long radicadoId, Integer adjuntoId);
}
