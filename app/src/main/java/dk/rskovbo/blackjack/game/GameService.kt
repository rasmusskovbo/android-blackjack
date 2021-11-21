package dk.rskovbo.blackjack.game

import dk.rskovbo.blackjack.model.Card
import dk.rskovbo.blackjack.model.CardDeckDrawPile

class GameService() {

    var playerMoney = 100
    var currentBet = 10
    var betChangeSize = 10

    var isMidGame = false
    var isPlayersTurn: Boolean = true
    var playerCardsPlayed = 0
    var dealerCardsPlayed = 0
    var currentPlayerCount = 0
    var currentDealerCount = 0

    var currentDeckCount = 0
    var cardDeck: CardDeckDrawPile

    init {
        cardDeck = CardDeckDrawPile(GameStats.amountOfDecksToPlay)
        // todo reset gamestats
    }

    fun resetCardDeck() {
        cardDeck = CardDeckDrawPile(GameStats.amountOfDecksToPlay)
        currentDeckCount = 0
    }

    fun resetRoundStats() {
        playerCardsPlayed = 0
        dealerCardsPlayed = 0
        currentPlayerCount = 0
        currentDealerCount = 0
        isPlayersTurn = true
        isMidGame = false
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

        currentDeckCount += cardToDraw.deckCountValue

        return cardToDraw
    }

    fun drawCardForDealerSetup(): Card {
        val cardToDraw = cardDeck.drawCard()

        aceCountsFor11(cardToDraw)
        dealerCardsPlayed ++
        currentDealerCount += cardToDraw.countValue

        currentDeckCount += cardToDraw.deckCountValue

        return cardToDraw
    }

    private fun aceCountsFor11(cardToDraw: Card) {
        if ((cardToDraw.isAce() && currentPlayerCount < 11) || (cardToDraw.isAce() && currentDealerCount < 11)) {
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

    fun raiseBet(): Boolean {
        return if ((currentBet + betChangeSize) <= playerMoney) {
            currentBet += betChangeSize
            true
        } else {
            false
        }
    }

    fun lowerBet(): Boolean {
        return if ((currentBet - betChangeSize) > 0) {
            currentBet -= betChangeSize
            true
        } else {
            false
        }
    }

    fun didPlayerWinAnyMoney() {
        if (playerMoney > 100) GameStats.finalWinnings = playerMoney - 100 else GameStats.finalWinnings = 0
    }

    fun isPlayerBroke(): Boolean {
        return playerMoney <= 0
    }

    fun isCurrentBetHigherThanPlayerMoney(): Boolean {
        return currentBet > playerMoney
    }
    
    fun isEnoughCardsToPlayARound(): Boolean {
        return cardDeck.getDrawPile().size >= 10
    }
}