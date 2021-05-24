package atm;

import javax.swing.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connection {

    public java.sql.Connection con = null;
    Statement stmt = null;

    public Connection() {
        con = getConnection();
    }

    public java.sql.Connection getConnection() {

        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/useraccounts", "root", "root" +
                    "");

        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return con;

    }

    public boolean chkUserName(String userName) {

        try {

            String query = "SELECT * FROM `useraccounts`";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                if (rs.getString("UserName").equals(userName)) {
                    return true;
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return false;
    }

    public boolean chkPassword(String userName, String password) {

        try {

            String query = "SELECT * FROM `useraccounts` where UserName='" + userName + "'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                if (rs.getString(2).equals(password)) {
                    return true;
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return false;
    }

    public double getBalance(String userName) {

        try {

            String query = "SELECT * FROM `useraccounts` where UserName='" + userName + "' ";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                return rs.getDouble("Balance");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return 0;
    }

    void updateBalance(String userName, double balance) {
        try {
            String query = "UPDATE `useraccounts` SET `Balance`= '" + balance + "' WHERE `UserName` = '" + userName + "'";
            Statement st = con.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }


}
