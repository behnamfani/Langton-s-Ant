# Langton-s-Ant
A Java Simulator for the movement of a Langton's Ant 

A Langton's Ant is a cellular automaton which has simple rules that leads in very complex emergent behavior. 
On a 2D rectangular grid, The rules are: (with a torus toplogy to manage the boundary of the grid)
1. At a white cell (O) --> turn 90° right, flip the cell, and then move forward one step 
2. At a black cell (#) --> turn 90° left, flip the cell, and then move forward one step


You choose the initial setting for the grid between complete white, complete black, checker board, random, and horizontal stripes. You can also choose the starting position and orientation of the ant. 

For example, on a uniform white grid:
The ant creates an almost symmetric pattern up to approximately 420 steps:
(step = 200)


Then is has a chaotic growth from 400 - 10000 steps and after that, it builts a highly structured, repetitive pattern (Highway) with every cycle = 104 steps.
(step = 12000)

