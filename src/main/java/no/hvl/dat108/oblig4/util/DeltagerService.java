package no.hvl.dat108.oblig4.util;

import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import no.hvl.dat108.oblig4.model.Deltager;
import no.hvl.dat108.oblig4.model.DeltagerRepo;

@Service
public class DeltagerService {

	@Autowired private DeltagerRepo deltagerRepo;

	/**
	 * TODO 
	 *
	 * @return
	 */
	public List<Deltager> finnAlle() {
		return deltagerRepo.findAll().stream()
									.sorted(Comparator.comparing(Deltager::getFornavn)
										.thenComparing(Deltager::getEtternavn))
									.toList();
	}
}
