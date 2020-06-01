/**
* This class calculates the gravitational acceleration and potential for a particle
* being acted upon by all of the other bodies.
*
* @author Dominic Reeves
* @version 1.0
**/
public class Gravity {
	static final double gravconstant = -(6.67408E-11);
	
	/*/**
	* Calculates and prints the accelerationd due to the effect of gravity from
	* the other bodies on particle A.
	*
	* @param A The particle A that is being affected gravitationally by B
	* @param B The particle that is creating the non-uniform gravitational field.
	* @param X This is the array identifier for the current particle.
	* @param N This is the total number of particles in the array.
	* @return The vector for the acceleration on A due to the gravity of the other particles.
	*
	public static PhysicsVector gravity(Particle A, Particle[] projectile,int X, int N){
		PhysicsVector gravity = new PhysicsVector(0,0,0);
		for(int i = 0; i < N; i++){
			if(i != X){
				PhysicsVector radius = PhysicsVector.subtract(A.GetCoordinates(),projectile[i].GetCoordinates());
				gravity = PhysicsVector.add(gravity, PhysicsVector.scale(gravconstant*projectile[i].GetMass()/Math.pow(radius.magnitude(),2),radius.getUnitVector()));
			}
		}
		return gravity;
	}*/ //It should be noted that this function can be used to calculate the gravitational acceleration due to all particles in the system using an array of particles
		//where as the one in use, due to some restrictions, does not take particles but arrays of the mass and position to start out with.
	
	/**
	* Calculates and prints the gravitational potential for particle A due to all the other bodies.
	*
	* @param A The particle A that is being affected gravitationally by B
	* @param B The particle that is creating the non-uniform gravitational field.
	* @param X This is the array identifier for the current particle.
	* @param N This is the total number of particles in the array.
	* @return The magnitude of the gravitational potential for A.
	*/
	public static PhysicsVector Potential(Particle A, Particle[] projectile,int X, int N){
		PhysicsVector potential = new PhysicsVector(0,0,0);
		for(int i = 0; i < N; i++){
			if(i != X){
				PhysicsVector radius = PhysicsVector.subtract(A.GetCoordinates(),projectile[i].GetCoordinates());
				potential = PhysicsVector.add(potential, PhysicsVector.scale(gravconstant*projectile[i].GetMass()*A.GetMass()/radius.magnitude(),radius.getUnitVector()));
			}
		}
		return potential;
	}
	

	
	/**
	* Calculates and prints the accelerationd due to the effect of gravity from
	* the other bodies on particle A.
	*
	* @param A The coordinates of the particle that is being accelerated.
	* @param projectile The array of coordinates of each particle in the system.
	* @param X This is the array identifier for the current particle.
	* @param N This is the total number of particles in the array.
	* @param mass This is the array of masses of each of the particles in the system.
	* @return The vector for the acceleration on A due to the gravity of the other particles.
	*/
	public static PhysicsVector gravityRK(PhysicsVector A, PhysicsVector[] projectile,int X, int N, double[] Mass){
		PhysicsVector gravity = new PhysicsVector(0,0,0);
		for(int i = 0; i < N; i++){
			if(i != X){
				PhysicsVector radius = PhysicsVector.subtract(A,projectile[i]);
				gravity = PhysicsVector.add(gravity, PhysicsVector.scale(gravconstant*Mass[i]/Math.pow(radius.magnitude(),2), radius.getUnitVector()));
			}
		}
		return gravity;
	}
	
}


