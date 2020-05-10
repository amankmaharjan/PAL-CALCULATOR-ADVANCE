

/**
  * file name: User.java
 * @author aman & damodar
 * This file stores the user information
 */
// class to store the user information
public class User {
// fields to store the user information
    private String name;
    private String gender;
    private String ageGroup;
    private double height;
    private double weight;

    // constructor
    public User() {
    }

    // Parameterised consturctor
    public User(String name, String gender, String ageGroup, double height, double weight) {
        this.name = name;
        this.gender = gender;
        this.ageGroup = ageGroup;
        this.height = height;
        this.weight = weight;
    }
 //Getter and setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "User{" + "name=" + name + ", gender=" + gender + ", ageGroup=" + ageGroup + ", height=" + height + ", weight=" + weight + '}';
    }

    
}
