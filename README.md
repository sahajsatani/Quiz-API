# Quiz App API Documentation
Project Summary
This project is a Quiz Application built using Spring Boot. It provides a REST API for managing quiz sessions, retrieving questions, submitting answers, and viewing results. The application uses HttpSession to maintain user-specific quiz sessions and features endpoints for quiz management and participation.

# Instructions to Run the Code
Follow these steps to set up and run the Quiz Application API:<br>
Prerequisites<br>
Make sure the following are installed on your system:<br>

Java Development Kit (JDK): Version 8 or above<br>
Maven: Version 3.6 or above<br>
Spring Boot: Included as part of the Maven dependencies<br>
H2: To set up the database<br>
Postman or Curl: For testing the API endpoints (optional but recommended)<br>


# Endpoints

1.Register Student<br>
Endpoint: /student/register<br>
Method: POST<br>
Description: Registers a new student in the system. The student's details are saved in the database for future reference and participation in quizzes.

2. Start a New Quiz Session<br>
Endpoint: /quiz/start<br>
Method: POST<br>
Description: Initializes a new quiz session for a user.<br>
Request Body:<br>
{<br>
    "userName":"sahaj",<br>
    "email":"sahaj1032@gmail.com",<br>
    "password":"sahaj"<br>
}<br>
Response: Session initialized message with user details<br>

3. Get a Random Question<br>
Endpoint: /quiz/question<br>
Method: GET<br>
Description: Fetches a random multiple-choice question for the user from the database.<br>
Headers: Requires session tracking through HttpSession.<br>
Response:<br>
{<br>
    "A": "Elephant",<br>
    "B": "Blue Whale",<br>
    "C": "Giraffe",<br>
    "D": "Great White Shark",<br>
    "QuestionID": "3",<br>
    "Question": "What is the largest mammal on Earth?"<br>
}<br>

4. Submit an Answer<br>
Endpoint: /quiz/answer<br>
Method: POST<br>
Description: Submits the user's answer for the current question.<br>
Request Body:<br>
{<br>
    "questionId":3,<br>
    "answer":"B"<br>
}<br>
Response: Result of the submitted answer (correct/incorrect).<br>

5. Add Questions<br>
Endpoint: /quiz/addQuestion<br>
Method: POST<br>
Description: Adds a list of questions to the quiz database.<br>
Request Body:<br>
[<br>
  {<br>
    "question": "What is the capital of France?",<br>
    "a": "Paris",<br>
    "b": "London",<br>
    "c": "Rome",<br>
    "d": "Berlin",<br>
    "answer": "A"<br>
  }<br>
]<br>
Response: Status message indicating the success or failure of adding questions.<br>

6. Get Quiz Summary<br>
Endpoint: /quiz/summary<br>
Method: GET<br>
Description: Retrieves a summary of the quiz session, including the total questions answered, correct answers, and incorrect answers.<br>
Response:<br>
[<br>
    {<br>
        "Total Correct Questions : ": "1",<br>
        "Total Attempted Questions : ": "1",<br>
        "Total Incorrect Questions : ": "0",<br>
        "QuizId": "1"<br>
    },<br>
    {<br>
        "Question": "What is the largest mammal on Earth?",<br>
        "Student Answer": "B",<br>
        "Right Answer": "B"<br>
    }<br>
]<br>

7. Logout<br>
Endpoint: /quiz/logout<br>
Method: POST<br>
Description: Logs the user out by invalidating the session.<br>
Response:<br>
Logged out successfully.

# Database Design
![image](https://github.com/user-attachments/assets/c4ff10da-00c8-47bd-b88b-5e5d68efd636)
