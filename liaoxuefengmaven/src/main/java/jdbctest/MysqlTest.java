package jdbctest;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * App entry for Maven project.
 *
 * @author liaoxuefeng
 */
public class MysqlTest {

    public static void main(String[] args) throws Exception {
//        Class.forName("com.mysql.jdbc.Driver");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        List<Student> students = queryStudents();
        students.forEach(System.out::println);
    }

    static List<Student> queryStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
            try (PreparedStatement ps = conn
                    .prepareStatement("SELECT * FROM students WHERE grade = ? AND score >= ?")) {
                ps.setInt(1, 3); // 第一个参数grade=?
                ps.setInt(2, 90); // 第二个参数score=?
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        students.add(extractRow(rs));
                    }
                }
            }
        }
        return students;
    }

    static Student extractRow(ResultSet rs) throws SQLException {
        var std = new Student();
        std.setId(rs.getLong("id"));
        std.setName(rs.getString("name"));
        std.setGender(rs.getBoolean("gender"));
        std.setGrade(rs.getInt("grade"));
        std.setScore(rs.getInt("score"));
        return std;
    }

    static final String jdbcUrl = "jdbc:mysql://localhost:3306/learnjdbc?useSSL=true&characterEncoding=utf8&serverTimezone=UTC";
    static final String jdbcUsername = "root";
    static final String jdbcPassword = "lixiaoxu";
}
