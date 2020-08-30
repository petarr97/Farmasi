package model;

public class Filteri {

	static Filteri instance = null;
	public String filterNarudzbaIme;

	public static Filteri getInstace() {
		if (instance == null)
			instance = new Filteri();
		return instance;
	}
}
