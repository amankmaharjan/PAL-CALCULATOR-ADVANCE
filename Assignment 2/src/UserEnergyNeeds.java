
/**
 * file name: UserEnergyNeeds.java
 * @author aman 
 * This file stores the average weight, Bmrdata and Pal data of the user
 * 
 */
// class to store the BMR, Paldata of user
public class UserEnergyNeeds extends User {

//    Fields for the  user engery information
    private double avgWt;
    private BMRdata bmr;
    private PALdata paldata;

    //constructor
    public UserEnergyNeeds() {
    }

    // paramaterised constructor
    public UserEnergyNeeds(double avgWt, BMRdata bmr, PALdata paldata, String name, String gender, String ageGroup, double height, double weight) {
        super(name, gender, ageGroup, height, weight);
        this.avgWt = avgWt;
        this.bmr = bmr;
        this.paldata = paldata;
    }
// Getter and Setter to access fields
    public double getAvgWt() {
        return avgWt;
    }

    public void setAvgWt(double avgWt) {
        this.avgWt = avgWt;
    }

    public BMRdata getBmr() {
        return bmr;
    }

    public void setBmr(BMRdata bmr) {
        this.bmr = bmr;
    }

    public PALdata getPaldata() {
        return paldata;
    }

    public void setPaldata(PALdata paldata) {
        this.paldata = paldata;
    }

    @Override
    public String toString() {
        return "UserEnergyNeeds{" + "avgWt=" + avgWt + ", bmr=" + bmr + ", paldata=" + paldata + '}';
    }

}
