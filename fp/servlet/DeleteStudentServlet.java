package fp.servlet;

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

@WebServlet(urlPatterns = "/delete")
public class DeleteStudentServlet extends HttpServlet {
    StudentService studentService=new StudentServiceImpl();
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student student=new Student(  );
        boolean isDeleted=studentService.deleteStudentById( Integer.parseInt( req.getParameter("id" ) ) );
        System.out.println(req.getParameter("id"));PrintWriter printWriter=resp.getWriter();
        if (isDeleted){
            printWriter.println( "deleted" );
        }else  printWriter.println( "not deleted" );
//        ObjectMapper objectMapper = new ObjectMapper();
//        String requestBody = req.getReader().lines().collect( Collectors.joining());
//        student = objectMapper.readValue(requestBody, Student.class);
//        int id=student.getId();
//
//        PrintWriter writer=resp.getWriter();
//        System.out.println(id);
//        studentService.deleteStudentById( id );
    }


}
