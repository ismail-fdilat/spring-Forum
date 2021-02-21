package com.formation.msglc.models;

public class Nav {
	private String CDescription;
	private String SCDescription;
	private String SDescription;
	private Long idCat;
	private Long idSCat;
	private Long idSuj;
	public String getCDescription() {
		return CDescription;
	}
	public void setCDescription(String cDescription) {
		CDescription = cDescription;
	}
	public String getSCDescription() {
		return SCDescription;
	}
	public void setSCDescription(String sCDescription) {
		SCDescription = sCDescription;
	}
	public String getSDescription() {
		return SDescription;
	}
	public void setSDescription(String sDescription) {
		SDescription = sDescription;
	}
	public Long getIdCat() {
		return idCat;
	}
	public void setIdCat(Long idCat) {
		this.idCat = idCat;
	}
	public Long getIdSCat() {
		return idSCat;
	}
	public void setIdSCat(Long idSCat) {
		this.idSCat = idSCat;
	}
	public Long getIdSuj() {
		return idSuj;
	}
	public void setIdSuj(Long idSuj) {
		this.idSuj = idSuj;
	}
}
