
import java.util.*;

public class MazeGame {

    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;
        UserInterface ui = new UserInterface();
        System.out.println("************************************************");
        System.out.println("*                                              *");
        System.out.println("*           Welcome to Maze Challenge!         *");
        System.out.println("*                                              *");
        System.out.println("************************************************");
        while (playAgain) {
            ui.homePage();

            int option = scanner.nextInt();

            int[][] grid;
            int rows;
            int cols;
            if (option == 1) {
                //player-1(user);
                ui.message("number of rows and columns");
                rows = scanner.nextInt();
                cols = scanner.nextInt();

                grid = new int[rows][cols];

                ui.message("grid(Maze) elements (0 for empty, 1 for obstacle");
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        grid[i][j] = scanner.nextInt();
                    }
                }

                ui.message("Starting points");
                int startX = scanner.nextInt();
                int startY = scanner.nextInt();
                ui.message("goal points");
                int goalX = scanner.nextInt();
                int goalY = scanner.nextInt();

                List<Node> path = MazeSolver.findPath(grid, DIRS, startX, startY, goalX, goalY);


                if (!path.isEmpty()) {
                    ui.path(path);
                    MazeVisualizer.visualizePath(path, grid, rows, cols); // Visualize the path
                } else {
                    ui.noPath();
                }
            } else if (option == 2) {
                ui.level();
                int level = scanner.nextInt();

                if (level == 1) {
                    rows = 3;
                    cols = 3;
                } else if (level == 2) {
                    rows = 4;
                    cols = 5;
                } else if (level == 3) {
                    rows = 5;
                    cols = 5;
                } else {
                    System.out.println("Invalid level selection.");
                    return;
                }
                grid = MazeGenerator.generateRandomMaze(rows, cols);
                scanner.nextLine(); // Consume the newline
                System.out.print("Enter your solution path (e.g., 0 0, 1 0, 2 0, .../no path): ");
                String userPathInput = scanner.nextLine();

                if (userPathInput.equals("no path")) {
                    int startX = 0;
                    int goalX = rows - 1;
                    List<Node> path = MazeSolver.findPath(grid, DIRS, startX, 0, goalX, cols - 1);


                    if (!path.isEmpty()) {
                        ui.aPathMsg();
                        ui.path(path);
                        MazeVisualizer.visualizePath(path, grid, rows, cols); // Visualize the computer's solution
                    } else {
                        ui.noPathMsg();
                    }
                } else {
                    // Parse the user's solution path
                    String[] pathTokens = userPathInput.split(", ");
                    List<Node> userPath = new ArrayList<>();
                    for (String token : pathTokens) {
                        String[] coordinates = token.split(" ");
                        int x = Integer.parseInt(coordinates[0]);
                        int y = Integer.parseInt(coordinates[1]);
                        userPath.add(new Node(x, y));
                    }

                    // Computer provides a solution
                    int startX = 0;
                    int goalX = rows - 1;
                    List<Node> computerPath = MazeSolver.findPath(grid, DIRS, startX, 0, goalX, cols - 1);

                    if (MazeVerifier.isSolutionCorrect(userPath, rows, cols, grid)) {
                        ui.PathMsg("");

                        // Check if the computer's path is also correct and has the same length as the user's path
                        if (MazeVerifier.isSolutionCorrect(userPath, rows, cols, grid) && computerPath.size() == userPath.size()) {
                            ui.PathMsg(" Optimal"); // Both paths are correct and have the same length (optimal).
                        } else {
                            System.out.println("But not optimal ");
                        }

                        MazeVisualizer.visualizePath(userPath, grid, rows, cols); // Visualize the user's path
                    } else {
                        ui.PathMsg("in");

                        if (!computerPath.isEmpty()) {
                            ui.path(computerPath);

                            MazeVisualizer.visualizePath(computerPath, grid, rows, cols); // Visualize the computer's solution
                        } else {
                            ui.noPath();
                        }
                    }

                }
            } else {
                System.out.println("Invalid option.");
            }
            // Prompt the user to play again or exit
            System.out.print("Do you want to play again? (play/exit): ");
            String playAgainInput = scanner.next();
            if (!playAgainInput.equalsIgnoreCase("play")) {
                playAgain = false; // Exit the loop if the user chooses to exit
            }
        }

        System.out.println("Goodbye!");
        scanner.close();
    }
}