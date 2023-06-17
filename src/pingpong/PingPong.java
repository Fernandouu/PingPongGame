package pingpong;

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

public class PingPong {

    //Variables 
    private static final int width = 800; //Ancho
    private static final int  height = 600; //Largo
    private static final int  player_Height = 100; //Altura del "personaje/barra"
    private static final int player_Width = 15; //Ancho del "personaje/barra"
    private static final double ball_R = 15;
    private int ballYSpeed = 1; //Velocidad en el eje Y
    private int ballXSpeed = 1; //Velocidad en el eje X
    private double jugador1YPos = height / 2;
    private double jugador2YPos = height / 2;
    private boolean inicio; 
    private int playerOneXPos = 0;
    private double playerTwoXPos = width - player_Width;
    private int puntajeJ1 = 0; //Conteo de Puntos para el Jugador 1
    private int puntajeJ2 = 0; //Conteo de Puntos para el Jugador 2 
    
    
    public void start(Stage escenario) throws Exception  { //Metodo para iniciar aplicacion en JAVAFX
        escenario.setTitle("Ping Pong Game");
        Canvas canvas = new Canvas(width, height); //Se declara el "Canvas"que es el "cuadro" donde estara nuestro juego, como el JForms
        GraphicsContext gc = canvas.getGraphicsContext2D(); //Contexto Grafico que se le dara al Canvas, oseaq 2D
        Timeline tl = new Timeline(new KeyFrame(Duration.millis(10), e ->run(gc))); //TimeLine es para controlar el tiempo
        
        
    }
    public static void main(String[] args) {
        
        
       
       
    }
    
}
