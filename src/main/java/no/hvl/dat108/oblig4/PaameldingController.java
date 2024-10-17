package no.hvl.dat108.oblig4;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaameldingController {
	
	@GetMapping("/paamelding")
	public String paamelding(Model model) {
		return "paameldingSkjema";
	}
}
