import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

/**
 * This class acts as part of the Waitlist program. This class creates the waitlist and has appropriate methods that can
 * be called on the waitlist.
 *
 * JDK version: 11.0.3
 *
 * @author  Kyrel Polifrone || 822711442 || CS108 Section 04
 * @version 1.0.0
 * @since   05-03-2020
 */

public class WaitlistBuilder<E> extends WaitlistSorter {

    Queue<Entry> waitlistBuild = new LinkedList<>();
    Queue<Entry> sortedWaitlist; // This Queue is used for when original waitlist is sorted.

    /**
     * Getter for the waitlist
     * @return waitlistBuild - returns the currently build waitlist, NOT sorted!
     */
    public Queue<Entry> getWaitlist() {
        return waitlistBuild;
    }

    /**
     * Getter for the waitlist
     * @return sortedWaitlist - returns the currently build sorted waitlist.
     */
    public Queue<Entry> getSortedWaitlist() {
        return sortedWaitlist;
    }

    /**
     * Adds an Entry to the end of the Queue waitlistBuild
     * @param entry - The Entry to be added into the waitlist
     */
    public void add(Entry entry) {
        waitlistBuild.add(entry);
    }

    /**
     * Prints both the original, non-sorted waitlist, followed by the sorted waitlist into a file
     * @throws FileNotFoundException - If output file does not exist
     */
    public void waitlistToFile() throws FileNotFoundException {
        try (PrintWriter fileOut = new PrintWriter("output.txt")) {
            fileOut.println("Original Waitlist: ");
            int i = 1;
            for (Entry entry : waitlistBuild) {
                fileOut.println(i + ": " + entry.getData());
                i++;
            }

            fileOut.println();

            fileOut.println("Sorted Waitlist: ");
            int j = 1;
            for (Entry entry : sortedWaitlist) {
                fileOut.println(j + ": " + entry.getData());
                j++;
            }
        }
    }

    /**
     * Sorts the Queue waitlistBuild by calling the sort method of WaitlistSorter
     * @param waitlistBuild - Queue that needs to be sorted
     * @return sortedWaitlist - returns a new Queue which is a sorted version of the inserted waitlist.
     */
    @Override
    public Queue<Entry> sort(Queue<Entry> waitlistBuild) {
        sortedWaitlist = super.sort(waitlistBuild);
        return sortedWaitlist;
    }

    /**
     * Searches the sortedWaitlist for an Entry with a certain priority
     * @param waitlistToSearch - the waitlist which will be searched
     * @param key - the priority number to search for in the waitlist.
     * @return
     */
    @Override
    public Entry search(Queue<Entry> waitlistToSearch, int key) {
        return super.search(sortedWaitlist, key);
    }


}
