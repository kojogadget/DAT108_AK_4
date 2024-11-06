package no.hvl.dat108.oblig4.util;

import java.util.Optional;
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
	 * TODO
	 *
	 * @param deltager
	 * @return
	 */
	public boolean erRegistrert(DeltagerSkjema deltager) {
		Optional<Deltager> sok = deltagerRepo.findById(deltager.getMobil());
	
		return !sok.isEmpty();
	};

	/**
	 * TODO
	 *
	 * @param deltager
	 */
	public void registrer(DeltagerSkjema deltager) {
		String salt = passordService.genererTilfeldigSalt();
		String hash = passordService.hashMedSalt(deltager.getPassord(), salt);
		Passord passord = new Passord(hash, salt);

		deltagerRepo.save(new Deltager(deltager, passord));
	}
}
