Project Name: Gravitational N-Body Simulator.
Project Author: Dominic Reeves
Note: The files have java documentation. However, it has not been compiled yet to ensure no complications occur.
== Description ==
Projectile.java - This is the main java file it calls each other class file in turn, it also gets all of the necessary data from the user to tailor the simulation to their needs. It also opens a file writer and prints all of the calculated data.

Planets.java - Contains all of the data for the average values of velocity, position and mass of each of the planets (as well as Pluto and Earth’s moon). It also contains the method to calculate the centre of mass and shift all of the particles in the system by that value.

PhysicsVector.java - This is the basic PhysicsVector class file however there were also some amendments made to the code that allow the scale and add functions to act on an array of PhysicsVectors.

Particle.java - This class file contains the necessary methods to define a particle as a mass a position and a velocity. It also contains methods that will update the position and velocity based on the other particles (using a Runge-Kutta 4 numerical method), calculates the total energy and total momentum of the system.

Gravity.java - Contains methods to calculate the gravitational potential and gravitational acceleration of a particle due to all of the other particles.

Choices.java - Contains all of the methods for both identifying the user choice and then setting up the projectile array to be full of the relevant particles.

== Installation ==

How to install the program and get the simulation running.

1. Javac all of the .java files to create the classes.
2. To start the program you’ll have to run the Projectile class.
3. Choose which of the program options you want to use, details below:
	- 1. Solar, this option will fill the projectile array with all of the values with all of the pre-set Particle values.
	- 2. Inner Planets, this option will fill the projectile array with the bodies up to mars.
	- 3. Outer Planets, this option will fill the projectile array with the sun and bodies from Jupiter to Pluto.
	- 4. Individual Planet, this option fills the projectile array with the sun and an individual planet, apart from option 3 which contains the Earth and Moon.
	- 5. N Body Simulator, this option allows the user to input any values for any N number of bodies that they want.
	- 6. Random Solar System Generator, this option is more of a proof of concept, it uses values within approximately the range of our solar system to simulate a roughly random system.
4. Input any values for the range the simulation that the user wants, be it a set value of time or finding the half orbit of a planet in the solar system (only applicable to original option 1.)
5. Choose the option for the file name you want to save the data to (the .csv file type is not necessary).
6. Choose whether or not the user wants to print the velocity values as well as the positions.

== Output ==

It is worth noting that the output is not a direct graphical depiction, to import it into QtiPlot you will need to:

1. Use the first row as Column Names
2. Have the separator set as: ,
3. Have Endline character set as CRLF (Windows)

Using these settings should allow you to correctly import the data into QtiPlot.




