import java.util.Scanner;

public class TreeViewer {
    private ParanoidMinimaxTree tree;
    private int parentID;

     TreeViewer(ParanoidMinimaxTree tree){
         this.tree = tree;
         parentID = tree.treeID;
     }

     public void browseTree(){
         boolean userBrowsing = true;
         while (userBrowsing){
             System.out.println("\n\n\nCurrent Tree Stats");
             Pile.printPiles(tree.getPiles());
             System.out.println("Is computer active player: " + (tree.activePlayer == tree.computerID));
             System.out.println("Strength: " + tree.getStrength());
             System.out.println("Recommended move: " + tree.getMove());

             System.out.println("\nChildren:");

             printSubtrees();
             userBrowsing = getUserInput();
         }
     }

     private void printSubtrees(){
         for (int i = 0; i < tree.subTrees.size(); i++){
             System.out.println("\nOption " + i + ": ");
             Pile.printPiles(tree.subTrees.get(i).getPiles());
             System.out.println("Child Strength: " + tree.subTrees.get(i).getStrength());
             System.out.println("Child Hash: " + tree.subTrees.get(i).hashCode());
         }
     }

     private boolean getUserInput(){
         System.out.println("Which option would you like to explore (-1 for parent, -2 for exit):");
         Scanner scan = new Scanner(System.in);
         int option = scan.nextInt();
         if (option == -1) tree = ParanoidMinimaxTree.index.get(parentID);
         if (option == -2) return false;
         if (option < -2 || option > tree.subTrees.size()) return false;
         tree = tree.subTrees.get(option);
         return true;
     }
}
