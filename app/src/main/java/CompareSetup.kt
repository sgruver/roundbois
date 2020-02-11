import java.io.Serializable
import com.example.roundbois.Setup

class CompareSetup(setup1: Setup, setup2: Setup) : Serializable {
    var newSetup: Setup? = setup1
    var currentSetup: Setup? = setup2
}