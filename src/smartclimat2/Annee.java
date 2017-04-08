/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartclimat2;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author mammerly
 */

public class Annee {
    int id;
    
    public List<Releve> climat = new ArrayList<>();
    Calendar calendar =new GregorianCalendar();
//calendar.setTime(new Date());
    int annee =calendar.get(Calendar.YEAR);
    
    public void getReleves(int idMois,int idJour){
     //elle doit retourner list<releve>
    }
    
    public void getMoyenneParMois(){
    //elle doit retourne list<releve>
    }
    
    
}
