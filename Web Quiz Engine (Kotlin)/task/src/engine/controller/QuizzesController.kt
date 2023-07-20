package engine.controller

import engine.dto.NewQuizDTO
import engine.dto.QuizDTO
import engine.model.AnswerDTO
import engine.service.QuizService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.PositiveOrZero


@RestController
@Validated
@RequestMapping("/api/quizzes")
class QuizzesController(
    private val quizService: QuizService
) {
    @PostMapping
    fun createQuiz(@RequestBody @Valid newQuizDTO: NewQuizDTO): ResponseEntity<Any> {
        return ResponseEntity.ok().body(QuizDTO.fromEntity(quizService.save(NewQuizDTO.toEntity(newQuizDTO))))
    }


    @GetMapping("")
    fun getAllQuizzes(): ResponseEntity<Any> {
        return ResponseEntity.ok().body(quizService.findAll().map { QuizDTO.fromEntity(it) })
    }

    @GetMapping("/{id}")
    fun getQuizById(@PathVariable id: Long): ResponseEntity<Any> {
        val quiz = quizService.findById(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok().body(QuizDTO.fromEntity(quiz))
    }

    class AnswerDTO(@field:NotEmpty var answer: Set<Int>)

    @PostMapping("/{id}/solve")
    fun solve(
        @PathVariable @NotNull @PositiveOrZero id: Long,
        @RequestBody @Valid answerDTO: AnswerDTO
    ): ResponseEntity<Any> {
        val quiz = quizService.findById(id) ?: return ResponseEntity.notFound().build()

        if (quiz.answer == answerDTO.answer) {
            return ResponseEntity.ok().body(AnswerDTO(true, "Congratulations, you're right!"))
        }

        return ResponseEntity.ok().body(AnswerDTO(false, "Wrong answer! Please, try again."))
    }
}










