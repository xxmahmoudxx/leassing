package com.leasing.leasing.leasingModel;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Document(collection ="voiture")
@Data
public class voiture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String id;
    @Indexed(direction = IndexDirection.ASCENDING)

    String marque;
    @Indexed(direction = IndexDirection.ASCENDING)
    String model;
    @Indexed(direction = IndexDirection.ASCENDING)

    int annee;
    @Indexed(direction = IndexDirection.ASCENDING)
    double Prix;
    @Indexed(direction = IndexDirection.ASCENDING)
    int kelometrage;

    String coleur;
    @Indexed(direction = IndexDirection.ASCENDING)
    boolean estelectrique;

    private int nombreporte;
    @Indexed(direction = IndexDirection.ASCENDING)
    boolean estautomatique ;
    String [] image=new String[10] ;


}
