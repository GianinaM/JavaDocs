package exercise3;

/**
 * Created by Gianina.Carp on 7/7/2017.
 */

//(b) Now create another class that extends the Student class and override the equals and hash code methods like this:
//        hash code: returns the hashcode ONLY for the first name property.
//        equals: must be correctly implemented for all the properties.

public class Student_2 extends Student{

    public Student_2(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public int hashCode (){
        return this.firstName.hashCode();
    }

    public boolean equals(Object o) {

        if (this == o) return true;
        if(o == null || this.getClass() != o.getClass()) return false;

        Student_2 student = (Student_2) o;

        if((!this.firstName.equals(student.getFirstName())) || (!this.lastName.equals(student.getLastName()))) return false;

        return true;
    }
}
