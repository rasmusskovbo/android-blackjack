package dk.rskovbo.blackjack.game

class GameStats {

    companion object {
        var reasonForGameOver = ""
        var amountOfDecksToPlay = 1
        var cardsPlayed = 0
        var finalWinnings = 0
        var handsPlayed = 0
        private var winningsPrHand = 0
        var handsWon = 0
        var winPercentage = 0

        fun getWinningsPrHand(): Int {
            if (finalWinnings <= 0) {
                return 0
            } else {
                return finalWinnings / handsPlayed
            }
        }
    }


}