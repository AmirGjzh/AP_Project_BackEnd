import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class Main {

    static String RESET = "\u001B[0m";
    static String RED = "\u001B[31m";
    static String GREEN = "\u001B[32m";
    static String BLUE = "\u001B[34m";
    static String YELLOW = "\u001B[33m";

    public static void main(String[] args) {

        System.out.print("\033[H\033[2J");
        System.out.flush();

        Scanner input = new Scanner(System.in);

        System.out.println(YELLOW + "|------------------------- WELCOME ------------------------|" + RESET);

        out:
        while (true) {
            System.out.print(GREEN + """
                    |----------------- What is your access? -----------------|
                    |---------------- (admin = 1, teacher = 2) ----------------|
                    """ + RESET);
            int access = input.nextInt();

            switch (access) {
                case 1: {
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println(GREEN + "|---------------------- Welcome Admin ---------------------|" + RESET);

                    out1:
                    while (true) {
                        System.out.print(BLUE + """
                                Choose your order:
                                1- Adding new teacher
                                2- Removing a teacher
                                3- Adding new course
                                4- Removing a course
                                5- Adding new student
                                6- Removing a student
                                7- Adding course for teacher
                                9- Removing course from teacher
                                10- Exit.
                                """ + RESET);
                        int order = input.nextInt();

                        switch (order) {
                            case 1: {
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                System.out.println(GREEN + "|----------------- Task -> Adding teacher -----------------|" + RESET);



                                System.out.println(BLUE + "|----------------- Enter teacher username -----------------|" + RESET);
                                String username = input.next();
                                while (!teacherUserValidation(username)) {
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println(RED + "|---- This username has already been used! Try again ------|" + RESET);
                                    username = input.next();
                                }
                                System.out.print("\033[H\033[2J");
                                System.out.flush();



                                System.out.println(BLUE + "|----------------- Enter teacher password -----------------|" + RESET);
                                String pass1 = input.next();
                                while (!passValidation(pass1, username)) {
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println(RED + "|--------------- Wrong password! Try again ----------------|" + RESET);
                                    pass1 = input.next();
                                }
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                System.out.println(BLUE + "|----------------- Verify teacher password ----------------|" + RESET);
                                String pass2 = input.next();
                                while (!pass1.equals(pass2)) {
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println(RED + "|---------------- Doesn't match! Try again ----------------|" + RESET);
                                    pass2 = input.next();
                                }
                                System.out.print("\033[H\033[2J");
                                System.out.flush();



                                System.out.println(BLUE + "|------------------- Enter teacher name --------------------|" + RESET);
                                input.next();
                                String name = input.nextLine();
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                System.out.println(BLUE + "|----------------- Enter teacher lastname -----------------|" + RESET);
                                String lastname = input.nextLine();
                                System.out.print("\033[H\033[2J");
                                System.out.flush();



                                addTeacher(username, pass1, name, lastname);
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                System.out.println(YELLOW + "|--------------- Teacher successfully added ---------------|\n" + RESET);
                                break;
                            }
                            case 2: {
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                System.out.println(GREEN + "|---------------- Task -> Removing teacher ----------------|" + RESET);


                                System.out.println(BLUE + "|------------ Enter teacher username to remove ------------|" + RESET);
                                String username = input.next();
                                while (!findTeacherUsername(username)) {
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println(RED + "|-------------- Username not found! Try again -------------|" + RESET);
                                    username = input.next();
                                }

                                removeTeacher(username);

                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                System.out.println(YELLOW + "|-------------- Teacher successfully removed --------------|\n" + RESET);
                                break;
                            }
                            case 3: {
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                System.out.println(GREEN + "|------------------ Task -> Adding course -----------------|" + RESET);



                                System.out.println(BLUE + "|-------------------- Enter course name -------------------|" + RESET);
                                String name = input.next();
                                while (!courseNameValidation(name)) {
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println(RED + "|------- This name has already been used! Try again -------|" + RESET);
                                    name = input.next();
                                }

                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                System.out.println(BLUE + "|------------------- Enter course units -------------------|" + RESET);
                                int units = input.nextInt();

                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                System.out.println(BLUE + "|--------------------- Enter exam date --------------------|" + RESET);
                                String examDate = input.next();

                                addCourse(name, units, examDate);
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                System.out.println(YELLOW + "|---------------- Course successfully added ---------------|\n" + RESET);
                                break;
                            }
                            case 4: {
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                System.out.println(GREEN + "|---------------- Task -> Removing a course ---------------|" + RESET);


                                System.out.println(BLUE + "|-------------------- Enter course name -------------------|" + RESET);
                                String name = input.next();
                                while (!findCourseName(name)) {
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println(RED + "|------------ Course name not found! Try again ------------|" + RESET);
                                    name = input.next();
                                }



                                removeCourse(name);
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                System.out.println(YELLOW + "|--------------- Course successfully removed --------------|\n" + RESET);
                                break;
                            }
                            case 5: {
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                System.out.println(GREEN + "|----------------- Task -> Adding student -----------------|" + RESET);



                                System.out.println(BLUE + "|-------------------- Enter student id --------------------|" + RESET);
                                String id = input.next();
                                while (!studentIdValidation(id)) {
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println(RED + "|------- This id has already been used! Try again ---------|" + RESET);
                                    id = input.next();
                                }
                                System.out.print("\033[H\033[2J");
                                System.out.flush();



                                System.out.println(BLUE + "|----------------- Enter student password -----------------|" + RESET);
                                String pass1 = input.next();
                                while (!passValidation(pass1, id)) {
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println(RED + "|--------------- Wrong password! Try again ----------------|" + RESET);
                                    pass1 = input.next();
                                }
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                System.out.println(BLUE + "|---------------- Verify student password -----------------|" + RESET);
                                String pass2 = input.next();
                                while (!pass1.equals(pass2)) {
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println(RED + "|---------------- Doesn't match! Try again ----------------|" + RESET);
                                    pass2 = input.next();
                                }
                                System.out.print("\033[H\033[2J");
                                System.out.flush();



                                System.out.println(BLUE + "|------------------- Enter student name -------------------|" + RESET);
                                input.next();
                                String name = input.nextLine();
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                System.out.println(BLUE + "|----------------- Enter student lastname -----------------|" + RESET);
                                String lastname = input.nextLine();
                                System.out.print("\033[H\033[2J");
                                System.out.flush();



                                addStudent(id, pass1, name, lastname);
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                System.out.println(YELLOW + "|--------------- Student successfully added ---------------|\n" + RESET);
                                break;
                            }
                            case 6: {
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                System.out.println(GREEN + "|---------------- Task -> Removing student ----------------|" + RESET);


                                System.out.println(BLUE + "|--------------- Enter student id to remove ---------------|" + RESET);
                                String id = input.next();
                                while (!findStudentId(id)) {
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println(RED + "|----------------- Id not found! Try again ----------------|" + RESET);
                                    id = input.next();
                                }

                                removeStudent(id);

                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                System.out.println(YELLOW + "|-------------- Student successfully removed --------------|\n" + RESET);
                                break;
                            }
                            case 7: {
                                System.out.println(BLUE + "Enter teacher username" + RESET);
                                String username = input.next();
                                while (!findTeacherUsername(username)) {
                                    System.out.println(RED + "Username not found! Try again" + RESET);
                                    username = input.next();
                                }

                                System.out.println(BLUE + "Enter course name" + RESET);
                                String name = input.next();
                                while (!findCourseName(name)) {
                                    System.out.println(RED + "Course not found! Try again" + RESET);
                                    name = input.next();
                                }

                                Teacher teacher = getTeacherFromDataBase(username);
                                Course course = getCourseFromDataBase(name);

                                switch (teacher.addCourse(course)) {
                                    case 1:
                                        System.out.println(RED + "This teacher already have this course" + RESET);
                                        break;
                                    case 2:
                                        System.out.println(RED + "This course already has a teacher" + RESET);
                                        break;
                                    case 3:
                                        updateTeacher(teacher);
                                        updateCourse(course);
                                        System.out.println(GREEN + "successfully added"  +RESET);
                                        break;
                                }

                                break;
                            }
                            case 8: {
                                System.out.println(BLUE + "Enter teacher username" + RESET);
                                String username = input.next();
                                while (!findTeacherUsername(username)) {
                                    System.out.println(RED + "Username not found! Try again" + RESET);
                                    username = input.next();
                                }

                                System.out.println(BLUE + "Enter course name" + RESET);
                                String name = input.next();
                                while (!findCourseName(name)) {
                                    System.out.println(RED + "Course not found! Try again" + RESET);
                                    name = input.next();
                                }

                                Teacher teacher = getTeacherFromDataBase(username);
                                Course course = getCourseFromDataBase(name);

                                switch (teacher.removeCourse(course)) {
                                    case 1:
                                        System.out.println(RED + "This teacher doesn't have this course" + RESET);
                                        break;
                                    case 2:
                                        updateCourse(course);
                                        updateTeacher(teacher);
                                        System.out.println(GREEN + "Successfully removed" + RESET);
                                        break;
                                }
                                break;
                            }
                            case 10: {
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                System.out.println(YELLOW + "|------------------------- Exited -------------------------|" + RESET);
                                break out1;
                            }
                            default: {
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                System.out.println(RED + "|---------------------- Wrong answer! ---------------------|" + RESET);
                            }
                        }
                    }
                    break out;
                }
                case 2: {
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println(YELLOW + "|------------------- Teacher login page -------------------|" + RESET);


                    System.out.println(BLUE + "|------------------ Enter Your username -----------------|" + RESET);
                    String username = input.next();
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println(BLUE + "|------------------ Enter Your password -----------------|" + RESET);
                    String pass = input.next();
                    System.out.print("\033[H\033[2J");
                    System.out.flush();

                    while (teacherLoginState(username, pass) != 0) {
                        switch (teacherLoginState(username, pass)) {
                            case 1:
                                System.out.println(RED + "|-------------- Username not found! Try again -------------|" + RESET);
                                break;
                            case 2:
                                System.out.println(RED + "|---------------- Wrong password! Try again ---------------|" + RESET);
                                break;
                        }

                        System.out.println(BLUE + "|------------------ Enter Your username -----------------|" + RESET);
                        username = input.next();
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println(BLUE + "|------------------ Enter Your password -----------------|" + RESET);
                        pass = input.next();
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                    }
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println(GREEN + "|--------------------- Welcome Teacher --------------------|" + RESET);



                    out1:
                    while (true) {
                        System.out.print(BLUE + """
                                Choose your order:
                                1- Adding new teacher
                                2- Removing a teacher
                                3- Adding new course
                                4- Removing a course
                                5- Adding new student
                                6- Removing a student
                                10- Exit.
                                """ + RESET);
                        int order = input.nextInt();

                        switch (order) {

                        }
                    }

                //    break out;
                }
                default: {
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println(RED + "|---------------------- Wrong answer! ---------------------|" + RESET);
                }
            }

        }
    }

    private static void updateCourse(Course course) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\Users\\Sumsung\\OneDrive\\" +
                    "Documents\\Java\\AP_Project_BackEnd\\Data Base Files\\Courses\\" + course.getName() + ".txt"));
            oos.writeObject(course);
            oos.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void updateTeacher(Teacher teacher) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\Users\\Sumsung\\OneDrive\\" +
                    "Documents\\Java\\AP_Project_BackEnd\\Data Base Files\\Teachers\\" + teacher.getUsername() + ".txt"));
            oos.writeObject(teacher);
            oos.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Course getCourseFromDataBase(String name) {
        File dir = new File("C:\\Users\\Sumsung\\OneDrive\\Documents\\Java" +
                "\\AP_Project_BackEnd\\Data Base Files\\Courses");
        File[] files = dir.listFiles();

        for (File file : files) {
            if (file.getName().equals(name + ".txt")) {
                try {
                    ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Users\\Sumsung\\" +
                            "OneDrive\\Documents\\Java\\AP_Project_BackEnd\\Data Base Files\\Courses\\" + name + ".txt"));
                    Course course = (Course) ois.readObject();
                    ois.close();
                    return course;
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private static Teacher getTeacherFromDataBase(String username) {
        File dir = new File("C:\\Users\\Sumsung\\OneDrive\\Documents\\Java" +
                "\\AP_Project_BackEnd\\Data Base Files\\Teachers");
        File[] files = dir.listFiles();

        for (File file : files) {
            if (file.getName().equals(username + ".txt")) {
                try {
                    ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Users\\Sumsung\\" +
                            "OneDrive\\Documents\\Java\\AP_Project_BackEnd\\Data Base Files\\Teachers\\" + username + ".txt"));
                    Teacher teacher = (Teacher) ois.readObject();
                    ois.close();
                    return teacher;
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private static int teacherLoginState(String username, String pass) {
        File dir = new File("C:\\Users\\Sumsung\\OneDrive\\Documents\\Java" +
                "\\AP_Project_BackEnd\\Data Base Files\\Teachers");
        File[] files = dir.listFiles();

        if (files == null) {
            return 1;
        }

        for (File file : files) {
            if (file.getName().equals(username + ".txt")) {
                try {
                    ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Users\\Sumsung\\" +
                            "OneDrive\\Documents\\Java\\AP_Project_BackEnd\\Data Base Files\\Teachers\\" + username + ".txt"));
                    Teacher teacher = (Teacher) ois.readObject();
                    ois.close();
                    if (teacher.getPassword().equals(pass)) {
                        return 0;
                    }
                    else {
                        return 2;
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return 1;
    }

    private static boolean findStudentId(String id) {
        File dir = new File("C:\\Users\\Sumsung\\OneDrive\\Documents\\Java" +
                "\\AP_Project_BackEnd\\Data Base Files\\Students");
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
    }

    private static void removeStudent(String id) {
        File file = new File("C:\\Users\\Sumsung\\OneDrive\\Documents\\Java\\" +
                "AP_Project_BackEnd\\Data Base Files\\Students\\" + id + ".txt");
        file.delete();
    }

    private static void addStudent(String id, String pass, String name, String lastname) {
        try {
            FileOutputStream fos = new FileOutputStream("C:\\Users\\Sumsung\\OneDrive\\Documents\\Java" +
                    "\\AP_Project_BackEnd\\Data Base Files\\Students\\" + id + ".txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(new Teacher(name, lastname, id, pass));

            fos.close();
            oos.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean studentIdValidation(String id) {
        return !findStudentId(id);
    }

    private static void removeCourse(String name) {
        File file = new File("C:\\Users\\Sumsung\\OneDrive\\Documents\\Java\\" +
                "AP_Project_BackEnd\\Data Base Files\\Courses\\" + name + ".txt");
        file.delete();
    }

    private static boolean findCourseName(String name) {
        File dir = new File("C:\\Users\\Sumsung\\OneDrive\\Documents\\Java" +
                "\\AP_Project_BackEnd\\Data Base Files\\Courses");
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
    }

    private static void addCourse(String name, int units, String examDate) {
        try {
            FileOutputStream fos = new FileOutputStream("C:\\Users\\Sumsung\\OneDrive\\Documents\\Java" +
                    "\\AP_Project_BackEnd\\Data Base Files\\Courses\\" + name + ".txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(new Course(name, units, examDate));

            fos.close();
            oos.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean courseNameValidation(String name) {
        return !findCourseName(name);
    }

    private static void removeTeacher(String username) {
        File file = new File("C:\\Users\\Sumsung\\OneDrive\\Documents\\Java\\" +
                "AP_Project_BackEnd\\Data Base Files\\Teachers\\" + username + ".txt");
        file.delete();
    }

    private static boolean findTeacherUsername(String username) {
        File dir = new File("C:\\Users\\Sumsung\\OneDrive\\Documents\\Java" +
                "\\AP_Project_BackEnd\\Data Base Files\\Teachers");
        File[] files = dir.listFiles();

        if (files == null) {
            return false;
        }

        for (File file : files) {
            if (file.getName().equals(username + ".txt")) {
                return true;
            }
        }
        return false;
    }

    private static boolean teacherUserValidation(String username) {
        return !findTeacherUsername(username);
    }

    private static boolean passValidation(String pass, String username) {
        Pattern pattern1 = Pattern.compile(".*[A-Z].*");
        Pattern pattern2 = Pattern.compile(".*[a-z].*");
        Pattern pattern3 = Pattern.compile(".*\\d.*");
        return pass.length() >= 8 && !pass.contains(username) && pattern1.matcher(pass).matches() && pattern2.matcher(pass).matches()
                && pattern3.matcher(pass).matches();
    }

    private static void addTeacher(String username, String pass, String name, String lastname) {
        try {
            FileOutputStream fos = new FileOutputStream("C:\\Users\\Sumsung\\OneDrive\\Documents\\Java" +
                    "\\AP_Project_BackEnd\\Data Base Files\\Teachers\\" + username + ".txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(new Teacher(name, lastname, username, pass));

            fos.close();
            oos.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
