package francisco.berral.Cronometro.Connection;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

public class Connect {
	private String file = "connection.xml";
    private static Connection con;
    private static Connect _newInstance;

    private Connect() {
        DataConnection dc = loadFile();
        try {
            con = DriverManager.getConnection(dc.getServer()+"/"+dc.getDatabase(),dc.getUser(),dc.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
            con = null;
        }
    }

    public static Connection getConnection() {
        if (_newInstance == null){
            _newInstance = new Connect();
        }
        return con;
    }
    
    public DataConnection loadFile() {
        DataConnection dc = new DataConnection();
 
        JAXBContext context;
        try {
            context = JAXBContext.newInstance(DataConnection.class);
            Unmarshaller um = context.createUnmarshaller();
            DataConnection newR = (DataConnection) um.unmarshal(new File(file));
            dc = newR;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dc;
    }
}
