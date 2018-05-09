package com.tkartasinski.todolist;

import com.tkartasinski.todolist.datamodel.ToDoData;
import com.tkartasinski.todolist.datamodel.ToDoItem;
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
    private List<ToDoItem> toDoItems;
    @FXML
    private ListView<ToDoItem> todoListView;
    @FXML
    private TextArea itemDetailsTextArea;
    @FXML
    private Label deadlineLabel;

    public void initialize(){
        ToDoItem item1=new ToDoItem("Buy birthday card","Buy a 30th birthday card for Anna",
                LocalDate.of(2018, Month.APRIL,8));
        ToDoItem item2=new ToDoItem("Doctor's appointment","See doctor at Sierakowskiego Street",
                LocalDate.of(2018, Month.MAY,7));
        ToDoItem item3=new ToDoItem("Finish on-boarding process","Finish before vacation",
                LocalDate.of(2018, Month.MAY,22));
        ToDoItem item4=new ToDoItem("Speedcubing event","Prepare for speedcubing event in July.",
                LocalDate.of(2018, Month.JUNE,23));
        ToDoItem item5=new ToDoItem("Find place for vacation","Find comfortable and rather cheap place to stay during vacation",
                LocalDate.of(2018, Month.JUNE,30));

        toDoItems=new ArrayList<ToDoItem>();
        toDoItems.add(item1);
        toDoItems.add(item2);
        toDoItems.add(item3);
        toDoItems.add(item4);
        toDoItems.add(item5);

        ToDoData.getInstance().setTodoItems(toDoItems);

        todoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ToDoItem>() {
            @Override
            public void changed(ObservableValue<? extends ToDoItem> observable, ToDoItem oldValue, ToDoItem newValue) {
                if(newValue!=null){
                    ToDoItem item=todoListView.getSelectionModel().getSelectedItem();
                    itemDetailsTextArea.setText(item.getDetails());
                    DateTimeFormatter df= DateTimeFormatter.ofPattern("d MMMM yyyy");
                    deadlineLabel.setText(df.format(item.getDeadline()));
                }
            }
        });

        todoListView.getItems().setAll(toDoItems);
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        todoListView.getSelectionModel().selectFirst();
    }

    @FXML
    public void handleClickListView(){
        ToDoItem item=todoListView.getSelectionModel().getSelectedItem();
//        System.out.println("The selected item is "+item);
//        StringBuilder sb=new StringBuilder(item.getDetails());
//        sb.append("\n\n\n\n");
//        sb.append("Due: ");
//        sb.append(item.getDeadline().toString());
//        itemDetailsTextArea.setText(sb.toString());
        itemDetailsTextArea.setText(item.getDetails());
        deadlineLabel.setText(item.getDeadline().toString());
    }
}
