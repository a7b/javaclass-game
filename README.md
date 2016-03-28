# Word Game Documentation

## Authors
- Tej Nair
- Tom Zhang

## Overview
Our game is a single-player educational game. It helps kids improve their typing skills while keeping the game interesting and involving. It could be used instead of programs like Type To Learn, Typing Tutor, and other common programs used in elementary school to teach kids how to type. Features could be added to teach the user math, vocabulary, or trivia facts. The game’s name has not been decided yet.

On screen, there are words spawning and dropping towards the bottom of the screen. There is also a cannon that is controlled by the user. The player has to type a word, and then presses enter to shoot a laser. If the laser beam collides with the same word as the word that is typed, the player wins a point and the word disappears. If a word reaches the bottom of the screen without being shot, then the player loses a life. The goal of the game is to get as many points as possible by destroying word without running out of lives.

When the player first starts the program, there will be a welcome screen with two buttons: `Play` and `How to Play`. If `How to Play` is clicked, the program will show instructions for how to play this game. There will be a back button at the top of the screen. When back is clicked, it will take the player back to the welcome screen. If `Play` is clicked, then the game will begin. There will be a drop-down menu at the top of the screen, with the options `New Game`, `Main Menu`, and `Exit`. The player’s score will be displayed in the top right corner of the screen. The number of lives left will be in the top left corner of the screen. At the end of the game, the screen will show the screens with the buttons `Play Again`, `Main Menu` and `Exit`, as well as the user’s score and time elapsed.

## Game Mechanics
The score starts at zero, and the number of lives starts at 5. The time elapsed, starting at zero, will be displayed in the top center part of the screen. The words will begin to spawn and then fall towards the bottom. At first, there will be very fewer words spawned and they won’t be traveling very fast. As the game progresses, the game will spawn more words and they will begin to fall at a higher speed. The player will type a word into a textbox at the bottom of the screen. When the player is ready, the player will move the mouse in the direction that they want to shoot, press enter, and a laser beam will instantly be shot. If the laser beam is on target and the word that is hit is the same word as the word that is typed into the textbox, the word will disappear and the user will gain a point. Every time an undestroyed word hits the bottom of the screen, it will disappear, and one life will be taken.

The cannon at the bottom of the screen travels at a set speed; it will not instantly point in the direction the user wants. It is always moving parallel to the bottom of the screen, either left or right. The player uses the mouse to aim the cannon in the direction they want.

## Game Controls
- Running program creates window and sets up game
- Clicking `Play` starts the game
- Clicking `How to Play` shows the Instructions
- Clicking `Back` returns to the welcome screen
- Clicking `New Game` resets the game
- Clicking `Main Menu` returns the player to the Main Menu
- Clicking `Exit` closes the window and terminates the program
- Typing a word into the text box loads the cannon
- Moving the mouse will aim the cannon in the direction that the mouse is going
- Pressing `Enter` will shoot a laser beam
- If the laser beam collides with the same word as the word that is typed into the textbox, the player gets a point and the word disappears
- The cannon is stationary and the angle is controlled by the user
- Clicking `Play Again` will perform the same function as `New Game`

## Extra stuff (to do when done)
- Show definition of word at bottom of the screen, so that ~~scrubs~~ students can learn vocabulary in addition to typing
- Have a version of the game where math problems appear on the screen instead of words, and have the user type the answer
- Have a version of the game where the user answers trivia questions, instead of words
- ~~Create a version of the game where the cannon is not controlled by the user, but simply moves left and right by itself~~

## Important things
- There is clearly a box to type in, and it’s pretty big.
- Text box must listen to hitting the enter key.
- You have X lives before you lose. Lose 1 life per word hitting the bottom.
- Words must have a border/contrast the background
- High score should be persistent
- OS X dictionary is `/usr/share/dict/words`
