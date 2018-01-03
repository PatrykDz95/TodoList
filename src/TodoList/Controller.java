package TodoList;

import TodoList.DataModel.TodoItem;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;

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

public void initialize(){
    TodoItem item1 = new TodoItem("Mail Birthday card", "Buy birthday card",
            LocalDate.of(2018, Month.FEBRUARY,15));
    TodoItem item2 = new TodoItem("Doctor Appointment", "See the doctor",
            LocalDate.of(2018, Month.JANUARY,11));
    TodoItem item3 = new TodoItem("Meet Mike", "Talk to Mike in Work",
            LocalDate.of(2018, Month.FEBRUARY,25));
    TodoItem item4 = new TodoItem("Pick up Daniel from station", "Daniel arrives on March 23 on the 14:00 train",
            LocalDate.of(2018, Month.JANUARY,15));

    todoitems = new ArrayList<TodoItem>();
    todoitems.add(item1);
    todoitems.add(item2);
    todoitems.add(item3);
    todoitems.add(item4);

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

    todoListView.getItems().setAll(todoitems);
    todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE); // single allows us to choose only one element
    todoListView.getSelectionModel().selectFirst();
}

@FXML
public void handleClickListView(){
    TodoItem item =  todoListView.getSelectionModel().getSelectedItem();
    itemDetailsTextArea.setText(item.getDetails());
    DeadlineLabel.setText(item.getDeadline().toString());
    //System.out.println("The selected item is: " +item);
//    StringBuilder sb = new StringBuilder(item.getDetails());
//
//    sb.append("\n\n\n\n");
//    sb.append("Due: " + item.getDeadline().toString());
    //  itemDetailsTextArea.setText(sb.toString());
}
}
