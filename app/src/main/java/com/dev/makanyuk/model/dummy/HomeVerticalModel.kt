package com.dev.makanyuk.model.dummy

class HomeVerticalModel(title: String, price: String, src: String, rating: Float) {

    var title = ""
    var price = ""
    var src = ""
    var rating = 0f

    init {
        this.title = title
        this.price = price
        this.src = src
        this.rating = rating
    }
}