# Air Hockey
---
A two player game of Air Hockey. This object-oriented game features two playable paddles so you can play it locally with your friend!
---
### A Gif depiction
![Air Hockey GIF](https://github.com/Jacob-Lillywhite/AirHockey/blob/master/Screenshots/AirHockey.gif)
---
Currently this works by drawing on top of a JPanel that sits on a JFrame. The game takes place in an infinite loop defined by ticks. If the puck reaches a border it 'bounces' away by inverting its velocity. If it intersects a paddle it doubles its current horizontal velocity, while vertical speed increments slowly to avoid the puck bouncing up and down forever while players wait for it to get to one side. If a puck makes it into one of the goals the score is incremented for the corresponding player.
---
### Future Improvements?: 
- [x] First to 10 points Victory Condition.
- [x] Simple AI controlled paddle
- [ ] Implement Gameplay Selection (aka 'Player vs AI' or 'Player vs Player')
- [ ] Possibly Change how the Paddle collision works for TOP and BOTTOM hits
- [ ] Clean Up code
---
