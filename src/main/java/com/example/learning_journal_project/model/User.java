package com.example.learning_journal_project.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity //→ Markiert diese Klasse als eine JPA-Entität
@Table(name = "users") //→ Markiert diese Klasse als eine Tabelle im Datenbank
@Getter //→ Erzeugt automatisch Getter- und Setter-Methoden
@Setter
@NoArgsConstructor //→ Generiert einen Standard-Konstruktor
@AllArgsConstructor // → Erstellt einen Konstruktor mit allen Feldern
@ToString //→ Generiert eine toString()-Methode
@Builder //→ Generiert einem Builder-Klassenmitglied für diese Klasse

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String fullName;


    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    //Definiert eine Many-to-Many-Beziehung zwischen User und Topic.
    @ManyToMany
    @JoinTable(
            name = "user_topic", // Bestimmt den Namen der Zwischentabelle
            joinColumns = @JoinColumn(name = "user_id"), // Verknüpfung zur User-Tabelle & erstellt eine FK-Spalte (user_id) in der Zwischentabelle.
            inverseJoinColumns = @JoinColumn(name = "topic_id") // Verknüpfung zur Topic-Tabelle & erstellt eine FK-Spalte (topic_id) in der Zwischentabelle.
    )
    private Set<Topic> topics = new HashSet<>(); // Verwenden einer Set-Menge zur Vermeidung doppelter Einträge

}
