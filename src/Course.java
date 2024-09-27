import java.util.ArrayList;

public class Course {
    private String name;
    private String code;
    private ArrayList<Student> students = new ArrayList<>();

    /**
     * Creates an instance of the Course class with its name and code as unique identifiers.
     *
     * @param name the name of the course
     * @param code the course code
     */
    public Course(String name, String code){
        this.name = name;
        this.code = code;
    }

    /**
     * Accesses the arraylist of students enrolled in the course.
     * @return the students
     */
    public ArrayList<Student> getStudents() {
        return students;
    }

    /**
     * Searches for and returns a student in the class
     * @param name name of the student
     * @param id student ID
     * @return the student that matches the inputs, null if no such student exits
     */
    public Student findStudent(String name, String id){
        for (Student person : students){
            if (person.getName().equals(name) && person.getId().equals(id)){
                return person;
            }
        }
        return null;
    }

    /**
     * Edits a student's name without duplicates in the class list (No student can have the same name and id).
     * @param name current name of the student
     * @param id student ID
     * @param newName updated student name
     */
    public void editStudentName(String name, String id, String newName){
        if (this.findStudent(newName, id) == null){
            this.findStudent(name, id).setName(newName);
            System.out.println(name + ", " + id + " has been changed to " + newName + ", " + id + ".");

        }
        else{
            System.out.println("Name cannot be changed; this student already exists.");
        }
    }

    /**
     * Edits a student's ID without duplicates in the class list (No student can have the same name and id).
     * @param name name of the student
     * @param id current student ID
     * @param newId updated student ID
     */
    public void editStudentId(String name, String id, String newId){
        if (this.findStudent(name, newId) == null){
            this.findStudent(name, id).setId(newId);
            System.out.println(name + ", " + id + " has been changed to " + name + ", " + newId + ".");
        }
        else{
            System.out.println("ID cannot be changed; this student already exists.");
        }
    }

    /**
     * Adds a new, unique student to the course with the same assignments as the rest of the class (but unmarked).
     * @param name name of the student
     * @param id student ID
     */
    public void addStudent(String name, String id){
        if (this.findStudent(name, id) != null){
            System.out.println(name + ", " + id + " is already in this course.");
            System.out.println("Student information: ");
            System.out.println(this.findStudent(name, id));
        }
        else {
            students.add(new Student(name, id));
            System.out.println(name + ", " + id + " has been added to " + this.name + ", " + code);
            for (int i = 0; i < this.students.get(0).getMarks().size(); i++){
                this.students.get(this.students.size()-1).addAssignment();
            }
        }
    }

    /**
     * Removes a student from the course
     * @param name name of the student
     * @param id student ID
     */
    public void removeStudent(String name, String id){
        if (this.findStudent(name, id) != null){
            System.out.println(name + ", " + id + " has been removed from " + this.name + ", " + code);
            students.remove(findStudent(name, id));
        }
        else{
            System.out.println(name + ", " + id + " is not in " + this.name + ", " + code);
        }
    }

    /**
     * Adds an unmarked assignment for each student in the class
     */
    public void addAssignment(){
        for (Student person : students){
            person.addAssignment();
        }
    }

    /**
     * Deletes a selected assignment from the marks of each student in the class.
     * @param assignment the assignment number
     */
    public void deleteAssignment(int assignment){
        for (Student person : students){
            person.deleteAssignment(assignment);
        }
    }

    /**
     * Calculates the average of the student marks for an assignment in the course; returns -1 if there are no marks entered.
     * @param assignment the assignment number
     * @return the average of the students' marks, excluding any values of -1
     */
    public double assignmentAverage(int assignment){
        double sum = 0;
        int num = 0;
        for (Student person : students){
            if (person.getSingleMark(assignment) != -1){
                sum += person.getMarks().get(assignment);
                num += 1;
            }
        }
        if (num == 0){
            return -1;
        }
        return sum/num;
    }

    /**
     * Calculates the average of all student averages for every the students in the course.
     * @return the course average
     */
    public double courseAverage(){
        double sum = 0;
        int num = 0;
        for (Student person : students){
            if (person.average() != -1){
                sum += person.average();
                num += 1;
            }
        }
        if (num == 0){
            return -1;
        }
        return sum/num;
    }

    /**
     * Prints the course information: name, code, course average, list of students with ids and averages.
     */
    public void courseSummary(){
        System.out.println("Course Information:");
        System.out.print(name + ", " + code + ": ");
        if (this.courseAverage() != -1){
            System.out.println(Main.roundOneDecimal(this.courseAverage()) + "% Average");
        }
        else{
            System.out.println("Nothing has been marked in this course.");
        }
        for (Student person : students){
            System.out.println("\t" + person + ": " + Main.roundOneDecimal(person.average()));
        }
    }

    /**
     * Prints an assignment's information: number, average, all marks with student names.
     * @param assignment
     */
    public void assignmentSummary(int assignment){
        System.out.println("Assignment Information: ");
        System.out.print("Assignment " + assignment + ": ");
        if (this.assignmentAverage(assignment) != -1){
            System.out.println(Main.roundOneDecimal(this.assignmentAverage(assignment)) + "% Average");
        }
        else{
            System.out.println("This assignment has not been marked for any students in the course.");
        }
        for (Student person : students){
            System.out.println("\t" + person + ": " + person.getMarks().get(assignment));
        }
    }

    /**
     * Prints  a class list of all students and all their marks for every assignment.
     */
    public void studentMarks(){
        for (int i = 0; i < students.size(); i++){
            if (i == 0){
                students.get(i).printAssignments();
                System.out.println("");
            }
            System.out.print(students.get(i) + ": \n");
            students.get(i).printMarks();
            System.out.println("");
        }
    }

    /**
     * Prints a class list of all assignments and all the student marks.
     */
    public void assignmentMarks(){
        int num = students.get(0).getMarks().size();
        for (int i = 0; i < num; i++){
            int assignment_number = i;
            System.out.print("Assignment " + i + ": [");
            for (int j = 0; j < students.size(); j++){
                System.out.print(students.get(j).getMarks().get(assignment_number));
                if (j+1 < students.size()){
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        }
    }
}
