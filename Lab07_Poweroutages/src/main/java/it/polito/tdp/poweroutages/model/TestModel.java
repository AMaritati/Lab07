package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		
		Nerc nerc = new Nerc (3,"MAAC");
		System.out.println(model.getNercList());
	
		
		/*int maxYears = 2;
		List<PowerOutages> po = model.getPowerOutages(nerc);
		//System.out.println(po);
		List<PowerOutages> po1 = new ArrayList<>();
		int max = model.annoMax(po);
		for(PowerOutages p : po) {
			if(max-p.getDataInizio().getYear()<=maxYears) {
				po1.add(p);
			}
		}
		
		System.out.println(po1);
		
		//System.out.println(model.scremaList(model.getPowerOutages(nerc)));*/
		
		
		
		//System.out.println(model.getPowerOutages(nerc));
		List<PowerOutages> slz = new ArrayList<>(model.funzioneMax(nerc, 4,200));
		
	
		for(PowerOutages p : slz) {
			System.out.println(p);
		}
			
	System.out.println(slz.size());
		
		
	}

}
