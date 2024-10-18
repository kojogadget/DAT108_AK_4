package no.hvl.dat108.oblig4;

import java.util.Comparator;
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
	
	@GetMapping("/paamelding")
	public String paamelding() {
		return "paameldingSkjema";
	}

	@PostMapping("/paameldt")
	public String paameldtPost(Model model,
					@Valid @ModelAttribute("deltager") Deltager deltager,
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
		Deltagerene.alleDeltagere.add(deltager);
		return "redirect:paameldt";
	}

	@GetMapping("/paameldt")
	public String paameldtGet(Model model) {
		if (model.getAttribute("fornavn") == null) return "redirect:paamelding";

		return "paameldt";
	}

	@GetMapping("/deltagerliste")
	public String deltagerliste(Model model) {
		List<Deltager> sorterteDeltagere = Deltagerene.alleDeltagere.stream()
																	.sorted(Comparator.comparing(Deltager::getFornavn)
																			.thenComparing(Deltager::getEtternavn))
																	.toList();
		model.addAttribute("deltagerListe", sorterteDeltagere);

		return "deltagerliste";
	}
}
