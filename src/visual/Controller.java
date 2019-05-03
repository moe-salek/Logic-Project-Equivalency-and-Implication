package visual;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import logic.Logic;

public class Controller {

    @FXML private TextArea inputText0;

    @FXML private TextArea inputText1;

    @FXML private Text resultText;

    @FXML private Button submitBtn;

    @FXML private VBox resultVBox;

    @FXML private ScrollPane scrollPane;

    private static int resultNum = 0;

    public void initialize() { }

    @FXML
    private void onSubmitBtnClicked() {

        String result = Logic.function(inputText0.getText(), inputText1.getText());
        if (result != null) {
            resultNum++;
            Text text = new Text(resultNum + " - " + result);
            text.setStyle("-fx-font: 13 arial;");
            resultVBox.getChildren().add(0, text);
        }
    }

}
