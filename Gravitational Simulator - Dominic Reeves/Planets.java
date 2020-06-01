

/**
* This class contains all the pre-set data for the main gravitational bodies in
* our solar system. It also contains a method that will configure the centre of
* mass of a system to be located at position (0,0,0).
*
* @author Dominic Reeves
* @version 1.0
**/
public class Planets{
	
	/**
	* Returns the set data for the Sun.
	*
	* @return The Sun particle.
	*/
	public static Particle Sun(){
		Particle sun = new Particle(1.989E30, new PhysicsVector(0,0,0), new PhysicsVector(0,0,0));
		return sun;
	}
	
	/**
	* Returns the set data for Mercury.
	*
	* @return The Mercury particle.
	*/
	public static Particle Mercury(){
		Particle mercury = new Particle(0.330E24, new PhysicsVector(-47.4E3,0,0), new PhysicsVector(0,57.9E9,0));
		return mercury;
	}
	
	/**
	* Returns the set data for Venus.
	*
	* @return The Venus particle.
	*/
	public static Particle Venus(){
		Particle venus = new Particle(4.87E24, new PhysicsVector(-35.0E3,0,0), new PhysicsVector(0,108.2E9,0));
		return venus;
	}
	
	/**
	* Returns the set data for Earth.
	*
	* @return The Earth particle.
	*/
	public static Particle Earth(){
		Particle earth = new Particle(5.97E24, new PhysicsVector(-29.8E3,0,0), new PhysicsVector(0,149.6E9,0));
		return earth;
	}
	
	/**
	* Returns the set data for the Earth's Moon.
	*
	* @return The Moon particle.
	*/
	public static Particle Moon(){
		Particle moon = new Particle(0.073E24, new PhysicsVector(-29.8E3,1022,0), new PhysicsVector(0.384E9,149.6E9,0));
		return moon;
	}
	
	/**
	* Returns the set data for Mars.
	*
	* @return The Mars particle.
	*/
	public static Particle Mars(){
		Particle mars = new Particle(0.642E24, new PhysicsVector(-24.1E3,0,0), new PhysicsVector(0,227.9E9,0));
		return mars;
	}
	
	/**
	* Returns the set data for Jupiter.
	*
	* @return The Jupiter particle.
	*/
	public static Particle Jupiter(){
		Particle jupiter = new Particle(1898E24, new PhysicsVector(-13.1E3,0,0), new PhysicsVector(0,778.6E9,0));
		return jupiter;
	}
	
	/**
	* Returns the set data for Saturn.
	*
	* @return The Saturn particle.
	*/
	public static Particle Saturn(){
		Particle saturn = new Particle(568E24, new PhysicsVector(-9.7E3,0,0), new PhysicsVector(0,1433.5E9,0));
		return saturn;
	}
	
	/**
	* Returns the set data for Uranus.
	*
	* @return The Uranus particle.
	*/
	public static Particle Uranus(){
		Particle uranus = new Particle(86.8E24, new PhysicsVector(-6.8E3,0,0), new PhysicsVector(0,2872.5E9,0));
		return uranus;
	}
	
	/**
	* Returns the set data for Neptune.
	*
	* @return The Neptune particle.
	*/
	public static Particle Neptune(){
		Particle neptune = new Particle(102E24, new PhysicsVector(-5.4E3,0,0), new PhysicsVector(0,4495.1E9,0));
		return neptune;
	}
	
	/**
	* Returns the set data for Pluto.
	*
	* @return The Pluto particle.
	*/
	public static Particle Pluto(){
		Particle pluto = new Particle(0.0146E24, new PhysicsVector(-4.7E3,0,0), new PhysicsVector(0,5906.4E9,0));
		return pluto;
	}
	
	
	/**
	* This method calculates the centre of mass and shifts all of the other
	* particles so that it's located at (0,0,0)
	*
	* @param N This is the size of the particle array to be created.
	* @param Projectile This is the projectile class that has been fed in at the correct size for those methods that need it.
	* @return Returns the updated projectile array with COM at (0,0,0).
	**/
	public static Particle[] COM(Particle[] projectile, int N){
		double sum = 0;
		for(int i = 0; i < N; i++){
			sum = sum + projectile[i].GetMass();
		}
		
		
		PhysicsVector COM = new PhysicsVector(0,0,0);
		for(int i = 0; i < N; i++){
			COM = PhysicsVector.add(COM, PhysicsVector.scale(projectile[i].GetMass(),projectile[i].GetCoordinates()));
		}
		COM.scale(1/sum);
		
		for(int i = 0; i < N; i++){
			projectile[i].SetCoordinates(PhysicsVector.subtract(projectile[i].GetCoordinates(),COM));
		}
		return projectile;
	}
	
}