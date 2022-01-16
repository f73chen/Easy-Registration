package chengrace_afterschool;

/**
 * Represents a student, an extension of a person
 *
 * @author Grace Chen
 */
public class Student extends Person {

    /**
     * Constructs an empty student
     */
    public Student() {
    }

    /**
     * Constructs a student by reading from a file
     *
     * @param ID The ID
     * @param firstName The first name
     * @param lastName The last name
     * @param phoneNum The phone number
     * @param classes The list of classes that this student currently takes
     */
    public Student(String ID, String firstName, String lastName, String phoneNum, String classes) {
        super(ID, firstName, lastName, phoneNum, classes);
    }

    /**
     * Calculates how much money this student earns the school per week
     *
     * @return how much money this student earns the school per week
     */
    public double profit() { //how much the student earns the school per week
        double sum = 0;
        for (Course c : this.getClasses()) { //for each course that this student takes
            sum += c.getHourlyProfitPerStudent() * c.getHoursPerDay();
        }
        return sum;
    }

    /**
     * Prints status, super.toString(), and profit
     *
     * @return The formatted information about this student
     */
    @Override
    public String toString() { //prints status, super toString, and amount payable
        return "Status: Student\n" + super.toString() + "Profit: $" + profit();
    }
}
