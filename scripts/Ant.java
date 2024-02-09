import java.io.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Ant {
	
	public static String[][] grid= new String[101][82];
	public static String ori = "";
	public static String[] turn = new String[]{"left", "up", "right", "down"};
	public static int x = 0;
	public static int y = 0;
	public static int total_on_cells = 0;
	private static PrintWriter printWriter;
	
	// --------------
	
	public static void Init_grid(int n)  
	{  // Initialization of the grid
	switch(n)
	{
	case 1: // All white
		for(int i = 0; i < grid.length; i++)
		{
			Arrays.fill(grid[i], "O");
			total_on_cells = grid.length * grid[0].length;
		}
		break;
	case 2: // All black
		for(int i = 0; i < grid.length; i++)
		{
			Arrays.fill(grid[i], "#");
		}
		break;
	case 3: // Checker board
		for(int i = 0; i < grid.length; i++)
		{
			for(int j = 0; j < grid[0].length; j++)
			{
				if(j%2 == 0 && i%2 == 0)
				{
					grid[i][j] = "#";
				}
				else if(j%2 == 1 && i%2 == 0)
				{
					grid[i][j] = "O";
					total_on_cells ++;
				}
				else if(j%2 == 0 && i%2 == 1)
				{
					grid[i][j] = "O";
					total_on_cells = total_on_cells + 1;
				}
				else
				{
					grid[i][j] = "#";
				}
			}
		}
		break;
	case 4: // Horizontal stripes
		for(int i = 0; i < grid.length; i++)
		{
			for(int j = 0; j < grid[0].length; j++)
			{
				if(i%2 == 0)
				{
					grid[i][j] = "#";
				}
				else
				{
					grid[i][j] = "O";
					total_on_cells = total_on_cells + 1;
				}
			}
		}
		break;
	case 5: // Random
		Random rn = new Random();
		for(int i = 0; i < grid.length; i++)
		{
			for(int j = 0; j < grid[0].length; j++)
			{
				if(rn.nextInt(2) == 0) { grid[i][j] = "O"; }
				else { grid[i][j] = "#";}
			}
		}
		break;
	default:
		break;
	} 
	}  
	// --------------
	
	public static void Ant_Move()
	{ // Updating of the Ant's movement and the gird
		for(int i = 0; i < turn.length; i++)
		{
			if(ori.equals(turn[i]))
			{
				if(grid[x][y].equals("O")) // If the cell is white, turn 90 degree right and update
				{
					if(i == (turn.length - 1))
					{ 
						ori = turn[0]; 
					}
					else 
					{ 
						ori = turn[i+1]; 
					}
					grid[x][y] = "#";
					total_on_cells = total_on_cells - 1;
				}
				else // If the cell is black, turn 90 degree left and update
				{
					if(i == 0)
					{ 
						ori = turn[3]; 
					}
					else 
					{ 
						ori = turn[i-1]; 
					}
					grid[x][y] = "O";
					total_on_cells = total_on_cells + 1;
				}
				 
				switch(ori) // Move based on the new direction (w.r.t torus topology at the boundaries)
				{
				case "left":
					if(y == 0){y = grid[0].length;}
					y = y - 1;
					break;
				case "right":
					if(y == (grid[0].length-1)){y = -1;}
					y = y + 1;
					break;
				case "up":
					if(x == 0){x = grid.length;}
					x = x - 1;
					break;
				case "down":
					if(x == (grid.length-1)){x = -1;}
					x = x + 1;
					break;
				default:
					break;
				}
				break;
			}			
		}
	}
	// --------------
	
	public static void main(String[] args) throws IOException {
		
		System.out.println("Enter the initial setting of the grid:");
		System.out.println("1. All white  2. All black  3. Checker board  4. Horizontal stripes  5. Random setting ");
		Scanner s = new Scanner(System.in);
		// Create grid
		Init_grid(s.nextInt());
		// System.out.printf("%d %d %n", grid.length, grid[0].length);
		System.out.println("2. Choose starting position (row x, column y) and orientation for the ant:");
		x = s.nextInt();
		y = s.nextInt();
		ori = s.next();
		
	    FileWriter fileWriter = new FileWriter("Living_cells_per_step.txt");
	    printWriter = new PrintWriter(fileWriter);
	    
	    // i < # steps we want
		for(int i = 0; i <12000; i++)
		{
			printWriter.print(total_on_cells + "\n");
			Ant_Move();
		}
		
		printWriter.print(total_on_cells);
		printWriter.close();
		
		// print grid after all steps
		for(int i = 0; i < grid.length; i++)
		{
			for(int j = 0; j < grid[0].length; j++)
			{
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}
	}

}
