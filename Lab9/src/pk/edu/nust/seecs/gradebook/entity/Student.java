package pk.edu.nust.seecs.gradebook.entity;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pk.edu.nust.seecs.gradebook.util.HibernateUtil;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 * Typical Student Entity. 
 * <p>
 This entity class holds the general attributes of a student. 
 */
@Entity
public class Student implements java.io.Serializable {

    @Id
    @GeneratedValue
    @Column(name = "student_id")
    private Integer studentId;
    @Column
    private String name;
    
    /*
    * This relationship contains the courses this student is taking.
    * The reverse relationship is present in the Course entity class
    */
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "course_students", joinColumns = {
        @JoinColumn(name = "student_id")}, inverseJoinColumns = {
        @JoinColumn(name = "course_id")})
    private Set<Course> Courses = new HashSet<>(0);
    /*
    * Class constructor.
    */
    public Student() {
    }


    /*
    * Class constructor that creates this student object by their name.
    */
    public Student(int x, String name) {
        this.studentId=x;
        this.name = name;
    }

    public Set getCourses() {
        return Courses;
    }

    public void setCourses(Set Courses) {
        this.Courses = Courses;
    }
    /*
    * This method will return the id of this student.
    * Note; we have not defined any setter method for student id, since it is 
    * to be generated by the dbms.
    */
    public Integer getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" + "studentId=" + studentId + ", name=" + name + ", Courses=" + Courses + '}';
    }

}
