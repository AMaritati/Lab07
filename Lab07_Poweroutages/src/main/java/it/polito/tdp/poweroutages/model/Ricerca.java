package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.List;

public class Ricerca {
	Model m;
	List<PowerOutages> powerOutages;
	List<PowerOutages> bestSoluzione;
	private int maxCustomers;
	private double sommaOre;
	private int maxYears;
	private int maxHours;
	
	public Ricerca() {
		powerOutages = new ArrayList<>();
		bestSoluzione = null;
		m = new Model();
	}

	public List<PowerOutages> funzioneMax(Nerc nerc, int years, int hours) {
	    
		powerOutages = m.getPowerOutages(nerc);
		bestSoluzione = new ArrayList<>();
		
		maxCustomers = 0;
		sommaOre = 0;
		this.maxHours = hours;
		this.maxYears = years;
		
		List<PowerOutages> parziale = new ArrayList<>();
		
		cerca(parziale,0);  
		

		return bestSoluzione;
	}

	private void cerca(List<PowerOutages> parziale, int livello) {
		
		// casi terminali
		
		   // controllo ore
		if (this.getSommaOre(parziale)>this.maxHours) {  
			return;
		}
		  
		/*  // controllo lasso di tempo
		if (!controlloAnno(parziale)) {
			return;
		}
		*/
		if (getSomma(parziale)>maxCustomers) {
			maxCustomers=getSomma(parziale);
			bestSoluzione = new ArrayList<>(parziale);
			return;
		}
		
		if (livello == powerOutages.size()) {
			return;
		}
		
		// sotto problemi
		parziale.add(powerOutages.get(livello));
		cerca(parziale,livello+1);
		parziale.remove(powerOutages.get(livello));
		
		
		
	}


	private int getSomma(List<PowerOutages> parziale) {
		
		int somma = 0;
		
		for(PowerOutages p : parziale) {
			somma+= p.getCustomers();
		}
		
		return somma;
	}
	
		
		
	public boolean controlloAnno(List<PowerOutages> parziale) {
		int anno = m.annoMax(this.powerOutages);
		boolean flag = false;
		for (PowerOutages p : parziale) {
			if (anno-p.getDataInizio().getYear()<=this.maxYears) {
				flag = true;
			}
			else {
				flag = false;
			}
		}
		return flag;
	}

	public double getSommaOre(List<PowerOutages> parziale) {
		
		for (PowerOutages p : parziale) {
			this.sommaOre+=p.getOre();
			}
		
		return this.sommaOre;
	}


}
