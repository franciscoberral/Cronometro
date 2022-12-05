package francisco.berral.Cronometro.Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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
}
