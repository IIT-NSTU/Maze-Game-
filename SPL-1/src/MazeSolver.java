import java.util.*;

public class MazeSolver {
    public static List<Node> findPath(int[][] grid, int[][] DIRS, int startX, int startY, int goalX, int goalY) {
        Node startNode = new Node(startX, startY);
        Node goalNode = new Node(goalX, goalY);

        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(Node::getF));
        Set<Node> closedSet = new HashSet<>();
        openSet.add(startNode);

        boolean[][] visited = new boolean[grid.length][grid[0].length];

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            if (current.x == goalNode.x && current.y == goalNode.y) {
                return reconstructPath(current);
            }

            closedSet.add(current);

            for (int[] dir : DIRS) {
                int newX = current.x + dir[0];
                int newY = current.y + dir[1];

                if (isValidPosition(newX, newY, grid) && !closedSet.contains(new Node(newX, newY)) && grid[newX][newY] != 1) {
                    Node neighbor = new Node(newX, newY);
                    int tentativeG = current.g + 1;
                    int h = calculateHeuristic(newX, newY, goalNode.x, goalNode.y);

                    if (visited[newX][newY]) {
                        continue; // Skip visited neighbors
                    }

                    if (openSet.contains(neighbor) && tentativeG >= neighbor.g) {
                        continue;
                    }

                    neighbor.parent = current;
                    neighbor.g = tentativeG;
                    neighbor.h = h;

                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }

            visited[current.x][current.y] = true; // Mark the current node as visited
        }

        System.out.println("Path not found.");
        return Collections.emptyList();
    }

    private static int calculateHeuristic(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    private static boolean isValidPosition(int x, int y, int[][] grid) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length;
    }

    private static List<Node> reconstructPath(Node current) {
        List<Node> path = new ArrayList<>();
        while (current != null) {
            path.add(0, current);
            current = current.parent;
        }
        return path;
    }
}
