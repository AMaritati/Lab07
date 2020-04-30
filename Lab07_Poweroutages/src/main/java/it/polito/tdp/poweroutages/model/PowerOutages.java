package it.polito.tdp.poweroutages.model;

import java.time.LocalDateTime;

public class PowerOutages {
	private int id;
	private Nerc nerc;
	private int customers;
	private LocalDateTime dataInizio;
	private LocalDateTime dataFine;
	private double ore;
	

	public PowerOutages(int id, Nerc nerc, int customers, LocalDateTime dataInizio, LocalDateTime dataFine,
			double ore) {
		
		this.id = id;
		this.nerc = nerc;
		this.customers = customers;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.ore = ore;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Nerc getNerc() {
		return nerc;
	}

	public void setNerc(Nerc nerc) {
		this.nerc = nerc;
	}

	public int getCustomers() {
		return customers;
	}

	public void setCustomers(int customers) {
		this.customers = customers;
	}

	public LocalDateTime getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(LocalDateTime dataInizio) {
		this.dataInizio = dataInizio;
	}

	public LocalDateTime getDataFine() {
		return dataFine;
	}

	public void setDataFine(LocalDateTime dataFine) {
		this.dataFine = dataFine;
	}

	public double getOre() {
		return ore;
	}

	public void setOre(double ore) {
		this.ore = ore;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PowerOutages other = (PowerOutages) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("%-20s %-20s %-10s %-20s", dataInizio, dataFine, ore, customers);
	}

	
	

}
