import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*

fun main(args: Array<String>) {

    val repository = PostRepository()
    val service = PostService(repository)
    val controller = PostController(service)

    val app = Javalin.create().start(7000)

    app.routes {
        path("posts"){
            get { ctx -> ctx.json( controller.findAll(ctx) ) }

            post { ctx -> ctx.json( controller.create(ctx) ) }

            path(":id"){
                delete { ctx -> ctx.json( controller.delete(ctx) ) }

                patch { ctx -> ctx.json(controller.update(ctx)) }

                get { ctx -> ctx.json(controller.findById(ctx)) }
            }
        }
    }
}
