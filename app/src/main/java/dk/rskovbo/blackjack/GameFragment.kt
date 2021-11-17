package dk.rskovbo.blackjack

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import dk.rskovbo.blackjack.databinding.FragmentGameBinding
import kotlin.random.Random

/**
 * An example full-screen fragment that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class GameFragment : Fragment() {
    private var _binding: FragmentGameBinding? = null
    private val cardBindings: CardBindings = CardBindings()
    private val randomizer: Random = Random

    private lateinit var dealerCard1: ImageView
    private lateinit var playerCard1: ImageView
    private lateinit var button: Button

    fun render() {
        val newCard1 = Card("", randomizer.nextInt(53))
        val newCard2 = Card("", randomizer.nextInt(53))
        dealerCard1.setImageResource(cardBindings.getCardDrawableFromValue(newCard1))
        playerCard1.setImageResource(cardBindings.getCardDrawableFromValue(newCard2))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameBinding.inflate(inflater, container, false)

        dealerCard1 = binding.dealerCards1
        playerCard1 = binding.playerCards1
        binding.button.setOnClickListener{
            render()
        }

        dealerCard1.setImageResource(cardBindings.getCardDrawableFromValue(Card("empty", 0)))
        playerCard1.setImageResource(cardBindings.getCardDrawableFromValue(Card("empty", 0)))       

        return binding.root

    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}