package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.PowerOutages;

public class PowerOutageDAO {
	
	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}
	/**
	 * Ottengo una lista di PO contenente id,id_nerc,customers,dataI,dataF,oreTotali;
	 * oreTotali può essere max di un valore passato come parametro
	 * @param nerc entità regionale
	 * @param hours se aggiungo un orario piccolo, mi prende solo poche pO
	 * @return lista di PowerOutages
	 * @author Alessandro
	 */
	public List<PowerOutages> getPowerOutages(Nerc nerc) {

		String sql = "SELECT id, nerc_id,customers_affected,date_event_finished,date_event_began,"
				+ "(HOUR(TIMEDIFF(date_event_finished,date_event_began))+ MINUTE(TIMEDIFF(date_event_finished,date_event_began))/60)AS TOTH "
				+ "FROM poweroutages WHERE nerc_id = ?";
		List<PowerOutages> poList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, nerc.getId());
		
			ResultSet res = st.executeQuery();
			while (res.next()) {
				Nerc n = new Nerc(res.getInt("nerc_id"));
				
				PowerOutages p = new PowerOutages(res.getInt("id"), n ,res.getInt("customers_affected"),res.getTimestamp("date_event_began").toLocalDateTime(),res.getTimestamp("date_event_finished").toLocalDateTime(),res.getDouble("TOTH"));
				poList.add(p);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return poList;
	}
	

}
