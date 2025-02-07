package sample.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import sample.Main;
import sample.Person;
import javafx.fxml.Initializable;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import static sample.Person.file;

public class AdminPanel implements Initializable {
    @FXML
    private TableView<Person> tableView;
    @FXML
    private TableColumn<Person, String> colName;
    @FXML
    private TableColumn<Person, String> colSex;
    @FXML
    private TableColumn<Person, String> colPhoneNumber;
    @FXML
    private TableColumn<Person, String> colVoterID;
    @FXML
    private TableColumn<Person, String> colPassword;
    @FXML
    private TextField name, sex, phoneNumber, voterID, password;
    @FXML
    private Button addVoter, removeVoter, adminLogOut;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colName.setCellValueFactory(new PropertyValueFactory<>("voterName"));
        colSex.setCellValueFactory(new PropertyValueFactory<>("voterSex"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("voterPhoneNumber"));
        colVoterID.setCellValueFactory(new PropertyValueFactory<>("voterVoterID"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("voterPassword"));

        try {
            tableView.setItems(loadVotersFromFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

        tableView.setEditable(true);
        colName.setCellFactory(TextFieldTableCell.forTableColumn());
        colSex.setCellFactory(TextFieldTableCell.forTableColumn());
        colPhoneNumber.setCellFactory(TextFieldTableCell.forTableColumn());
        colVoterID.setCellFactory(TextFieldTableCell.forTableColumn());
        colPassword.setCellFactory(TextFieldTableCell.forTableColumn());

        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    private ObservableList<Person> loadVotersFromFile() throws IOException {
        ObservableList<Person> people = FXCollections.observableArrayList();
        File voterFile = new File(file + "\\voters.txt");

        if (!voterFile.exists()) return people;

        try (BufferedReader br = new BufferedReader(new FileReader(voterFile))) {
            String name, sex, phone, voterId, password;
            while ((name = br.readLine()) != null) {
                sex = br.readLine();
                phone = br.readLine();
                voterId = br.readLine();
                password = br.readLine();
                br.readLine(); // Skip blank line

                if (name.startsWith("Name: ") &&
                        sex.startsWith("Sex: ") &&
                        phone.startsWith("Phone: ") &&
                        voterId.startsWith("VoterID: ") &&
                        password.startsWith("Password: ")) {

                    // Do not trim leading zeros
                    String extractedName = name.replace("Name: ", "").trim();
                    String extractedSex = sex.replace("Sex: ", "").trim();
                    String extractedPhone = phone.replace("Phone: ", "").trim();
                    String extractedVoterID = voterId.replace("VoterID: ", "").trim();
                    String extractedPassword = password.replace("Password: ", "").trim();

                    people.add(new Person(extractedName, extractedSex, extractedPhone, extractedVoterID, extractedPassword, false));
                } else {
                    System.err.println("Warning: Malformed entry in voters.txt, skipping...");
                }
            }
        }
        return people;
    }

    private void saveVotersToFile() {
        File voterFile = new File(file + "\\voters.txt");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(voterFile))) {
            for (Person p : tableView.getItems()) {
                bw.write("Name: " + p.getVoterName() + "\n");
                bw.write("Sex: " + p.getVoterSex() + "\n");
                bw.write("Phone: " + p.getVoterPhoneNumber() + "\n");  // Keeps leading zeros
                bw.write("VoterID: " + p.getVoterVoterID() + "\n");    // Keeps full ID
                bw.write("Password: " + p.getVoterPassword() + "\n");
                bw.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeNameEvent(TableColumn.CellEditEvent<Person, String> editedCell) {
        Person personSelected = tableView.getSelectionModel().getSelectedItem();
        personSelected.setVoterName(editedCell.getNewValue());
        saveVotersToFile();
    }

    public void changeSexEvent(TableColumn.CellEditEvent<Person, String> editedCell) {
        Person personSelected = tableView.getSelectionModel().getSelectedItem();
        personSelected.setVoterSex(editedCell.getNewValue());
        saveVotersToFile();
    }

    public void changePhoneNumberEvent(TableColumn.CellEditEvent<Person, String> editedCell) {
        Person personSelected = tableView.getSelectionModel().getSelectedItem();
        personSelected.setVoterPhoneNumber(editedCell.getNewValue());  // Keeps number as a string
        saveVotersToFile();
    }

    public void changeVoterIDEvent(TableColumn.CellEditEvent<Person, String> editedCell) {
        Person personSelected = tableView.getSelectionModel().getSelectedItem();
        personSelected.setVoterVoterID(editedCell.getNewValue());  // Keeps ID format
        saveVotersToFile();
    }

    public void changePasswordEvent(TableColumn.CellEditEvent<Person, String> editedCell) {
        Person personSelected = tableView.getSelectionModel().getSelectedItem();
        personSelected.setVoterPassword(editedCell.getNewValue());
        saveVotersToFile();
    }

    @FXML
    public void addVoter(ActionEvent event) {
        if (name.getText().isEmpty() || sex.getText().isEmpty() ||
                phoneNumber.getText().isEmpty() || voterID.getText().isEmpty() ||
                password.getText().isEmpty()) {
            showAlert("Error", "All fields must be filled out!");
            return;
        }

        Person newPerson = null;
        try {
            newPerson = new Person(
                    name.getText().trim(),
                    sex.getText().trim(),
                    phoneNumber.getText().trim(),  // Prevents number trimming
                    voterID.getText().trim(),
                    password.getText().trim(),
                    true
            );
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        tableView.getItems().add(newPerson);
        saveVotersToFile();
    }

    @FXML
    public void removeVoter(ActionEvent event) {
        ObservableList<Person> selectedRows = tableView.getSelectionModel().getSelectedItems();
        tableView.getItems().removeAll(selectedRows);
        saveVotersToFile();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void adminLogOut(ActionEvent event) throws Exception {
        Main ma = new Main();
        ma.changeScene("FirstPage.fxml");
    }
}
