package com.example.appmvvmkotlin.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appmvvmkotlin.databinding.OrderItemBinding
import com.example.appmvvmkotlin.model.Order

class OrderAdapter(private val order:ArrayList<Order>):RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    inner class OrderViewHolder(val binding:OrderItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(order: Order){
            binding.orderId.text=order.id.toString()
            binding.product.text=order.product.toString()
            binding.user.text=order.user.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val binding=OrderItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return OrderViewHolder(binding)
    }

    override fun getItemCount() = order.size



    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(order[position])
    }
    fun updateOrders(newOrders:List<Order>){
        order.clear()
        order.addAll(newOrders)
        notifyDataSetChanged()
    }
}