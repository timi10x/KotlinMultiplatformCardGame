package me.aliyuolalekan.common.domain.model

import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import me.aliyuolalekan.common.Strings
import me.aliyuolalekan.common.ingame.data.CardImages

private const val PICK_TWO = 2
private const val PICK_THREE = 5
private const val GENERAL_MARKET = 14
private const val HOLD_ON = 8
private const val I_NEED = 20

typealias ImagePath = String

data class Card(
    val shape: CardShape,
    val number: CardNumber,
    val front: ImagePath,
    val back: ImagePath = CardImages.cardBack
) {
    val strike: Strike
        get() = when (number.value) {
            PICK_TWO -> Strike.PICK_TWO
            PICK_THREE -> Strike.PICK_THREE
            GENERAL_MARKET -> Strike.GENERAL_MARKET
            HOLD_ON -> Strike.HOLD_ON
            I_NEED -> Strike.I_NEED
            else -> Strike.NONE
        }

    val isKing: Boolean
        get() = shape == CardShape.King

    override fun equals(other: Any?): Boolean {
        if (other !is Card) return false
        return other.number == number && other.shape == shape
    }

    override fun hashCode(): Int {
        return shape.hashCode() + number.hashCode()
    }

    private var isFlipped: Boolean = false

    fun flip() {
        isFlipped = !isFlipped
    }

    fun matchKingCard(card: Card): Boolean =
        card.shape == CardShape.King || card.shape == shape

    fun matches(card: Card): Boolean =
        card.shape == CardShape.King || card.number.value == number.value || card.shape == shape

    val face: String
        get() = if (isFlipped) back else front

    companion object {
        val DEFAULT = Card(CardShape.King, CardNumber.TWENTY, "", "")
    }
}

enum class Strike(val power: Int, val strikeName: String) {
    PICK_TWO(2, StrikeName.PICK_TWO),
    PICK_THREE(3, StrikeName.PICK_THREE),
    GENERAL_MARKET(1, StrikeName.GENERAL_MARKET),
    HOLD_ON(0, StrikeName.HOLD_ON),
    I_NEED(0, StrikeName.I_NEED),
    NONE(0, StrikeName.NONE)
}

fun Strike.isKingStrike() = this == Strike.I_NEED

object StrikeName {
    const val PICK_TWO = "Pick Two"
    const val PICK_THREE = "Pick Three"
    const val GENERAL_MARKET = "General Market"
    const val HOLD_ON = "Hold On"
    const val I_NEED = "King Needs"
    const val NONE = "NONE"
}

sealed class CardShape {
    abstract fun string(): String

    object Circle : CardShape() {
        override fun string(): String = Strings.CIRCLE
    }

    object Square : CardShape() {
        override fun string(): String = Strings.SQUARE
    }

    object Cross : CardShape() {
        override fun string(): String = Strings.CROSS
    }

    object Triangle : CardShape() {
        override fun string(): String = Strings.TRIANGLE
    }

    object Star : CardShape() {
        override fun string(): String = Strings.STAR
    }

    object King : CardShape() {
        override fun string(): String = Strings.KING
    }
}

enum class CardNumber(val value: Int) {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SEVEN(7),
    EIGHT(8),
    TEN(10),
    ELEVEN(11),
    TWELVE(12),
    THIRTEEN(13),
    FOURTEEN(14),
    TWENTY(20);
}

fun Card.formattedName() = face.replace("_", " ").toUpperCase(Locale.current)

fun Card.shapeName() = shape.string()

fun Int.toCardNumber(): CardNumber =
    CardNumber.values().first { it.value == this }

