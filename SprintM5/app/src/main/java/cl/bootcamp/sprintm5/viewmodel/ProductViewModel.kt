package cl.bootcamp.sprintm5.viewmodel

import androidx.compose.runtime.mutableStateListOf
import cl.bootcamp.sprintm5.R
import cl.bootcamp.sprintm5.model.ProductData

class ProductViewModel {

    private val _products = mutableStateListOf<ProductData>()
    val products: List<ProductData> = _products




    fun sumProduct(product: ProductData) {
        product.quantity ++

    }

    fun subProduct(product: ProductData) {
        if (product.quantity > 0) {
            product.quantity--
        }
    }


    fun addProduct(product: ProductData) {
        if(product.addedToCart){
            sumProduct(product)

        }
        else{
            _products.add(product)
            product.addedToCart=true

        }

    }

    fun removeProduct(product: ProductData) {
        _products.remove(product)
        product.addedToCart=false

    }


    fun getTotalPrice(): Int {
        return _products.sumOf { it.price*it.quantity }

    }

    fun clearProduct(product: ProductData) {
        product.quantity=1
        product.addedToCart=false
        _products.remove(product)
    }

    fun clearProducts() {
        for(item in _products){
            item.addedToCart=false
            item.quantity=1
        }
        _products.clear()

    }



    //Encuentra el producto en la lista de productos Predefinida
    //Pensar otra forma sin pasar id a navigation

   fun getProductById(id: Int): ProductData? {
       return items.find { it.id == id }
   }



    //Lista Predefinida de Productos disponibles
    val items=listOf(
    ProductData(
    1,
    "Zapato tacon blanco",
    R.drawable.tacos,
    39990,
    " Zapato de fiesa tacon aguja.Material sintetico." +
    "La horma de este calzado es pequeña, para un calce perfecto se debe pedir un número más,"),
    ProductData(
    2,
    "Zapato formal blanco",
    R.drawable.zapatoformal,
    49990,
    "El Zapato de Hombre de 16 Hrs W414, cuida cada detalle para otorgarte" +
    " un diseño estiloso y cómodo, que te acompañe durante todo tu día " +
    " materiales suaves y ligeros y que se adaptan a la anatomía de tus pies"
    ),
    ProductData(
    3,
    "Zapato casual cafe",
    R.drawable.zapatocafe,
    74990,
    " Capellada cuero,interior sintetico")
 ,
    ProductData(
    4,
    "Zapatilla Columbia",
    R.drawable.beyondazul,
    74990,
    " Modelo Beyond"

    ),
    ProductData(
    5,
    "Zapatilla Nike nina",
    R.drawable.nikeairmax90,
    74990,
    " Modelo Air Max 90"
    ),
    ProductData(
    6,
    "Zapatilla Columbia ",
    R.drawable.trailstormblue,
    74990,
    " Modelo Trailstorm. Waterproof"
    )
    )
    //Aca separamos en listas de zapatos y zapatillas
    val shoesList = items.filter { it.id <= 3 }
    val sneakersList = items.filter { it.id > 3 }


}