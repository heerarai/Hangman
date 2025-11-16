# Hangman

Authors: Suhani Rai, Joshua Kim

## Overview

This program is a simple, text-based Hangman game written in Java.
The game randomly selects a word from a provided .txt file (or a default list if the file is empty or missing).
The user then tries to guess the word one letter at a time before reaching 9 incorrect attempts.

 ---

## Features

- Randomly selects a secret word from a file or from 5 default fallback words
- Displays underscores for each unguessed letter
- Ensures user only enters one valid lowercase letter
- Tracks and prints incorrect guesses
- Reveals letters in the correct positions when guessed
- Ends the game when:
  - The word is fully guessed ️
  - The user reaches 9 incorrect attempts 
- Uses a do-while loop to manage the main game cycle

___

## File Input

- The program expects one argument, the name of a .txt file containing words.
- Each line in the file is treated as a separate playable word.
- If:
  - No file is provided → prompts the user
  - File not found → displays an error
  - File is empty → defaults to these words:
    - headphone
    - notebook
    - apple
    - icecream
    - bluetooth

___

## Program Flow

- Load words from file
- Randomly choose one word
- Initialize revealedLetters with underscores (_)
- Enter game loop (do-while):
   - Ask the user for a single lowercase letter
   - Validate the input
   - Check if the letter exists in the word
   - Update board
   - Track incorrect attempts
- End the game when:
   - The word is fully revealed
   - The user reaches 9 incorrect guesses
