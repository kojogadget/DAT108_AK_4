package no.hvl.dat108.oblig4.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import no.hvl.dat108.oblig4.model.Deltager;
import no.hvl.dat108.oblig4.model.DeltagerRepo;
import no.hvl.dat108.oblig4.model.DeltagerSkjema;
import no.hvl.dat108.oblig4.model.Passord;

@Service
public class PaameldingService {

	@Autowired private PassordService passordService;
	@Autowired private DeltagerRepo deltagerRepo;
	
	/**
	 * Registrerer en deltager basert på input fra {@link DeltagerSkjema}.
	 * Passordet som er registrert hashes før lagring.
	 *
	 * @param deltager Ny deltager før lagring.
	 * @return Deltageren som er registrert i databasen.
	 */
	public Deltager registrer(DeltagerSkjema deltager) {
		String salt = passordService.genererTilfeldigSalt();
		String hash = passordService.hashMedSalt(deltager.getPassord(), salt);
		Passord passord = new Passord(hash, salt);

		return deltagerRepo.save(new Deltager(deltager, passord));
	}
}
