import java.util.Queue;

/**
 * This interface acts as part of the Waitlist program. This interface defines the search method
 *
 * JDK version: 11.0.3
 *
 * @author  Kyrel Polifrone || 822711442 || CS108 Section 04
 * @version 1.0.0
 * @since   05-03-2020
 */

public interface Searcher<E> {

    E search(Queue<E> waitlistToSearch, int key);
}
