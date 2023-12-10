import java.util.ArrayList;
import java.util.List;

// Định nghĩa interface hoặc lớp cơ sở cho mỗi cá nhân
interface Person {
    void displayInfo();
}

class Individual implements Person {
    private String name;
    private String dateOfBirth;
    private String gender;
    private List<Person> children = new ArrayList<>();
    private Person spouse;

    public Individual(String name, String dateOfBirth, String gender) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public void setSpouse(Person spouse) {
        this.spouse = spouse;
    }

    public void addChild(Person child) {
        children.add(child);
    }

    @Override
    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Date of Birth: " + dateOfBirth);
        System.out.println("Gender: " + gender);
    }

    public boolean isMarried() {
        return spouse != null;
    }

    public List<Person> getChildren() {
        return children;
    }
}
