import java.util.*;

class Student {
    protected String id, name;
    protected int marks;

    public Student(String id, String name, int marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getMarks() { return marks; }

    @Override
    public String toString() {
        String m = (marks == -1) ? "N/A" : String.valueOf(marks);
        return id + " " + name + " (" + m + ")";
    }
}

class GraduateStudent extends Student {
    private String area;

    public GraduateStudent(String id, String name, int marks, String area) {
        super(id, name, marks);
        this.area = area;
    }
}

class Teacher extends Student {
    private String subject;

    public Teacher(String id, String name, String subject) {
        super(id, name, -1);   
        this.subject = subject;
    }

    @Override
    public int getMarks() {
        return -1;
    }
}

class Repository<T> {
    private Map<String, T> data = new HashMap<>();

    public void save(String id, T obj) { data.put(id, obj); }
    public T find(String id) { return data.get(id); }
    public void delete(String id) { data.remove(id); }

    public Collection<T> values() { return data.values(); }
}

class ReportUtil {
    public static <T extends Student> void showReport(List<T> list) {
        System.out.println("\n--- STUDENT REPORT ---");
        for (T obj : list) {
            if (obj instanceof Teacher) continue;  
            System.out.println(obj.getId() + "  " + obj.getName() + "  " + obj.getMarks());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        list.add(new Student("s1", "anshul", 43));
        list.add(new Student("s2", "taarak", 59));
        list.add(new Student("s3", "gurdeep", 70));
        list.add(new Student("s4", "vivan", 96));
        list.add(new GraduateStudent("G4", "sukhdeep", 87, "CS"));
        list.add(new Teacher("t1", "himanshu", "Math"));

        Repository<Student> repo = new Repository<>();
        for (Student s : list) repo.save(s.getId(), s);

        System.out.println("ALL:");
        list.forEach(System.out::println);

        System.out.println("\nLOOKUP s2:");
        Student s = repo.find("s2");
        System.out.println(s != null ? s : "Not found");

        Iterator<Student> it = list.iterator();
        while (it.hasNext()) {
            Student st = it.next();
            if (st instanceof Teacher) continue;         
            if (st.getMarks() < 80) {
                it.remove();
                repo.delete(st.getId());
            }
        }

        System.out.println("\nAfter Removal:");
        list.forEach(System.out::println);

        ReportUtil.showReport(list);
    }
}

