package cl.bootcamp.mobistore.model

data class Catalog(
    val results: ArrayList<Product>
)

data class Product (
    val id: Int,
    val name: String,
    val price: Int,
    val image: String
)


