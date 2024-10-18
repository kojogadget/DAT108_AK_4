package no.hvl.dat108.oblig4;

import java.util.Comparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DeltagerController {

	/**
     * Viser en liste over alle deltagere, sortert alfabetisk etter fornavn og etternavn.
     * 
     * @param model Model-objektet som brukes til å sende deltagerlisten til visningen.
     * @return String - navnet på visningen for deltagerlisten.
     */
	@GetMapping("/deltagerliste")
	public String getDeltagerliste(Model model) {
		model.addAttribute("deltagerListe", Deltagerene.deltagereList.stream()
																	.sorted(Comparator.comparing(Deltager::getFornavn)
																			.thenComparing(Deltager::getEtternavn))
																	.toList());

		return "deltagerliste";
	}
}
