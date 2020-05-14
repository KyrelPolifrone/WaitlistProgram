import java.util.ArrayList;

/**
 * This class acts as part of the Waitlist program. This class deals with the creation of an Entry, which is an element
 * inserted into the Queue waitlist.
 *
 * JDK version: 11.0.3
 *
 * @author  Kyrel Polifrone || 822711442 || CS108 Section 04
 * @version 1.0.0
 * @since   05-03-2020
 */

class Entry {
    private String name;
    private int priority;

    /**
     * Constructor with all parameters to define an Entry provided
     * @param name
     * @param priority
     */
    Entry(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    /**
     * Constructor if priority is not specified, sets priority to zero
     * @param name
     */
    Entry(String name) {
        this.name = name;
        priority = 0;
    }

    /**
     * Getter for Entry's name
     * @return name - the name of the Entry
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for Entry's priority
     * @return priority - the priority of the Entry
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Getter for all the data associated with an Entry. It creates an ArrayList to store the data that is held in an
     * Entry.
     * @return String representation of the data in an Entry
     */
    public String getData() {
        ArrayList<String> dataArrayList = new ArrayList<>();
        dataArrayList.add(getName());
        dataArrayList.add(Integer.toString(getPriority()));

        return dataArrayList.toString();
    }

}
