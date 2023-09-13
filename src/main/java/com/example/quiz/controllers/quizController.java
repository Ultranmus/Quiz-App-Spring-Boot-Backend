package com.example.quiz.controllers;

import com.example.quiz.models.QuestionWrapper;
import com.example.quiz.models.Response;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz.services.quizService;

@RestController
@RequestMapping("quiz")
public class quizController {

    @Autowired
    quizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String category,@RequestParam int numQ,@RequestParam String title){
        return quizService.createQuiz(category,numQ,title);
    }

    @PostMapping("/createCustom/{title}")
    public ResponseEntity<String> createCustomQuiz(@PathVariable String title,@RequestBody List<Integer> ids){
        return quizService.createCustomQuiz(title,ids);
    }

    @GetMapping("/get/{quizId}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable int quizId){
        return quizService.getQuiz(quizId);
    }

    @GetMapping("/get/id/{title}")
    public ResponseEntity<Integer> getQuizIdByTitle(@PathVariable String title){
        return quizService.getQuizIdByTitle(title);
    }

    @GetMapping("/get/title/{quizId}")
    public ResponseEntity<String> getQuizTitle(@PathVariable int quizId){
        return quizService.getQuizTitle(quizId);
    }

    @PostMapping("/submit/{quizId}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable int quizId,@RequestBody List<Response> reponse){
        return quizService.submitQuiz(quizId,reponse);
    }

}