package co.com.grupoasd.documental.cliente.trd.model;

public class TrdSubserieOpcionAdcDispPK {
  
    private int trdSubserieId;
	
    private int opcDispId;

    public TrdSubserieOpcionAdcDispPK() {
    }

    public TrdSubserieOpcionAdcDispPK(int trdSubserieId, int opcDispId) {
        this.trdSubserieId = trdSubserieId;
        this.opcDispId = opcDispId;
    }

    public int getTrdSubserieId() {
        return trdSubserieId;
    }

    public void setTrdSubserieId(int trdSubserieId) {
        this.trdSubserieId = trdSubserieId;
    }

    public int getOpcDispId() {
        return opcDispId;
    }

    public void setOpcDispId(int opcDispId) {
        this.opcDispId = opcDispId;
    }
}
