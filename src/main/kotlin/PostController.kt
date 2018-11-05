import io.javalin.Context
import io.javalin.NotFoundResponse

class PostController (private val service : PostService) {

    fun findAll(context: Context) : List<Post>{
        return service.findAll()
    }

    fun findById(context: Context) : Post {

        val id = context.pathParam("id").toInt()

        return service.findById( id )
                ?: throw NotFoundResponse("post not found.")
    }

    fun create(context : Context) : Int {
        val post = context.body<Post>()

        return service.create(post)
                ?: throw InternalError("error to create a post")
    }

    fun delete(context: Context) : Boolean {
        val id =  context.pathParam("id").toInt()
        return service.delete( id )
    }

    fun update(context: Context): Boolean {
        val post = context.bodyAsClass(Post::class.java)
        val id = context.pathParam("id").toInt()

        return service.update(id, post.title, post.content)
    }
}