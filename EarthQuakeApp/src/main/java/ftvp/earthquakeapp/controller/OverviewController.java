package ftvp.earthquakeapp.controller;

import ftvp.earthquakeapp.persistence.rest.EarthquakeRequestMaker;
import ftvp.earthquakeapp.persistence.model.Earthquake;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class OverviewController {

    @FXML
    private Button searchButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField searchField;

    @FXML
    private TextField minMag;

    @FXML
    private TextField maxMag;

    @FXML
    private DatePicker datePicker;

    private List<Earthquake> earthquakesFound = new ArrayList<>();
    private ObservableList<Earthquake> earthquakes = FXCollections.observableArrayList();
    private EarthquakeRequestMaker earthquakeRequestMaker = new EarthquakeRequestMaker();

    @FXML
    private TableView<Earthquake> tvEarthquakes;

    TableColumn<Earthquake, String> titleCol = new TableColumn<>("Title");
    TableColumn<Earthquake, Integer> magCol = new TableColumn<>("Magnitude");
    TableColumn<Earthquake, String> placeCol = new TableColumn<>("Place");
    TableColumn<Earthquake, Date> timeCol = new TableColumn<>("Time");


    public void initialize(){
        earthquakeRequestMaker.setPlace(null);
        earthquakeRequestMaker.setMinmag(0.0);
        earthquakeRequestMaker.setMaxmag(0.0);
        earthquakeRequestMaker.setDate(null);
        initDataSource();
        initializeTableViewProperties();
    }

    public void initDataSource(){
        earthquakesFound = earthquakeRequestMaker.getByParams();
        earthquakes.addAll(StreamSupport.stream(earthquakesFound.spliterator(), false).toList());
    }

    public void setTableView(){
        tvEarthquakes.setItems(earthquakes);
        tvEarthquakes.getColumns().setAll(titleCol, magCol, placeCol, timeCol);
    }

    @FXML
    public void initializeTableViewProperties(){

        titleCol.setPrefWidth(280);
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));

        magCol.setPrefWidth(90);
        magCol.setCellValueFactory(new PropertyValueFactory<>("mag"));

        placeCol.setPrefWidth(200);
        placeCol.setCellValueFactory(new PropertyValueFactory<>("place"));

        timeCol.setPrefWidth(210);
        timeCol.setCellValueFactory(new PropertyValueFactory<>("datetime"));

        setTableView();
    }

    public void refresh(){

        earthquakesFound.clear();
        earthquakes.clear();
        tvEarthquakes.getItems().clear();
        earthquakesFound = earthquakeRequestMaker.getByParams();
        earthquakes.addAll(StreamSupport.stream(earthquakesFound.spliterator(), false).toList());

        setTableView();
    }

    @FXML
    void onSearchClicked() {
        deleteButton.setDisable(false);

        if(datePicker.getValue() == null){
            earthquakeRequestMaker.setDate(null);
        }else{
            earthquakeRequestMaker.setDate(datePicker.getValue().toString());
        }

        if(searchField.getText().isEmpty()){
            earthquakeRequestMaker.setPlace(null);
        }
        else{
            earthquakeRequestMaker.setPlace(searchField.getText());
        }

        if(minMag.getText().isEmpty()){
            earthquakeRequestMaker.setMinmag(0.0);
        }else{
            earthquakeRequestMaker.setMinmag(Double.parseDouble(minMag.getText()));
        }

        if(maxMag.getText().isEmpty()){
            earthquakeRequestMaker.setMaxmag(0.0);
        }
        else{
            earthquakeRequestMaker.setMaxmag(Double.parseDouble(maxMag.getText()));
        }

        refresh();
    }

    @FXML
    void onHomeClicked(){
        initialize();
    }

    @FXML
    void onMapClicked() throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://earthquake.usgs.gov/earthquakes/map/?extent=-88.3591,-538.59375&extent=88.3591,316.40625"));
    }

    @FXML
    void onRefreshClicked() {
        refresh();
    }

    @FXML
    void onDeleteClicked() {
        searchField.clear();
        minMag.clear();
        maxMag.clear();
        datePicker.setValue(null);
    }

}