package no.hvl.dat108.oblig4.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import no.hvl.dat108.oblig4.model.Deltager;
import no.hvl.dat108.oblig4.model.DeltagerSkjema;
import no.hvl.dat108.oblig4.util.DeltagerService;
import no.hvl.dat108.oblig4.util.LogginnService;
import no.hvl.dat108.oblig4.util.PaameldingService;

@Controller
public class PaameldingController {
	@Autowired private PaameldingService registrerService;
	@Autowired private DeltagerService deltagerService;
	@Autowired private LogginnService logginnService;
	    
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
	@PostMapping("/paamelding")
	public String postPaameldt(@Valid @ModelAttribute("deltager") DeltagerSkjema deltager,
									BindingResult bindingResult,
									RedirectAttributes re,
									HttpServletRequest request) {

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

		if (deltagerService.mobilRegistrert(deltager.getMobil())) {
			re.addFlashAttribute("feilmeldinger", List.of("Mobilnummer er allerede registrert!"));
			return "redirect:paamelding";
		}
		
		Deltager regDeltager = registrerService.registrer(deltager);
		logginnService.loggInnBruker(request, regDeltager);
		
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
	public String getPaameldt(HttpSession session) {
		if (!logginnService.erBrukerInnlogget(session)) {
			return "redirect:paamelding";
		}
		return "paameldt";
	}
}
