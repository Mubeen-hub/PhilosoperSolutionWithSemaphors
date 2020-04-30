/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DiningProblemSolution;

import java.util.concurrent.Semaphore;

/**
 *
 * @author mubeen
 */

public class DiningProblemSolution {

    // The number of philosophers
    private static final int numberOfPhilophers = 20;

    
    public static void main(String[] args) {
        
        // Each chopstick with a lock
        Semaphore[] chopsticks = new Semaphore[numberOfPhilophers];

        for (int i = 0; i < numberOfPhilophers; i++) {
            chopsticks[i] = new Semaphore(1);
        }

        // Create numberOfPhilophers and start running threads for each philosopher.
        Philosopher[] philosophers = new Philosopher[numberOfPhilophers];

        for (int i = 0; i < numberOfPhilophers; i++) {
            
            // Passing philopher unique id, it's left and right fork
            
            philosophers[i] = new Philosopher(i, chopsticks[i], chopsticks[(i + 1) % numberOfPhilophers]);
            new Thread(philosophers[i]).start();
        }

    }

}
