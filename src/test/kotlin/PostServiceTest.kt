import org.junit.Test
import org.koin.test.KoinTest
import shared.KindServiceResponse
import kotlin.test.assertEquals

class PostServiceTest : KoinTest {

    @Test
    fun `should be return posts when call findAll and exist posts`() {
        val repository = PostRepositoryBuilder()
                        .findAll()
                        .build()

        val service = PostServiceImpl(repository)

        val postsResult = service.findAll()

        assert(postsResult.Success())
        assertEquals(3, postsResult.Result?.size)
    }

    @Test
    fun `should be return NotFound when call findAll and not exist posts`(){
        val repository = PostRepositoryBuilder()
                        .notFoundFindAll()
                        .build()

        val service = PostServiceImpl(repository)

        val postsResult = service.findAll()

        assertEquals(false, postsResult.Success())
        assertEquals(KindServiceResponse.NOT_FOUND, postsResult.getErrorKind())
    }

    @Test
    fun `should be return InternalServerError when call findAll and throw some exception`(){
        val repository = PostRepositoryBuilder()
                        .throwExceptionFindAll()
                        .build()

        val service = PostServiceImpl(repository)

        val postsResult = service.findAll()

        assertEquals(false, postsResult.Success())
        assertEquals(KindServiceResponse.INTERNAL_SERVER_ERROR, postsResult.getErrorKind())
    }

    @Test
    fun `should be return post when call find post by id`(){
        val expectedId = 1
        val repository = PostRepositoryBuilder()
            .findById(expectedId)
            .build()

        val service = PostServiceImpl(repository)

        val postResult = service.findById(expectedId)

        assert(postResult.Success())
        assertEquals(expectedId, postResult.Result?.id)
    }
}
