import java.util.ArrayList;

public class ChainingHash {

    private static int tableSize;
    private static int elements = 0;
    private static int insertProbes = 0;
    private static int searchProbes = 0;
    private static int sumSearchProbes = 0;
    private static int maxSearchProbes = 0;

    private ArrayList<HashElement>[] chainTable;

    public ChainingHash(int tableSize) {
        this.tableSize = tableSize;
        chainTable = new ArrayList[tableSize];
        for (int i = 0; i < tableSize; i++) {
            chainTable[i] = null;
        }
    }

    /**
     * Gets the value of the key using a Chaining Probing method
     * @param dateTime the non-unicode value of the key
     */
    public PowerUser SearchChaining(String dateTime){
        searchProbes = 0;
        PowerUser user = new PowerUser();
        int hash = hashCode(dateTime);
        if(chainTable[hash] == null){
            return null;
        }
        else{
            for (int i = 0; i < chainTable[hash].size(); i++) {
                if(chainTable[hash].get(i).getDateTime().equals(dateTime)){
                    user = chainTable[hash].get(i).getUser();
                    break;
                }
                searchProbes++;
            }
            checkMax(searchProbes);
            sumSearchProbes += searchProbes;
        }
        return user;
    }

    /**
     * Adds a new key value pair to the hash map
     * @param dateTime the non-unicode value of the key
     * @param user the user object related to the dateTime
     */
    public void Chaining(String dateTime, PowerUser user){
        int hash = hashCode(dateTime);
        if (chainTable[hash] == null) {
            chainTable[hash] = new ArrayList<HashElement>();
        }
        insertProbes += chainTable[hash].size();
        chainTable[hash].add(new HashElement(dateTime, user));
        elements++;
    }

    /**
     * Adds a new key value pair to the hash map
     * @param dateTime this is converted to unicode
     */
    public int hashCode(String dateTime){
        int hashVal = 0;
        for(int i = 0; i < dateTime.length(); i++) {
            hashVal = 37 * hashVal + dateTime.charAt(i);
        }
        return Math.abs(hashVal % tableSize);
    }

    public double LoadFactor(){
        double value = elements/(1.0*tableSize);
        return Math.round(value*100)/100.0;
    }

    public int getInsertProbes(){
        return insertProbes;
    }

    public int getSearchProbes(){
        return searchProbes;
    }

    public double averageNumberOfProbes(){
        double value = sumSearchProbes/(1.0*elements);
        return Math.round(value*100)/100.0;
    }

    public void checkMax(int num){
        maxSearchProbes = Math.max(maxSearchProbes, num);
    }

    public int getMaxSearches(){
        return maxSearchProbes;
    }

    public int getSumSearchProbes(){
        return  sumSearchProbes;
    }

    public int getElements(){
        return elements;
    }

    private class HashElement {
        private String dateTime;
        private PowerUser user;

        public HashElement(String dateTime, PowerUser user) {
            this.dateTime = dateTime;
            this.user = user;
        }

        public String getDateTime() {
            return dateTime;
        }

        public PowerUser getUser() {
            return user;
        }

        public void setDateTime(String dateTime) {
            this.dateTime = dateTime;
        }

        public void setUser(PowerUser user) {
            this.user = user;
        }
    }
}
