import io.mockk.every
import io.mockk.spyk

class PostRepositoryBuilder {

    private var repository = spyk<PostRepositoryImpl>()

    fun throwExceptionFindAll() : PostRepositoryBuilder {

        every { repository.findAll() } throws Exception("Error to connect in database.")
        return this
    }

    fun throwExceptionFindById(id :Int) : PostRepositoryBuilder {

        every { repository.findById(id) } throws Exception("Error to connect in database.")
        return this
    }


    fun findAll() : PostRepositoryBuilder {

        every { repository.findAll() } returns listOf(
            Post(1, "Teste", "Content"),
            Post(2, "Teste", "Content"),
            Post(3, "Teste", "Content")
        )

        return this
    }

    fun findById(id : Int) : PostRepositoryBuilder {

        every { repository.findById(id) } returns Post(id, "Teste", "Content")

        return this
    }


    fun notFoundFindAll(): PostRepositoryBuilder {
        every { repository.findAll() } returns emptyList()

        return this
    }

    fun notFoundFindById(id : Int): PostRepositoryBuilder {
        every { repository.findById(id) } returns null

        return this
    }


    fun build() : interfaces.PostRepository {
        return repository
    }


}