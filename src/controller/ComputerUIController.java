package controller;

import hardware.Computer;
import hardware.Hardware;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sample.viewManager;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ComputerUIController extends HardwareController implements Initializable {

    //<editor-fold desc="FXML Atributes">
    //@FXML
   // public ListView<Computer> lv_ausgabe;

    @FXML
    private TextField cpu;
    @FXML
    private TextField ram;
    @FXML
    private TextField betriebssystem;
    @FXML
    private ChoiceBox<Computer.Typ> typ;
    @FXML
    private TextField grafikkarte;
    @FXML
    private TextField ssd;
    @FXML
    private TextField hdd;


    //</editor-fold>

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        status.getItems().setAll(Computer.Status.values()); // weißt enum zur choiceBox zu
        typ.getItems().setAll(Computer.Typ.values());

        loadHardware();

    }

    public void loadHardware() {
        lv_ausgabe.getItems().add(new Computer("R001", "Acer Aspire XC-885", "Acer", "Ok", 24, LocalDate.parse("2019-11-12"), "Intel Core i5-8400", 8, "Windows 10 Home 64 bit", "Multimedia-PC", "Nvidia", 512, 5));
        lv_ausgabe.getItems().add(new Computer("R002", "Geforce GTX 1050", "GeForce", "Defekt", 36, LocalDate.parse("2019-11-12"), "AMD FX-6300", 8, "Windows 10 64 bit", "Gaming-PC", "Nvidia Geforce", 512, 5));
        lv_ausgabe.getItems().add(new Computer("R003", "Apple Mac Mini", "Apple", "In Reperatur", 24, LocalDate.parse("2019-11-12"), "Intel Core i3-8100b", 8, "macOS 10.14 Mojave 64 bit", "Multimedia-PC", "AMD", 512, 5));
    }

    public void lv_clicked() {
        //TODO muss in drucker rein
        Computer pc_safed = lv_ausgabe.getSelectionModel().getSelectedItem(); //wird geholt und in pc_safed gespeichert

        super.lv_clickedHardware(pc_safed); //Methode aus HardwareController wird aufgerufen und pc_safed übergeben

       cpu.setText(pc_safed.getCpu());
        ram.setText(""+pc_safed.getArbeitspeicher());
       betriebssystem.setText(pc_safed.getBetriebssystem());
       typ.setValue(Computer.getTyp(pc_safed.getTyp()));
        grafikkarte.setText(pc_safed.getGrafikkarte());
        hdd.setText(""+pc_safed.getFestplatte_hdd());
        ssd.setText(""+pc_safed.getFestplatte_ssd());

    }

    public void clickCancel(ActionEvent actionEvent) {
        clearValues();
    }

    public void clickSafe(ActionEvent actionEvent) {
        try {
            //lv_ausgabe.getItems().add(new Computer(seriennummer.getText(), modell.getText(), hersteller.getText(), status.getSelectionModel().getSelectedItem().toString(), Integer.parseInt(herstellergarantie.getText()), lieferdatum.getValue(), cpu.getText(), Integer.parseInt(ram.getText()), betriebssystem.getText(), typ.getSelectionModel().getSelectedItem().toString(), grafikkarte.getText(), Integer.parseInt(hdd.getText()), Integer.parseInt(ssd.getText())));
        } catch (Exception e) {
            Alert Test = new Alert(Alert.AlertType.ERROR, "Sie haben Möglicherweise ein Feld leer gelassen\nBitte nur Zahlen bei Ram/SSD/HDD eingeben");
            Test.showAndWait();
        }

//TODO NEUANLAGE ODER VORHANDEN
        if(id.getText().isEmpty()){
            System.out.printf(id.getText());
            System.out.println("Neuanlagae");
            lv_ausgabe.getItems().add(new Computer(seriennummer.getText(), modell.getText(), hersteller.getText(), status.getSelectionModel().getSelectedItem().toString(), Integer.parseInt(herstellergarantie.getText()), lieferdatum.getValue(), cpu.getText(), Integer.parseInt(ram.getText()), betriebssystem.getText(), typ.getSelectionModel().getSelectedItem().toString(), grafikkarte.getText(), Integer.parseInt(hdd.getText()), Integer.parseInt(ssd.getText())));

        } else{
            Computer pc_safed = lv_ausgabe.getSelectionModel().getSelectedItem();
            System.out.println("vorhanden");
            System.out.println(id.getText());

            super.lv_clickedHardware(pc_safed);
            pc_safed.setSeriennummer(seriennummer.getText());
            pc_safed.setModell(cpu.getText());
            pc_safed.setHersteller(hersteller.getText());

            pc_safed.setStatus(status.getValue().toString());
            pc_safed.setHerstellergarantie(Integer.parseInt(herstellergarantie.getText()));
            pc_safed.setLieferdatum(lieferdatum.getValue());
            //pc_safed.


            pc_safed.setCpu(cpu.getText());
            pc_safed.setArbeitspeicher(Integer.parseInt(ram.getText()));
            pc_safed.setBetriebssystem(betriebssystem.getText());
            pc_safed.setTyp(typ.getValue().toString());
            pc_safed.setGrafikkarte(grafikkarte.getText());
            pc_safed.setFestplatte_hdd(Integer.parseInt(hdd.getText()));
            pc_safed.setFestplatte_ssd(Integer.parseInt(ssd.getText()));
            lv_ausgabe.refresh();



        }
        clearValues();
    }

    @FXML
    public void clickDashboard(ActionEvent actionEvent) {
        viewManager.getInstance()
                .activateScene(viewManager.getInstance()
                        .getDashboardscene());

    }

    public void clearValues() {
        id.clear();
        seriennummer.clear();
        modell.clear();
        hersteller.clear();
        status.setValue(null);
        herstellergarantie.clear();
        lieferdatum.setValue(null);
        cpu.clear();
        ram.clear();
        betriebssystem.clear();
        typ.setValue(null);
        grafikkarte.clear();
        hdd.clear();
        ssd.clear();
    }
}
