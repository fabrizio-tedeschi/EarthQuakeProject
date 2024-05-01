package ftvp.earthquakeapp.controller;

import ftvp.earthquakeapp.persistence.dao.EarthquakeRepository;
import ftvp.earthquakeapp.persistence.model.Earthquake;
import ftvp.earthquakeapp.persistence.model.Infos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.StreamSupport;

public class OverviewController {

    private final ObservableList<Earthquake> earthquakes = FXCollections.observableArrayList();
    private final ObservableList<Infos> infosList = FXCollections.observableArrayList();
    private EarthquakeRepository earthquakeRepository = new EarthquakeRepository();

    @FXML
    private TableView<Infos> tvProperties;

    public void initialize(){
        initDataSource();
        initializeTableViewProperties();
    }

    public void initDataSource(){
        this.earthquakeRepository = new EarthquakeRepository();
        List<Earthquake> earthquakesFound = earthquakeRepository.getDefault();
        earthquakes.addAll(StreamSupport.stream(earthquakesFound.spliterator(), false).toList());

        List<Infos> tmpList = new ArrayList<>();
        for(Earthquake eq : earthquakesFound){
            tmpList.add(eq.getProperties());
        }

        infosList.addAll(StreamSupport.stream(tmpList.spliterator(), false).toList());
    }

    @FXML
    public void initializeTableViewProperties(){

        TableColumn<Infos, String> titleCol = new TableColumn<Infos, String>("Title");
        TableColumn<Infos, Integer> magCol = new TableColumn<>("Magnitude");
        TableColumn<Infos, String> placeCol = new TableColumn<>("Place");
        TableColumn<Infos, Date> timeCol = new TableColumn<>("Time");
        TableColumn<Infos, Integer> tsunamiCol = new TableColumn<>("Tsunami");

        titleCol.setPrefWidth(250);
        titleCol.setCellValueFactory(new PropertyValueFactory<Infos, String>("title"));

        magCol.setPrefWidth(70);
        magCol.setCellValueFactory(new PropertyValueFactory<>("mag"));

        placeCol.setPrefWidth(200);
        placeCol.setCellValueFactory(new PropertyValueFactory<>("place"));

        timeCol.setPrefWidth(200);
        timeCol.setCellValueFactory(new PropertyValueFactory<>("datetime"));

        tsunamiCol.setPrefWidth(70);
        tsunamiCol.setCellValueFactory(new PropertyValueFactory<>("tsunami"));

        tvProperties.setItems(infosList);
        tvProperties.getColumns().setAll(titleCol, magCol, placeCol, timeCol, tsunamiCol);
    }
}