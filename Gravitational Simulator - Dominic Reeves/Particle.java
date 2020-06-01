/**
* This method combines PhysicsVectors together to create a particle with position, velocity and mass
* and provides some basic functions for the particle, such as returning the coordinate and updting the position/velocity
*
* @author Dominic Reeves
* @version 1.0
**/
public class Particle {
	
	private double mass;
	private PhysicsVector  particlevelocity;
	private PhysicsVector  particlecoordinates;
	
	/**
	*Creates a particle with 
	*
	* @param Mass The mass of the particle
	* @param Velocity the Vector of the velocity of the particle
	* @param Coordinates The vector of the coordinates of the particle upon creation
	*/
	public Particle(double mass, PhysicsVector velocity, PhysicsVector coordinates){
		this.mass = mass;
		this.particlevelocity = velocity;
		this.particlecoordinates = coordinates;
	}
	
	
	/**
	* Updates the particles mass 
	*
	* @param mass The new mass
	* 
	*/
	public void SetMass(double mass){
		this.mass = mass;
	}                          
	
	/**
	* Updates the particles velocity vector
	*
	* @param velocity The new particle velocity
	*/
	public void SetVelocity(PhysicsVector velocity){
		this.particlevelocity = velocity;
	}
	
	
	/**
	* Updates the particles coordinate vector
	*
	* @param coordinates The new particle coordinates
	*/
	public void SetCoordinates(PhysicsVector coordinates){
		this.particlecoordinates = coordinates;
	}
	
	
	/**
	* Returns a value for the particle mass
	*
	* @return The particle mass
	*/
	public double GetMass(){
		return this.mass;
	}
	
	
	/**
	* Returns a value for the particle velocity vector
	*
	* @return The particle velocity
	*/
	public PhysicsVector GetVelocity(){
		return this.particlevelocity;
	}
	
	
	
	/**
	* Returns a value for the particle coordinates
	*
	* @return The particle coordinate vector
	*/
	public PhysicsVector GetCoordinates(){
		return this.particlecoordinates;
	}
	
	
	
	
	/**
	* Updates the position and velocity vectors for the particle in a non-uniform gravitational field using the Runge-Kutta method.
	*
	* @param bodies is the array of particles that we'll be calculating the force from.
	* @param time this is the time step rather than the time from zero
	* @param N The number of particles in the array
	*/
	public static Particle[] updateParticle(Particle[] bodies, double time, int N){
		
		
		PhysicsVector[] originalCoordinates = new PhysicsVector[N];
		PhysicsVector[] originalVelocities = new PhysicsVector[N];
		double[] masses = new double[N];
		PhysicsVector[] temporary = new PhysicsVector[N];
		PhysicsVector[] KV1 = new PhysicsVector[N];
		PhysicsVector[] KV2 = new PhysicsVector[N];
		PhysicsVector[] KV3 = new PhysicsVector[N];
		PhysicsVector[] KV4 = new PhysicsVector[N];
		PhysicsVector[] KR1 = new PhysicsVector[N];
		PhysicsVector[] KR2 = new PhysicsVector[N];
		PhysicsVector[] KR3 = new PhysicsVector[N];
		PhysicsVector[] KR4 = new PhysicsVector[N];
		for(int i = 0; i < N; i++){
			originalCoordinates[i] = bodies[i].GetCoordinates();
		}
		for(int i = 0; i < N; i++){
			originalVelocities[i] = bodies[i].GetVelocity();
		}
		for(int i = 0; i < N; i++){
			masses[i] = bodies[i].GetMass();
		}
		for(int i = 0; i < N; i++){
			KV1[i] = Gravity.gravityRK(originalCoordinates[i],originalCoordinates, i, N, masses);
		}
		for(int i = 0; i < N; i++){
			KR1[i] = originalVelocities[i];
		}
		for(int i = 0; i < N; i++){
			
			KV2[i] = Gravity.gravityRK(PhysicsVector.add(originalCoordinates[i],PhysicsVector.scale(time*0.5, KR1[i])),PhysicsVector.addArray(originalCoordinates, PhysicsVector.scaleArray(time*0.5, KR1, N), N), i, N, masses);
		}
		for(int i = 0; i< N; i++){
			KR2[i] = PhysicsVector.add(originalVelocities[i],PhysicsVector.scale(time*0.5,KV1[i]));
		}
		for(int i = 0; i < N; i++){
			KV3[i] = Gravity.gravityRK(PhysicsVector.add(originalCoordinates[i],PhysicsVector.scale(time*0.5, KR2[i])), PhysicsVector.addArray(originalCoordinates,PhysicsVector.scaleArray(time*0.5, KR2, N), N), i, N, masses);
		}
		for(int i = 0; i< N; i++){
			KR3[i] = PhysicsVector.add(originalVelocities[i],PhysicsVector.scale(time*0.5,KV2[i]));
		}
		for(int i = 0; i < N; i++){
			KV4[i] = Gravity.gravityRK(PhysicsVector.add(originalCoordinates[i],PhysicsVector.scale(time, KR3[i])),PhysicsVector.addArray(originalCoordinates,PhysicsVector.scaleArray(time, KR3, N), N), i, N, masses);
		}
		for(int i = 0; i< N; i++){
			KR4[i] = PhysicsVector.add(originalVelocities[i],PhysicsVector.scale(time,KV3[i]));
		}
		for(int i = 0; i < N; i++){
			bodies[i].SetCoordinates(PhysicsVector.add(bodies[i].GetCoordinates(),PhysicsVector.scale(time/6, PhysicsVector.add(KR1[i],PhysicsVector.add(PhysicsVector.scale(2,KR2[i]),PhysicsVector.add(PhysicsVector.scale(2,KR3[i]),KR4[i])))))); 
			bodies[i].SetVelocity(PhysicsVector.add(originalVelocities[i],PhysicsVector.scale(time/6, PhysicsVector.add(KV1[i],PhysicsVector.add(PhysicsVector.scale(2,KV2[i]),PhysicsVector.add(PhysicsVector.scale(2,KV3[i]),KV4[i]))))));
		}
		return bodies;
	}
	
	
	
	/**
	* This method returns the comma seporated values for x,y and z coordinates
	*
	* @param i This is the array position of the current particle to be printed.
	* @param Projectile This is the projectile class that has been fed in at the correct size for those methods that need it.
	* @return The string containing comma seporated calues of the cooridnates.
	**/
	public static String printCoordinates(Particle[] projectile, int i){
		return (projectile[i].GetCoordinates().getX() + "," + projectile[i].GetCoordinates().getY() + "," + projectile[i].GetCoordinates().getZ() + ",");
	}
	
	
	/**
	* This method returns the comma seporated values for x,y and z coordinates
	*
	* @param i This is the array position of the current particle to be printed.
	* @param Projectile This is the projectile class that has been fed in at the correct size for those methods that need it.
	* @return The string containing comma seporated calues of the velocities.
	**/
	public static String printVelocity(Particle[] projectile, int i){
		return (projectile[i].GetVelocity().getX() + "," + projectile[i].GetVelocity().getY() + "," + projectile[i].GetVelocity().getZ() + ",");
	}
	
	
	/**
	* This method calculates and returns the total energy of the system.
	*
	* @param N This is the number of particles in the system.
	* @param Projectile This is the projectile class that has been fed in at the correct size for those methods that need it.
	* @return The total energy of the system as a double.
	*/
	public static double Energy(Particle[] projectile, int N){
		double KE = 0;
		double PE = 0;
		double Energy = 0;
		for(int i = 0; i<N; i++){
			KE += 0.5*projectile[i].GetMass()*Math.pow(projectile[i].GetVelocity().magnitude(),2);
			PE += Gravity.Potential(projectile[i], projectile, i, N).magnitude();
		}
		Energy = KE + PE;
		return (Energy);
	}
	
	
	/**
	* This method calculates and returns the total momentum of the system.
	*
	* @param N This is the number of particles in the system.
	* @param Projectile This is the projectile class that has been fed in at the correct size for those methods that need it.
	* @return The total momentum of the system as a double.
	**/
	public static double Momentum(Particle[] projectile, int N){
		PhysicsVector momentum = new PhysicsVector(0,0,0);
		for(int i = 0; i<N; i++){
			momentum = PhysicsVector.add(momentum,PhysicsVector.scale(projectile[i].GetMass(),projectile[i].GetVelocity()));
		}
		return (momentum.magnitude());
	}
	
}


