package no.hvl.dat108.oblig4.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(schema = "dat108_oblig4", name = "deltager")
public class Deltager {
	@Id private String mobil;
	private String fornavn;
	private String etternavn;
	private String kjonn;
	@Embedded private Passord passord;

	public Deltager() {}

	public Deltager(DeltagerSkjema deltager, Passord passord) {
		this.mobil = deltager.getMobil();
		this.fornavn = deltager.getFornavn();
		this.etternavn = deltager.getEtternavn();
		this.kjonn = deltager.getKjonn();
		this.passord = passord;
	}

	public String getMobil() {
		return mobil;
	}
	public void setMobil(String mobil) {
		this.mobil = mobil;
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
	public String getKjonn() {
		return kjonn;
	}
	public void setKjonn(String kjonn) {
		this.kjonn = kjonn;
	}
	public Passord getPassord() {
		return passord;
	}
	public void setPassord(Passord passord) {
		this.passord = passord;
	}
}

