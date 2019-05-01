/*
Power User class that will create a single power user and store the values of dateTime, power and voltage
Both default and parameterised constructors
All getters and setters as well as a toString
 */
public class PowerUser implements Comparable<PowerUser>{
    private String dateTime, power, voltage;

    public PowerUser(String dateTime, String power, String voltage){
        this.dateTime = dateTime;
        this.power = power;
        this.voltage = voltage;
    }

    public PowerUser(){

    }

    public String getDateTime() {
        return dateTime;
    }

    public String getPower() {
        return power;
    }

    public String getVoltage() {
        return voltage;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }

    public String toString(){
        String out = "Date/Time: "+dateTime+"\nPower: "+power+"\nVoltage: "+voltage+"\n";
        return out;
    }

    public int compareTo(PowerUser other){
        return dateTime.compareTo(other.dateTime);
    }
}
