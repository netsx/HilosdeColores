package adaptadores;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.core.hilosdecolores.R;
import com.core.hilosdecolores.mainmodule.Categoria;
import com.core.hilosdecolores.mainmodule.MainModule.view.Galeria;

import java.util.ArrayList;

public class AdaptadorCategoria extends RecyclerView.Adapter<AdaptadorCategoria.ViewHolder> {
    private ArrayList<Categoria> categorias;

    Context context;
    Activity mActivity;
    public AdaptadorCategoria(ArrayList<Categoria> android,Activity mActivity) {
        this.categorias = android;
        this.mActivity=mActivity;
    }

    @Override
    public AdaptadorCategoria.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tarjeta_categoria, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdaptadorCategoria.ViewHolder viewHolder, int i) {

        final String id= categorias.get(i).getCategoria_id();
        final String ruta =categorias.get(i).getImagen();

        viewHolder.tv_name.setText(categorias.get(i).getCategoria_titulo());

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(viewHolder.cardView.getContext(), Galeria.class);
                intent.putExtra("id",id);
                intent.putExtra("ruta",ruta);
                viewHolder.cardView.getContext().startActivity(intent);

                mActivity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);


            }
        });

        Glide.with(viewHolder.imageView.getContext())
                .load(categorias.get(i).getImagen())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(viewHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return categorias.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name, tv_version, tv_api_level;

        AppCompatImageView imageView;
        CardView cardView;
        public ViewHolder(View view) {
            super(view);

            tv_name = (TextView) view.findViewById(R.id.categoria_title);
            cardView =(CardView)view.findViewById(R.id.cardView_categoria);
            imageView=(AppCompatImageView)view.findViewById(R.id.categoria_image);

        }
    }
}
