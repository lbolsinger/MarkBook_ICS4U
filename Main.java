import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static Course ics = new Course("Introduction to Computer Science", "CSC100");
    public static final Scanner READER = new Scanner(System.in);
    public static final int MAX_MARK = 100;
    public static final int MIN_MARK = -1;
    public static final String EXIT = "exit";
    public static final String BACK_TO_MAIN = "main";
    public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.#");

    public static void main(String[] args) {
        userInformation();
        System.out.println("...Course information is being initialized...\n");
        setUpClass();
        System.out.println("\n");
        mainMenu();
    }

    /**
     * Prints a message for the user when Grade Book is opened.
     */
    public static void userInformation(){
        System.out.println("\n\nWELCOME TO GRADE BOOK");
        System.out.println("\nEnter EXIT at any time to end the program; enter MAIN at any time to return to the main menu.");
        System.out.println("\nMain Menu Options");
        System.out.println("\t1 - Course\n\t\ta - add student to the course\n\t\tb - remove student from the course\n\t\tc - create an assignment for the course, unmarked");
        System.out.println("\t\td - delete an assignment from the course\n\t\te - print a course summary\n\t\tf - print all student marks\n\t\tg - print all assignment marks");
        System.out.println("\t2 - Student\n\t\ta - edit a mark for an assignment\n\t\tb - edit the marks for all of the student's assignment\n\t\tc - print the student's information");
        System.out.println("\t\td - edit student's name\n\t\te - edit student's ID");
        System.out.println("\t3 - Assignment\n\t\ta - edit the mark of a student\n\t\tb - edit the marks for all students\n\t\tc - print the assignment's information");
        System.out.println("\n");
    }

    /**
     * Initializes Introduction to Computer Science, CSC100 with five students, each with five graded assignments.
     */
    public static void setUpClass(){
        ics.addStudent("Alan T", "110101011");
        ics.addStudent("Donald K", "314159265");
        ics.addStudent("Albert E", "299792458" );
        ics.addStudent("Marie C", "002661600");
        ics.addStudent("Ada L", "018151210");

        ArrayList<Integer> m1 = new ArrayList<>(Arrays.asList(83, 71, 76, 91, 85));
        ics.getStudents().get(0).setMarks(m1);
        ArrayList<Integer> m2 = new ArrayList<>(Arrays.asList(84, 90, 88, 99, 80));
        ics.getStudents().get(1).setMarks(m2);
        ArrayList<Integer> m3 = new ArrayList<>(Arrays.asList(93, 65, 95, 40, 19));
        ics.getStudents().get(2).setMarks(m3);
        ArrayList<Integer> m4 = new ArrayList<>(Arrays.asList(76, 52, 96, 92, 66));
        ics.getStudents().get(3).setMarks(m4);
        ArrayList<Integer> m5 = new ArrayList<>(Arrays.asList(91, 98, 89, 99, 99));
        ics.getStudents().get(4).setMarks(m5);
    }
    /**
     * Prompts the user to enter a valid option from the main menu.
     */
    public static void mainMenu(){
        String input = "";
        do{
            System.out.println("MAIN MENU");
            System.out.println("1 - COURSE \n2 - STUDENT\n3 - ASSIGNMENT\n\nEXIT");
            input = getInput();
            switch(input){
                case "1":
                    courseMenu(); break;
                case "2":
                    studentMenu(); break;
                case "3":
                    assignmentMenu(); break;
                default:
                    System.out.println("Invalid input.");
                    break;
            }
            System.out.println("");
        }
        while (input.equals(EXIT) == false);
        end();
    }

    /**
     * Prompts the user to enter a valid option from the course menu.
     */
    public static void courseMenu(){
        String choice;
        String name;
        String id;
        int assignment;
        while (true){
            System.out.println("COURSE MENU");
            System.out.println("a - ADD STUDENT \nb - REMOVE STUDENT\nc - CREATE ASSIGNMENT\nd - REMOVE ASSIGNMENT\ne - COURSE SUMMARY\nf - STUDENT MARKS\ng - ASSIGNMENT MARKS\n\nMAIN or EXIT");
            choice = getInput().toLowerCase();
            switch (choice){
                case "a":
                    System.out.print("Enter student name: ");
                    name = getInput();
                    System.out.print("Enter student id: ");
                    id = getInput();
                    ics.addStudent(name, id);
                    break;
                case "b":
                    System.out.print("Enter student name: ");
                    name = getInput();
                    System.out.print("Enter student id: ");
                    id = getInput();
                    ics.removeStudent(name, id);
                    break;
                case "c":
                    ics.addAssignment();
                    System.out.println("Assignment " + (ics.getStudents().get(0).getMarks().size() - 1) + " has been created.");
                    System.out.println("Student marks have not been entered for this assignment.");
                    break;
                case "d" :
                    assignment = assignmentIndex();
                    ics.deleteAssignment(assignment);
                    System.out.println("Assignment " + assignment + " has been removed.");
                    break;
                case "e":
                    ics.courseSummary();
                    break;
                case "f":
                    ics.studentMarks();
                    break;
                case "g":
                    ics.assignmentMarks();
                    break;
                default:
                    System.out.println("Invalid input.");
            }
            System.out.println("\n");
        }
    }

    /**
     * Prompts the user to enter a valid option from the student menu.
     */
    public static void studentMenu(){
        String choice;
        int student;
        int assignment;
        int mark;
        String newName;
        String newId;
        while (true) {
            System.out.println("STUDENT MENU");
            System.out.println("Select a student - You may enter MAIN to return to the main menu; you may enter EXIT to close Grade Book");
            student = studentIndex();
            System.out.println("");
            System.out.println("a - EDIT ASSIGNMENT MARK \nb - EDIT ALL ASSIGNMENTS\nc - STUDENT INFORMATION\nd - EDIT NAME\ne - EDIT ID\n\nMAIN or EXIT");
            choice = getInput().toLowerCase();
            switch (choice) {
                case "a" :
                    assignment = assignmentIndex();
                    mark = validMark("Enter mark for assignment " + assignment + ": ");
                    ics.getStudents().get(student).editMark(assignment, mark);
                    System.out.println(ics.getStudents().get(student) + " mark for assignment " + assignment + " has been updated to " + mark + ".\n");
                    break;
                case "b" :
                    for (int i = 0; i < ics.getStudents().get(student).getMarks().size(); i++){
                        mark = validMark("Enter mark for assignment " + i + ": ");
                        ics.getStudents().get(student).editMark(i, mark);
                        System.out.println(ics.getStudents().get(student) + " mark for assignment " + i + " has been updated to " + mark + ".\n");
                    }
                    break;
                case "c" :
                    ics.getStudents().get(student).studentInfo();
                    break;
                case "d":
                    System.out.print("Enter the new name for " + ics.getStudents().get(student) + ": ");
                    newName = getInput();
                    ics.editStudentName(ics.getStudents().get(student).getName(), ics.getStudents().get(student).getId(), newName);
                    break;
                case "e":
                    System.out.print("Enter the new ID for " + ics.getStudents().get(student) + ": ");
                    newId = getInput();
                    ics.editStudentId(ics.getStudents().get(student).getName(), ics.getStudents().get(student).getId(), newId);
                    break;
                default :
                    System.out.println("Invalid input.");
            }
            System.out.println("");
        }
    }

    /**
     * Prompts the user to enter a valid option from the assignment menu.
     */
    public static void assignmentMenu(){
        String choice;
        int assignment;
        int mark;
        int student;
        while (true) {
            System.out.println("ASSIGNMENT MENU");
            System.out.println("Select an assignment - You may enter MAIN to return to the main menu; you may enter EXIT to close Grade Book");
            assignment = assignmentIndex();
            System.out.println("a - EDIT SINGLE MARK \nb - EDIT ALL STUDENTS\nc - ASSIGNMENT INFORMATION\n\nMAIN or EXIT");
            choice = getInput().toLowerCase();
            switch (choice) {
                case "a" :
                    System.out.println("Select a student");
                    student = studentIndex();
                    mark = validMark("Enter assignment " + assignment + " mark for " + ics.getStudents().get(student) + ": ");
                    ics.getStudents().get(student).editMark(assignment, mark);
                    System.out.println(ics.getStudents().get(student) + " mark for assignment " + assignment + " has been updated to " + mark + ".\n");
                    break;
                case "b" :
                    for (Student person : ics.getStudents()){
                        mark = validMark("Enter assignment " + assignment + " mark for " + person + ": ");
                        person.editMark(assignment, mark);
                        System.out.println(person + " mark for assignment " + assignment + " has been updated to " + mark + ".\n");
                    }
                    break;
                case "c" :
                    ics.assignmentSummary(assignment);
                    break;
                default:
                    System.out.println("Invalid input.\n");
            }
            System.out.println("");
        }
    }

    /**
     * Gets an integer input from the user and catches invalid inputs.
     * @param message what is printed as a prompt for the user
     * @return the integer they entered
     */
    public static int getInt(String message){
        while (true){
            try {
                System.out.print(message);
                int num = Integer.parseInt(getInput());
                return num;
            }
            catch(NumberFormatException nfe){
                System.out.print("Invalid input; not an integer.\n");
            }
        }
    }

    /**
     * Finds the index of a student within the class list given their name and ID.
     * @return the index of the student in course.students arraylist
     */
    public static int studentIndex(){
        String name;
        String id;
        while (true) {
            System.out.print("Enter student name: ");
            name = getInput();
            System.out.print("Enter student ID: ");
            id = getInput();
            if (ics.findStudent(name, id) == null) {
                System.out.println(name + ", " + id + "is not in this class.");
            }
            else{
                return ics.getStudents().indexOf(ics.findStudent(name, id));
            }
        }
    }

    /**
     * Gets a valid assignment index from the user.
     * @return the assignment index in student.marks arraylist
     */
    public static int assignmentIndex(){
        int assignment;
        while (true) {
            assignment = getInt("Enter assignment number: ");
            if (assignment + 1 <= ics.getStudents().get(0).getMarks().size() && assignment >= 0){
                return assignment;
            }
            else {
                System.out.print("Invalid input; assignment out of range.\n");
            }
        }
    }

    /**
     * Gets a valid mark entered for a student's assignment from the user.
     * @param message what is printed as a prompt for the user
     * @return the mark
     */
    public static int validMark(String message){
        int mark;
        while (true) {
            mark = getInt(message);
            if (MIN_MARK <= mark && mark <= MAX_MARK){
                return mark;
            }
            else {
                System.out.print("Invalid input; mark out of range.\n");
            }
        }
    }

    /**
     * Gets a string input from the user that exits the code if "exit" is entered, and goes to the main menu if "main" is entered.
     * @return the user's input
     */
    public static String getInput(){
        String input = READER.nextLine();
        if (input.toLowerCase().equals(EXIT)){
            end();
        }
        else if (input.toLowerCase().equals(BACK_TO_MAIN)){
            System.out.println("\n... Returning to main menu ...\n");
            mainMenu();
        }
        return input;
    }

    /**
     * Exits the code.
     */
    public static void end(){
        System.out.println("\n... Grade Book is closing ...\n");
        System.exit(0);
    }

    /**
     * Rounds a double to one decimal place.
     * @param num the number being rounded
     * @return the rounded number
     */
    public static double roundOneDecimal(double num){
        double roundedNum = Math.round(num * 10.0) / 10.0;
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        return Double.parseDouble(DECIMAL_FORMAT.format(roundedNum));
    }
}