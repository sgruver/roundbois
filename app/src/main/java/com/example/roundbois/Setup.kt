package com.example.roundbois

import java.io.Serializable

class Setup(var wheel:Wheel, var tire:Tire, var fender:Fender) : Serializable {
    //constructor(w:Wheel) : this(w,,)
    //constructor() : this(null, null,null)

    var diameter = this.wheel.diameter
    var sidewall = this.tire.sidewall
    var depth = this.fender.depth

    var totalHeight = diameter + (2 * sidewall) + depth
}

class Wheel(var diameter:Int, var width:Double, var offset:Int, var bore:Double?, var boltPattern:String?, var maxBrake:Int?, var weight:Double?) : Serializable{
    var inset: Double = (width*25.4)/2 + offset
    var outset: Double = (width*25.4)/2 - offset

    constructor() : this(18, 10.0, 20, null, null, null, null)
    constructor(diameter:Int, width:Double, offset:Int) : this(diameter, width, offset, null, null, null, null)
}

class Tire(var diameter:Int, var width:Int, var aspect:Int, var widthRange:String?, var load:Int?) : Serializable {
    var stretch: Int? = null
    var sidewall: Double = (width*aspect*.01)/25.4
    var circumference: Double = ((sidewall*2)+diameter)*Math.PI
    var revMile: Double = 63360/circumference

    constructor() : this(18, 255, 35, null, null)
    constructor(diameter: Int, width: Int, aspect: Int) : this(diameter,width,aspect,null,null)
}

class Fender(var pull:Int, var height:Int, var width:Int, var depth:Int, var camber:Double) : Serializable {
    var tuck: Int? = null
    var fitment: Int? = null

    constructor() : this(1, 27, 1, 4, 0.0)
    constructor(pull:Int, height:Int, width:Int) : this(pull,height,width,4,0.0)
    constructor(camber: Double) : this(1, 27, 1, 4, camber)
}