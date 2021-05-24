package atm;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionTest {
    public Connection  con= new Connection();

    @Test
    java.sql.Connection getConnection() {
        System.out.println("getConnection");

        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/useraccounts", "root", "root" +
                    "");

        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

        return (java.sql.Connection) con;

    }

    @Test
    boolean chkUserName() {
        try {

            String query = "SELECT * FROM `useraccounts`";
            Statement st = con.con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                if (rs.getString("UserName").equals("abc")) {
                    return true;
                }

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return false;
    }

    @Test
    void chkPassword() {
        try {

            String query = "SELECT * FROM `useraccounts` where UserName='" + "abc" + "'";
            Statement st = con.con.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                if (rs.getString(2).equals("1234")) {
                    return;
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    @Test
    double getBalance(String abc) {
        try {

            String query = "SELECT * FROM `useraccounts` where UserName='" + "abc" + "' ";
            Statement st = con.con.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                return rs.getDouble("Balance");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return 0;
    }

    @Test
    void updateBalance() {
        try {
            String query = "UPDATE `useraccounts` SET `Balance`= '" + 2000 + "' WHERE `UserName` = '" + "abc" + "'";
            Statement st = con.con.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        assertEquals(2000,getBalance("abc"));
    }
}