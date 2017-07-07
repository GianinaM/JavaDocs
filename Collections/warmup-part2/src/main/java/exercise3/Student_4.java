package exercise3;

/**
 * Created by Gianina.Carp on 7/7/2017.
 */

//(d) Now create another class that extends the student class and override the equals and hash code methods like this:
//        hash code: must be correctly implemented for all the properties.
//        equals: must be correctly implemented for all the properties.

public class Student_4 extends Student{
    public Student_4(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public int hashCode (){
        return 31 * this.firstName.hashCode() + this.lastName.hashCode();
    }

    public boolean equals(Object o) {

        if (this == o) return true;
        if(o == null || this.getClass() != o.getClass()) return false;

        Student_4 student = (Student_4) o;

        if((!this.firstName.equals(student.getFirstName())) || (!this.lastName.equals(student.getLastName()))) return false;

        return true;
    }
}
