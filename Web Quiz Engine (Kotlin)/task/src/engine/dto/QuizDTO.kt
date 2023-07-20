package engine.dto

import engine.model.Quiz
import org.modelmapper.ModelMapper

class QuizDTO(
    var title: String? = null,
    var text: String? = null,
    var options: List<String>? = null
) {
    companion object {
        fun fromEntity(quiz: Quiz): QuizDTO = ModelMapper().map(quiz, QuizDTO::class.java)
    }
}