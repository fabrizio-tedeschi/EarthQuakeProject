package ftvp.earthquakeapp.controller;

import ftvp.earthquakeapp.persistence.rest.EarthquakeRequestMaker;
import ftvp.earthquakeapp.persistence.model.Earthquake;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.stream.StreamSupport;

public class OverviewController {

    @FXML
    private Button searchButton;
    @FXML
    private Button deleteButton;


    private final ObservableList<Earthquake> earthquakes = FXCollections.observableArrayList();
    private EarthquakeRequestMaker earthquakeRequestMaker = new EarthquakeRequestMaker();

    @FXML
    private TableView<Earthquake> tvEarthquakes;

    public void initialize(){
        initDataSource();
        initializeTableViewProperties();
    }

    public void initDataSource(){
        List<Earthquake> earthquakesFound = earthquakeRequestMaker.getDefault();
        earthquakes.addAll(StreamSupport.stream(earthquakesFound.spliterator(), false).toList());
    }

    @FXML
    public void initializeTableViewProperties(){

        TableColumn<Earthquake, String> titleCol = new TableColumn<>("Title");
        TableColumn<Earthquake, Integer> magCol = new TableColumn<>("Magnitude");
        TableColumn<Earthquake, String> placeCol = new TableColumn<>("Place");
        TableColumn<Earthquake, Date> timeCol = new TableColumn<>("Time");


        titleCol.setPrefWidth(280);
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));

        magCol.setPrefWidth(90);
        magCol.setCellValueFactory(new PropertyValueFactory<>("mag"));

        placeCol.setPrefWidth(200);
        placeCol.setCellValueFactory(new PropertyValueFactory<>("place"));

        timeCol.setPrefWidth(210);
        timeCol.setCellValueFactory(new PropertyValueFactory<>("datetime"));


        tvEarthquakes.setItems(earthquakes);
        tvEarthquakes.getColumns().setAll(titleCol, magCol, placeCol, timeCol);
    }

    @FXML
    void onSearchClicked() {
        deleteButton.setDisable(false);
    }

    @FXML
    void onHomeClicked(){
        initialize();
    }

    @FXML
    void onMapClicked() throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://earthquake.usgs.gov/earthquakes/map/?extent=-88.3591,-538.59375&extent=88.3591,316.40625"));
    }

}