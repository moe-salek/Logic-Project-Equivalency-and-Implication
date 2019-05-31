package visual;

import debugging.Log;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import logic.Logic;
import tools.Save;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Controller {

    @FXML private TextArea inputText0;

    @FXML private TextArea inputText1;

    @FXML private Text resultText;

    @FXML private Button submitBtn;

    @FXML private VBox resultVBox;

    @FXML private VBox detailsVbox;

    @FXML private VBox helpVbox;

    @FXML private ScrollPane scrollPane;

    private static int resultNum = 0;

    public void initialize() {
        showHelp();
    }

    @FXML
    private void onSubmitBtnClicked() {
        String result = Logic.function(inputText0.getText(), inputText1.getText());
        String logs = Log.getLogs();
        if (result != null) {
            resultNum++;
            Text text = new Text(resultNum + " - " + result);
            text.setStyle("-fx-font: 14 arial;");
            resultVBox.getChildren().add(0, text);
        }
        if (logs != null) {
            detailsVbox.getChildren().remove(0, detailsVbox.getChildren().size());
            Text text = new Text(logs);
            text.setStyle("-fx-font: 13 arial;");
            detailsVbox.getChildren().addAll(text);
        }
    }

    @FXML
    private void onExitBtn() {
        System.exit(0);
    }

    @FXML
    private void onClearBtn() {
        resultNum = 0;
        resultVBox.getChildren().remove(0, resultVBox.getChildren().size());
        inputText0.deleteText(0, inputText0.getLength());
        inputText1.deleteText(0, inputText1.getLength());
        detailsVbox.getChildren().remove(0, detailsVbox.getChildren().size());
    }

    @FXML
    private void onSaveResultsBtn() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < resultVBox.getChildren().size(); ++i) {
            String result = ((Text)resultVBox.getChildren().get(i)).getText();
            str.append(result);
            str.append('\n');
        }
        Save.save("results.txt", str.toString());
    }

    @FXML
    private void onSaveLogsBtn() {

    }

    private void showHelp() {
        try(BufferedReader br = new BufferedReader(new FileReader("tools/other/help.txt"))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String everything = sb.toString();

            Text text = new Text(everything);
            text.setStyle("-fx-font: 13 arial;");
            text.setWrappingWidth(250);
            text.setTextAlignment(TextAlignment.JUSTIFY);
            helpVbox.getChildren().add(text);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
