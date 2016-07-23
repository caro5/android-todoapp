package caro.todoapp;

import java.util.Date;

/**
 * Created by carolinewong on 7/21/16.
 */
public class TodoItem {
    public String text;
    public Date dueDate;

    public TodoItem(String todoText) {
        text = todoText;
    }

    public TodoItem(String todoText, Date date) {
        text = todoText;
        dueDate = date;
    }
}
