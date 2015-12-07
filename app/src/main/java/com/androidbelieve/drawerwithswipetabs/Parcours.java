package com.androidbelieve.drawerwithswipetabs;

import java.util.Date;

/**
 * Created by Sirine on 06/12/2015.
 */
public class Parcours {

    private String VilleDepart;
    private String VilleArrive;
    private Date DateDepart;
    private String Heure;
    private String tel;
    private int nbrePlace;
    private float tarif;
    private Boolean Fumer;
    private Boolean AutorisationAnimal;
    private Boolean AutorisationBagage;
    private int Etat;

    public String getVilleDepart() {
        return VilleDepart;
    }

    public void setVilleDepart(String villeDepart) {
        VilleDepart = villeDepart;
    }

    public String getVilleArrive() {
        return VilleArrive;
    }

    public void setVilleArrive(String villeArrive) {
        VilleArrive = villeArrive;
    }

    public Date getDateDepart() {
        return DateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        DateDepart = dateDepart;
    }

    public String getHeure() {
        return Heure;
    }

    public void setHeure(String heure) {
        Heure = heure;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getNbrePlace() {
        return nbrePlace;
    }

    public void setNbrePlace(int nbrePlace) {
        this.nbrePlace = nbrePlace;
    }

    public float getTarif() {
        return tarif;
    }

    public void setTarif(float tarif) {
        this.tarif = tarif;
    }

    public Boolean getFumer() {
        return Fumer;
    }

    public void setFumer(Boolean fumer) {
        Fumer = fumer;
    }

    public Boolean getAutorisationAnimal() {
        return AutorisationAnimal;
    }

    public void setAutorisationAnimal(Boolean autorisationAnimal) {
        AutorisationAnimal = autorisationAnimal;
    }

    public Boolean getAutorisationBagage() {
        return AutorisationBagage;
    }

    public void setAutorisationBagage(Boolean autorisationBagage) {
        AutorisationBagage = autorisationBagage;
    }

    public int getEtat() {
        return Etat;
    }

    public void setEtat(int etat) {
        Etat = etat;
    }

    public Parcours(String villeDepart, String villeArrive, Date dateDepart, String heure, String tel, int nbrePlace, float tarif, Boolean fumer, Boolean autorisationAnimal, Boolean autorisationBagage, int etat) {
        VilleDepart = villeDepart;
        VilleArrive = villeArrive;
        DateDepart = dateDepart;
        Heure = heure;
        this.tel = tel;
        this.nbrePlace = nbrePlace;
        this.tarif = tarif;
        Fumer = fumer;
        AutorisationAnimal = autorisationAnimal;
        AutorisationBagage = autorisationBagage;
        Etat = etat;
    }
}
