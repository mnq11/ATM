package atm;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button btnLogin;
    @FXML
    private TextField textField;
    @FXML
    private TextArea txtarSummary;
    @FXML
    private Button btnWithdraw;
    @FXML
    private Button btnBalance;
    
    Connection con;
    String userName = "";
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        con = new Connection();
        txtarSummary.setEditable(false);
        txtarSummary.appendText("\nEnter User Name");
        btnWithdraw.setVisible(false);
        btnBalance.setVisible(false);
        
    }
    
    @FXML
    private void handlebtnLoginAction(ActionEvent event) {
        
        if (btnLogin.getText().equals("Next") && !textField.getText().trim().isEmpty()) {
            if (con.chkUserName(textField.getText().trim())) {
                userName = textField.getText().trim();
                btnLogin.setText("Login");
                textField.setPromptText("Password");
                textField.setText("");
                txtarSummary.setText("Enter Password");
            } else {
                txtarSummary.setText("Incorrect UserName..\nEnter again");
            }
        } else if (btnLogin.getText().equals("Login") && !textField.getText().trim().isEmpty()) {
            if (con.chkPassword(userName, textField.getText().trim())) {
                btnLogin.setText("Next");
                textField.setPromptText("Amount");
                textField.setText("");
                txtarSummary.setText("Suucessfull!\nDo Transaction that you require");
                btnWithdraw.setVisible(true);
                btnBalance.setVisible(true);
            } else {
                txtarSummary.setText("Incorrect Password..\nEnter again");
                textField.setText(null);
            }
        }
    }
    
    @FXML
    private void handlebtnWithdrawAction(ActionEvent event) {
        
        if (!textField.getText().trim().isEmpty()) {
            double balance = con.getBalance(userName);
            try {
                double currentBalance = balance;
                double amount = Double.parseDouble(textField.getText().trim());
                
                if (amount <= currentBalance) {
                    con.updateBalance(userName, currentBalance - amount);
                    txtarSummary.setText("Succesfull");
                    textField.setText("");
                } else {
                    txtarSummary.setText("InSufficient Balance");
                }
                
            } catch (Exception NumberFormatException) {
                txtarSummary.setText("Only enter Numbers for Amount\nEnter again");
            }
        } else {
            txtarSummary.setText("Enter amount");
        }
        
    }
    
    @FXML
    private void handlebtnBalanceAction(ActionEvent event) {
        
        txtarSummary.setText("Your Balance: " + con.getBalance(userName));
        
    }
    
}
