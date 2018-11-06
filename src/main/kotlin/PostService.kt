import shared.ServiceResponse

class PostService (private val repository: PostRepository) : IPostService {

    override fun findAll() :  ServiceResponse<List<Post>> {
        var result  = ServiceResponse<List<Post>>()
        var postList = repository.findAll()

        if (postList == null || !postList.any())
            return result.setNotFound("Posts not found")

        return result.setResult(postList)
    }

    override fun findById(id : Int) : ServiceResponse<Post> {
        var result = ServiceResponse<Post>()

        try {
            var post = repository.findById(id) ?: return result.setNotFound("Post not found")

            return result.setResult(post)

        }catch(e : Exception){
            return result.setInternalServerError(e.message!!)
        }
    }

    override fun create(target : Post) : ServiceResponse<Int> {
        var result = ServiceResponse<Int>()

        var key = repository.create(target.title, target.content) ?:
                        return result.setInternalServerError("Error to create a post")

        return result.setResult(key)
    }

    override fun delete(id: Int) : ServiceResponse<Boolean> {
        var result = ServiceResponse<Boolean>()

        var deleted = repository.deleteById( id )

        if (deleted)
            return result.setResult(deleted)
        else
            return result.setInternalServerError("Error to delete a post")
    }

    override fun update(id : Int, title : String, content : String): ServiceResponse<Boolean> {
        var result = ServiceResponse<Boolean>()

        var updated = repository.update(id, title, content)

        if (updated)
            return result.setResult(updated)
        else
            return result.setInternalServerError("Error to update a post")
    }
}

interface IPostService {
    fun findAll() : ServiceResponse<List<Post>>
    fun findById(id : Int) : ServiceResponse<Post>
    fun create(target : Post) : ServiceResponse<Int>
    fun delete(id : Int) : ServiceResponse<Boolean>
    fun update(id : Int, title : String, content : String) : ServiceResponse<Boolean>
}