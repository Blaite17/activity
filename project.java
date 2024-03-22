import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

class Entity {
    String id;
    String lastName;
    String firstName;
    String middleName;
    String address;
    int age;

    Entity(String lastName, String firstName, String middleName, String address, int age) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.address = address;
        this.age = age;
    }
}

class Instructor extends Entity {
    List<String> subjectsHandled;

    Instructor(String lastName, String firstName, String middleName, String address, int age) {
        super(lastName, firstName, middleName, address, age);
        subjectsHandled = new ArrayList<>();
    }
}

class Student extends Entity {
    List<String> subjectsEnrolled;

    Student(String lastName, String firstName, String middleName, String address, int age) {
        super(lastName, firstName, middleName, address, age);
        subjectsEnrolled = new ArrayList<>();
    }
}

class Subject {
    String code;
    String title;
    String description;

    Subject(String title, String description) {
        this.title = title;
        this.description = description;
    }
}

public class project {
    private static final Random random = new Random();
    private static final List<Instructor> instructors = new ArrayList<>();
    private static final List<Student> students = new ArrayList<>();
    private static final List<Subject> subjects = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            displayMainMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    manageInstructors();
                    break;
                case 2:
                    manageStudents();
                    break;
                case 3:
                    manageSubjects();
                    break;
                case 4:
                    displayInformation();
                    break;
                case 5:
                    assignSubjectToInstructor();
                    break;
                case 6:
                    enrollSubjectToStudent();
                    break;
                case 7:
                    System.out.print("\033[31;49;1m");
                    System.out.println("Exiting...");
                    System.out.print("\033[0m");
                    break;
                default:
                    System.out.print("\033[31;49;1m");
                    System.out.println("Invalid choice. Please enter a number between 1 and 7.");
                    System.out.print("\033[0m");
            }
        } while (choice != 7);
    }

    private static void displayMainMenu() {
        System.out.println("");
        System.out.println("-------------------------------------------------");
        System.out.println("|                                               |");
        System.out.println("|   ========================================    |");
        System.out
                .println("|" + "\033[31;49;1m" + "             UNIVERSITY MANAGEMENT             " + "\033[0m" + "| ");
        System.out.println("|   ========================================    |");
        System.out.println("|                                               |");
        System.out.println("|    1. Manage Instructors                      |");
        System.out.println("|    2. Manage Students                         |");
        System.out.println("|    3. Manage Subjects                         |");
        System.out.println("|    4. Display Information                     |");
        System.out.println("|    5. Assign Subject to Instructor            |");
        System.out.println("|    6. Enroll Subject to Student               |");
        System.out.println("|    7. Exit                                    |");
        System.out.println("|                                               |");
        System.out.println("-------------------------------------------------");

        System.out.print("\033[36;49;1m");
        System.out.print("\nEnter your choice: ");
        System.out.print("\033[0m");
    }

    private static void manageInstructors() {
        int choice;
        do {
            displayInstructorMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    addInstructor();
                    break;
                case 2:
                    editInstructor();
                    break;
                case 3:
                    deleteInstructor();
                    break;
                case 4:
                    break;
                default:
                    System.out.print("\033[31;49;1m");
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                    System.out.print("\033[0m");
            }
        } while (choice != 4);
    }

    private static void displayInstructorMenu() {
        System.out.println("========================================");
        System.out.print("\033[32;1m");
        System.out.println("            INSTRUCTOR MENU            ");
        System.out.print("\033[0m");
        System.out.println("========================================");
        System.out.println("1. Add Instructor");
        System.out.println("2. Edit Instructor");
        System.out.println("3. Delete Instructor");
        System.out.println("4. Back");
        System.out.print("\033[36;49;1m");
        System.out.print("\nEnter your choice: ");
        System.out.print("\033[0m");
    }

    private static void addInstructor() {
        System.out.print("\033[33;1m");
        System.out.println("\n=== Add Instructor ===");
        System.out.print("\033[0m");
        String lastName, firstName, middleName, address;
        int age;

        do {
            System.out.print("Enter last name: ");
            lastName = scanner.nextLine();
            if (lastName.isEmpty()) {
                System.out.print("\033[31;49;1m");
                System.out.println("Last name cannot be empty!");
                System.out.print("\033[0m");
            }
        } while (lastName.isEmpty());

        do {
            System.out.print("Enter first name: ");
            firstName = scanner.nextLine();
            if (firstName.isEmpty()) {
                System.out.print("\033[31;49;1m");
                System.out.println("First name cannot be empty!");
                System.out.print("\033[0m");
            }
        } while (firstName.isEmpty());

        System.out.print("Enter middle name (optional): ");
        middleName = scanner.nextLine();

        do {
            System.out.print("Enter address: ");
            address = scanner.nextLine();
            if (address.isEmpty()) {
                System.out.print("\033[31;49;1m");
                System.out.println("Address cannot be empty!");
                System.out.print("\033[0m");
            }
        } while (address.isEmpty());

        do {
            System.out.print("Enter age (minimum 24 years old): ");
            age = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            if (age < 24) {
                System.out.print("\033[31;49;1m");
                System.out.println("Age must be at least 24 years old.");
                System.out.print("\033[0m");
            }
        } while (age < 24);

        String id = generateInstructorID();
        Instructor instructor = new Instructor(lastName, firstName, middleName, address, age);
        instructor.id = id;
        instructors.add(instructor);
        System.out.println("Instructor added successfully with ID: " + id);
    }

    private static String generateInstructorID() {
        // Generate ID with specified format
        String year = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        String id = "INS" + year + "-";
        boolean isUnique;
        do {
            id += String.format("%04d", random.nextInt(10000));
            isUnique = true;
            for (Instructor instructor : instructors) {
                if (instructor.id.equals(id)) {
                    isUnique = false;
                    break;
                }
            }
        } while (!isUnique);
        return id;
    }

    private static void editInstructor() {
        System.out.print("\033[33;1m");
        System.out.println("\n=== Edit Instructor ===");
        System.out.print("\033[0m");
        System.out.print("Enter Instructor ID: ");
        String instructorIdd = scanner.nextLine();
        String instructorId = instructorIdd.toUpperCase();
        Instructor instructor = findInstructorById(instructorId);
        if (instructor != null) {
            System.out.print("Enter new last name (leave blank to keep unchanged): ");
            String lastName = scanner.nextLine();
            if (!lastName.isEmpty()) {
                instructor.lastName = lastName;
            }
            System.out.print("Enter new first name (leave blank to keep unchanged): ");
            String firstName = scanner.nextLine();
            if (!firstName.isEmpty()) {
                instructor.firstName = firstName;
            }
            System.out.print("Enter new middle name (leave blank to keep unchanged): ");
            String middleName = scanner.nextLine();
            if (!middleName.isEmpty()) {
                instructor.middleName = middleName;
            }
            System.out.print("Enter new address (leave blank to keep unchanged): ");
            String address = scanner.nextLine();
            if (!address.isEmpty()) {
                instructor.address = address;
            }
            System.out.print("Enter new age (leave blank to keep unchanged): ");
            String ageStr = scanner.nextLine();
            if (!ageStr.isEmpty()) {
                int age = Integer.parseInt(ageStr);
                if (age >= 24) {
                    instructor.age = age;
                } else {
                    System.out.print("\033[31;49;1m");
                    System.out.println("Age must be at least 24 years old.");
                    System.out.print("\033[0m");
                }
            }
            System.out.println("Instructor details updated successfully.");
        } else {
            System.out.print("\033[31;49;1m");
            System.out.println("Instructor not found!");
            System.out.print("\033[0m");
        }
    }

    private static void deleteInstructor() {
        System.out.print("\033[33;1m");
        System.out.println("\n=== Delete Instructor ===");
        System.out.print("\033[0m");
        System.out.print("Enter Instructor ID: ");
        String instructorIdd = scanner.nextLine();
        String instructorId = instructorIdd.toUpperCase();
        Instructor instructor = findInstructorById(instructorId);
        if (instructor != null) {
            instructors.remove(instructor);
            System.out.println("Instructor deleted successfully.");
        } else {
            System.out.print("\033[31;49;1m");
            System.out.println("Instructor not found!");
            System.out.print("\033[0m");
        }
    }

    private static void manageStudents() {
        int choice;
        do {
            displayStudentMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    editStudent();
                    break;
                case 3:
                    deleteStudent();
                    break;
                case 4:
                    break;
                default:
                    System.out.print("\033[31;49;1m");
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                    System.out.print("\033[0m");
            }
        } while (choice != 4);
    }

    private static void displayStudentMenu() {
        System.out.println("========================================");
        System.out.print("\033[32;1m");
        System.out.println("             STUDENT MENU              ");
        System.out.print("\033[0m");
        System.out.println("========================================");
        System.out.println("1. Add Student");
        System.out.println("2. Edit Student");
        System.out.println("3. Delete Student");
        System.out.println("4. Back");
        System.out.print("\033[36;49;1m");
        System.out.print("\nEnter your choice: ");
        System.out.print("\033[0m");
    }

    private static void addStudent() {
        System.out.print("\033[33;1m");
        System.out.println("\n=== Add Student ===");
        System.out.print("\033[0m");
        String lastName, firstName, middleName, address;
        int age;

        do {
            System.out.print("Enter last name: ");
            lastName = scanner.nextLine();
            if (lastName.isEmpty()) {
                System.out.print("\033[31;49;1m");
                System.out.println("Last name cannot be empty!");
                System.out.print("\033[0m");
            }
        } while (lastName.isEmpty());

        do {
            System.out.print("Enter first name: ");
            firstName = scanner.nextLine();
            if (firstName.isEmpty()) {
                 System.out.print("\033[31;49;1m");
                System.out.println("First name cannot be empty!");
                System.out.print("\033[0m");
            }
        } while (firstName.isEmpty());

        System.out.print("Enter middle name (optional): ");
        middleName = scanner.nextLine();

        do {
            System.out.print("Enter address: ");
            address = scanner.nextLine();
            if (address.isEmpty()) {
                 System.out.print("\033[31;49;1m");
                System.out.println("Address cannot be empty!");
                System.out.print("\033[0m");
            }
        } while (address.isEmpty());

        do {
            System.out.print("Enter age (minimum 17 years old): ");
            age = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            if (age < 17) {
                System.out.print("\033[31;49;1m");
                System.out.println("Age must be at least 17 years old.");
                System.out.print("\033[0m");
            }
        } while (age < 17);

        String id = generateStudentID();
        Student student = new Student(lastName, firstName, middleName, address, age);
        student.id = id;
        students.add(student);
        System.out.println("Student added successfully with ID: " + id);
    }

    private static String generateStudentID() {
        // Generate ID with specified format
        String year = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        String id = "ST" + year + "-";
        boolean isUnique;
        do {
            id += String.format("%04d", random.nextInt(10000));
            isUnique = true;
            for (Student student : students) {
                if (student.id.equals(id)) {
                    isUnique = false;
                    break;
                }
            }
        } while (!isUnique);
        return id;
    }

    private static void editStudent() {
        System.out.print("\033[33;1m");
        System.out.println("\n=== Edit Student ===");
        System.out.print("\033[0m");
        System.out.print("Enter Student ID: ");
        String studentIdd = scanner.nextLine();
        String studentId = studentIdd.toUpperCase();
        Student student = findStudentById(studentId);
        if (student != null) {
            System.out.print("Enter new last name (leave blank to keep unchanged): ");
            String lastName = scanner.nextLine();
            if (!lastName.isEmpty()) {
                student.lastName = lastName;
            }
            System.out.print("Enter new first name (leave blank to keep unchanged): ");
            String firstName = scanner.nextLine();
            if (!firstName.isEmpty()) {
                student.firstName = firstName;
            }
            System.out.print("Enter new middle name (leave blank to keep unchanged): ");
            String middleName = scanner.nextLine();
            if (!middleName.isEmpty()) {
                student.middleName = middleName;
            }
            System.out.print("Enter new address (leave blank to keep unchanged): ");
            String address = scanner.nextLine();
            if (!address.isEmpty()) {
                student.address = address;
            }
            System.out.print("Enter new age (leave blank to keep unchanged): ");
            String ageStr = scanner.nextLine();
            if (!ageStr.isEmpty()) {
                int age = Integer.parseInt(ageStr);
                student.age = age;
            }
            System.out.println("Student details updated successfully.");
        } else {
            System.out.print("\033[31;49;1m");
            System.out.println("Student not found!");
            System.out.print("\033[0m");
        }
    }

    private static void deleteStudent() {
        System.out.print("\033[33;1m");
        System.out.println("\n=== Delete Student ===");
        System.out.print("\033[0m");
        System.out.print("Enter Student ID: ");
        String studentIdd = scanner.nextLine();
        String studentId = studentIdd.toUpperCase();
        Student student = findStudentById(studentId);
        if (student != null) {
            students.remove(student);
            System.out.println("Student deleted successfully.");
        } else {
            System.out.print("\033[31;49;1m");
            System.out.println("Student not found!");
            System.out.print("\033[0m");
        }
    }

    private static void manageSubjects() {
        int choice;
        do {
            displaySubjectMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    addSubject();
                    break;
                case 2:
                    editSubject();
                    break;
                case 3:
                    deleteSubject();
                    break;
                case 4:
                    break;
                default:
                    System.out.print("\033[31;49;1m");
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                    System.out.print("\033[0m");
            }
        } while (choice != 4);
    }

    private static void displaySubjectMenu() {
        System.out.println("========================================");
        System.out.print("\033[32;1m");
        System.out.println("             SUBJECT MENU              ");
        System.out.print("\033[0m");
        System.out.println("========================================");
        System.out.println("1. Add Subject");
        System.out.println("2. Edit Subject");
        System.out.println("3. Delete Subject");
        System.out.println("4. Back");
        System.out.print("\033[36;49;1m");
        System.out.print("\nEnter your choice: ");
        System.out.print("\033[0m");
    }

    private static void addSubject() {
        System.out.print("\033[33;1m");
        System.out.println("\n=== Add Subject ===");
        System.out.print("\033[0m");
        String title