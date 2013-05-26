package com.rtm.readbook.viwer;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.rtm.readbook.R;
import com.rtm.readbook.Entity.livroEntity;
import com.rtm.readbook.util.createDB;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class infoFullActivity extends Activity {
	
	private TextView tvTitulo;
	private TextView tvSubtitulo;
	private TextView tvAutor;
	private TextView tvEditora;
	private TextView tvEdicao;
	private TextView tvAno;
	private TextView tvNumeroPaginas;
	private TextView tvISBN;
	private TextView tvDescricao;
	
	private livroEntity livro;

	private SQLiteDatabase db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.infolivro);
	
		tvTitulo = (TextView) findViewById(R.id.tvTituloInfo);
		tvSubtitulo = (TextView) findViewById(R.id.tvSubtituloInfo);
		tvAutor = (TextView) findViewById(R.id.tvAutorInfo);
		tvEditora = (TextView) findViewById(R.id.tvEditoraInfo);
		tvEdicao = (TextView) findViewById(R.id.tvEdicaoInfo);
		tvAno = (TextView) findViewById(R.id.tvAnoInfo);
		tvNumeroPaginas = (TextView) findViewById(R.id.tvNumeroPaginasInfo);
		tvISBN = (TextView) findViewById(R.id.tvISBNInfo);
		tvDescricao = (TextView) findViewById(R.id.tvDescricaoInfo);
		
		Intent it = getIntent();
		
		if(it != null){
			
			Bundle param = it.getExtras();	
	
			if(param!=null){
			
				livro = (livroEntity) param.get("livro");
			
				if(livro.getTitulo() != null && !livro.getTitulo().equalsIgnoreCase(""))
					tvTitulo.setText(livro.getTitulo());
					
				if(livro.getSubtitulo() != null && !livro.getSubtitulo().equalsIgnoreCase(""))
					tvSubtitulo.setText(livro.getSubtitulo());
				
				if(livro.getAutor() != null && !livro.getAutor().equalsIgnoreCase(""))
					tvAutor.setText(livro.getAutor());
				
				if(livro.getEditora() != null && !livro.getEditora().equalsIgnoreCase(""))
					tvEditora.setText(livro.getEditora());
				
				if(livro.getEdicao() != null && !livro.getEdicao().equalsIgnoreCase(""))
					tvEdicao.setText(livro.getEdicao());
				
				if(livro.getAno() != null)
					tvAno.setText(livro.getAno().toString());
				
				if(livro.getNumeroPaginas() != null);
					tvNumeroPaginas.setText(livro.getNumeroPaginas().toString());
				
				if(livro.getISBN() != null && !livro.getISBN().equalsIgnoreCase(""))
					tvISBN.setText(livro.getISBN());
				
				if(livro.getDescricao() != null && !livro.getDescricao().equalsIgnoreCase(""))
					tvDescricao.setText(livro.getDescricao());
				
			}
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.info, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.comecarLer:
                comecarLer();
                return true;
            case R.id.editarInformacoes:
            	editarInformacoes();
            	return true;
            case R.id.excluir:
                excluir();
                return true;
            case R.id.inicio:
            	inicio();
            	return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

	private void inicio() {
		finish();
	}

	private void excluir() {
		try{
			
			AlertDialog alerta;
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			
			builder.setTitle("Excluir...");
			builder.setMessage("Tem certeza que desejá excluir este livro?");
			
			builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface arg0, int arg1) {
		        	db = openOrCreateDatabase("book", Context.MODE_PRIVATE, null);
		        	db.delete("livro", "id=" + livro.getId(), null);
		        	db.delete("lendoLivro", "IdLivro=" + livro.getId(), null);
		        	db.close();
		        	messagem(1);
		        	finish();
		        }

		    });
		builder.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				
			}
		});

		alerta = builder.create();
		alerta.show();
		
		}catch(SQLiteException ex){
			Toast.makeText(this, "Erro ao excluir livro", Toast.LENGTH_LONG).show();
		}
		
	}

	private void editarInformacoes() {

		Intent it = new Intent(this, novoActivity.class);
		
		it.putExtra("livro", livro);
		
		startActivity(it);
		
		finish();
	}

	private void comecarLer() {
		try{
		db = openOrCreateDatabase("book", Context.MODE_PRIVATE, null);
		Cursor c = db.query("lendoLivro", new String[]{"id"}, "IdLivro = ?", new String[]{livro.getId().toString()}, null, null, null);
		
		if(c.getCount() > 0){
			Toast.makeText(this, "Você já está lendo este livro", Toast.LENGTH_LONG).show();
		}else{
			ContentValues valores = new ContentValues();
			
			valores.put("IdLivro", livro.getId());
			valores.put("paginaAtual", "1");
			valores.put("ultimaLeitura", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			
			if(db.insert("lendoLivro", null, valores) != -1){
				Toast.makeText(this, "O livro foi colocado em sua lista de \"lendo\" ", Toast.LENGTH_LONG).show();
			}else{
				Toast.makeText(this, "Desculpe, um erro ocorreu ao tentar adicionar o livro", Toast.LENGTH_LONG).show();
			}
		}	
		
		c.close();
		db.close();
		
		}catch(SQLiteException ex){
			createDB create = new createDB();
			create.createTableLendoLivro(db);
			
			ContentValues valores = new ContentValues();
			
			valores.put("IdLivro", livro.getId());
			valores.put("paginaAtual", "1");
			valores.put("ultimaLeitura", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			
			if(db.insert("lendoLivro", null, valores) != -1){
				Toast.makeText(this, "O livro foi colocado em sua lista de \"lendo\" ", Toast.LENGTH_LONG).show();
			}else{
				Toast.makeText(this, "Desculpe, um erro ocorreu ao tentar adicionar o livro", Toast.LENGTH_LONG).show();
			}
		}
			
		}
	
	private void messagem(int i) {
		switch (i) {
		case 1:
			Toast.makeText(this, "Livro \"" + livro.getTitulo() + "\", excluido com sucesso.", Toast.LENGTH_LONG).show();
			break;

		}
	}
	
	}


