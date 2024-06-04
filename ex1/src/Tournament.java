/**
 * The Tournament class represents a game tournament involving multiple rounds and players.
 * It manages the execution of the tournament and displays the final results.
 */

public class Tournament {
    private final int rounds;
    private final Renderer renderer;
    private final Player firstPlayer;
    private final Player secondPlayer;

    /**
     * Constructs a Tournament with the specified number of rounds, renderer, and players array.
     *
     * @param rounds   The number of rounds in the tournament.
     * @param renderer The renderer used for displaying the game board.
     * @param firstPlayer  first player.
     * @param secnodPlayer  second player.
     */
    public Tournament(int rounds, Renderer renderer, Player firstPlayer, Player secnodPlayer) {
        this.rounds = rounds;
        this.renderer = renderer;
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secnodPlayer;
    }


    /**
     * Plays the tournament with the given parameters and displays the final results.
     *
     * @param size        The size of the game board.
     * @param winsStreak  The winning streak required for a player to win a round.
     * @param firstPlayer  first player.
     * @param secondPlayer  second player.
     * */
    public void playTournament(int size, int winsStreak, String firstPlayer, String secondPlayer) {
        int[] playersCounterWinRounds = {Constants.ZERO, Constants.ZERO, Constants.ZERO};  // [0]: first
        // player wins, [1]: second player wins, [2]: draw rounds
        for (int i = 2; i < this.rounds + 2; ++i) {
            Game game = new Game(i % 2 == 0 ? this.firstPlayer : this.secondPlayer,   i % 2 == 0 ?
                    this.secondPlayer : this.firstPlayer, size, winsStreak, this.renderer);
            Mark mark = game.run();

            switch (mark) {
                case BLANK:
                    playersCounterWinRounds[Constants.DRAW_ROUNDS_COUNTER]++;
                    break;
                case X: {
                    playersCounterWinRounds[i % 2]++; // i % 2 == 0 ? firstPlayer++ : secondPlayer++
                    break;
                }
                case O: {
                    playersCounterWinRounds[(i + 1) % 2]++; // (i + 1)%2 == 0 ? firstPlayer++ : secondPlayer++
                    break;
                }
            }
        }
        String fillFinalResultMsg = String.format(Constants.FINAL_RESULT_MSG,
                firstPlayer,
                playersCounterWinRounds[Constants.FIRST_PLAYER],
                secondPlayer,
                playersCounterWinRounds[Constants.SECOND_PLAYER],
                playersCounterWinRounds[Constants.DRAW_ROUNDS_COUNTER]);
        System.out.printf(fillFinalResultMsg);
    }

    /**
     * Main method to start the tournament simulation.
     *
     * @param args Command line arguments containing tournament parameters.
     *             args[0]: Number of rounds
     *             args[1]: Board size
     *             args[2]: Win streak required for victory
     *             args[3]: First player's name
     *             args[4]: Second player's name
     */
    public static void main(String[] args) {
        // Initialize player and renderer factories
        PlayerFactory playerFactory = new PlayerFactory();
        RendererFactory rendererFactory = new RendererFactory();

        // Parse command line arguments
        final int rounds = Integer.parseInt(args[Constants.ROUNDS_PARAMETER]);
        final int size = Integer.parseInt(args[Constants.SIZE_PARAMETER]);
        final int winStreak = Integer.parseInt(args[Constants.WIN_STREAK_PARAMETER]);
        final String[] playersNames = {
                args[Constants.FIRST_PLAYER_PARAMETER],
                args[Constants.SECOND_PLAYER_PARAMETER]
        };
        // Build renderer
        Renderer renderer = rendererFactory.buildRenderer(args[Constants.RENDER_MODE_PARAMETER], size);
        if (renderer == null) {
            System.out.println(Constants.UNKNOWN_RENDERER_NAME);
            return;
        }
        // Build first player
        Player firstPlayer = playerFactory.buildPlayer(playersNames[Constants.FIRST_PLAYER]);
        if (firstPlayer == null) {
            System.out.println(Constants.UNKNOWN_PLAYER_NAME);
            return;
        }
        // Build second player
        Player secondPlayer = playerFactory.buildPlayer(playersNames[Constants.SECOND_PLAYER]);
        if (secondPlayer == null) {
            System.out.println(Constants.UNKNOWN_PLAYER_NAME);
            return;
        }
        // Initialize and play the tournament
        Tournament tournament = new Tournament(rounds, renderer, firstPlayer, secondPlayer);
        tournament.playTournament(size, winStreak, playersNames[Constants.FIRST_PLAYER],
                playersNames[Constants.SECOND_PLAYER]);
    }
}