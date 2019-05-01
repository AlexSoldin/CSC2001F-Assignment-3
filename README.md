# CSC2001F-Assignment-3

A separate class was created for each hash method - LinearHash, QuadraticHash, ChainingHash.

A CSV utility is used to read in the data from the data file (.csv) provided. This is used to create an array of PowerUser objects (dateTime, power, voltage) in the PowerUserApp.

The PowerUserApp provides an interface to test each method seperately (option 1, 2 or 3) or to compare the performance of each probing method (option 0). 

When an option is chosen, both the insertion and search data will be displayed.
For each insertion case, the load factor and total number of insert probes will be displayed. 
For each search case, the total number of search probes, the average number of search probes and the maximum sequence of search probes will be displayed.

For more accurate results, the program prompts the user to enter the number of search keys required. This number will be used in the GenerateSearches method to randomise the keys and add them to a new array that will then be used to search through the Hash Table.
