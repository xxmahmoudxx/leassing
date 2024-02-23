package com.leasing.leasing.leasingModel;


import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@EqualsAndHashCode(callSuper = true)
@Document(collection ="clientPhysique")
@Data
@NoArgsConstructor
@AllArgsConstructor

@SuperBuilder


public class clientPhysique extends  client {


    String nom;
    String Prenom;

    String civilite;
    String fonction;
    String nature_identite;
    String nomComercial;
    String numidentite;


}