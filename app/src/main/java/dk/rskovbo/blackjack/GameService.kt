package dk.rskovbo.blackjack

import dk.rskovbo.blackjack.model.Card
import dk.rskovbo.blackjack.model.CardDeckDrawPile

class GameService {

    var playerMoney = 100
    var currentBet = 10
    var shoesToPlay = 3

    var isPlayersTurn: Boolean = true
    var playerCardsPlayed = 0
    var dealerCardsPlayed = 0

    var currentPlayerCount = 0
    var currentDealerCount = 0

    var cardDeck = CardDeckDrawPile(shoesToPlay)

    fun resetCardDeck() {
        cardDeck = CardDeckDrawPile(shoesToPlay)
    }

    fun resetRoundStats() {
        playerCardsPlayed = 0
        dealerCardsPlayed = 0
        currentPlayerCount = 0
        currentDealerCount = 0
        isPlayersTurn = true
    }

    fun drawCard(): Card {
        val cardToDraw = cardDeck.drawCard()
        aceCountsFor11(cardToDraw)

        if (isPlayersTurn) {
            playerCardsPlayed ++
            currentPlayerCount += cardToDraw.countValue
        } else {
            dealerCardsPlayed ++
            currentDealerCount += cardToDraw.countValue
        }

        return cardToDraw
    }

    fun drawCardForDealerSetup(): Card {
        val cardToDraw = cardDeck.drawCard()

        aceCountsFor11(cardToDraw)
        dealerCardsPlayed ++
        currentDealerCount += cardToDraw.countValue

        return cardToDraw
    }

    private fun aceCountsFor11(cardToDraw: Card) {
        if (cardToDraw.isAce() && currentPlayerCount < 11) {
            cardToDraw.countValue = 11
        }
    }

    fun dealerCanDraw(): Boolean {
        return !dealerHasBlackjack() && !dealerHasExceeded21() && !dealerHasExceeded16() && !playerHasExceeded21()
    }

    fun playerCanContinue(): Boolean {
        if (!isPlayersTurn) {
            return false
        } else {
            if (playerHasExceeded21()) {
                isPlayersTurn
                resolveBets()
                return false
            } else if (playerHasBlackjack()) {
                isPlayersTurn = false
                return false
            } else {
                return true
            }
        }
    }

    fun playerHasExceeded21(): Boolean {
        if (currentPlayerCount > 21) {
            isPlayersTurn = false
            return true
        }
        return false
    }

    fun playerHasBlackjack(): Boolean {
        if (playerCardsPlayed == 5 || currentPlayerCount == 21) {
            isPlayersTurn = false
            return true
        }
        return false
    }

    fun dealerHasExceeded16(): Boolean {
        return currentDealerCount > 16
    }

    fun dealerHasExceeded21(): Boolean {
        return currentDealerCount > 21
    }

    fun dealerHasBlackjack(): Boolean {
        return dealerCardsPlayed == 5 || currentDealerCount == 21
    }

    fun didPlayerWin(): Boolean {
        return when {
            playerHasExceeded21() -> false
            dealerHasBlackjack() -> false
            dealerHasExceeded21() -> true
            playerHasBlackjack() -> true
            else -> currentPlayerCount > currentDealerCount
        }
    }

    fun resolveBets() {
        when (didPlayerWin()) {
            true -> playerMoney += currentBet
            else -> playerMoney -= currentBet
        }
    }
}