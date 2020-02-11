import java.io.Serializable

class Setup(var wheel:Wheel, var tire:Tire, var fender:Fender) : Serializable {
    //constructor(w:Wheel) : this(w,,)
    //constructor() : this(null, null,null)
}

class Wheel(var diameter:Int, var width:Double, var offset:Int, var bore:Double?, var boltPattern:String?, var maxBrake:Int?, var weight:Double?) : Serializable{
    var inset: Double = (width*25.4)/2 + offset
    var outset: Double = (width*25.4)/2 - offset

    constructor() : this(18, 10.0, 20, null, null, null, null)
    constructor(diameter:Int, width:Double, offset:Int) : this(diameter, width, offset, null, null, null, null)
}

class Tire(var diameter:Int, var width:Int, var aspect:Int, var widthRange:String?, var load:Int?) : Serializable {
    var stretch: Int? = null
    var sidewall: Double = width*aspect*.01
    var circumference: Double = (sidewall*2/25.4+diameter)*3.14159
    var revMile: Double? = null

    constructor() : this(18, 255, 35, null, null)
    constructor(diameter: Int, width: Int, aspect: Int) : this(diameter,width,aspect,null,null)
}

class Fender(var pull:Int, var height:Int, var width:Int, var camber:Double) : Serializable {
    var tuck: Int? = null
    var fitment: Int? = null

    constructor() : this(20, 660, 20, 0.0)
    constructor(pull:Int, height:Int, width:Int) : this(pull,height,width,0.0)
    constructor(camber: Double) : this(20, 660, 20, camber)
}