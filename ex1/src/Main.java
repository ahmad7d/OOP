
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.




public class Main {

    public static void main(String[] args){
//        Tournament tournament = null;
//        tournament.main(args);

        int[] x = {1, 2};
        foo(x);
        System.out.println(x[0]);

        }
    static int[] foo(int[] x){
        x[0]++;
        return x;


    }
}





//Mark[][] board = {{Mark.X, Mark.X, Mark.X, Mark.X},
//        {Mark.BLANK,Mark.BLANK,Mark.BLANK,Mark.BLANK},
//        {Mark.BLANK,Mark.BLANK,Mark.BLANK,Mark.BLANK},
//        {Mark.BLANK,Mark.BLANK,Mark.BLANK,Mark.BLANK}};
//
//Player firstPlayer = new HumanPlayer();
//Player secondPlayer = new HumanPlayer();
//RendererFactory rendererFactory = new RendererFactory();
//Renderer renderer = rendererFactory.buildRenderer("console", 4);
//
//
//Game game = new Game(firstPlayer, secondPlayer, 4, 3, renderer);
//
//        game.run()