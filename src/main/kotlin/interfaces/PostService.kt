package interfaces

import Post
import shared.ServiceResponse

interface PostService {
    fun findAll() : ServiceResponse<List<Post>>
    fun findById(id : Int) : ServiceResponse<Post>
    fun create(target : Post) : ServiceResponse<Int>
    fun delete(id : Int) : ServiceResponse<Boolean>
    fun update(id : Int, title : String, content : String) : ServiceResponse<Boolean>
}