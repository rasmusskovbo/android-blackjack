package dk.rskovbo.blackjack.model

import dk.rskovbo.blackjack.R
import dk.rskovbo.blackjack.model.Card

// Holds maps of all card values and returns either drawable or string.
class CardBindings {

    companion object {
        val valueToDrawableMap: HashMap<Int, Int> = HashMap()
        val valueToStringMap: HashMap<Int, String> = HashMap()

        fun getCardDrawableFromValue(value: Int): Int {
            return valueToDrawableMap[value]!!
        }

        fun getCardNameFromValue(value: Int): String {
            return valueToStringMap[value]!!
        }

        init {
            valueToDrawableMap[0] = 0

            valueToDrawableMap[1] = R.drawable.ace_of_spades
            valueToDrawableMap[2] = R.drawable.two_of_spades
            valueToDrawableMap[3] = R.drawable.three_of_spades
            valueToDrawableMap[4] = R.drawable.four_of_spades
            valueToDrawableMap[5] = R.drawable.five_of_spades
            valueToDrawableMap[6] = R.drawable.six_of_spades
            valueToDrawableMap[7] = R.drawable.seven_of_spades
            valueToDrawableMap[8] = R.drawable.eight_of_spades
            valueToDrawableMap[9] = R.drawable.nine_of_spades
            valueToDrawableMap[10] = R.drawable.ten_of_spades
            valueToDrawableMap[11] = R.drawable.jack_of_spades
            valueToDrawableMap[12] = R.drawable.queen_of_spades
            valueToDrawableMap[13] = R.drawable.king_of_spades

            valueToDrawableMap[14] = R.drawable.ace_of_hearts
            valueToDrawableMap[15] = R.drawable.two_of_hearts
            valueToDrawableMap[16] = R.drawable.three_of_hearts
            valueToDrawableMap[17] = R.drawable.four_of_hearts
            valueToDrawableMap[18] = R.drawable.five_of_hearts
            valueToDrawableMap[19] = R.drawable.six_of_hearts
            valueToDrawableMap[20] = R.drawable.seven_of_hearts
            valueToDrawableMap[21] = R.drawable.eight_of_hearts
            valueToDrawableMap[22] = R.drawable.nine_of_hearts
            valueToDrawableMap[23] = R.drawable.ten_of_hearts
            valueToDrawableMap[24] = R.drawable.jack_of_hearts
            valueToDrawableMap[25] = R.drawable.queen_of_hearts
            valueToDrawableMap[26] = R.drawable.king_of_hearts

            valueToDrawableMap[27] = R.drawable.ace_of_clubs
            valueToDrawableMap[28] = R.drawable.two_of_clubs
            valueToDrawableMap[29] = R.drawable.three_of_clubs
            valueToDrawableMap[30] = R.drawable.four_of_clubs
            valueToDrawableMap[31] = R.drawable.five_of_clubs
            valueToDrawableMap[32] = R.drawable.six_of_clubs
            valueToDrawableMap[33] = R.drawable.seven_of_clubs
            valueToDrawableMap[34] = R.drawable.eight_of_clubs
            valueToDrawableMap[35] = R.drawable.nine_of_clubs
            valueToDrawableMap[36] = R.drawable.ten_of_clubs
            valueToDrawableMap[37] = R.drawable.jack_of_clubs
            valueToDrawableMap[38] = R.drawable.queen_of_clubs
            valueToDrawableMap[39] = R.drawable.king_of_clubs

            valueToDrawableMap[40] = R.drawable.ace_of_diamonds
            valueToDrawableMap[41] = R.drawable.two_of_diamonds
            valueToDrawableMap[42] = R.drawable.three_of_diamonds
            valueToDrawableMap[43] = R.drawable.four_of_diamonds
            valueToDrawableMap[44] = R.drawable.five_of_diamonds
            valueToDrawableMap[45] = R.drawable.six_of_diamonds
            valueToDrawableMap[46] = R.drawable.seven_of_diamonds
            valueToDrawableMap[47] = R.drawable.eight_of_diamonds
            valueToDrawableMap[48] = R.drawable.nine_of_diamonds
            valueToDrawableMap[49] = R.drawable.ten_of_diamonds
            valueToDrawableMap[50] = R.drawable.jack_of_diamonds
            valueToDrawableMap[51] = R.drawable.queen_of_diamonds
            valueToDrawableMap[52] = R.drawable.king_of_diamonds

        }

        init {
            valueToStringMap[0] = "empty"

            valueToStringMap[1] = "ace_of_spades"
            valueToStringMap[2] = "two_of_spades"
            valueToStringMap[3] = "three_of_spades"
            valueToStringMap[4] = "four_of_spades"
            valueToStringMap[5] = "five_of_spades"
            valueToStringMap[6] = "six_of_spades"
            valueToStringMap[7] = "seven_of_spades"
            valueToStringMap[8] = "eight_of_spades"
            valueToStringMap[9] = "nine_of_spades"
            valueToStringMap[10] = "ten_of_spades"
            valueToStringMap[11] = "jack_of_spades"
            valueToStringMap[12] = "queen_of_spades"
            valueToStringMap[13] = "king_of_spades"

            valueToStringMap[14] = "ace_of_hearts"
            valueToStringMap[15] = "two_of_hearts"
            valueToStringMap[16] = "three_of_hearts"
            valueToStringMap[17] = "four_of_hearts"
            valueToStringMap[18] = "five_of_hearts"
            valueToStringMap[19] = "six_of_hearts"
            valueToStringMap[20] = "seven_of_hearts"
            valueToStringMap[21] = "eight_of_hearts"
            valueToStringMap[22] = "nine_of_hearts"
            valueToStringMap[23] = "ten_of_hearts"
            valueToStringMap[24] = "jack_of_hearts"
            valueToStringMap[25] = "queen_of_hearts"
            valueToStringMap[26] = "king_of_hearts"

            valueToStringMap[27] = "ace_of_clubs"
            valueToStringMap[28] = "two_of_clubs"
            valueToStringMap[29] = "three_of_clubs"
            valueToStringMap[30] = "four_of_clubs"
            valueToStringMap[31] = "five_of_clubs"
            valueToStringMap[32] = "six_of_clubs"
            valueToStringMap[33] = "seven_of_clubs"
            valueToStringMap[34] = "eight_of_clubs"
            valueToStringMap[35] = "nine_of_clubs"
            valueToStringMap[36] = "ten_of_clubs"
            valueToStringMap[37] = "jack_of_clubs"
            valueToStringMap[38] = "queen_of_clubs"
            valueToStringMap[39] = "king_of_clubs"

            valueToStringMap[40] = "ace_of_diamonds"
            valueToStringMap[41] = "two_of_diamonds"
            valueToStringMap[42] = "three_of_diamonds"
            valueToStringMap[43] = "four_of_diamonds"
            valueToStringMap[44] = "five_of_diamonds"
            valueToStringMap[45] = "six_of_diamonds"
            valueToStringMap[46] = "seven_of_diamonds"
            valueToStringMap[47] = "eight_of_diamonds"
            valueToStringMap[48] = "nine_of_diamonds"
            valueToStringMap[49] = "ten_of_diamonds"
            valueToStringMap[50] = "jack_of_diamonds"
            valueToStringMap[51] = "queen_of_diamonds"
            valueToStringMap[52] = "king_of_diamonds"
        }

    }


}