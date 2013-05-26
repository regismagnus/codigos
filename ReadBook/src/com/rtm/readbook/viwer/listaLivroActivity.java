package com.rtm.readbook.viwer;

import java.util.ArrayList;

import com.rtm.readbook.R;
import com.rtm.readbook.Entity.livroEntity;

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
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class listaLivroActivity extends Activity implements OnClickListener, OnItemClickListener, Runnable{

	private EditText etBuscar;
	private Button bBuscar;
	private TextView tvNenhum;
	private ListView listView;
	
	private ArrayList<livroEntity> lista=new ArrayList<livroEntity>();
	private ArrayAdapter<livroEntity> adaptador;
	
	private ProgressDialog dialog;
	private Handler handler = new Handler();

	private livroEntity[] livroEntity;
	
	private String busca = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listalivro);
		
		adaptador = new ArrayAdapter<livroEntity>(this, android.R.layout.simple_list_item_1, lista);
		
		etBuscar = (EditText) findViewById(R.id.etBusca);
		tvNenhum = (TextView) findViewById(R.id.tvNenhum);
		bBuscar = (Button) findViewById(R.id.bBuscar);
		listView = (ListView) findViewById(R.id.listLivro);
		
		bBuscar.setOnClickListener(this);
		
		tvNenhum.setVisibility(View.VISIBLE);
		
		 listView.invalidate();
	     listView.setAdapter(adaptador);
	     listView.setOnItemClickListener(this);
		
	     dialog = ProgressDialog.show(this, "Lista", "Buscando livros, por favor aguarde...", false, true);
	        
	     new Thread(this).start();
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lista, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.cadastroLivro:
                cadastrarLivro();
                return true;
            case R.id.inicio:
            	inicio();
            	return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

	private void inicio() {
		this.finish();
	}

	private void cadastrarLivro() {
		Intent it = new Intent(this, novoActivity.class);
		
		startActivity(it);
		this.finish();
		
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Intent it = new Intent(this, infoFullActivity.class);
		
		it.putExtra("livro", lista.get(arg2));
		
		startActivity(it);
		finish();
		
	}

	@Override
	public void onClick(View v) {
		
		if(lista.size() > 0){
		busca = "%" + etBuscar.getText().toString() + "%";
		
		dialog = ProgressDialog.show(this, "Lista", "Buscando livros, por favor aguarde...", false, true);
		
		run();
		}
		
	}

	@Override
	public void run() {
		try{
			SQLiteDatabase db = openOrCreateDatabase("book", Context.MODE_PRIVATE, null);
			Cursor c;
			if(busca.equalsIgnoreCase("")){
				c = db.query("livro", new String[]{"id","titulo","subtitulo","autor","editora",
					"edicao","ano","numeroPaginas","isbn","descricao"}, null, null, null, null, "titulo");
			}else{
				c = db.query("livro", new String[]{"id","titulo","subtitulo","autor","editora",
						"edicao","ano","numeroPaginas","isbn","descricao"}, "titulo like ?", new String[]{busca}, null, null, "titulo");
			}
			
			if(c.getCount()>0){
				
				livroEntity = null;
				livroEntity = new livroEntity[c.getCount()];
				int aux = 0;
				
				while(c.moveToNext()){
					livroEntity[aux] = new livroEntity();
					
					livroEntity[aux].setId(c.getInt(0));
					livroEntity[aux].setTitulo(c.getString(1));
					livroEntity[aux].setSubtitulo(c.getString(2));
					livroEntity[aux].setAutor(c.getString(3));
					livroEntity[aux].setEditora(c.getString(4));
					livroEntity[aux].setEdicao(c.getString(5));
					livroEntity[aux].setAno(c.getInt(6));
					livroEntity[aux].setNumeroPaginas(c.getInt(7));
					livroEntity[aux].setISBN(c.getString(8));
					livroEntity[aux].setDescricao(c.getString(9));
					
					aux++;
				}
			}else{
				messagem(1);
			}
			
			c.close();
			db.close();
			
			handler.post(new Runnable() {
				
				@Override
				public void run() {
					
					lista.clear();
					Log.i("QUANTIA","Nume: " +livroEntity.length);
					if(livroEntity.length > 0){
						tvNenhum.setVisibility(View.GONE);
					}
					for(int i = 0; i< livroEntity.length; i++){
						lista.add(livroEntity[i]);
					}
					adaptador.notifyDataSetChanged();
					
				}
			});
			
			
		}catch(Throwable ex){
			Log.i("ERRO LISTA", "Erro: " + ex.toString());
		}finally{
			dialog.dismiss();
		}
	}

	private void messagem(int i) {
			
		switch(i){
		case 1:
			Toast.makeText(this, "Nenhum livro, com este titulo foi encontrado", Toast.LENGTH_LONG).show();
		break;
		}
		
	}

}
