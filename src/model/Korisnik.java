/***********************************************************************
 * Module:  Korisnik.java
 * Author:  Petar
 * Purpose: Defines the Class Korisnik
 ***********************************************************************/

package model;

public class Korisnik {

	public static Korisnik instance = null;
	public String username;
	public String password;
	public int ID;
	public String trenutnaTabela = "";

	public String getTrenutnaTabela() {
		return trenutnaTabela;
	}

	public void setTrenutnaTabela(String trenutnaTabela) {
		this.trenutnaTabela = trenutnaTabela;
	}

	// privilegije
	public int dodavanje;
	public int brisanje;
	public int izmjena;
	public int prikaz;
	public int tipKorisnika;

	public int getTipKorisnika() {
		return tipKorisnika;
	}

	public void setTipKorisnika(int tipKorisnika) {
		this.tipKorisnika = tipKorisnika;
	}

	public int getDodavanje() {
		return dodavanje;
	}

	public void setDodavanje(int dodavanje) {
		this.dodavanje = dodavanje;
	}

	public int getBrisanje() {
		return brisanje;
	}

	public void setBrisanje(int brisanje) {
		this.brisanje = brisanje;
	}

	public int getIzmjena() {
		return izmjena;
	}

	public void setIzmjena(int izmjena) {
		this.izmjena = izmjena;
	}

	public int getPrikaz() {
		return prikaz;
	}

	public void setPrikaz(int prikaz) {
		this.prikaz = prikaz;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public static Korisnik getInstance() {
		if (instance == null)
			instance = new Korisnik();

		return instance;

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}