package com.androidbelieve.drawerwithswipetabs;

import java.util.Date;

/**
 * Created by Sirine on 06/12/2015.
 */
public class Parcours {

    private String HeureDepart;
    private Date DateDepart;
    private String VilleDepart;
    private String VilleDarrivé;
    private Boolean Fumer;
    private Boolean AutorisationBagage;
    private Boolean AutorisationAnimal;
    private int Etat;
    private int NombrePlaces;

    public Parcours(String heureDepart, Date dateDepart, String villeDepart, String villeDarrivé,
                    Boolean fumer, Boolean autorisationBagage,
                    Boolean autorisationAnimal, int etat, int nombrePlaces,
                    String description, String tarif) {
        HeureDepart = heureDepart;
        DateDepart = dateDepart;
        VilleDepart = villeDepart;
        VilleDarrivé = villeDarrivé;
        Fumer = fumer;
        AutorisationBagage = autorisationBagage;
        AutorisationAnimal = autorisationAnimal;
        Etat = etat;
        NombrePlaces = nombrePlaces;
        Description = description;
        Tarif = tarif;
    }

    public String getHeureDepart() {
        return HeureDepart;
    }

    public void setHeureDepart(String heureDepart) {
        HeureDepart = heureDepart;
    }

    public Date getDateDepart() {
        return DateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        DateDepart = dateDepart;
    }

    public String getVilleDepart() {
        return VilleDepart;
    }

    public void setVilleDepart(String villeDepart) {
        VilleDepart = villeDepart;
    }

    public String getVilleDarrivé() {
        return VilleDarrivé;
    }

    public void setVilleDarrivé(String villeDarrivé) {
        VilleDarrivé = villeDarrivé;
    }

    public Boolean getFumer() {
        return Fumer;
    }

    public void setFumer(Boolean fumer) {
        Fumer = fumer;
    }

    public Boolean getAutorisationBagage() {
        return AutorisationBagage;
    }

    public void setAutorisationBagage(Boolean autorisationBagage) {
        AutorisationBagage = autorisationBagage;
    }

    public Boolean getAutorisationAnimal() {
        return AutorisationAnimal;
    }

    public void setAutorisationAnimal(Boolean autorisationAnimal) {
        AutorisationAnimal = autorisationAnimal;
    }

    public int getEtat() {
        return Etat;
    }

    public void setEtat(int etat) {
        Etat = etat;
    }

    public int getNombrePlaces() {
        return NombrePlaces;
    }

    public void setNombrePlaces(int nombrePlaces) {
        NombrePlaces = nombrePlaces;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getTarif() {
        return Tarif;
    }

    public void setTarif(String tarif) {
        Tarif = tarif;
    }

    private String Description ;
    private String Tarif;
}
