package it.polito.tdp.extflightdelays.model;

public class CollegamentoAeroporti {
	
	public Integer aeropOrig;
	public Integer aeropDest;
	public Integer distanza;
	
	public CollegamentoAeroporti(Integer aeropOrig, Integer aeropDest, Integer distanza) {
		super();
		this.aeropOrig = aeropOrig;
		this.aeropDest = aeropDest;
		this.distanza = distanza;
	}

	public Integer getAeropOrig() {
		return aeropOrig;
	}

	public void setAeropOrig(Integer aeropOrig) {
		this.aeropOrig = aeropOrig;
	}

	public Integer getAeropDest() {
		return aeropDest;
	}

	public void setAeropDest(Integer aeropDest) {
		this.aeropDest = aeropDest;
	}

	public Integer getDistanza() {
		return distanza;
	}

	public void setDistanza(Integer distanza) {
		this.distanza = distanza;
	}
	
}
