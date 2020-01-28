import java.io.Serializable

class CompareSetup(setup1: Setup, setup2: Setup) : Serializable {
    var newSetup: Setup? = setup1
    var currentSetup: Setup? = setup2
}