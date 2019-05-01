import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class CSVUtility {

    public interface UserListener {
        public void powerUserRead(PowerUser user);
    }


    public static void readUsers(UserListener userListener, String file) {

        final String csvComma = ",";
        String line = "";


        try (BufferedReader bReader = new BufferedReader(new FileReader(file))) {

            line = bReader.readLine();
            line = bReader.readLine();

            while (line != null) {

                String[] details = line.split(csvComma);
                PowerUser user = new PowerUser(details[0],details[1],details[3]);
                if (user != null) {
                    userListener.powerUserRead(user);
                }
                line = bReader.readLine();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static PowerUser extractUser(String[] userDetails) {
        PowerUser user;
        if(userDetails.length >= 3) {


            String dateTime = userDetails[0];
            String power;
            String voltage;
            if(userDetails[1] != null && !userDetails[1].isEmpty()) {
                power = userDetails[1];
            }else{
                return null;
            }
            if(userDetails[3] != null && !userDetails[2].isEmpty()) {
                voltage = userDetails[3];
            }else{
                return null;
            }
            user = new PowerUser(dateTime, power, voltage);


        }else{
            return null;
        }
        return user;
    }
}
