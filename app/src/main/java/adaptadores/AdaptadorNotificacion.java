package adaptadores;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.core.hilosdecolores.R;
import com.core.hilosdecolores.mainmodule.Notificacion;

import java.util.ArrayList;

public  class AdaptadorNotificacion extends RecyclerView.Adapter<AdaptadorNotificacion.ViewHolder> {

    private ArrayList<Notificacion> android;
    private Context context;

    public AdaptadorNotificacion( ArrayList<Notificacion> android) {
        this.android = android;
        //  this.context=context;
    }

    @Override
    public AdaptadorNotificacion.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tarjeta_notificacion, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdaptadorNotificacion.ViewHolder viewHolder, int i) {


        String url = android.get(i).getNotificacion_imagen().toString();
        viewHolder.tv_name.setText(android.get(i).getNotificacion_titulo());
        viewHolder.tv_version.setText(android.get(i).getNotificacion_descripcion());
        Glide.with(viewHolder.imageView.getContext()).load(url)
                .thumbnail(0.5f)

                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.imageView);


    }

    @Override
    public int getItemCount() {
        return android.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name, tv_version, tv_api_level;
        ImageView imageView;
        CardView cardView;
        public ViewHolder(View view) {
            super(view);

            tv_name = (TextView) view.findViewById(R.id.notification_title);
            tv_version =(TextView)view.findViewById(R.id.notification_message);
            imageView=(ImageView)view.findViewById(R.id.notification_image);
        }
    }
}
