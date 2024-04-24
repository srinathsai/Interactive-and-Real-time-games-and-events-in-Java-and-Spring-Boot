## Goal :- </br>
This repository contains all my real time projects  that are coded by me and passed all the test cases of competitions for each project . These competitions tested my Data Structures and Algorithms , problem solving and Spring boot skills in similar to real life scenarios 
conducted as live events.

### Project 1 ( Backend Live Project of Blood Donation System) :-
This competiton involved testing the challenges of developing 8 REST APIs of the live Blood donation project.  This project was developed using Spring boot , Java and Object Oriented design involving 4 Jpa Entities , 4 Repository layers and 4 service layers. The db used was MySql for storing the donations, donors, blood bags details and are retrieved from it as well for processing. </br>
**Additionally a feature has been added as well which verfies the validity of user, eligibility of blood donation and quantity required. As a whole with proper front end this project can be utilized in daily life in blood donation camps.** </br>

### project 2 ( SnakeGame) :- 
Back in early 2000s we all used to play our beloved game in Nokia 1100 phones where snake body increases it's body size after finding any food. And then movement of Snake should be decided by us. This project on execution in IDE will take back to that sweet memory of that game. Here you can even decide the grid dimensions and should give directions using keys like u,d,l,r . This game is developed based on queue, and bfs traversal in the grid that amkes you play live snake game as of that in Nokia 1100 game. </br>


### project 3 ( Sudoku Solver) :-
Have you got struck in playing sudoku? Then don't worry no matter the hard sudoku you enter , this project will solve and gives the solved sudoku within fraction of seconds. It even checks the validity of sudoku and if you enter invalid sudoku it will clearly indicates that the given sudoku is wrong. So the solving involves a technique called backtracing which is exponential time complexity but tries all possible options. if any possibility is breaking sudoku conditions, it will backtrack whatever it has done step by step and do the same process as before like a reccursion .</br>
Note:- you need to enter sudoku details row by row.

### project 4 ( Live project of Shortening of URLS) :-
Did you find difficulty in remembering long urls or even copying the whole thing? Or better to say are you lazy to copy large urls? So this live backend project is designed to the peope who answered yes to my previous questions. This project is built using Springboot and Thymeleaf. So with the help of Thymeleaf which is one part of Spring boot used to have html pages for getting rest APIs request from user. So using thymeleaf we no need to depend on POSTMAN for sending rest APIs. There will be HTML pages generated before you once you have given specific host adress in the chrome. From this page using Spring.model.ui we will capture the request which is turned to java Object at controller layer and processing on this object is exactly as similar as project 1 where we use repository layer(JpaRepository<Entity, Entity Id> interface)  to communicate with db either saving the jpa entity or retrieving it. And finally the result after processing the object will be returned in Strings that match the .html pages names. With the help of th (Tymeleaf) tag in that respective .html file, we can show the output in html pages.</br>
So , with this knowledge this competition was designed to perform 3 tasks. which involves asking longer url from user , saving it in db with 8 length random generated short url in db and retriving it or retrieve long url if short ulr is given .

### project 5 (Simulation of Chess Game) :- 
For all the chess lovers , this project is for you. This project is developed using Object oriented and Data Structures and Algorithms concepts to simulate live chess . The user inputs are taken from the console and if it is a valid move the motion of the piece is shown on chess grid which will be printed on console. But if it is not valid move it agains ask to enter thr valid coordinates . After moving the piece now a boolean isWhite class variable is altered indicating the switch of colors alternatively. All the pieces of the chess board that appear on Console are  Capital of first letter of original piece names. And this game continues until any of the color king is in checkmate, or stalemate or insufficient pieces. User can also choose castling and this feature is coded which will check the validity of castling. </br>
Note :- Only special moves related to pawns like :- </br>
1) pawn once entered opponent last column can be transformed to user choice piece.</br>
2) pawn killing opponent pawn if opponent pawn jumped 2 steps at first and killer pawn has one empty slot diagnolly. </br>
are not implemented here.</br>
so **future scope of this project** is to include the above special moves with better GUI to make it much more universally accepted console chess game.


                                                 
