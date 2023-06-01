package fp.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import fp.entity.Student;
import fp.service.StudentService;
import fp.service.impl.StudentServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

@WebServlet("/save")
public class SaveStudentServlet extends HttpServlet {
    StudentService studentService=new StudentServiceImpl();
    Student student=new Student(  );
    @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = request.getReader().lines().collect( Collectors.joining());
        student = objectMapper.readValue(requestBody, Student.class);
        System.out.println(student);
        PrintWriter writer=response.getWriter();
        if(( studentService.addStudent( student ) )){
            writer.println( "saved" );
        }else writer.println( "not saved" );
        }

}

