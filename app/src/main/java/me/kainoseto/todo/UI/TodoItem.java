package me.kainoseto.todo.UI;

/**
 * Created by Kainoa on 10/14/16.
 */

public class TodoItem
{
    private int idx;
    private String name;
    private String description;
    private boolean done;

    public TodoItem(int idx, String name, String description, boolean done)
    {
        this.idx = idx;
        this.name = name;
        this.description = description;
        this.done = done;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdx() { return idx; }

    public void setIdx(int idx) {
        this.idx = idx;
    }
}