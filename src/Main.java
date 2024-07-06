import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class Main {

    static final String dataBaseUrl = "C:\\Users\\Sumsung\\OneDrive\\Documents\\Java\\AP_Project_BackEnd\\Data Base Files";

    static String RESET = "\u001B[0m";
    static String RED = "\u001B[31m";
    static String GREEN = "\u001B[32m";
    static String BLUE = "\u001B[34m";

//----------------------------------------------------------------------------------------------------------------------

    private static void updateProject(Assignment assignment) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(dataBaseUrl
                    + "\\Courses\\" + assignment.getCourse().getName() + "\\Project\\" + assignment.getTitle() + ".txt"));
            objectOutputStream.writeObject(assignment);
            objectOutputStream.close();
        } catch (Exception e) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println(RED + "Couldn't update project! (Exception)" + RESET);
            System.exit(1384);
        }
    } //--- Completed ---//

    private static boolean findProject(String title, String name) {
        File dir = new File(dataBaseUrl + "\\Courses\\" + name + "\\Project");
        File[] files = dir.listFiles();

        if (files == null) {
            return false;
        }

        for (File file : files) {
            if (file.getName().equals(title + ".txt")) {
                return true;
            }
        }
        return false;
    } //--- Completed ---//

    private static Assignment getProjectFromDataBase(String title, String name) {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(dataBaseUrl +
                    "\\Courses\\" + name + "\\Project\\" + title + ".txt"));
            Assignment assignment = (Assignment) objectInputStream.readObject();
            objectInputStream.close();
            return assignment;
        } catch (Exception e) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println(RED + "Couldn't get project! (Exception)" + RESET);
            System.exit(1384);
        }
        return null;
    } //--- Completed ---//

    private static void removeProject(Assignment assignment) {
        File file = new File(dataBaseUrl + "\\Courses\\" + assignment.getCourse().getName() +
                "\\Project\\" + assignment.getTitle() + ".txt");
        file.delete();
    } //--- Completed ---//

    private static boolean addProject(String title, Course course, int deadLine) {
        try {
            File dir = new File(dataBaseUrl + "\\Courses\\" + course.getName() + "\\Project");
            File[] files = dir.listFiles();

            if (files.length == 1) {
                return false;
            }

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(dataBaseUrl +
                    "\\Courses\\" + course.getName() + "\\Project\\" + title + ".txt"));

            objectOutputStream.writeObject(new Assignment(title, course, deadLine));

            objectOutputStream.close();
            return true;
        } catch (Exception e) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println(RED + "Couldn't add project! (Exception)" + RESET);
            System.exit(1384);
        }
        return false;
    } //--- Completed ---//

//----------------------------------------------------------------------------------------------------------------------

    static void updateExercise(Assignment assignment) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(dataBaseUrl
                    + "\\Courses\\" + assignment.getCourse().getName() + "\\" + assignment.getTitle() + ".txt"));
            objectOutputStream.writeObject(assignment);
            objectOutputStream.close();
        } catch (Exception e) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println(RED + "Couldn't update exercise! (Exception)" + RESET);
            System.exit(1384);
        }
    } //--- Completed ---//

    private static boolean findExercise(String title, String name) {
        File dir = new File(dataBaseUrl + "\\Courses\\" + name);
        File[] files = dir.listFiles();

        if (files == null) {
            return false;
        }

        for (File file : files) {
            if (file.getName().equals(title + ".txt")) {
                return true;
            }
        }
        return false;
    } //--- Completed ---//

    private static void removeExercise(Assignment assignment) {
        File file = new File(dataBaseUrl + "\\Courses\\" + assignment.getCourse().getName() +
                "\\" + assignment.getTitle() + ".txt");
        file.delete();
    } //--- Completed ---//

    static Assignment getExerciseFromDataBase(String title, String name) {
        try {
            File dir = new File(dataBaseUrl + "\\Courses\\" + name);
            File[] files = dir.listFiles();

            for (File file : files) {
                if (file.getName().equals(title + ".txt")) {
                    ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(dataBaseUrl
                            + "\\Courses\\" + name + "\\" + title + ".txt"));
                    Assignment assignment = (Assignment) objectInputStream.readObject();
                    objectInputStream.close();
                    return assignment;
                }
            }
        } catch (Exception e) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println(RED + "Couldn't get exercise! (Exception)" + RESET);
            System.exit(1384);
        }
        return null;
    } //--- Completed ---//

    private static boolean addExercise(String title, Course course, int deadLine) {
        try {
            File dir = new File(dataBaseUrl + "\\Courses\\" + course.getName());
            File[] files = dir.listFiles();

            for (File f : files) {
                if (f.getName().equals(title + ".txt")) {
                    return false;
                }
            }

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(dataBaseUrl
                    + "\\Courses\\" + course.getName() + "\\" + title + ".txt"));

            objectOutputStream.writeObject(new Assignment(title, course, deadLine));

            objectOutputStream.close();
            return true;
        } catch (Exception e) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println(RED + "Couldn't add exercise! (Exception)" + RESET);
            System.exit(1384);
        }
        return false;
    } //--- Completed ---//

//----------------------------------------------------------------------------------------------------------------------

    static void updateStudent(Student student) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(dataBaseUrl
                    + "\\Students\\" + student.getId() + ".txt"));
            objectOutputStream.writeObject(student);
            objectOutputStream.close();
        } catch (Exception e) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println(RED + "Couldn't update student! (Exception)" + RESET);
            System.exit(1384);
        }
    } //--- Completed ---//

    static Student getStudentFromDataBase(String id) {
        try {
            File dir = new File(dataBaseUrl + "\\Students");
            File[] files = dir.listFiles();

            for (File file : files) {
                if (file.getName().equals(id + ".txt")) {
                    ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(dataBaseUrl
                            + "\\Students\\" + id + ".txt"));
                    Student student = (Student) objectInputStream.readObject();
                    objectInputStream.close();
                    return student;
                }
            }
        } catch (Exception e) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println(RED + "Couldn't get student! (Exception)" + RESET);
            System.exit(1384);
        }
        return null;
    } //--- Completed ---//

    private static boolean studentIdJustNumber(String id) {
        return id.matches("^[0-9]+$");
    } //--- Completed ---//

    static void removeStudent(String id) {
        for (Course course : getStudentFromDataBase(id).getCourses()) {
            course.removeStudent(getStudentFromDataBase(id));
        }

        File file = new File(dataBaseUrl + "\\Students\\" + id + ".txt");
        file.delete();
        file = new File(dataBaseUrl + "\\Tasks\\" + id + ".txt");
        file.delete();
    } //--- Completed ---//

    static void addStudent(String id, String pass, String name, String lastname) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(dataBaseUrl + "\\Students\\"
                    + id + ".txt"));

            objectOutputStream.writeObject(new Student(name, lastname, id, pass));

            objectOutputStream.close();

            File file = new File(dataBaseUrl + "\\Tasks\\" + id + ".txt");
            file.createNewFile();
        } catch (Exception e) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println(RED + "Couldn't add student! (Exception)" + RESET);
            System.exit(1384);
        }
    } //--- Completed ---//

    static boolean findStudentId(String id) {
        File dir = new File(dataBaseUrl + "\\Students");
        File[] files = dir.listFiles();

        if (files == null) {
            return false;
        }

        for (File file : files) {
            if (file.getName().equals(id + ".txt")) {
                return true;
            }
        }

        return false;
    } //--- Completed ---//

    private static boolean studentIdValidation(String id) {
        return !findStudentId(id);
    } //--- Completed ---//

//----------------------------------------------------------------------------------------------------------------------

    static void updateCourse(Course course) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(dataBaseUrl
                    + "\\Courses\\" + course.getName() + ".txt"));
            objectOutputStream.writeObject(course);
            objectOutputStream.close();
        } catch (Exception e) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println(RED + "Couldn't update course! (Exception)" + RESET);
            System.exit(1384);
        }
    } //--- Completed ---//

    static Course getCourseFromDataBase(String name) {
        try {
            File dir = new File(dataBaseUrl + "\\Courses");
            File[] files = dir.listFiles();

            for (File file : files) {
                if (file.getName().equals(name + ".txt")) {
                    ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(dataBaseUrl
                            + "\\Courses\\" + name + ".txt"));
                    Course course = (Course) objectInputStream.readObject();
                    objectInputStream.close();
                    return course;
                }
            }
        } catch (Exception e) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println(RED + "Couldn't get course! (Exception)" + RESET);
            System.exit(1384);
        }
        return null;
    } //--- Completed ---//

    private static void deleteDirectory(File file) {
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                deleteDirectory(f);
            } else {
                f.delete();
            }
        }
        file.delete();
    } //--- Completed ---//

    private static void removeCourse(String name) {
        File file = new File(dataBaseUrl + "\\Courses\\" + name + ".txt");
        file.delete();
        deleteDirectory(new File(dataBaseUrl + "\\Courses\\" + name));
    } //--- Completed ---//

    static boolean findCourseName(String name) {
        File dir = new File(dataBaseUrl + "\\Courses");
        File[] files = dir.listFiles();

        if (files == null) {
            return false;
        }

        for (File file : files) {
            if (file.getName().equals(name + ".txt")) {
                return true;
            }
        }
        return false;
    } //--- Completed ---//

    private static boolean courseNameValidation(String name) {
        return !findCourseName(name);
    } //--- Completed ---//

    private static void addCourse(String name, int units, int examDate) {
        try {
            File dir = new File(dataBaseUrl + "\\Courses\\" + name);
            dir.mkdirs();
            File dir1 = new File(dataBaseUrl + "\\Courses\\" + name + "\\" + "Project");
            dir1.mkdirs();

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(dataBaseUrl + "\\Courses\\"
                    + name + ".txt"));

            objectOutputStream.writeObject(new Course(name, units, examDate));

            objectOutputStream.close();
        } catch (Exception e) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println(RED + "Couldn't add course! (Exception)" + RESET);
            System.exit(1384);
        }
    } //--- Completed ---//

//----------------------------------------------------------------------------------------------------------------------

    private static int teacherLoginState(String username, String pass) {
        try {
            File dir = new File(dataBaseUrl + "\\Teachers");
            File[] files = dir.listFiles();

            if (files == null) {
                return 1;
            }

            for (File file : files) {
                if (file.getName().equals(username + ".txt")) {
                    ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(dataBaseUrl
                            + "\\Teachers\\" + username + ".txt"));
                    Teacher teacher = (Teacher) objectInputStream.readObject();
                    objectInputStream.close();
                    if (teacher.getPassword().equals(pass)) {
                        return 0;
                    } else {
                        return 2;
                    }
                }
            }
        } catch (Exception e) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println(RED + "Couldn't login! (Exception)" + RESET);
            System.exit(1384);
        }
        return 1;
    } //--- Completed ---//

    static void updateTeacher(Teacher teacher) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(dataBaseUrl
                    + "\\Teachers\\" + teacher.getUsername() + ".txt"));
            objectOutputStream.writeObject(teacher);
            objectOutputStream.close();
        } catch (Exception e) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println(RED + "Couldn't update teacher! (Exception)" + RESET);
            System.exit(1384);
        }
    } //--- Completed ---//

    static Teacher getTeacherFromDataBase(String username) {
        try {
            File dir = new File(dataBaseUrl + "\\Teachers");
            File[] files = dir.listFiles();

            for (File file : files) {
                if (file.getName().equals(username + ".txt")) {
                    ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(dataBaseUrl
                            + "\\Teachers\\" + username + ".txt"));
                    Teacher teacher = (Teacher) objectInputStream.readObject();
                    objectInputStream.close();
                    return teacher;
                }
            }
        } catch (Exception e) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println(RED + "Couldn't get teacher! (Exception)" + RESET);
            System.exit(1384);
        }
        return null;
    } //--- Completed ---//

    private static void removeTeacher(String username) {
        File file = new File(dataBaseUrl + "\\Teachers\\" + username + ".txt");
        file.delete();
    } //--- Completed ---//

    private static boolean passValidation(String pass, String username) {
        Pattern pattern1 = Pattern.compile(".*[A-Z].*");
        Pattern pattern2 = Pattern.compile(".*[a-z].*");
        Pattern pattern3 = Pattern.compile(".*\\d.*");
        return pass.length() >= 8 && !pass.contains(username) && pattern1.matcher(pass).matches() &&
                pattern2.matcher(pass).matches() && pattern3.matcher(pass).matches();
    } //--- Completed ---//

    private static boolean findTeacherUsername(String username) {
        File[] files = new File(dataBaseUrl + "\\Teachers").listFiles();

        if (files == null) {
            return false;
        }

        for (File file : files) {
            if (file.getName().equals(username + ".txt")) {
                return true;
            }
        }

        return false;
    } //--- Completed ---//

    private static boolean teacherUserValidation(String username) {
        return !findTeacherUsername(username);
    } //--- Completed ---//

    private static void addTeacher(String username, String pass, String name, String lastname) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(dataBaseUrl
                    + "\\Teachers\\" + username + ".txt"));

            objectOutputStream.writeObject(new Teacher(name, lastname, username, pass));

            objectOutputStream.close();
        } catch (Exception exception) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println(RED + "Couldn't add teacher! (Exception)" + RESET);
            System.exit(1384);
        }
    } //--- Completed ---//

//----------------------------------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println(GREEN + "WELCOME" + RESET);
        } //--- Welcome

        Scanner input = new Scanner(System.in);

        out:
        while (true) {
            {
                System.out.print(BLUE + """
                        What is your access?
                        (admin = 1, teacher = 2)
                        """ + RESET);
            } //--- Choose access

            int access = input.nextInt();

            switch (access) {
                case 1: {
                    {
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println(GREEN + "Welcome Admin" + RESET);
                    } //--- Welcome admin

                    out1:
                    while (true) {
                        {
                            System.out.print(BLUE + """
                                    Choose your order:
                                    1 - Add new teacher
                                    2 - Remove a teacher
                                    3 - Add new course
                                    4 - Remove a course
                                    5 - Add new student
                                    6 - Remove a student
                                    7 - Add a course for a teacher
                                    8 - Remove a course from a teacher
                                    9 - Add a student to a course
                                    10 - Remove a student from a course
                                    11 - Show students of a course
                                    12 - Show score of a student
                                    13 - Show courses of a student
                                    14 - Show student average
                                    15 - Show courses of a teacher
                                    16 - Exit
                                    """ + RESET);
                        } //--- Choose order
                        int order = input.nextInt();

                        switch (order) {
                            case 1: {
                                {
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println(BLUE + "Task -> Add teacher" + RESET);
                                } //--- Show task

                                String username;
                                String pass1;
                                String pass2;
                                String name;
                                String lastname;

                                {
                                    System.out.println(BLUE + "Enter teacher username" + RESET);
                                    username = input.next();
                                    while (!teacherUserValidation(username)) {
                                        System.out.println(RED + "This username has already been used! Try again" + RESET);
                                        username = input.next();
                                    }

                                    System.out.println(BLUE + "Enter teacher password" + RESET);
                                    pass1 = input.next();
                                    while (!passValidation(pass1, username)) {
                                        System.out.println(RED + "Wrong password! Try again" + RESET);
                                        pass1 = input.next();
                                    }
                                    System.out.println(BLUE + "Verify password" + RESET);
                                    pass2 = input.next();
                                    while (!pass1.equals(pass2)) {
                                        System.out.println(RED + "Doesn't match! Try again" + RESET);
                                        pass2 = input.next();
                                    }

                                    System.out.println(BLUE + "Enter teacher name" + RESET);
                                    name = input.next();
                                    System.out.println(BLUE + "Enter teacher lastname" + RESET);
                                    lastname = input.next();
                                } //--- Get info

                                addTeacher(username, pass1, name, lastname);

                                {
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println(GREEN + "Teacher successfully added\n" + RESET);
                                } //--- End task

                                break;
                            } //--- Add new teacher ---//
                            case 2: {
                                {
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println(BLUE + "Task -> Remove teacher" + RESET);
                                } //--- Show task

                                String username;

                                {
                                    System.out.println(BLUE + "Enter teacher username to remove" + RESET);
                                    username = input.next();
                                    while (!findTeacherUsername(username)) {
                                        System.out.println(RED + "Username not found! Try again" + RESET);
                                        username = input.next();
                                    }
                                } //--- Get info

                                removeTeacher(username);

                                {
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println(GREEN + "Teacher successfully removed\n" + RESET);
                                } //--- End task

                                break;
                            } //--- Remove teacher ---//
                            case 3: {
                                {
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println(BLUE + "Task -> Add course" + RESET);
                                } //--- Show task

                                String name;
                                int units;
                                int examDate;

                                {
                                    System.out.println(BLUE + "Enter course name" + RESET);
                                    name = input.next();
                                    while (!courseNameValidation(name)) {
                                        System.out.println(RED + "This name has already been used! Try again" + RESET);
                                        name = input.next();
                                    }

                                    System.out.println(BLUE + "Enter course units" + RESET);
                                    units = input.nextInt();

                                    System.out.println(BLUE + "Enter exam date" + RESET);
                                    examDate = input.nextInt();
                                } //--- Get info

                                addCourse(name, units, examDate);

                                {
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println(GREEN + "Course successfully added\n" + RESET);
                                } //--- End task

                                break;
                            } //--- Add course ---//
                            case 4: {
                                {
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println(BLUE + "Task -> Remove course" + RESET);
                                } //--- Show task

                                String name;

                                {
                                    System.out.println(BLUE + "Enter course name" + RESET);
                                    name = input.next();
                                    while (!findCourseName(name)) {
                                        System.out.println(RED + "Course not found! Try again" + RESET);
                                        name = input.next();
                                    }
                                } //--- Get info

                                removeCourse(name);

                                {
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println(GREEN + "Course successfully removed\n" + RESET);
                                } //--- End task

                                break;
                            } //--- Remove course ---//
                            case 5: {
                                {
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println(BLUE + "Task -> Add student" + RESET);
                                } //--- Show task

                                String id;
                                String pass1;
                                String pass2;
                                String name;
                                String lastname;

                                {
                                    System.out.println(BLUE + "Enter student id" + RESET);
                                    id = input.next();
                                    while (!studentIdJustNumber(id)) {
                                        System.out.println(RED + "Id can only contain numbers! Try again" + RESET);
                                        id = input.next();
                                    }
                                    while (!studentIdValidation(id)) {
                                        System.out.println(RED + "This id has already been used! Try again" + RESET);
                                        id = input.next();
                                    }

                                    System.out.println(BLUE + "Enter student password" + RESET);
                                    pass1 = input.next();
                                    while (!passValidation(pass1, id)) {
                                        System.out.println(RED + "Wrong password! Try again" + RESET);
                                        pass1 = input.next();
                                    }
                                    System.out.println(BLUE + "Verify password" + RESET);
                                    pass2 = input.next();
                                    while (!pass1.equals(pass2)) {
                                        System.out.println(RED + "Doesn't match! Try again" + RESET);
                                        pass2 = input.next();
                                    }

                                    System.out.println(BLUE + "Enter student name" + RESET);
                                    name = input.next();
                                    System.out.println(BLUE + "Enter student lastname" + RESET);
                                    lastname = input.next();
                                } //--- Get info

                                addStudent(id, pass1, name, lastname);

                                {
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println(GREEN + "Student successfully added\n" + RESET);
                                } //--- End task

                                break;
                            } //--- Add student ---//
                            case 6: {
                                {
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println(BLUE + "Task -> Remove student" + RESET);
                                } //--- Show task

                                String id;

                                {
                                    System.out.println(BLUE + "Enter student id to remove" + RESET);
                                    id = input.next();
                                    while (!findStudentId(id)) {
                                        System.out.println(RED + "Id not found! Try again" + RESET);
                                        id = input.next();
                                    }
                                } //--- Get info

                                removeStudent(id);

                                {
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println(GREEN + "Student successfully removed\n" + RESET);
                                } //--- End task

                                break;
                            } //--- Remove student ---//
                            case 7: {
                                {
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println(BLUE + "Task -> Add a course for a teacher" + RESET);
                                } //--- Show task

                                String username;
                                String name;

                                {
                                    System.out.println(BLUE + "Enter teacher username" + RESET);
                                    username = input.next();
                                    while (!findTeacherUsername(username)) {
                                        System.out.println(RED + "Username not found! Try again" + RESET);
                                        username = input.next();
                                    }

                                    System.out.println(BLUE + "Enter course name" + RESET);
                                    name = input.next();
                                    while (!findCourseName(name)) {
                                        System.out.println(RED + "Course not found! Try again" + RESET);
                                        name = input.next();
                                    }
                                } //--- Get info

                                Teacher teacher = getTeacherFromDataBase(username);
                                Course course = getCourseFromDataBase(name);

                                switch (teacher.addCourse(course)) {
                                    case 1:
                                        System.out.print("\033[H\033[2J");
                                        System.out.flush();
                                        System.out.println(RED + "This teacher already has this course\n" + RESET);
                                        break;
                                    case 2:
                                        System.out.print("\033[H\033[2J");
                                        System.out.flush();
                                        System.out.println(RED + "This course already has a teacher\n" + RESET);
                                        break;
                                    case 3:
                                        updateCourse(course);
                                        updateTeacher(teacher);
                                        System.out.print("\033[H\033[2J");
                                        System.out.flush();
                                        System.out.println(GREEN + "Successfully added\n" + RESET);
                                        break;
                                } //--- End task

                                break;
                            } //--- Add a course for a teacher ---//
                            case 8: {
                                {
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println(BLUE + "Task -> Remove a course from a teacher" + RESET);
                                } //--- Show task

                                String username;
                                String name;

                                {
                                    System.out.println(BLUE + "Enter teacher username" + RESET);
                                    username = input.next();
                                    while (!findTeacherUsername(username)) {
                                        System.out.println(RED + "Username not found! Try again" + RESET);
                                        username = input.next();
                                    }

                                    System.out.println(BLUE + "Enter course name" + RESET);
                                    name = input.next();
                                    while (!findCourseName(name)) {
                                        System.out.println(RED + "Course not found! Try again" + RESET);
                                        name = input.next();
                                    }
                                } //--- Get info

                                Teacher teacher = getTeacherFromDataBase(username);
                                Course course = getCourseFromDataBase(name);

                                switch (teacher.removeCourse(course)) {
                                    case 1:
                                        System.out.print("\033[H\033[2J");
                                        System.out.flush();
                                        System.out.println(RED + "This teacher doesn't have this course\n" + RESET);
                                        break;
                                    case 2:
                                        updateCourse(course);
                                        updateTeacher(teacher);
                                        System.out.print("\033[H\033[2J");
                                        System.out.flush();
                                        System.out.println(GREEN + "Successfully removed\n" + RESET);
                                        break;
                                } //--- End task

                                break;
                            } //--- Remove a course from a teacher ---//
                            case 9: {
                                {
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println(BLUE + "Task -> Add a student to a course" + RESET);
                                } //--- Show task

                                String id;
                                String name;

                                {
                                    System.out.println(BLUE + "Enter student id" + RESET);
                                    id = input.next();
                                    while (!findStudentId(id)) {
                                        System.out.println(RED + "Id not found! Try again" + RESET);
                                        id = input.next();
                                    }

                                    System.out.println(BLUE + "Enter course name" + RESET);
                                    name = input.next();
                                    while (!findCourseName(name)) {
                                        System.out.println(RED + "Course not found! Try again" + RESET);
                                        name = input.next();
                                    }
                                } //--- Get info

                                Student student = getStudentFromDataBase(id);
                                Course course = getCourseFromDataBase(name);

                                switch (course.addStudent(student)) {
                                    case 1:
                                        System.out.print("\033[H\033[2J");
                                        System.out.flush();
                                        System.out.println(RED + "This student is already in this course\n" + RESET);
                                        break;
                                    case 2:
                                        updateStudent(student);
                                        updateCourse(course);
                                        for (Assignment assignment : course.getExercises()) {
                                            updateExercise(assignment);
                                        }
                                        if (course.getProject() != null) {
                                            updateProject(course.getProject());
                                        }
                                        System.out.print("\033[H\033[2J");
                                        System.out.flush();
                                        System.out.println(GREEN + "Successfully added\n" + RESET);
                                        break;
                                } //--- End task

                                break;
                            } //--- Add a student to a course ---//
                            case 10: {
                                {
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println(BLUE + "Task -> Remove a student from a course" + RESET);
                                } //--- Show task

                                String id;
                                String name;

                                {
                                    System.out.println(BLUE + "Enter student id" + RESET);
                                    id = input.next();
                                    while (!findStudentId(id)) {
                                        System.out.println(RED + "Id not found! Try again" + RESET);
                                        id = input.next();
                                    }

                                    System.out.println(BLUE + "Enter course name" + RESET);
                                    name = input.next();
                                    while (!findCourseName(name)) {
                                        System.out.println(RED + "Course not found! Try again" + RESET);
                                        name = input.next();
                                    }
                                } //---Get info

                                Student student = getStudentFromDataBase(id);
                                Course course = getCourseFromDataBase(name);

                                switch (course.removeStudent(student)) {
                                    case 1:
                                        System.out.print("\033[H\033[2J");
                                        System.out.flush();
                                        System.out.println(RED + "This student isn't in this course\n" + RESET);
                                        break;
                                    case 2:
                                        updateCourse(course);
                                        updateStudent(student);
                                        for (Assignment assignment : course.getExercises()) {
                                            updateExercise(assignment);
                                        }
                                        if (course.getProject() != null) {
                                            updateProject(course.getProject());
                                        }
                                        System.out.print("\033[H\033[2J");
                                        System.out.flush();
                                        System.out.println(GREEN + "Successfully removed\n" + RESET);
                                        break;
                                } //--- End task

                                break;
                            } //--- Remove a student from a course ---//
                            case 11: {
                                {
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println(BLUE + "Task -> Show students of a course" + RESET);
                                } //--- Show task

                                String name;

                                {
                                    System.out.println(BLUE + "Enter course name" + RESET);
                                    name = input.next();
                                    while (!findCourseName(name)) {
                                        System.out.println(RED + "Course not found! Try again" + RESET);
                                        name = input.next();
                                    }
                                } //--- Get info

                                Course course = getCourseFromDataBase(name);
                                course.printStudents();

                                break;
                            } //--- Show students of a course ---//
                            case 12: {
                                {
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println(BLUE + "Task -> Show score of a student" + RESET);
                                } //--- Show task

                                String id;
                                String name;

                                {
                                    System.out.println(BLUE + "Enter student id" + RESET);
                                    id = input.next();
                                    while (!findStudentId(id)) {
                                        System.out.println(RED + "Id not found! Try again" + RESET);
                                        id = input.next();
                                    }

                                    System.out.println(BLUE + "Enter course name" + RESET);
                                    name = input.next();
                                    while (!findCourseName(name)) {
                                        System.out.println(RED + "Course not found! Try again" + RESET);
                                        name = input.next();
                                    }
                                } //--- Get info

                                Course course = getCourseFromDataBase(name);
                                Student student = getStudentFromDataBase(id);

                                if (course.getScore(student) == -1) {
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println(RED + "This student isn't in this course\n" + RESET);
                                } else {
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println(GREEN + "Score: " + String.format("%.2f", course.getScore(student)) + "\n" + RESET);
                                }

                                break;
                            } //--- Show score of a student ---//
                            case 13: {
                                {
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println(BLUE + "Task -> Show courses of a student" + RESET);
                                } //--- Show task

                                String id;

                                {
                                    System.out.println(BLUE + "Enter student id" + RESET);
                                    id = input.next();
                                    while (!findStudentId(id)) {
                                        System.out.println(RED + "Id not found! Try again" + RESET);
                                        id = input.next();
                                    }
                                } //--- Get info

                                Student student = getStudentFromDataBase(id);
                                student.printCourses();

                                break;
                            } //--- Show courses of a student ---//
                            case 14: {
                                {
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println(BLUE + "Task -> Show student average" + RESET);
                                } //--- Show task

                                String id;

                                {
                                    System.out.println(BLUE + "Enter student id" + RESET);
                                    id = input.next();
                                    while (!findStudentId(id)) {
                                        System.out.println(RED + "Id not found! Try again" + RESET);
                                        id = input.next();
                                    }
                                } //--- Get info

                                Student student = getStudentFromDataBase(id);
                                student.printTotalAverage();

                                break;
                            } //--- Show student average ---//
                            case 15: {
                                {
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println(BLUE + "Task -> Show courses of a teacher" + RESET);
                                } //--- Show task

                                String username;

                                {
                                    System.out.println(BLUE + "Enter teacher username to remove" + RESET);
                                    username = input.next();
                                    while (!findTeacherUsername(username)) {
                                        System.out.println(RED + "Username not found! Try again" + RESET);
                                        username = input.next();
                                    }
                                } //--- Get info

                                Teacher teacher = getTeacherFromDataBase(username);
                                teacher.printCourses();

                                break;
                            } //--- Show courses of a teacher ---//
                            case 16: {
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                System.out.println(RED + "Exited" + RESET);
                                break out1;
                            } //--- Exit ---//
                            default: {
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                System.out.println(RED + "Wrong answer! Try again" + RESET);
                            } //--- Wrong answer ---//
                        }
                    }
                    break out;
                }
                case 2: {
                    String username;
                    String pass;

                    {
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println(BLUE + "Enter Your username" + RESET);
                        username = input.next();

                        System.out.println(BLUE + "Enter Your password" + RESET);
                        pass = input.next();


                        while (teacherLoginState(username, pass) != 0) {
                            switch (teacherLoginState(username, pass)) {
                                case 1:
                                    System.out.println(RED + "Username not found! Try again" + RESET);
                                    break;
                                case 2:
                                    System.out.println(RED + "Wrong password! Try again" + RESET);
                                    break;
                            }

                            System.out.println(BLUE + "Enter Your username" + RESET);
                            username = input.next();
                            System.out.println(BLUE + "Enter Your password" + RESET);
                            pass = input.next();
                        }

                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println(GREEN + "Welcome Teacher\n" + RESET);
                    } //--- Get info

                    out1:
                    while (true) {
                        {
                            System.out.print(BLUE + """
                                    Choose your order:
                                    1 - Add new student to a course
                                    2 - Removing a student from a course
                                    3 - Adding new exercise to a course
                                    4 - Removing an exercise from a course
                                    5 - Defining project for a course
                                    6 - Removing project from a course
                                    7 - Change deadline of an assignment or project
                                    8 - Add score to a student
                                    9 - Exit
                                    """ + RESET);
                        } //--- Choose order
                        int order = input.nextInt();

                        switch (order) {
                            case 1: {
                                {
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println(BLUE + "Task -> Add new student to a course" + RESET);
                                } //--- Show task

                                String id;
                                String name;

                                {
                                    System.out.println(BLUE + "Enter student id" + RESET);
                                    id = input.next();
                                    while (!findStudentId(id)) {
                                        System.out.println(RED + "Id not found! Try again" + RESET);
                                        id = input.next();
                                    }

                                    System.out.println(BLUE + "Enter course name" + RESET);
                                    name = input.next();
                                    while (!findCourseName(name)) {
                                        System.out.println(RED + "Course not found! Try again" + RESET);
                                        name = input.next();
                                    }
                                } //--- Get info

                                Student student = getStudentFromDataBase(id);
                                Course course = getCourseFromDataBase(name);
                                Teacher teacher = getTeacherFromDataBase(username);

                                switch (teacher.addStudentToCourse(student, course)) {
                                    case 1:
                                        System.out.print("\033[H\033[2J");
                                        System.out.flush();
                                        System.out.println(RED + "This student is already in this course\n" + RESET);
                                        break;
                                    case 2:
                                        updateCourse(course);
                                        updateStudent(student);
                                        for (Assignment assignment : course.getExercises()) {
                                            updateExercise(assignment);
                                        }
                                        if (course.getProject() != null) {
                                            updateProject(course.getProject());
                                        }
                                        System.out.print("\033[H\033[2J");
                                        System.out.flush();
                                        System.out.println(GREEN + "Successfully added\n" + RESET);
                                        break;
                                    case 3:
                                        System.out.print("\033[H\033[2J");
                                        System.out.flush();
                                        System.out.println(RED + "You don't have access to this course\n" + RESET);
                                        break;
                                } //--- End task

                                break;
                            } //--- Add new student to a course ---//
                            case 2: {
                                {
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println(BLUE + "Task -> Removing a student from a course" + RESET);
                                } //--- Show task

                                String id;
                                String name;

                                {
                                    System.out.println(BLUE + "Enter student id" + RESET);
                                    id = input.next();
                                    while (!findStudentId(id)) {
                                        System.out.println(RED + "Id not found! Try again" + RESET);
                                        id = input.next();
                                    }

                                    System.out.println(BLUE + "Enter course name" + RESET);
                                    name = input.next();
                                    while (!findCourseName(name)) {
                                        System.out.println(RED + "Course not found! Try again" + RESET);
                                        name = input.next();
                                    }
                                } //--- Get info

                                Student student = getStudentFromDataBase(id);
                                Course course = getCourseFromDataBase(name);
                                Teacher teacher = getTeacherFromDataBase(username);

                                switch (teacher.removeStudentFromCourse(student, course)) {
                                    case 1:
                                        System.out.print("\033[H\033[2J");
                                        System.out.flush();
                                        System.out.println(RED + "This student isn't in this course\n" + RESET);
                                        break;
                                    case 2:
                                        updateCourse(course);
                                        updateStudent(student);
                                        for (Assignment assignment : course.getExercises()) {
                                            updateExercise(assignment);
                                        }
                                        if (course.getProject() != null) {
                                            updateProject(course.getProject());
                                        }
                                        System.out.print("\033[H\033[2J");
                                        System.out.flush();
                                        System.out.println(GREEN + "Successfully removed\n" + RESET);
                                        break;
                                    case 3:
                                        System.out.print("\033[H\033[2J");
                                        System.out.flush();
                                        System.out.println(RED + "You don't have access to this course\n" + RESET);
                                        break;
                                } //--- End task

                                break;
                            } //--- Removing a student from a course ---//
                            case 3: {
                                {
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println(BLUE + "Task -> Adding new exercise to a course" + RESET);
                                } //--- Show task

                                String name;
                                String title;
                                int deadLine;

                                {
                                    System.out.println(BLUE + "Enter course name" + RESET);
                                    name = input.next();
                                    while (!findCourseName(name)) {
                                        System.out.println(RED + "Course not found! Try again" + RESET);
                                        name = input.next();
                                    }

                                    System.out.println(BLUE + "Enter exercise title" + RESET);
                                    title = input.next();
                                    System.out.println(BLUE + "Enter exercise deadline" + RESET);
                                    deadLine = input.nextInt();
                                } //--- Get info

                                Course course = getCourseFromDataBase(name);
                                Teacher teacher = getTeacherFromDataBase(username);

                                if (addExercise(title, course, deadLine)) {

                                    Assignment assignment = getExerciseFromDataBase(title, course.getName());

                                    switch (teacher.addExerciseToCourse(assignment, course)) {
                                        case 1:
                                            removeExercise(assignment);
                                            System.out.print("\033[H\033[2J");
                                            System.out.flush();
                                            System.out.println(RED + "You don't have access to this course\n" + RESET);
                                            break;
                                        case 3:
                                            updateCourse(course);
                                            updateExercise(assignment);
                                            System.out.print("\033[H\033[2J");
                                            System.out.flush();
                                            System.out.println(GREEN + "Successfully added\n" + RESET);
                                            break;
                                    }
                                } else {
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println(RED + "This exercise is already defined\n" + RESET);
                                }

                                break;
                            } //--- Adding new exercise to a course ---//
                            case 4: {
                                {
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println(BLUE + "Task -> Removing a exercise from a course" + RESET);
                                } //--- Show task

                                String name;
                                String title;

                                {
                                    System.out.println(BLUE + "Enter course name" + RESET);
                                    name = input.next();
                                    while (!findCourseName(name)) {
                                        System.out.println(RED + "Course not found! Try again" + RESET);
                                        name = input.next();
                                    }

                                    System.out.println(BLUE + "Enter exercise title" + RESET);
                                    title = input.next();
                                    while (!findExercise(title, name)) {
                                        System.out.println(RED + "There is no such exercise! Try again" + RESET);
                                        title = input.next();
                                    }
                                } //--- Get info

                                Course course = getCourseFromDataBase(name);
                                Teacher teacher = getTeacherFromDataBase(username);
                                Assignment assignment = getExerciseFromDataBase(title, course.getName());

                                switch (teacher.removeExerciseFromCourse(assignment, course)) {
                                    case 1:
                                        System.out.print("\033[H\033[2J");
                                        System.out.flush();
                                        System.out.println(RED + "You don't have access to this course\n" + RESET);
                                        break;
                                    case 3:
                                        updateCourse(course);
                                        removeExercise(assignment);
                                        System.out.print("\033[H\033[2J");
                                        System.out.flush();
                                        System.out.println(GREEN + "successfully removed\n" + RESET);
                                        break;
                                }

                                break;
                            } //--- Removing an exercise from a course ---//
                            case 5: {
                                {
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println(BLUE + "Task -> Defining project for a course" + RESET);
                                } //--- Show task

                                String name;
                                String title;
                                int deadLine;

                                {
                                    System.out.println(BLUE + "Enter course name" + RESET);
                                    name = input.next();
                                    while (!findCourseName(name)) {
                                        System.out.println(RED + "Course not found! Try again" + RESET);
                                        name = input.next();
                                    }

                                    System.out.println(BLUE + "Enter project title" + RESET);
                                    title = input.next();
                                    System.out.println(BLUE + "Enter project deadline" + RESET);
                                    deadLine = input.nextInt();
                                } //--- Get info

                                Course course = getCourseFromDataBase(name);
                                Teacher teacher = getTeacherFromDataBase(username);

                                if (addProject(title, course, deadLine)) {

                                    Assignment project = getProjectFromDataBase(title, course.getName());

                                    switch (teacher.addProjectToCourse(project, course)) {
                                        case 1:
                                            removeProject(project);
                                            System.out.print("\033[H\033[2J");
                                            System.out.flush();
                                            System.out.println(RED + "You don't have access to this course\n" + RESET);
                                            break;
                                        case 3:
                                            updateCourse(course);
                                            System.out.print("\033[H\033[2J");
                                            System.out.flush();
                                            System.out.println(GREEN + "Successfully defined\n" + RESET);
                                            break;
                                    }
                                } else {
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println(RED + "Project is already defined\n" + RESET);
                                }

                                break;
                            } //--- Defining project for a course ---//
                            case 6: {
                                {
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println(BLUE + "Task -> Removing project from a course" + RESET);
                                } //--- Show task

                                String name;
                                String title;

                                {
                                    System.out.println(BLUE + "Enter course name" + RESET);
                                    name = input.next();
                                    while (!findCourseName(name)) {
                                        System.out.println(RED + "Course not found! Try again" + RESET);
                                        name = input.next();
                                    }

                                    System.out.println(BLUE + "Enter project title" + RESET);
                                    title = input.next();
                                    while (!findProject(title, name)) {
                                        System.out.println(RED + "There is no such project! Try again" + RESET);
                                        title = input.next();
                                    }
                                } //--- Get info

                                Course course = getCourseFromDataBase(name);
                                Teacher teacher = getTeacherFromDataBase(username);
                                Assignment project = getProjectFromDataBase(title, course.getName());

                                switch (teacher.removeProjectFromCourse(project, course)) {
                                    case 1:
                                        System.out.print("\033[H\033[2J");
                                        System.out.flush();
                                        System.out.println(RED + "You don't have access to this course\n" + RESET);
                                        break;
                                    case 2:
                                        updateCourse(course);
                                        removeProject(project);
                                        System.out.print("\033[H\033[2J");
                                        System.out.flush();
                                        System.out.println(GREEN + "Successfully removed\n" + RESET);
                                        break;
                                }

                                break;
                            } //--- Removing project from a course ---//
                            case 7: {
                                {
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println(BLUE + "Task -> Change deadline of an assignment or project" + RESET);
                                } //--- Show task

                                String name;
                                String title = "";
                                int isProject;
                                int newDeadLine;

                                {
                                    System.out.println(BLUE + "Enter course name" + RESET);
                                    name = input.next();
                                    while (!findCourseName(name)) {
                                        System.out.println(RED + "Course not found! Try again" + RESET);
                                        name = input.next();
                                    }

                                    System.out.println(BLUE + "You want to change deadline of an exercise or project?"
                                            + " (exercise = 1, project = 2)" + RESET);
                                    isProject = input.nextInt();

                                    System.out.println(BLUE + "Enter new deadline" + RESET);
                                    newDeadLine = input.nextInt();

                                    switch (isProject) {
                                        case 1:
                                            System.out.println(BLUE + "Enter exercise title" + RESET);
                                            title = input.next();
                                            while (!findExercise(title, name)) {
                                                System.out.println(RED + "There is no such exercise! Try again" + RESET);
                                                title = input.next();
                                            }
                                            break;
                                        case 2:
                                            System.out.println(BLUE + "Enter project title" + RESET);
                                            title = input.next();
                                            while (!findProject(title, name)) {
                                                System.out.println(RED + "There is no such project! Try again" + RESET);
                                                title = input.next();
                                            }
                                            break;
                                    }
                                } //--- Get info

                                Teacher teacher = getTeacherFromDataBase(username);
                                Assignment assignment = switch (isProject) {
                                    case 1 -> getExerciseFromDataBase(title, name);
                                    case 2 -> getProjectFromDataBase(title, name);
                                    default -> null;
                                };

                                switch (teacher.changeDeadLine(assignment, newDeadLine)) {
                                    case 1:
                                        System.out.print("\033[H\033[2J");
                                        System.out.flush();
                                        System.out.println(RED + "You don't have access to this course\n" + RESET);
                                        break;
                                    case 2:
                                        switch (isProject) {
                                            case 1:
                                                updateExercise(assignment);
                                                break;
                                            case 2:
                                                updateProject(assignment);
                                                break;
                                        }
                                        System.out.print("\033[H\033[2J");
                                        System.out.flush();
                                        System.out.println(GREEN + "Deadline successfully changed\n" + RESET);
                                }

                                break;
                            } //--- Change deadline of an assignment or project ---//
                            case 8: {
                                {
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println(BLUE + "Task -> Add score to a student" + RESET);
                                } //--- Show task

                                String id;
                                String name;
                                double score;

                                {
                                    System.out.println(BLUE + "Enter student id" + RESET);
                                    id = input.next();
                                    while (!findStudentId(id)) {
                                        System.out.println(RED + "Id not found! Try again" + RESET);
                                        id = input.next();
                                    }

                                    System.out.println(BLUE + "Enter course name" + RESET);
                                    name = input.next();
                                    while (!findCourseName(name)) {
                                        System.out.println(RED + "Course not found! Try again" + RESET);
                                        name = input.next();
                                    }

                                    System.out.println(BLUE + "Enter score" + RESET);
                                    score = input.nextDouble();
                                } //--- Get info

                                Teacher teacher = getTeacherFromDataBase(username);
                                Student student = getStudentFromDataBase(id);
                                Course course = getCourseFromDataBase(name);

                                switch (teacher.addScoreToStudent(student, course, score)) {
                                    case 1:
                                        System.out.print("\033[H\033[2J");
                                        System.out.flush();
                                        System.out.println(RED + "You don't have access to this course\n" + RESET);
                                        break;
                                    case 2:
                                        System.out.print("\033[H\033[2J");
                                        System.out.flush();
                                        System.out.println(RED + "This student isn't in this course\n" + RESET);
                                        break;
                                    case 3:
                                        updateCourse(course);
                                        System.out.print("\033[H\033[2J");
                                        System.out.flush();
                                        System.out.println(GREEN + "Successfully added score\n" + RESET);
                                        break;
                                }

                                break;
                            } //--- Add score to a student ---//
                            case 9: {
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                System.out.println(RED + "Exited" + RESET);
                                break out1;
                            } //--- Exit ---//
                            default: {
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                System.out.println(RED + "Wrong answer! Try again" + RESET);
                            } //--- Wrong answer ---//
                        }
                    }
                    break out;
                }
                default: {
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println(RED + "Wrong answer! Try again" + RESET);
                }
            }

        }
    }
}
