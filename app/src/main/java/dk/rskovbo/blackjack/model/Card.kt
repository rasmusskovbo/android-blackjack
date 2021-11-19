package dk.rskovbo.blackjack.model

class Card(
    var countValue: Int,
    val cardName: String,
    var deckCountValue: Int,
    val cardDrawableId: Int
) {
    fun isAce(): Boolean{
        return cardName.contains("ace")
    }
}

