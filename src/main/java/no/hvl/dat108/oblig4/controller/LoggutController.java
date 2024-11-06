package no.hvl.dat108.oblig4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpSession;
import no.hvl.dat108.oblig4.util.LogginnService;

@Controller
@RequestMapping("/loggut")
public class LoggutController {
	@Autowired private LogginnService logginnService;

	@PostMapping
	public String loggUt(HttpSession session, RedirectAttributes ra) {
		logginnService.loggUtBruker(session);

		ra.addFlashAttribute("redirectMessage", "Du er nå logget ut");
		return "redirect:logginn";
	}
}
