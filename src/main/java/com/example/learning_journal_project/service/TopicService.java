package com.example.learning_journal_project.service;

import com.example.learning_journal_project.model.Topic;
import com.example.learning_journal_project.repository.TopicRepository;
import com.example.learning_journal_project.repository.UserRepository;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Builder
public class TopicService {
    private final TopicRepository topicRepository;
    private final UserRepository userRepository;

    public void createTopic(Topic topic) {
        topicRepository.save(topic);
    }

    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    public void deleteTopic(Long topicId) {
        topicRepository.deleteById(topicId);
    }

}
