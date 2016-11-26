package me.kainoseto.todo.UI;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import me.kainoseto.todo.Content.Subtask;
import me.kainoseto.todo.Content.TodoContentManager;
import me.kainoseto.todo.Database.ContentManager;
import me.kainoseto.todo.R;

/**
 * Created by tylerdomitrovich on 11/25/16.
 */

public class SubtaskListAdapter extends RecyclerView.Adapter<SubtaskItemHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private ContentManager contentManager;

    //Index of the todoitem whose subtasks we are viewing
    private int uiIdx;

    public SubtaskListAdapter(Context context, int uiIdx){
        this.context = context;
        this.uiIdx = uiIdx;

        layoutInflater = LayoutInflater.from(context);
        contentManager = TodoContentManager.getInstance();
    }

    @Override
    public SubtaskItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.subtask, parent, false);
        return new SubtaskItemHolder(view);
    }

    @Override
    public void onBindViewHolder(SubtaskItemHolder holder, final int position) {
        final Subtask currItem = contentManager.getTodoItem(uiIdx).getSubtasks().get(position);

        String fullName = currItem.getName();

        /*
         If the name is too long or is a block description add '...' to the end so it
         doesn't mess up the title or any other views. TODO: Refactor this code to a utility class
         */
        int len = 30;
        if(fullName.length() < 30) {
            len = fullName.length();
        }
        for (int i = 0; i < len; i++)
        {
            if(fullName.charAt(i) == '\n')
            {
                fullName = fullName.substring(0, i-1);
                fullName += "...";
                break;
            }
        }

        if(fullName.length() > 30)
        {
            fullName = fullName.substring(0, 30);
            fullName += "...";
        }

        holder.name.setText(fullName);

        if(currItem.isDone()) {
            holder.checkMark.setImageResource(R.drawable.ic_check_circle_white_48dp);
        }

        holder.checkMark.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                boolean isDone = currItem.isDone();
                currItem.setDone(!isDone);
                contentManager.setSubtaskForTodoItem(uiIdx, position, currItem);

                AnimationUtil.checkAnimate((ImageView) v, true);
                if(isDone) {
                    ((ImageView) v).setImageResource(R.drawable.ic_remove_circle_outline_white_48dp);
                } else {
                    ((ImageView) v).setImageResource(R.drawable.ic_check_circle_white_48dp);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return contentManager.getTodoItem(uiIdx).getSubtasks().size();
    }
}
