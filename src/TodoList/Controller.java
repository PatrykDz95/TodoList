package TodoList;

import TodoList.DataModel.TodoData;
import TodoList.DataModel.TodoItem;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Controller {

private List<TodoItem> todoitems;
@FXML
private ListView<TodoItem> todoListView;
@FXML
private TextArea itemDetailsTextArea;
@FXML
private Label DeadlineLabel;
@FXML
private BorderPane mainBorderPain;

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
    dialog.initOwner(mainBorderPain.getScene().getWindow());
    try{
        Parent root = FXMLLoader.load(getClass().getResource("TodoItemDialog"));
        dialog.getDialogPane().setContent(root);
    }catch (IOException e){
        System.out.println("Couldn't load the dialog!");
        e.printStackTrace();
        return;
    }
}

@FXML
public void handleClickListView(){
    TodoItem item =  todoListView.getSelectionModel().getSelectedItem();
    itemDetailsTextArea.setText(item.getDetails());
    DeadlineLabel.setText(item.getDeadline().toString());
}
}
