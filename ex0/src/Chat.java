import java.util.Scanner;


import java.util.Scanner;

/**
 * The Chat class represents a simple chat application using two ChatterBot instances.
 * It allows the bots to respond to legal and illegal requests in an interactive manner.
 */
public class Chat {

    public static void main(String[] args) {

        // Illegal replies for the first bot
        String[] firstBotIllegalReplies = new String[]{
                "What is that mean " + ChatterBot.PLACEHOLDER_FOR_ILLEGAL_REQUEST + '!',
                "I don't want to say " + ChatterBot.PLACEHOLDER_FOR_ILLEGAL_REQUEST + ":)",
                "What do you mean !",
                "say say " + ChatterBot.PLACEHOLDER_FOR_ILLEGAL_REQUEST
        };

        // Illegal replies for the second bot
        String[] secondBotIllegalReplies = new String[]{
                "What is " +  ChatterBot.PLACEHOLDER_FOR_ILLEGAL_REQUEST +  " mean ?",
                "That's weird to say "  + ChatterBot.PLACEHOLDER_FOR_ILLEGAL_REQUEST + '!',
                "Whattt",
                "say I should say " + ChatterBot.PLACEHOLDER_FOR_ILLEGAL_REQUEST
        };

        // Legal replies for the first bot
        String[] firstBotLegalReplies = new String[]{
                "Okay, " + ChatterBot.PLACEHOLDER_FOR_REQUESTED_PHRASE,
                "looks weird but ok, " + ChatterBot.PLACEHOLDER_FOR_REQUESTED_PHRASE,
                ChatterBot.PLACEHOLDER_FOR_REQUESTED_PHRASE
        };

        // Legal replies for the second bot
        String[] secondBotLegalReplies = new String[]{
                "Alright, " + ChatterBot.PLACEHOLDER_FOR_REQUESTED_PHRASE,
                "That's it ? Okay," + ChatterBot.PLACEHOLDER_FOR_REQUESTED_PHRASE,
                ChatterBot.PLACEHOLDER_FOR_REQUESTED_PHRASE
        };

        // Create instances of ChatterBot
        ChatterBot firstBot = new ChatterBot("First bot", firstBotIllegalReplies, firstBotLegalReplies);
        ChatterBot secondBot = new ChatterBot("Second bot", secondBotIllegalReplies,
                secondBotLegalReplies);

        // Array of ChatterBot instances
        ChatterBot[] bots = new ChatterBot[]{firstBot, secondBot};

        // Initial statement
        String statement = "say Hello";

        // Scanner for user input to avoid infinity chat and giving the user opportunity to read the chat
        Scanner scanner = new Scanner(System.in);

        // Chat loop
        for (int i = 0; ; ++i) {
            // Display bot name and current statement
            System.out.print(bots[i % bots.length].getName() + ": " + statement);

            // Get and display bot's reply
            statement = bots[i % bots.length].replyTo(statement);

            // Wait for user input to continue the conversation
            scanner.nextLine();
        }
    }
}

