package com.test.quiz.service;

import com.test.quiz.DTO.AnswerDto;
import com.test.quiz.entity.Question;
import com.test.quiz.entity.QuestionDetails;
import com.test.quiz.entity.Quiz;
import com.test.quiz.entity.Student;
import com.test.quiz.repositories.QuestionRepo;
import com.test.quiz.repositories.QuizRepo;
import com.test.quiz.repositories.StudentRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class QuizService {
    @Autowired
    StudentRepo studentRepo;

    @Autowired
    QuizRepo quizRepo;

    @Autowired
    QuestionRepo questionRepo;

    public ResponseEntity<?> startSession(HttpSession session, Student student) {
        try {
            Student student1 = studentRepo.findByEmail(student.getEmail());
            if(student1.getEmail().equals(student.getEmail()) && student1.getPassword().equals(student.getPassword())){
                Quiz quiz = new Quiz();
                quiz.setDate(LocalDate.now());
                quiz.setStudent(student1);
                student1.getQuizList().add(quiz);
                List<Quiz> temp = studentRepo.save(student1).getQuizList();
                quiz = temp.get(temp.size()-1);

                //create session
                String sessionId = session.getId();
                session.setAttribute("studentId",student1.getStudentId());
                session.setAttribute("startTime", LocalDate.now());
                session.setAttribute("QuizId",quiz);

                return new ResponseEntity<>("Session ID : "+sessionId, HttpStatus.CREATED);
            }
            return new ResponseEntity<>("Not Fount", HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getQuestion(HttpSession session) {
        try {
            if(session.getAttribute("studentId")==null){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No active session found");
            }else{
                Quiz quiz = (Quiz) session.getAttribute("QuizId");
                if(quiz!=null) {
                    Question question = questionRepo.getRandomQuestion(quiz.getQuizId());


                    if(question!=null) {
                        HashMap<String,String> map = new HashMap<>();
                        map.put("QuestionID",String.valueOf(question.getQuestionId()));
                        map.put("Question",question.getQuestion());
                        map.put("A",question.getA());
                        map.put("B",question.getB());
                        map.put("C",question.getC());
                        map.put("D",question.getD());
                        return new ResponseEntity<>(map,HttpStatus.OK);
                    }else{
                        return new ResponseEntity<>("No more questions available",HttpStatus.NOT_FOUND);
                    }
                }
                return new ResponseEntity<>("Restart quiz, It is not defined.",HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> addQuestion(List<Question> list) {
        try {
            if(questionRepo.saveAll(list).size()==list.size()){
                return new ResponseEntity<>("All question saved.",HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity<>("Not saved all questions list.",HttpStatus.ACCEPTED);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> answer(HttpSession session, AnswerDto answerDto) {
        try{
            Question question = questionRepo.findById(answerDto.getQuestionId()).get();
            if(question!=null){
                Quiz sessionAttribute = (Quiz) session.getAttribute("QuizId");
                Quiz quiz = quizRepo.findById(sessionAttribute.getQuizId()).get();
                if(quiz!=null) {
                    quiz.setTotalAttemptQuestion(quiz.getTotalAttemptQuestion() + 1);

                    //check true false
                    if (answerDto.getAnswer().equals(question.getAnswer()))
                        quiz.setTotalCorrect(quiz.getTotalCorrect() + 1);
                    else
                        quiz.setTotalIncorrect(quiz.getTotalIncorrect() + 1);

                    //create object for detail
                    QuestionDetails questionDetails = new QuestionDetails();
                    questionDetails.setStudentAnswer(answerDto.getAnswer());
                    questionDetails.setQuestion(question);
                    questionDetails.setQuiz(quiz);
                    List<QuestionDetails> questionDetailsList = quiz.getQuestionDetailsList();
                    questionDetailsList.add(questionDetails);
                    quiz.setQuestionDetailsList(questionDetailsList);
                    quizRepo.save(quiz);

                    if (answerDto.getAnswer().equals(question.getAnswer()))
                        return new ResponseEntity<>("Correct answer! Well done.",HttpStatus.OK);
                    else
                        return new ResponseEntity<>("Wrong answer!",HttpStatus.OK);
                }
                else{
                    return new ResponseEntity<>("Restart quiz, It is not defined.",HttpStatus.OK);
                }
            }
            return new ResponseEntity<>("Not present any question with ID: "+answerDto.getQuestionId(),HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getSummary(HttpSession session) {
        try {
            Quiz sessionAttribute = (Quiz) session.getAttribute("QuizId");
            Quiz quiz = quizRepo.findById(sessionAttribute.getQuizId()).get();
            if(quiz!=null){
                List<HashMap<String,String>> listmap = new ArrayList<>();
                listmap.add(new HashMap<>());
                listmap.get(0).put("QuizId",String.valueOf(quiz.getQuizId()));
                listmap.get(0).put("Total Attempted Questions : ",String.valueOf(quiz.getTotalAttemptQuestion()));
                listmap.get(0).put("Total Correct Questions : ",String.valueOf(quiz.getTotalCorrect()));
                listmap.get(0).put("Total Incorrect Questions : ",String.valueOf(quiz.getTotalIncorrect()));
                for(QuestionDetails i : quiz.getQuestionDetailsList()){
                    listmap.add(new HashMap<>());
                    listmap.get(listmap.size()-1).put("Question",i.getQuestion().getQuestion());
                    listmap.get(listmap.size()-1).put("Student Answer",String.valueOf(i.getStudentAnswer()));
                    listmap.get(listmap.size()-1).put("Right Answer",String.valueOf(i.getQuestion().getAnswer()));
                }
                return new ResponseEntity<>(listmap,HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Restart quiz, It is not defined.",HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
