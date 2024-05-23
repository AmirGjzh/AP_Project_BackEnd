import java.io.Serial;
import java.io.Serializable;

public class Assignment implements Serializable {
    @Serial
    private static final long serialVersionUID = 0;

    private final String title;

    private final Course course;

    private int deadLine;

    private boolean isActive;
//----------------------------------------------------------------------------------------------------------------------
    public Assignment(String title, Course course, int deadLine) {
        this.title = title;
        this.course = course;
        this.deadLine = deadLine;
        isActive = true;
    }

    public String getTitle() {
        return title;
    }

    public Course getCourse() {
        return course;
    }

    public int getDeadLine() {
        return deadLine;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setDeadLine(int deadLine) {
        this.deadLine = deadLine;
        System.out.println("Deadline successfully changed.");
    }

    public void setActive(boolean active) {
        isActive = active;
    }
//----------------------------------------------------------------------------------------------------------------------
}
