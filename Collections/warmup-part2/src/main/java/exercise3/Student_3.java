package exercise3;

/**
 * Created by Gianina.Carp on 7/7/2017.
 */

//(c) Now create another class that extends the student class and override the equals and hash code methods like this:
//        hash code: must be correctly implemented for all the properties.
//        equals: checks if two objects are equal taking into account ONLY the first name property.

public class Student_3 extends Student{
    public Student_3(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public int hashCode (){
        return 31 * this.firstName.hashCode() + this.lastName.hashCode();
    }

    public boolean equals(Object o) {

        if (this == o) return true;
        if(o == null || this.getClass() != o.getClass()) return false;

        Student_3 student = (Student_3) o;

        if(!this.firstName.equals(student.getFirstName())) return false;

        return true;
    }
}
