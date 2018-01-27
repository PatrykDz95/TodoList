package TodoList;

import TodoList.DataModel.DialogController;
import TodoList.DataModel.TodoData;
import TodoList.DataModel.TodoItem;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class Controller {

private List<TodoItem> todoitems;
@FXML
private ListView<TodoItem> todoListView;
@FXML
private TextArea itemDetailsTextArea;
@FXML
private Label DeadlineLabel;
@FXML
private BorderPane mainBorderPane;

public void initialize(){

    todoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TodoItem>() {
        @Override
        public void changed(ObservableValue<? extends TodoItem> observable, TodoItem oldValue, TodoItem newValue) {
            if(newValue!=null){
                TodoItem item = todoListView.getSelectionModel().getSelectedItem();
                itemDetailsTextArea.setText(item.getDetails());
                DateTimeFormatter df = DateTimeFormatter.ofPattern("d MMM yyyy");
                DeadlineLabel.setText(df.format(item.getDeadline()));
            }
        }
    });

    todoListView.getItems().setAll(TodoData.getInstance().getTodoItems());//calling singleton
    todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE); // single allows us to choose only one element
    todoListView.getSelectionModel().selectFirst();
}
@FXML
public void showNewItemDialog(){
    Dialog<ButtonType> dialog = new Dialog<>();
    dialog.initOwner(mainBorderPane.getScene().getWindow());
    dialog.setTitle("Add New Todo Item");
    dialog.setHeaderText("Creat new Todo List!");
    FXMLLoader fxmlLoader = new FXMLLoader();
    fxmlLoader.setLocation(getClass().getResource("DataModel/TodoItemDialog.fxml"));
    try{
        dialog.getDialogPane().setContent(fxmlLoader.load());
    }catch (IOException e){
        System.out.println("Couldn't load the dialog!");
        e.printStackTrace();
        return;
    }

    dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
    dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
    Optional<ButtonType> result = dialog.showAndWait();
    if(result.isPresent() && result.get() == ButtonType.OK){
        DialogController controller = fxmlLoader.getController();
        TodoItem newItem = controller.processResults();
        todoListView.getItems().setAll(TodoData.getInstance().getTodoItems()); //replaces all content, so that it updates the left bar after we add smthing
        todoListView.getSelectionModel().select(newItem);
        System.out.println("Ok, pressed");
    }else {
        System.out.println("Cancel pressed");
    }


}

@FXML
public void handleClickListView(){
    TodoItem item =  todoListView.getSelectionModel().getSelectedItem();
    itemDetailsTextArea.setText(item.getDetails());
    DeadlineLabel.setText(item.getDeadline().toString());
}
}
