package engine.dto

import engine.model.Quiz
import org.modelmapper.ModelMapper

class NewQuizDTO(
    var title: String? = null,
    var text: String? = null,
    var options: List<String>? = null,
    var answer: Int? = null,
) {
    companion object{
        fun toEntity(newQuizDTO: NewQuizDTO): Quiz = ModelMapper().map(newQuizDTO, Quiz::class.java).apply {
            answer = mutableListOf(newQuizDTO.answer!!)
        }
    }
}