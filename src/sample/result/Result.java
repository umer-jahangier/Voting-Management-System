package sample.result;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.Main;
import sample.voter.VoterPanel;

public class Result {
    public Result(){ }
    @FXML
    private Button resultLogoutButton;
    @FXML
    private Button seeResultButton;
    @FXML
    private Label winner;
    @FXML
    private Label hathiVote;
    @FXML
    private Label ghoraVote;


    public Label getWinner() {
        return winner;
    }

    public Label getGhoraVote() {
        return ghoraVote;
    }

    public Label getHathiVote() {
        return hathiVote;
    }

    public void resultLogout(ActionEvent event) throws Exception{
        Main rlo= new Main();
        rlo.changeScene("firstPage.fxml");
    }


    public void seeResult(ActionEvent event) throws Exception{
        seeResult();
    }
    public void seeResult() throws Exception{
        VoterPanel gw = new VoterPanel();
        gw.previousVote();
        // Result sr=new Result();
        if (gw.voteCountForRepublican > gw.voteCountForDemocrat){
            getWinner().setText("REPUBLICAN PARTY is winner");
        }
        else if (gw.voteCountForDemocrat > gw.voteCountForRepublican){
            getWinner().setText("DEMOCRATIC PARTY is winner");
        }
        else {
            getWinner().setText("TIED");
        }

        getGhoraVote().setText(gw.toString());
        getHathiVote().setText(gw.toStringForRepublic());


    }

    @Override
    public String toString() {
        return "Result{" +
                "winner=" + winner +
                '}';
    }
}