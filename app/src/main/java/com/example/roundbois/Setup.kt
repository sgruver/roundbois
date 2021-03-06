package com.example.roundbois

import java.io.Serializable

class Setup(var wheel:Wheel, var tire:Tire, var fender:Fender) : Serializable {
    val totalHeight: Double
        get() = this.wheel.diameter + (2 * this.tire.sidewall) + this.fender.depth
}

class Wheel(var diameter:Int, var width:Double, var offset:Int, var bore:Double?, var boltPattern:String?, var maxBrake:Int?, var weight:Double?) : Serializable{
    var inset: Double = (width*25.4)/2 + offset
    var outset: Double = (width*25.4)/2 - offset

    constructor() : this(18, 10.0, 20, null, null, null, null)
}

class Tire(var diameter:Int, var width:Int, var aspect:Int, var widthRange:String?, var load:Int?) : Serializable {
    var stretch: Int? = null
    var sidewall: Double = (width*aspect*.01)/25.4
    var circumference: Double = ((sidewall*2)+diameter)*Math.PI
    var revMile: Double = 63360/circumference

    constructor() : this(18, 255, 35, null, null)
}

class Fender(var pull:Int, var height:Int, var width:Int, var depth:Int, var camber:Double) : Serializable {
    var tuck: Int? = null
    var fitment: Int? = null

    constructor() : this(1, 27, 1, 4, 0.0)
}