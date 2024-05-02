package ftvp.earthquakeapp.controller;

import ftvp.earthquakeapp.persistence.rest.EarthquakeRequestMaker;
import ftvp.earthquakeapp.persistence.model.Earthquake;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Date;
import java.util.List;
import java.util.stream.StreamSupport;

public class OverviewController {

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

        TableColumn<Earthquake, String> idCol = new TableColumn<>("ID");
        TableColumn<Earthquake, String> titleCol = new TableColumn<>("Title");
        TableColumn<Earthquake, Integer> magCol = new TableColumn<>("Magnitude");
        TableColumn<Earthquake, String> placeCol = new TableColumn<>("Place");
        TableColumn<Earthquake, Date> timeCol = new TableColumn<>("Time");
        TableColumn<Earthquake, Integer> tsunamiCol = new TableColumn<>("Tsunami");

        idCol.setPrefWidth(70);
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        titleCol.setPrefWidth(250);
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));

        magCol.setPrefWidth(70);
        magCol.setCellValueFactory(new PropertyValueFactory<>("mag"));

        placeCol.setPrefWidth(200);
        placeCol.setCellValueFactory(new PropertyValueFactory<>("place"));

        timeCol.setPrefWidth(200);
        timeCol.setCellValueFactory(new PropertyValueFactory<>("datetime"));

        tsunamiCol.setPrefWidth(70);
        tsunamiCol.setCellValueFactory(new PropertyValueFactory<>("tsunami"));

        tvEarthquakes.setItems(earthquakes);
        tvEarthquakes.getColumns().setAll(idCol, titleCol, magCol, placeCol, timeCol, tsunamiCol);
    }
}