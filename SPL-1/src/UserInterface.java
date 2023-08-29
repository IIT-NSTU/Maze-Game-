import java.util.List;
public class UserInterface {
    public  void homePage() {
        System.out.println("Choose your option:");
        System.out.println("1. Play as the Computer");
        System.out.println("2. Play as the User");
    }

    public  void path(List<Node> path){
        System.out.println("There is a Path :");
        for (Node node : path) {
            System.out.println("(" + node.x + ", " + node.y + ")");
        }
    }
    public  void level(){
        System.out.println("Choose your level : ");
        System.out.println("1. Beginner ");
        System.out.println("2. Medium ");
        System.out.println("3. Advanced ");
    }
    public void noPathMsg(){
        System.out.println("Your answer is correct. There is no path.");
    }
    public void aPathMsg(){
        System.out.println("Your answer is incorrect. There is a path.");
    }
    public void PathMsg(String msg){
        System.out.println("Your path is "+msg+"correct!");
    }
    public void noPath(){
        System.out.println("Path not found.");
    }
    public void message(String msg){
        System.out.println("Enter the "+msg);
    }


}
