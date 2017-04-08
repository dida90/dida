/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartclimat2;

import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author mammerly
 */
public class Station {
   int id;
   String nom;
   
   Station(int s_id,  String s_nom ){
   id = s_id;
   nom = s_nom;
   }
   
  public void initialize(URL url, ResourceBundle rb) {
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

   
      
   
}

}
