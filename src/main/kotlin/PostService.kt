
class PostService (private val repository: PostRepository){

    fun findAll() : List<Post>{
        return repository.findAll()
    }

    fun findById(id : Int) : Post? {
        return repository.findById( id )
    }

    fun create(target : Post) : Int? {

        return repository.create(target.title, target.content)
    }

    fun delete(id: Int) : Boolean {
        return repository.deleteById( id )
    }

    fun update(id : Int, title : String, content : String): Boolean {
        return repository.update(id, title, content)
    }
}