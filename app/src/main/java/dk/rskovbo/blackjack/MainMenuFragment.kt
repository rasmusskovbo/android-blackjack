package dk.rskovbo.blackjack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import dk.rskovbo.blackjack.model.GameSettings

class MainMenuFragment : Fragment() {
    private lateinit var amountOfDecksView: TextView
    private var amountOfDecks = 1


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = inflater.inflate(R.layout.fragment_main_menu, container, false)

        binding.findViewById<Button>(R.id.new_game).setOnClickListener {
            GameSettings.amountOfDecksToPlay = amountOfDecks
            findNavController().navigate(R.id.action_mainMenuFragment_to_gameFragment)
        }

        amountOfDecksView = binding.findViewById(R.id.amount_of_decks)
        amountOfDecksView.text = amountOfDecks.toString()

        binding.findViewById<SeekBar>(R.id.seekBar).setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                amountOfDecksView.text = progress.toString()
                amountOfDecks = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        return binding
    }

}