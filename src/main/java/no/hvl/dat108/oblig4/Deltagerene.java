package no.hvl.dat108.oblig4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Deltagerene {

	public static List<Deltager> alleDeltagere = new ArrayList<>();
	public static Map<String, Deltager> deltagereMap = new HashMap<>();

	static {
		Deltager d1 = new Deltager("Donald", "Larsen", "91111111", "Passord1!", "mann");
		Deltager d2 = new Deltager("Dolly", "Hansen", "92222222", "Passord1!", "kvinne");
		Deltager d3 = new Deltager("Ole", "Olsen", "93333333", "Passord1!", "mann");
		Deltager d4 = new Deltager("Dole", "Knutsen", "94444444", "Passord1!", "mann");
		Deltager d5 = new Deltager("Doffen", "Jonsen", "95555555", "Passord1!", "mann");

		alleDeltagere.add(d1);
		alleDeltagere.add(d2);
		alleDeltagere.add(d3);
		alleDeltagere.add(d4);
		alleDeltagere.add(d5);

		deltagereMap.put(d1.getMobil(), d1);
		deltagereMap.put(d2.getMobil(), d2);
		deltagereMap.put(d3.getMobil(), d3);
		deltagereMap.put(d4.getMobil(), d4);
		deltagereMap.put(d5.getMobil(), d5);
	};
}
