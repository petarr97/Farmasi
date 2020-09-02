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
	public Boolean uspjesno = false;

	public String getTrenutnaTabela() {
		return trenutnaTabela;
	}

	public void setTrenutnaTabela(String trenutnaTabela) {
		this.trenutnaTabela = trenutnaTabela;
	}

	public int tipKorisnika;

	public int getTipKorisnika() {
		return tipKorisnika;
	}

	public void setTipKorisnika(int tipKorisnika) {
		this.tipKorisnika = tipKorisnika;
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