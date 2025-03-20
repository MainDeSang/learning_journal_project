package com.example.learning_journal_project.repository;

import com.example.learning_journal_project.model.User;
import com.example.learning_journal_project.model.Vintage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VintageRepository extends JpaRepository<Vintage, Long>{
    List<Vintage> id(Long id);

    Long Id(Long id);
}
