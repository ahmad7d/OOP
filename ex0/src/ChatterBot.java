import java.util.*;

/**
 * Base file for the ChatterBot exercise.
 * The bot's replyTo method receives a statement.
 * If it starts with the constant REQUEST_PREFIX, the bot returns
 * whatever is after this prefix. Otherwise, it returns one of
 * a few possible replies as supplied to it via its constructor.
 * In this case, it may also include the statement after
 * the selected reply (coin toss).
 *
 * @author Dan Nirel
 */
/**
 * The ChatterBot class represents a simple chatterbot that responds to legal and illegal requests.
 * Legal requests start with the specified request prefix, while illegal requests do not.
 * The bot generates random replies based on predefined patterns for both types of requests.
 */
public class ChatterBot {

    /** The name of the chatterbot. */
    public String name;

    /** The prefix indicating a legal request. */
    static final String REQUEST_PREFIX = "say ";

    /** Placeholder for the requested phrase in legal requests. */
    static final String PLACEHOLDER_FOR_REQUESTED_PHRASE = "<phrase>";

    /** Placeholder for illegal requests. */
    static final String PLACEHOLDER_FOR_ILLEGAL_REQUEST = "<request>";

    /** Random number generator for selecting random patterns. */
    Random rand = new Random();

    /** Array of replies to illegal requests. */
    String[] repliesToIllegalRequest;

    /** Array of replies to legal requests. */
    String[] legalRequestsReplies;

    /**
     * Constructor for the ChatterBot class.
     *
     * @param name                   The name of the chatterbot.
     * @param repliesToIllegalRequest An array of replies to illegal requests.
     * @param repliesToLegalRequests  An array of replies to legal requests.
     */
    ChatterBot(String name, String[] repliesToIllegalRequest, String[] repliesToLegalRequests) {
        this.name = name;

        // Copy replies to illegal requests
        this.repliesToIllegalRequest = new String[repliesToIllegalRequest.length];
        for (int i = 0; i < repliesToIllegalRequest.length; i++) {
            this.repliesToIllegalRequest[i] = repliesToIllegalRequest[i];
        }

        // Copy replies to legal requests
        this.legalRequestsReplies = new String[repliesToLegalRequests.length];
        for (int i = 0; i < repliesToLegalRequests.length; i++) {
            this.legalRequestsReplies[i] = repliesToLegalRequests[i];
        }
    }

    /**
     * Generates a random reply to a legal request by replacing the placeholder in a random pattern.
     *
     * @param statement The legal request statement.
     * @return A randomly generated reply.
     */
    String replyToLegalRequest(String statement) {
        return replacePlaceholderInARandomPattern(
                legalRequestsReplies,
                PLACEHOLDER_FOR_REQUESTED_PHRASE,
                statement.replaceAll(REQUEST_PREFIX, "")
        );
    }

    /**
     * Generates a reply to a given statement, whether it is a legal or illegal request.
     *
     * @param statement The input statement.
     * @return A randomly generated reply.
     */
    String replyTo(String statement) {
        if (statement.startsWith(REQUEST_PREFIX)) {
            return replyToLegalRequest(statement);
        }
        return replacePlaceholderInARandomPattern(
                repliesToIllegalRequest,
                PLACEHOLDER_FOR_ILLEGAL_REQUEST,
                statement
        );
    }

    /**
     * Replaces a placeholder in a random pattern from the given array of patterns.
     *
     * @param patterns    The array of patterns to choose from.
     * @param placeholder The placeholder to be replaced.
     * @param replacement The string to replace the placeholder with.
     * @return The modified pattern with the placeholder replaced.
     */
    String replacePlaceholderInARandomPattern(String[] patterns, String placeholder, String replacement) {
        int randomIndex = rand.nextInt(patterns.length);
        String reply = patterns[randomIndex];
        return reply.replaceAll(placeholder, replacement);
    }

    /**
     * Gets the name of the chatterbot.
     *
     * @return The name of the chatterbot.
     */
    String getName() {
        return this.name;
    }
}

