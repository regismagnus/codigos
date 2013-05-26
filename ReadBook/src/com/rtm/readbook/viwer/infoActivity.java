package com.rtm.readbook.viwer;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.rtm.readbook.R;
import com.rtm.readbook.Entity.lendoLivroEntity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class infoActivity extends Activity {
	
	private TextView tvTitulo;
	private TextView tvSubtitulo;
	private TextView tvAutor;
	private TextView tvPaginaAtual;
	private TextView tvPag;
	
	private lendoLivroEntity lendoLivroEntity;

	protected void onCreate(Bundle incicle){
		super.onCreate(incicle);
		setContentView(R.layout.infoleratual);
		
		tvTitulo = (TextView) findViewById(R.id.tvLabelTitulo);
		tvSubtitulo = (TextView) findViewById(R.id.tvLabelSubtitulo);
		tvAutor = (TextView) findViewById(R.id.tvLabelAutor);
		tvPaginaAtual = (TextView) findViewById(R.id.tvPaginaAtual);
		tvPag = (TextView) findViewById(R.id.tvPag);
		
		Intent it = getIntent();
		
		if(it != null){
			
			Bundle param = it.getExtras();	
	
			if(param!=null){
			
				lendoLivroEntity = (lendoLivroEntity) param.get("lendoLivro");
			
				if(lendoLivroEntity.getBookEntity().getTitulo() != null && !lendoLivroEntity.getBookEntity().getTitulo().equalsIgnoreCase(""))
					tvTitulo.setText(lendoLivroEntity.getBookEntity().getTitulo());
					
				if(lendoLivroEntity.getBookEntity().getSubtitulo() != null && !lendoLivroEntity.getBookEntity().getSubtitulo().equalsIgnoreCase(""))
					tvSubtitulo.setText(lendoLivroEntity.getBookEntity().getSubtitulo());
				
				if(lendoLivroEntity.getBookEntity().getAutor() != null && !lendoLivroEntity.getBookEntity().getAutor().equalsIgnoreCase(""))
					tvAutor.setText(lendoLivroEntity.getBookEntity().getAutor());

				if(lendoLivroEntity.getBookEntity().getNumeroPaginas() != null);
					tvPag.setText(lendoLivroEntity.getBookEntity().getNumeroPaginas().toString());
				
				if(lendoLivroEntity.getPaginaAtual() != null)
					tvPaginaAtual.setText(lendoLivroEntity.getPaginaAtual().toString());
				
			}
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ler, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.editarPagina:
                editarPagina();
                return true;
            case R.id.andamentoLeitura:
            	andamentoLeitura();
            	return true;
            case R.id.pararLeitura:
                pararLeitura();
                return true;
            case R.id.inicio:
            	inicio();
            	return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

	private void andamentoLeitura() {

		Intent it = new Intent(this, statusActivity.class);
		
		it.putExtra("lendoLivro", lendoLivroEntity);
		
		startActivity(it);
		
	}

	private void inicio() {
		
		finish();
		
	}

	private void pararLeitura() {
		try{
		SQLiteDatabase db = openOrCreateDatabase("book", Context.MODE_PRIVATE, null);
		
		db.delete("lendoLivro", "IdLivro="+lendoLivroEntity.getBookEntity().getId(), null);
		
		db.close();
		
		AlertDialog alerta;
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		builder.setTitle("Leitura...");
		builder.setMessage("O livro " + lendoLivroEntity.getBookEntity().getTitulo() + " foi retirado da lista \"Ler\"");
		
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface arg0, int arg1) {
	        	finish();
	        }

	    });
		
		alerta = builder.create();
		alerta.show();
		
		}catch (SQLiteException e) {
			Toast.makeText(this, "Desculpe, ocorreu um erro ao tentar tirar o livro da lista \"Ler\"", Toast.LENGTH_LONG).show();
		}
	}

	private void editarPagina() {
		
			AlertDialog alerta;
			final EditText pagina = new EditText(this);
			pagina.setId(0);
			pagina.setInputType(InputType.TYPE_CLASS_NUMBER);
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			
			builder.setTitle("Leitura...");
			builder.setMessage("Página atual:");
			builder.setView(pagina);
			
			builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface arg0, int arg1) {
				
		        	try{
		        	Integer valor = Integer.parseInt(pagina.getText().toString());
		        	
		        	if(valor > 0 && valor <= lendoLivroEntity.getBookEntity().getNumeroPaginas()){
		        		SQLiteDatabase db = openOrCreateDatabase("book", Context.MODE_PRIVATE, null);
						ContentValues valores = new ContentValues();
						
						valores.put("IdLivro", lendoLivroEntity.getBookEntity().getId());
		        		valores.put("paginaAtual", valor);
		        		valores.put("ultimaLeitura", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		    			
		    			lendoLivroEntity.setId((int) db.insert("lendoLivro", null, valores));
		    			
		    			tvPaginaAtual.setText(valor.toString());
		    			lendoLivroEntity.setPaginaAtual(valor);
		    			lendoLivroEntity.setUltimaLeitura(new Date());
		    			
		    			db.close();
		    			
		        	}else{
		        		mensagem(1);
		        	}
		        	
		        	
		        	}catch (SQLiteException e) {
		    			mensagem(2);
		    		}catch (NumberFormatException e) {
		    			mensagem(3);
		    		}
		    			
		        }

		    });
			
			alerta = builder.create();
			alerta.show();
			
	}
	
	private void mensagem(int i) {
		
		switch (i) {
		case 1:
			Toast.makeText(this, "O número não pode ser menor do que 1 e maior do que " + lendoLivroEntity.getBookEntity().getNumeroPaginas() + ".", Toast.LENGTH_LONG).show();
			break;
		case 2:
			Toast.makeText(this, "Desculpe, ocorreu um erro ao tentar tirar o livro da lista \"Ler\"", Toast.LENGTH_LONG).show();
			break;
		case 3:
			Toast.makeText(this, "Por favor digite o número da página corretamente.", Toast.LENGTH_LONG).show();
			break;
		}
		
	}
	

}
