package Comp;

import java.awt.Color;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Ventana extends JFrame {
	static Hashtable<String, Simbolo> tabla;
	static TextArea t1;
	static TextArea t2;
	static comp analizador;
	public Ventana(String title) {	
		tabla= new Hashtable();
		InputStream stream = new ByteArrayInputStream( "".getBytes(StandardCharsets.UTF_8));
		analizador = new comp(stream) ;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle(title);
		this.setLayout(null);
		this.setResizable(false);
		t1 = new TextArea();
		this.setVisible(true);
		this.setLocation(230, 80);
		this.setSize(800, 600);										
		t1.setLocation(0, 0);	
		t1.setSize(550, 350);	
		t1.setBackground(Color.WHITE);
		JButton btn1= new JButton("Correr");
		Correr run = new Correr();
		btn1.addActionListener(run);
		btn1.setSize(120, 60);
		btn1.setLocation(600, 20);
		JButton btn2= new JButton("Abrir  archivo");
		AdjuntarArchivo adj= new AdjuntarArchivo();
		btn2.addActionListener(adj);
		btn2.setSize(120, 60);
		btn2.setLocation(600, 100);
		JButton btn3= new JButton("Borrar");
		Borrar borrar = new Borrar();
		btn3.addActionListener(borrar);
		btn3.setSize(120, 60);
		btn3.setLocation(600, 180);
		Font fuente=new Font("Dialog", Font.ROMAN_BASELINE, 18);
		t1.setFont(fuente);
		this.add(t1);
		this.add(btn1);		
		this.add(btn2);
		this.add(btn3);
		t2 = new TextArea();
		Font fuente2=new Font("Dialog", Font.ITALIC, 16);
		t2.setFont(fuente2);		
		t2.setSize(780, 205);	
		t2.setLocation(5, 355);			
		t2.setEditable(false);
		t2.setBackground(Color.WHITE);		
		t2.setForeground(Color.RED);
		this.add(t2);
		this.repaint();
		
	}
	class Borrar implements ActionListener{	
		public void actionPerformed(ActionEvent arg0) {			
			t1.setText(null);
			t2.setText(null);
			tabla.clear();
			
		}
		
	}
	class AdjuntarArchivo implements ActionListener {	
		public void actionPerformed(ActionEvent e) {
			 JFileChooser chooser = new JFileChooser();
			 chooser.setDialogTitle("Abrir archivo");
			
			 File rut=new File("C:\\Users\\Abel RM\\Desktop\\tec\\GitHub\\Compilador\\Compilador\\src\\Comp");
			 chooser.setCurrentDirectory(rut);
       	  chooser.setFileSelectionMode( JFileChooser.FILES_ONLY );        	  
             FileNameExtensionFilter filtro = new FileNameExtensionFilter( ".java","java" ); 
             chooser.setFileFilter( filtro );
             int estado=chooser.showOpenDialog(null);
            String cadena=" ";            
             if(estado==JFileChooser.APPROVE_OPTION)
             {	            	  	            	            	  
           	  try
                 { 	            		 
                     File archivoelegido=chooser.getSelectedFile();
                     String ruta=archivoelegido.getPath();          
                     FileReader f = new FileReader(ruta);
                     BufferedReader b = new BufferedReader(f);                                                     		 
				     String g="";         	 
                     while((cadena = b.readLine())!=null) {
                    	 g+=cadena+"\n";
                     }
                     t1.setText(g);
                     b.close();
                 }                 
                 catch(Exception es)
                 {}           	            	  
           	  
             }
		     		
			
		}

	}
	class Correr implements ActionListener {	
		public void actionPerformed(ActionEvent arg0) {	
			t2.setText(null);
			tabla.clear();
			try {
				iniciar();
			} catch (Exception e) {
				t2.setText(e.getMessage());
			}
			Enumeration<Simbolo> enumeration = tabla.elements();
			Simbolo s= new Simbolo(null,null,null);	
			t2.setText(t2.getText()+"\n\n\nTabla de Simbolos:\n<symbol name,  type,  attribute>\n\n");
			while (enumeration.hasMoreElements()) {
				s=enumeration.nextElement();
				t2.setText(t2.getText()+"<"+s.getNombre()+"\t"+s.getTipo()+"\t"+s.getAtributo()+">\n");
			}
						
			
		}		
	}
	static void iniciar(){
		try
		{		
			String g=t1.getText();
			InputStream stream = new ByteArrayInputStream( g.getBytes(StandardCharsets.UTF_8));
			analizador.ReInit(stream);     		
			analizador.Programa();
			t2.setText("\tAnalizador ha terminado.");
		}
		catch(ParseException e)
		{
			t2.setText(e.getMessage()+"\n"+"\tAnalizador ha terminado.");			
		}
	}
}

