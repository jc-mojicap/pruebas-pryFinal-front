package co.com.grupoasd.documental.cliente.trd.model;

/**
 * Model TrdSubserieOpcionAdcDisp
 * @author gmora
 * @since Junio 30 de 2017
 */
public class TrdSubserieOpcionAdcDisp {
	
	protected TrdSubserieOpcionAdcDispPK trdSubserieOpcionAdcDispPK;
	   
    private boolean activo;
    
    private Integer trdSubserie;
   
    private Integer trdOpcionDisposicionFinal;

    public TrdSubserieOpcionAdcDisp() {
    }

    public TrdSubserieOpcionAdcDisp(TrdSubserieOpcionAdcDispPK trdSubserieOpcionAdcDispPK) {
        this.trdSubserieOpcionAdcDispPK = trdSubserieOpcionAdcDispPK;
    }

    public TrdSubserieOpcionAdcDisp(TrdSubserieOpcionAdcDispPK trdSubserieOpcionAdcDispPK, boolean activo) {
        this.trdSubserieOpcionAdcDispPK = trdSubserieOpcionAdcDispPK;
        this.activo = activo;
    }

    public TrdSubserieOpcionAdcDisp(int trdSubserieId, int opcDispId) {
        this.trdSubserieOpcionAdcDispPK = new TrdSubserieOpcionAdcDispPK(trdSubserieId, opcDispId);
    }
    
    public TrdSubserieOpcionAdcDispPK getTrdSubserieOpcionAdcDispPK() {
        return trdSubserieOpcionAdcDispPK;
    }
    
    public void setTrdSubserieOpcionAdcDispPK(TrdSubserieOpcionAdcDispPK trdSubserieOpcionAdcDispPK) {
        this.trdSubserieOpcionAdcDispPK = trdSubserieOpcionAdcDispPK;
    }
    
    
	/**
	 * @return the trdOpcionDisposicionFinal
	 */
	public Integer getTrdOpcionDisposicionFinal() {
		return trdOpcionDisposicionFinal;
	}

	/**
	 * @param trdOpcionDisposicionFinal the trdOpcionDisposicionFinal to set
	 */
	public void setTrdOpcionDisposicionFinal(Integer trdOpcionDisposicionFinal) {
		this.trdOpcionDisposicionFinal = trdOpcionDisposicionFinal;
	}

	/**
	 * @return the activo
	 */
	public boolean isActivo() {
		return activo;
	}

	/**
	 * @param activo the activo to set
	 */
	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	/**
	 * @return the trdSubserie
	 */
	public Integer getTrdSubserie() {
		return trdSubserie;
	}

	/**
	 * @param trdSubserie the trdSubserie to set
	 */
	public void setTrdSubserie(Integer trdSubserie) {
		this.trdSubserie = trdSubserie;
	}
}
