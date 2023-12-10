import java.util.ArrayList;
import java.util.List;

class Family implements Person {
    private List<Person> members = new ArrayList<>();

    public void addMember(Person person) {
        members.add(person);
    }

    @Override
    public void displayInfo() {
        for (Person member : members) {
            member.displayInfo();
            System.out.println();
        }
    }

    public List<Person> getMembers() {
        return members;
    }
}

