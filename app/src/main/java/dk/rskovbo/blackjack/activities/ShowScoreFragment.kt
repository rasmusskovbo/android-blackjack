package dk.rskovbo.blackjack.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import dk.rskovbo.blackjack.R
import dk.rskovbo.blackjack.game.GameStats


class ShowScoreFragment : Fragment() {

    lateinit var finalWinnings: TextView
    lateinit var cardsPlayed: TextView
    lateinit var handsPlayed: TextView
    lateinit var winsPrHand: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding = inflater.inflate(R.layout.fragment_show_score, container, false)

        finalWinnings = binding.findViewById(R.id.final_winnings)
        cardsPlayed = binding.findViewById(R.id.cards_played)
        handsPlayed = binding.findViewById(R.id.hands_played)
        winsPrHand = binding.findViewById(R.id.wins_pr_hand)

        cardsPlayed.text = GameStats.cardsPlayed.toString()
        finalWinnings.text = GameStats.finalWinnings.toString()
        handsPlayed.text = GameStats.handsPlayed.toString()
        winsPrHand.text = GameStats.getWinningsPrHand().toString()

        binding.findViewById<Button>(R.id.replay_game_button).setOnClickListener {
            findNavController().navigate(R.id.action_showScoreFragment_to_gameFragment)
        }

        binding.findViewById<Button>(R.id.main_menu_button).setOnClickListener {
            findNavController().navigate(R.id.action_showScoreFragment_to_mainMenuFragment)
        }

        return binding
    }

}