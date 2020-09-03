
import java.awt.Color;
import java.io.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

/**
 *
 * @author Ramon Cruz Perez
 */
public class Ventana extends javax.swing.JFrame {


    private javax.swing.JFileChooser seleccionFile  = new javax.swing.JFileChooser();
    private Timer tiempo = new Timer();
    /**
     * Creates new form Ventana
     */

    public Ventana() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Editor de texto");
        autoGuardado();
    }

    /*
    *Guarda automaticamente el archivo
    */
    private void autoGuardado(){
        TimerTask auto = new TimerTask(){
            @Override
            public void run(){
                guardar();
            }
        };
        tiempo.schedule(auto,0,350000); // se guarda cada 5 min.
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jFileChooser2 = new javax.swing.JFileChooser();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jRadioButtonMenuItem3 = new javax.swing.JRadioButtonMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuBar3 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        area1 = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        archivo = new javax.swing.JMenu();
        abrir = new javax.swing.JRadioButtonMenuItem();
        controlS = new javax.swing.JRadioButtonMenuItem();
        guardar = new javax.swing.JRadioButtonMenuItem();
        nuevo = new javax.swing.JRadioButtonMenuItem();
        buscarPalabra = new javax.swing.JMenu();
        busca = new javax.swing.JRadioButtonMenuItem();
        eliminarBusca = new javax.swing.JRadioButtonMenuItem();
        Diccionario = new javax.swing.JMenu();
        agregarDiccionario = new javax.swing.JRadioButtonMenuItem();
        Corregir = new javax.swing.JMenu();

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        jRadioButtonMenuItem3.setSelected(true);
        jRadioButtonMenuItem3.setText("jRadioButtonMenuItem3");

        jMenu2.setText("jMenu2");

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        jMenu5.setText("File");
        jMenuBar3.add(jMenu5);

        jMenu6.setText("Edit");
        jMenuBar3.add(jMenu6);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        area1.setColumns(20);
        area1.setRows(5);
        jScrollPane1.setViewportView(area1);

        archivo.setText("Archivo");

        abrir.setSelected(true);
        abrir.setText("Abrir");
        abrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirActionPerformed(evt);
            }
        });
        archivo.add(abrir);

        controlS.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        controlS.setSelected(true);
        controlS.setText("Guardar");
        controlS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                controlSActionPerformed(evt);
            }
        });
        archivo.add(controlS);

        guardar.setSelected(true);
        guardar.setText("Guardar como...");
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });
        archivo.add(guardar);

        nuevo.setSelected(true);
        nuevo.setText("Nuevo");
        nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoActionPerformed(evt);
            }
        });
        archivo.add(nuevo);

        jMenuBar1.add(archivo);

        buscarPalabra.setText("Buscar");

        busca.setSelected(true);
        busca.setText("Buscar Palabra");
        busca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscaActionPerformed(evt);
            }
        });
        buscarPalabra.add(busca);

        eliminarBusca.setSelected(true);
        eliminarBusca.setText("Eliminar busqueda");
        eliminarBusca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarBuscaActionPerformed(evt);
            }
        });
        buscarPalabra.add(eliminarBusca);

        jMenuBar1.add(buscarPalabra);

        Diccionario.setText("Diccionario");

        agregarDiccionario.setSelected(true);
        agregarDiccionario.setText("Agregar al diccionario");
        agregarDiccionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarDiccionarioActionPerformed(evt);
            }
        });
        Diccionario.add(agregarDiccionario);

        jMenuBar1.add(Diccionario);

        Corregir.setText("Corregir ");
        Corregir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CorregirMouseClicked(evt);
            }
        });
        jMenuBar1.add(Corregir);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 860, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    *@returg s String que el todo el archivo
    *@param f file
    */
    private String obtenerArchivo(File f){
       // File f = new  File(c);
        String c ="";
         try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String cadena;
            while((cadena = br.readLine())!=null) {
                c +=cadena+"\n";
            }
            br.close();
            return c;
          }catch (Exception e) {
         System.out.println(e);
        }
         return c;
    }

    /**
     * Abre una ventana para buscar el archivo y mete al area de texto
     * @param evt
     */
    private void abrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirActionPerformed
        seleccionFile.showDialog(abrir, "Abrir");
        File f = seleccionFile.getSelectedFile();
        String c = "";
           //JOptionPane.showMessageDialog(null,f);
        if(seleccionFile.accept(f))
          c = obtenerArchivo(f);
        area1.setText(c);
    }//GEN-LAST:event_abrirActionPerformed

    /**
     * Abre una ventana para buscar donde guradar el archivo
     * @param evt
     */
    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
       seleccionFile.showDialog(guardar, "Guadar");
       guardar();
    }//GEN-LAST:event_guardarActionPerformed

    /**
     * Nos muestra un menu en el que podemos usar el diccionario
     * @param evt
     */
    private void agregarDiccionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarDiccionarioActionPerformed
        DiccionarioApp d = new DiccionarioApp();
        d.setVisible(true);
        d.setLocationRelativeTo(null);
    }//GEN-LAST:event_agregarDiccionarioActionPerformed

    /**
     * Obtiene el texto del area de texto para guardar cada renglon del texto en un arreglo
     * @return arreglo de arreglos de String[]
     */
    private Object[] obtenerRenglones(){
        File f = new File("temporal.txt");
        try{
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw;
            bw = new BufferedWriter(fw);
            bw.write(area1.getText());
            bw.close();
        }catch (Exception e) {
            System.out.println(e);
        }
        Object[] arreglo = new Object[50];
        int cont =0;
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String cadena;
            while((cadena = br.readLine())!=null) {
              cont++;
            }
            //br.close();
            fr = new FileReader(f);
            arreglo = new Object[cont+1];
            br = new BufferedReader(fr);
            cont = 0;
            String c;
            while((c = br.readLine())!=null) {
              String[] palabrasren = c.split("\\s");
              arreglo[cont] = palabrasren;
              cont++;
            }
            br.close();
        }catch (Exception e) {
            System.out.println(e);
        }
        if(f.delete())
            return arreglo;
        System.out.println("No se elimino temporal");
        return arreglo;
    }

    /**
     * Acomada las palabras del arreglo(palabras) en un file
     * @param palabras
     * @return f File con las palabras ya acomodadas
     */
    private File acomodaPalabras(Palabra[][] palabras){
        File f = new File("temporal.txt");
        try{
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw;
            bw = new BufferedWriter(fw);
            for(int i =0 ; i < palabras.length;i++ ) {
                for(int j=0 ; palabras[i][j] != null; j++){//revisar si sirve con null o " ".
                    bw.write(palabras[i][j].getCadena()+" ");
                }
                bw.write("\n");
            }
            bw.close();
            return f;
        }catch (Exception e) {
            System.out.println(e);
        }
        return f;
    }

    /**
     * Se corrige todo el area de texto.
     * @param evt
     */
    private void CorregirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CorregirMouseClicked
        JOptionPane.showMessageDialog(null,"Estamos corrigiendo espere porfavor.");
        Diccionario dic = new Diccionario();
        Object[] renglones = obtenerRenglones();  //obetengo cada renglon del area de texto
        Palabra[][] palabras = new Palabra[renglones.length][1000];// creo un arreglo para saparar palabra por palabra cada renglon
        for(int i =0; i < renglones.length; i++){
            String[]  tmp = (String[])renglones[i];
            for(int j = 0; (tmp!=null)? j<tmp.length: false; j++){
                palabras[i][j] = new Palabra(tmp[j]);
            }
        }

        //en este for verifico si las palabras se tienen que corregir
        for(int i=0; i < palabras.length; i++){
            Palabra n;
            for(int j=0; palabras[i][j]!= null;j++){
                if (!dic.contiene(palabras[i][j])) {
                    // buscamos la sugerencia del arbol y lo eliminamos del heap para remplazar esa palabra
                    MaxHeap<Palabra> h = dic.opcionesSuguerencias(palabras[i][j]);
                    n = (!h.esVacio())? h.elimina(): palabras[i][j];
                    palabras[i][j] = n;
                }else{
                    n = dic.busca(palabras[i][j]);
                     palabras[i][j] = n ;
                }
            }
        }
        File f = acomodaPalabras(palabras);
        String c = obtenerArchivo(f);
        if(f.delete()){
            area1.setText(c);
            JOptionPane.showMessageDialog(null,"Texto corregido");
        }

    }//GEN-LAST:event_CorregirMouseClicked

    /**
     * guarda en el archivo selecionado por el atributo "seleccionFile".
     */
    private void guardar(){
        File f = seleccionFile.getSelectedFile(); //+"txt"
        try{
            File nuevo = f;
            FileWriter fw = new FileWriter(nuevo);
            BufferedWriter bw;
            bw = new BufferedWriter(fw);
            bw.write(area1.getText());
            bw.close();
          }catch (Exception e) {
            System.out.println(e);
            }
    }

    /**
     * Guarda en el mismo archivo
     * @param evt
     */
    private void controlSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_controlSActionPerformed
       guardar();
    }//GEN-LAST:event_controlSActionPerformed

    private void nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoActionPerformed
         Ventana n = new Ventana();
         n.setVisible(true);
         n.setLocation(10,10);

    }//GEN-LAST:event_nuevoActionPerformed

    /**
     * Busca la pabra en el area de texto
     * @param evt
     */
    private void buscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscaActionPerformed
        String respuesta = JOptionPane.showInputDialog("Escribe la palabra:");
        if (respuesta.length() >= 1) {
            DefaultHighlighter.DefaultHighlightPainter highlightPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.GREEN);
            Highlighter h = area1.getHighlighter();
            h.removeAllHighlights();
            String text = area1.getText();
            String caracteres = respuesta;
            Pattern p = Pattern.compile("(?i)" + caracteres);
            Matcher m = p.matcher(text);
            while (m.find()) {
                try {
                    h.addHighlight(m.start(), m.end(), highlightPainter);
                } catch (BadLocationException ex) {
                    Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
             //h.removeAllHighlights();
        } else {
            JOptionPane.showMessageDialog(area1, "la palabra a buscar no puede ser vacia");
        }
    }//GEN-LAST:event_buscaActionPerformed

    /**
     * Elimina las lo que se subrayo en area de texto
     * @param evt
     */
    private void eliminarBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarBuscaActionPerformed
        DefaultHighlighter.DefaultHighlightPainter highlightPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.GREEN);
        Highlighter h = area1.getHighlighter();
        h.removeAllHighlights();
    }//GEN-LAST:event_eliminarBuscaActionPerformed

    /**
     * @param args the command line arguments
     */

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true);
            }
        })  ;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Corregir;
    private javax.swing.JMenu Diccionario;
    private javax.swing.JRadioButtonMenuItem abrir;
    private javax.swing.JRadioButtonMenuItem agregarDiccionario;
    private javax.swing.JMenu archivo;
    private javax.swing.JTextArea area1;
    private javax.swing.JRadioButtonMenuItem busca;
    private javax.swing.JMenu buscarPalabra;
    private javax.swing.JRadioButtonMenuItem controlS;
    private javax.swing.JRadioButtonMenuItem eliminarBusca;
    private javax.swing.JRadioButtonMenuItem guardar;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JFileChooser jFileChooser2;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButtonMenuItem nuevo;
    // End of variables declaration//GEN-END:variables
}
