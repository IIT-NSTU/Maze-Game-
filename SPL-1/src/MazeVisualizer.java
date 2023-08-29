import java.util.Arrays;
import java.util.List;

 class MazeVisualizer {


    public static void visualizePath(List<Node> path, int[][] grid, int rows, int cols) {
        if (path.isEmpty()) {
            System.out.println("No path to visualize.");
            return;
        }

        char[][] visualGrid = new char[rows][cols];

        // Initialize the visual grid with empty cells
        for (int i = 0; i < rows; i++) {
            Arrays.fill(visualGrid[i], '.');
        }

        // Mark the path with '*' and obstacles with '#'
        for (Node node : path) {
            visualGrid[node.x][node.y] = '*';
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    visualGrid[i][j] = '#'; // Mark obstacles
                }
            }
        }

        System.out.println("Visualization of the path:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(visualGrid[i][j] + " ");
            }
            System.out.println();
        }
    }
}
