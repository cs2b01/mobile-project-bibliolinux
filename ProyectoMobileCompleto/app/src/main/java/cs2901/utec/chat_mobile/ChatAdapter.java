package cs2901.utec.chat_mobile;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    public JSONArray elements;
    private Context context;

    public ChatAdapter(JSONArray elements, Context context) {
        this.elements = elements;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView first_line, second_line;
        RelativeLayout container;

        public ViewHolder(View itemView) {
            super(itemView);
            first_line = itemView.findViewById(R.id.element_view_first_line);
            second_line = itemView.findViewById(R.id.element_view_second_line);
            container = itemView.findViewById(R.id.element_view_container);
        }
    }

    @NonNull
    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_view,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.ViewHolder holder, int position) { // TODO fixme
        try {
            final JSONObject element = elements.getJSONObject(position);
            final String name = element.getString("name"); // name of the command
            final String id = element.getString("id"); // id of the command
            final String description = element.getString("description"); // description of the command

            holder.first_line.setText(name);

            holder.container.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent goToMessage = new Intent(context, CommandActivity.class);
                    goToMessage.putExtra("id", id);
                    goToMessage.putExtra("name", name);
                    goToMessage.putExtra("description", description);
                    context.startActivity(goToMessage);
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return elements.length();
    }
}