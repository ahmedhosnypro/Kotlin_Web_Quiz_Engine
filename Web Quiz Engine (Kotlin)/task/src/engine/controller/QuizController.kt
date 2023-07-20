package engine.controller

import engine.dto.QuizDTO
import engine.model.AnswerDTO
import engine.service.QuizService
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.constraints.NotNull
import javax.validation.constraints.PositiveOrZero


@RestController
@Validated
@RequestMapping("/api/quiz")
class QuizController (
    private val quizService: QuizService
){
    @GetMapping("")
    fun getQuiz(): ResponseEntity<Any>{
        return ResponseEntity.ok().body(quizService.findById(1)?.let { QuizDTO.fromEntity(it) })
    }

    @PostMapping("")
    fun solve(@RequestParam @NotNull @PositiveOrZero answer: Int): ResponseEntity<Any>{
        if (answer == 2){
            return ResponseEntity.ok().body(AnswerDTO(true, "Congratulations, you're right!"))
        }
        return ResponseEntity.ok().body(AnswerDTO())
    }
}