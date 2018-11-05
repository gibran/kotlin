import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class PostRepository{

    object PostTable : Table(name = "posts") {
        val id = integer("id").autoIncrement().primaryKey()
        var subject = varchar("title", 255)
        val description = text("description")
    }

    private var database =  Database.connect("jdbc:postgresql://localhost:5432/blogpost", "org.postgresql.Driver", "postgres",password = "example")

    fun findAll() : List<Post> {
        var postResult = emptyList<Post>()

        database
        transaction {
            postResult = PostTable
                    .selectAll()
                    .map {
                        Post(it[PostTable.id], it[PostTable.subject], it[PostTable.description])
                    }
        }

        return postResult
    }

    fun deleteById(id: Int) : Boolean {
        var result = 0

        database
        transaction {
            result = PostTable.deleteWhere { PostTable.id eq id }
        }

        return result > 0
    }

    fun create (title : String, content : String) : Int? {
        var result : Int? = null

        database
        transaction {
            addLogger(StdOutSqlLogger)
            var post = PostTable.insert { it ->
                it[subject] = title
                it[description] = content
            }

            result = post.generatedKey?.toInt() ?: null
        }

        return result
    }

    fun update (id : Int, title : String, content : String) : Boolean  {
        var result = 0

        database
        transaction {
            result = PostTable.update ({ PostTable.id eq id }) { it ->
                it[subject] = title
                it[description] = content
            }
        }

        return result > 0
    }

    fun findById(id: Int) : Post? {
        var post: Post? = null

        database
        transaction {
            post = PostTable.select { PostTable.id eq id }
                    .map {
                        Post(it[PostTable.id], it[PostTable.subject], it[PostTable.description])
                    }.first()
        }

        return post
    }
}

