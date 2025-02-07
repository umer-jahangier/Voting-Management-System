package sample.voter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.Main;
import sample.Person;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.SQLOutput;

import static sample.Person.file;
import static sample.Person.line;

public class VoterLogin {
    public VoterLogin(){}
    @FXML
    private Button voterLoginButton;
    @FXML
    private TextField voterUsername;
    @FXML
    private PasswordField voterPassword;
    @FXML
    private Label voterLabel;

    public void voterPanelWork(ActionEvent event) throws Exception{
        checkvoterlogin();
    }

    public void checkvoterlogin() throws Exception{
        Main vl = new Main();
        int i=0;
        try{
            RandomAccessFile raf = new RandomAccessFile(file+"\\voters.txt","rwd");
            Person.countLines();
            for(i=0;i<line;i+=6){
                for(int j=0;j<3;j++)
                    raf.readLine();
                String usr = raf.readLine().substring(9);
                String pass = raf.readLine().substring(10);
                if(voterUsername.getText().equals(usr) && voterPassword.getText().equals(pass)) {
                    voterLabel.setText("success");
                    vl.changeScene("voter/voterPanel.fxml");
                    return;
                }
                raf.readLine();
            }
            JOptionPane.showMessageDialog(null, "Password Not Matched");
            voterLabel.setText("Please Enter Right Username or Password");
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    public void resultLogout(ActionEvent event) throws Exception {
        Main rlo = new Main();
        rlo.changeScene("firstPage.fxml");
    }

}
