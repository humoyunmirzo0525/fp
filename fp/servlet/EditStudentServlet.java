package fp.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import fp.entity.Student;
import fp.service.StudentService;
import fp.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

@WebServlet("/edit")
public class EditStudentServlet extends HttpServlet {
    StudentService studentService=new StudentServiceImpl();
    Student student=new Student(  );

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = req.getReader().lines().collect( Collectors.joining());
        student = objectMapper.readValue(requestBody, Student.class);
        PrintWriter writer=resp.getWriter();
        int id = student.getId();

        String email = student.getEmail();
        String name = student.getName();
        int age = student.getAge();
        Student student1 = new Student(id, name, email, age);
        if(studentService.editStudent(student1)>0){
            writer.println( "ishladi" );
        }else writer.println( "ishlamadi" );
    }
}
