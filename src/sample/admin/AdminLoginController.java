package sample.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.Main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Paths;
import java.util.Scanner;

import static sample.Person.file;


public class AdminLoginController{

    @FXML
    private Button adminButton;
    @FXML
    private Label wrongId;
    @FXML
    private TextField adminUsername;
    @FXML
    private PasswordField adminPassword;

    public void adminLogin(ActionEvent event) throws Exception{
        try{

            writeFile();
            checkLogin();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void checkLogin() throws Exception {
        Main m=new Main();
        RandomAccessFile raf = new RandomAccessFile(file+"\\cred.txt","rwd");
        String username = raf.readLine();
        String password = raf.readLine();


        if (adminUsername.getText().toString().equals(username) && adminPassword.getText().toString().equals(password)){
            wrongId.setText("success");
            m.changeScene("admin/adminPanel.fxml");
        }
        else if (adminUsername.getText().isEmpty()&& adminPassword.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please Enter Username or Password");
            alert.setHeaderText("CANNOT BE EMPTY");
            alert.showAndWait();
            wrongId.setText("Please enter your username and password to login");
        }
        else {

            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error");
            alert1.setContentText("Please Enter Correct Username or Password");
            alert1.setHeaderText("WRONG CREDENTIALS");
            alert1.showAndWait();
            wrongId.setText("Wrong username or password");

        }

    }

    public void writeFile() throws IOException {
        try{
            RandomAccessFile raf = new RandomAccessFile(file+"\\cred.txt","rw");
            raf.writeBytes("Master\n");
            raf.writeBytes("Oogway\n");

        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    public String getUsername(){
        Scanner input = null;
        try {
            input = new Scanner(Paths.get("E:\\Object Oriented - Java\\Practice\\FINAL TERM\\VOTING APP OOP\\Credentials","rwd"));
            return input.next();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String getPass(){
        Scanner input = null;
        try {
            input = new Scanner(Paths.get("E:\\Object Oriented - Java\\Practice\\FINAL TERM\\VOTING APP OOP\\Credentials","rwd"));
            input.next();
            return input.next();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void resultLogout(ActionEvent event) throws Exception{
        Main rlo= new Main();
        rlo.changeScene("firstPage.fxml");
    }

}
