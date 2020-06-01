import java.util.Scanner; 
import java.lang.Math;
import java.io.*;
import java.io.FileWriter;
import java.io.IOException;


/**
* This is the main class that will draw on others to simulate the solar system,
* it works for any N Body gravitational system but also can call upon pre-set
* configurations of gravitational bodies corresponding to those in our Solar System.
*
* @author Dominic Reeves
* @version 1.0
**/
public class Projectile
{
	//Defining the new scanner
	public static Scanner sc = new Scanner(System.in);
	
	/**
	* A static and void method that calls the necessary methods in other
	* classes and gets the input values from the user before printing
	* the calculated data.
	*
	*/
	public static void main(String[] args)
	{
		//Initialising some of the most basic variables.
		int choice;
		int endCondition = 1;
		int N;
		int Y = 0;
		Particle[] projectile = new Particle[0];
		boolean flag;
		
		
		//Setting up the projectile array to contain all of the users wanted data.
		do{
			System.out.println("Please state which option you want to use: (1: Solar, 2: Inner Planets, 3: Outer Planets, 4: Individual Planet, 5:N Body simulator, 6: Random Solar System Generator.)");
			choice = sc.nextInt();
			if(choice == 4){
				System.out.println("Please input which planet you want: 1 = Mercury, 2 = Venus, 3 = Earth and Moon etc.");
				Y = sc.nextInt();
			}
			N = Choices.findN(choice, Y);
			projectile = new Particle[N];
			projectile = createProjectile(choice, Y, N, projectile);
			
		}while(N == 0);
		if(choice == 1){
			do{
				
				flag = false;
				System.out.println("Please state which end condition you want to use: (1: A set time, 2: A half orbit).");
				endCondition = sc.nextInt();
				switch(endCondition){
				case 1: flag = true;
					break;
				case 2: flag = true;
					break;
					default: flag = false;
					break;
				}
			}while(flag == false);
		}
		
		
		switch(endCondition){
		case 1: setTime(projectile, N);
			break;
		case 2: halfOrbit(projectile, N);
			break;
		}
		
		
		
	}
	
	
	/**
	* This method gets all the data necesary for a half orbit simulation, before calling the update methods and writing to a .csv file.
	*
	* @param projectile An particle array for all the bodies in the system.
	* @param N The number of particles in the system.
	**/
	public static void halfOrbit(Particle[] projectile, int N){
		//Asking the user for, and collecting, all the data necessary to run the simulation with their given bodies.
		String Filename;
		double step;
		int planet;
		System.out.println("Please input a value for your time step:");
		step = sc.nextDouble();
		System.out.println("Please input which planet you want to calculate the half orbit for:");
		planet = sc.nextInt();
		projectile = Planets.COM(projectile, N);
		System.out.println("Please input the filename you want to save your results to:");
		Filename = sc.next();
		boolean printFlag = printOption(); 
		
		
		//defining csv as a FileWriter
		FileWriter csv = null;
		try{
			//creating the filewriter function csv which prints to a file with name given by the user above.
			csv = new FileWriter(Filename + ".csv");
			
			//defining a double containing total time.
			double time = 0;
			
			//printing all of the headers for the csv file.
			csv.append("Time" + "," + "Energy" + "," + "Momentum" + ",");
			for(int i = 0; i < N; i++){
				csv.append("Particle " + i + " X Coordinate" + "," + "Particle " + i + " Y Coordinate" + "," + "Particle " + i + " Z Coordinate" + ",");
				if(printFlag == true){ 
					csv.append("Particle " + i + " X Velocity" + "," + "Particle " + i + " Y Velocity" + "," + "Particle " + i + " Z Velocity" + ",");
				}
			}
			csv.append("\n");
			
			
			//creating a double that stores the total energy of the system by calling on a method Particle.Energy to calculate the value
			double Energy = Particle.Energy(projectile, N);
			double Momentum = Particle.Momentum(projectile, N);
			csv.append(time + ",");
			csv.append(Energy + ",");
			csv.append(Momentum + ",");
			for(int i = 0; i < N; i++){
				csv.append(Particle.printCoordinates(projectile, i));
				if(printFlag == true){
					csv.append(Particle.printVelocity(projectile, i));
				}
			}
			csv.append("\n");
			do{
				
				//updating time, velocity and coordinate values then shifting so that the new centre of mass is still at position (0,0,0), here we're using the Runge-Kutta method.
				time = time + step;
				projectile = Particle.updateParticle(projectile, step, N);
				
				projectile = Planets.COM(projectile, N);
				
				Energy = Particle.Energy(projectile, N);
				Momentum = Particle.Momentum(projectile, N);
				
				csv.append(time + ",");
				csv.append(Energy + ",");
				csv.append(Momentum + ",");
				for(int i = 0; i < N; i++){
					csv.append(Particle.printCoordinates(projectile, i));
					if(printFlag == true){
						csv.append(Particle.printVelocity(projectile, i));
					}
				}
				csv.append("\n");
				
			}while(projectile[planet].GetCoordinates().getX() < 0); //the check is for end - step as otherwise the program will produce one calculation more than is necessary which can alter percieved orbital periods.
			
			//Letting the user know that the csv file has been successfully created.
			System.out.println("The .csv file was created successfully");
			
			
		} catch (Exception e) {//catching any exceptions and printing the error	
			System.out.println("Error in CsvFileWriter");
			e.printStackTrace();
		} finally { //clearing and closing the file writer.
			try {
				csv.flush();
				csv.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();
			}
		}
	}
	
	/**
	* This method gets all the data necesary for a set time simulation, before calling the update methods and writing to a .csv file.
	*
	* @param projectile An particle array for all the bodies in the system.
	* @param N The number of particles in the system.
	**/
	public static void setTime(Particle[] projectile, int N){
		//Asking the user for, and collecting, all the data necessary to run the simulation with their given bodies.
		String Filename;
		double step;
		double end;
		System.out.println("Please input a value for your time step:");
		step = sc.nextDouble();
		System.out.println("Please input a value for the time period that the simulation will run for:");
		end = sc.nextDouble();
		projectile = Planets.COM(projectile, N);
		System.out.println("Please input the filename you want to save your results to:");
		Filename = sc.next();
		boolean printFlag = printOption(); 
		
		
		//defining csv as a FileWriter
		FileWriter csv = null;
		try{
			//creating the filewriter function csv which prints to a file with name given by the user above.
			csv = new FileWriter(Filename + ".csv");
			
			//defining a double containing total time.
			double time = 0;
			
			//printing all of the headers for the csv file.
			csv.append("Time" + "," + "Energy" + "," + "Momentum" + ",");
			for(int i = 0; i < N; i++){
				csv.append("Particle " + i + " X Coordinate" + "," + "Particle " + i + " Y Coordinate" + "," + "Particle " + i + " Z Coordinate" + ",");
				if(printFlag == true){ 
					csv.append("Particle " + i + " X Velocity" + "," + "Particle " + i + " Y Velocity" + "," + "Particle " + i + " Z Velocity" + ",");
				}
			}
			csv.append("\n");
			
			
			//creating a double that stores the total energy of the system by calling on a method Particle.Energy to calculate the value
			double Energy = Particle.Energy(projectile, N);
			double Momentum = Particle.Momentum(projectile, N);
			csv.append(time + ",");
			csv.append(Energy + ",");
			csv.append(Momentum + ",");
			for(int i = 0; i < N; i++){
				csv.append(Particle.printCoordinates(projectile, i));
				if(printFlag == true){
					csv.append(Particle.printVelocity(projectile, i));
				}
			}
			csv.append("\n");
			do{
				
				//updating time, velocity and coordinate values then shifting so that the new centre of mass is still at position (0,0,0), here we're using the Runge-Kutta method.
				time = time + step;
				projectile = Particle.updateParticle(projectile, step, N);
				
				projectile = Planets.COM(projectile, N);
				
				Energy = Particle.Energy(projectile, N);
				Momentum = Particle.Momentum(projectile, N);
				
				csv.append(time + ",");
				csv.append(Energy + ",");
				csv.append(Momentum + ",");
				for(int i = 0; i < N; i++){
					csv.append(Particle.printCoordinates(projectile, i));
					if(printFlag == true){
						csv.append(Particle.printVelocity(projectile, i));
					}
				}
				csv.append("\n");
				
			}while(time < end - step); //the check is for end - step as otherwise the program will produce one calculation more than is necessary which can alter percieved orbital periods.
			
			//Letting the user know that the csv file has been successfully created.
			System.out.println("The .csv file was created successfully");
			
			
		} catch (Exception e) {//catching any exceptions and printing the error	
			System.out.println("Error in CsvFileWriter");
			e.printStackTrace();
		} finally { //clearing and closing the file writer.
			try {
				csv.flush();
				csv.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
	/**
	* This method takes the users choice and alters the projectile array to fit.
	*
	* @param choice The integer value representing which system the user wishes to run
	* @param Y If the user wishes to run an individual planet/sun simulation this number coresponds to which planet
	* @param N This is the size of the particle array to be created.
	* @param Projectile This is the projectile class that has been fed in at the correct size for those methods that need it.
	* @return Returns the updated projectile array (consisting of particles).
	**/
	public static Particle[] createProjectile(int choice, int Y, int N, Particle[] projectile){
		boolean flag = false;
		switch(choice){
		case 1: projectile = Choices.Solar();
			flag = true;
			break;
		case 2: projectile = Choices.Inner();
			flag = true;
			break;
		case 3: projectile = Choices.Outer();
			flag = true;
			break;
		case 4: projectile = Choices.Individual(Y, projectile);
			flag = true;
			break;
		case 5: projectile = Choices.NBody(N);
			flag = true;
			break;
		case 6: projectile = Choices.NBodyRnd(N);
			flag = true;
			break;
		default:
			break;
		}
		
		return projectile;
	}
	
	
	/**
	* This method simply returns a boolean true or false for if the user wants to print the velocities of each particle alongside the positions.
	*
	* @return returns true for if the user wants velocities and false if they don't.
	**/
	public static boolean printOption(){
		boolean flag = false;
		boolean velocityChoice = false;
		do{
			System.out.println("Do you want to print the velocities as well as the positions of each body? (Y/N)");
			String choice = sc.next();
			if(choice.equals("Y")){
				velocityChoice = true;
				flag = true;
			}else if(choice.equals("N")){
				velocityChoice = false;
				flag = true;
			}else {
				System.out.println("Please input 'Y' or 'N' for if you want the velocities printed or not.");
			}
		}while(flag == false);
		return velocityChoice;
	}
}
