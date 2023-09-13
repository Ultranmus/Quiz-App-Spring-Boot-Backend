package com.example.quiz.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class QuestionWrapper {

     private int id;
     private String questionTitle;
     private String option1;
     private String option2;
     private String option3;
     private String option4;

}
