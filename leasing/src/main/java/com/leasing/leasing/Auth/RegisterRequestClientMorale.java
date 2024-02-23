package com.leasing.leasing.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestClientMorale {


    String date_entree ;
    String Rue;
    int code_postal;
    String Localite;
    String gouvernorat;
    String secteur_dactivite;

    boolean estClientLeasing;
    int mobile;

    int telephone;
    String nom_entreprise;
    String raison_sociale;

    String identifiant_unique;
    String type;
    String mail;
    String password;
}
