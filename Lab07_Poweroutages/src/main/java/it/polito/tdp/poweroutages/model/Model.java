package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	
	public Model() {
		podao = new PowerOutageDAO();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	
	public List<PowerOutages> getPowerOutages(Nerc nerc){
		return podao.getPowerOutages(nerc);
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
