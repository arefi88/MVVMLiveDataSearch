package com.example.appmvvmkotlin.model

data class User(
    val id:Int,
    val name:String,
    val email:String
) {
    override fun toString() = "$name ($email)"

}

data class Product(
    val id: Int,
    val name: String,
    val brand:String,
    val price:Int
){
    override fun toString() = "$name - $brand ($$price)"


}

data class Order(
    val id:Int,
    val user: User,
    val product: Product
)

val userList = arrayListOf("Olivia","Lima","Emma","Noah","Oliver","Ava","Elijah","Sophia")
val brandList = arrayListOf("Amazon","Apple","Google","Microsoft","Tencent","Facebook","Alibaba","Intel")
val productList= arrayListOf("Tv","Clock","Bike","Xbox 360","Wii","Spaghetti","Batman","Tim Drake")