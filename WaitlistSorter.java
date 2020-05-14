import java.util.LinkedList;
import java.util.Queue;

/**
 * This class acts as part of the Waitlist program. This class is used to sort through the provided Queue waitlist.
 *
 * JDK version: 11.0.3
 *
 * @author  Kyrel Polifrone || 822711442 || CS108 Section 04
 * @version 1.0.0
 * @since   05-03-2020
 */

abstract class WaitlistSorter extends WaitlistSearcher {

    Queue<Entry> waitlistToSort;

    /**
     * Constructor which assigns the field waitlistToSort to the parameter, waitlist.
     * @param waitlist - non-sorted waitlist of type Queue, consisting of element Entry
     */
    WaitlistSorter(Queue<Entry> waitlist) {
        waitlistToSort = waitlist;

    }

    /**
     * Default constructor
     */
    WaitlistSorter() {
    }

    /**
     * Uses linear sorting in order to sort the Queue from highest priority to lowest priority
     * @param waitlistToSort - unsorted Queue with elements of type Entry
     * @return sortedWaitlist - a Queue with elements of type Entry sorted in order of highest priority to lowest
     * priority.
     */
    public Queue<Entry> sort(Queue<Entry> waitlistToSort) {
        Queue<Entry> sortedWaitlist = new LinkedList<>();
        Queue<Entry> waitlistDuplicate = new LinkedList<>(waitlistToSort);
        Queue<Entry> waitlistDuplicate2 = new LinkedList<>(waitlistToSort);
        /**
         * These 2 duplicate Queues of type Entry were created in order to copy the elements of the original Queue into
         * two arrays, one with the names of the Entry's and another with the priority associated with the Entry's.
        */

        int[] priorityArray = new int[waitlistToSort.size()];
        String[] nameArray = new String[waitlistToSort.size()]; // These are the arrays that will be sorted

        for (int i = 0; i < waitlistToSort.size(); ++i) {
            priorityArray[i] = waitlistDuplicate.poll().getPriority();
        } // Copies priority from the Queue into an array which can be sorted

        for (int i = 0; i < waitlistToSort.size(); ++i) {
            nameArray[i] = waitlistDuplicate2.poll().getName();
        } // Copies name from the Queue into an array which can be sorted

        // Loop which sorts the priorityArray from greatest to least
        int higherPriority;
        int tempVal;
        String tempString; // temp variables are only used for swapping purposes

        for (int i = 0; i < priorityArray.length - 1; ++i) {
            higherPriority = i;
            for (int j = i + 1; j < priorityArray.length; ++j) {

                if (priorityArray[j] > priorityArray[higherPriority]) {
                    higherPriority = j;
                }
            } // checks each element in the priorityArray, if it is higher, it is noted as the higherPriority

            tempVal = priorityArray[i];
            tempString = nameArray[i];
            priorityArray[i] = priorityArray[higherPriority];
            nameArray[i] = nameArray[higherPriority];
            priorityArray[higherPriority] = tempVal;
            nameArray[higherPriority] = tempString;
        } // the higher priority is swapped with the lower priority by using a temp variable
        /**
         * In order for the priorities to still be associated with the correct name, when the priorityArray is swapped,
         * so is the nameArray.
         */

        for (int i = 0; i < nameArray.length; i++) {
             sortedWaitlist.add(new Entry(nameArray[i], priorityArray[i]));
        } // following the sorting, the elements are added to a new Queue

        return sortedWaitlist;
    }

}
