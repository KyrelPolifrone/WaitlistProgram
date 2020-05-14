import java.util.LinkedList;
import java.util.Queue;

/**
 * This class acts as part of the Waitlist program. This class is searches through the waitlist and prints to the Terminal
 * the Entry with the priority specified by the key.
 *
 * JDK version: 11.0.3
 *
 * @author  Kyrel Polifrone || 822711442 || CS108 Section 04
 * @version 1.0.0
 * @since   05-03-2020
 */

public class WaitlistSearcher implements Searcher<Entry> {

    public int key; // priority which will be searched for in Queue
    public int[] priorityArray;
    public String[] nameArray;

    /**
     * This method starts the searching by instantiating the fields in order for the binary search to function correctly.
     * The searching is split into two methods in order to avoid the fields being instantiated every time the method is
     * recurred.
     * @param waitlistToSearch - sorted waitlist which will be searched
     * @param key - value which will be searched for against the priorities in the Queue
     * @return person - returns an Entry with the data of the element that was being searched for
     */
    @Override
    public Entry search(Queue<Entry> waitlistToSearch, int key) {
        Entry person;
        this.key = key;
        Queue<Entry> waitlistDuplicate = new LinkedList<>(waitlistToSearch);
        Queue<Entry> waitlistDuplicate2 = new LinkedList<>(waitlistToSearch);
        /**
         * These two duplicate waitlists are created in order to convert the Queue into two arrays, one with the names,
         * and another with the priorities. By creating arrays from the Queue waitlist, it makes it possible to search
         * throughout the array which is not possible with a Queue.
         */

        priorityArray = new int[waitlistToSearch.size()];
        nameArray = new String[waitlistToSearch.size()];

        for (int i = 0; i < waitlistToSearch.size(); ++i) {
            priorityArray[i] = waitlistDuplicate.poll().getPriority();
        }

        for (int i = 0; i < waitlistToSearch.size(); ++i) {
            nameArray[i] = waitlistDuplicate2.poll().getName();
        } // It was necessary to have 2 duplicate waitlists or else the poll() from the first one would effect the creation of this one.

        person = binarySearch(0,0,priorityArray.length - 1); // Once the fields are instantiated, the binarySearch is called.

        return person;
    }

    /**
     * Searches for an element in the priority array which matches the key. Returns Entry with name and priority if found,
     * if not, returns NULL and priority of -1.
     * @param low - low end of range searched in binary search
     * @param mid - the index of the element being compared to the key
     * @param high - high end of range searched in binary search
     * @return returns an Entry with the given specified priority, if it exists. If not, null is returned.
     */
    static Entry output = new Entry("NULL", -1); // initializing output in the event that search is not found
    private Entry binarySearch(int low, int mid, int high) {
        int prevMid = mid;

        if (high >= low) {
            mid = (high + low) / 2;
            if (priorityArray[mid] < key) {
                high = mid;
                prevMid = mid;
                mid = ((high - low) / 2) + low;

                if (prevMid != mid) {
                    binarySearch(low, mid, high); // recursive call to redo method if Entry is not found
                }

            } else if (priorityArray[mid] > key) {
                low = mid;
                prevMid = mid;
                mid = ((high - low) / 2) + low;

                if (prevMid != mid) {
                    binarySearch(low, mid, high); // recursive call to redo method if Entry is not found
                }

            } else {
                output = new Entry(nameArray[mid], priorityArray[mid]);
                return output;
            }
            
        }
        return output;
    }
}

