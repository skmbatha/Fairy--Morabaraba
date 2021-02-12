import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

public class SU22628363{
    //Board size
    static int X = 6;
    static int Y = 7;
    static int PowerUp=0;
    static boolean DEBUG = true;//This variable can be used to turn your debugging output on and off. Preferably use 
    
    public static void main (String[] args){
        //TODO: Read in a comamnd line argument that states whether the program will be in either terminal
        //      mode (T) or in GUI mode (G) [Hint: use args[0] and the variable below]

        //Sets whether the game is in terminal mode or GUI mode
        boolean gui = false;
        int input = 0;
        int column = 0; 
        boolean player_toggle=true;
        String dummyReader;
            
        
        
        //The 6-by-7 grid that represents the gameboard, it is initially empty
        int [][] grid = new int [X][Y];
        //Shows which player's turn it is, true for player 1 and false for player 2
        boolean player1 = true;
        //Shows the number of special tokens a player has left
        int [] p1powers = {2, 2, 2};
        int [] p2powers = {2, 2, 2};     
        
        //toggles between the gui and terminal mode
        if(args[0].compareTo("T")==0)
        gui=false;
        else
        gui=true;
        
        grid=Test("map");//importing a map for simulation
        
        if (!gui) {
            //Terminal mode

            //TODO: Display 10 line title
            StdOut.println("****************************************************************************");
	        StdOut.println("*  _______  _______  __    _  __    _  _______  _______  _______  _   ___  *"+
						 "\n* |       ||       ||  |  | ||  |  | ||       ||       ||       || | |   | *"+
						 "\n* |       ||   _   ||   |_| ||   |_| ||    ___||       ||_     _|| |_|   | *"+
						 "\n* |       ||  | |  ||       ||       ||   |___ |       |  |   |  |       | *"+
						 "\n* |      _||  |_|  ||  _    ||  _    ||    ___||      _|  |   |  |___    | *"+
						 "\n* |     |_ |       || | |   || | |   ||   |___ |     |_   |   |      |   | *"+
						 "\n* |_______||_______||_|  |__||_|  |__||_______||_______|  |___|      |___| *");
	        StdOut.println("*                                                                          *");
	        StdOut.println("****************************************************************************");
	        
            //Array for current player's number of power storage
            int [] curppowers = new int[3];
            
            Scanner reader=new Scanner(System.in);

            while (true) {
            	
                if (player1) {
                    StdOut.println("Player 1's turn (You are Red):");
                    curppowers = p1powers;
                } else {
                    StdOut.println("Player 2's turn (You are Yellow):");
                    curppowers = p2powers;
                }  
                
                while(true){
                StdOut.println("Choose your move: \n 1. Play Normal \n 2. Play Bomb ("+curppowers[0]+" left) \n 3. Play Teleportation ("+curppowers[1]+" left) \n 4. Play Colour Changer ("+curppowers[2]+" left)\n 5. Display Gameboard \n 6. Load Test File \n 0. Exit");
                
                //TODO: Read in chosen move, check that the data is numeric
                
                if(reader.hasNextInt()){
                input=reader.nextInt();
                break;}
                else
                StdOut.println("Invalid Input!");
                dummyReader=reader.next();
                }
                
                switch(input) {
                    case 0: Exit();
                            break;

                            
                            
                    case 1: //Making sure that the input is  number
                    		while(true){
                    	    StdOut.println("Choose a column to play in:");
                            if(reader.hasNextInt())
                              {	
                              column=reader.nextInt();
                              break;
                              } 
                               else
                              {
                               StdOut.println("Invalid Input!\n");
                               dummyReader=reader.next();
                              }}
                               
                            
                            //TODO: Check that value is within the given bounds, check that the data is numeric
                            if(column>(grid[0].length-1)){
                            	StdOut.println("Invalid choice,make a valid move!");
                            	player_toggle=false ;
                               break;
                            	}
                            
                            //TODO: Play normal disc in chosen column
                            grid=Play (column,grid,player1);

                            break;

                            
                            
                    case 2: if((player1 && p1powers[0]!=0) || (!player1 && p2powers[0]!=0)){
                    	
                    		//Making sure that the input is  number
                    		while(true){
                    	    StdOut.println("Choose a column to play in:");
                            if(reader.hasNextInt()){	
                              column=reader.nextInt();
                              break;
                              } else{
                               StdOut.println("Invalid Input!\n");
                               dummyReader=reader.next();
                             }}
                    		
							//TODO: Read in chosen column
							//TODO: Check that value is within the given bounds, check that the data is numeric
							//TODO: Play bomb disc in chosen column and reduce the number of bombs left
							//TODO: Check that player has bomb discs left to play, otherwise print out an error message
                           
                           if(column>(grid[0].length-1)){ //if invalid column is chosen
                                 StdOut.println("Illegal move, please input legal move.");
                                 player_toggle=false ;
                                 break;
                    	      }
                                 grid=Bomb(column,grid,player1);
                              }
                              else
                              {
                                 StdOut.println("You have no bomb power discs left.");
                                 player_toggle=false ;
                              }
                                 break;

                            
                            
                    case 3: if((player1 && p1powers[1]!=0) || (!player1 && p2powers[1]!=0)){
                    	    
                    		//Making sure that the input is  number
                    		while(true){
                    	    StdOut.println("Choose a column to play in:");
                            if(reader.hasNextInt()){	
                              column=reader.nextInt();
                              break;
                              } else{
                               StdOut.println("Invalid Input!\n");
                               dummyReader=reader.next();
                             }}
                             
							//TODO: Read in chosen column
							//TODO: Check that value is within the given bounds, check that the data is numeric
							//TODO: Play teleport disc in chosen column and reduce the number of teleporters left
							//TODO: Check that player has teleport discs left to play, otherwise print out an error message
                    
                    		if(column>(grid[0].length-1)){ //if invalid column is chosen
                    		StdOut.println("Illegal move, please input legal move.");
                    		player_toggle=false ;
                      		break;
             	      		}
                    		//only open function if player still have bombs
                    		grid=Teleport(column,grid,player1);
                    		}
                    		else
                    		{
                    		StdOut.println("You have no teleporter power discs left.");
                    		player_toggle=false;
                    		}
                            break;
                            
                            
             
                    case 4: if((player1 && p1powers[2]!=0) || (!player1 && p2powers[2]!=0)){
                    	    
                    		//Making sure that the input is  number
                    		while(true){
                    	    StdOut.println("Choose a column to play in:");
                            if(reader.hasNextInt()){	
                              column=reader.nextInt();
                              break;
                              } else{
                               StdOut.println("Invalid Input!\n");
                               dummyReader=reader.next();
                             }}
                    		
							//TODO: Read in chosen column
							//TODO: Check that value is within the given bounds, check that the data is numeric
							//TODO: Play Color Change disc in chosen column and reduce the number of Color changers left
							//TODO: Check that player has Color Change discs left to play, otherwise print out an error message 
                    
            				if(column>(grid[0].length-1))
            				{ //if invalid column is chosen
            				StdOut.println("Illegal move, please input legal move.");
            				player_toggle=false ;
            				break;
            				}
            				//only go in if player still have bombs
            				grid=Colour_Changer(column,grid,player1);		
	            			}
                    		else
                    		{
            				StdOut.println("You have no colour changer power discs left.");
        					player_toggle=false ;
        					}
                            break;
                            
                            

					//This part will be used during testing, please DO NOT change it. This will result in loss of marks
                    case 5: Display(grid);
                    		//Displays the current gameboard again, doesn't count as a move, so the player stays the same
                            player1 = !player1;
                            break;

					//This part will be used during testing, please DO NOT change it. This will result in loss of marks
                    case 6: grid = Test(StdIn.readString());
                    		//Reads in a test file and loads the gameboard from it.
                            player1 = !player1;
                            break;
                    //This part will be used during testing, please DO NOT change it. This will result in loss of marks
                    case 7: Save(StdIn.readString(), grid);
				            player1 = !player1;
				            break;

					default:player_toggle=false ;//TODO: Invalid choice was made, print out an error message but do not switch player turns
						    StdOut.println("Please choose a valid option");
                            break;
                }      
                //=========================================================================================================================
                
                //Display the grid
                Display(grid);  
                  
                
				//TODO: Checks whether a winning condition was met
                int win = Check_Win(grid);
                if(win==1)
                	StdOut.print("Player 1  wins!");
                	if(win==2)
                		StdOut.print("Player 2 wins!");
                		if(win==3)
                			StdOut.print("Draw!");
                		
                if(win>0 && win <4)	{	
                StdOut.print("\nDo you want to play again? \n 1. Yes \n 2. No");
                input=StdIn.readInt();
                if(input==0)
                	Exit();
                else
                {   //If player selected replay,reset the grid and the powerups
                    win=0;//reset flag
                	for(int i=0;i<3;i++){
                	p1powers[i]=2;//reset the power ups
                	p2powers[i]=2;//reset the power ups
                	}
                	
                	for(int i=0;i<grid.length;i++) //reset the array/grid
                		for(int j=0;j<grid[0].length;j++)
                			grid[i][j]=42;
                }}
                
                
                
                //Decrement power ups if used.Var PowerUp set int the power up functions)
                if(PowerUp!=0)
                	if(player1){
                	 if(p1powers[PowerUp-1]!=0)
                	   p1powers[PowerUp-1]--;}
                	else{
                	 if(p2powers[PowerUp-1]!=0)
                	   p2powers[PowerUp-1]--;}
                PowerUp=0;
                
                
                //TODO: Switch the players turns
                if(player_toggle)
                player1=!player1;
                player_toggle=true;
                
            }
            } else {
          
        	  //Graphics mode
            	
        	  StdDraw.enableDoubleBuffering();//enable double buffering(images won't show until StdDraw.show();) 
        	  StdDraw.setCanvasSize(800,440);//set canvas size
        	  StdDraw.setXscale(0,800);//set x scale to 800
        	  StdDraw.setYscale(0,440);//set y scale to 440
        	  player_toggle=false;//reset the variable
        	  
        	  int val=0;
        	  while(true)//Game loop
        	  {
        		  //draw all graphics
        		  StdDraw.setPenColor(StdDraw.BLACK);
        		  StdDraw.filledRectangle(400,220, 400, 220);
        		  StdDraw.setPenColor(StdDraw.BLUE);
        		  StdDraw.filledRectangle(600, 220, 200, 220);
        		  StdDraw.setPenColor(StdDraw.BLACK);
        		  StdDraw.setPenRadius(0.005);
        		  StdDraw.line(400, 400, 800, 400);
        		  StdDraw.setPenColor(StdDraw.BLUE);
        		  StdDraw.line(0, 220, 400, 220);
        		  StdDraw.setPenColor(StdDraw.YELLOW);
        		  
        		  //Player Information
        		  StdDraw.text(200, 370,"PLAYER 1");
        		  StdDraw.setPenRadius(0.002);
        		  StdDraw.line(20, 360, 380, 360);
        		  StdDraw.setPenRadius(0.005);
        		  StdDraw.text(200,200,"PLAYER 2");
        		  StdDraw.setPenRadius(0.002);
        		  StdDraw.line(20, 190, 380,190);
        		  
        		  //Player 1 powerups
        		  StdDraw.text(70, 310,"BOMB        :"+p1powers[0]);
        		  StdDraw.text(87, 280,"TELEPORTER  :"+p1powers[1]);	
        		  StdDraw.text(93, 250,"COLR CHANGER:"+p1powers[2]);

        		  //Player 2's powerups
        		  StdDraw.text(70, 140,"BOMB        :"+p2powers[0]);
        		  StdDraw.text(87, 110,"TELEPORTER  :"+p2powers[1]);	
        		  StdDraw.text(93, 80,"COLR CHANGER:"+p2powers[2]);
        		  
        		  //player status (which player's turn)
        		  StdDraw.setPenColor(StdDraw.RED);
        		  if(player1)
        		  StdDraw.filledCircle(25, 375, 5);
        		  else
        		  StdDraw.filledCircle(25, 205, 5);  
        		  
        		  //display the grid  
        		  DisplayGUI (grid);
        		  
        		  StdDraw.setPenColor(StdDraw.RED);
     			  StdDraw.text(200, 420,"Choose your move!");
     			  StdDraw.setPenColor(StdDraw.RED);
    			  StdDraw.text(200, 15,"NB:Power disk:Press (B,T,C) then mouse click!");
    			  
     			  
        		  StdDraw.show();//show all buffered drawings
        		  StdDraw.pause(2);
        		  
        		  //choose power
        		  input=0;
        		  while((StdDraw.isMousePressed()==false) && (StdDraw.hasNextKeyTyped()==false));//wait while no mouse/keyboard key is pressed 
        		  if(StdDraw.hasNextKeyTyped())//if key was pressed save value into input
        			  input=StdDraw.nextKeyTyped();
        		  
        		  
        		  if(input=='Q' || input=='q')//if input is Q,exit the program
        			  Exit();
        		  
        		  
        		  //Normal play mode
        		  if(StdDraw.isMousePressed() && input==0)
        		  {
        			 while(!StdDraw.isMousePressed());
        			 StdDraw.setPenColor(StdDraw.BLACK);
        			 StdDraw.filledRectangle(200, 420, 200,20);
        			 StdDraw.setPenColor(StdDraw.RED);
        			 StdDraw.text(200, 420,"Enter the column number --->");
        			 StdDraw.show();
        			 
        			 while(true){
        			 while(!StdDraw.hasNextKeyTyped());
        			 
        			 val=Character.getNumericValue(StdDraw.nextKeyTyped());
        			 
        			 if(val>-1 && val<(grid[0].length)){
        			 grid=Play(val,grid,player1);
        			 player_toggle=true;
        			 break;
        			 }else
        			 {
            			 //Show error message
            			 StdDraw.setPenColor(StdDraw.BLACK);
            			 StdDraw.filledRectangle(200, 420, 200,20);
            			 StdDraw.setPenColor(StdDraw.RED);
            			 StdDraw.text(200, 420,"Enter a Valid column number --->");
            			 StdDraw.show(); 
        			 }        			
        			 }
        		   }
        		  
        		  
        		  
        		  //BOMB Mode
        		  else if(input=='B'|| input=='b')
        		  {
        			 if((player1 && p1powers[0]!=0) || (!player1 && p2powers[0]!=0)){
        			 while(!StdDraw.isMousePressed());
        			 StdDraw.setPenColor(StdDraw.BLACK);
        			 StdDraw.filledRectangle(200, 420, 200,20);
        			 StdDraw.setPenColor(StdDraw.RED);
        			 StdDraw.text(200, 420,"Bomb:Enter the column number --->");
        			 StdDraw.show();
        			 while(StdDraw.isMousePressed());
        			 StdDraw.pause(1000);
        			 
        			 while(true){
        			 while(!StdDraw.hasNextKeyTyped());
        			 
        			 val=Character.getNumericValue(StdDraw.nextKeyTyped());
        			 
        			 if(val>-1 && val<(grid[0].length)){
        			 grid=Bomb(val,grid,player1);
        			 PowerUp=1;
        			 player_toggle=true;
        			 break;
        			 }else
        			 {
            			 //Show error message
            			 StdDraw.setPenColor(StdDraw.BLACK);
            			 StdDraw.filledRectangle(200, 420, 200,20);
            			 StdDraw.setPenColor(StdDraw.RED);
            			 StdDraw.text(200, 420,"Bomb:Enter a Valid column number --->");
            			 StdDraw.show();        				 
        			 }      			
        			 }}else
        			 {
            			 StdDraw.setPenColor(StdDraw.BLACK);
            			 StdDraw.filledRectangle(200, 420, 200,20);
            			 StdDraw.setPenColor(StdDraw.RED);
            			 StdDraw.text(200, 420,"You have no Bomb power discs left!");
            			 StdDraw.show(); 
            			 StdDraw.pause(2000);
            			 while(StdDraw.isMousePressed());
            			 player_toggle=false;
        			 }
        		   }
        		  
        		  
        		  //TELEPOTER Mode
        		  else if(input=='T'|| input=='t')
        		  {
        			  if((player1 && p1powers[1]!=0) || (!player1 && p2powers[1]!=0)){
        			 while(!StdDraw.isMousePressed());
        			 StdDraw.setPenColor(StdDraw.BLACK);
        			 StdDraw.filledRectangle(200, 420, 200,20);
        			 StdDraw.setPenColor(StdDraw.RED);
        			 StdDraw.text(200, 420,"Teleporter:Enter the column number --->");
        			 StdDraw.show();
        			 while(StdDraw.isMousePressed());
        			 StdDraw.pause(1000);
        			 
        			 while(true){
        			 while(!StdDraw.hasNextKeyTyped());
        			 
        			 val=Character.getNumericValue(StdDraw.nextKeyTyped());
        			 
        			 if(val>-1 && val<(grid[0].length)){
        			 grid=Teleport(val,grid,player1);
        			 PowerUp=2;
        			 player_toggle=true;
        			 break;
        			 }else
        			 {
            			 //Show error message
            			 StdDraw.setPenColor(StdDraw.BLACK);
            			 StdDraw.filledRectangle(200, 420, 200,20);
            			 StdDraw.setPenColor(StdDraw.RED);
            			 StdDraw.text(200, 420,"Teleporter:Enter a Valid column number --->");
            			 StdDraw.show();        				 
        			 }
        			 }}else
        			 {
            			 StdDraw.setPenColor(StdDraw.BLACK);
            			 StdDraw.filledRectangle(200, 420, 200,20);
            			 StdDraw.setPenColor(StdDraw.RED);
            			 StdDraw.text(200, 420,"You have no Teleporter power discs left!");
            			 StdDraw.show(); 
            			 StdDraw.pause(2000);
            			 while(StdDraw.isMousePressed() && StdDraw.hasNextKeyTyped());
            			 player_toggle=false;
        			 }
        		   }
        		  
        		  
        		  
        		  //Color Changer Mode
        		  else if(input=='C'|| input=='c')
        		  {
        			 if((player1 && p1powers[2]!=0) || (!player1 && p2powers[2]!=0)){
        			 while(!StdDraw.isMousePressed());
        			 StdDraw.setPenColor(StdDraw.BLACK);
        			 StdDraw.filledRectangle(200, 420, 200,20);
        			 StdDraw.setPenColor(StdDraw.RED);
        			 StdDraw.text(200, 420,"Clr Changer:Enter the column number --->");
        			 StdDraw.show();
        			 while(StdDraw.isMousePressed());
        			 StdDraw.pause(1000);
        			 
        			 while(true){
        			 while(!StdDraw.hasNextKeyTyped());
        			 
        			 val=Character.getNumericValue(StdDraw.nextKeyTyped());
        			 
        			 if(val>-1 && val<(grid[0].length)){
        			 grid=Colour_Changer(val,grid,player1);
        			 PowerUp=3;
        			 player_toggle=true;
        			 break;
        			 }else
        			 {
            			 //Show error message
            			 StdDraw.setPenColor(StdDraw.BLACK);
            			 StdDraw.filledRectangle(200, 420, 200,20);
            			 StdDraw.setPenColor(StdDraw.RED);
            			 StdDraw.text(200, 420,"Clr Changer:Enter a Valid column number --->");
            			 StdDraw.show();        				 
        			 }
        			
        			 }}else{
            			 StdDraw.setPenColor(StdDraw.BLACK);
            			 StdDraw.filledRectangle(200, 420, 200,20);
            			 StdDraw.setPenColor(StdDraw.RED);
            			 StdDraw.text(200, 420,"You have no Colour Changer power discs left!");
            			 StdDraw.show(); 
            			 StdDraw.pause(2000);
            			 while(StdDraw.isMousePressed());
            			 player_toggle=false;
        			 }
        		   }
        		  
                  
        		  
                  //Decrement power ups (PoweUps variable handles in functions)
                  if(PowerUp!=0)
                  	if(player1){
                  	 if(p1powers[PowerUp-1]!=0)
                  	   p1powers[PowerUp-1]--;}
                  	else{
                  	 if(p2powers[PowerUp-1]!=0)
                  	   p2powers[PowerUp-1]--;}
                  PowerUp=0;
                  
                  
  				//TODO: Checks whether a winning condition was met
                 int win = Check_Win(grid);
                 if(win>0){
                  DisplayGUI (grid);
                  if(win==1){
         			 StdDraw.setPenColor(StdDraw.BLACK);
         			 StdDraw.filledRectangle(200, 420, 200,20);
         			 StdDraw.setPenColor(StdDraw.RED);
         			 StdDraw.text(200, 420,"Player 1 wins!");
         			StdDraw.show();
         			 StdDraw.pause(2000);
                  }
                  	if(win==2){
            			 StdDraw.setPenColor(StdDraw.BLACK);
            			 StdDraw.filledRectangle(200, 420, 200,20);
            			 StdDraw.setPenColor(StdDraw.RED);
            			 StdDraw.text(200, 420,"Player 2 wins!");
            			 StdDraw.show();
            			 StdDraw.pause(2000);}
                  		if(win==3){
                			 StdDraw.setPenColor(StdDraw.BLACK);
                			 StdDraw.filledRectangle(200, 420, 200,20);
                			 StdDraw.setPenColor(StdDraw.RED);
                			 StdDraw.text(200, 420,"Draw!");
                			 StdDraw.show();
                			 StdDraw.pause(2000);
                			 }
                  		
                  				if(win>0 && win <4)	{	
                  					StdDraw.setPenColor(StdDraw.BLACK);
                  					StdDraw.filledRectangle(200, 420, 200,20);
                  					StdDraw.setPenColor(StdDraw.RED);
                  					StdDraw.text(200, 420,"Do you want to play again? Y/N");
                  					StdDraw.show();
                  					}
          		  while(!StdDraw.hasNextKeyTyped());
          		  input=StdDraw.nextKeyTyped();	 
          			 
                  if(input=='N'|| input=='n')
                  	Exit();
                  else
                  {
                      win=0;//reset flag
                  	for(int i=0;i<3;i++){
                  	p1powers[i]=2;//reset the power ups
                  	p2powers[i]=2;//reset the power ups
                  	}
                  	
                  	for(int i=0;i<grid.length;i++) //reset the array/grid
                  		for(int j=0;j<grid[0].length;j++)
                  			grid[i][j]=42;
                  }}
                 
                  
                  
                  //Toggle player
                  if(player_toggle)
        		  player1=!player1;
                  player_toggle=false;
        	  }
        	  

        }
        
    }
    //GUI graphics
    public static void DisplayGUI (int [][] grid) {
    	   
    	
    	int x=grid[0].length;
    	int y=grid.length;
    	
    	int xScale=400/(x+1),xp;
    	int yScale=400/(y+1),yp;
    	double radius=xScale/2.5;
    	

    	
    	StdDraw.setPenColor(StdDraw.WHITE);
    	for(int g=1;g<(x+1);g++)
    	StdDraw.text((400+g*xScale), 420,""+(g-1));
    	
    	//draw cirles
    	for(int u=1;u<(y+1);u++)
    	{
    		for(int p=1;p<(x+1);p++)
    		{
    			xp=400+(xScale*p);
    			yp=(400-(yScale*u));
    			
    			if(grid[u-1][p-1]==42 || grid[u-1][p-1]=='*')
    			{
    		    	Color c=new Color(112,112,112);//grey
    		    	StdDraw.setPenColor(c);
    		    	StdDraw.filledCircle(xp,yp, radius);
    			}
    			
    			if(grid[u-1][p-1]==82 || grid[u-1][p-1]=='R')
    			{
    		    	StdDraw.setPenColor(StdDraw.RED);
    		    	StdDraw.filledCircle(xp,yp, radius);;
    			}
    			
    			if(grid[u-1][p-1]==89 || grid[u-1][p-1]=='Y')
    			{
    		    	StdDraw.setPenColor(StdDraw.YELLOW);
    		    	StdDraw.filledCircle(xp,yp, radius);
    			}
    		}
    	}
    }

    //Command line functions
    /**
     * Displays the grid, empty spots as *, player 1 as R and player 2 as Y. Shows column and row numbers at the top.
     * @param grid  The current board state
     */
    public static void Display (int [][] grid) {
        //TODO: Display the given board state with empty spots as *, player 1 as R and player 2 as Y. Shows column and row numbers at the top.
    	
       int y=grid.length;
       int x=grid[0].length;
       
      StdOut.println();//space around print 
      StdOut.print("  ");
      for(int i=0;i<x;i++)
    	  StdOut.print(i+" ");
          StdOut.println();
      
      for(int i=0;i<y;i++)
       {
    	  StdOut.print(i+" ");
    	   for(int j=0;j<x;j++)
    	   {
    		   
    		if(grid[i][j]==42 || grid[i][j]=='*')
    	           StdOut.print("* ");
    		
    		if(grid[i][j]==82 || grid[i][j]=='R')
    	    	   StdOut.print("R ");
    		
    		if(grid[i][j]==89 || grid[i][j]=='Y')
    	    	   StdOut.print("Y ");
    		
           }
    	   StdOut.println();
  
       }
      StdOut.println();//space around print
    }
    /**
     * Exits the current game state
     */
    public static void Exit() {
        //TODO: Exit the game
    	System.exit(0);///Close the program
    }
    /**
     * Plays a normal disc in the specified position (i) in the colour of the player given. Returns the modified grid or
     * prints out an error message if the column is full.
     * @param i         Column that the disc is going to be played in
     * @param grid      The current board state
     * @param player1   The current player
     * @return grid     The modified board state
     */
    public static int [][] Play (int i, int [][] grid, boolean player1) {
        //TODO: Play a normal disc of the given colour, if the column is full, print out the message: "Column is full."
    	int y=grid.length;
    	boolean filled=false;
    	
    	for(int j=0; j<y ;j++)
    	{
    		if(grid[(y-1)-j][i]==42)
    		{
    			if(player1)
    			grid[(y-1)-j][i]=82;
    			else
    			grid[(y-1)-j][i]=89;
    			
    			filled=true;
    			break;
    		}
    	}
    	
    	if(!filled)//check if the column has been filled
    	StdOut.println("Column is full.");
    	
        return grid;
    }
    /**
     *Checks whether a player has 4 tokens in a row or if the game is a draw.
     * @param grid The current board state in a 2D array of integers
     */
    public static int Check_Win (int [][] grid) {
        int winner = 0;//provided
        
        int counter=0;
        boolean p1Win=false;
        boolean p2Win=false;
        int Test=0;
        
        int x=grid[0].length;
        int y=grid.length;
        
        //TODO: Check for all the possible win conditions as well as for a possible draw.
        
        //vertical win
        for(int j=0;j<y;j++)
        {
        	for(int i=0;i<x;i++)
        	{
 
        		if(grid[j][i]==82 || grid[j][i]==89)
        		{
        			Test=grid[j][i];
        			
        			///VERTICAL WIN TEST
        			for(int v=j;v<(j+4);v++)
        			{
        				if(v<y)///if within grid size
        				{
        					if(grid[v][i]==Test){
        						counter++;
        					}
        				}
        				
        				if((v-j)==3)
        				{
        					if(counter==4)
        					{
        						if(Test==82)
        						p1Win=true;
        						else
        						p2Win=true;
        					}	
        					counter=0;
        				}
        			}
        			
        			
        			//HORIZONTAL TEST
        			for(int v=i;v<(i+4);v++)
        			{
        				if(v<x)///if within grid size
        				{
        					if(grid[j][v]==Test)
        						counter++;
        				}
        				
        				if((v-i)==3)
        				{
        					if(counter==4)
        					{
        						if(Test==82)
        						p1Win=true;
        						else
        						p2Win=true;
        					}	
        					counter=0;
        				}
        			}
        			
        			//+DIAGONAL TEST
        			int w=j;
        			for(int v=i;v<(i+4);v++)
        			{
        				if(v<x && w<y )///if within grid size
        				{
        					if(grid[w][v]==Test)
        						counter++;
        				}
        				
        				if((v-i)==3)
        				{
        					if(counter==4)
        					{
        						if(Test==82)
        						p1Win=true;
        						else
        						p2Win=true;
        					}	
        					counter=0;
        				}
        				w++;
        			}
        			
        			//-DIAGONAL TEST
        			w=j;
        			for(int v=i;v<(i+4);v++)
        			{
        				if(v<x && (w>-1) )///if within grid size
        				{
        					if(grid[w][v]==Test)
        						counter++;
        				}
        				
        				if((v-i)==3)
        				{
        					if(counter==4)
        					{
        						if(Test==82)
        						p1Win=true;
        						else
        						p2Win=true;
        					}	
        					counter=0;
        				}
        				w--;
        			}
        		}	
        	} 	
        }
        
        ///check for win conditions
        if(p1Win)
        	winner=1;
        
        if(p2Win)
        	winner=2;
        
        if(p1Win && p2Win)
        	winner=3;
  
        return winner;
    }
    /**
     * Plays a bomb disc that blows up the surrounding tokens and drops down tokens above it. Should not affect the
     * board state if there's no space in the column. In that case, print the error message: "Column is full."
     * @param i         Column that the disc is going to be played in
     * @param grid      The current board state
     * @param player1   The current player
     * @return grid     The modified board state
     */
    public static int [][] Bomb (int i, int [][] grid, boolean player1) {
        //TODO: Play a bomb in the given column and make an explosion take place. Discs should drop down afterwards. Should not affect the
        //      board state if there's no space in the column. In that case, print the error message: "Column is full."
        //      Leaves behind a normal disc of the player's colour
    	
    	int y=grid.length;
    	int x=grid[0].length;
    	
    	boolean filled=false;
    	
    	for(int j=0; j<y ;j++)
    	{
    		//perform the bomb  power up
    		if(grid[(y-1)-j][i]==42)
    		{	
    			///kill all the surrounding disks
    			if(i!=0){//left
    			grid[(y-1)-j][i-1]=42;}
    			
    			if(i!=(x-1)){//most right
        		grid[(y-1)-j][i+1]=42;}
    			
    			if(((y-1)-j)!=(y-1)){//most right
            	grid[(y-1)-j+1][i]=42;}
    			
    			if(((y-1)-j)!=0){//most right
            	grid[(y-1)-j-1][i]=42;}
    			
                //=======================================
    			
    			if(i!=0 && ((y-1)-j)!=0){//left top
    			grid[(y-1)-j-1][i-1]=42;}
    			
    			if(i!=(x-1) && ((y-1)-j)!=0){//most right top
        		grid[(y-1)-j-1][i+1]=42;}
    			
    			if(((y-1)-j)!=(y-1) && i!=0){//bottom left
            	grid[(y-1)-j+1][i-1]=42;}
    			
    			if(((y-1)-j)!=(y-1) && i!=(x-1)){//bottom right
                	grid[(y-1)-j+1][i+1]=42;}
    			filled=true;
    			break;
    		}
    	}
    	
    	//place a new disk
    	for(int j=0; j<y ;j++)
    	{
    		if(grid[(y-1)-j][i]==42)
    		{	
    			if(player1)
    				grid[(y-1)-j][i]=82;
    				else
    				grid[(y-1)-j][i]=89;
    			PowerUp=1;
    			break;
    		}
    		
    	}
    	
    	//===================================================
    	//down fall all broken enemies function
    	int cntr=0;
    	boolean found=false;
    	
    	here:
        if(i!=0){
    	for(int j=0; j<y ;j++)
    	{
    	  if(grid[(y-1)-j][i-1]==42)
    		{   
    		found=true;
    		}
    	
    	if(found)
    	{
    	  cntr++;//find length of space between bottom and top
    	  if(grid[(y-1)-j][i-1]==82 || grid[(y-1)-j][i-1]==89)
    	  {
    		  for(int f=((y-1)-j);f>(-1);f--){
    			  grid[f+cntr-1][i-1]=grid[f][i-1];  
    			  grid[f][i-1]=42;
    		  }
    		  break here;
    	  }
    	}
    	}	}
    	StdOut.print("\n"); 
    	
    	//function.2
    	cntr=0;
    	found=false;
    	here2:
        if(i!=x){
    	for(int j=0; j<y ;j++)
    	{
    	  if(grid[5-j][i+1]==42)
    		{   
    		found=true;
    		}
    	
    	if(found)
    	{
    	  cntr++;//find length of space between bottom and top
    	  if(grid[(y-1)-j][i+1]==82 || grid[(y-1)-j][i+1]==89)
    	  {
    		  for(int f=((y-1)-j);f>(-1);f--){
    			  grid[f+cntr-1][i+1]=grid[f][i+1]; 
    		      grid[f][i+1]=42;
    		      }
    		  break here2;
    	  }
    	}
    	}}
    	//===================================================

    	
    	if(!filled){//check if the column has been filled
    	StdOut.println("Column is full.");
		PowerUp=1;
    	}
    	
        return grid;
    }

    /**
     * Plays a teleporter disc that moves the targeted disc 3 columns to the right. If this is outside of the board
     * boundaries, it should wrap back around to the left side. If the column where the targeted disc lands is full,
     * destroy that disc. If the column where the teleporter disc falls is full, play as normal, with the teleporter
     * disc replacing the top disc.
     * @param i         Column that the disc is going to be played in
     * @param grid      The current board state
     * @param player1   The current player
     * @return grid     The modified board state
     */
    public static int [][] Teleport (int i, int [][] grid, boolean player1) {
        //TODO: Play a teleporter disc that moves the targeted disc 3 columns to the right. If this is outside of the board
        //      boundaries, it should wrap back around to the left side. If the column where the targeted disc lands is full,
        //      destroy that disc. If the column where the teleporter disc falls is full, play as normal, with the teleporter
        //      disc replacing the top disc.
        //      No error message is required.
        //      If the colour change disc lands on the bottom row, it must leave a disc of the current player’s colour.
    	int y=grid.length;
    	int x=grid[0].length;
    	PowerUp=2;
    	
    	//If the column is empty and you landed at the empty bottom
    	if(grid[y-1][i]==42){//whole column empty
    	for(int j=(y-1); j>(-1) ;j--)
    	{
    		if(grid[j][i]==42)
    		{
    			if(player1)
    			grid[j][i]=82;
    			else
    			grid[j][i]=89;
    			
    			break;
    		}
    	}}
    	//If the teleporter hits a disk  
    	else if(grid[y-1][i]!=42)
    	{
    		int cntr=0;
    		
        	for(int j=(y-1); j>(-1) ;j--)
        	{
        		if(grid[j][i]==42)
        		{
        			
        			if(i+3>(x-1))
        			{
        				for(int h=0;h<y;h++)
        				{
        					cntr++;
        					if(grid[h][i-4]==42)
        					break;
        				}
        				if(cntr!=y)//if column not full*/
        				grid[j+1][i-4]=grid[j+1][i];
        				else
        				StdOut.println("Column is full.");
        				
        				}else{
        					
        				for(int h=0;h<y;h++)
            			{
            				cntr++;
            				if(grid[h][i+3]==42)
            				break;
            			}
            			if(cntr!=y)//if column not full	*/
        				grid[j+1][i+3]=grid[j+1][i];
            			else
            		    StdOut.println("Column is full.");}

        			
        			if(player1)
            			grid[j+1][i]=82;
            			else
            			grid[j+1][i]=89;
        			
        			break;
        		}
        	}   		
    	}
    	//if teleporter hits the top of the column and its full
    	else if(grid[0][i]!=42)
    	{
    		int cntr=0;
			
			if(i+3>(x-1))
			{
				for(int h=0;h<y;h++)
				{
					cntr++;
					if(grid[h][i-4]==42)
					break;
				}
				if(cntr!=y)//if column not full
				grid[0][i-4]=grid[0][i];
				else
    			StdOut.println("Column is full.");}
			else
			{
				for(int h=0;h<y;h++)
    			{
    				cntr++;
    				if(grid[h][i+3]==42)
    				break;
    			}
    			if(cntr!=y)//if column not full	
				grid[0][i+3]=grid[0][i];
    			else
    			StdOut.println("Column is full.");}	
			
			if(player1)
    			grid[0][i]=82;
    			else
    			grid[0][i]=89;

    	}
    	
    	
    	
    	//stack the players nicely(FROM BOTTOM TO TOP)
    	//===================================================
    	//down fall all broken enemies function.1
    	int cntr=0;
    	boolean found=false;
    	
    	for(i=0;i<x;i++){
    		
    	here:
    	for(int j=(y-1); j>(-1) ;j--)
    	{
    	  if(grid[j][i]==42)
    		{   
    		found=true;
    		}
    	
    	if(found)
    	{
    	  cntr++;//find length of space between bottom and top
    	  if(grid[j][i]==82 || grid[j][i]==89)
    	  {
    		  for(int f=(j);f>(-1);f--){
    			  grid[f+cntr-1][i]=grid[f][i]; 
    			  grid[f][i]=42;
    		  }
    		  break here;
    	  }
    	}
    	
    	}
    	
    	found=false;
    	cntr=0;
    	}
    	
    	
        return grid;
    }
 
    /**
     * Plays the colour changer disc that changes the affected disc's colour to the opposite colour
     * @param i         Column that the disc is going to be played in
     * @param grid      The current board state
     * @param player1   The current player
     * @return grid     The modified board state
     */
    public static int [][] Colour_Changer (int i, int [][] grid, boolean player) {
        //TODO: Colour Change: If the colour change disc lands on top of another disc, it changes the colour of that
        //      disc to the opposite colour. The power disc does not remain.
        //      If the colour change disc lands on the bottom row, it must leave a disc of the current player’s colour.
    	int y=grid.length;
    	PowerUp=3;
    	
    	//if there is no disk
    	if(grid[y-1][i]==42)
    		for(int j=0; j<y ;j++)
    		{
    			if(grid[(y-1)-j][i]==42)
    			{
    				if(player)
    				grid[(y-1)-j][i]=82;
    				else
    				grid[(y-1)-j][i]=89;
    			
    				break;
    			}
    	}
    	else if(grid[0][i]==42 && grid[(y-1)][i]!=42 )//replace and change color
    	{
        	for(int j=0; j<y ;j++)
        	{
        		if(grid[(y-1)-j][i]==42)
        		{
        			if(grid[(y-1)-j+1][i]==89)
        			grid[(y-1)-j+1][i]=82;
        			else
        			grid[(y-1)-j+1][i]=89;

        			break;
        		}
        	}   	
    	}else//if column is full
    	{
    			if(grid[0][i]==89)
    			grid[0][i]=82;
    			else
    			grid[0][i]=89;
    	}
	
        return grid;
    }

    /**
     * Reads in a board from a text file.
     * @param name  The name of the given file
     * @return
     */
    //Reads in a game state from a text file, assumes the file is a txt file
    public static int [][] Test(String name) {
        int [][] grid = new int[6][7];
        try{
            File file = new File(name+".txt");
            Scanner sc = new Scanner(file);

            for (int i = 0; i < X; i++) {
                for (int j = 0; j < Y; j++) {
                    grid[i][j] = sc.nextInt();
                    
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return grid;
    }
    /**
     * Saves the current game board to a text file.
     * @param name  The name of the given file
     * @param grid  The current game board
     * @return
     */
    // Used for testing
    public static int [][] Save(String name, int [][] grid) {
    	try{
    		FileWriter fileWriter = new FileWriter(name+".txt");
	    	for (int i = 0; i < X; i++) {
	    		for (int j = 0; j < Y; j++) {
		    		fileWriter.write(Integer.toString(grid[i][j]) + " ");
		    	}
		    	fileWriter.write("\n");
		    }
		    fileWriter.close();
	    } catch (Exception ex) {
            ex.printStackTrace();
        }
    	return grid;
    }
    /**
     * Debugging tool, preferably use this since we can then turn off your debugging output if you forgot to remove it.
     * Only prints out if the DEBUG variable is set to true.
     * @param line  The String you would like to print out.
     */
    public static void Debug(String line) {
        if (DEBUG)
            System.out.println(line);
    }
}

