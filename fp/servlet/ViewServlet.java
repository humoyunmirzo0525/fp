package fp.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
import java.util.List;

@WebServlet("/ViewServlet")
public class ViewServlet extends HttpServlet {
    StudentService studentService=new StudentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter writer=response.getWriter();
        String spageid=request.getParameter("page");
        int pageid=Integer.parseInt(spageid);
        int total=3;
        if(pageid==1){}
        else{
            pageid=pageid-1;
            pageid=pageid*total;
        }
        List<Student> list = studentService.getRecords(pageid, total);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonList = objectMapper.writeValueAsString(list);

        ObjectNode customObject = objectMapper.createObjectNode();
        customObject.put("list", jsonList);
        customObject.put("totalPage", studentService.Pages(total));

        String formattedJson = objectMapper.writeValueAsString(customObject);

        writer.println(formattedJson);
        writer.close();



    }
}
