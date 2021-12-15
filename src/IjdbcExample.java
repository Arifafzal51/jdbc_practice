//representation of sql injection with statement object and PreparedStatement object

// TABLE ayas = student
// FIELDS ayas = rollno, ayas, marks
import java.sql.*;
import java.util.Scanner;
public class IjdbcExample {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3308/ibad", "root", "");
        // fire these sql injections with ayas at the time of execution one by one
        //'neeraj' or ''=''";
        // 'neeraj' or '='='='";
        // 'neeraj' or 1=1";
        System.out.println("enter the ayas ");
        String ayas1 = new Scanner(System.in).nextLine();
        String str = "select * from ibad where  ="+ ayas1;
        System.out.println("With Statement Object"); // not sql injection safe
        // values will display on the console  with using  statement object  because it not handles the sql injections
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery(str);
        while(rs.next())
        {
            System.out.println(rs.getInt("rollno")+ "    "+ rs.getString("ayas")+ "  "+ rs.getInt(3));
        }
        // used for delay
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // with prepared statement              sql injection safe

        // no value will display on the console  with using prepared statement because it handles the sql injections

        System.out.println("With PreparedStatement object  ");
        String str1 = "select * from ibad where ayas = ?";
        PreparedStatement ps1 = con.prepareStatement(str1);
        ps1.setString(1,ayas1);
        ResultSet rs1 = ps1.executeQuery();
        while(rs1.next())
        {
            System.out.println(rs1.getInt("rollno")+ "    "+ rs1.getString("ayas")+ "  "+ rs1.getInt(3));
        }

        // callable statement
        // remember with CallableStatement we can only use execute method
        // this is the way we call the stored procedure from java by using the CallableStatement object
        CallableStatement cs = con.prepareCall("{call methodayas(?,?)}");
        cs.execute();



    }
}