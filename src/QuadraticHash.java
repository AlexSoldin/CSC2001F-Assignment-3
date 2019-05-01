public class QuadraticHash {

    private static int tableSize;
    private static int elements = 0;
    private static int insertProbes = 0;
    private static int searchProbes = 0;
    private static int sumSearchProbes = 0;
    private static int maxSearchProbes = 0;

    private HashElement[] table;

    public QuadraticHash(int tableSize) {
        this.tableSize = tableSize;
        table = new HashElement[tableSize];
        for (int i = 0; i < tableSize; i++) {
            table[i] = null;
        }
    }

    /**
     * Gets the value of the key using a Quadratic Probing method
     * @param dateTime the dateTime that is being searched for
     */
    public PowerUser SearchQuadratic(String dateTime){
        searchProbes = 0;
        int hash = hashCode(dateTime);
        int count = 1;
        while(table[hash] != null && !table[hash].getDateTime().equals(dateTime)){
            hash = (hash+(count<<1)-1)%tableSize;
            count++;
            searchProbes++;
        }
        checkMax(searchProbes);
        sumSearchProbes += searchProbes;
        if(table[hash] == null){
            return null;
        }
        else{
            return table[hash].getUser();
        }
    }

    /**
     * Adds a new key value pair to the hash map
     * @param dateTime the non-unicode value of the key
     * @param user the user object related to the dateTime
     */
    public void Quadratic(String dateTime, PowerUser user){
        int hash = hashCode(dateTime);
        int count = 1;
        while (table[hash] != null && !table[hash].getDateTime().equals(dateTime)) {
            hash = (hash + (count << 1) - 1) % tableSize;
            count++;
            insertProbes++;
        }
        table[hash] = new HashElement(dateTime, user);
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
