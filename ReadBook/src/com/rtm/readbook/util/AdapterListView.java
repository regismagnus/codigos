package com.rtm.readbook.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.rtm.readbook.Entity.lendoLivroEntity;

import com.rtm.readbook.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterListView extends BaseAdapter {
	 
    private LayoutInflater mInflater;
    private ArrayList<lendoLivroEntity> itens;
 
    public AdapterListView(Context context, ArrayList<lendoLivroEntity> itens) {
        //Itens que preencheram o listview
        this.itens = itens;
        //responsavel por pegar o Layout do item.
        mInflater = LayoutInflater.from(context);
    }
 
    /**
     * Retorna a quantidade de itens
     *
     * @return
     */
    public int getCount() {
        return itens.size();
    }
 
    /**
     * Retorna o item de acordo com a posicao dele na tela.
     *
     * @param position
     * @return
     */
    public lendoLivroEntity getItem(int position) {
        return itens.get(position);
    }
 
    /**
     * Sem implementação
     *
     * @param position
     * @return
     */
    public long getItemId(int position) {
        return position;
    }
 
    public View getView(int position, View view, ViewGroup parent) {
        //Pega o item de acordo com a posção.
        lendoLivroEntity item = itens.get(position);
        //infla o layout para podermos preencher os dados
        view = mInflater.inflate(R.layout.listapersonalizada, null);
 
        //atravez do layout pego pelo LayoutInflater, pegamos cada id relacionado
        //ao item e definimos as informações.
        ((TextView) view.findViewById(R.id.tvTituloLista)).setText(item.getBookEntity().getTitulo());
        ((TextView) view.findViewById(R.id.tvPaginaLista)).setText("Parou na página: " + item.getPaginaAtual().toString());
        ((TextView) view.findViewById(R.id.tvDataLista)).setText("Lido ultima vez: " + new SimpleDateFormat("dd/MM/yyyy").format(item.getUltimaLeitura()));
        
 
        return view;
    }
}