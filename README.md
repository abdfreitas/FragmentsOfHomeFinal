Fragments of Home

Description
Welcome to Fragments of Home, a thrilling adventure where the player navigates through a maze, 
collecting his special star and escaping the unknown volcano planet! 

Table of Contents:
- Resources
- How to Play
- Starting the Game
- Winning the Game
- Git Repository
- How We Used Git
- Additional Information

Resources
Game Assets: Images and sounds are stored in the res directory.

- res/items/Star.png : The image representing a star.
- res/player/down-(1/12).png.png : The sprites for the walking down animation.
- res/player/up-(1/12).png.png : The sprites for the walking up animation.
- res/player/right-(1/12).png.png : The sprites for the walking right animation.
- res/player/left-(1/12).png.png : The sprites for the walking left animation.
- res/player/idle-(1/8).png.png : The sprites for the idle animation.
- res/tiles/BasicPath.png : The image representing a path.
- res/tiles/LavaFloor.png: The image representing maze wall.
- res/tiles/Path.png: The image representing a path.
- res/tiles/PlanetFloor.png: The image representing the background for the screen.
- res/tiles/SideBar.png: The image representing the sidebar.
- res/tiles/StartTile.png: The image representing the start tile.
- res/tiles/WinTile.png: The image representing the win tile.

Game Logic: The main part of the game is in the src directory.
Maze Structure: The Maze class supports the creation logic of the maze.

How to Play
Objective: To reach to the end of the maze by collecting the star and stepping on the win tile.

Controls:
Use WASD for character movement.
Walk on top of the star to collect it.

To Start the Game
Ensure Java is installed on your machine. Download it from here.
Clone the repository: https://github.com/abdfreitas/FragmentsOfHomeFinal

To Win the Game
Collect the star for the the win tile to allow you to win the game.


Git Repository
Link to your GitHub repository: (https://github.com/abdfreitas/FragmentsOfHomeFinal)

How We Used Git
Version Control: We have used Git as our version control tool to manage changes efficiently in this codebase.
Branching: For each new feature that was developed, we treated that as a separate branch so that the main/master branch remains stable.
Commits: In each feature added, we included commits with proper commit messages for understanding the changes.
Collaboration: Team members can work at the same time without their changes overriding each other's through pull requests.
