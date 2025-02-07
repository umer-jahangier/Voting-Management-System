package sample;

import javafx.beans.property.SimpleStringProperty;
import java.io.*;

public class Person {
    private SimpleStringProperty voterName;
    private SimpleStringProperty voterSex;
    private SimpleStringProperty voterPhoneNumber;
    private SimpleStringProperty voterVoterID;
    private SimpleStringProperty voterPassword;
    public static int line = 0;
    public static File file = new File("Credentials");

    public Person(String voterName, String voterSex, String voterPhoneNumber, String voterVoterID, String voterPassword, boolean write) throws FileNotFoundException {
        this.voterName = new SimpleStringProperty(voterName);
        this.voterSex = new SimpleStringProperty(voterSex);
        this.voterPhoneNumber = new SimpleStringProperty(voterPhoneNumber);
        this.voterVoterID = new SimpleStringProperty(voterVoterID);
        this.voterPassword = new SimpleStringProperty(voterPassword);
        if (write) {
            createFolder();
            countLines();
            addData(voterName, voterSex, voterPhoneNumber, voterVoterID, voterPassword);
        }
    }

    public void createFolder() {
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public void addData(String voterName, String voterSex, String voterPhoneNumber, String username, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file + "\\voters.txt", true))) {
            writer.write("Name: " + voterName + "\n");
            writer.write("Gender: " + voterSex + "\n");
            writer.write("Phone No: " + voterPhoneNumber + "\n");
            writer.write("Username: " + username + "\n");
            writer.write("Password: " + password + "\n\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void countLines() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file + "\\voters.txt"))) {
            line = 0;
            while (reader.readLine() != null) {
                line++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getVoterName() { return voterName.get(); }
    public void setVoterName(String voterName) { this.voterName.set(voterName); }
    public String getVoterSex() { return voterSex.get(); }
    public void setVoterSex(String voterSex) { this.voterSex.set(voterSex); }
    public String getVoterPhoneNumber() { return voterPhoneNumber.get(); }
    public void setVoterPhoneNumber(String voterPhoneNumber) { this.voterPhoneNumber.set(voterPhoneNumber); }
    public String getVoterVoterID() { return voterVoterID.get(); }
    public void setVoterVoterID(String voterVoterID) { this.voterVoterID.set(voterVoterID); }
    public String getVoterPassword() { return voterPassword.get(); }
    public void setVoterPassword(String voterPassword) { this.voterPassword.set(voterPassword); }
}
