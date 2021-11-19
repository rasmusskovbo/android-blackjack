package dk.rskovbo.blackjack.model

import kotlin.random.Random

class CardDeckFactory {

    private val cardDeck = ArrayList<Card>()

    fun getCardDeck(): ArrayList<Card> {
        return cardDeck
    }

    private val randomizer: Random = Random

    init {
        for (i in 1..52) {
            if (i in 1..13) {
                cardDeck.add(
                    Card(i ,CardBindings.getCardNameFromValue(i), 0, CardBindings.getCardDrawableFromValue(i))
                )
            }
            if (i in 14..26) {
                cardDeck.add(
                    Card(i-13 ,CardBindings.getCardNameFromValue(i), 0, CardBindings.getCardDrawableFromValue(i))
                )
            }
            if (i in 27..39) {
                cardDeck.add(
                    Card(i-26 ,CardBindings.getCardNameFromValue(i), 0, CardBindings.getCardDrawableFromValue(i))
                )
            }
            if (i in 40..52) {
                cardDeck.add(
                    Card(i-39 ,CardBindings.getCardNameFromValue(i), 0, CardBindings.getCardDrawableFromValue(i))
                )
            }
        }

        cardDeck.forEach {
            if (it.cardName.contains("jack") || it.cardName.contains("queen") || it.cardName.contains("king")) {
                    it.countValue = 10
            }
        }

        cardDeck.forEach {
            if (it.countValue in 2..6) {
                it.deckCountValue = 1
            }
            if (it.countValue == 10 || it.isAce()) {
                it.deckCountValue = -1
            }
        }

    }

}