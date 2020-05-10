
/**
 *file name: BMRdata.java
 *
 * @author aman & damodar
 * This file is used to store the BMR data value from the file
 *
 */
// stores the BMR data for the  particular  age group,gender, height and weight
public class BMRdata {

    // fields to store the age group,gender,height and weight
    private String ageGroup;
    private String gender;
    private double height;
    private double weight;
    private double bmr;
    public BMRdata() {
    }
    //   constructor  to initialise BMR fields
    public BMRdata(String ageGroup, String gender, double height, double weight, double bmr) {
        this.ageGroup = ageGroup;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.bmr = bmr;
    }

    //Getter
    public String getAgeGroup() {
        return ageGroup;
    }

    //Setter
    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }
    //Getter
    public String getGender() {
        return gender;
    }
    //Setter
    public void setGender(String gender) {
        this.gender = gender;
    }

    //Getter
    public double getHeight() {
        return height;
    }

    //Setter
    public void setHeight(double height) {
        this.height = height;
    }

    //Getter
    public double getWeight() {
        return weight;
    }

    //Setter
    public void setWeight(double weight) {
        this.weight = weight;
    }
    //Getter

    public double getBmr() {
        return bmr;
    }
    //Setter

    public void setBmr(double bmr) {
        this.bmr = bmr;
    }

    @Override
    public String toString() {
        return "BMRdata{" + "ageGroup=" + ageGroup + ", gender=" + gender + ", height=" + height + ", weight=" + weight + ", bmr=" + bmr + '}';
    }
}
