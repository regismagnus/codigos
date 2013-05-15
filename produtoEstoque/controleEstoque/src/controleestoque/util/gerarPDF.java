/*
 * Classe responsavel para criar PDF
 */

package controleestoque.util;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import controleestoque.bean.produtoMovimentoEntity;
import controleestoque.tela.movimentoEstoque;
import java.awt.Desktop;
import java.io.File;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;
import javax.annotation.processing.FilerException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author re
 */
public class gerarPDF {

File file;

    public void criar(List<produtoMovimentoEntity> movimento) throws IOException{
       //Variavel para abrir caixa Salvar Como...
        JFileChooser salvar = null ;
        Document doc = null;
        OutputStream os = null;
        

        Double subtotal;
        Double total = 0.0;

        try {

             salvar = new JFileChooser();
                    salvar.addChoosableFileFilter(new filtroSalvar());
                     salvar.setMultiSelectionEnabled(false);
                     salvar.setAcceptAllFileFilterUsed(false);
                    int resultado = salvar.showSaveDialog(null);
                    
                    if(resultado == JFileChooser.APPROVE_OPTION){

                        file= new File(salvar.getSelectedFile().toURL().getFile()+".pdf");

            //cria o documento tamanho A4, margens de 2,54cm
            doc = new Document(PageSize.A4, 30, 30, 30, 30);

            //cria a stream de saída
            os = new FileOutputStream(file);

            //associa a stream de saída ao
            PdfWriter.getInstance(doc, os);

            //abre o documento
            doc.open();

            //adiciona o texto ao PDF
            Font f = new Font(FontFamily.COURIER, 20, Font.BOLD);
            Paragraph p = new Paragraph("Relatorio de Estoque", f);
            p.setSpacingAfter(20);

//Cria a tabela com 5 colunas
            PdfPTable table = new PdfPTable(5);
//Utiliza 100% do espaco da folha
            table.setWidthPercentage(100);
            table.setSpacingAfter(10);
            table.setSpacingBefore(10);

//Cria colunas de cabeçado
table.addCell("Data");
table.addCell("Observação");
table.addCell("Quantidade");
table.addCell("Preço Unt.");
table.addCell("Total");

int idAnterior = movimento.get(0).getId_Produto();

PdfPCell header = new PdfPCell(new Paragraph(movimento.get(0).getDescricao() + ": " + movimento.get(0).getId_Produto().toString(), new Font(FontFamily.TIMES_ROMAN, 14, Font.BOLD)));
header.setColspan(5);
table.addCell(header);

//Coloca informacoes do Log na tabela
for(int i = 0; i < movimento.size(); i++){

    if(idAnterior != movimento.get(i).getId_Produto()){
        idAnterior = movimento.get(i).getId_Produto();
        header.setPhrase(new Paragraph(movimento.get(i).getDescricao() + ": " + movimento.get(i).getId_Produto().toString(), new Font(FontFamily.TIMES_ROMAN, 14, Font.BOLD)));
        table.addCell(header);
    }
    table.addCell(movimento.get(i).getDia().toString());
    table.addCell(movimento.get(i).getObservacao());
    table.addCell(movimento.get(i).getQuantidade().toString());
    table.addCell(new DecimalFormat("0.00").format(movimento.get(i).getPreco_unidade()));
    subtotal = movimento.get(i).getPreco_unidade()*movimento.get(i).getQuantidade();
    total += subtotal;
    table.addCell(new DecimalFormat("0.00").format(subtotal));

}

header.setPhrase(new Paragraph("Total: " + new DecimalFormat().getCurrencyInstance(new Locale("pt","BR")).format(total), new Font(FontFamily.TIMES_ROMAN, 18)));
header.setHorizontalAlignment(Element.ALIGN_RIGHT);
table.addCell(header);
//Adiciona os textos
            doc.add(p);
            doc.add(table);
                    }
        } catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Erro ao criar o PDF");
        }

        finally {
            if (doc != null) {
                //fechamento do documento
                doc.close();
            }
            if (os != null) {
               //fechamento da stream de saída
               os.close();
            }

            Desktop.getDesktop().open(file);

        }
    }
}
