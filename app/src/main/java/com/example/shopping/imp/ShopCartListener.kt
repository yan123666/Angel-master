package com.example.shopping.imp

import android.view.View

/**
 * @Describe 右侧商品数据增加减少点击接口
 */
interface ShopCartListener {

    fun onReduce(parentId:String, parentCount:Int){

    }

    fun onAdd(view: View, parentId: String, parentCount: Int){

    }
}