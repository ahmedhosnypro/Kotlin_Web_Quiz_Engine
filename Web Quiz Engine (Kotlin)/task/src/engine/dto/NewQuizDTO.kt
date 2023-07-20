package engine.dto

import engine.model.Quiz
import org.modelmapper.ModelMapper
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty

class NewQuizDTO(
    @field:NotBlank var title: String,
    @field:NotBlank var text: String,
    @field:NotEmpty var options: List<String>,
    var answer: Set<Int>?
) {
    companion object {
        fun toEntity(newQuizDTO: NewQuizDTO): Quiz = ModelMapper().map(newQuizDTO, Quiz::class.java).apply {
            if (answer == null) {
                answer = emptySet()
            }
        }
    }
}