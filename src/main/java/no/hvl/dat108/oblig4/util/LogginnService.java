package no.hvl.dat108.oblig4.util;

import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import no.hvl.dat108.oblig4.model.Deltager;

@Service
public class LogginnService {
	public void loggUtBruker(HttpSession session) {
		if (session != null) {
			session.invalidate();		
		}
	}

	public void loggInnBruker(HttpServletRequest request, Deltager deltager) {
		loggUtBruker(request.getSession());
		
		HttpSession session = request.getSession();
		session.setAttribute("deltager", deltager);
		session.setMaxInactiveInterval(120); 
	}
	
	public boolean erBrukerInnlogget(HttpSession session) {
		return session != null && session.getAttribute("deltager") != null;
	}
}
