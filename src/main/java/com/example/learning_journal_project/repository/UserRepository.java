package com.example.learning_journal_project.repository;

import com.example.learning_journal_project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// UserRepository ist ein Interface, die von JpaRepository erbt.
// Automatische Bereitstellung von Methoden (save(), findById(), delete() usw.)
public interface UserRepository extends JpaRepository<User, Long>  {
    // Hier kann man eigene Methoden für wie z.b. die Suche nach Nutzern, hinzufügen.
    User findByEmail(String email);


}
