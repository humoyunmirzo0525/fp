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

@WebServlet("/getByEmail")
public class GetStudentServlet extends HttpServlet {
    StudentService studentService= new StudentServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer=resp.getWriter();
        String email=req.getParameter( "email" );
        ObjectMapper objectMapper = new ObjectMapper();
        String getStudent = objectMapper.writeValueAsString(studentService.getStudentByEmail( email ));
        writer.println(getStudent );


    }
}
