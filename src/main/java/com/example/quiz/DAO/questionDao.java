package com.example.quiz.DAO;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.quiz.models.Question;


public interface questionDao extends JpaRepository<Question,Integer>{

    List<Question> findByCategory(String category);

    @Query(value = "SELECT * FROM Question q where q.category=:category ORDER BY RANDOM() LIMIT :numQ",nativeQuery = true)
    List<Question> getRandomQuestionByCategory(String category, int numQ);

}
