package dk.rskovbo.blackjack.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dk.rskovbo.blackjack.R
import dk.rskovbo.blackjack.databinding.FragmentGameBinding
import dk.rskovbo.blackjack.game.GameService
import dk.rskovbo.blackjack.game.GameStats
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * An example full-screen fragment that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class GameFragment : Fragment() {
    private var _binding: FragmentGameBinding? = null

    private val gameService: GameService = GameService()

    // Cards
    val dealerCards: ArrayList<ImageView> = ArrayList()
    val playerCards: ArrayList<ImageView> = ArrayList()

    // TextViews
    lateinit var cardsLeftInDrawPile: TextView
    lateinit var playerMoney: TextView
    lateinit var currentBet: TextView
    lateinit var currentDeckCount: TextView
    lateinit var gameStatus: TextView


    // Animation
    private var dealersTurnDelay: Long = 2_000
    private var dealerDrawingSpeed: Long = 1_000

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameBinding.inflate(inflater, container, false)

        initCardViews()
        initTextViews()
        initButtons()

        return binding.root

    }

    private fun initButtons() {
        binding.drawPlayerCard.setOnClickListener {
            drawAndRenderCardForPlayer()
            render()
        }

        binding.playerStopDraw.setOnClickListener {
            standOnCardsForPlayer()
        }

        binding.newRound.setOnClickListener {
            restartRound()
            render()
        }


        binding.raiseBetButton.setOnClickListener{
            if (gameService.raiseBet()) gameStatus.text = "Raised bet" else gameStatus.text = "Not enough money!"
           render()
        }

        binding.lowerBetButton.setOnClickListener{
            if (gameService.lowerBet()) gameStatus.text = "Lowered bet" else gameStatus.text = "Can't lower bet further"
            render()
        }

        toggleButtonVisibility()
    }

    private fun initTextViews() {
        // Cache Textviews
        cardsLeftInDrawPile = binding.cardsLeftInDrawPile
        playerMoney = binding.playerMoney
        currentBet = binding.currentBet
        currentDeckCount = binding.currentDeckCount
        gameStatus = binding.gameStatusText

        // Assign values
        cardsLeftInDrawPile.text = gameService.cardDeck.getDrawPile().size.toString()
        playerMoney.text = gameService.playerMoney.toString()
        currentBet.text = gameService.currentBet.toString()
        currentDeckCount.text = "0"
    }

    private fun initCardViews() {
        dealerCards.add(binding.dealerCards1)
        dealerCards.add(binding.dealerCards2)
        dealerCards.add(binding.dealerCards3)
        dealerCards.add(binding.dealerCards4)
        dealerCards.add(binding.dealerCards5)

        playerCards.add(binding.playerCards1)
        playerCards.add(binding.playerCards2)
        playerCards.add(binding.playerCards3)
        playerCards.add(binding.playerCards4)
        playerCards.add(binding.playerCards5)

        emptyCardViews()
    }

    private fun render() {
        cardsLeftInDrawPile.text = gameService.cardDeck.getDrawPile().size.toString()
        currentDeckCount.text = gameService.currentDeckCount.toString()
        playerMoney.text = gameService.playerMoney.toString()
        currentBet.text = gameService.currentBet.toString()
        toggleButtonVisibility()
    }

    // When Stop button is pressed
    private fun standOnCardsForPlayer() {
        if (gameService.isPlayersTurn) {
            gameService.isPlayersTurn = false
            gameStatus.text = "Dealer's turn!"
            resolveDealersTurn()
        }
    }

    // When Draw Card button is pressed
    private fun drawAndRenderCardForPlayer() {
        if (gameService.isPlayersTurn) {
            val card = gameService.drawCard()
            playerCards[gameService.playerCardsPlayed - 1].setImageResource(card.cardDrawableId)

            if (gameService.playerHasBlackjack() && gameService.dealerCanDraw()) {
                gameStatus.text = "BLACKJACK! Dealer's turn"
            }
            if (gameService.playerHasExceeded21()) {
                gameStatus.text = "Bust! You lose"
                gameService.resolveBets()
                gameService.isMidGame = false
                redirectIfPlayerIsBroke()
                redirectIfNotEnoughCardsLeft()
                toggleButtonVisibility()
            }

            if (!gameService.playerCanContinue() && gameService.dealerCanDraw()) resolveDealersTurn()
        }
    }

    private fun drawAndRenderCardForDealer() {
        val card = gameService.drawCard()
        dealerCards[gameService.dealerCardsPlayed-1].setImageResource(card.cardDrawableId)
    }

    // When it's the dealers turn
    private fun resolveDealersTurn() {
        GlobalScope.launch(Dispatchers.Main) {
            delay(dealersTurnDelay)
            while (gameService.dealerCanDraw()) {
                gameStatus.text = "Dealer's drawing!"
                delay(dealerDrawingSpeed)
                drawAndRenderCardForDealer()
                render()
            }

            if (gameService.didPlayerWin()) {
                if (gameService.dealerHasExceeded21()) {
                    gameStatus.text = "Dealer bust! You win!"
                } else {
                    gameStatus.text = "You win! Your " + gameService.currentPlayerCount + " pts beat the dealer's " + gameService.currentDealerCount + " pts"
                }
            } else {
                if (gameService.dealerHasBlackjack()) {
                   gameStatus.text = "Dealer's got BLACKJACK! You lose."
                } else {
                    gameStatus.text =
                        "You lose. Dealer's " + gameService.currentDealerCount + " pts beat or was equal to your " + gameService.currentPlayerCount + " pts"
                }
            }
            gameService.isMidGame = false
            gameService.resolveBets()
            render()
            redirectIfPlayerIsBroke()
            redirectIfNotEnoughCardsLeft()
        }
    }

    private fun startRound() {
        gameStatus.text = ""
        GameStats.handsPlayed++
        gameService.isMidGame = true
        if (gameService.isCurrentBetHigherThanPlayerMoney()) {
            gameService.currentBet = gameService.playerMoney
            currentBet.text = gameService.currentBet.toString()
        }
        toggleButtonVisibility()

        drawAndRenderCardForPlayer()
        drawAndRenderCardForPlayer()

        val card = gameService.drawCardForDealerSetup()
        dealerCards[gameService.dealerCardsPlayed-1].setImageResource(card.cardDrawableId)

    }

    private fun toggleButtonVisibility() {
        if (gameService.isMidGame) {
            binding.drawPlayerCard.isVisible = true
            binding.playerStopDraw.isVisible = true
            binding.newRound.isVisible = false
            binding.raiseBetButton.isVisible = false
            binding.lowerBetButton.isVisible = false
        } else {
            binding.drawPlayerCard.isVisible = false
            binding.playerStopDraw.isVisible = false
            binding.newRound.isVisible = true
            binding.raiseBetButton.isVisible = true
            binding.lowerBetButton.isVisible = true
        }
    }

    private fun redirectIfPlayerIsBroke() {
        if (gameService.isPlayerBroke()) {
            binding.gameButtonsLayout.isVisible = false
            gameService.isMidGame = false
            GameStats.reasonForGameOver = "You've lost all your money!"
            GlobalScope.launch(Dispatchers.Main) {
                delay(2000)
                gameStatus.text = "You've run out of money!"
                delay(2000)
                GameStats.finalWinnings = 0
                findNavController().navigate(R.id.action_gameFragment_to_showScoreFragment)
            }
        }
    }

    private fun redirectIfNotEnoughCardsLeft() {
        if (!gameService.isEnoughCardsToPlayARound()) {
            binding.gameButtonsLayout.isVisible = false
            gameService.isMidGame = false
            GameStats.reasonForGameOver = "No more cards in the deck"
            GlobalScope.launch(Dispatchers.Main) {
                delay(2000)
                gameStatus.text = "No cards left in the draw pile - Game has ended."
                delay(2000)
                gameService.didPlayerWinAnyMoney()
                findNavController().navigate(R.id.action_gameFragment_to_showScoreFragment)
            }
        }
    }

    private fun restartRound() {
        gameService.resetRoundStats()
        emptyCardViews()
        startRound()
    }

    private fun emptyCardViews() {
        // Assign empty images
        dealerCards.forEach {
            it.setImageResource(0)
        }
        playerCards.forEach {
            it.setImageResource(0)
        }
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}