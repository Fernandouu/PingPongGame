package application;

import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
	
	//Variables
	private static final int width = 800; //Ancho del "Stage" 
	private static final int height = 600; //Largo del "Stage"
	private static final int player_height = 100; // Alto del jugador
	private static final int player_width = 15; //Ancho del jugador
	private static final double BALL_R = 15; 
	private int ballYSpeed = 1; //Velocidad de la pelota en el eje Y
	private int ballXSpeed = 1; //Velocidad de la pelota en el eje X
	private double jugador1YPos = height / 2; //Posicion del jugador 1 en el eje Y
	private double jugador2YPos = height / 2; //Posicion del jugador 2 en el eje Y
	private double ballXPos = width / 2; //Posicion de la pelota en el eje X
	private double ballYPos = height / 2; //Posicion de la pelota en el eje Y
	private int puntajeJ1 = 0; //Puntaje jugador 1
	private int puntajeJ2 = 0; //Puntaje jugador 2 (En este caso la "CPU")
	private boolean inicioJuego; //Inicio del juego
	private int jugador1XPos = 0;//Posicion del jugador 1 en el eje X
	private double jugador2XPos = width - player_width;////Posicion del jugador 2 en el eje X
		
	public void start(Stage stage) throws Exception {
		stage.setTitle("Ping Pong Game"); //Para ponerle titulo a la ventana del juego
		//Tamaño del fondo
		Canvas canvas = new Canvas(width, height);
		GraphicsContext gc = canvas.getGraphicsContext2D(); //Contexto grafico en el que se trabajara
		
		//JavaFX Timeline = La clase Timeline en JavaFX se utiliza para crear y controlar animaciones y transiciones en una aplicación JavaFX.
		Timeline tl = new Timeline(new KeyFrame(Duration.millis(10), e -> run(gc)));
		//Numero de ciclos que tendra la animacion: INDEFINITE para que se repita y repita
		tl.setCycleCount(Timeline.INDEFINITE);
		
		//Controles
		canvas.setOnMouseMoved(e ->  jugador1YPos  = e.getY());
		canvas.setOnMouseClicked(e ->  inicioJuego = true);
		stage.setScene(new Scene(new StackPane(canvas)));
		stage.show();
		tl.play();
	}

	private void run(GraphicsContext gc) {
		//Color del Fondo
		gc.setFill(Color.SKYBLUE);
		gc.fillRect(0, 0, width, height);
		
		//Configuracion del texto
		gc.setFill(Color.BLACK);
		gc.setFont(Font.font(25));
			
		if(inicioJuego) {
			//Configuramos la pelota (en este caso la velocidad)
			ballXPos+=ballXSpeed;
			ballYPos+=ballYSpeed;
			
			//La "CPU" el jugador 2 que seguira a la pelota 
			if(ballXPos < width - width  / 4) {
				jugador2YPos = ballYPos - player_height / 2;
			}  else {
				jugador2YPos =  ballYPos > jugador2YPos + player_height / 2 ?jugador2YPos += 1: jugador2YPos - 1;
			}
			//Para la pelota (Dibujarla y crearla)
			gc.fillOval(ballXPos, ballYPos, BALL_R, BALL_R);
			
		} else {
			//Texto de INICIO
			gc.setStroke(Color.BLACK);
			gc.setTextAlign(TextAlignment.CENTER);
			gc.strokeText("Inicia el juego con un CLICK!", width / 2, height / 2);
			
			//Reiniciar la posicion inicial de la pelota
			ballXPos = width / 2;
			ballYPos = height / 2;
			
			//Reiniciar direccion y velocidad de la pelota
			ballXSpeed = new Random().nextInt(2) == 0 ? 1: -1;
			ballYSpeed = new Random().nextInt(2) == 0 ? 1: -1;
		}
		
		//Asegurarse que la pelota no salga del escenario
		if(ballYPos > height || ballYPos < 0) ballYSpeed *=-1;
		
		//Metodo para que si pasa la pelota en tu lado, le de un punto al CPU/Jugador2
		if(ballXPos < jugador1XPos - player_width) {
			puntajeJ2++;
			inicioJuego = false;
		}
		
		//Lo mismo pero ahora si el CPU/Jugador2 pierde la pelota
		if(ballXPos > jugador2XPos + player_width) {  
			puntajeJ1++;
			inicioJuego = false;
		}
	
		//Metodo para que la velocidad de la pelota incremente.
		if( ((ballXPos + BALL_R > jugador2XPos) && ballYPos >= jugador2YPos && ballYPos <= jugador2YPos + player_height) || 
			((ballXPos < jugador1XPos + player_width) && ballYPos >= jugador1YPos && ballYPos <= jugador1YPos + player_height)) {
			ballYSpeed += 1 * Math.signum(ballYSpeed);
			ballXSpeed += 1 * Math.signum(ballXSpeed);
			ballXSpeed *= -1;
			ballYSpeed *= -1;
		}
		
		//Puntaje
		gc.fillText(puntajeJ1 + "\t\t\t\t\t\t\t\t" + puntajeJ2, width / 2, 100);
		//Jugador 1 y 2
		gc.fillRect(jugador2XPos, jugador2YPos, player_width, player_height);
		gc.fillRect(jugador1XPos, jugador1YPos, player_width, player_height);
	}
	
		//Iniciar la aplicacion
		public static void main(String[] args) {
		launch(args);
		}
}