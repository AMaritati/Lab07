package it.polito.tdp.poweroutages.model;

import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		Ricerca r = new Ricerca();
		Nerc nerc = new Nerc (3,"MAAC");
		System.out.println(model.getNercList());
		List<PowerOutages> l = model.getPowerOutages(nerc);
		System.out.println(model.annoMax(l));
		
		System.out.println(r.controlloAnno(l));

		for (PowerOutages a : l) {
			System.out.println(a.toString());
		}
		
		
		
	}

}
