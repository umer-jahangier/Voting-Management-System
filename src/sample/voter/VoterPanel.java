package sample.voter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import sample.Main;
import sample.Person;

import javax.swing.*;
import java.io.IOException;
import java.io.RandomAccessFile;

import static sample.Person.file;


public class VoterPanel {
    public VoterPanel() throws Exception{}
    public static int voteCountForDemocrat=0;
    public static int voteCountForRepublican=0;

    @FXML
    private CheckBox republicanPartyBox;

    @FXML
    private CheckBox democraticPartyBox;

    @FXML
    private Button voterLogOut;
    @FXML
    private Button casteVote;

    public void logOut(ActionEvent event) throws Exception{
        Main lo=new Main();
        lo.changeScene("firstPage.fxml");
    }
    public void casteVote(ActionEvent event)throws Exception{
        countVote();
        previousVote();
    }
    public void countVote() throws Exception{
            if (republicanPartyBox.isSelected()){
                voteCountForRepublican++;

            }
            else if (democraticPartyBox.isSelected()){
                voteCountForDemocrat++;
            }
            else{
                JOptionPane.showMessageDialog(null, "No Party Selected");
                return;
            }
        JOptionPane.showMessageDialog(null, "Vote Casted Successfully");
        Main lo=new Main();
        lo.changeScene("firstPage.fxml");
        updateVoteFile();
    }

    public void previousVote() throws Exception{
        RandomAccessFile raf= new RandomAccessFile(file+"\\votes.txt","rwd");
        voteCountForDemocrat = Integer.parseInt(raf.readLine());
        voteCountForRepublican = Integer.parseInt(raf.readLine());
    }

    public void updateVoteFile() throws Exception{
        try {
                RandomAccessFile raf = new RandomAccessFile(file+"\\votes.txt","rwd");
                raf.writeBytes(String.valueOf(voteCountForDemocrat)+"\n");
                raf.writeBytes(String.valueOf(voteCountForRepublican));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void handleRepublicanParty() throws Exception{
        if(republicanPartyBox.isSelected()){
            democraticPartyBox.setSelected(false);
            // voteCountForRepublican++;
        }
    }
    @FXML
    private void handleDemocraticParty() throws Exception{
        if (democraticPartyBox.isSelected()){
            republicanPartyBox.setSelected(false);
            //voteCountForDemocrat++;
        }
    }

    public static int getVoteCountForDemocrat() throws Exception {
        return voteCountForDemocrat;
    }

    public static int getVoteCountForRepublican() throws Exception {
        return voteCountForRepublican;
    }

    @Override
    public String toString() {
        try {
            return ""+getVoteCountForDemocrat();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String toStringForRepublic() throws Exception {
        return ""+getVoteCountForRepublican();
    }
}