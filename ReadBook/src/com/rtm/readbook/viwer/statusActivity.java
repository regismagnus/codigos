package com.rtm.readbook.viwer;

import java.text.SimpleDateFormat;

import com.rtm.readbook.R;
import com.rtm.readbook.Entity.lendoLivroEntity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewStyle;
import com.jjoe64.graphview.LineGraphView;


public class statusActivity extends Activity {

	private TextView tvPaginaAtual;
	private TextView tvInicioLeitura;
	private TextView tvUltimaLeitura;
	
	private lendoLivroEntity lendoLivroEntity;
	private lendoLivroEntity[] all;
	
	private GraphViewSeries leituraSeries;
	private GraphView graphView;
	
	protected void onCreate(Bundle incicle){
		super.onCreate(incicle);
		setContentView(R.layout.status);

		tvPaginaAtual = (TextView) findViewById(R.id.tvPagStatus);
		tvInicioLeitura = (TextView) findViewById(R.id.tvInicioLeituraStatus);
		tvUltimaLeitura = (TextView) findViewById(R.id.tvUltimaLeituraStatus);
		
		graphView = new LineGraphView(
			      this // context
			      , "Historico de Leitura" // heading
			);
		
		Intent it = getIntent();
		
		if(it != null){
			
			Bundle param = it.getExtras();
			
			if(param!=null){
				
				lendoLivroEntity = (lendoLivroEntity) param.get("lendoLivro");
			
				criarDados();
			}	
		}
		
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.status, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.voltar:
            	voltar();
            	return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

	private void voltar() {

		this.finish();
		
	}

	private void criarDados() {
		try{
			SQLiteDatabase db  = openOrCreateDatabase("book", Context.MODE_PRIVATE, null);
			Cursor c;
			GraphViewData[] graph;
			String[] pag, data;
			double dividir, total = 0.0;
			
			c = db.query("lendoLivro", new String[]{"id" ,"paginaAtual","ultimaLeitura"}, "IdLivro= ? AND id!=?", new String[]{lendoLivroEntity.getBookEntity().getId().toString(), lendoLivroEntity.getId().toString()}, null, null, "ultimaLeitura");
				
			data = new String[2];
			
			if(c.getCount() > 0){
				
				all = new lendoLivroEntity[c.getCount()];
				Integer aux = 0;
				
				if(lendoLivroEntity.getBookEntity().getNumeroPaginas() >= 12){
					pag = new String[7];
				
					dividir = lendoLivroEntity.getBookEntity().getNumeroPaginas()/5.935; //talvez 5.936
					
					pag[6] = "1 p.";
					total += dividir;
					pag[5] = (int) total + " p.";
					total += dividir;
					pag[4] = (int) total + " p.";
					total += dividir;
					pag[3] = (int) total + " p.";
					total += dividir;
					pag[2] = (int) total + " p.";
					total += dividir;
					pag[1] = (int) total + " p.";
					pag[0] = lendoLivroEntity.getBookEntity().getNumeroPaginas().toString() + " p.";
					
				
				}else{
					pag = new String[2];
					
					pag[0] = lendoLivroEntity.getBookEntity().getNumeroPaginas().toString() + " p.";
					pag[1] = "1 p.";
				}
				
				graph = new GraphViewData[c.getCount() + 1];
				
				while(c.moveToNext()){
					
					all[aux] = new lendoLivroEntity();
					
					all[aux].setId(c.getInt(0));
					all[aux].setPaginaAtual(c.getInt(1));
					all[aux].setUltimaLeitura(new SimpleDateFormat("yyyy-MM-dd").parse(c.getString(2)));
					
					graph[aux] = new GraphViewData( aux + 1, all[aux].getPaginaAtual());
					
					aux++;
				}
			
				tvInicioLeitura.setText(new SimpleDateFormat("dd/MM/yyyy").format(all[0].getUltimaLeitura()));
				
				data[0] = new SimpleDateFormat("dd/MM/yyyy").format(all[0].getUltimaLeitura());
				graph[c.getCount()] = new GraphViewData(aux + 1, lendoLivroEntity.getPaginaAtual());
				
			}else{
				
				pag = new String[2];
				
				pag[0] = lendoLivroEntity.getBookEntity().getNumeroPaginas().toString() + " p.";
				pag[1] = lendoLivroEntity.getPaginaAtual().toString() + " p.";
				
				data[0] = new SimpleDateFormat("dd/MM/yyyy").format(lendoLivroEntity.getUltimaLeitura());
				
				graph = new GraphViewData[1];
				
				graph[0] = new GraphViewData(1, lendoLivroEntity.getPaginaAtual());
				
				
				tvInicioLeitura.setText(new SimpleDateFormat("dd/MM/yyyy").format(lendoLivroEntity.getUltimaLeitura()));
						
			}
			
			data[1] = new SimpleDateFormat("dd/MM/yyyy").format(lendoLivroEntity.getUltimaLeitura());
			
			tvPaginaAtual.setText(lendoLivroEntity.getPaginaAtual().toString());
			tvUltimaLeitura.setText(new SimpleDateFormat("dd/MM/yyyy").format(lendoLivroEntity.getUltimaLeitura()));
			
		
			leituraSeries = new GraphViewSeries(graph);
			
			graphView.setMaximo(lendoLivroEntity.getBookEntity().getNumeroPaginas());
			graphView.addSeries(leituraSeries); // data
			graphView.setHorizontalLabels(data);  
			graphView.setVerticalLabels(pag); 
			graphView.setGraphViewStyle(new GraphViewStyle(Color.BLACK, Color.BLACK, Color.CYAN));
			
			
			LinearLayout layout = (LinearLayout) findViewById(R.id.linear);
			layout.addView(graphView);
		
			db.close();
			c.close();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
}
