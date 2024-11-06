package no.hvl.dat108.oblig4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpSession;
import no.hvl.dat108.oblig4.util.DeltagerService;
import no.hvl.dat108.oblig4.util.LogginnService;

@Controller
public class DeltagerController {
	@Autowired private DeltagerService deltagerService;
	@Autowired private LogginnService logginnService; 

	/**
     * Viser en liste over alle deltagere, sortert alfabetisk etter fornavn og etternavn.
     * 
     * @param model Model-objektet som brukes til å sende deltagerlisten til visningen.
     * @return String - navnet på visningen for deltagerlisten.
     */
	@GetMapping("/deltagerliste")
	public String getDeltagerliste(Model model, HttpSession session) {
		if (!logginnService.erBrukerInnlogget(session)) {
			return "redirect:logginn";
		}

		model.addAttribute("deltagerListe", deltagerService.finnAlle());
		return "deltagerliste";
	}
}
