/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * listar.java
 *
 * Created on 18/03/2013, 01:40:45
 */

package controleestoque.tela;

import controleestoque.bean.produtoEntity;
import controleestoque.crud.produtoDAO;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author re
 */
public class listar extends javax.swing.JDialog {

    private produtoDAO produtoDAO;
    private produtoEntity produtoEntity;
    private List<produtoEntity> allProduto;
    private boolean modoSelecao = false;

    private produtoEntity produtoSelecionado;

    private DefaultTableModel model;

    private boolean stop = true;
    private Thread thread;
    private cadastro c;
    /** Creates new form listar */
    public listar(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        produtoDAO = new produtoDAO();
        produtoEntity = new produtoEntity();

        initComponents();

        JBSelecionar.setVisible(false);

        model = new DefaultTableModel();
        jTable1.setModel(model);
        model.addColumn("Código");
        model.addColumn("Descrição do Produto");
        model.addColumn("Marca");
        model.addColumn("Modelo");
        model.addColumn("Unidade");
        model.addColumn("Estoque Un.");
        model.addColumn("Ultimo Custo (R$)");
        model.addColumn("Valor Venda (R$)");

        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTable1.getColumnModel().getColumn(0).setMinWidth(8);
        jTable1.getColumnModel().getColumn(1).setMinWidth(200);
        jTable1.getColumnModel().getColumn(2).setMinWidth(90);
        jTable1.getColumnModel().getColumn(3).setMinWidth(100);
        jTable1.getColumnModel().getColumn(4).setMinWidth(100);
        jTable1.getColumnModel().getColumn(5).setMinWidth(70);
        jTable1.getColumnModel().getColumn(6).setMinWidth(100);
        jTable1.getColumnModel().getColumn(6).setMinWidth(100);
        jTable1.setDefaultEditor(Object.class, null);

       listar(0);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        JBAlterar = new javax.swing.JButton();
        JBExcluir = new javax.swing.JButton();
        JLBuscar = new javax.swing.JLabel();
        JTFBuscar = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        JBSelecionar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Produtos");
        setResizable(false);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Código", "Descrição do Produto", "Marca", "Modelo", "Unidade", "Ultimo Custo (R$)", "Valor Venda (R$)"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        JBAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/alterar.png"))); // NOI18N
        JBAlterar.setLabel("Alterar");
        JBAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBAlterarActionPerformed(evt);
            }
        });

        JBExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/cancelar.png"))); // NOI18N
        JBExcluir.setLabel("Excluir");
        JBExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBExcluirActionPerformed(evt);
            }
        });

        JLBuscar.setText("Buscar:");

        JTFBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JTFBuscarKeyReleased(evt);
            }
        });

        JBSelecionar.setText("Selecionar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(JBAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(JBExcluir)
                .addGap(41, 41, 41)
                .addComponent(JLBuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JTFBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JBSelecionar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 753, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 753, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(JBAlterar, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(JBExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(JLBuscar)
                        .addComponent(JTFBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JBSelecionar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JBAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBAlterarActionPerformed

        alterar();

    }//GEN-LAST:event_JBAlterarActionPerformed

    private void JBExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBExcluirActionPerformed
       try{
           if(JOptionPane.showConfirmDialog(null, "Se deletar este produto, todas as informações de entrada e saida do estoque referentes a ele seram deletadas. Deseja continuar?")==0){
                produtoDAO.remover(allProduto.get(jTable1.getSelectedRow()).getId());
                listar(0);
                JTFBuscar.setText("");
           }
       }catch(ArrayIndexOutOfBoundsException ex){
           JOptionPane.showMessageDialog(null, "Selecione um produto", "Erro", JOptionPane.ERROR_MESSAGE);
       }
    }//GEN-LAST:event_JBExcluirActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        if(evt.getClickCount()>=2){
            if(!modoSelecao){
            alterar();
            }else{
                produtoSelecionado =  allProduto.get(jTable1.getSelectedRow());
                this.dispose();
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void JTFBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTFBuscarKeyReleased

       listar(1);

    }//GEN-LAST:event_JTFBuscarKeyReleased

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                listar dialog = new listar(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBAlterar;
    private javax.swing.JButton JBExcluir;
    private javax.swing.JButton JBSelecionar;
    private javax.swing.JLabel JLBuscar;
    private javax.swing.JTextField JTFBuscar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

 public void listar(int tipo) {

     if(tipo==0){
         allProduto = produtoDAO.listar();
     }else{
         allProduto = produtoDAO.listarDescricao(JTFBuscar.getText());
     }

        model.setNumRows(allProduto.size());

        for(int i = 0; i < allProduto.size(); i++){
            jTable1.setValueAt(allProduto.get(i).getId(), i, 0);
            jTable1.setValueAt(allProduto.get(i).getDescricaoProduto(), i, 1);
            jTable1.setValueAt(allProduto.get(i).getMarca(), i, 2);
            jTable1.setValueAt(allProduto.get(i).getModelo(), i, 3);
            jTable1.setValueAt(allProduto.get(i).getUnidade(), i, 4);
            jTable1.setValueAt(allProduto.get(i).getUnidadeEstoque(), i, 5);
            jTable1.setValueAt(new DecimalFormat().getCurrencyInstance(new Locale("pt","BR")).format(allProduto.get(i).getUltimoCusto()), i, 6);
            jTable1.setValueAt(new DecimalFormat().getCurrencyInstance(new Locale("pt","BR")).format(allProduto.get(i).getValorVenda()), i, 7);

        }

    }


    private void alterar() {
        try{
        c = new cadastro((JFrame)getParent(), true, allProduto.get(jTable1.getSelectedRow()) );
       stop = false;
        start();
        c.show();

        }catch(ArrayIndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(null, "Selecione um produto", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void selecionar() {

        modoSelecao = true;
        produtoSelecionado = new produtoEntity();

        this.setTitle("Selecionar");
        
        JBSelecionar.setVisible(true);
        JBSelecionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getSelecionar();
            }


        });

        JBAlterar.setVisible(false);
        JBExcluir.setVisible(false);
        this.add(JBSelecionar);
        
    }
    private void getSelecionar() {
                try{
                    produtoSelecionado = allProduto.get(jTable1.getSelectedRow());
                    this.dispose();

                }catch(ArrayIndexOutOfBoundsException ex){
                    JOptionPane.showMessageDialog(null, "Selecione um produto", "Erro", JOptionPane.ERROR_MESSAGE);
                 }
    }

    public produtoEntity getProdutoSelecionado(){
        return produtoSelecionado;
    }

    public void start(){

		thread=new Thread(new Runnable(){
			public void run(){
				try{
				while(!stop){
                                    if(!c.getAlterar()){
                                        listar(0);
                                        JTFBuscar.setText("");
                                        stop = true;
                                        c = null;
                                        thread.stop();
                                    }
                                    thread.sleep(100);
				}
			}catch(Exception e){
                                e.printStackTrace();
			}
		}});
		thread.start();
	}


}
