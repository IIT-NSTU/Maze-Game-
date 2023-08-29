import java.util.List;

public class MazeVerifier {
    public static boolean isSolutionCorrect(List<Node> path,int rows,int cols,int [][] grid) {
        if (path.isEmpty()) {
            return false; // Path is empty, not a valid solution.
        }

        int startX = 0;
        int startY = 0;
        int goalX = rows - 1;
        int goalY = cols - 1;

        // Check if the path starts at the starting point and ends at the ending point.
        if (path.get(0).x != startX || path.get(0).y != startY || path.get(path.size() - 1).x != goalX
                || path.get(path.size() - 1).y != goalY) {
            return false; // Path doesn't start or end at the correct points.
        }

        // Check if the path goes through valid empty cells and doesn't go through obstacles.
        for (int i = 0; i < path.size() - 1; i++) {
            Node current = path.get(i);
            Node next = path.get(i + 1);

            if (grid[next.x][next.y] == 1) {
                return false; //  obstacle.
            }

            if (Math.abs(current.x - next.x) + Math.abs(current.y - next.y) != 1) {
                return false; //  invalid move.
            }
        }

        return true;
    }
}
