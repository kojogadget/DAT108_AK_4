package no.hvl.dat108.oblig4;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.validation.Valid;

@Controller
public class PaameldingController {
	    
	/**
     * Henter påmeldingsskjemaet.
     * 
     * @return String - navnet på visningen for påmeldingsskjemaet.
     */
	@GetMapping("/paamelding")
	public String getPaamelding() {
		return "paameldingSkjema";
	}

	/**
     * Prosesserer påmeldingsskjemaet når det sendes inn.
     * Hvis det er valideringsfeil, blir brukeren omdirigert tilbake til påmeldingsskjemaet med feilmeldinger.
     * Hvis mobilnummeret allerede er registrert, blir brukeren også omdirigert med en feilmelding.
     * Hvis alt er ok, blir deltageren lagret og brukeren omdirigert til bekreftelsessiden.
     * 
     * @param deltager         Det validerte deltager-objektet fra skjemaet.
     * @param bindingResult    Resultat av valideringen, brukes for å sjekke om det finnes feil.
     * @param re               RedirectAttributes-objektet som brukes til å sende flash-attributter mellom requests.
     * @return String - omdirigering til enten bekreftelsessiden eller tilbake til påmeldingsskjemaet ved feil.
     */
	@PostMapping("/paameldt")
	public String postPaameldt(@Valid @ModelAttribute("deltager") Deltager deltager,
									BindingResult bindingResult,
									RedirectAttributes re) {

		re.addFlashAttribute("fornavn", deltager.getFornavn());
		re.addFlashAttribute("etternavn", deltager.getEtternavn());
		re.addFlashAttribute("mobil", deltager.getMobil());
		re.addFlashAttribute("kjonn", deltager.getKjonn());

		if (bindingResult.hasErrors()) {
			List<String> feilmeldinger = bindingResult.getAllErrors().stream()
												.map(error -> error.getDefaultMessage())
												.toList();
			re.addFlashAttribute("feilmeldinger", feilmeldinger);
			return "redirect:paamelding";
		}

		if (Deltagerene.deltagereMap.containsKey(deltager.getMobil())) {
			re.addFlashAttribute("feilmeldinger", List.of("Mobilnummer er allerede registrert!"));
			return "redirect:paamelding";
		}

		Deltagerene.deltagereMap.put(deltager.getMobil(), deltager);
		Deltagerene.deltagereList.add(deltager);
		return "redirect:paameldt";
	}

	/**
     * Viser bekreftelsessiden etter vellykket påmelding.
     * Hvis påmeldingen ikke er fullført (ingen attributter i modellen), omdirigeres brukeren tilbake til påmeldingsskjemaet.
     * 
     * @param model Model-objektet som brukes til å sende data til visningen.
     * @return String - navnet på visningen for bekreftelsessiden eller omdirigering til påmeldingsskjemaet.
     */
	@GetMapping("/paameldt")
	public String getPaameldt(Model model) {
		return model.getAttribute("fornavn") == null ? "redirect:paamelding" : "paameldt";
	}
}
