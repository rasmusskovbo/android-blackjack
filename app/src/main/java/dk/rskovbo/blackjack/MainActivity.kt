package dk.rskovbo.blackjack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dk.rskovbo.blackjack.model.Card
import dk.rskovbo.blackjack.model.CardBindings

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}