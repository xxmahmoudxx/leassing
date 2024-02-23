package com.leasing.leasing.leasingModel;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@EqualsAndHashCode(callSuper = true)
@Document(collection ="clientMoral")
@Data
@SuperBuilder

@AllArgsConstructor
@NoArgsConstructor


public class clientMoral extends client{


    String nom_entreprise;
    String raison_sociale;

     String identifiant_unique;
     String type;



}



