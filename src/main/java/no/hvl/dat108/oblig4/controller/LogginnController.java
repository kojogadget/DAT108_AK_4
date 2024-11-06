package no.hvl.dat108.oblig4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpServletRequest;
import no.hvl.dat108.oblig4.model.Deltager;
import no.hvl.dat108.oblig4.util.DeltagerService;
import no.hvl.dat108.oblig4.util.LogginnService;

@Controller
@RequestMapping("/logginn")
public class LogginnController {
	@Autowired private DeltagerService deltagerService;
	@Autowired private LogginnService logginnService;

	@GetMapping
	public String visLogginn() {
		return "logginn";
	}

	@PostMapping
	public String logginn(Model model, String mobil, String passord, 
						RedirectAttributes ra, HttpServletRequest request) {
		Deltager deltager = deltagerService.finnDeltager(mobil);

		if (deltager == null) {
			ra.addFlashAttribute("redirectMessage", "Mobil og/eller passord er feil...");
			return "redirect:logginn";
		}

		if (!deltagerService.passordKorrekt(deltager, passord)) {
			ra.addFlashAttribute("redirectMessage", "Mobil og/eller passord er feil...");
			return "redirect:logginn";
		}

		logginnService.loggInnBruker(request, deltager);
		return "redirect:deltagerliste";
	}
}
