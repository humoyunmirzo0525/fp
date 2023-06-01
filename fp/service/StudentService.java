package fp.service;

import fp.entity.Student;

import java.util.List;

public interface StudentService {

    boolean addStudent(Student student);

    int editStudent(Student student);

    boolean deleteStudentById(int id);

    List<Student> getAllStudent();

    Student getStudentByEmail(String email);

    List<Student> getRecords(int start, int total);

    int Pages(int total);
}
