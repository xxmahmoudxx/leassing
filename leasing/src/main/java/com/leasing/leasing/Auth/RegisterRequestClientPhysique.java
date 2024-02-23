package com.leasing.leasing.Auth;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestClientPhysique {

    String nom;
    String Prenom;

    String civilite;
    String fonction;
    String nature_identite;
    String nomComercial;
    String numidentite;
    String date_entree ;
    String Rue;
    int code_postal;
    String Localite;
    String gouvernorat;
    String secteur_dactivité;

    @Getter
    boolean estClientLeasing;
    int mobile;

    int telephone;
    String mail;
    String password;


}
