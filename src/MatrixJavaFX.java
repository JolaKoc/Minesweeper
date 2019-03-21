package minesweeper;

import static java.awt.Color.RED;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Optional;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class MatrixJavaFX extends Application {

    static GameLevels Gl;
    static int matrixNr[][];
    Button[][] matrix; //names the grid of buttons
    static  ComboBox comboBox;
    static int length;
    static int width;
    ArrayList<Integer> array;
@Override
public void start(Stage primaryStage) {
   //COmbo Box for game Levels
    ObservableList<String> options = 
    FXCollections.observableArrayList(
        "SMALL",
        "MEDIUM",
        "LARGE",
        "CUSTOM"
            
    );
     comboBox = new ComboBox(options);//create the combo box with the above options
     comboBox.getSelectionModel().selectFirst();//select for "SMALL" to be seen as default to the combobox
    //set SMALL level set as default
    Gl=new GameLevels("SMALL");
    generate(Gl.getWidth(),Gl.getHeight(),Gl.getMines());
    generate1(primaryStage,Gl.getWidth(),Gl.getHeight()); 
} 

public void generate(int width, int height, int mines){
    
    MatrixGeneration m=new MatrixGeneration(width,height);
     int bombMatrix[][]=m.bombGenerator(mines);//generate the bombs 
    //generate the matrix
    matrixNr=m.matrixGenerator(bombMatrix);
    
    //test the blank space counter
    BlankSpace bs=new BlankSpace(width,height);
    boolean visited[][]=new boolean[width][height];
    System.out.println(bs.DFS(matrixNr, 0, 0, visited));
}

public void generate1(Stage primaryStage,int width,int height){
   //needed to call DFS in order to open all blank spaces
    BlankSpace bs=new BlankSpace(width,height);
    boolean visited[][]=new boolean[width][height];
    //gui
    GridPane gp = new GridPane();//gridpane to generate the matrix of the buttons
    matrix = new Button[width][height];//button matrix
    TextField h=new TextField();
    TextField w=new TextField();
    TextField m=new TextField();
    HBox hb=new HBox();
    hb.getChildren().addAll(h,w,m);
    
    Button ok=new Button("ok");//button to set levels
    Button sub=new Button("Submit");//button to submit values out of text\fields
    
    ok.setOnAction(new EventHandler<ActionEvent>() {//the okay button 
                
                    @Override
                    public void handle(ActionEvent event) {
                        String level=comboBox.getValue()+"";//get the value from the combo box
                        if(level.equals("SMALL")){
                            Gl=new GameLevels("SMALL");
                            generate(Gl.getWidth(),Gl.getHeight(),Gl.getMines());
                            generate1(primaryStage,Gl.getWidth(),Gl.getHeight());
                        }
                        if(level.equals("MEDIUM")){//if it is medium change the length and width of matrix to the medium level parameters
                           
                            Gl=new GameLevels("MEDIUM");
                            generate(Gl.getWidth(),Gl.getHeight(),Gl.getMines());
                            generate1(primaryStage,Gl.getWidth(),Gl.getHeight());
                        }
                        if(level.equals("LARGE")){//if it is large change the length and width of the button matrix to large
                            
                             Gl=new GameLevels("LARGE");
                            generate(Gl.getWidth(),Gl.getHeight(),Gl.getMines());
                            generate1(primaryStage,Gl.getWidth(),Gl.getHeight());
                        }
                        if(level.equals("CUSTOM")){
                            //show the different view
                            VBox root1=new VBox();
                            root1.getChildren().addAll(comboBox,ok,hb,sub,gp);

                            Scene scene = new Scene(root1);
                            primaryStage.setTitle("");
                            primaryStage.setScene(scene);
                            primaryStage.show();
                            
                            sub.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    Gl=new GameLevels(Integer.parseInt(w.getText()),Integer.parseInt(h.getText()),Integer.parseInt(m.getText()));
                                    generate(Gl.getWidth(),Gl.getHeight(),Gl.getMines());
                                    generate1(primaryStage,Gl.getWidth(),Gl.getHeight());
                                }
                                
                            });
                            //generate new matrix
                            
                        }
                        }
                    }
                     );  
    for(int y = 0; y < height; y++)
    {
            for(int x = 0; x < width; x++)
            {
                matrix[x][y] = new Button();
                
                final Button b1 = matrix[x][y]; 
                b1.setText("");
                matrix[x][y].setMinWidth(30);
                final int i=x;
                final int j=y;
                
                b1.setOnAction(new EventHandler<ActionEvent>() {
                
                    @Override
                 public void handle(ActionEvent event) {
                     
                     
                        if(matrixNr[i][j]==0){
                                
                            array=bs.DFS(matrixNr, i, j, visited);
                            for(int a=0;a<array.size();a+=2){
                                
                                matrix[array.get(a)][array.get(a+1)].setText(""+matrixNr[array.get(a)][array.get(a+1)]);
                                
                            }
                            
                        }
                        if(matrixNr[i][j]==-1){
                           
                           matrix[i][j].setText(""+matrixNr[i][j]); 
                           matrix[i][j].setTextFill(Color. RED);
                            Alert a = new Alert(AlertType.ERROR, 
                                "You Lost. Do you want to play again?", 
                                ButtonType.YES, ButtonType.NO);
                            
                            Optional<ButtonType> result = a.showAndWait();
                                if (result.get() == ButtonType.YES){
                                  start(primaryStage);  // user chose YES then start the game from the start
                                } else {
                                     System.exit(0);// user chose NO and closed the dialog
                                }
                           
                        }
                        if(matrixNr[i][j]==1 || matrixNr[i][j]==3 || matrixNr[i][j]==5 || matrixNr[i][j]==7)
                            matrix[i][j].setTextFill(Color. BLUE);
                        if(matrixNr[i][j]==2 || matrixNr[i][j]==4 || matrixNr[i][j]==6)
                            matrix[i][j].setTextFill(Color. GREEN);
                       
                        matrix[i][j].setStyle("-fx-font-weight: bold;-fx-font-family: \"Arial\"");
                        matrix[i][j].setText(""+matrixNr[i][j]);
                    }
                });
                gp.add(matrix[x][y],y,x);
            }
    }     
    VBox root=new VBox();
    root.getChildren().addAll(comboBox,ok,gp);
    
    Scene scene = new Scene(root);
    primaryStage.setTitle("");
    primaryStage.setScene(scene);
    primaryStage.show();
}


public static void main(String[] args) {
    
    //Gui
    launch(args);

}
}

