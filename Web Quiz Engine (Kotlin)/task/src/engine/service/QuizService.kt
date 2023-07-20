package engine.service

import engine.model.Quiz
import engine.repository.QuizRepository
import org.springframework.stereotype.Service

@Service
class QuizService (
    private val quizRepository: QuizRepository
){
    fun save(quiz: Quiz): Quiz = quizRepository.save(quiz)
    fun findById(id: Long): Quiz? = quizRepository.findById(id).orElse(null)
    fun findAll(): MutableList<Quiz> = quizRepository.findAll()
}