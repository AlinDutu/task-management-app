package academy.learnprogramming.service;

import academy.learnprogramming.model.TodoData;
import academy.learnprogramming.model.TodoItem;

public interface TodoItemService {

    public void addItem(TodoItem todoItem);
    public void removeItem(int id);
    public TodoItem getItem(int id);
    public void updateItem(TodoItem todoItem);
    public TodoData getData();

}
