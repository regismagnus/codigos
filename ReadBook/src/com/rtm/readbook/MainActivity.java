package com.rtm.readbook;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.rtm.readbook.Entity.lendoLivroEntity;
import com.rtm.readbook.Entity.livroEntity;
import com.rtm.readbook.util.AdapterListView;
import com.rtm.readbook.viwer.infoActivity;
import com.rtm.readbook.viwer.listaLivroActivity;
import com.rtm.readbook.viwer.novoActivity;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity implements OnItemClickListener, Runnable{

	private TextView tvNenhum;
	private ListView listaLendo;
	
	private ArrayList<lendoLivroEntity> lista=new ArrayList<lendoLivroEntity>();
	//private ArrayAdapter<lendoLivroEntity> adaptador;
	private AdapterListView adapterListView;
	
	private ProgressDialog dialog;
	private Handler handler = new Handler();
	
	private lendoLivroEntity[] lendoLivroEntity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		adapterListView = new AdapterListView(this, lista);
		
		tvNenhum = (TextView) findViewById(R.id.tvLendoNenhum);
		listaLendo = (ListView) findViewById(R.id.listLendo);
		
		 listaLendo.invalidate();
	     listaLendo.setAdapter(adapterListView);
	     listaLendo.setOnItemClickListener(this);
		
	     dialog = ProgressDialog.show(this, "Lista", "Buscando livros, por favor aguarde...", false, true);
	        
	     new Thread(this).start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.listaLivro:
                abrirListaLivro();
                return true;
            case R.id.cadastroLivro:
            	abrirCadastrar();
            	return true;
            case R.id.sobre:
                sobre();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

	private void sobre() {
		// TODO Auto-generated method stub
		
	}

	private void abrirCadastrar() {

		Intent it = new Intent(this, novoActivity.class);
		startActivity(it);
		
	}

	private void abrirListaLivro() {
		
		Intent it = new Intent(this, listaLivroActivity.class);
		startActivity(it);
		
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

		Intent it = new Intent(this, infoActivity.class);
		
		it.putExtra("lendoLivro", lista.get(arg2));
		
		startActivity(it);
		
	}
	
	@Override
	public void run() {
		try{
			SQLiteDatabase db = openOrCreateDatabase("book", Context.MODE_PRIVATE, null);
			
			Cursor c, c2;
			Integer idLivro;
			c = db.query(true, "lendoLivro", new String[]{"id","IdLivro","paginaAtual","ultimaLeitura"}, null, null, "IdLivro", null, "ultimaLeitura DESC", null);
				
				lendoLivroEntity = null;
				lendoLivroEntity = new lendoLivroEntity[c.getCount()];
			if(c.getCount()>0){
				
				int aux = 0;
				
				while(c.moveToNext()){
					lendoLivroEntity[aux] = new lendoLivroEntity();
					
					lendoLivroEntity[aux].setId(c.getInt(0));
					idLivro = c.getInt(1);
					lendoLivroEntity[aux].setPaginaAtual(c.getInt(2));
					lendoLivroEntity[aux].setUltimaLeitura(new SimpleDateFormat("yyyy-MM-dd").parse(c.getString(3)));
					
					c2 = db.query("livro", new String[]{"id","titulo","subtitulo","autor","editora",
							"edicao","ano","numeroPaginas","isbn","descricao"}, "id=?", new String[]{idLivro.toString()}, null, null, null);
				
					if(c2.getCount() > 0){
						c2.moveToFirst();
						livroEntity livroEntity = new livroEntity();
						
						livroEntity.setId(c2.getInt(0));
						livroEntity.setTitulo(c2.getString(1));
						livroEntity.setSubtitulo(c2.getString(2));
						livroEntity.setAutor(c2.getString(3));
						livroEntity.setEditora(c2.getString(4));
						livroEntity.setEdicao(c2.getString(5));
						livroEntity.setAno(c2.getInt(6));
						livroEntity.setNumeroPaginas(c2.getInt(7));
						livroEntity.setISBN(c2.getString(8));
						livroEntity.setDescricao(c2.getString(9));
						
						lendoLivroEntity[aux].setBookEntity(livroEntity);
				
					}
					
					c2.close();
					
					aux++;
				}
			}
			
			c.close();
			db.close();
			
			handler.post(new Runnable() {
				
				@Override
				public void run() {
				
					lista.clear();
					
					if(lendoLivroEntity.length > 0){
						tvNenhum.setVisibility(View.INVISIBLE);
					}else{
						tvNenhum.setVisibility(View.VISIBLE);
					}
					for(int i = 0; i< lendoLivroEntity.length; i++){
						lista.add(lendoLivroEntity[i]);
					}
				
					adapterListView.notifyDataSetChanged();
					
				}
			});
			
			
		}catch(Throwable ex){
			Log.i("ERRO LISTA", "Erro: " + ex.toString());
		}finally{
			dialog.dismiss();
		}
		
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		
		dialog = ProgressDialog.show(this, "Lista", "Buscando livros, por favor aguarde...", false, true);
		
		run();	
	}

}
