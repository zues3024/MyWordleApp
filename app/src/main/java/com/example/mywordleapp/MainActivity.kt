package com.example.mywordleapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
class MainActivity : AppCompatActivity() {
    var wordToGuess = FourLetterWordList.getRandomFourLetterWord()
    var guessCount = 0;

    /**
     * Parameters / Fields:
     *   wordToGuess : String - the target word the user is trying to guess
     *   guess : String - what the user entered as their guess
     *
     * Returns a String of 'O', '+', and 'X', where:
     *   'O' represents the right letter in the right place
     *   '+' represents the right letter in the wrong place
     *   'X' represents a letter not in the target word
     */
    private fun checkGuess(guess: String) : String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }

    private fun checkForCorrect(hintString: String) : String {
        var response = ""
        if (hintString.equals("OOOO")) {
            response = "You answered correctly!"
            return response;
        }
        else {
            response = "You did not guess correctly"
            return response
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var answer = findViewById<TextView>(R.id.answer_text_view)
        answer.text = wordToGuess

        val submission = findViewById<EditText>(R.id.input_text)
        val submitButton = findViewById<Button>(R.id.submitButton)
        val resetButton = findViewById<Button>(R.id.reset_button)
        val guess1 = findViewById<TextView>(R.id.firstGuess)
        val guess2 = findViewById<TextView>(R.id.secondGuess)
        val guess3 = findViewById<TextView>(R.id.thirdGuess)
        val result1 = findViewById<TextView>(R.id.firstResult)
        val result2 = findViewById<TextView>(R.id.secondResult)
        val result3 = findViewById<TextView>(R.id.thirdResult)

        submitButton.setOnClickListener {
            var userInput = submission.text.toString()
            guessCount++
            var changeInput = when(guessCount) {
                1 -> {
                    guess1.text = submission.text.toString()
                    guess1.visibility = View.VISIBLE
                    result1.text = checkGuess(userInput)
                    result1.visibility = View.VISIBLE
                }

                2 -> {
                    userInput = submission.text.toString()
                    guess2.text = submission.text.toString()
                    guess2.visibility = View.VISIBLE
                    result2.text = checkGuess(userInput)
                    result2.visibility = View.VISIBLE
                }

                else -> {
                    userInput = submission.text.toString()
                    guess3.text = submission.text.toString()
                    guess3.visibility = View.VISIBLE
                    result3.text = checkGuess(userInput)
                    result3.visibility = View.VISIBLE
                }
            }
            submission.text.clear()
            if (checkGuess(userInput).equals("OOOO")) {
                Toast.makeText(this, checkForCorrect(checkGuess(userInput)), Toast.LENGTH_LONG).show()
                submitButton.visibility = View.INVISIBLE
                resetButton.visibility = View.VISIBLE
                answer.visibility = View.VISIBLE
                resetButton.setOnClickListener{
                    answer.visibility = View.INVISIBLE
                    guessCount = 0
                    wordToGuess = FourLetterWordList.getRandomFourLetterWord()
                    answer.text = wordToGuess
                    guess1.visibility = View.INVISIBLE
                    guess2.visibility = View.INVISIBLE
                    guess3.visibility = View.INVISIBLE
                    result1.visibility = View.INVISIBLE
                    result2.visibility = View.INVISIBLE
                    result3.visibility = View.INVISIBLE
                    resetButton.visibility = View.INVISIBLE
                    submitButton.visibility = View.VISIBLE
                }
            }
            else {
                Toast.makeText(this, checkForCorrect(checkGuess(userInput)), Toast.LENGTH_LONG).show()
            }
            if(guessCount == 3)
            {
                submitButton.visibility = View.INVISIBLE
                answer.visibility = View.VISIBLE
                submission.visibility = View.INVISIBLE
                resetButton.visibility = View.VISIBLE
                resetButton.setOnClickListener {
                    answer.visibility = View.INVISIBLE
                    submission.visibility = View.VISIBLE
                    guessCount = 0
                    wordToGuess = FourLetterWordList.getRandomFourLetterWord()
                    answer.text = wordToGuess
                    guess1.visibility = View.INVISIBLE
                    guess2.visibility = View.INVISIBLE
                    guess3.visibility = View.INVISIBLE
                    result1.visibility = View.INVISIBLE
                    result2.visibility = View.INVISIBLE
                    result3.visibility = View.INVISIBLE
                    resetButton.visibility = View.INVISIBLE
                    submitButton.visibility = View.VISIBLE
                }
            }
        }
    }
}

object FourLetterWordList {
    // List of most common 4 letter words from: https://7esl.com/4-letter-words/
    val fourLetterWords =
        "Area,Army,Baby,Back,Ball,Band,Bank,Base,Bill,Body,Book,Call,Card,Care,Case,Cash,City,Club,Cost,Date,Deal,Door,Duty,East,Edge,Face,Fact,Farm,Fear,File,Film,Fire,Firm,Fish,Food,Foot,Form,Fund,Game,Girl,Goal,Gold,Hair,Half,Hall,Hand,Head,Help,Hill,Home,Hope,Hour,Idea,Jack,John,Kind,King,Lack,Lady,Land,Life,Line,List,Look,Lord,Loss,Love,Mark,Mary,Mind,Miss,Move,Name,Need,News,Note,Page,Pain,Pair,Park,Part,Past,Path,Paul,Plan,Play,Post,Race,Rain,Rate,Rest,Rise,Risk,Road,Rock,Role,Room,Rule,Sale,Seat,Shop,Show,Side,Sign,Site,Size,Skin,Sort,Star,Step,Task,Team,Term,Test,Text,Time,Tour,Town,Tree,Turn,Type,Unit,User,View,Wall,Week,West,Wife,Will,Wind,Wine,Wood,Word,Work,Year,Bear,Beat,Blow,Burn,Call,Care,Cast,Come,Cook,Cope,Cost,Dare,Deal,Deny,Draw,Drop,Earn,Face,Fail,Fall,Fear,Feel,Fill,Find,Form,Gain,Give,Grow,Hang,Hate,Have,Head,Hear,Help,Hide,Hold,Hope,Hurt,Join,Jump,Keep,Kill,Know,Land,Last,Lead,Lend,Lift,Like,Link,Live,Look,Lose,Love,Make,Mark,Meet,Mind,Miss,Move,Must,Name,Need,Note,Open,Pass,Pick,Plan,Play,Pray,Pull,Push,Read,Rely,Rest,Ride,Ring,Rise,Risk,Roll,Rule,Save,Seek,Seem,Sell,Send,Shed,Show,Shut,Sign,Sing,Slip,Sort,Stay,Step,Stop,Suit,Take,Talk,Tell,Tend,Test,Turn,Vary,View,Vote,Wait,Wake,Walk,Want,Warn,Wash,Wear,Will,Wish,Work,Able,Back,Bare,Bass,Blue,Bold,Busy,Calm,Cold,Cool,Damp,Dark,Dead,Deaf,Dear,Deep,Dual,Dull,Dumb,Easy,Evil,Fair,Fast,Fine,Firm,Flat,Fond,Foul,Free,Full,Glad,Good,Grey,Grim,Half,Hard,Head,High,Holy,Huge,Just,Keen,Kind,Last,Late,Lazy,Like,Live,Lone,Long,Loud,Main,Male,Mass,Mean,Mere,Mild,Nazi,Near,Neat,Next,Nice,Okay,Only,Open,Oral,Pale,Past,Pink,Poor,Pure,Rare,Real,Rear,Rich,Rude,Safe,Same,Sick,Slim,Slow,Soft,Sole,Sore,Sure,Tall,Then,Thin,Tidy,Tiny,Tory,Ugly,Vain,Vast,Very,Vice,Warm,Wary,Weak,Wide,Wild,Wise,Zero,Ably,Afar,Anew,Away,Back,Dead,Deep,Down,Duly,Easy,Else,Even,Ever,Fair,Fast,Flat,Full,Good,Half,Hard,Here,High,Home,Idly,Just,Late,Like,Live,Long,Loud,Much,Near,Nice,Okay,Once,Only,Over,Part,Past,Real,Slow,Solo,Soon,Sure,That,Then,This,Thus,Very,When,Wide"

    // Returns a list of four letter words as a list
    fun getAllFourLetterWords(): List<String> {
        return fourLetterWords.split(",")
    }

    // Returns a random four letter word from the list in all caps
    fun getRandomFourLetterWord(): String {
        val allWords = getAllFourLetterWords()
        val randomNumber = (0..allWords.size).shuffled().last()
        return allWords[randomNumber].lowercase()
    }
}