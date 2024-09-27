import java.util.ArrayList;

public class Student {
    private String name;
    private String id;
    private ArrayList<Integer> marks = new ArrayList<>();

    /**
     * Creates an instance of the Student class with its name and id as unique identifiers.
     *
     * @param name the name of the student
     * @param id the ID of the student
     */
    public Student(String name, String id){
        this.name = name;
        this.id = id;
    }

    /**
     * Accesses the name of a student.
     * @return the name of the student
     */
    public String getName(){
        return name;
    }

    /**
     * Accesses the ID of a student.
     * @return the ID of the student
     */
    public String getId(){
        return id;
    }

    /**
     * Accesses all the marks of a student.
     * @return the array list of marks
     */
    public ArrayList<Integer> getMarks(){
        return marks;
    }

    /**
     * Accesses a specific assignment mark of a student.
     * @param assignment the assignment number
     * @return the mark
     */
    public int getSingleMark(int assignment){
        return marks.get(assignment);
    }

    /**
     * Changes the name of a student.
     * @param name the student's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Changes the id of the student.
     * @param id student ID
     */
    public void setId(String id) {
        this.id = id;
    }

    public void setMarks(ArrayList<Integer> marks){
        this.marks = marks;
    }

    /**
     * Modifies the mark for a specific assignment of a student.
     * @param assignment the assignment number/index
     * @param grade the mark the student received
     */
    public void editMark(int assignment, int grade){
        marks.set(assignment, grade);
    }

    /**
     * Calculates the average of a student, excluding any assignments with -1 as the mark.
     * @return the student's average
     */
    public double average(){
        double sum = 0;
        int num = 0;
        for (int mark: marks){
            if (mark >= 0){
                sum += mark;
                num += 1;
            }
        }
        if (num == 0){
            return -1;
        }
        return sum / num;
    }

    /**
     * Creates an unmarked assignment for a student with -1 as the current grade.
     */
    public void addAssignment(){
        marks.add(-1);
    }

    /**
     * Removes an assignment.
     * @param assignment the assignment index
     */
    public void deleteAssignment(int assignment){
        if (assignment >= 0 && assignment < marks.size()) {
            marks.remove(assignment);
        }
    }

    /**
     * Overrides how a student is displayed as a String.
     * @return name, id as a string
     */
    @Override
    public String toString(){
        return name + ", " + id;
    }

    /**
     * Prints all marks of a student in order.
     */
    public void printMarks(){
        System.out.print("\tMarks:  \t\t\t");
        for (int mark : marks){
            System.out.print(mark + "\t");
        }
    }

    /**
     * Prints the assignment numbers of a student.
     */
    public void printAssignments(){
        System.out.print("\tAssignments: \t\t");
        for (int i = 0; i < this.marks.size(); i++){
            System.out.print(i + "\t");
        }
        System.out.println("");
    }

    /**
     * Prints the name, id, current average rounded to one decimal place, and the marks for each assignment.
     */
    public void studentInfo(){
        System.out.print(this.toString() + ": ");
        if (this.average() != -1){
            System.out.println(Main.roundOneDecimal(this.average()) + "% Average");
        }
        else{
            System.out.println("No work has been marked.");
        }
        this.printAssignments();
        this.printMarks();
        System.out.print("\n\t");
    }
}
