# QuizApp Spring Boot Backend

QuizApp Spring Boot Backend is the backend API for the QuizApp, a cross-platform quiz application developed using Flutter. This README provides an overview of the backend API's features and how to use it.
- For the frontend of this application with flutter ,you can check this repo.
[Quiz App Flutter Frontend](https://github.com/Ultranmus/Quiz-App-Flutter-Frontend)

## Features

The QuizApp Spring Boot Backend provides the following features:

1. **API Endpoints**:
   - Various RESTful API endpoints to support the QuizApp front-end features.
   - Various end points are :-
     
    ### - /questions/allQuestions
   This end point give all questions from database.
   
    ### - /questions/add
   This end point add questions in the database.
   
    ### - /quiz/get/{quizId}
   This end point give a list of questions belonging to a quiz by quiz id from database.
   
    ### - /quiz/create?category=category&numQ=numQ&title=title
   This end point is used to create random quiz with category,number of questions and a title.
   
    ### - /quiz/createCustom/{title}
   This end point create a custom quiz by accepting list of question ids and title and create the quiz in database.
   
    ### - /quiz/submit/{quizId}
   This end point is used to check and score the correct answered question selected by the user.
   
    ### - /quiz/get/title/{quizId}
   This end point is used to get quiz title by quiz id.
   
    ### - /quiz/get/id/{quizTitle}
   This end point is used to get quiz id by quiz title.
     
3. **Database Integration**:
   - Integration with a PostgreSQL database to store and retrieve quiz questions and quiz details.

4. **Quiz Management**:
   - Ability to create, read and score quizzes and questions.

5. **User Authentication**:
   - Secure endpoints using user authentication with custom filter to authenticate api-key.
   - And please don't misuse my api-key.

6. **Error Handling**:
   - Comprehensive error handling to provide informative responses.
