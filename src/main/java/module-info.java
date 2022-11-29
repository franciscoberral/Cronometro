module francisco.berral.Cronometro {
    requires javafx.controls;
    requires javafx.fxml;

    opens francisco.berral.Cronometro to javafx.fxml;
    exports francisco.berral.Cronometro;
}
