# Quiz App API Documentation
Project Summary
This project is a Quiz Application built using Spring Boot. It provides a REST API for managing quiz sessions, retrieving questions, submitting answers, and viewing results. The application uses HttpSession to maintain user-specific quiz sessions and features endpoints for quiz management and participation.

# Endpoints
1.Register Student
Endpoint: /student/register
Method: POST
Description: Registers a new student in the system. The student's details are saved in the database for future reference and participation in quizzes.

2. Start a New Quiz Session
Endpoint: /quiz/start
Method: POST
Description: Initializes a new quiz session for a user.
Request Body:
{
    "userName":"sahaj",
    "email":"sahaj1032@gmail.com",
    "password":"sahaj"
}
Response: Session initialized message with user details

3. Get a Random Question
Endpoint: /quiz/question
Method: GET
Description: Fetches a random multiple-choice question for the user from the database.
Headers: Requires session tracking through HttpSession.
Response:
{
    "A": "Elephant",
    "B": "Blue Whale",
    "C": "Giraffe",
    "D": "Great White Shark",
    "QuestionID": "3",
    "Question": "What is the largest mammal on Earth?"
}

4. Submit an Answer
Endpoint: /quiz/answer
Method: POST
Description: Submits the user's answer for the current question.
Request Body:
{
    "questionId":3,
    "answer":"B"
}
Response: Result of the submitted answer (correct/incorrect).

5. Add Questions
Endpoint: /quiz/addQuestion
Method: POST
Description: Adds a list of questions to the quiz database.
Request Body:
[
  {
    "question": "What is the capital of France?",
    "a": "Paris",
    "b": "London",
    "c": "Rome",
    "d": "Berlin",
    "answer": "A"
  }
]
Response: Status message indicating the success or failure of adding questions.

6. Get Quiz Summary
Endpoint: /quiz/summary
Method: GET
Description: Retrieves a summary of the quiz session, including the total questions answered, correct answers, and incorrect answers.
Response:
[
    {
        "Total Correct Questions : ": "1",
        "Total Attempted Questions : ": "1",
        "Total Incorrect Questions : ": "0",
        "QuizId": "1"
    },
    {
        "Question": "What is the largest mammal on Earth?",
        "Student Answer": "B",
        "Right Answer": "B"
    }
]

7. Logout
Endpoint: /quiz/logout
Method: POST
Description: Logs the user out by invalidating the session.
Response:
Logged out successfully.
