import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Executable;
import java.util.Formatter;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    static String RESET = "\u001B[0m";
    static String RED = "\u001B[31m";
    static String GREEN = "\u001B[32m";
    static String BLUE = "\u001B[34m";

    public static void main(String[] args) {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        Scanner input = new Scanner(System.in);

        System.out.println(GREEN + "|------------------------- WELCOME ------------------------|" + RESET);

        out:
        while (true) {
            System.out.print(GREEN + """
                    |----------------- What is you're access? -----------------|
                    |---------- (admin = 1, teacher = 2, student = 3) ---------|
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
                                Choose you're order:
                                1- Adding new teacher.
                                2- Removing a teacher
                                3- Adding new course.
                                4- Removing a course.
                                5- Adding a student to a class
                                6- Removing a student from a class
                                10- Exit.
                                """ + RESET);
                        int order = input.nextInt();

                        switch (order) {
                            case 1: {
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                System.out.println(GREEN + "|-------------------- Teacher sign up ---------------------|" + RESET);



                                System.out.println(BLUE + "|----------------- Enter you're username: -----------------|" + RESET);
                                String username = input.next();
                                while (!userValidation(username)) {
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println(RED + "|---- This username has already been used! Try again: -----|" + RESET);
                                    username = input.next();
                                }
                                System.out.print("\033[H\033[2J");
                                System.out.flush();



                                System.out.println(BLUE + "|----------------- Enter you're password: -----------------|" + RESET);
                                String pass1 = input.next();
                                while (!passValidation(pass1, username)) {
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println(RED + "|--------------- Wrong password! Try again: ---------------|" + RESET);
                                    pass1 = input.next();
                                }
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                System.out.println(BLUE + "|----------------- Verify you're password: ----------------|" + RESET);
                                String pass2 = input.next();
                                while (!pass1.equals(pass2)) {
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println(RED + "|---------------- Doesn't match! Try again: ---------------|" + RESET);
                                    pass2 = input.next();
                                }
                                System.out.print("\033[H\033[2J");
                                System.out.flush();



                                System.out.println(BLUE + "|------------------- Enter you're name: -------------------|" + RESET);
                                input.next();
                                String name = input.nextLine();
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                System.out.println(BLUE + "|----------------- Enter you're lastname: -----------------|" + RESET);
                                String lastname = input.nextLine();
                                System.out.print("\033[H\033[2J");
                                System.out.flush();



                                addTeacher(username, pass1, name, lastname);
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                System.out.println(GREEN + "|---------------- Successfully signed up :) ---------------|\n" + RESET);
                                break;
                            }
                            case 2: {
                                break;
                            }
                            case 3: {
                                break;
                            }
                            case 4: {
                                break;
                            }
                            case 5: {
                                break;
                            }
                            case 6: {
                                break;
                            }
                            case 10: {
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                System.out.println(GREEN + "|------------------------- Exited -------------------------|" + RESET);
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
                    break out;
                }
                case 3: {
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



    private static boolean userValidation(String username) {
        try {
            Scanner scanner = new Scanner(new File("C:\\Users\\Sumsung\\OneDrive\\Documents\\" +
                    "Java\\AP_Project_BackEnd\\Teacher_List.txt"));
            while (scanner.hasNext()) {
                if (scanner.nextLine().split(" {2}", 2)[0].equals(username)) {
                    return false;
                }
            }
            System.out.println("jj");
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
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
            Formatter formatter = new Formatter(new FileWriter("C:\\Users\\Sumsung\\OneDrive\\Documents\\" +
                    "Java\\AP_Project_BackEnd\\Teacher_List.txt", true));
            formatter.format(username + "  " + pass + "\n");
            formatter.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
