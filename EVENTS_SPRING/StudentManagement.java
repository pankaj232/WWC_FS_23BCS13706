package wwc;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

// ------------------------
// Base Student Class
// ------------------------
class Student {
    private String id, name;
    private int marks;

    public Student(String id, String name, int marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public String getId() {
        return id;
    }

    public int getMarks() {
        return marks;
    }

    public String getRole() {
        return "Undergraduate";
    }

    @Override
    public String toString() {
        return "ID: " + id + " Name: " + name + " Marks: " + marks;
    }
}

// ------------------------
// Graduate Student
// ------------------------
class GradeStudent extends Student {
    private String area;

    public GradeStudent(String id, String name, int marks, String area) {
        super(id, name, marks);
        this.area = area;
    }

    @Override
    public String getRole() {
        return "Grad(" + area + ")";
    }
}

// ------------------------
// Teacher subclass
// ------------------------
class Teacher extends Student {
    private String subject;

    public Teacher(String id, String name, int marks, String subject) {
        super(id, name, marks);
        this.subject = subject;
    }

    @Override
    public String getRole() {
        return "Teacher(" + subject + ")";
    }
}

// ------------------------
// Generic Repository
// ------------------------
class Repository<T> {
    private Map<String, T> data = new HashMap<>();

    public void save(String id, T student) {
        data.put(id, student);
    }

    public T find(String id) {
        return data.get(id);
    }

    public void delete(String id) {
        data.remove(id);
    }
}

// ------------------------
// Main Class
// ------------------------
public class StudentManagement {

    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    // generateReport as a FUNCTION
    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public static void generateReport(List<Student> students) {
        System.out.println("=== Student Marks Report ===");
        for (Student s : students) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) {

        List<Student> list = new ArrayList<>();

        list.add(new Student("S101", "Alice", 85));
        list.add(new Student("S102", "Bob", 90));
        list.add(new Student("S103", "Charlie", 78));
        list.add(new GradeStudent("S104", "Diana", 92, "AI"));
        list.add(new Teacher("T201", "Mr. John", 0, "Physics"));

        // Repository usage
        Repository<Student> repo = new Repository<>();
        for (Student s : list) {
            repo.save(s.getId(), s);
        }

        // Show records
        System.out.println("=== Student Records ===");
        for (Student s : list) {
            System.out.println(repo.find(s.getId()) + " Role: " + s.getRole());
        }

        // Call report function
        generateReport(list);
    }
}
