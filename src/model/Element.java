package model;

public class Element {

	public int id;
	public String naziv;
	public String tipSobe;
	public String ps_naziv;
	public String datum;
	public String ime;

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public String getPs_naziv() {
		return ps_naziv;
	}

	public void setPs_naziv(String ps_naziv) {
		this.ps_naziv = ps_naziv;
	}

	public Element(String naziv, int id) {
		this.naziv = naziv;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public String getTipSobe() {
		return tipSobe;
	}

	public void setTipSobe(String tipSobe) {
		this.tipSobe = tipSobe;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
}
