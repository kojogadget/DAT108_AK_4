package no.hvl.dat108.oblig4;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class Deltager {

	@Pattern(regexp = "^\\p{L}{2,}((\\s+|-)\\p{L}{2,})*$", 
		message = "Tillate tegn er kun bokstaver, mellomrom og enkel bindestrek mellom delnavn")
	@NotNull(message = "Fornavn er obligatorisk")
	private String fornavn;

	@Pattern(regexp = "^\\p{L}{2,}((-)\\p{L}{2,})*$", 
		message = "Tillate tegn er kun bokstaver og enkel bindestrek mellom delnavn")
	@NotNull(message="Etternavn er obligatorisk")
	private String etternavn;
	 
	@Pattern(regexp = "^\\d{8}$", 
		message = "Nummer er kun gyldig ved 8 siffer")
	@NotNull(message = "Mobilnummer er obligatorisk")
	private String mobil;

	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
		message = "Passordet må være minst 8 tegn og minimum inneholde en liten og en stor bokstav, et tall og et symbol")
	@NotNull(message = "Passord er obligatorisk")
	private String passord;

	@Pattern(regexp = "^(mann|kvinne)$",
		message = "Kjønn kan kun være 'mann' eller 'kvinne' i denne oppgaven")
	@NotNull(message = "Kjønn er obligatorisk")
	private String kjonn;

	public Deltager() {}

	public Deltager(String fornavn, String etternavn, String mobil, String passord,
			String kjonn) {
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.mobil = mobil;
		this.passord = passord;
		this.kjonn = kjonn;
	}

	public String getFornavn() {
		return fornavn;
	}

	public void setFornavn(String fornavn) {
		this.fornavn = fornavn;
	}

	public String getEtternavn() {
		return etternavn;
	}

	public void setEtternavn(String etternavn) {
		this.etternavn = etternavn;
	}

	public String getMobil() {
		return mobil;
	}

	public void setMobil(String mobil) {
		this.mobil = mobil;
	}

	public String getPassord() {
		return passord;
	}

	public void setPassord(String passord) {
		this.passord = passord;
	}

	public String getKjonn() {
		return kjonn;
	}

	public void setKjonn(String kjonn) {
		this.kjonn = kjonn;
	}
}
