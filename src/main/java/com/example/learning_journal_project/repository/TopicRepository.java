package com.example.learning_journal_project.repository;

import com.example.learning_journal_project.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {

}
