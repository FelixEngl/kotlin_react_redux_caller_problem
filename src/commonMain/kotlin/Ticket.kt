import kotlin.jvm.JvmInline
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmName

@JvmInline
@Serializable
value class Ticket private constructor(val value: Long) {
    companion object {
        val NULL: Ticket = Ticket(-1L)

        @JvmName("create")
        operator fun invoke(long: Long): Ticket {
            require(long != -1L){
                "A ticket can not hold the value -1, this value is reserved for NULL!"
            }
            return Ticket(long)
        }
    }

    /**
     * Returns true iff [value] == -1.
     */
    fun isNull(): Boolean = value == -1L
}