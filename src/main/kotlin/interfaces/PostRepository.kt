package interfaces

import Post

interface PostRepository {
    fun findAll() : List<Post>
    fun deleteById(id: Int) : Boolean
    fun create (title : String, content : String) : Int?
    fun update (id : Int, title : String, content : String) : Boolean
    fun findById(id: Int) : Post?
}