package TodoList.DataModel;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class DialogController {

    @FXML
    private TextField shortDescriptionField;
    @FXML
    private TextArea detailsArea;
    @FXML
    private DatePicker deadLinePicker;

    //after we click the OK Button
    public TodoItem processResults(){
        String shortDescripton = shortDescriptionField.getText().trim();
        String details = detailsArea.getText().trim();
        LocalDate deadlineValue = deadLinePicker.getValue();

        TodoItem newItem = new TodoItem(shortDescripton,details,deadlineValue);
        TodoData.getInstance().addTodoItem(newItem);
        return newItem;

    }

}
