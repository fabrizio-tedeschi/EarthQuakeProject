package ftvp.earthquakeapp.controller;

import ftvp.earthquakeapp.persistence.rest.EarthquakeRequestMaker;
import ftvp.earthquakeapp.persistence.model.Earthquake;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javafx.scene.control.TextField;

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
        applySearchFilter();
    }

    private void applySearchFilter() {
        String searchText = searchField.getText().toLowerCase();
        if (isNumeric(searchText)) {
            return;
        }

        Double minMagValue = parseDouble(minMag.getText());
        Double maxMagValue = parseDouble(maxMag.getText());

        ObservableList<Earthquake> filteredList = earthquakes.stream().filter(eq -> {
                    boolean matchesText = eq.getTitle().toLowerCase().contains(searchText) || eq.getPlace().toLowerCase().contains(searchText);
                    boolean matchesMinMag = minMagValue == null || eq.getMag() >= minMagValue;
                    boolean matchesMaxMag = maxMagValue == null || eq.getMag() <= maxMagValue;
                    return matchesText && matchesMinMag && matchesMaxMag;
                }).collect(Collectors.toCollection(FXCollections::observableArrayList));

        tvEarthquakes.getItems().clear();
        tvEarthquakes.setItems(filteredList);
    }

    private boolean isNumeric(String str) {
        return str.matches("\\d+");
    }

    private Double parseDouble(String str) {
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return null;
        }
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
        earthquakes.clear();
        List<Earthquake> earthquakesFound = earthquakeRequestMaker.getDefault();
        earthquakes.addAll(earthquakesFound);
        applySearchFilter();
    }

    @FXML
    void onDeleteClicked() {
        searchField.clear();
        minMag.clear();
        maxMag.clear();
        applySearchFilter();
        initialize();

    }

}