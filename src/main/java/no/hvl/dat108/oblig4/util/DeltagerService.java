package no.hvl.dat108.oblig4.util;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import no.hvl.dat108.oblig4.model.Deltager;
import no.hvl.dat108.oblig4.model.DeltagerRepo;

@Service
public class DeltagerService {

	@Autowired private DeltagerRepo deltagerRepo;
	@Autowired private PassordService passordService;

	/**
	 * Henter alle deltakere fra databasen, sortert alfabetisk etter fornavn og deretter etternavn.
	 *
	 * @return en liste med alle deltakere, sortert alfabetisk
	 */
	public List<Deltager> finnAlle() {
		return deltagerRepo.findAll().stream()
									.sorted(Comparator.comparing(Deltager::getFornavn)
										.thenComparing(Deltager::getEtternavn))
									.toList();
	}

	/**
	 * Finner en deltaker basert på mobilnummeret.
	 *
	 * @param deltagerMobil mobilnummeret til deltakeren som skal søkes opp
	 * @return en instans av {@link Deltager} hvis den finnes, ellers {@code null}
	 */
	public Deltager finnDeltager(String deltagerMobil) {
		Optional<Deltager> sok = deltagerRepo.findById(deltagerMobil);
	
		return sok.isPresent() ? sok.get() : null;
	};

	/**
	 * Sjekker om et mobilnummer allerede er registrert i databasen.
	 *
	 * @param deltagerMobil mobilnummeret som skal sjekkes
	 * @return {@code true} hvis mobilnummeret er registrert, ellers {@code false}
	 */
	public boolean mobilRegistrert(String deltagerMobil) {
		Optional<Deltager> sok = deltagerRepo.findById(deltagerMobil);
	
		return sok.isPresent();
	};

	/**
	 * Verifiserer om et gitt passord er korrekt for en spesifikk deltaker.
	 *
	 * @param deltager deltakeren hvis passord skal verifiseres
	 * @param passord det oppgitte passordet som skal verifiseres
	 * @return {@code true} hvis passordet er korrekt, ellers {@code false}
	 */
	public boolean passordKorrekt(Deltager deltager, String passord) {
		String deltagerSalt = deltager.getPassord().getSalt();
		String deltagerHash = deltager.getPassord().getHash();
		String passordHash = passordService.hashMedSalt(passord, deltagerSalt);

		return passordHash.equals(deltagerHash);
	};
}
