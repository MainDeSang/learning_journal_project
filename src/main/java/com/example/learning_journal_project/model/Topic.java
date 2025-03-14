package com.example.learning_journal_project.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity //→ Markiert diese Klasse als eine JPA-Entität
@Table(name = "topics") //→ Markiert diese Klasse als eine Tabelle im Datenbank
@Getter //→ Erzeugt automatisch Getter- und Setter-Methoden
@Setter
@NoArgsConstructor //→ Generiert einen Standard-Konstruktor
@AllArgsConstructor // → Erstellt einen Konstruktor mit allen Feldern
@ToString //→ Generiert eine toString()-Methode
@Builder //→ Generiert einem Builder-Klassenmitglied für diese Klasse

public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String duration;


    //Definiert die Beziehung als bidirektional.
    @ManyToMany(mappedBy = "topics") //Das Feld "topics" in der User-Klasse wird als "users" in der Topic-Klasse gemappt. & mappedBy=Bedeutet, dass die User-Entität die Beziehung steuert (führt).
    private Set<User> users;//Enthält die User, die dieses Topic gewählt haben.

}
