package com.codeup.blogapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Random;

@Controller
public class RollDiceController {
    public long rollDice(){
        Random rand = new Random();
        return rand.nextInt(6) +1;
    }

    @GetMapping("/RollDice")
    public String showPage(){
        return "RollDice";
    }

    @GetMapping("/RollDice/{guess}")
    public String guessDiceRoll(@PathVariable String guess, Model model){
        long diceRolled = rollDice();
        String guessResult = "";
        if (diceRolled == Long.parseLong(guess)){
            guessResult += "You guessed it!";
        } else {
            guessResult += "Not quite, try again!";
        }
        String guessDisplay = "You guessed : " + guess;
        String diceRolledDisplay = "The dice roll is : " + diceRolled;

        model.addAttribute("guess", guessDisplay);
        model.addAttribute("diceRolled", diceRolledDisplay);
        model.addAttribute("guessResult", guessResult);
        return "RollDice";
    }
}
