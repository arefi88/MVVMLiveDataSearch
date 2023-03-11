package com.example.appmvvmkotlin.model

import kotlinx.coroutines.delay
import kotlin.random.Random

object OrderDataGenerator {
    private val currentUser = arrayListOf<User>()
    private val  currentProduct= arrayListOf<Product>()
    private val currentOrder = arrayListOf<Order>()

    private const val stdDelay = 2000L

    init {
        for (i in 1..100){
            generateUser()
            generateProduct()
        }
        for (i in 1..100){
            generateOrders()
        }
    }

   suspend fun getAllOrders():List<Order> {
        delay(stdDelay)
       return currentOrder
    }

   suspend fun searchOrders(query:String):List<Order>{
        delay(stdDelay)
      return currentOrder.filter {order->
           order.product.name.lowercase().contains(query.lowercase()) or
                   order.product.brand.lowercase().contains(query.lowercase()) or
                   order.user.name.lowercase().contains(query.lowercase()) or
                   order.user.email.lowercase().contains(query.lowercase())
       }
    }

    private fun generateUser() {
        val index = Random.nextInt(userList.size)
        val userName = userList[index]
        val email="$userName@gmail.com"
        val userId=1000 + currentUser.size

        currentUser.add(User(userId,userName,email))
    }
    private fun generateProduct(){
        val brandIndex = Random.nextInt(brandList.size)
        val brandName= brandList[brandIndex]
        val productIndex= Random.nextInt(productList.size)
        val productName= productList[productIndex]
        val price=Random.nextInt(1000)
        val productId=2000 + currentProduct.size
        currentProduct.add(Product(productId,productName,brandName,price))
    }
    private fun generateOrders(){
        val user= currentUser[Random.nextInt(currentUser.size)]
        val product= currentProduct[Random.nextInt(currentProduct.size)]
        val orderId= 3000 + currentOrder.size

        currentOrder.add(Order(orderId,user,product))
    }
}