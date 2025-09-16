package layouts;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Calculadora {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MarcoPrincipal miMarco = new MarcoPrincipal();
		
		miMarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		miMarco.setVisible(true);

	}
}

class MarcoPrincipal extends JFrame {
	
	public MarcoPrincipal(){
		
		setTitle("Calculadora");
		
		setBounds(400,350,200,300);
		
		Panel1 lamina = new Panel1();
				
		//Marco - JFrame tiene por defecto un BorderLayout!
		//Lamina - JPanel tiene por defecto un FlowLayout!
		
		add(lamina, BorderLayout.NORTH);
			
	}
}

class Panel1 extends JPanel { //panel
	
		private boolean comienzo = true;
	
		private JButton pantalla;
		
		private JPanel panelInferior;
				
		private int contador;
		
		private double ultimoValor, resultado;
	
		public Panel1(){
						
			setLayout(new BorderLayout());
			
			pantalla = new JButton("0");
			
			pantalla.setEnabled(false);
			
			add(pantalla, BorderLayout.NORTH);
			
			panelInferior = new JPanel();
			
			panelInferior.setLayout(new GridLayout(4, 4));
			
			GenerarNumero generar = new GenerarNumero();
			
			OperacionNumero operacion = new OperacionNumero();
			
			ponerBoton("7", generar);
			ponerBoton("8", generar);
			ponerBoton("9", generar);
			ponerBotonOperacion("/", operacion);
			ponerBoton("4", generar);
			ponerBoton("5", generar);
			ponerBoton("6", generar);
			ponerBotonOperacion("x", operacion);
			ponerBoton("1", generar);
			ponerBoton("2", generar);
			ponerBoton("3", generar);
			ponerBotonOperacion("-", operacion);
			ponerBoton("", generar);
			ponerBoton("0", generar);
			ponerBotonOperacion("=", operacion);
			ponerBotonOperacion("+", operacion);
			
			add(panelInferior, BorderLayout.CENTER);
			
		}

		private void ponerBoton(String textoBoton, GenerarNumero oyente) {
			
			JButton boton = new JButton(textoBoton);
			boton.addActionListener(oyente);
			panelInferior.add(boton);

		}

		private void ponerBotonOperacion(String textoBoton, OperacionNumero oyente) {
			
			JButton boton = new JButton(textoBoton);
			boton.addActionListener(oyente);
			panelInferior.add(boton);

		}
		
		private class GenerarNumero implements ActionListener {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String entrada = e.getActionCommand(); //me devuelve un String del objeto seleccionado (boton)
				
				if (comienzo) {
					
					pantalla.setText(entrada);
					
					comienzo = false;
					
				} else {
				
				pantalla.setText(pantalla.getText() + entrada);
				
				}
				
				ultimoValor = Double.parseDouble(pantalla.getText());
				
			}
			
		}
		
		private class OperacionNumero implements ActionListener {

			private String ultimaOperacion = "";
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			
				String operacionBuscada = e.getActionCommand();
				
				
				if(operacionBuscada.equals("+")) {
				
					resultado += ultimoValor;
					
					pantalla.setText("" + resultado);
					
					ultimaOperacion = "+";
				
				} else if(operacionBuscada.equals("x")) {
					
					double valor = Double.parseDouble(pantalla.getText());
					
					if (contador == 0) {
						
						resultado = valor*1;
						
					} else {
						
						resultado *= ultimoValor;
						
					}
										
					pantalla.setText("" + resultado);
					
					ultimaOperacion = "x";
					
					contador++;
					
				} else if(operacionBuscada.equals("-")) {
				
					if (contador == 0) {
					
						resultado = Double.parseDouble(pantalla.getText());;
					
					} else {
						
						resultado -= ultimoValor;
						
					}
					
					pantalla.setText("" + resultado);
					
					ultimaOperacion = "-";
					
					contador++;
					
				} else if(operacionBuscada.equals("/")) {
					
					double valor = Double.parseDouble(pantalla.getText());
					
					if (contador == 0) {
						
						resultado = valor/1;
						
					} else {
						
						resultado /= ultimoValor;
						
					}
										
					pantalla.setText("" + resultado);
					
					ultimaOperacion = "/";
					
					contador++;
					
				}
				
				else {
					
					if(ultimaOperacion.equals("+")) resultado += ultimoValor;
								
					if(ultimaOperacion.equals("x")) resultado *= ultimoValor; contador = 0;
					
					if(ultimaOperacion.equals("-")) resultado -= ultimoValor; contador = 0;
					
					if(ultimaOperacion.equals("/")) resultado /= ultimoValor; contador = 0;
					
					pantalla.setText("" + resultado);
					
					ultimoValor = 0;
					
				}
				
				comienzo = true;
				
				
			}
		}
}