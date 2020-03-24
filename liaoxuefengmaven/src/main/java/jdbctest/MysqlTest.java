package jdbctest;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import javax.xml.transform.Result;
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
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(jdbcUsername);
        config.setPassword(jdbcPassword);
        config.addDataSourceProperty("connectionTimeout", "1000"); // 连接超时：1秒
        config.addDataSourceProperty("idleTimeout", "60000"); // 空闲超时：60秒
        config.addDataSourceProperty("maximumPoolSize", "10"); // 最大连接数：10
        DataSource ds = new HikariDataSource(config);
        try(Connection connection = ds.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(" select count(*) from students")){
                ResultSet result = ps.executeQuery();
                while (result.next()) {
                    System.out.println(result.getInt(1));
                }
            }
        }

        int i = 0;
        switch (i){
            case 1:
                List<Student> students = queryStudentsAll();
                students.forEach(System.out::println);
                System.out.println("insert new student...");
                try (Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
                    try (PreparedStatement ps = conn
                            .prepareStatement("INSERT INTO students (name, gender, grade, score) VALUES (?, ?, ?, ?)")) {
                        ps.setString(1, "大白");
                        ps.setBoolean(2, true);
                        ps.setInt(3, 3);
                        ps.setInt(4, 97);
                        int n = ps.executeUpdate();
                        System.out.println(n + " inserted.");
                    }
                }
                System.out.println("insert new student and return id...");
                try (Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
                    try (PreparedStatement ps = conn.prepareStatement(
                            "INSERT INTO students (name, gender, grade, score) VALUES (?, ?, ?, ?)",
                            Statement.RETURN_GENERATED_KEYS // 返回数据库自动生成的key
                    )) {
                        ps.setString(1, "老王");
                        ps.setBoolean(2, true);
                        ps.setInt(3, 3);
                        ps.setInt(4, 99);
                        int n = ps.executeUpdate();
                        long id = 0;
                        try (ResultSet rs = ps.getGeneratedKeys()) {
                            if (rs.next()) {
                                id = rs.getLong(1);
                            }
                        }
                        System.out.println(n + " inserted. id = " + id);
                    }
                }
                System.out.println("update student...");
                try (Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
                    try (PreparedStatement ps = conn.prepareStatement("UPDATE students set score = ? WHERE name = ?")) {
                        ps.setInt(1, 99);
                        ps.setString(2, "小贝");
                        int n = ps.executeUpdate();
                        System.out.println(n + " updated.");
                    }
                }
                System.out.println("delete student...");
                try (Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
//            String sql = "DELETE FROM students WHERE id = 15 or id = 16 or id = 17 or id = 18";
                    String sql1 = "DELETE FROM students WHERE score < ?";
                    try (PreparedStatement ps = conn.prepareStatement(sql1)) {
                        ps.setInt(1, 80);
                        int n = ps.executeUpdate();
                        System.out.println(n + " deleted.");
                    }
                }
                break;
            case 2:
                //事务
                insertStudents("大黄", true, 3, 100);
                insertStudents("大头", false, 3, 101);
                students = queryStudents2();
                students.forEach(System.out::println);
                break;
            case 3:
                //jdbc batch test
                ArrayList<Student> studentArrayList = new ArrayList<>();
                Connection coon = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
                for(int i1 = 0; i1<3000; i1++){
                    Student student = new Student();
                    student.setGender(true);
                    student.setName("batch" + i1);
                    student.setGrade(3);
                    student.setScore(100);
                    studentArrayList.add(student);
                }
                Long startTime1 = System.currentTimeMillis();
                for(int i1 = 0; i1<studentArrayList.size(); i1++) {
                    try (PreparedStatement preparedStatement = coon
                            .prepareStatement("INSERT INTO students (name, gender, grade, score) VALUES (?, ?, ?, ?)")) {
                        preparedStatement.setString(1, studentArrayList.get(i1).getName());
                        preparedStatement.setBoolean(2, studentArrayList.get(i1).isGender());
                        preparedStatement.setInt(3, studentArrayList.get(i1).getGrade());
                        preparedStatement.setInt(4, studentArrayList.get(i1).getScore());
                        preparedStatement.executeUpdate();
                    }
                }
                Long endTime1 = System.currentTimeMillis();
                System.out.println("逐条插入花费的时间是" + (endTime1 - startTime1) + "ms");
                Long startTime2 = System.currentTimeMillis();
                try(PreparedStatement preparedStatement = coon
                        .prepareStatement("INSERT INTO students (name, gender, grade, score) VALUES (?, ?, ?, ?)")){
                    for(int i1 = 0; i1<studentArrayList.size(); i1++) {
                        preparedStatement.setString(1, studentArrayList.get(i1).getName());
                        preparedStatement.setBoolean(2, studentArrayList.get(i1).isGender());
                        preparedStatement.setInt(3, studentArrayList.get(i1).getGrade());
                        preparedStatement.setInt(4, studentArrayList.get(i1).getScore());
                        preparedStatement.addBatch();
                    }
                    int[] arr = preparedStatement.executeBatch();
                }
                Long endTime2 = System.currentTimeMillis();
                System.out.println("使用Batch插入花费的时间是" + (endTime2 - startTime2) + "ms");

        }

//        queryStudentsAll().forEach(System.out::println);
    }
    static void insertStudents(String name, boolean gender, int grade, int score) throws SQLException {
        try (Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
            boolean isAutoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false); // 关闭自动提交事务
            try (PreparedStatement ps = conn
                    .prepareStatement("INSERT INTO students (name, gender, grade, score) VALUES (?, ?, ?, ?)")) {
                ps.setString(1, name);
                ps.setBoolean(2, gender);
                ps.setInt(3, grade);
                ps.setInt(4, score);
                int n = ps.executeUpdate();
                System.out.println(n + " inserted.");
            }
            if (score > 100) {
                conn.rollback();
                System.out.println("rollback.");

            } else {
                conn.commit();
                System.out.println("commit.");
            }
            conn.setAutoCommit(isAutoCommit); // 恢复AutoCommit设置
        }
    }

    static List<Student> queryStudents2() throws SQLException {
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

    static List<Student> queryStudentsAll() throws SQLException {
        List<Student> students = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM students")) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        students.add(extractRow(rs));
                    }
                }
            }
        }
        return students;
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

    //之前一直用的useSSL=true运行没问题，加线程池后就一直报错。现在改为false，可以正常运行。就很迷，之前明明可以的。。2020年3月24日20:50:40
//    static final String jdbcUrl = "jdbc:mysql://localhost:3306/learnjdbc?useSSL=true&characterEncoding=utf8&serverTimezone=UTC";
    static final String jdbcUrl = "jdbc:mysql://localhost:3306/learnjdbc?useSSL=false&characterEncoding=utf8&serverTimezone=UTC&rewriteBatchedStatements=true";//Batch批量插入
    static final String jdbcUsername = "learn";
    static final String jdbcPassword = "learnpassword";
//    static final String jdbcUsername = "root";
//    static final String jdbcPassword = "lixiaoxu";
}
