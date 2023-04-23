package com.example.tictactoe_msics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.tictactoe_msics.databinding.ActivityMainBinding
import android.app.AlertDialog

class MainActivity : AppCompatActivity() {
    var pvc = false
    var pvp = false
    var p1Name = "Player1"

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        findViewById<Button>(binding.button11.id)
            .setOnClickListener {
                clicked(findViewById<Button>(binding.button11.id))
            }
        findViewById<Button>(binding.button12.id)
            .setOnClickListener {
                clicked(findViewById<Button>(binding.button12.id))
            }
        findViewById<Button>(binding.button13.id)
            .setOnClickListener {
                clicked(findViewById<Button>(binding.button13.id))
            }
        findViewById<Button>(binding.button21.id)
            .setOnClickListener {
                clicked(findViewById<Button>(binding.button21.id))
            }
        findViewById<Button>(binding.button22.id)
            .setOnClickListener {
                clicked(findViewById<Button>(binding.button22.id))
            }
        findViewById<Button>(binding.button23.id)
            .setOnClickListener {
                clicked(findViewById<Button>(binding.button23.id))
            }
        findViewById<Button>(binding.button31.id)
            .setOnClickListener {
                clicked(findViewById<Button>(binding.button31.id))
            }
        findViewById<Button>(binding.button32.id)
            .setOnClickListener {
                clicked(findViewById<Button>(binding.button32.id))
            }
        findViewById<Button>(binding.button33.id)
            .setOnClickListener {
                clicked(findViewById<Button>(binding.button33.id))
            }

        findViewById<Button>(binding.buttonpvp.id)
            .setOnClickListener {
                pvp = true
                pvc = false
            }
        findViewById<Button>(binding.buttonpvc.id)
            .setOnClickListener {
                pvc = true
                pvp = false
            }

        findViewById<Button>(binding.buttonreset.id)
            .setOnClickListener {
                reset()
            }

        findViewById<Button>(binding.nameSend.id)
            .setOnClickListener {
                p1Name = binding.inputName.text.toString()
                binding.inputName.text.clear()
                welcome()


            }
    }

    val gameresult: Array<Array<Int>> = Array(3) {
        Array(3) {
            0
        }
    }

    var winconditionx = false
    var winconditiono = false

    fun clicked(view:View){
        var currentButton = view as Button
        if(pvp){
            move(currentButton)
        }
        else if(pvc){
            movepvc(currentButton)
        }
    }

    var currentTurn = 1
    var movecounter = 0
    fun move(currentButton:Button){
        if(currentButton.text != "X" && currentButton.text != "O" && movecounter != 9){
            if(currentTurn == 1){
                currentButton.text = "X"
                currentTurn = 2
                movecounter += 1
                addtoresult(currentButton, 1)
                wincondition(1)
                if(winconditionx){
                    winmessage(p1Name + " wins")
                    movecounter = 9
                }
                if((movecounter == 9) && (winconditionx != true) && (winconditiono != true)){
                    winmessage("Draw")
                }
            }
            else{
                currentButton.text = "O"
                currentTurn = 1
                movecounter += 1
                addtoresult(currentButton, 2)
                wincondition(2)
                if(winconditiono){
                    winmessage("Player2 wins")
                    movecounter = 9
                }
            }
        }
    }

    fun movepvc(currentButton:Button){
        if(currentButton.text != "X" && currentButton.text != "O" && movecounter != 9){
            if(currentTurn == 1) {
                currentButton.text = "X"
                currentTurn = 2
                movecounter += 1
                addtoresult(currentButton, 1)
                wincondition(1)
                if (winconditionx) {
                    winmessage(p1Name + " wins")
                    movecounter = 9
                }
                if(movecounter < 9){
                    movepvcAI()
                    wincondition(2)
                    if(winconditiono){
                        winmessage("Computer wins")
                        movecounter = 9
                    }
                }
                else if((movecounter == 9) && (winconditionx != true) && (winconditiono != true)){
                    winmessage("Draw")
                }
            }
        }
    }

    fun movepvcAI(){
        var checkforvalidmove = false
        while(checkforvalidmove == false){
            var i = (0..2).random()
            var j = (0..2).random()
            if(gameresult[i][j] == 0){
                checkforvalidmove = true
                gameresult[i][j] = 2
                if(i == 0 && j == 0){
                    (binding.button11).text = "O"
                }
                else if(i == 0 && j == 1){
                    (binding.button12).text = "O"
                }
                else if(i == 0 && j == 2){
                    (binding.button13).text = "O"
                }
                else if(i == 1 && j == 0){
                    (binding.button21).text = "O"
                }
                else if(i == 1 && j == 1){
                    (binding.button22).text = "O"
                }
                else if(i == 1 && j == 2){
                    (binding.button23).text = "O"
                }
                else if(i == 2 && j == 0){
                    (binding.button31).text = "O"
                }
                else if(i == 2 && j == 1){
                    (binding.button32).text = "O"
                }
                else if(i == 2 && j == 2){
                    (binding.button33).text = "O"
                }
                currentTurn = 1
                movecounter += 1
            }
        }

    }

    fun addtoresult(currentButton:Button, value:Int){
        if(currentButton.id == binding.button11.id){
            gameresult[0][0] = value
        }
        if(currentButton.id == binding.button12.id){
            gameresult[0][1] = value
        }
        if(currentButton.id == binding.button13.id){
            gameresult[0][2] = value
        }
        if(currentButton.id == binding.button21.id){
            gameresult[1][0] = value
        }
        if(currentButton.id == binding.button22.id){
            gameresult[1][1] = value
        }
        if(currentButton.id == binding.button23.id){
            gameresult[1][2] = value
        }
        if(currentButton.id == binding.button31.id){
            gameresult[2][0] = value
        }
        if(currentButton.id == binding.button32.id){
            gameresult[2][1] = value
        }
        if(currentButton.id == binding.button33.id){
            gameresult[2][2] = value
        }
    }

    fun wincondition(value:Int){
        if(gameresult[0][0] == value && gameresult[0][1] == value && gameresult [0][2] == value){
            valuecheck(value)
        }
        else if(gameresult[1][0] == value && gameresult[1][1] == value && gameresult [1][2] == value){
            valuecheck(value)
        }
        else if(gameresult[2][0] == value && gameresult[2][1] == value && gameresult [2][2] == value){
            valuecheck(value)
        }
        else if(gameresult[0][0] == value && gameresult[1][0] == value && gameresult [2][0] == value){
            valuecheck(value)
        }
        else if(gameresult[0][1] == value && gameresult[1][1] == value && gameresult [2][1] == value){
            valuecheck(value)
        }
        else if(gameresult[0][2] == value && gameresult[1][2] == value && gameresult [2][2] == value){
            valuecheck(value)
        }
        else if(gameresult[0][0] == value && gameresult[1][1] == value && gameresult [2][2] == value){
            valuecheck(value)
        }
        else if(gameresult[0][2] == value && gameresult[1][1] == value && gameresult [2][0] == value){
            valuecheck(value)
        }
    }

    fun valuecheck(value:Int){
        if(value == 1){
            winconditionx = true
        }
        else if(value == 2){
            winconditiono = true
        }
    }

    fun welcome() {
        AlertDialog.Builder(this)
            .setTitle("Welcome!")
            .setMessage("Hello " + p1Name + "!")
            .setCancelable(true)
            .show()
    }

    fun winmessage(winner: String) {
        AlertDialog.Builder(this)
            .setTitle("Game result:")
            .setMessage(winner)
            .setCancelable(true)
            .show()
    }

    fun reset(){
        (binding.button11).text = " "
        (binding.button12).text = " "
        (binding.button13).text = " "
        (binding.button21).text = " "
        (binding.button22).text = " "
        (binding.button23).text = " "
        (binding.button31).text = " "
        (binding.button32).text = " "
        (binding.button33).text = " "
        var i = 0
        var j = 0
        while(i <= 2){
            while(j <= 2){
                gameresult[i][j] = 0
                j += 1
            }
            j = 0
            i += 1
        }

        pvp = false
        pvc = false
        winconditionx = false
        winconditiono = false
        currentTurn = 1
        movecounter = 0
    }
}

