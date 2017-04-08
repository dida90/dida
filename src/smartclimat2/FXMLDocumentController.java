/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartclimat2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Math.abs;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

/**
 *
 * @author mammerly
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML 
    private Button parcourir, afficher_courbe,comparaison;
    
     @FXML
    private CheckBox temp_k, temp_c, humidite, nebulosite;
    
      @FXML
    private TableView table_donne;

    @FXML
    private ComboBox choix_ville, annee, mois, jours, annee_v1, mois_v1, jour_v1, ville1, annee_v2, mois_v2, jour_v2, ville2, c_comparaison;

    @FXML
    private ListView liste;
    
    @FXML
    private LineChart courbe;
    
    @FXML
    private NumberAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    
 
    public List<Releve> climat = new ArrayList<>();
    public List<Stations> station =new ArrayList<>();
     @FXML 
    private void afficher_courbe(ActionEvent event){
         XYChart.Series series = new XYChart.Series();
        xAxis.setLabel("Heure");
        yAxis.setLabel("Y ");
        courbe.setTitle("Evolution ");
        series.setName("series"); // a voir apres pour modification de couleur
        int heure = 0;
        for (int i = 0; i < 8; i++) {
            series.getData().add(new XYChart.Data(heure, i));
            heure = heure + 3;
        }
        courbe.setVisible(true);
        courbe.getData().add(series);
        
    }
    
     @FXML
    private Float ecart_type(List<Float> S) {

        int ecrt_val;

        Collections.sort(S);
        if ((S.size() % 2) != 0) {
            ecrt_val = (S.size() + 1) / 2;
            return S.get(ecrt_val);
        } else {
            return (S.get(S.size() / 2) + S.get((S.size() + 1) / 2)) / 2;
        }
    }
    
       @FXML
  private void importer_fichier(ActionEvent event) {
        FileChooser filechooser = new FileChooser();
        filechooser.setTitle("Ouvrir un fichier");
        File file = filechooser.showOpenDialog(null);
        if (file != null) {
            String[] extensions = {"txt", "csv"};
            for (String extension : extensions) {
                if (file.getName().toLowerCase().endsWith("." + extension)) {
                    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                        final String fileName = file.toURI().toString();
                        String fileNamelast = null;

                        for (String retval : fileName.split("/")) {
                            fileNamelast = retval;
                        }
                        liste.getItems().add(fileNamelast);

                        ObservableList<Releve> data = FXCollections.observableArrayList();
                        List<String> row = new ArrayList<>();
                        String line;
//                         donnes = new ArrayList<>();
                        boolean line_one = false;
                        while ((line = br.readLine()) != null) {
                            if (line_one) {
                                row.clear();
                                //   donnes.add(line);
                                int i = 0;
                                for (String retval : line.split(";")) {

                                    if ( i== 0||i == 7 || i == 9 || i == 14 || i == 1) {
                                        row.add(retval);
                                    }

                                    i++;
                                }
                                
                              //              i_climat.add(new info_climat())
                                //Conversion Kelvin to celsius
                                Float kelvin = Float.parseFloat(row.get(0));
                                Float celsius = kelvin - 273.15F;

                                data.add(new Releve(row.get(0), Float.toString(celsius), row.get(1), row.get(2)));
                            }
                            line_one = true;
                        }
                        TableColumn c = new TableColumn("Ordre");
                        c.setMinWidth(200);
                        c.setCellValueFactory(new PropertyValueFactory<Releve, String>("Ordre"));

                        TableColumn c1 = new TableColumn("Température ");
                        c1.setMinWidth(200);
                        c1.setCellValueFactory(new PropertyValueFactory<Releve, String>("temperature"));

                        TableColumn c2 = new TableColumn("Humidité %");
                        c2.setMinWidth(200);
                        c2.setCellValueFactory(new PropertyValueFactory<Releve, String>("humidite"));

                        TableColumn c3 = new TableColumn("Nebulosité %");
                        c3.setMinWidth(200);
                        c3.setCellValueFactory(new PropertyValueFactory<Releve, String>("nebulosite"));

                        table_donne.setItems(data);
                        table_donne.getColumns().addAll(c, c1, c2, c3);

                    } catch (IOException ex) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);

                    }
                }
            }
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        String[] villes = {"ABBEVILLE", "LILLE-LESQUIN", "PTE DE LA HAGUE", "CAEN-CARPIQUET", "ROUEN-BOOS", "REIMS-PRUNAY", "BREST-GUIPAVAS", "PLOUMANACH", "RENNES-ST JACQUES", "ALENCON", "ORLY", "TROYES-BARBEREY", "NANCY-OCHEY", "STRASBOURG-ENTZHEIM",
            "BELLE ILE-LE TALUT", "NANTES-BOUGUENAIS", "TOURS", "BOURGES", "DIJON-LONGVIC", "BALE-MULHOUSE", "PTE DE CHASSIRON",
            "POITIERS-BIARD", "LIMOGES-BELLEGARDE", "CLERMONT-FD", "LE PUY-LOUDES", "LYON-ST EXUPERY", "BORDEAUX-MERIGNAC",
            "GOURDON", "MILLAU", "MONTELIMAR", "EMBRUN", "MONT-DE-MARSAN", "TARBES-OSSUN", "ST GIRONS", "TOULOUSE-BLAGNAC",
            "MONTPELLIER", "MARIGNANE", "CAP CEPET", "NICE", "PERPIGNAN", "AJACCIO", "BASTIA", "GLORIEUSES", "JUAN DE NOVA",
            "EUROPA", "TROMELIN", "GILLOT-AEROPORT", "NOUVELLE AMSTERDAM", "CROZET", "KERGUELEN", "PAMANDZI", "ST-PIERRE",
            "LA DESIRADE METEO", "ST-BARTHELEMY METEO", "LE RAIZET AERO", "TRINITE-CARAVEL"};

        String[] idVilles = {"07005", "07015", "07020", "07027", "07037", "07072", "07110", "07117", "07130", "07139", "07149", "07168",
            "07181", "07190", "07207", "07222", "07240", "07255", "07280", "07299", "07314", "07335", "07434", "07460", "07471", "07481",
            "07510", "07535", "07558", "07577", "07591", "07607", "07621", "07627", "07630", "07643", "07650", "07661", "07690", "07747",
            "07761", "07790", "61968", "61976", "61980", "61996", "61998", "67005", "71805", "78897", "78925", "81401",
            "81405", "81408", "81415", "89642"};

        System.out.println(villes.length + " " + idVilles.length);
        for (int i = 0; i < idVilles.length; i++) {
            station.add(new Stations(idVilles[i], villes[i]));
        }
        
        
        choix_ville.getItems().addAll("ABBEVILLE", "LILLE-LESQUIN", "PTE DE LA HAGUE", "CAEN-CARPIQUET", "ROUEN-BOOS", "REIMS-PRUNAY", "BREST-GUIPAVAS", "PLOUMANACH", "RENNES-ST JACQUES", "ALENCON", "ORLY", "TROYES-BARBEREY", "NANCY-OCHEY", "STRASBOURG-ENTZHEIM",
                "BELLE ILE-LE TALUT", "NANTES-BOUGUENAIS", "TOURS", "BOURGES", "DIJON-LONGVIC", "BALE-MULHOUSE", "PTE DE CHASSIRON",
                "POITIERS-BIARD", "LIMOGES-BELLEGARDE", "CLERMONT-FD", "LE PUY-LOUDES", "LYON-ST EXUPERY", "BORDEAUX-MERIGNAC",
                "GOURDON", "MILLAU", "MONTELIMAR", "EMBRUN", "MONT-DE-MARSAN", "TARBES-OSSUN", "ST GIRONS", "TOULOUSE-BLAGNAC",
                "MONTPELLIER", "MARIGNANE", "CAP CEPET", "NICE", "PERPIGNAN", "AJACCIO", "BASTIA", "GLORIEUSES", "JUAN DE NOVA",
                "EUROPA", "TROMELIN", "GILLOT-AEROPORT", "NOUVELLE AMSTERDAM", "CROZET", "KERGUELEN", "PAMANDZI", "ST-PIERRE",
                "LA DESIRADE METEO", "ST-BARTHELEMY METEO", "LE RAIZET AERO", "TRINITE-CARAVEL");

        ville1.getItems().addAll("ABBEVILLE", "LILLE-LESQUIN", "PTE DE LA HAGUE", "CAEN-CARPIQUET", "ROUEN-BOOS", "REIMS-PRUNAY", "BREST-GUIPAVAS", "PLOUMANACH", "RENNES-ST JACQUES", "ALENCON", "ORLY", "TROYES-BARBEREY", "NANCY-OCHEY", "STRASBOURG-ENTZHEIM",
                "BELLE ILE-LE TALUT", "NANTES-BOUGUENAIS", "TOURS", "BOURGES", "DIJON-LONGVIC", "BALE-MULHOUSE", "PTE DE CHASSIRON",
                "POITIERS-BIARD", "LIMOGES-BELLEGARDE", "CLERMONT-FD", "LE PUY-LOUDES", "LYON-ST EXUPERY", "BORDEAUX-MERIGNAC",
                "GOURDON", "MILLAU", "MONTELIMAR", "EMBRUN", "MONT-DE-MARSAN", "TARBES-OSSUN", "ST GIRONS", "TOULOUSE-BLAGNAC",
                "MONTPELLIER", "MARIGNANE", "CAP CEPET", "NICE", "PERPIGNAN", "AJACCIO", "BASTIA", "GLORIEUSES", "JUAN DE NOVA",
                "EUROPA", "TROMELIN", "GILLOT-AEROPORT", "NOUVELLE AMSTERDAM", "CROZET", "KERGUELEN", "PAMANDZI", "ST-PIERRE",
                "LA DESIRADE METEO", "ST-BARTHELEMY METEO", "LE RAIZET AERO", "TRINITE-CARAVEL");
                
        ville2.getItems().addAll("ABBEVILLE", "LILLE-LESQUIN", "PTE DE LA HAGUE", "CAEN-CARPIQUET", "ROUEN-BOOS", "REIMS-PRUNAY", "BREST-GUIPAVAS", "PLOUMANACH", "RENNES-ST JACQUES", "ALENCON", "ORLY", "TROYES-BARBEREY", "NANCY-OCHEY", "STRASBOURG-ENTZHEIM",
                "BELLE ILE-LE TALUT", "NANTES-BOUGUENAIS", "TOURS", "BOURGES", "DIJON-LONGVIC", "BALE-MULHOUSE", "PTE DE CHASSIRON",
                "POITIERS-BIARD", "LIMOGES-BELLEGARDE", "CLERMONT-FD", "LE PUY-LOUDES", "LYON-ST EXUPERY", "BORDEAUX-MERIGNAC",
                "GOURDON", "MILLAU", "MONTELIMAR", "EMBRUN", "MONT-DE-MARSAN", "TARBES-OSSUN", "ST GIRONS", "TOULOUSE-BLAGNAC",
                "MONTPELLIER", "MARIGNANE", "CAP CEPET", "NICE", "PERPIGNAN", "AJACCIO", "BASTIA", "GLORIEUSES", "JUAN DE NOVA",
                "EUROPA", "TROMELIN", "GILLOT-AEROPORT", "NOUVELLE AMSTERDAM", "CROZET", "KERGUELEN", "PAMANDZI", "ST-PIERRE",
                "LA DESIRADE METEO", "ST-BARTHELEMY METEO", "LE RAIZET AERO", "TRINITE-CARAVEL");

        c_comparaison.getItems().addAll("temperature_C","temperature_k","humiditè","nebulosite");
        
        // a voir apres pour finalisation
        for (int i = 1; i < 32; i++) {
            if (i < 10) {
            jours.getItems().addAll('0' + String.valueOf(i));
            jour_v1.getItems().addAll('0' + String.valueOf(i));
            jour_v2.getItems().addAll('0' + String.valueOf(i));
            } else {
            jours.getItems().addAll(String.valueOf(i));
            jour_v1.getItems().addAll(String.valueOf(i));
            jour_v2.getItems().addAll(String.valueOf(i));}

        }

        for (int i = 1; i < 13; i++) {
            if (i < 10) {
                mois.getItems().addAll('0' + String.valueOf(i));
                mois_v1.getItems().addAll('0' + String.valueOf(i));
                mois_v2.getItems().addAll('0' + String.valueOf(i));
            } else {
                mois.getItems().addAll(String.valueOf(i));
                mois_v1.getItems().addAll(String.valueOf(i));
                mois_v2.getItems().addAll(String.valueOf(i));
            }
            
        }

        for (int i = 1996; i < 2018; i++) {
            annee.getItems().addAll(String.valueOf(i));
            annee_v1.getItems().addAll(String.valueOf(i));
            annee_v2.getItems().addAll(String.valueOf(i));
        }
    }

    public float difference(ArrayList<Float> s, ArrayList<Float> n) {

        return (abs(ecart_type(s) - ecart_type(n)));
    }

    public ArrayList<String> split_date(String date) {
        ArrayList<String> s = new ArrayList<>();

        String annee, mois, jour, heure;

        annee = date.substring(0, 4);
        mois = date.substring(4, 6);
        jour = date.substring(6, 8);
        heure = date.substring(8, 10);

        System.out.println("annee " + annee);
        System.out.println("\n mois " + mois);
        System.out.println("\n jour " + jour);
        System.out.println("\n heure " + heure);
        s.add(date);
        s.add(mois);
        s.add(jour);
        s.add(heure);

        return s;

    }

}

