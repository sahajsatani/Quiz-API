package com.test.quiz.controller;

import com.test.quiz.DTO.AnswerDto;
import com.test.quiz.entity.Question;
import com.test.quiz.entity.Student;
import com.test.quiz.service.QuizService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("/start")
    public ResponseEntity<?> startSession(HttpSession session, @RequestBody Student student){
        return quizService.startSession(session,student);
    }

    @GetMapping("/question")
    public ResponseEntity<?> getQuestion(HttpSession session){
        return quizService.getQuestion(session);
    }

    @PostMapping("/answer")
    public ResponseEntity<?> answer(HttpSession session,@RequestBody AnswerDto answerDto){
        return quizService.answer(session,answerDto);
    }

    @PostMapping("/addQuestion")
    public ResponseEntity<?> addQuestion(@RequestBody List<Question> list){
        for (Question i : list)
            System.out.println(i.toString());
        return quizService.addQuestion(list);
    }

    @GetMapping("/summary")
    public ResponseEntity<?> getSummary(HttpSession session){
        return quizService.getSummary(session);
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        if (session != null) {
            session.invalidate(); // Invalidate the session
        }
        return "Logged out successfully.";
    }
}
