import java.util.Scanner; 
import java.lang.Math;


public class Choices{
	//defining the scanner
	public static Scanner sc = new Scanner(System.in);
	
	/**
	* Finds out, based on user choices, the number of particles in the array.
	*
	* @param choice This correlates to one of the given set gravitational systems.
	* @param Y This correlates to the individual pairing of sun/planet given that 'choice' correlated to wanting this.
	* @return The value of N number of particles in the system.
	*/
	public static int findN(int choice, int Y){
		int N = 0;
		switch(choice){
		case 1: N = 11;
			break;
		case 2: N = 6;
			break;
		case 3: N = 6;
			break;
		case 4: if(Y == 3){
				N = 3;
		} else{
			N = 2;
		}
		break;
	case 5: System.out.println("Please input the number of particles you wish to simulate:");
		N = sc.nextInt();
		break;
	case 6: System.out.println("Please input the number of suns in your system:");
		N = sc.nextInt();
		break;
		default: System.out.println("Unfortunately you input an invalid option, please start again:");
		break;
		}
		return N;
	}
	
	
	/**
	* Finds out, based on user choices, the individual planet system they want to simulate.
	*
	* @param Projectile The array of particles for the system.
	* @param Y This correlates to the individual pairing of sun/planet given that 'choice' correlated to wanting this.
	* @return The completed array of particles .
	*/
	public static Particle[] Individual(int Y, Particle[] projectile){
		switch(Y){
		case 1:projectile[0] = Planets.Sun();
			projectile[1] = Planets.Mercury();
			break;
		case 2:projectile[0] = Planets.Sun();
			projectile[1] = Planets.Venus();
			break;
		case 3:projectile[0] = Planets.Sun();
			projectile[1] = Planets.Earth();
			projectile[2] = Planets.Moon();
			break;
		case 4:projectile[0] = Planets.Sun();
			projectile[1] = Planets.Mars();
			break;
		case 5:projectile[0] = Planets.Sun();
			projectile[1] = Planets.Jupiter();
			break;
		case 6:projectile[0] = Planets.Sun();
			projectile[1] = Planets.Saturn();
			break;
		case 7:projectile[0] = Planets.Sun();
			projectile[1] = Planets.Uranus();
			break;
		case 8:projectile[0] = Planets.Sun();
			projectile[1] = Planets.Neptune();
			break;
		case 9:projectile[0] = Planets.Sun();
			projectile[1] = Planets.Pluto();
			break;
			default:System.out.println("Please input a valid option.");
		}
		return projectile;
	}
	
	
	/**
	* Returns the main bodies in our solar system as the particles in the array.
	*
	* @param Projectile The array of particles for the system.
	* @return The completed array of particles for our solar system.
	*/
	public static Particle[] Solar(){
		Particle[] projectile = new Particle[11];
		projectile[0] = Planets.Sun();
		projectile[1] = Planets.Mercury();
		projectile[2] = Planets.Venus();
		projectile[3] = Planets.Earth();
		projectile[4] = Planets.Moon();
		projectile[5] = Planets.Mars();
		projectile[6] = Planets.Jupiter();
		projectile[7] = Planets.Saturn();
		projectile[8] = Planets.Uranus();
		projectile[9] = Planets.Neptune();
		projectile[10] = Planets.Pluto();
		return projectile;
	}
	
	
	/**
	* Returns the main inner planets (and moon) from our solar system as the particles in the array.
	*
	* @param Projectile The array of particles for the system.
	* @return The completed array of particles for inner solar system.
	*/
	public static Particle[] Inner(){
		Particle[] projectile = new Particle[6];
		projectile[0] = Planets.Sun();
		projectile[1] = Planets.Mercury();
		projectile[2] = Planets.Venus();
		projectile[3] = Planets.Earth();
		projectile[4] = Planets.Moon();
		projectile[5] = Planets.Mars();
		return projectile;
	}
	
	/**
	* Returns the main outer planets from our solar system as the particles in the array.
	*
	* @param Projectile The array of particles for the system.
	* @return The completed array of particles for our solar system.
	*/
	public static Particle[] Outer(){
		Particle[] projectile = new Particle[6];
		projectile[0] = Planets.Sun();
		projectile[1] = Planets.Jupiter();
		projectile[2] = Planets.Saturn();
		projectile[3] = Planets.Uranus();
		projectile[4] = Planets.Neptune();
		projectile[5] = Planets.Pluto();
		return projectile;
	}
	
	
	/**
	* Using user input an array is created of size N then data values are manually entered by the user for each coordinate, velocity and mass.
	*
	* @param N The number of particles in the system/array.
	* @return The completed array of particles for the user designed system.
	*/
	public static Particle[] NBody(int N){
		double xvelocity;
		double yvelocity;
		double zvelocity;
		double xcoordinate;
		double ycoordinate;
		double zcoordinate;
		double mass;
		Particle[] projectile = new Particle[N];
		for(int i = 0; i < N; i++){
			System.out.println("Please input a value for the x-velocity for particle " + i + ":");
			xvelocity = sc.nextDouble();
			System.out.println("Please input a value for the y-velocity for particle " + i + ":");
			yvelocity = sc.nextDouble();
			System.out.println("Please input a value for the z-velocity for particle " + i + ":");
			zvelocity = sc.nextDouble();
			System.out.println("Please input a value for the x-coordinate for particle " + i + ":");
			xcoordinate = sc.nextDouble();
			System.out.println("Please input a value for the y-coordinate for particle " + i + ":");
			ycoordinate = sc.nextDouble();
			System.out.println("Please input a value for the z-coordinate for particle " + i + ":");
			zcoordinate = sc.nextDouble();
			System.out.println("Please input a value for the mass for particle " + i + ":");
			mass = sc.nextDouble();
			
			projectile[i] = new Particle(mass, new PhysicsVector(xvelocity, yvelocity, zvelocity), new PhysicsVector(xcoordinate,ycoordinate,zcoordinate));
		}
		return projectile;
	}
	
	
	/**
	* Note: This is more proof of concept than any useful thing hence the data ranges being semi-`arbritray numbers.
	*Creates N particles of randomised mass, coordinates and velocity then puts them into the particle array.
	*
	* @param N The number of particles in the system/array.
	* @return N randomised particles in the particle array.
	*/
	public static Particle[] NBodyRnd(int N){
		Particle[] projectile = new Particle[N];
		for(int i = 0; i < N; i++){
			projectile[i] = new Particle(Math.random()*2E32, new PhysicsVector(-50E3 + Math.random()*100E3, -50E3 + Math.random()*100E3, -50E3 + Math.random()*100E3), new PhysicsVector(-1000E10 + Math.random()*2000E10,-1000E10 + Math.random()*2000E10,-1000E10 + Math.random()*2000E10));
		}
		return projectile;
	}
}