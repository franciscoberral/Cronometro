package francisco.berral.Model.Cronometro.Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import francisco.berral.Cronometro.Connection.Connect;
import francisco.berral.Model.Cronometro.Model.DataObject.Chronometer;

public class ChronometerDAO {
	Connection myConnection = null;

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
