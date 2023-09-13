package com.example.quiz.DAO;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.quiz.models.quizModel;

public interface quizDAO extends JpaRepository<quizModel,Integer>{

    @Query(value = "Select * from quiz_model qm where qm.title =:quizTitle LIMIT 1",nativeQuery = true)
    Optional<quizModel> findByTitle(String quizTitle);
}
