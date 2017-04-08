/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartclimat2;

/**
 *
 * @author mammerly
 */
public class Releve {
    String ordre;
    String temperature;
    String humidite;
    String nebulosite;

    public String getOrdre() {
        return ordre;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getHumidite() {
        return humidite;
    }

    public String getNebulosite() {
        return nebulosite;
    }

    public void setOrdre(String ordre) {
        this.ordre = ordre;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setHumidite(String humidite) {
        this.humidite = humidite;
    }

    public void setNebulosite(String nebulosite) {
        this.nebulosite = nebulosite;
    }

    public Releve(String ordre, String temperature, String humidite, String nebulosite) {
        this.ordre = ordre;
        this.temperature = temperature;
        this.humidite = humidite;
        this.nebulosite = nebulosite;
    }
    
    
    
}
