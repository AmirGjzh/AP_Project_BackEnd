import java.io.FileWriter;
import java.util.Formatter;
import java.util.Scanner;
import java.util.function.Predicate;
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

                    break out;
                }
                default: {
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println(RED + "|---------------------- Wrong answer! ---------------------|" + RESET);
                }
            }
        }
    }

    private static int teacherLoginState(String username, String pass) {
        return 0;
    }

    private static boolean findStudentId(String id) {
        return true;
    }

    private static void removeStudent(String id) {
    }

    private static void addStudent(String username, String pass1, String name, String lastname) {
    }

    private static boolean studentIdValidation(String username) {
        return true;
    }

    private static void removeCourse(String name) {
    }

    private static boolean findCourseName(String name) {
        return true;
    }

    private static void addCourse(String name, int units, String examDate) {
    }

    private static boolean courseNameValidation(String name) {
        return true;
    }

    private static void removeTeacher(String username) {
    }

    private static boolean findTeacherUsername(String username) {
        return true;
    }

    private static boolean teacherUserValidation(String username) {
        return true;
    }

    private static boolean passValidation(String pass, String username) {
        Pattern pattern1 = Pattern.compile(".*[A-Z].*");
        Pattern pattern2 = Pattern.compile(".*[a-z].*");
        Pattern pattern3 = Pattern.compile(".*\\d.*");
        return pass.length() >= 8 && !pass.contains(username) && pattern1.matcher(pass).matches() && pattern2.matcher(pass).matches()
                && pattern3.matcher(pass).matches();
    }

    private static void addTeacher(String username, String pass, String name, String lastname) {

    }
}
