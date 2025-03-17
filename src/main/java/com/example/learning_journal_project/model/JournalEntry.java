package com.example.learning_journal_project.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class JournalEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne //Definiert die Beziehung zu User (Viele JournalEntry gehören zu einem User)
    @JoinColumn(name = "user_id", nullable = false) // Erstellt eine user_id-Spalte als Fremdschlüssel
    private User user;

}
