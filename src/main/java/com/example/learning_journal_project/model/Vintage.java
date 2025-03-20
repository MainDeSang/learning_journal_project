package com.example.learning_journal_project.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity //→ Markiert diese Klasse als eine JPA-Entität
@Table(name = "vintages") //→ Markiert diese Klasse als eine Tabelle im Datenbank
@Getter //→ Erzeugt automatisch Getter- und Setter-Methoden
@Setter
@NoArgsConstructor //→ Generiert einen Standard-Konstruktor
@AllArgsConstructor // → Erstellt einen Konstruktor mit allen Feldern
@ToString //→ Generiert eine toString()-Methode
@Builder //→ Generiert einem Builder-Klassenmitglied für diese Klasse

public class Vintage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vintage_year; //Speichert den Jahrgang als Text (kann auch int sein).


    @OneToMany(mappedBy = "vintage", cascade = CascadeType.ALL, orphanRemoval = true)
    //Eine Klasse hat mehrere User, wobei mappedBy = "klasse" auf das Feld klasse in User verweist
    private List<User> users = new ArrayList<>();   // CascadeType & orphanRemoval: Bestimmt, ob abhängige User-Objekte automatisch gespeichert oder gelöscht werden.

    // Konstruktor für die Erstellung von vordefinierten Jahrgängen.
    public Vintage(String year) {
        this.vintage_year = year;
    }
}
