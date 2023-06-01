package fp.service.impl;

import fp.entity.Student;
import fp.service.StudentService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpl implements StudentService {
    private final static String className = "org.postgresql.Driver";
    private final static String db_driver="org.hibernate.dialect.PostgreSQLDialect";
    private final static String db_url = "jdbc:postgresql://localhost:5432/university";
    private final static String db_studentname = "postgres";
    private final static String db_password = "6832748";

    @Override
    public boolean addStudent(Student student) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName( "org.postgresql.Driver" );
            connection = DriverManager.getConnection( db_url, db_studentname, db_password );
            String sql = "insert into students(name,email,age) values(?,?,?);";
            preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setString( 1, student.getName() );
            preparedStatement.setString( 2, student.getEmail() );
            preparedStatement.setInt( 3, student.getAge() );
            return !preparedStatement.execute();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public boolean deleteStudentById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            System.out.println( className );
            Class.forName( "org.postgresql.Driver" );
            connection = DriverManager.getConnection( db_url, db_studentname, db_password );
            String sql = "delete from students where id=?;";
            preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setInt( 1, id );
            return preparedStatement.executeUpdate() != 0;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
    @Override
    public List<Student> getAllStudent() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName( "org.postgresql.Driver" );
            connection = DriverManager.getConnection( db_url, db_studentname, db_password );
            String sql = "select * from students";
            preparedStatement = connection.prepareStatement( sql );
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Student> studentList = new ArrayList<>();
            Student student;
            while (resultSet.next()) {
                student = new Student(  );
                student.setId( resultSet.getInt( "id" ) );
                student.setName( resultSet.getString( "name" ) );
                student.setEmail( resultSet.getString( "email" ) );
                student.setAge( resultSet.getInt( "age" ) );
                studentList.add(student);
            }
                        return studentList;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;

    }


    @Override
    public Student getStudentByEmail(String email) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName( "org.postgresql.Driver" );
            connection = DriverManager.getConnection( db_url, db_studentname, db_password );
            String sql = "select * from students s where s.email=?";
            preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setString( 1, email );
            ResultSet resultSet = preparedStatement.executeQuery();
            Student student = new Student();
            if (resultSet.next()) {
                student.setId( resultSet.getInt( "id" ) );
                student.setName( resultSet.getString( "name" ) );
                student.setEmail( resultSet.getString( "email" ) );
                student.setAge( resultSet.getInt( "age" ) );
                return student;
            }
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;

    }

    @Override
    public int editStudent(Student student) {
        int result=0;
    Connection connection=null;
    PreparedStatement preparedStatement=null;
    String sql="update students set email=?, name=?,  age=? where id=?";
        try {
            Class.forName( "org.postgresql.Driver" );
            connection=DriverManager.getConnection( db_url,db_studentname,db_password );
            preparedStatement=connection.prepareStatement( sql );
            preparedStatement.setString( 2, student.getName() );
            preparedStatement.setString( 1, student.getEmail() );
            preparedStatement.setInt( 3, student.getAge() );
            preparedStatement.setInt( 4, student.getId() );
            preparedStatement.executeUpdate();
           return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public  List<Student> getRecords(int start, int total){
        List<Student> list=new ArrayList<>();
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        String sql="select * from students limit "+total+" offset "+(start);
        try{
            Class.forName( className );
            connection=DriverManager.getConnection( db_url,db_studentname,db_password );
            preparedStatement=connection.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            while(rs.next()){
                Student student=new Student();
                student.setId(rs.getInt(1));
                student.setName(rs.getString(2));
                student.setEmail(rs.getString(3));
                student.setAge(rs.getInt(4));
                list.add(student);
            }
            connection.close();
        }catch(Exception e){System.out.println(e);}
        return list;
    }
    @Override
    public int Pages(int total){
        double totalPages=1.0*getAllStudent().size()/total;
        return (int) Math.ceil( totalPages );
    }
}
