import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import org.koin.dsl.module.module
import org.koin.standalone.KoinComponent
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.get

class Application : KoinComponent {

    fun start() {

        val myModules = module {
            single<interfaces.PostService> { PostServiceImpl(get()) }
            single<interfaces.PostRepository> { PostRepositoryImpl() }
        }

        startKoin(listOf(myModules))

        val controller = PostController(get())

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
}
