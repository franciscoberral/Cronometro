package francisco.berral.Cronometro.Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import francisco.berral.Cronometro.Connection.Connect;
import francisco.berral.Cronometro.Interfaces.IChronometerDAO;
import francisco.berral.Cronometro.Model.DataObject.Chronometer;

/**
 * Clase ChronometerDAO
 * @author Francisco José Berral Zafra
 *
 */
public class ChronometerDAO implements IChronometerDAO {
	Connection myConnection = null;

	/**
	 * Método para insertar cronómetro
	 * @param c Cronómetro que recibe
	 * @return Si el objeto a sido insertado o no
	 */
	public boolean insert(Chronometer c) {
		boolean result = false;
		myConnection = Connect.getConnection();
		String query = "INSERT INTO Chronometer (time, date) VALUES (?, ?)";

		try {
			PreparedStatement sentence = myConnection.prepareStatement(query);
			sentence.setString(1, c.getHour() + ":" + c.getMinute() + ":" + c.getSecond() + ":" + c.getMillisecond());
			sentence.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
			sentence.executeUpdate();
			result = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Método para obtener todos los elementos de la base de datos
	 * @return Lista con los datos de los tiempos
	 */
	public List<Chronometer> getAll() {
		List<Chronometer> list = new ArrayList<Chronometer>();
		myConnection = Connect.getConnection();
		String query = "SELECT id, time, date FROM Chronometer ORDER BY id desc";
		
		try {
			Statement st = myConnection.createStatement();
			ResultSet rs = st.executeQuery(query);
			Chronometer aux = null;
			while(rs.next()) {
				aux = new Chronometer();
				aux.setId(rs.getInt(1));
				aux.setTime(rs.getString(2));
				aux.setDate(rs.getTimestamp(3));
				list.add(aux);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
