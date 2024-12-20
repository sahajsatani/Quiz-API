package com.test.quiz.repositories;

import com.test.quiz.entity.Question;
import com.test.quiz.enums.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepo extends JpaRepository<Question,Integer> {
    @Query(value = "select * from tblquestion where question_id not in (select question_id from tblquestiondetails where quiz_id=?1) order by RAND() LIMIT 1",nativeQuery = true)
    Question getRandomQuestion(int quizId);


}
