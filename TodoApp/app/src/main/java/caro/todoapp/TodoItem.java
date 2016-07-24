package caro.todoapp;

import java.util.Date;

/**
 * Created by carolinewong on 7/21/16.
 */
public class TodoItem {
    public String text;
    public String dueDateStr;

    public TodoItem(String todoText) {
        this.text = todoText;
    }

    public TodoItem(String todoText, String dateStr) {
        this.text = todoText;
        this.dueDateStr = dateStr;
    }
}
