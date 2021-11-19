package dk.rskovbo.blackjack.model

import kotlin.random.Random

class CardDeckDrawPile(cardDecksInPlay: Int) {

    private var drawPile: ArrayList<Card> = ArrayList()
    private val randomizer: Random = Random
    private val cardDeckFactory: CardDeckFactory = CardDeckFactory()

    init {
        for (i in 1..cardDecksInPlay) {
            drawPile += cardDeckFactory.getCardDeck()
        }
    }

    fun drawCard(): Card {
        val card = drawPile[randomizer.nextInt(drawPile.size)]
        drawPile.remove(card)
        return card
    }

    fun getDrawPile(): ArrayList<Card> {
        return drawPile
    }

}