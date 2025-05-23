import java.util.Random; // This is for the random number generator

public class newForDiseaseSimulation { // Setting up the variables
    public static void main(String[] args) {
        
        int N = 9; // Size of the grid WxL
        String[][] grid = new String[N][N];

        Random random = new Random(); // creates the new number
        double infectionRate = .7; // infection rate from the patient zero
        double recoveryRate = 0.1; // recovery rate after being infected

        for (int i = 0; i < N; i++) { // Starts out with everyone being susceptible with one patient zero
            for (int j = 0; j < N; j++) {
                grid[i][j] = "S"; // Puts everyone to susceptible except for one
            }
        }

        int infectedRow = random.nextInt(N); // Makes one person start out as patient zero
        int infectedColumn = random.nextInt(N);
        grid[infectedRow][infectedColumn] = "I"; // Gives that one person I for infected

        System.out.println("Run Simulation"); // Writes that on the screen
        printGrid(grid); // prints the grid
        System.out.println("Infected: " + countInfected(grid) + ", Susceptible: " + countSusceptible(grid) + ", Recovered: " + countRecovered(grid));

        for (int testRun = 1; testRun < 25; testRun++) { // The amount of times the code will run giving the infected time to spread the disease
            String[][] nextGrid = new String[N][N]; // array for next grid

            for (int i = 0; i < N; i++) { // copies from one array to the next one for when the grid needs to change
                for (int j = 0; j < N; j++) {
                    nextGrid[i][j] = grid[i][j]; // when there is no change

                    if (grid[i][j].equals("I")) { // gives a chance for the infected to become recovered
                        if (random.nextDouble() < recoveryRate) {
                            nextGrid[i][j] = "R"; 
                        }
                        else {
                            // The infected person stays infected
                            int[] r = {-1, 1, 0, 0}; // the r is for row
                            int[] c = {0, 0, -1, 1}; // the c is for column

                            for (int k = 0; k < 4; k++) {
                                int nr = i + r[k]; // nr = neighborRow
                                int nc = j + c[k]; // nc = neighborColumn

                                if (nr >= 0 && nr < N && nc >= 0 && nc < N && grid[nr][nc].equals("S")) { // gives a chance for a susceptible individual to become infected
                                    if (random.nextDouble() < infectionRate) {
                                        nextGrid[nr][nc] = "I";
                                    }
                                }
                            }
                        }
                    }
                }
            }
            grid = nextGrid;

            System.out.println("Test Run " + testRun); // To see which numbered test run it is
            printGrid(grid); // shows the grid onto the screen
            System.out.println("Infected: " + countInfected(grid) + ", Susceptible: " + countSusceptible(grid) + ", Recovered: " + countRecovered(grid)); // This prints out the results below the grid
        }
    }

    static void printGrid(String[][] grid) { // Prints out the grid by also giving a space between each individual
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    static int countInfected(String[][] grid) { // Counts up all of the infected people and returns that count
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].equals("I")) {
                    count++;
                }
            }
        }
        return count;
    }

    static int countSusceptible(String[][] grid) { // Counts up all of the susceptible people and returns that count
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].equals("S")) {
                    count++;
                }
            }
        }
        return count;
    }

    static int countRecovered(String[][] grid) { // Counts up all of the recovered people and returns that count
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].equals("R")) {
                    count++;
                }
            }
        }
        return count;
    }
}
