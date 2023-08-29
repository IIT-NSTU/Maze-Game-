public class Node {
    int x, y;
    int g, h;
    Node parent;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getF() {
        return g + h;
    }
}
