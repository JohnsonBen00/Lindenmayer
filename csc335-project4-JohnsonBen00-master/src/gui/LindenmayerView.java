package gui;

import java.util.ArrayList;
import java.util.Observable;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.LindenmayerModel;
import controller.LindenmayerController;
import graphics.TurtleGraphics;

/**
 * This class acts as the view, and displays the L-system pattern on a different window.
 * @author Benhur J. Tadiparti
 */
public class LindenmayerView extends javafx.application.Application implements java.util.Observer {
	
	private LindenmayerModel model;
	private LindenmayerController control;
	private TurtleGraphics graphics;
	private Rectangle2D screen;
	private GraphicsContext cs;
	
	/**
	 * Starts the program.
	 * @param args -- not used
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	/**
	 * Creates an interactive window where the user can enter the value of their desired L-system,
	 * and displays the result on the same window.
	 */
	public void start(Stage stage) {
		this.model = new LindenmayerModel(); // Model
		this.model.addObserver(this); // Connects to the model, letting the view know that any changes were made to the user inputs
		this.control = new LindenmayerController(this.model); // Controller
		this.screen = Screen.getPrimary().getVisualBounds(); // Screen bounds
		
		stage.setTitle("Lindenmayer System Visualizer"); // Title
		
		BorderPane window = new BorderPane(); // Border Pane
		
		Scene scene = new Scene(window, this.screen.getWidth(), this.screen.getHeight()); // Scene size
		
		VBox vbox = new VBox(); // VBox
		
		HBox hbox = new HBox(); // Position HBox
		  
		Label label = new Label("Initial Position"); // Position label
		label.setFont(new Font(20));
		label.setAlignment(Pos.CENTER);
		
		hbox.getChildren().add(label);
		hbox.setAlignment(Pos.CENTER);
		
		vbox.getChildren().add(hbox);
		
		HBox tbox = new HBox(); // TextField HBox for coordinates
		
		Label x = new Label("X"); // X label
		x.setFont(new Font(20));
		x.setAlignment(Pos.CENTER);
		
		tbox.getChildren().add(x);
		
		TextField tX = new TextField(); // X TextField for X coordinate
		tX.setPrefWidth(this.screen.getWidth() * .04);
		tX.setOnAction((event) -> { // If value is entered, the model stores said value in the appropriate field
			model.setX(Integer.parseInt(tX.getText()));
		});
		
		tbox.getChildren().add(tX);
		
		Label y = new Label("Y"); // Y label
		y.setFont(new Font(20));
		y.setAlignment(Pos.CENTER);
		
		tbox.getChildren().add(y);
		
		TextField tY = new TextField(); // Y TextField for Y coordinate
		tY.setPrefWidth(this.screen.getWidth() * .04);
		tY.setOnAction((event) -> { // If value is entered, the model stores said value in the appropriate field
			model.setY(Integer.parseInt(tY.getText()));
		});
		
		tbox.getChildren().add(tY);
		tbox.setAlignment(Pos.CENTER);
		
		vbox.getChildren().add(tbox);
		
		HBox ibox = new HBox(); // Iterate HBox
		  
		Label iterate = new Label("Iterations"); // Iterations label
		iterate.setFont(new Font(20));
		iterate.setAlignment(Pos.CENTER);
		
		ibox.getChildren().add(iterate);
		ibox.setAlignment(Pos.CENTER);
		
		vbox.getChildren().add(ibox);
		
		HBox sbox = new HBox(); // Slider HBox
		
		Slider slider = new Slider(1, 10, 6);
		
		Label val = new Label("6"); // Slider label
		val.setFont(new Font(20));
		val.setAlignment(Pos.CENTER);
		
		slider.valueProperty().addListener(new ChangeListener<Number>() { 
				@Override
				// If value is entered, the model stores said value in the appropriate field and said value is also displayed on the window
				public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
					val.setText(Integer.toString(newValue.intValue()));
					model.setIterations(newValue.intValue());
				}
	        }); 
		
		sbox.getChildren().addAll(slider, val);
		
		vbox.getChildren().add(sbox);
		
		HBox abox = new HBox(); // Angle HBox
		  
		Label angle = new Label("Angle"); // Angle label
		angle.setFont(new Font(20));
		angle.setAlignment(Pos.CENTER);
		
		abox.getChildren().add(angle);
		abox.setAlignment(Pos.CENTER);
		
		vbox.getChildren().add(abox);
		
		TextField tA = new TextField(); // Angle TextField
		tA.setAlignment(Pos.CENTER);
		tA.setOnAction((event) -> { // If value is entered, the model stores said value in the appropriate field if and only if the value is valid
        	if (Integer.parseInt(tA.getText()) >= 0) { // If angle isn't negative
        		model.setAngle(Integer.parseInt(tA.getText()));
        	} else {
        		event.consume();
        	}
		});
		
		vbox.getChildren().add(tA);
		
		HBox axbox = new HBox(); // Axiom HBox
		  
		Label axiom = new Label("Axiom"); // Axiom label
		axiom.setFont(new Font(20));
		axiom.setAlignment(Pos.CENTER);
		
		axbox.getChildren().add(axiom);
		axbox.setAlignment(Pos.CENTER);
		
		vbox.getChildren().add(axbox);
		
		TextField tAX = new TextField(); // Axiom TextField
		tAX.setAlignment(Pos.CENTER);
		tAX.setOnAction((event) -> { // If value is entered, the model stores said value in the appropriate field if and only if the value is valid
			if (tAX.getText().length() == 1) {
        		model.setAxiom(tAX.getText().charAt(0));
        	} else {
        		event.consume();
        	}
		});
		
		vbox.getChildren().add(tAX);
		
		HBox mbox = new HBox(); // Mapping HBox
		  
		Label map = new Label("Mappings"); // Mapping label
		map.setFont(new Font(20));
		map.setAlignment(Pos.CENTER);
		
		mbox.getChildren().add(map);
		mbox.setAlignment(Pos.CENTER);
		
		vbox.getChildren().add(mbox);
		
		HBox mbox1 = new HBox(); // Mapping HBox 1
		
		TextField map1 = new TextField(); // M1 TextField
		map1.setPrefWidth(screen.getWidth() * .03);
		
		TextField map1f = new TextField(); // M1f TextField
		map1f.setPrefWidth(this.screen.getWidth() * .05);
		
		map1.setOnAction((event) -> { // If value is entered, the model stores said value in the appropriate field if and only if the value is valid
			if (!map1.getText().isEmpty() && !map1f.getText().isEmpty()) {
        		if (map1.getText().length() == 1 && (map1.getText().charAt(0) == 'F' || map1.getText().charAt(0) == 'G')) {
            		model.setMap(map1.getText().charAt(0), map1f.getText());
            	} else {
            		event.consume();
            	}
        	}
		});
		
		mbox1.getChildren().add(map1);
		
		Label arrow = new Label("->"); // Arrow label
		arrow.setFont(new Font(20));
		arrow.setAlignment(Pos.CENTER);
		
		mbox1.getChildren().add(arrow);
		
		map1f.setOnAction((event) -> { // If value is entered, the model stores said value in the appropriate field if and only if the value is valid
			if (!map1.getText().isEmpty() && !map1f.getText().isEmpty()) {
				if (map1.getText().length() == 1 && (map1.getText().charAt(0) == 'F' || map1.getText().charAt(0) == 'G')) {
            		model.setMap(map1.getText().charAt(0), map1f.getText());
            	} else {
            		event.consume();
            	}
        	}
		});
		
		mbox1.getChildren().add(map1f);
		mbox1.setAlignment(Pos.CENTER);
		
		vbox.getChildren().add(mbox1);
		
		HBox mbox2 = new HBox(); // Mapping HBox 2
		
		TextField map2 = new TextField(); // M2 TextField
		map2.setPrefWidth(this.screen.getWidth() * .03);
		
		TextField map2f = new TextField(); // M2f TextField
		map2f.setPrefWidth(this.screen.getWidth() * .05);
		
		map2.setOnAction((event) -> { // If value is entered, the model stores said value in the appropriate field if and only if the value is valid
			if (!map2.getText().isEmpty() && !map2f.getText().isEmpty()) {
				if (map2.getText().length() == 1 && (map2.getText().charAt(0) == 'F' || map2.getText().charAt(0) == 'G')) {
            		model.setMap(map2.getText().charAt(0), map2f.getText());
            	} else {
            		event.consume();
            	}
        	}
		});
		
		mbox2.getChildren().add(map2);
		
		Label arrow2 = new Label("->"); // Arrow label
		arrow2.setFont(new Font(20));
		arrow2.setAlignment(Pos.CENTER);
		
		mbox2.getChildren().add(arrow2);
		
		map2f.setOnAction((event) -> { // If value is entered, the model stores said value in the appropriate field if and only if the value is valid
			if (!map2.getText().isEmpty() && !map2f.getText().isEmpty()) {
				if (map2.getText().length() == 1 && (map2.getText().charAt(0) == 'F' || map2.getText().charAt(0) == 'G')) {
            		model.setMap(map2.getText().charAt(0), map2f.getText());
            	} else {
            		event.consume();
            	}
        	}
		});
		
		mbox2.getChildren().add(map2f);
		mbox2.setAlignment(Pos.CENTER);
		
		vbox.getChildren().add(mbox2);
		
		vbox.setPrefWidth(this.screen.getWidth() * .1);
		vbox.setPrefHeight(this.screen.getHeight() * .1);
		
		window.setLeft(vbox);
		
		Canvas canvas = new Canvas(5000, 5000); // Canvas
		
		// If cursor is clicked anywhere on the canvas, the model stores the x and y coordinate 
        //of where the cursor was clicked, and said coordinates are displayed in their 
        //appropriate TextFields
		canvas.setOnMouseClicked((event) -> { 
			tX.setText(Integer.toString((int)event.getX()));
			tY.setText(Integer.toString((int)event.getY()));
			model.setX(Integer.parseInt(tX.getText()));
			model.setY(Integer.parseInt(tY.getText()));
		});
		
		this.cs = canvas.getGraphicsContext2D();
		this.cs.setFill(Color.WHITE);
		this.cs.fillRect(0, 0, 5000, 5000);
		
		StackPane stack_pane = new StackPane(canvas);
		stack_pane.setStyle("-fx-background-color: white"); // White canvas background
		
		ScrollPane scroll_pane = new ScrollPane(stack_pane);
		
		window.setCenter(scroll_pane);
		
		stage.setScene(scene);

		stage.show();
	}
	
	@Override
	/**
	 * Calls the controller to compute the L-system pattern, and draws said pattern onto the canvas
	 */
	public void update(Observable o, Object arg) {
		this.control.getFinalString(Character.toString(model.getAxiom()), 0);
		this.cs.clearRect(0, 0, 5000, 5000); // Clears previous image on the canvas
		this.graphics = new TurtleGraphics(this.model); // Turtle Graphics
		draw();
	}
	
	/**
	 * Draws the patterns onto the canvas
	 */
	public void draw() {
		ArrayList<Character> list = model.getResult(); // L-system pattern
		double x = 0;
		double y = 0;
		this.cs.setStroke(Color.BLACK);
        this.cs.setLineWidth(1);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) == 'F' || list.get(i) == 'G') {
				x = this.graphics.getX();
				y = this.graphics.getY();
				this.graphics.move(); // Calculates the end x,y coordinates of the line stroke
				this.cs.strokeLine(x, y, this.graphics.getX(), this.graphics.getY());
			} else if (list.get(i) == '-') {
				this.graphics.rotateLeft(); // Calculates how far left should the line turn
			} else if (list.get(i) == '+') {
				this.graphics.rotateRight(); // Calculates how far right should the line turn
			} else if (list.get(i) == '[') {
				ArrayList<Double> list2 = new ArrayList<Double>();
				list2.add(x);
				list2.add(y);
				list2.add(this.graphics.getRotations());
				this.graphics.push(list2); // Stores x,y coordinates, and the turning angle
			} else if (list.get(i) == ']') {
				this.graphics.pop();
			}
		}
	}
	
}