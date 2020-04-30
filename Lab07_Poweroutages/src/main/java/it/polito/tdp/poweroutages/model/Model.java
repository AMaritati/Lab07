package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	List<PowerOutages> tempPowerOutages;
	List<PowerOutages> powerOutages;
	List<PowerOutages> bestSoluzione;
	private int maxCustomers;
	private double sommaOre;
	private int maxYears;
	private int maxHours;
	
	public Model() {
		podao = new PowerOutageDAO();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	
	public List<PowerOutages> getPowerOutages(Nerc nerc){
		return podao.getPowerOutages(nerc);
	}
	
public List<PowerOutages> funzioneMax(Nerc nerc, int years, int hours) {
	    
		tempPowerOutages = new ArrayList<>(this.getPowerOutages(nerc));
		powerOutages = new ArrayList<>();
		bestSoluzione = new ArrayList<>();
		
		maxCustomers = 0;
		sommaOre = 0;
		this.maxHours = hours;
		this.maxYears = years;
		
		int max = annoMax(tempPowerOutages);
		for(PowerOutages p : tempPowerOutages) {
			if(max-p.getDataInizio().getYear()<=this.maxYears) {
				powerOutages.add(p);
			}
		}
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
		
		cerca(parziale, livello + 1);
		
	}


	public int getSomma(List<PowerOutages> parziale) {
		
		int somma = 0;
		
		for(PowerOutages p : parziale) {
			somma+= p.getCustomers();
		}
		
		return somma;
	}
	
	public double getSommaOre(List<PowerOutages> parziale) {
		sommaOre = 0;
		for (PowerOutages p : parziale) {
			sommaOre+=p.getOre();
			}
		
		return sommaOre;
	}
	// creo un metodo di anno massimo
	public int annoMax(List<PowerOutages> powerOutages) {
		int bestAnno = 0;
		for(PowerOutages p : powerOutages) {
			if(p.getDataInizio().getYear()>bestAnno) {
				bestAnno = p.getDataInizio().getYear();
			}
		}
		return bestAnno;
	}

	/**
	 * Metodo per il controllo di inserimento cifre
	 * @param p Stringa in input
	 * @return valore vero o falso
	 */
	public boolean controllaTxt (String p) {
		char c;
		boolean result = true;
		
		for(int i=0;i<p.length();i++){
            c = p.charAt(i);
            if(!((Character.isDigit(c)))){
                result = false;
                return result;
            }
        }
        
		return result;
	}

}
