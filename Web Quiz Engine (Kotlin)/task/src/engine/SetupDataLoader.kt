package engine

import com.fasterxml.jackson.databind.ObjectMapper
import engine.model.Quiz
import engine.service.QuizService
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class SetupDataLoader(
    private val quizService: QuizService
) : ApplicationListener<ContextRefreshedEvent> {
    private var alreadySetup = false

    @Transactional
    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        if (alreadySetup) return
        val quiz = ObjectMapper().readValue(
            """
            {
              "title": "The Java Logo",
              "text": "What is depicted on the Java logo?",
              "options": ["Robot","Tea leaf","Cup of coffee","Bug"],
              "answer": [2]
            }
            """.trimIndent(), Quiz::class.java
        )
        quizService.save(quiz)
        alreadySetup = true
    }
}