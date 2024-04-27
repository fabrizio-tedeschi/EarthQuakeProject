module ftvp.earthquakeapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens ftvp.earthquakeapp to javafx.fxml;
    exports ftvp.earthquakeapp;
}