package dk.rskovbo.blackjack

// Holds all card values and returns a corresponding image
class CardBindings {

    private val valueToDrawableMap: HashMap<Int, Int> = HashMap()
    private val stringToDrawableMap: HashMap<String, Int> = HashMap()

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
        valueToDrawableMap[24] = R.drawable.ten_of_hearts
        valueToDrawableMap[25] = R.drawable.jack_of_hearts
        valueToDrawableMap[26] = R.drawable.queen_of_hearts
        valueToDrawableMap[27] = R.drawable.king_of_hearts

        valueToDrawableMap[14] = R.drawable.ace_of_hearts
        valueToDrawableMap[15] = R.drawable.two_of_hearts
        valueToDrawableMap[16] = R.drawable.three_of_hearts
        valueToDrawableMap[17] = R.drawable.four_of_hearts
        valueToDrawableMap[18] = R.drawable.five_of_hearts
        valueToDrawableMap[19] = R.drawable.six_of_hearts
        valueToDrawableMap[20] = R.drawable.seven_of_hearts
        valueToDrawableMap[21] = R.drawable.eight_of_hearts
        valueToDrawableMap[22] = R.drawable.nine_of_hearts
        valueToDrawableMap[24] = R.drawable.ten_of_hearts
        valueToDrawableMap[25] = R.drawable.jack_of_hearts
        valueToDrawableMap[26] = R.drawable.queen_of_hearts
        valueToDrawableMap[27] = R.drawable.king_of_hearts

        valueToDrawableMap[28] = R.drawable.ace_of_clubs
        valueToDrawableMap[29] = R.drawable.two_of_clubs
        valueToDrawableMap[30] = R.drawable.three_of_clubs
        valueToDrawableMap[31] = R.drawable.four_of_clubs
        valueToDrawableMap[32] = R.drawable.five_of_clubs
        valueToDrawableMap[33] = R.drawable.six_of_clubs
        valueToDrawableMap[34] = R.drawable.seven_of_clubs
        valueToDrawableMap[35] = R.drawable.eight_of_clubs
        valueToDrawableMap[36] = R.drawable.nine_of_clubs
        valueToDrawableMap[37] = R.drawable.ten_of_clubs
        valueToDrawableMap[38] = R.drawable.jack_of_clubs
        valueToDrawableMap[39] = R.drawable.queen_of_clubs
        valueToDrawableMap[40] = R.drawable.king_of_clubs

        valueToDrawableMap[41] = R.drawable.ace_of_diamonds
        valueToDrawableMap[42] = R.drawable.two_of_diamonds
        valueToDrawableMap[43] = R.drawable.three_of_diamonds
        valueToDrawableMap[44] = R.drawable.four_of_diamonds
        valueToDrawableMap[45] = R.drawable.five_of_diamonds
        valueToDrawableMap[46] = R.drawable.six_of_diamonds
        valueToDrawableMap[47] = R.drawable.seven_of_diamonds
        valueToDrawableMap[48] = R.drawable.eight_of_diamonds
        valueToDrawableMap[49] = R.drawable.nine_of_diamonds
        valueToDrawableMap[50] = R.drawable.ten_of_diamonds
        valueToDrawableMap[51] = R.drawable.jack_of_diamonds
        valueToDrawableMap[52] = R.drawable.queen_of_diamonds
        valueToDrawableMap[53] = R.drawable.king_of_diamonds

        // TODO Maybe not needed
        stringToDrawableMap["ace_of_spades"] = R.drawable.ace_of_spades
        stringToDrawableMap["two_of_spades"] = R.drawable.two_of_spades
        stringToDrawableMap["three_of_spades"] = R.drawable.three_of_spades
        stringToDrawableMap["four_of_spades"] = R.drawable.four_of_spades
        stringToDrawableMap["five_of_spades"] = R.drawable.five_of_spades
    }

    fun getCardDrawableFromValue(card: Card): Int {
        return valueToDrawableMap[card.cardValue]!!
    }
}