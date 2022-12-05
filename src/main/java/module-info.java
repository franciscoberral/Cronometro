module francisco.berral.Cronometro {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;
	requires java.xml.bind;
	
    opens francisco.berral.Cronometro to javafx.fxml;
    opens francisco.berral.Cronometro.Connection to java.xml.bind;
    exports francisco.berral.Cronometro;
}
