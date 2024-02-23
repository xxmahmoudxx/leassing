package com.leasing.leasing.leasingModel;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@EqualsAndHashCode(callSuper = true)
@Document(collection ="client")
@Data
@SuperBuilder



@AllArgsConstructor
@NoArgsConstructor
public class client extends user{

String date_entree;
String Rue;
int code_postal;
String Localite;
String gouvernorat;
String secteur_dactivite;

boolean estClientLeasing;
int mobile;

int telephone;




  }

