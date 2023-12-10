import java.util.ArrayList;
import java.util.List;

public class GenealogyTree {
    public static List<Person> findUnmarriedIndividuals(Person root) {
        List<Person> unmarriedIndividuals = new ArrayList<>();
        findUnmarriedIndividuals(root, unmarriedIndividuals);
        return unmarriedIndividuals;
    }

    private static void findUnmarriedIndividuals(Person person, List<Person> unmarriedIndividuals) {
        if (person instanceof Individual) {
            Individual individual = (Individual) person;
            if (!individual.isMarried()) {
                unmarriedIndividuals.add(individual);
            }
        } else if (person instanceof Family) {
            for (Person member : ((Family) person).getMembers()) {
                findUnmarriedIndividuals(member, unmarriedIndividuals);
            }
        }
    }

    public static List<Family> findCouplesWithTwoChildren(Person root) {
        List<Family> couplesWithTwoChildren = new ArrayList<>();
        findCouplesWithTwoChildren(root, couplesWithTwoChildren);
        return couplesWithTwoChildren;
    }

    private static void findCouplesWithTwoChildren(Person person, List<Family> couplesWithTwoChildren) {
        if (person instanceof Family) {
            Family family = (Family) person;
            List<Person> members = family.getMembers();
            if (members.size() == 2) {
                Person firstChild = members.get(0);
                Person secondChild = members.get(1);
                if (firstChild instanceof Individual && secondChild instanceof Individual) {
                    couplesWithTwoChildren.add(family);
                }
            }
        } else if (person instanceof Family) {
            for (Person member : ((Family) person).getMembers()) {
                findCouplesWithTwoChildren(member, couplesWithTwoChildren);
            }
        }
    }

    public static List<Person> findLatestGeneration(Person root) {
        List<Person> latestGeneration = new ArrayList<>();
        findLatestGeneration(root, latestGeneration);
        return latestGeneration;
    }

    private static void findLatestGeneration(Person person, List<Person> latestGeneration) {
        if (person instanceof Individual) {
            Individual individual = (Individual) person;
            List<Person> children = individual.getChildren();
            if (children.isEmpty()) {
                latestGeneration.add(individual);
            } else {
                for (Person child : children) {
                    findLatestGeneration(child, latestGeneration);
                }
            }
        } else if (person instanceof Family) {
            for (Person member : ((Family) person).getMembers()) {
                findLatestGeneration(member, latestGeneration);
            }
        }
    }
    public static void main(String[] args) {
        Individual james = new Individual("James", "01/01/1970", "Male");
        Individual hana = new Individual("Hana", "05/05/1975", "Female");
        Individual ryan = new Individual("Ryan", "10/10/1995", "Male");
        Individual kai = new Individual("Kai", "15/08/1998", "Male");
        Individual jennifer = new Individual("Jennifer", "20/12/2000", "Female");

        james.setSpouse(hana);
        james.addChild(ryan);
        james.addChild(kai);

        kai.setSpouse(jennifer);
        kai.addChild(new Individual("Child1", "01/01/2025", "Male"));
        kai.addChild(new Individual("Child2", "01/01/2027", "Female"));

        System.out.println("Unmarried Individuals:");
        List<Person> unmarriedIndividuals = findUnmarriedIndividuals(james);
        for (Person person : unmarriedIndividuals) {
            person.displayInfo();
            System.out.println();
        }

        System.out.println("\nCouples with Two Children:");
        List<Family> couplesWithTwoChildren = findCouplesWithTwoChildren(james);
        for (Family family : couplesWithTwoChildren) {
            family.displayInfo();
            System.out.println();
        }

        System.out.println("\nLatest Generation:");
        List<Person> latestGeneration = findLatestGeneration(james);
        for (Person person : latestGeneration) {
            person.displayInfo();
            System.out.println();
        }
    }
}
