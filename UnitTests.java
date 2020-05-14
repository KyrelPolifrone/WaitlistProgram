import java.util.Queue;

/**
 * This class acts as part of the Waitlist program. This class is used to test each method in the program.
 *
 * JDK version: 11.0.3
 *
 * @author  Kyrel Polifrone || 822711442 || CS108 Section 04
 * @version 1.0.0
 * @since   05-03-2020
 */

public class UnitTests {
    public static void main(String[] args) {
        getNameTest();
        getPriorityTest();
        getDataTest();
        addTest();
        getWaitlistTest();
        sortTest();
        getSortedWaitlistTest();
        searchTest();
        waitlistToFileTest();
    }


    /**
     * Tests the getName method by creating a new Entry and attempting to get the name from it. If the name returned is
     * incorrect, an exception is thrown and the test fails.
     */
    private static void getNameTest() {
        Entry tester = new Entry("testName",-1);
        try {
            if (!tester.getName().equals("testName")) {
                throw new Exception();
            }
            System.out.println("SUCCESS: getName()");
        } catch (Exception e) {
            System.out.println("FAILED: getName(). Expected results 'testName', received: " + tester.getName());
        }
    }


    /**
     * Tests the getPriority method by creating a new Entry and attempting to get the priority from it. If the priority
     * returned is incorrect, an exception is thrown and the test fails.
     */
    private static void getPriorityTest() {
        Entry tester = new Entry("testPriority",-1);
        try {
            if (tester.getPriority() != -1) {
                throw new Exception();
            }
            System.out.println("SUCCESS: getPriority()");
        } catch (Exception e) {
            System.out.println("FAILED: getPriority(). Expected results '-1', received: " + tester.getPriority());
        }
    }

    /**
     * Tests the getData method by creating a new Entry and attempting to get the data from it. If the data returned
     * is incorrect, an exception is thrown and the test fails.
     */
    private static void getDataTest() {
        Entry tester = new Entry("testData",-1);
        try {
            if (!tester.getData().equals("[testData, -1]")) {
                throw new Exception();
            }
            System.out.println("SUCCESS: getData()");
        } catch (Exception e) {
            System.out.println("FAILED: getData(). Expected results '[testData, -1]', received: " + tester.getData());
        }
    }

    /**
     * Tests the add method by creating a new Entry and attempting to add it to a waitlist. If the waitlist's length
     * does not change, an exception is thrown and the test fails.
     */
    private static void addTest() {
        WaitlistBuilder<Entry> testWaitlistAdd = new WaitlistBuilder<>();
        Entry testAdd = new Entry("testAdd",-1);
        testWaitlistAdd.add(testAdd);
        try {
            if (testWaitlistAdd.getWaitlist().size() != 1) {
                throw new Exception();
            }
            System.out.println("SUCCESS: add()");
        } catch (Exception e) {
            System.out.print("FAILED: add(). Expected results '1', received: ");
            System.out.println(testWaitlistAdd.getWaitlist().size());
        }
    }

    /**
     *  Tests the getWaitlist method by creating a new Entry, adding it to a waitlist, then trying to receive the data
     *  from the waitlist by using the getWaitlist method. If the data returned is not correct, an exception is thrown
     *  and the test fails.
     */
    private static void getWaitlistTest() {
        WaitlistBuilder<Entry> testGetWaitlist = new WaitlistBuilder<>();
        Entry testGet = new Entry("testGet",-1);
        testGetWaitlist.add(testGet);
        String data = testGetWaitlist.getWaitlist().poll().getData();
        try {
            if (!data.equals("[testGet, -1]")) {
                throw new Exception();
            }
            System.out.println("SUCCESS: getWaitlistTest()");
        } catch (Exception e) {
            System.out.print("FAILED: getWaitlistTest(). Expected results '[testGet, -1]', received: ");
            System.out.println(data);
        }
    }

    /**
     * Tests the sort method by creating three new Entry objects, adding them to a waitlist, calling the sort method,
     * then checking if the elements in the new sorted waitlist are in the correct order based on priority. If the
     * elements are not sorted in descending order based on priority, an exception is thrown and the test fails.
     */
    private static void sortTest() {
        Queue<Entry> sortedWaitlist;
        WaitlistBuilder<Entry> waitlist = new WaitlistBuilder<>();

        Entry sort1 = new Entry("Element1",4);
        Entry sort2 = new Entry("Element2",6);
        Entry sort3 = new Entry("Element3",1);
        waitlist.add(sort1);
        waitlist.add(sort2);
        waitlist.add(sort3);

        sortedWaitlist = waitlist.sort(waitlist.getWaitlist());

        int[] priorityArray = new int[sortedWaitlist.size()];

        for (int i = 0; i < sortedWaitlist.size(); ++i) {
            priorityArray[i] = sortedWaitlist.poll().getPriority();
        } // stores the priorities from the sorted waitlist in an array

        String priorityArrayString = priorityArray[0] +"";
        for (int i = 1; i < priorityArray.length; i++) {
            priorityArrayString = priorityArrayString + " " + priorityArray[i];
        }  // converts the array of priorities into a String

        try {
            if (!priorityArrayString.equals("6 4 0")) {
                throw new Exception();
            } // checks if the String array is in the proper order, descending order.
            System.out.println("SUCCESS: sortTest()");
        } catch (Exception e) {
            System.out.println("FAILED: sortTest(). Expected results '6 4 0', received: " + priorityArrayString);
        }

    }

    /**
     * Tests the getSortedWaitlist method by creating three new Entry objects, adding them to a waitlist, calling the
     * sort method, then comparing the results of the getSortedWaitlist method. If the returned sortedWaitlist is
     * incorrect, an exception is thrown and the test fails.
     */
    private static void getSortedWaitlistTest() {
        Queue<Entry> testGetSortedWaitlist;
        WaitlistBuilder<Entry> waitlist = new WaitlistBuilder<>();
        Entry get1 = new Entry("get1", 3);
        Entry get2 = new Entry("get2", 1);
        Entry get3 = new Entry("get3", 2);
        waitlist.add(get1);
        waitlist.add(get2);
        waitlist.add(get3);
        waitlist.sort(waitlist.getWaitlist());
        testGetSortedWaitlist = waitlist.getSortedWaitlist();

        String sortedWaitlistString = testGetSortedWaitlist.poll().getData() + " ";
        int loopCount = testGetSortedWaitlist.size();
        for (int i = 0; i < loopCount; i++) {
            sortedWaitlistString += testGetSortedWaitlist.poll().getData() + " ";
        } // Converts the data from the getSortedWaitlist method into a String so it can be easily compared to

        try {
            if (!sortedWaitlistString.contains("[get1, 3] [get3, 2] [get2, 1]")) {
                throw new Exception();
            } // Compares the sortedWaitlistString to the String expected
            System.out.println("SUCCESS: getSortedWaitlistTest()");
        } catch (Exception e) {
            System.out.println("FAILED: getSortedWaitlistTest(). Expected results '[get1, 3] [get3, 2] [get2, 1]', received: " + sortedWaitlistString);
        }
    }

    /**
     * Tests the search method by creating three new Entry objects, adding them to a waitlist, sorting the waitlist,
     * then trying to search for one of the elements in the waitlist. If the incorrect entry is returned, an exception
     * is thrown and the test fails.
     */
    private static void searchTest() {
        Queue<Entry> testSearch;
        WaitlistBuilder<Entry> waitlist = new WaitlistBuilder<>();
        Entry search1 = new Entry("search1", 3);
        Entry search2 = new Entry("search2", 1);
        Entry search3 = new Entry("search3", 2);
        waitlist.add(search1);
        waitlist.add(search2);
        waitlist.add(search3);

        testSearch = waitlist.sort(waitlist.getWaitlist());
        Entry searched = waitlist.search(testSearch, 2);

        try {
            if (!searched.getData().equals("[search3, 2]")) {
                throw new Exception();
            } // tests if the data returned by the search method is the expected data.
            System.out.println("SUCCESS: searchTest()");
        } catch (Exception e) {
            System.out.println("FAILED: searchTest(). Expected results '[search3, 2]', received: " + searched.getData());
        }
    }

    /**
     * Tests the waitlistToFile method by creating three new Entry objects, adding them to a waitlist, sorting the
     * waitlist, then attempting to print the original, and sorted waitlist, to a file. If the waitlist to file does not
     * throw an exception, the test passes.
     */
    private static void waitlistToFileTest() {
        Queue<Entry> testToFile;
        Queue<Entry> sortedWaitlist;
        WaitlistBuilder<Entry> waitlist = new WaitlistBuilder<>();
        Entry search1 = new Entry("toFile1", 3);
        Entry search2 = new Entry("toFile2", 1);
        Entry search3 = new Entry("toFile3", 2);
        waitlist.add(search1);
        waitlist.add(search2);
        waitlist.add(search3);

        sortedWaitlist = waitlist.sort(waitlist.getWaitlist());

        try {
            waitlist.waitlistToFile();
            System.out.println("SUCCESS: waitlistToFileTest()");
            // As long as no exception is thrown, the waitlistToFile method is expected to work.
        } catch (Exception e) {
            System.out.println("FAILED: waitlistToFileTest(). Input or Output defined incorrectly");
            e.printStackTrace();
        }
    }
}
