import java.util.*;

  class MazeGenerator {
      public static int [][] generateRandomMaze(int rows, int cols) {

        // Initialize the maze grid with walls (all cells set to '1')
        int[][] maze = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                maze[i][j] = 1;
            }
        }

        // Create a starting point and make it the current cell
        int startX = 0;
        int startY = 0;
        maze[startX][startY] = 0; // Mark the starting point as empty

        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{startX, startY});

        // Use the current time as a seed for randomness
        long seed = System.currentTimeMillis();
        Random random = new Random(seed);

        while (!stack.isEmpty()) {
            int[] currentCell = stack.peek();
            int x = currentCell[0];
            int y = currentCell[1];
            boolean hasUnvisitedNeighbor = false;

            // Randomly shuffle the order in which neighbors are visited
            List<int[]> neighbors = Arrays.asList(
                    new int[]{x - 2, y}, new int[]{x + 2, y}, new int[]{x, y - 2}, new int[]{x, y + 2}
            );
            Collections.shuffle(neighbors, random); // Use the random object

            for (int[] neighbor : neighbors) {
                int nx = neighbor[0];
                int ny = neighbor[1];

                if (nx >= 0 && nx < rows && ny >= 0 && ny < cols && maze[nx][ny] == 1) {
                    maze[nx][ny] = 0; // Mark the wall as empty
                    maze[x + (nx - x) / 2][y + (ny - y) / 2] = 0; // Remove the wall between cells
                    stack.push(neighbor); // Make the neighbor the current cell
                    hasUnvisitedNeighbor = true;
                    break;
                }
            }

            if (!hasUnvisitedNeighbor) {
                stack.pop(); // Backtrack if no unvisited neighbors are found
            }
        }

        // Set the ending point at the last row and column
        int endX = rows - 1;
        int endY = cols - 1;
        maze[endX][endY] = 0; // Mark the end point as empty

        // Update the grid with the generated maze

          // Display the starting and ending points
        System.out.println("Starting Point: (0, 0)");
        System.out.println("Ending Point: (" + endX + ", " + endY + ")");

        // Visualize the maze with starting and ending points
        char[][] visualGrid = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                visualGrid[i][j] = maze[i][j] == 1 ? '#' : '.';
            }
        }

        // Mark starting and ending points
        visualGrid[startX][startY] = 'S';
        visualGrid[endX][endY] = 'E';

        // Print the visual representation of the maze
        System.out.println("Visualization of the maze:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(visualGrid[i][j] + " ");
            }
            System.out.println();
        }
        return maze;
    }
}
