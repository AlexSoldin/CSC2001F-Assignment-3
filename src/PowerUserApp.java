import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class PowerUserApp {

    LinearHash linear;
    QuadraticHash quadratic;
    ChainingHash chaining;
    ArrayList<PowerUser> users = new ArrayList<PowerUser>();
    int keys;
    String[] searchKeys;
    int tableSize;

    public static void main(String[] args) {
        new PowerUserApp();
    }

    public PowerUserApp() {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter a prime number as the table size:");
        tableSize = in.nextInt();

        while(isPrime(tableSize)==false){
            System.out.println("Please enter a prime number as the table size");
            tableSize = in.nextInt();
        }

        System.out.println("Please choose from the following collision resolution schemes:\n" +
                "1) Linear Probing\n" +
                "2) Quadratic Probing\n" +
                "3) Chaining\n" +
                "4) Quit\n" +
                "0) Test - Linear vs. Quadratic vs. Chaining");

        int option = Integer.parseInt(in.next().charAt(0)+"");

        switch (option){
            case 1:
                linear = new LinearHash(tableSize);
                break;
            case 2:
                quadratic = new QuadraticHash(tableSize);
                break;
            case 3:
                chaining = new ChainingHash(tableSize);
                break;
            case 0:
                linear = new LinearHash(tableSize);
                quadratic = new QuadraticHash(tableSize);
                chaining = new ChainingHash(tableSize);
                break;
            default:
                System.out.println("Please enter the collision resolution scheme");
        }

        System.out.println("Enter the name of the data file:");
        String file = in.next();
        //String file = "cleaned_data.csv";
        readDataCSV(file);

        System.out.println("Please enter the number of search keys");
        keys = in.nextInt();
        //keys = 400;
        GenerateSearchItems();

        if(option==0){
            InsertMethod(1);
            SearchMethod(1);
            InsertMethod(2);
            SearchMethod(2);
            InsertMethod(3);
            SearchMethod(3);
        }
        else
            InsertMethod(option);
            SearchMethod(option);
    }

    /**
     * Searches through K number of items for each collision resolution scheme
     * @param option whether or not to add using linear, quadratic or chaining (in menu above)
     */
    public void SearchMethod(int option) {
        switch (option) {
            case 1:
                for (int i = 0; i < keys; i++) {
                    linear.SearchLinear(searchKeys[i]);
                }
                System.out.println("Search Probes:\t" + linear.getSumSearchProbes());
                System.out.println("Average Number Probes:\t" + linear.averageNumberOfProbes());
                System.out.println("Max Search Probes:\t" + linear.getMaxSearches());
                break;
            case 2:
                for (int i = 0; i < keys; i++) {
                    quadratic.SearchQuadratic(searchKeys[i]);
                }
                System.out.println("Search Probes:\t" + quadratic.getSumSearchProbes());
                System.out.println("Average Number Probes:\t" + quadratic.averageNumberOfProbes());
                System.out.println("Max Search Probes:\t" + quadratic.getMaxSearches());
                break;
            case 3:
                for (int i = 0; i < keys; i++) {
                    chaining.SearchChaining(searchKeys[i]);
                }
                System.out.println("Search Probes:\t" + chaining.getSumSearchProbes());
                System.out.println("Average Number Probes:\t" + chaining.averageNumberOfProbes());
                System.out.println("Max Search Probes:\t" + chaining.getMaxSearches());
                break;
            case 4:
                break;
    }        }

    /**
     * Adds a new key value pair to the hash map
     * @param option whether or not to add using linear, quadratic or chaining (in menu above)
     *               
     */
    public void InsertMethod(int option) {
        switch (option) {
            case 1:
                for (int i = 0; i < users.size(); i++) {
                    linear.Linear(users.get(i).getDateTime(), users.get(i));
                    if (i >= tableSize - 1) {
                        System.out.println("Failure\nThe table is full\n");
                        break;
                    }
                }
                System.out.println("Linear Probing");
                System.out.println("Load Factor:\t" + linear.LoadFactor());
                System.out.println("Insert Probes:\t" + linear.getInsertProbes());
                break;
            case 2:

                for (int i = 0; i < users.size(); i++) {
                    quadratic.Quadratic(users.get(i).getDateTime(), users.get(i));
                    if (quadratic.getInsertProbes() >= tableSize - 1) {
                        System.out.println("\nFailure\nThe number of probes exceeded the table size\n");
                        break;
                    }
                }
                System.out.println("\nQuadratic Probing");
                System.out.println("Load Factor:\t" + quadratic.LoadFactor());
                System.out.println("Insert Probes:\t" + quadratic.getInsertProbes());
                break;
            case 3:
                for (int i = 0; i < users.size(); i++) {
                    chaining.Chaining(users.get(i).getDateTime(), users.get(i));
                }
                System.out.println("\nChaining");
                System.out.println("Load Factor:\t" + chaining.LoadFactor());
                System.out.println("Insert Probes:\t" + chaining.getInsertProbes());
                break;
            case 4:
                break;
            default:
                System.out.println("Please choose a valid collision resolution scheme");
        }
    }

    /**
     * Reads the CSV file and populates users
     * @param file the file name to hash
     */
    public void readDataCSV(String file) {
        CSVUtility.readUsers(user -> {
            users.add(user);
        }, file);
    }

    /**
     * Checks for whether the table size is a prime number
     * @param size number to be checked for a prime number
     */
    public boolean isPrime(int size){
        boolean flag = false;
        for(int i = 2; i <= size/2; ++i){
            if(size % i == 0)
            {
                flag = true;
                break;
            }
        }
        return !flag;
    }

    /**
     * Generates a set of random keys to search for
     */
    public void GenerateSearchItems(){
        Collections.shuffle(users);
        searchKeys = new String[keys];
        for (int i = 0; i < keys ; i++) {
            searchKeys[i] = users.get(i).getDateTime();
        }
    }

    /**
     * Testing that the search function works
     * @param dateTime dateTime to be searched for using either probing method
     */
    public void test(String dateTime) {
        //PowerUser user = linear.SearchLinear(dateTime);
        PowerUser user = quadratic.SearchQuadratic(dateTime);
        //PowerUser user = chaining.SearchChaining(dateTime);
        if (user == null) {
            System.out.println("Power user not found");
        } else {
            System.out.println(user.toString());
        }

    }
}
