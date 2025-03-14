package com.example.learning_journal_project.repository;

import com.example.learning_journal_project.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    //JpaRepository<User, Long> ermöglicht uns, User in der Datenbank zu speichern, zu suchen und zu verwalten.
    //Dasselbe gilt für TopicRepository.

}
