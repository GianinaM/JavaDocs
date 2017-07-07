package exercise3;

/**
 * Created by Gianina.Carp on 7/7/2017.
 */

//(a) Now create another class that extends the Student class and override the equals and hash code methods like this:
//        hash code: returns the hashcode ONLY for the first name property.
//        equals: checks if two objects are equal taking into account ONLY the first name property.

public class Student_1 extends Student {

    public Student_1(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public int hashCode (){
        return 31 * this.firstName.hashCode();
    }

    public boolean equals(Object o) {

        if (this == o) return true;
        if(o == null || this.getClass() != o.getClass()) return false;

        Student_1 student = (Student_1) o;

        if(!this.firstName.equals(student.getFirstName())) return false;

        return true;
    }
}
