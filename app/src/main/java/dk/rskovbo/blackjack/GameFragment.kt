package dk.rskovbo.blackjack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import dk.rskovbo.blackjack.databinding.FragmentGameBinding
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

    val dealerCards: ArrayList<ImageView> = ArrayList()
    val playerCards: ArrayList<ImageView> = ArrayList()
    lateinit var cardsLeftInDrawPile: TextView
    lateinit var currentPlayerCount: TextView
    lateinit var currentDealerCount: TextView
    lateinit var playerMoney: TextView
    private lateinit var button: Button

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

        binding.newRound.setOnClickListener {
            restartRound()
            render()
        }

        binding.playerStopDraw.setOnClickListener {
            standOnCardsForPlayer()
        }
    }

    private fun initTextViews() {
        // Cache Textviews
        cardsLeftInDrawPile = binding.cardsLeftInDrawPile
        currentPlayerCount = binding.currentPlayerCount
        currentDealerCount = binding.currentDealerCount
        playerMoney = binding.playerMoney

        // Assign values
        cardsLeftInDrawPile.text = gameService.cardDeck.getDrawPile().size.toString()
        currentPlayerCount.text = "0"
        currentDealerCount.text = "0"
        playerMoney.text = gameService.playerMoney.toString()
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
        currentPlayerCount.text = gameService.currentPlayerCount.toString()
        currentDealerCount.text = gameService.currentDealerCount.toString()
        playerMoney.text = gameService.playerMoney.toString()
    }

    // When Stop button is pressed
    private fun standOnCardsForPlayer() {
        if (gameService.isPlayersTurn) {
            gameService.isPlayersTurn = false
            resolveDealersTurn()
        }
    }

    // When Draw Card button is pressed
    private fun drawAndRenderCardForPlayer() {
        // Disable button if it's not the player's turn
        if (gameService.isPlayersTurn) {
            val card = gameService.drawCard()
            playerCards[gameService.playerCardsPlayed - 1].setImageResource(card.cardDrawableId)
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
            while (gameService.dealerCanDraw()) {
                    delay(dealerDrawingSpeed)
                    drawAndRenderCardForDealer()
                    render()
            }
            gameService.resolveBets()
            render()
        }
    }

    private fun startRound() {
        drawAndRenderCardForPlayer()
        drawAndRenderCardForPlayer()
        val card1 = gameService.drawCardForDealerSetup()
        dealerCards[gameService.dealerCardsPlayed-1].setImageResource(card1.cardDrawableId)

        val card2 = gameService.drawCardForDealerSetup()
        dealerCards[gameService.dealerCardsPlayed-1].setImageResource(card2.cardDrawableId)
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