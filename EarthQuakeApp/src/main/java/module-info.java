module ftvp.earthquakeapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens ftvp.earthquakeapp to javafx.fxml;
    exports ftvp.earthquakeapp;
    exports ftvp.earthquakeapp.controller;
    opens ftvp.earthquakeapp.controller to javafx.fxml;
}