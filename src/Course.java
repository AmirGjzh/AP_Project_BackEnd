import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Course implements Serializable {
    @Serial
    private static final long serialVersionUID = 0;

    private final String name;

    private Teacher teacher;

    private final int units;

    private final Map<Student, Double> students;

    private boolean isActive;

    private final ArrayList<Assignment> exercises;

    private Assignment project;

    private final String examDate;
//----------------------------------------------------------------------------------------------------------------------
    public Course(String name, int units, String examDate) {
        this.name = name;
        this.units = units;
        this.examDate = examDate;
        teacher = null;
        students = new HashMap<>();
        exercises = new ArrayList<>();
        isActive = true;
    }

    public String getName() {
        return name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public int getUnits() {
        return units;
    }

    public Map<Student, Double> getStudents() {
        return students;
    }

    public boolean isActive() {
        return isActive;
    }

    public ArrayList<Assignment> getExercises() {
        return exercises;
    }

    public Assignment getProject() {
        return project;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean setTeacher(Teacher teacher) {
        if (teacher == null) {
            this.teacher = null;
        }
        if (this.teacher == null) {
            this.teacher = teacher;
            //System.out.println("Teacher: " + teacher.getName() + " successfully set for course: " + name + ".");
            return true;
        }
        else {
            //System.out.println("This course already has a teacher!");
            return false;
        }
    }

    public void setProject(Assignment project) {
        if (project != null) {
            if (this.project != null) {
                System.out.println("Project for this course is already defined!");
            }
            else {
                this.project = project;
                System.out.println("Project successfully set to course: " + name + ".");
            }
        }
        else {
            if (this.project != null) {
                this.project = null;
                System.out.println("Project successfully removed from the course.");
            }
            else {
                System.out.println("There is no project in this course");
            }
        }
    }
//----------------------------------------------------------------------------------------------------------------------
    public String topStudent(double topScore) {
        for (Student student : students.keySet()) {
            if (students.get(student) == topScore) {
                return student.getName() + " " + student.getLastname();
            }
        }
        return null;
    }

    public void addStudent(Student student) {
        if (students.containsKey(student)) {
            System.out.println("This student is already in the course!");
        }
        else {
            students.put(student, 0.0);
            student.addCourse(this);
        }
    }

    public void removeStudent(Student student) {
        if (students.containsKey(student)) {
            students.remove(student);
            student.removeCourse(this);
        }
        else {
            System.out.println("This student isn't in this course!");
        }
    }

    public void addExercise(Assignment exercise) {
        if (exercises.contains(exercise)) {
            System.out.println("This exercise is already defined!");
        }
        else {
            exercises.add(exercise);
            System.out.println("Exercise successfully added.");
        }
    }

    public void removeExercise(Assignment exercise) {
        if (exercises.contains(exercise)) {
            exercises.remove(exercise);
            System.out.println("Exercise successfully removed.");
        }
        else {
            System.out.println("There is no such assignment!");
        }
    }

    public void printStudents() {
        if (students.isEmpty()) {
            System.out.println("There is no students in the course!");
        }
        else {
            for (Student student : students.keySet()) {
                System.out.println(student.getName() + " " + student.getLastname() + " "  + students.get(student));
            }
        }
    }

    public void addScore(Student student, Double score) {
        students.put(student, students.get(student) + score);
    }

    public void printTopScore() {
        double topScore = Collections.max(students.values());
        System.out.println("The top student is " + topStudent(topScore) + " with score: " + topScore);

    }

    public double getScore(Student student) {
        return students.get(student);
    }
}
