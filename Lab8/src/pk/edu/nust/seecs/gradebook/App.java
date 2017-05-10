package pk.edu.nust.seecs.gradebook;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import pk.edu.nust.seecs.gradebook.BOs.*;
import pk.edu.nust.seecs.gradebook.dao.*;
import pk.edu.nust.seecs.gradebook.entity.*;

/**
 * My main App. 
 * <p>
 This executes everything.
 */

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class App {

    private CloBO clobo;
    private ContentBO contentbo;
    private CourseBO coursebo;
    private GradeBO gradebo;
    private StudentBO studentbo;
    private TeacherBO teacherbo;

    public void main(String[] args) {
        java.util.Date date=new java.util.Date();
        ApplicationContext context=new ClassPathXmlApplicationContext(new String[]{"application-context.xml"});
        BeanFactory factory=context;
        clobo = (CloBO)factory.getBean("clobo");
        contentbo = (ContentBO)factory.getBean("contentbo");
        coursebo = (CourseBO)factory.getBean("coursebo");
        teacherbo = (TeacherBO)factory.getBean("teacherbo");
        studentbo = (StudentBO)factory.getBean("studentbo");
        gradebo = (GradeBO)factory.getBean("gradebo");

        Clo clo = new Clo(5,"a","b","c","d");
        clobo.addClo(clo);

        Teacher teacher = new Teacher(3, "Fahad Satti");
        teacherbo.addTeacher(teacher);

        Student student = new Student(1, "Nashmia");
        studentbo.addStudent(student);

        Course course = new Course("Software Construction", date, date, 3, teacher);
        coursebo.addCourse(course);

        Content content = new Content("xcd","dfsd",date,date,course);
        contentbo.addClo(content);

        Grade grade = new Grade ("SC", 50, course);
        gradebo.addClo(grade);
        Teacher d= new Teacher();
        d=   myBean.getTeacher(3);
        myBean.addCourse(1,"Construct",date,date,3, d);
        Course f= new Course();
        f=myBean.getCourse(1);
        myBean.addContent(1, "xcd", "dfyu", date, date,f);
        myBean.getTeachers();
        myBean.getAllClo();
        Content c=myBean.getContent(1);
        myBean.addGrade(1, "SC", 45,c);
        myBean.getAllGrade();
        myBean.getGrade(1);
    }

}