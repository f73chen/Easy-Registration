package chengrace_afterschool;

import java.util.*;
import java.io.*;

/**
 * The runner class
 *
 * @author Grace Chen
 */
public class ChenGrace_AfterSchool {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); //for receiving user input
        int choice = -1; //for selecting an action from the menu
        School s = null;

        System.out.print("Welcome. Would you like to make a new school or import an existing one?\n" //initiation menu, only runs once
                + "0 - New school\n"
                + "1 - Import from file\n"
                + "2 - Exit\n"
                + "Action: ");
        choice = action(in, 3);

        switch (choice) {
            case 0: //make a new school
                System.out.print("Name of school: ");
                s = new School(in.nextLine()); //any name is ok, no error handling needed
                break;

            case 1: //import school from file
                System.out.print("Name of file: ");
                File schoolFile = getFile(in); //loops until user enters valid file name
                try {
                    Scanner reader = new Scanner(schoolFile);
                    s = new School(reader.nextLine()); //school name should be the first line of the file
                    s.loadFile(reader); //calls the school to build its courseList and memberList by reading the file
                    reader.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                break;

            case 2:
                System.out.println("Program successfully exited.");
                return;

            default:
                System.out.println("Error: an unavailable action was chosen");
                break;
        } //by now, a school will have been made one way or another, time to move on to functions

        /*
         Add, edit, and delete teachers, students, and classes
         Find information on any person by typing their name / ID
         Display personal information, money, and their schedule
         Display all classes, or classes of a certain type / day
         Sort teachers and students by their name / ID
         Sort classes by their name / course code / subject
         Store info on teachers (what classes, what days, pay rate, contact info)
         Store info on students (what classes, what days, cost of classes, contact info)
         Change teachers for a class or merge two classes
         Remove all classes that has a teacher but no students
         Calculate the weekly profit 
         Able to break down incomes / expenses by class type
         MAKE SURE ALL METHODS ARE USED
         */
    }

    /**
     * Asks the user for an available action choice
     * @param in Scanner
     * @param max The maximum choice value
     * @return an available action choice
     */
    public static int action(Scanner in, int max) {
        while (true) {
            try {
                int choice = Integer.parseInt(in.nextLine());
                if (choice >= 0 && choice <= max) { //if it's an integer and within range
                    return choice; //ends call if an available choice is made
                } else { //if it's an integer but out of range
                    System.out.print("Integer out of range: ");
                }
            } catch (NumberFormatException e) { //if it's not an integer
                System.out.print("Please enter an integer: ");
            }
        }
    }

    /**
     * Asks the user for a valid file name
     * @param in Scanner
     * @return The valid file
     */
    public static File getFile(Scanner in) {
        while (true) {
            String name = in.nextLine();
            File temp = new File("src/chengrace_afterschool/" + name);
            if (temp.exists()) {
                return temp;
            } else {
                System.out.print("File name invalid. Try again: ");
            }
        }
    }
}
