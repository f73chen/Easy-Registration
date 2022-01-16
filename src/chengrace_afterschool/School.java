package chengrace_afterschool;

import java.util.*;
import java.io.*;

/**
 * Represents a school, which contains courses and people
 *
 * @author Grace Chen
 */
public class School {

    private String name;
    private ArrayList<Course> courseList;
    private ArrayList<Person> memberList;
    Course emptyCourse = new Course();
    Person emptyTeacher = new Teacher();
    Scanner in = new Scanner(System.in);

    /**
     * Constructs a school with a valid name
     *
     * @param name This school's new name
     */
    public School(String name) {
        this.name = name; //don't need to change the name ever
        this.courseList = new ArrayList<>();
        this.memberList = new ArrayList<>();
    }

    /**
     * Gets the name of this school
     *
     * @return This school's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the list of courses offered by this school
     *
     * @return The list of courses offered by this school
     */
    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    /**
     * Gets the list of people taking classes at this school
     *
     * @return The list of people taking classes at this school
     */
    public ArrayList<Person> getMemberList() {
        return memberList;
    }

    /**
     * Asks for valid information then constructs and adds a course to
     * courseList
     */
    public void addCourse() {
        Course c = new Course();
        System.out.print("Course code: ");
        c.setCourseCode(getValidCourseCode(in));
        System.out.print("Course name: ");
        c.setCourseName(in.nextLine());
        System.out.print("Subject (Capitalize): ");
        c.setSubject(in.nextLine());
        System.out.print("Hourly student price: ");
        c.setHourlyProfitPerStudent(checkDouble(in));
        System.out.print("Hourly teacher cost: ");
        c.setHourlyCostPerTeacher(checkDouble(in));
        System.out.print("Hours per day: ");
        c.setHoursPerDay(checkDouble(in));
        this.courseList.add(c);
    }

    /**
     * Asks user for a valid positive double
     *
     * @param in Scanner
     * @return a valid positive double
     */
    public static double checkDouble(Scanner in) { //different from getValidDouble because this doesn't allow -1
        while (true) {
            try {
                double num = Double.parseDouble(in.nextLine());
                if (num > 0) {
                    return num;
                } else {
                    System.out.print("Please enter a positive number: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Please enter a positive number: ");
            }
        }
    }

    /**
     * Asks user for a valid positive double or -1
     *
     * @param in Scanner
     * @return a valid positive double or -1
     */
    public double getValidDouble(Scanner in) { //makes sure user enters valid positive double
        while (true) {
            String num = in.nextLine();
            try {
                double tempDouble = Double.parseDouble(num);
                if (tempDouble > 0) {
                    return tempDouble;
                } else if (tempDouble == -1) { //-1 signifies a blank entry
                    return -1;
                } else {
                    System.out.print("Number invalid. Positive decimal only: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Number invalid. Positive decimal only: ");
            }
        }
    }

    /**
     * Removes a course from courseList and kicks every person currently taking
     * it
     *
     * @param in Scanner
     */
    public void deleteCourse(Scanner in) {
        System.out.println("What course would you like to delete?");
        Course tempCourse = chooseCourse(in);
        for (Person p : memberList) { //checks if anyone registered for the course
            for (int i = 0; i < 5; i++) { //checks each day
                if (p.getClassIDs()[i].equals(tempCourse.getCourseCode())) { //if the person registered for the course on that day
                    clear(p, i); //clear the course from that day
                }
            }
        }
        this.courseList.remove(tempCourse); //finally, remove it from the list of courses
    }

    /**
     * Asks the user to choose from one of the available courses
     *
     * @param in Scanner
     * @return the Course of choice
     */
    public Course chooseCourse(Scanner in) {
        int counter = 0;
        for (Course c : courseList) { //iterate through each course available
            System.out.println(counter + " - " + c.getCourseCode() + ", " + c.getCourseName());
            counter++;
        }
        System.out.print("Choice: ");
        int choice = ChenGrace_AfterSchool.action(in, courseList.size()); //guaranteed to receive valid choice
        return courseList.get(choice); //picks out a course, now to check availability
    }

    /**
     * Asks the user for a valid new course code
     *
     * @param in Scanner
     * @return a non-conflicting new course code
     */
    public String getValidCourseCode(Scanner in) {
        while (true) {
            String courseCode = in.nextLine();
            boolean taken = false;
            for (Course c : courseList) {
                if (c.getCourseCode().equals(courseCode)) { //if another course has matching courseCode
                    System.out.print("Course code already taken. Please try again: ");
                    taken = true;
                    break;
                }
            }
            if (!taken) {
                return courseCode;
            }
        }
    }

    /**
     * Asks for valid info then constructs and adds a person to memberList
     */
    public void addPerson() {
        System.out.print("Do you want to add a teacher or a student?\n"
                + "0 - Teacher\n"
                + "1 - Student\n"
                + "Choice: ");
        int choice = ChenGrace_AfterSchool.action(in, 1);

        System.out.print("ID: ");
        String ID = getValidID(in);
        System.out.print("First name: ");
        String firstName = in.nextLine();
        System.out.print("Last name: ");
        String lastName = in.nextLine();
        System.out.print("Phone number: ");
        String phoneNum = getPhoneNum(in);

        switch (choice) {
            case 0:
                Teacher t = new Teacher();
                t.setID(ID);
                t.setFirstName(firstName);
                t.setLastName(lastName);
                t.setPhoneNum(phoneNum);
                System.out.print("Subjects (capitalize, write all on the same line): ");
                t.setSubjects(in.nextLine());
                this.memberList.add(t);
                break;
            case 1:
                Student s = new Student();
                s.setID(ID);
                s.setFirstName(firstName);
                s.setLastName(lastName);
                s.setPhoneNum(phoneNum);
                //classIDs and classes automatically set to blank, change by registering classes
                this.memberList.add(s);
                break;
        }
    }

    /**
     * Asks the user for a valid new ID
     *
     * @param in Scanner
     * @return a valid new ID
     */
    public String getValidID(Scanner in) {
        while (true) {
            String ID = in.nextLine();
            boolean taken = false;
            for (Person p : memberList) {
                if (p.getID().equals(ID)) { //if another person has matching ID
                    System.out.print("ID already taken. Please try again: ");
                    taken = true;
                    break;
                }
            }
            if (!taken) {
                return ID;
            }
        }
    }

    /**
     * Asks the user for a valid new phone number
     *
     * @param in Scanner
     * @return a valid new phone number
     */
    public static String getPhoneNum(Scanner in) {
        while (true) {
            String num = in.nextLine();
            try {
                long tempLong = Long.parseLong(num);
                if (tempLong > 0) {
                    return num;
                } else {
                    System.out.println("Number invalid. Positive digits only: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Number invalid. Positive digits only: ");
            }
        }
    }

    /**
     * Edits a person's ID, name, and phone number
     *
     * @param p The person to be edited
     * @param in Scanner
     */
    public void changePersonInfo(Person p, Scanner in) {
        System.out.print("Enter \"-1\" to keep current info.\nNew ID: ");
        String ID = getValidID(in);
        if (!ID.equals("-1")) {
            p.setID(ID);
        }
        System.out.print("New first name: ");
        String fName = in.nextLine();
        if (!fName.equals("-1")) {
            p.setFirstName(fName);
        }
        System.out.print("New last name: ");
        String lName = in.nextLine();
        if (!lName.equals("-1")) {
            p.setLastName(lName);
        }
        System.out.print("New phone number: ");
        while (true) {
            String num = in.nextLine();
            try {
                long tempLong = Long.parseLong(num);
                if (tempLong > 0) {
                    p.setPhoneNum(num);
                    break;
                } else if (tempLong == -1) { //-1 is okay because it's the escape case
                    break;
                } else {
                    System.out.print("Number invalid. Positive digits only: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Number invalid. Positive digits only: ");
            }
        }
    }

    /**
     * Edits a course's name, profit per student, cost per teacher, and hours
     * per day
     *
     * @param c The course to be edited
     * @param in Scanner
     */
    public void changeCourseInfo(Course c, Scanner in) {
        System.out.print("Enter \"-1\" to keep current info.\n");
        System.out.print("New course name: ");
        String tempString = in.nextLine();
        if (!tempString.equals("-1")) {
            c.setCourseName(tempString); //name can be anything
        }
        System.out.print("New hourly profit per student: ");
        Double tempDouble = getValidDouble(in);
        if (tempDouble != -1) {
            c.setHourlyProfitPerStudent(tempDouble);
        }
        System.out.print("New hourly cost per teacher: ");
        tempDouble = getValidDouble(in);
        if (tempDouble != -1) {
            c.setHourlyCostPerTeacher(tempDouble);
        }
        System.out.print("New number of hours per day: ");
        tempDouble = getValidDouble(in);
        if (tempDouble != -1) {
            c.setHoursPerDay(tempDouble);
        }
    }

    /**
     * Removes a person from memberList and clears all of their courses
     */
    public void deletePerson() {
        System.out.println("Which person would you like to delete?");
        int counter = 0;
        for (Person p : memberList) { //iterate through each person
            System.out.println(counter + " - " + p.getID() + ", " + p.getName());
            counter++;
        }
        System.out.print("Choice: ");
        int choice = ChenGrace_AfterSchool.action(in, memberList.size()); //guaranteed to receive valid answer
        Person tempPerson = memberList.get(choice);

        for (int i = 0; i < 5; i++) { //clears courses for each day
            clear(tempPerson, i);
        }
        this.memberList.remove(tempPerson); //finally, remove it from the list of people

    }

    /**
     * Builds courseList and memberList by reading from a file
     *
     * @param reader The file reader
     */
    public void loadFile(Scanner reader) {
        while (reader.hasNext()) {
            String temp = reader.nextLine(); //starts reading from the second line
            String[] tempSplit = temp.split(","); //splits each element of each line
            String type = tempSplit[0];

            if (type.equals("C")) { //if line describes a course
                courseList.add(new Course(tempSplit[1], tempSplit[2], tempSplit[3], Double.parseDouble(tempSplit[4]), Double.parseDouble(tempSplit[5]), Double.parseDouble(tempSplit[6])));
            } else if (type.equals("T")) { //if line describes a teacher
                Teacher tempTeacher = new Teacher(tempSplit[1], tempSplit[2], tempSplit[3], tempSplit[4], tempSplit[5], tempSplit[6]);
                setCourses(tempTeacher);
                memberList.add(tempTeacher);
            } else if (type.equals("S")) { //if line describes a student
                Student tempStudent = new Student(tempSplit[1], tempSplit[2], tempSplit[3], tempSplit[4], tempSplit[5]);
                setCourses(tempStudent);
                memberList.add(tempStudent);
            } else {
                System.out.println("File format error, no object constructed.");
            }
        }
    }

    /**
     * Saves school name, courseList, and memberList to a file
     *
     * @param writer The file writer
     */
    public void saveFile(FileWriter writer) {
        try {
            writer.write(this.name + "\n");
            for (Course c : courseList) { //for each course
                writer.write("C," + c.getCourseCode() + "," + c.getCourseName() + "," + c.getSubject() + "," + c.getHourlyProfitPerStudent() + "," + c.getHourlyCostPerTeacher() + "," + c.getHoursPerDay() + "\n");
            }
            for (Person p : memberList) {
                if (p instanceof Teacher) { //for a teacher
                    writer.write("T," + p.getID() + "," + p.getFirstName() + "," + p.getLastName() + "," + p.getPhoneNum() + "," + ((Teacher) p).getSubjects());
                } else if (p instanceof Student) { //for a student
                    writer.write("S," + p.getID() + "," + p.getFirstName() + "," + p.getLastName() + "," + p.getPhoneNum());
                }
                //write classes the same way for both
                writer.write("," + String.join("@", p.getClassIDs()) + "\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Generates a person's list of courses from their list of courseIDs
     *
     * @param p The person
     */
    public void setCourses(Person p) {
        p.getClasses().clear();
        for (String id : p.getClassIDs()) { //for each course ID, find and add the corresponding course
            p.getClasses().add(findCourseID(id));
        }
    }

    /**
     * Finds a course using its courseCode
     *
     * @param courseID The courseCode
     * @return The course corresponding to its courseCode
     */
    public Course findCourseID(String courseID) {
        for (Course c : this.courseList) {
            if (c.getCourseCode().equals(courseID)) {
                return c;
            }
        }
        return emptyCourse;
    }

    /**
     * Selection sorts a list of courses by their courseCode
     */
    public void sortCourseID() {
        for (int index = 1; index < courseList.size(); index++) { //for each unsorted element
            Course tempCourse = courseList.get(index); //pick it out
            int testIndex = index - 1; //test against every value before it
            while (testIndex > -1 && courseList.get(testIndex).compareID(tempCourse) > 0) { //while there are more values to compare against and it's still too small
                courseList.set(testIndex + 1, courseList.get(testIndex)); //move up the previous value by a slot
                testIndex--; //move on to one value prior for the next comparison
            }
            courseList.set(testIndex + 1, tempCourse); //when it reaches the end of the comparison, drop it into the slot
        }
    }

    /**
     * Finds a course using its courseName
     *
     * @param courseName The courseName
     * @return The course corresponding to its courseName
     */
    public Course findCourseName(String courseName) {
        for (Course c : this.courseList) {
            if (c.getCourseName().equals(courseName)) {
                return c;
            }
        }
        return emptyCourse;
    }

    /**
     * Selection sorts a list of courses by their courseName
     */
    public void sortCourseName() {
        for (int index = 1; index < courseList.size(); index++) { //for each unsorted element
            Course tempCourse = courseList.get(index); //pick it out
            int testIndex = index - 1; //test against every value before it
            while (testIndex > -1 && courseList.get(testIndex).compareName(tempCourse) > 0) { //while there are more values to compare against and it's still too small
                courseList.set(testIndex + 1, courseList.get(testIndex)); //move up the previous value by a slot
                testIndex--; //move on to one value prior for the next comparison
            }
            courseList.set(testIndex + 1, tempCourse); //when it reaches the end of the comparison, drop it into the slot
        }
    }

    /**
     * Finds a person using their ID
     *
     * @param personID The ID
     * @return The person corresponding to their ID
     */
    public Person findPersonID(String personID) {
        for (Person p : memberList) {
            if (p.getID().equals(personID)) {
                return p;
            }
        }
        return emptyTeacher;
    }

    /**
     * Selection sorts a list of people by their ID
     */
    public void sortPersonID() {
        for (int index = 1; index < memberList.size(); index++) { //for each unsorted element
            Person tempPerson = memberList.get(index); //pick it out
            int testIndex = index - 1; //test against every value before it
            while (testIndex > -1 && memberList.get(testIndex).compareID(tempPerson) > 0) { //while there are more values to compare against and it's still too small
                memberList.set(testIndex + 1, memberList.get(testIndex)); //move up the previous value by a slot
                testIndex--; //move on to one value prior for the next comparison
            }
            memberList.set(testIndex + 1, tempPerson); //when it reaches the end of the comparison, drop it into the slot
        }
    }

    /**
     * Finds a person using their name
     *
     * @param personName The full name
     * @return The person corresponding to their name
     */
    public Person findPersonName(String personName) {
        for (Person p : memberList) {
            if (p.getName().equals(personName)) {
                return p;
            }
        }
        return emptyTeacher;
    }

    /**
     * Selection sorts a list of people by their name
     */
    public void sortPersonName() {
        for (int index = 1; index < memberList.size(); index++) { //for each unsorted element
            Person tempPerson = memberList.get(index); //pick it out
            int testIndex = index - 1; //test against every value before it
            while (testIndex > -1 && memberList.get(testIndex).compareName(tempPerson) > 0) { //while there are more values to compare against and it's still too small
                memberList.set(testIndex + 1, memberList.get(testIndex)); //move up the previous value by a slot
                testIndex--; //move on to one value prior for the next comparison
            }
            memberList.set(testIndex + 1, tempPerson); //when it reaches the end of the comparison, drop it into the slot
        }
    }

    /**
     * Calculates how much the school nets a week by adding all profits and
     * costs
     */
    public void weeklyEarnings() {
        double sum = 0;
        for (Person p : memberList) {
            System.out.println(p.getName() + ": $" + p.profit());
            sum += p.profit();
        }
        System.out.println("Total: $" + sum);
    }

    /**
     * Clears a course for someone on a specific day
     *
     * @param p The person
     * @param day The day of the course to be cleared
     */
    public void clear(Person p, int day) { //doesn't matter if it's already empty
        String ID = p.getClassIDs()[day]; //saves the class ID for later comparison
        p.getClassIDs()[day] = ""; //empties classID for that day
        p.getClasses().set(day, emptyCourse); //sets an empty Course object for that day
        if (p instanceof Student) { //for a student, kick the teacher if the class becomes empty
            Teacher tempTeacher = new Teacher();
            for (Person p2 : memberList) { //combs through memberList
                if (p2 instanceof Student && p2.getClassIDs()[day].equals(ID)) { //if there's another student taking the same class
                    return; //don't need to look anymore, class is not empty
                } else if (p2 instanceof Teacher && p2.getClassIDs()[day].equals(ID)) { //if finds a teacher teaching that class
                    tempTeacher = (Teacher) p2; //saves the teacher for later
                }
            } //after going through every member
            clear(tempTeacher, day); //if finds a teacher but no other students, kicks teacher
        } else if (p instanceof Teacher) { //for a teacher, kick all remaining students
            for (Person p2 : memberList) { //combs through memberList
                if (p2 instanceof Student && p2.getClassIDs()[day].equals(ID)) { //if there's a student taking the same class
                    clear(p2, day);
                }
            }
        }
    }

    /**
     * Registers a student for a course
     *
     * @param s The student
     * @param in Scanner
     */
    public void register(Student s, Scanner in) { //only add if available teacher, join existing class if available, assigns it for teacher if new
        if (courseList.size() == 0) { //if there are no courses to pick from, exit call
            System.out.println("Sorry, there are no courses to pick from.");
            return;
        }
        System.out.println("What class would you like to register " + s.getName() + " for?");
        Course tempCourse = chooseCourse(in); //picks out a course, now to check availability

        System.out.println("What day would you like to have " + tempCourse.getCourseName() + " on?");
        String[] status = {"", "", "", "", ""};
        Teacher[] availableTeachers = new Teacher[5];
        for (int day = 0; day < 5; day++) { //checks course availability for each day
            if (s.getClassIDs()[day].equals("")) { //if student has an empty/free slot on that day, else don't bother
                for (Person p : memberList) { //looks in memberList for anyone already taking that class
                    if (p.getClassIDs()[day].equals(tempCourse.getCourseCode())) { //if someone's taking tempCourse on that day
                        System.out.println("Day " + day + " available.");
                        status[day] = "found";
                        break; //break out of the memberList for loop once anyone has been found
                    }
                }
                if (status[day].equals("")) { //there are no existing classes found, must check if able to add new class
                    for (Person p : memberList) {
                        if (p instanceof Teacher) {
                            if (((Teacher) p).getSubjects().contains(tempCourse.getSubject()) && p.getClassIDs()[day].equals("")) {
                                //if there's a teacher that teaches that subject and has an empty slot on that day
                                availableTeachers[day] = (Teacher) p; //stores found teacher for future reference
                                System.out.println("Day " + day + " available.");
                                status[day] = "new"; //status remains blank if no teacher is found for that day
                                break; //stop looking for more after finding one
                            }
                        }
                    }
                }
            }
            if (status[day].equals("")) { //if not able to find or create a new class, move on to the next day
                System.out.println("Day " + day + " NOT available.");
            }
        }

        for (String stat : status) { //if status is completely empty, aka no available days
            if (!stat.equals("")) { //if there's one unempty slot
                break; //break and continue the registration
            } //if it doesn't manage to break, aka all are empty
            System.out.println("All days are unavailable. Please try registering for another course.");
            return;
        }

        System.out.print("Choice: "); //picking the day of the week
        int choice = ChenGrace_AfterSchool.action(in, 4); //pick any day of the week
        while (status[choice].equals("")) { //if they pick an unavailable day
            System.out.print("Sorry, that day is unavailable. Please try again: "); //loops to pick again
            choice = ChenGrace_AfterSchool.action(in, 4);
        }
        if (status[choice].equals("found")) { //class already exists, student can just join
            s.getClassIDs()[choice] = tempCourse.getCourseCode();
            s.getClasses().set(choice, tempCourse);
        } else if (status[choice].equals("new")) { //make new class for student and the stored teacher
            s.getClassIDs()[choice] = tempCourse.getCourseCode();
            s.getClasses().set(choice, tempCourse);
            availableTeachers[choice].getClassIDs()[choice] = tempCourse.getCourseCode();
            availableTeachers[choice].getClasses().set(choice, tempCourse);
        } else {
            System.out.println("Error: status stored incorrectly");
        }
    }

    /**
     * Substitutes a teacher for any other available teacher
     *
     * @param t The teacher to be replaced
     * @param day The day of the class
     */
    public void takeOver(Teacher t, int day) { //allows another teacher to take over if available & subject match, no kick student
        if (!t.getClassIDs()[day].equals("")) { //if this teacher is actually teaching that day
            Course tempCourse = t.getClasses().get(day);
            for (Person p : memberList) {
                if (p instanceof Teacher && !p.getID().equals(t.getID())) { //if there's another teacher that's not this teacher
                    if (((Teacher) p).getSubjects().contains(tempCourse.getSubject()) && p.getClassIDs()[day].equals("")) { //if the teacher is able to teach this subject and has time
                        p.getClassIDs()[day] = tempCourse.getCourseCode();
                        p.getClasses().set(day, tempCourse);
                        t.getClassIDs()[day] = "";
                        t.getClasses().set(day, emptyCourse);
                    }
                }
            }
        } else {
            System.out.println("This teacher is not teaching on that day.");
        }
    }

    /**
     * Prints a formatted schedule for a course
     *
     * @param c The course
     * @return a formatted schedule for the course
     */
    public String courseToString(Course c) {
        String out = c.toString();
        double sum = 0;
        for (int i = 0; i < 5; i++) { //for each day of the week
            out += "\nDay " + i + ":\n    Teacher:";
            for (Person p : memberList) {
                if (p instanceof Teacher && p.getClasses().get(i).compareID(c) == 0) {
                    out += "\n        ID: " + p.getID() + ", Name: " + p.getName();
                    sum -= c.getHourlyCostPerTeacher() * c.getHoursPerDay();
                }
            }
            out += "\n    Student:";
            for (Person p : memberList) {
                if (p instanceof Student && p.getClasses().get(i).compareID(c) == 0) {
                    out += "\n        ID: " + p.getID() + ", Name: " + p.getName();
                    sum += c.getHourlyProfitPerStudent() * c.getHoursPerDay();
                }
            }
            out += "\n";
        }
        out += "\nTotal profit: $" + sum;
        return out;
    }

    /**
     * Prints a simple list of courses and members
     *
     * @return a simple list of courses and members
     */
    @Override
    public String toString() {
        String out = "Courses:\n";
        for (Course c : courseList) {
            out += "ID: " + c.getCourseCode() + ", Name: " + c.getCourseName() + ", Subject: " + c.getSubject() + "\n";
        }
        out += "\nMembers:\n";
        for (Person p : memberList) {
            out += "ID: " + p.getID() + ", Name: " + p.getName() + ", Phone: " + p.getPhoneNum() + ", Classes: " + String.join("; ", p.getClassIDs()) + "\n";
        }
        return out;
    }
}
