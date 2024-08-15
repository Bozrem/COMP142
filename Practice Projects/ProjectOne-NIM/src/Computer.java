public class Computer extends Player {

    @Override
    public void makeMove(Pile[] piles){
        System.out.println("This overrides the normal player move");
    }

}
