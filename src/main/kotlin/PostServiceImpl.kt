import interfaces.PostService
import shared.ServiceResponse

class PostServiceImpl (private val repository: interfaces.PostRepository) : PostService {

    override fun findAll() :  ServiceResponse<List<Post>> {
        val result  = ServiceResponse<List<Post>>()

        try{
            val postList = repository.findAll()

            if (!postList.any())
                return result.setNotFound("Posts not found")

            return result.setResult(postList)
        }catch(e : Exception){
            return result.setInternalServerError(e.message!!)
        }
    }

    override fun findById(id : Int) : ServiceResponse<Post> {
        val result = ServiceResponse<Post>()

        try {
            val post = repository.findById(id) ?: return result.setNotFound("Post not found")

            return result.setResult(post)

        }catch(e : Exception){
            return result.setInternalServerError(e.message!!)
        }
    }

    override fun create(target : Post) : ServiceResponse<Int> {
        val result = ServiceResponse<Int>()

        try {
            val key = repository.create(target.title, target.content)
                ?: return result.setInternalServerError("Error to create a post")

            return result.setResult(key)
        } catch (e : Exception){
            return result.setInternalServerError(e.message!!)
        }
    }

    override fun delete(id: Int) : ServiceResponse<Boolean> {
        val result = ServiceResponse<Boolean>()

        return try{
            val deleted = repository.deleteById( id )

            if (deleted)
                result.setResult(deleted)
            else
                result.setInternalServerError("Error to delete a post")
        } catch (e : Exception){
            result.setInternalServerError(e.message!!)
        }
    }

    override fun update(id : Int, title : String, content : String): ServiceResponse<Boolean> {
        val result = ServiceResponse<Boolean>()

        return try {
            val updated = repository.update(id, title, content)

            if (updated)
                result.setResult(updated)
            else
                result.setInternalServerError("Error to update a post")

        } catch (e : Exception){
            result.setInternalServerError(e.message!!)
        }
    }
}

