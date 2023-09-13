package com.example.quiz.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.quiz.DAO.questionDao;
import com.example.quiz.DAO.quizDAO;
import com.example.quiz.models.Question;
import com.example.quiz.models.QuestionWrapper;
import com.example.quiz.models.Response;
import com.example.quiz.models.quizModel;

@Service
public class quizService {

    @Autowired
    quizDAO quizDAO;

    @Autowired
    questionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Question> questions = questionDao.getRandomQuestionByCategory(category, numQ);

        if (questions.size() == numQ) {
            quizModel quiz = new quizModel();
            quiz.setTitle(title);
            quiz.setQuestion(questions);
            quizModel savedQuiz = quizDAO.save(quiz);
            return new ResponseEntity<>(String.valueOf(savedQuiz.getId()), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Number of question in database is less then given number",
                    HttpStatus.BAD_REQUEST);

        }

    }

    public ResponseEntity<List<QuestionWrapper>> getQuiz(int quizId) {
        quizModel quizModel = quizDAO.findById(quizId).orElseThrow();
        List<QuestionWrapper> questions = new ArrayList<QuestionWrapper>();

        for (Question question : quizModel.getQuestion()) {
            QuestionWrapper questionWrapper = new QuestionWrapper(question.getId(), question.getQuestionTitle(),
                    question.getOption1(), question.getOption2(), question.getOption3(), question.getOption4());
            questions.add(questionWrapper);
        }
        return new ResponseEntity<>(questions, HttpStatus.OK);

    }

    public ResponseEntity<Integer> submitQuiz(int quizId, List<Response> responses) {

        int right = 0;

        for (Response response : responses) {
            Optional<Question> question = questionDao.findById(response.getId());

            if (question.isPresent()) {
                if (response.getReponse().equals(question.get().getRightAnswer())) {

                    right++;

                }
            }

        }

        return new ResponseEntity<Integer>(right, HttpStatus.OK);
    }

    public ResponseEntity<String> getQuizTitle(int quizId) {
        Optional<quizModel> quizModel = quizDAO.findById(quizId);
        if (quizModel.isEmpty()) {
            return new ResponseEntity<String>("No such quiz exist", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<String>(quizModel.get().getTitle(), HttpStatus.OK);

        }
    }

    public ResponseEntity<Integer> getQuizIdByTitle(String title) {
        Optional<quizModel> quizModel = quizDAO.findByTitle(title);
        if (quizModel.isEmpty()) {
            return new ResponseEntity<Integer>(-1, HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<Integer>(quizModel.get().getId(), HttpStatus.OK);
        }
    }

    public ResponseEntity<String> createCustomQuiz(String title, List<Integer> ids) {
        List<Question> questions = new ArrayList<>();
        questions.addAll(questionDao.findAllById(ids));
        quizModel quiz = new quizModel();
        quiz.setTitle(title);
        quiz.setQuestion(questions);
        quizModel savedQuiz = quizDAO.save(quiz);
        return new ResponseEntity<>(String.valueOf(savedQuiz.getId()), HttpStatus.CREATED);

    }

}