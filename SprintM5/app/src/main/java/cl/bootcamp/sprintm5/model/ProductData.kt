package cl.bootcamp.sprintm5.model

data class ProductData(
    val id: Int,
    val name: String,
    val image: Int,
    val price: Int,
    val desc: String,
    var quantity: Int = 1,
    var addedToCart: Boolean = false

)
