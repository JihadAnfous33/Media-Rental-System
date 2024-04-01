package application;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.function.UnaryOperator;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.effect.Light;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class MainInterface extends Application {
	static MediaRental m = new MediaRental();
	public void start(Stage primaryStage) throws Exception
	{
		try {
			File cust = new File("Customer.txt");
			Scanner custIn = new Scanner(cust);
			while(custIn.hasNextLine())
			{
				String data = custIn.nextLine();
				String [] tokens = data.split("\\$");
				if(tokens != null)
				{
					m.addCustomer(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4]);
				}
			}
			custIn.close();
		}catch(FileNotFoundException e)
		{
			System.out.println("An error occurred.");
		}
		try {
			File media = new File("Media.txt");
			Scanner mediaIn = new Scanner(media);
			while(mediaIn.hasNextLine())
			{
				String data = mediaIn.nextLine();
				String [] tokens = data.split("\\$");
				if(tokens != null)
				{
					if(tokens[0].equals("Movie"))
					{
						m.addMovie(tokens[2], Integer.parseInt(tokens[3]), tokens[4], tokens[1]);
					}
					else if(tokens[0].equals("Game"))
					{
						m.addGame(tokens[2], Integer.parseInt(tokens[3]) , Double.parseDouble(tokens[4]), tokens[1]);
					}
					else {
						m.addAlbum(tokens[2], Integer.parseInt(tokens[3]), tokens[4], tokens[5], tokens[1]);
					}
				}
			}
		} catch(FileNotFoundException e)
		{
			System.out.println("An error occurred.");
		}
		
		File Interested = new File("Interested.txt");
		Scanner InterestedIn = new Scanner(Interested);
		while(InterestedIn.hasNextLine())
		{
			String data = InterestedIn.nextLine();
			String tokens1[] = data.split("\\$");
			String tokens2[] = tokens1[1].split(",");
			for(int i=0; i<m.cList.size(); i++)
			{
				if(m.cList.get(i).getID().equals(tokens1[0]))
				{
					for(int j=0; j<tokens2.length; j++)
					{
						m.cList.get(i).getInterestedIn().add(tokens2[j]);
					}
				}
			}
		}
		
		File Rented = new File("Rented.txt");
		Scanner RentedIn = new Scanner(Rented);
		while(RentedIn.hasNextLine())
		{
			String data = RentedIn.nextLine();
			String tokens1[] = data.split("\\$");
			String tokens2[] = tokens1[1].split(",");
			for(int i=0; i<m.cList.size(); i++)
			{
				if(m.cList.get(i).getID().equals(tokens1[0]))
				{
					for(int j=0; j<tokens2.length; j++)
					{
						m.cList.get(i).getRented().add(tokens2[j]);
					}
				}
			}
			
		}
		//Main Interface
		AllInterfaces(primaryStage);
		
	}
	
	
	public static void main(String[] args) 
	{
		launch(args);
	}
	
	public static void AllInterfaces(Stage primaryStage)
	{
		Image image = new Image("mediarental.jpg");
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(500);
		imageView.setFitWidth(800);
		Button bt_cust = new Button("Customers", new ImageView("customers.png"));
		bt_cust.setStyle("-fx-background-color: black; -fx-border-color: red");
		bt_cust.setTextFill(Color.RED);
		bt_cust.setMaxSize(200, 200);
		Button bt_media = new Button("Media", new ImageView("media.png"));
		bt_media.setStyle("-fx-background-color: black; -fx-border-color: red");
		bt_media.setTextFill(Color.RED);
		bt_media.setMaxSize(200, 200);
		Button bt_rent = new Button("Rent", new ImageView("rent.png"));
		bt_rent.setStyle("-fx-background-color: black; -fx-border-color: red");
		bt_rent.setTextFill(Color.RED);
		bt_rent.setMaxSize(200, 200);
		Label lb_MediaRentalSys = new Label("Media Rental System");
		lb_MediaRentalSys.setTextAlignment(TextAlignment.CENTER);
		lb_MediaRentalSys.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
		lb_MediaRentalSys.setUnderline(true);
		lb_MediaRentalSys.setTextFill(Color.RED);
		bt_cust.setFont(Font.font("Times New Roman" , FontWeight.BOLD , 20));
		bt_media.setFont(Font.font("Times New Roman" , FontWeight.BOLD , 20));
		bt_rent.setFont(Font.font("Times New Roman" , FontWeight.BOLD , 20));
		VBox paneForButtons = new VBox(20);
		paneForButtons.setAlignment(Pos.CENTER_LEFT);
		paneForButtons.getChildren().addAll(bt_cust , bt_media , bt_rent);
		HBox labelPane1 = new HBox();
		labelPane1.getChildren().add(lb_MediaRentalSys);
		labelPane1.setAlignment(Pos.TOP_CENTER);
		labelPane1.setPadding(new Insets(20,20,20,20));
		labelPane1.setStyle("-fx-border-color: red");
		paneForButtons.setPadding(new Insets(250 , 250 , 250 , 250));
		BorderPane pane1 = new BorderPane();
		pane1.setTop(labelPane1);
		pane1.setLeft(paneForButtons);
		pane1.setCenter(imageView);
		pane1.setStyle("-fx-background-color: black");
		Scene scene = new Scene(pane1);
		
		//Customer InterFace
		CustomerMenu(primaryStage , scene , pane1 , bt_cust);
		
		//Media Interface
		MediaMenu(primaryStage, scene, pane1, bt_media);
		
		//Rent Window
		Rent(primaryStage, scene, pane1, bt_rent);
		
		primaryStage.setTitle("Media Rental System");
		primaryStage.setScene(scene);
		primaryStage.setMaximized(true);
		primaryStage.show();
				
	}
	public static void CustomerMenu(Stage primaryStage , Scene scene ,Pane pane1 ,Button bt_cust)
	{
		Button bt_AddCust = new Button("Add Customer");
		bt_AddCust.setTextFill(Color.RED);
		bt_AddCust.setStyle("-fx-background-color: black; -fx-border-color: red");
		Button bt_DeleteCust = new Button("Delete Customer");
		bt_DeleteCust.setTextFill(Color.RED);
		bt_DeleteCust.setStyle("-fx-background-color: black; -fx-border-color: red");
		Button bt_UpdateCust = new Button("Update Customer");
		bt_UpdateCust.setTextFill(Color.RED);
		bt_UpdateCust.setStyle("-fx-background-color: black; -fx-border-color: red");
		Button bt_Search = new Button("Search for a customer");
		bt_Search.setTextFill(Color.RED);
		bt_Search.setStyle("-fx-background-color: black; -fx-border-color: red");
		Button bt_Return = new Button("Return to main page");
		bt_Return.setTextFill(Color.RED);
		bt_Return.setStyle("-fx-background-color: black; -fx-border-color: red");
		Label lb_Cust = new Label("Customer menu");
		lb_Cust.setUnderline(true);
		bt_AddCust.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		bt_DeleteCust.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		bt_UpdateCust.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		bt_Search.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		bt_Return.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_Cust.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
		lb_Cust.setTextFill(Color.RED);
		GridPane ButtonPane = new GridPane();
		ButtonPane.setHgap(100);
		ButtonPane.setAlignment(Pos.CENTER_LEFT);
		ButtonPane.setPadding(new Insets(20,20,20,20));
		ButtonPane.add(bt_AddCust, 0, 0);
		ButtonPane.add(bt_DeleteCust, 1, 0);
		ButtonPane.add(bt_UpdateCust, 2, 0);
		ButtonPane.add(bt_Search, 3, 0);
		HBox labelPane2 = new HBox();
		labelPane2.setPadding(new Insets(20,20,20,20));
		labelPane2.setStyle("-fx-border-color: red");
		labelPane2.getChildren().add(lb_Cust);
		labelPane2.setAlignment(Pos.TOP_CENTER);
		HBox returnPane = new HBox();
		returnPane.getChildren().add(bt_Return);
		returnPane.setPadding(new Insets(20,20,20,20));
		returnPane.setAlignment(Pos.BOTTOM_LEFT);
		BorderPane Bpane = new BorderPane();
		Bpane.setTop(labelPane2);
		Bpane.setCenter(ButtonPane);
		Bpane.setLeft(returnPane);
		StackPane pane2 = new StackPane();
		pane2.setStyle("-fx-background-color: black");
		pane2.getChildren().add(Bpane);
		bt_cust.setOnAction(e -> {
			scene.setRoot(pane2);
			primaryStage.setScene(scene);
		});
		bt_Return.setOnAction(e -> {
			scene.setRoot(pane1);
			primaryStage.setScene(scene);
		});

		//Add New Customer
		try {
			AddCustomer(primaryStage, scene, pane2, bt_AddCust);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	
		//Delete Customer
		try {
			DeleteCustomer(primaryStage, scene, pane2 , bt_DeleteCust);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		//Update Customer's profile
		UpdateCust(primaryStage, scene, pane2, bt_UpdateCust);
		
		//Search for a Customer
		CustSearch(primaryStage, scene, pane2, bt_Search);
	}
	
	public static void AddCustomer(Stage primaryStage , Scene scene , Pane pane2 , Button bt_AddCust) throws IOException
	{
		File cust = new File("Customer.txt");
		ArrayList<String> plan = new ArrayList();
		plan.add("LIMITED");
		plan.add("UNLIMITED");
		ComboBox<String> cbo_Plan = new ComboBox(FXCollections.observableArrayList(plan));
		cbo_Plan.setStyle("-fx-background-color: black; -fx-border-color: red; -fx-font-size: 15pt;");
		Label lb_CustID = new Label("Customer ID");
		lb_CustID.setTextFill(Color.RED);
		Label lb_CustName = new Label("Customer Name");
		lb_CustName.setTextFill(Color.RED);
		Label lb_CustAddress = new Label("Customer Address");
		lb_CustAddress.setTextFill(Color.RED);
		Label lb_CustMobile = new Label("Customer Mobile");
		lb_CustMobile.setTextFill(Color.RED);
		Label lb_plan = new Label("Customer's Plan");
		lb_plan.setTextFill(Color.RED);
		Label lb_CustAdd = new Label("Add Customers");
		lb_CustAdd.setTextFill(Color.RED);
		TextField tf_CustID = new TextField();
		tf_CustID.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_CustID.setFont(Font.font(16));
		TextField tf_CustName = new TextField();
		tf_CustName.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_CustName.setFont(Font.font(16));
		TextField tf_CustAddress = new TextField();
		tf_CustAddress.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_CustAddress.setFont(Font.font(16));
		TextField tf_CustMobile = new TextField();
		tf_CustMobile.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_CustMobile.setFont(Font.font(16));
		TextField tf_Status = new TextField();
		tf_Status.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red;");
		tf_Status.setFont(Font.font(16));
		tf_Status.setEditable(false);
		//for inactivating the textfields
		BooleanBinding tf_CustNameFull = Bindings.createBooleanBinding(() -> {
			   if(tf_CustID.getText().isEmpty())
			   {
				   return true;
			   }
			   else return false;
			}, tf_CustID.textProperty());
		tf_CustName.disableProperty().bind(tf_CustNameFull);
		
		BooleanBinding tf_CustAddressFull = Bindings.createBooleanBinding(() -> {
			   if(tf_CustName.getText().isEmpty())
			   {
				   return true;
			   }
			   else return false;
			}, tf_CustName.textProperty());
		tf_CustAddress.disableProperty().bind(tf_CustNameFull.or(tf_CustAddressFull));
		
		BooleanBinding tf_CustMobileFull = Bindings.createBooleanBinding(() -> {
			   if(tf_CustAddress.getText().isEmpty())
			   {
				   return true;
			   }
			   else return false;
			}, tf_CustAddress.textProperty());
		tf_CustMobile.disableProperty().bind(tf_CustNameFull.or(tf_CustMobileFull).or(tf_CustAddressFull));
		
		BooleanBinding cbo_PlanFull = Bindings.createBooleanBinding(() -> {
			   if(tf_CustMobile.getText().isEmpty())
			   {
				   return true;
			   }
			   else return false;
			}, tf_CustMobile.textProperty());
		cbo_Plan.disableProperty().bind(tf_CustNameFull.or(tf_CustMobileFull).or(tf_CustAddressFull).or(cbo_PlanFull));
		
		HBox labelPane3 = new HBox();
		labelPane3.setPadding(new Insets(20,20,20,20));
		labelPane3.getChildren().add(lb_CustAdd);
		labelPane3.setAlignment(Pos.CENTER);
		labelPane3.setStyle("-fx-border-color: red");
		lb_CustAdd.setTextFill(Color.RED);
		lb_CustAdd.setUnderline(true);
		lb_CustAdd.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
		lb_CustID.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_CustName.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_CustAddress.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_CustMobile.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_plan.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		Button bt_back = new Button("Back" , new ImageView("back.png"));
		bt_back.setStyle("-fx-background-color: black; -fx-border-color: red");
		bt_back.setTextFill(Color.RED);
		bt_back.setOnAction(e -> {
			tf_CustID.setText("");
			tf_CustName.setText("");
			tf_CustAddress.setText("");
			tf_CustMobile.setText("");
			tf_Status.setText("");
			cbo_Plan.setValue("");
			scene.setRoot(pane2);
			primaryStage.setScene(scene);
		});
		
		Button bt_Add = new Button("Add" , new ImageView("add.png"));
		bt_Add.setStyle("-fx-background-color: black; -fx-border-color: red");
		bt_Add.setTextFill(Color.RED);
		bt_Add.setOnAction(e ->{
			boolean flag = true;
			for(int i=0; i<m.cList.size(); i++)
			{
				if(m.cList.get(i).getID().equals(tf_CustID.getText()))
				{
					flag=false;
					break;
				}
			}
			if(flag)
			{
				m.addCustomer(tf_CustID.getText(), tf_CustName.getText(), tf_CustAddress.getText(), tf_CustMobile.getText(), cbo_Plan.getValue().toString());
				try {
					FileWriter custOut = new FileWriter(cust, false);
					custOut.write(m.getAllCustomersInfo());
					custOut.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				tf_Status.setText("Added Successfully.");
			}
			else {
				tf_Status.setText("Customer already exists.");
			}
			cbo_Plan.getValue().toString();
			tf_CustID.setText("");
			tf_CustName.setText("");
			tf_CustAddress.setText("");
			tf_CustMobile.setText("");
			cbo_Plan.setValue("");
		});
		
		HBox ButtonsPane = new HBox(30);
		ButtonsPane.getChildren().addAll(bt_back , bt_Add, tf_Status);
		ButtonsPane.setAlignment(Pos.CENTER);
		ButtonsPane.setPadding(new Insets(40,40,40,40));
 		GridPane gpane = new GridPane();
		gpane.setAlignment(Pos.CENTER);
		gpane.setVgap(30);
		gpane.setHgap(30);
		gpane.add(lb_CustID, 0, 0);
		gpane.add(tf_CustID, 1, 0);
		gpane.add(lb_CustName, 0, 1);
		gpane.add(tf_CustName, 1, 1);
		gpane.add(lb_CustAddress, 0, 2);
		gpane.add(tf_CustAddress, 1, 2);
		gpane.add(lb_CustMobile, 0, 3);
		gpane.add(tf_CustMobile, 1, 3);
		gpane.add(lb_plan, 0, 4);
		gpane.add(cbo_Plan, 1, 4);
		BorderPane Bpane = new BorderPane();
		Bpane.setTop(labelPane3);
		Bpane.setCenter(gpane);
		Bpane.setBottom(ButtonsPane);
		
		StackPane pane = new StackPane();
		pane.setStyle("-fx-background-color: black");
		pane.getChildren().add(Bpane);
		
		bt_AddCust.setOnAction(e ->{
			scene.setRoot(pane);
			primaryStage.setScene(scene);
		});
		
		
	}
	
	public static void DeleteCustomer(Stage primaryStage ,Scene scene , Pane pane2 , Button bt_DeleteCust) throws IOException
	{
		File cust = new File("Customer.txt");
		File Interested = new File("Interested.txt");
		File Rented = new File("Rented.txt");
		Label lb_CustID = new Label("Customer ID");
		lb_CustID.setTextFill(Color.RED);
		lb_CustID.setFont(Font.font(16));
		Label lb_CustName = new Label("Customer Name");
		lb_CustName.setTextFill(Color.RED);
		lb_CustName.setFont(Font.font(16));
		Label lb_CustAddress = new Label("Customer Address");
		lb_CustAddress.setTextFill(Color.RED);
		lb_CustAddress.setFont(Font.font(16));
		Label lb_CustMobile = new Label("Customer Mobile");
		lb_CustMobile.setTextFill(Color.RED);
		lb_CustMobile.setFont(Font.font(16));
		Label lb_plan = new Label("Cusomer's Plan");
		lb_plan.setTextFill(Color.RED);
		lb_plan.setFont(Font.font(16));
		Label lb_CustDelete = new Label("Delete Customers");
		TextField tf_CustID = new TextField();
		tf_CustID.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_CustID.setFont(Font.font(16));
		TextField tf_CustName = new TextField();
		tf_CustName.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_CustName.setFont(Font.font(16));
		TextField tf_CustAddress = new TextField();
		tf_CustAddress.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_CustAddress.setFont(Font.font(16));
		TextField tf_CustMobile = new TextField();
		tf_CustMobile.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_CustMobile.setFont(Font.font(16));
		TextField tf_plan = new TextField();
		tf_plan.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_plan.setFont(Font.font(16));
		
		tf_CustName.setDisable(true);
		tf_CustAddress.setDisable(true);
		tf_CustMobile.setDisable(true);
		tf_plan.setDisable(true);
		tf_CustID.setDisable(false);
		
		tf_CustID.setEditable(true);
		tf_CustName.setEditable(false);//for disable editing for user
		tf_CustAddress.setEditable(false);
		tf_CustMobile.setEditable(false);
		tf_plan.setEditable(false);
		
		HBox labelPane = new HBox();
		labelPane.setPadding(new Insets(20,20,20,20));
		labelPane.getChildren().add(lb_CustDelete);
		labelPane.setAlignment(Pos.CENTER);
		labelPane.setStyle("-fx-border-color: red");
		lb_CustDelete.setTextFill(Color.RED);
		lb_CustDelete.setUnderline(true);
		lb_CustDelete.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
		lb_CustID.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_CustName.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_CustAddress.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_CustMobile.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_plan.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		Button bt_back = new Button("Back " , new ImageView("back.png"));
		bt_back.setTextFill(Color.RED);
		bt_back.setStyle("-fx-background-color: black; -fx-border-color: red");
		bt_back.setOnAction(e -> {
			tf_CustName.setDisable(true);
			tf_CustAddress.setDisable(true);
			tf_CustMobile.setDisable(true);
			tf_plan.setDisable(true);
			tf_CustID.setDisable(false);
			tf_CustID.setEditable(true);
			tf_CustID.setText("");
			tf_CustName.setText("");
			tf_CustAddress.setText("");
			tf_CustMobile.setText("");
			tf_plan.setText("");
			scene.setRoot(pane2);
			primaryStage.setScene(scene);
		});
		Button bt_Delete = new Button("Delete" , new ImageView("delete.png"));
		bt_Delete.setTextFill(Color.RED);
		bt_Delete.setStyle("-fx-background-color: black; -fx-border-color: red");
		bt_Delete.setOnAction(e ->{
			for(int i=0; i<m.cList.size(); i++)
			{
				if(m.cList.get(i).getID().equals(tf_CustID.getText()))
				{
					m.cList.remove(i);
					try {
						FileWriter custOut = new FileWriter(cust, false);
						custOut.write(m.getAllCustomersInfo());
						custOut.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					try {
						FileWriter InterestedOut = new FileWriter(Interested, false);
						InterestedOut.write(getAllInterestedInfo());
						InterestedOut.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					try {
						FileWriter RentOut = new FileWriter(Rented,false);
						RentOut.write(getAllRentedInfo());
						RentOut.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					tf_CustID.setText("");
					tf_CustName.setText("");
					tf_CustAddress.setText("");
					tf_CustMobile.setText("");
					tf_plan.setText("");
				}
			}
			tf_CustName.setDisable(true);
			tf_CustAddress.setDisable(true);
			tf_CustMobile.setDisable(true);
			tf_plan.setDisable(true);
			tf_CustID.setDisable(false);
			tf_CustID.setEditable(true);
		});
		Button bt_Find = new Button("Find " , new ImageView("find user.png"));
		bt_Find.setTextFill(Color.RED);
		bt_Find.setStyle("-fx-background-color: black; -fx-border-color: red");
		bt_Find.setOnAction(e ->{
			for(int i=0; i<m.cList.size(); i++)
			{
				if(m.cList.get(i).getID().equals(tf_CustID.getText()))
				{
					tf_CustName.setText(m.cList.get(i).getName());
					tf_CustAddress.setText(m.cList.get(i).getAddress());
					tf_CustMobile.setText(m.cList.get(i).getMobileNum());
					tf_plan.setText(m.cList.get(i).getPlan());
				}
			}
			tf_CustName.setDisable(false);
			tf_CustAddress.setDisable(false);
			tf_CustMobile.setDisable(false);
			tf_plan.setDisable(false);
			tf_CustID.setDisable(false);
			tf_CustID.setEditable(false);
		});
		HBox ButtonsPane = new HBox(30);
		ButtonsPane.getChildren().addAll( bt_back, bt_Find ,bt_Delete);
		ButtonsPane.setAlignment(Pos.CENTER);
		ButtonsPane.setPadding(new Insets(40,40,40,40));
 		GridPane gpane = new GridPane();
		gpane.setAlignment(Pos.CENTER);
		gpane.setVgap(30);
		gpane.setHgap(30);
		gpane.add(lb_CustID, 0, 0);
		gpane.add(tf_CustID, 1, 0);
		gpane.add(lb_CustName, 0, 1);
		gpane.add(tf_CustName, 1, 1);
		gpane.add(lb_CustAddress, 0, 2);
		gpane.add(tf_CustAddress, 1, 2);
		gpane.add(lb_CustMobile, 0, 3);
		gpane.add(tf_CustMobile, 1, 3);
		gpane.add(lb_plan, 0, 4);
		gpane.add(tf_plan, 1, 4);
		BorderPane Bpane = new BorderPane();
		Bpane.setTop(labelPane);
		Bpane.setCenter(gpane);
		Bpane.setBottom(ButtonsPane);
		
		StackPane pane = new StackPane();
		pane.getChildren().add(Bpane);
		pane.setStyle("-fx-background-color: black");
		bt_DeleteCust.setOnAction(e ->{
			scene.setRoot(pane);
			primaryStage.setScene(scene);
		});
		
	}
	
	public static void CustSearch(Stage primaryStage , Scene scene , Pane pane2 , Button bt_Search)
	{
		Label lb_CustID = new Label("Customer ID");
		lb_CustID.setTextFill(Color.RED);
		lb_CustID.setFont(Font.font(16));
		Label lb_CustName = new Label("Customer Name");
		lb_CustName.setTextFill(Color.RED);
		lb_CustName.setFont(Font.font(16));
		Label lb_CustAddress = new Label("Customer Address");
		lb_CustAddress.setTextFill(Color.RED);
		lb_CustAddress.setFont(Font.font(16));
		Label lb_CustMobile = new Label("Customer Mobile");
		lb_CustMobile.setTextFill(Color.RED);
		lb_CustMobile.setFont(Font.font(16));
		Label lb_plan = new Label("Customer's Plan");
		lb_plan.setTextFill(Color.RED);
		lb_plan.setFont(Font.font(16));
		Label lb_CustDelete = new Label("Search for a Customer by ID");
		TextField tf_CustID = new TextField();
		tf_CustID.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_CustID.setFont(Font.font(16));
		TextField tf_CustName = new TextField();
		tf_CustName.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_CustName.setFont(Font.font(16));
		TextField tf_CustAddress = new TextField();
		tf_CustAddress.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_CustAddress.setFont(Font.font(16));
		TextField tf_CustMobile = new TextField();
		tf_CustMobile.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_CustMobile.setFont(Font.font(16));
		TextField tf_plan = new TextField();
		tf_plan.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_plan.setFont(Font.font(16));
		tf_CustName.setEditable(false);//for disable editing for user
		tf_CustAddress.setEditable(false);
		tf_CustMobile.setEditable(false);
		tf_plan.setEditable(false);
		HBox labelPane = new HBox();
		labelPane.setPadding(new Insets(20,20,20,20));
		labelPane.getChildren().add(lb_CustDelete);
		labelPane.setAlignment(Pos.CENTER);
		labelPane.setStyle("-fx-border-color: red");
		lb_CustDelete.setTextFill(Color.RED);
		lb_CustDelete.setUnderline(true);
		lb_CustDelete.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
		lb_CustID.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_CustName.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_CustAddress.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_CustMobile.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_plan.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		
		tf_CustName.setDisable(true);
		tf_CustAddress.setDisable(true);
		tf_CustMobile.setDisable(true);
		tf_plan.setDisable(true);
		tf_CustID.setDisable(false);
		
		Button bt_back = new Button("Back " , new ImageView("back.png"));
		bt_back.setTextFill(Color.RED);
		bt_back.setStyle("-fx-background-color: black; -fx-border-color: red");
		bt_back.setOnAction(e -> {
			tf_CustName.setDisable(true);
			tf_CustAddress.setDisable(true);
			tf_CustMobile.setDisable(true);
			tf_plan.setDisable(true);
			tf_CustID.setDisable(false);
			tf_CustID.setEditable(true);
			tf_CustID.setText("");
			tf_CustName.setText("");
			tf_CustAddress.setText("");
			tf_CustMobile.setText("");
			tf_plan.setText("");
			scene.setRoot(pane2);
			primaryStage.setScene(scene);
		});
		Button bt_Find = new Button("Find" , new ImageView("find user.png"));
		bt_Find.setTextFill(Color.RED);
		bt_Find.setStyle("-fx-background-color: black; -fx-border-color: red");
		bt_Find.setOnAction(e ->{
			for(int i=0; i<m.cList.size(); i++)
			{
				if(m.cList.get(i).getID().equals(tf_CustID.getText()))
				{
					tf_CustName.setText(m.cList.get(i).getName());
					tf_CustAddress.setText(m.cList.get(i).getAddress());
					tf_CustMobile.setText(m.cList.get(i).getMobileNum());
					tf_plan.setText(m.cList.get(i).getPlan());
				}
			}
			tf_CustName.setDisable(false);
			tf_CustAddress.setDisable(false);
			tf_CustMobile.setDisable(false);
			tf_plan.setDisable(false);
			tf_CustID.setDisable(false);
			tf_CustID.setEditable(false);
		});
		Button bt_Clear = new Button("Clear All", new ImageView("clear.png"));
		bt_Clear.setStyle("-fx-background-color: black; -fx-border-color: red;");
		bt_Clear.setTextFill(Color.RED);
		bt_Clear.setOnAction(e ->{
			tf_CustID.setText("");
			tf_CustName.setText("");
			tf_CustAddress.setText("");
			tf_CustMobile.setText("");
			tf_plan.setText("");
			tf_CustID.setEditable(true);
			tf_CustName.setDisable(true);
			tf_CustAddress.setDisable(true);
			tf_CustMobile.setDisable(true);
			tf_plan.setDisable(true);
		});
		HBox ButtonsPane = new HBox(30);
		ButtonsPane.getChildren().addAll( bt_back, bt_Find, bt_Clear);
		ButtonsPane.setAlignment(Pos.CENTER);
		ButtonsPane.setPadding(new Insets(40,40,40,40));
 		GridPane gpane = new GridPane();
		gpane.setAlignment(Pos.CENTER);
		gpane.setVgap(30);
		gpane.setHgap(30);
		gpane.add(lb_CustID, 0, 0);
		gpane.add(tf_CustID, 1, 0);
		gpane.add(lb_CustName, 0, 1);
		gpane.add(tf_CustName, 1, 1);
		gpane.add(lb_CustAddress, 0, 2);
		gpane.add(tf_CustAddress, 1, 2);
		gpane.add(lb_CustMobile, 0, 3);
		gpane.add(tf_CustMobile, 1, 3);
		gpane.add(lb_plan, 0, 4);
		gpane.add(tf_plan, 1, 4);
		BorderPane Bpane = new BorderPane();
		Bpane.setTop(labelPane);
		Bpane.setCenter(gpane);
		Bpane.setBottom(ButtonsPane);
		
		StackPane pane = new StackPane();
		pane.setStyle("-fx-background-color: black");
		pane.getChildren().add(Bpane);
		bt_Search.setOnAction(e ->{
			scene.setRoot(pane);
			primaryStage.setScene(scene);
		});
	}
	
	public static void UpdateCust(Stage primaryStage , Scene scene , Pane pane2 , Button bt_UpdateCust)
	{
		File cust = new File("Customer.txt");
		ArrayList<String> plan = new ArrayList();
		plan.add("LIMITED");
		plan.add("UNLIMITED");
		ComboBox<String> cbo_Plan = new ComboBox(FXCollections.observableArrayList(plan));
		cbo_Plan.setStyle("-fx-background-color: black; -fx-border-color: red; -fx-font-size: 15pt;");
		Label lb_CustID = new Label("Customer ID");
		lb_CustID.setTextFill(Color.RED);
		lb_CustID.setFont(Font.font(16));
		Label lb_CustName = new Label("Customer Name");
		lb_CustName.setTextFill(Color.RED);
		lb_CustName.setFont(Font.font(16));
		Label lb_CustAddress = new Label("Customer Address");
		lb_CustAddress.setTextFill(Color.RED);
		lb_CustAddress.setFont(Font.font(16));
		Label lb_CustMobile = new Label("Customer Mobile");
		lb_CustMobile.setTextFill(Color.RED);
		lb_CustMobile.setFont(Font.font(16));
		Label lb_plan = new Label("Customer's Plan");
		lb_plan.setTextFill(Color.RED);
		lb_plan.setFont(Font.font(16));
		Label lb_CustDelete = new Label("Update Customer's Profile");
		TextField tf_CustID = new TextField();
		tf_CustID.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_CustID.setFont(Font.font(16));
		TextField tf_CustName = new TextField();
		tf_CustName.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_CustName.setFont(Font.font(16));
		TextField tf_CustAddress = new TextField();
		tf_CustAddress.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_CustAddress.setFont(Font.font(16));
		TextField tf_CustMobile = new TextField();
		tf_CustMobile.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_CustMobile.setFont(Font.font(16));
		
		tf_CustName.setDisable(true);
		tf_CustAddress.setDisable(true);
		tf_CustMobile.setDisable(true);
		cbo_Plan.setDisable(true);
		tf_CustID.setDisable(false);
		
		HBox labelPane = new HBox();
		labelPane.setPadding(new Insets(20,20,20,20));
		labelPane.getChildren().add(lb_CustDelete);
		labelPane.setAlignment(Pos.CENTER);
		labelPane.setStyle("-fx-border-color: red");
		lb_CustDelete.setTextFill(Color.RED);
		lb_CustDelete.setUnderline(true);
		lb_CustDelete.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
		lb_CustID.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_CustName.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_CustAddress.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_CustMobile.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_plan.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		Button bt_back = new Button("Back " , new ImageView("back.png"));
		bt_back.setTextFill(Color.RED);
		bt_back.setStyle("-fx-background-color: black; -fx-border-color: red");
		bt_back.setOnAction(e -> {
			tf_CustName.setDisable(true);
			tf_CustAddress.setDisable(true);
			tf_CustMobile.setDisable(true);
			cbo_Plan.setDisable(true);
			tf_CustID.setDisable(false);
			tf_CustID.setText("");
			tf_CustName.setText("");
			tf_CustAddress.setText("");
			tf_CustMobile.setText("");
			cbo_Plan.setValue("");
			scene.setRoot(pane2);
			primaryStage.setScene(scene);
		});
		Button bt_Find = new Button("Find " , new ImageView("find user.png"));
		bt_Find.setTextFill(Color.RED);
		bt_Find.setStyle("-fx-background-color: black; -fx-border-color: red");
		bt_Find.setOnAction(e ->{
			for(int i=0; i<m.cList.size(); i++)
			{
				if(m.cList.get(i).getID().equals(tf_CustID.getText()))
				{
					tf_CustName.setText(m.cList.get(i).getName());
					tf_CustAddress.setText(m.cList.get(i).getAddress());
					tf_CustMobile.setText(m.cList.get(i).getMobileNum());
					cbo_Plan.setValue(m.cList.get(i).getPlan());
				}
			}
			tf_CustName.setDisable(false);
			tf_CustAddress.setDisable(false);
			tf_CustMobile.setDisable(false);
			cbo_Plan.setDisable(false);
			tf_CustID.setDisable(true);
		});
		Button bt_Update = new Button("Update" , new ImageView("update.png"));
		bt_Update.setTextFill(Color.RED);
		bt_Update.setStyle("-fx-background-color: black; -fx-border-color: red");
		bt_Update.setOnAction(e ->{
			for(int i=0; i<m.cList.size(); i++)
			{
				if(m.cList.get(i).getID().equals(tf_CustID.getText()))
				{
					m.cList.get(i).setName(tf_CustName.getText());
					m.cList.get(i).setAddress(tf_CustAddress.getText());
					m.cList.get(i).setMobileNum(tf_CustMobile.getText());
					m.cList.get(i).setPlan(cbo_Plan.getValue().toString());
					
					try {
						FileWriter custOut = new FileWriter(cust, false);
						custOut.write(m.getAllCustomersInfo());
						custOut.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					tf_CustID.setText("");
					tf_CustName.setText("");
					tf_CustAddress.setText("");
					tf_CustMobile.setText("");
					cbo_Plan.setValue("");
				}
			}
			tf_CustName.setDisable(true);
			tf_CustAddress.setDisable(true);
			tf_CustMobile.setDisable(true);
			cbo_Plan.setDisable(true);
			tf_CustID.setDisable(false);
		});
		HBox ButtonsPane = new HBox(30);
		ButtonsPane.getChildren().addAll( bt_back, bt_Find ,bt_Update);
		ButtonsPane.setAlignment(Pos.CENTER);
		ButtonsPane.setPadding(new Insets(40,40,40,40));
 		GridPane gpane = new GridPane();
		gpane.setAlignment(Pos.CENTER);
		gpane.setVgap(30);
		gpane.setHgap(30);
		gpane.add(lb_CustID, 0, 0);
		gpane.add(tf_CustID, 1, 0);
		gpane.add(lb_CustName, 0, 1);
		gpane.add(tf_CustName, 1, 1);
		gpane.add(lb_CustAddress, 0, 2);
		gpane.add(tf_CustAddress, 1, 2);
		gpane.add(lb_CustMobile, 0, 3);
		gpane.add(tf_CustMobile, 1, 3);
		gpane.add(lb_plan, 0, 4);
		gpane.add(cbo_Plan, 1, 4);
		BorderPane Bpane = new BorderPane();
		Bpane.setTop(labelPane);
		Bpane.setCenter(gpane);
		Bpane.setBottom(ButtonsPane);
		
		StackPane pane = new StackPane();
		pane.setStyle("-fx-background-color: black");
		pane.getChildren().add(Bpane);
		bt_UpdateCust.setOnAction(e ->{
			scene.setRoot(pane);
			primaryStage.setScene(scene);
		});
		
	}
	
	public static void MediaMenu(Stage primaryStage , Scene scene , Pane pane1, Button bt_media)
	{
		Button bt_AddMedia = new Button("Add Media");
		bt_AddMedia.setTextFill(Color.RED);
		bt_AddMedia.setStyle("-fx-background-color: black; -fx-border-color: red");
		Button bt_DeleteMedia = new Button("Delete Media");
		bt_DeleteMedia.setTextFill(Color.RED);
		bt_DeleteMedia.setStyle("-fx-background-color: black; -fx-border-color: red");
		Button bt_UpdateMedia = new Button("Update Media");
		bt_UpdateMedia.setTextFill(Color.RED);
		bt_UpdateMedia.setStyle("-fx-background-color: black; -fx-border-color: red");
		Button bt_SearchMedia = new Button("Search for Media");
		bt_SearchMedia.setTextFill(Color.RED);
		bt_SearchMedia.setStyle("-fx-background-color: black; -fx-border-color: red");
		Button bt_PrintMedia = new Button("Media Information");
		bt_PrintMedia.setTextFill(Color.RED);
		bt_PrintMedia.setStyle("-fx-background-color: black; -fx-border-color: red");
		Button bt_ReturnM = new Button("Return to main page");
		bt_ReturnM.setTextFill(Color.RED);
		bt_ReturnM.setStyle("-fx-background-color: black; -fx-border-color: red");
		Label lb_Media = new Label("Media menu");
		lb_Media.setUnderline(true);
		bt_AddMedia.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		bt_DeleteMedia.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		bt_UpdateMedia.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		bt_SearchMedia.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		bt_PrintMedia.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		bt_ReturnM.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_Media.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
		lb_Media.setTextFill(Color.RED);
		GridPane ButtonPaneM = new GridPane();
		ButtonPaneM.setHgap(100);
		ButtonPaneM.setAlignment(Pos.CENTER_LEFT);
		ButtonPaneM.setPadding(new Insets(20,20,20,20));
		ButtonPaneM.add(bt_AddMedia, 0, 0);
		ButtonPaneM.add(bt_DeleteMedia, 1, 0);
		ButtonPaneM.add(bt_UpdateMedia, 2, 0);
		ButtonPaneM.add(bt_SearchMedia, 3, 0);
		ButtonPaneM.add(bt_PrintMedia, 4, 0);
		HBox labelPaneM = new HBox();
		labelPaneM.setPadding(new Insets(20,20,20,20));
		labelPaneM.setStyle("-fx-border-color: red");
		labelPaneM.getChildren().add(lb_Media);
		labelPaneM.setAlignment(Pos.TOP_CENTER);
		HBox returnPaneM = new HBox();
		returnPaneM.getChildren().add(bt_ReturnM);
		returnPaneM.setPadding(new Insets(20,20,20,20));
		returnPaneM.setAlignment(Pos.BOTTOM_LEFT);
		BorderPane Bpane = new BorderPane();
		Bpane.setTop(labelPaneM);
		Bpane.setCenter(ButtonPaneM);
		Bpane.setLeft(returnPaneM);
		StackPane pane3 = new StackPane();
		pane3.getChildren().add(Bpane);
		pane3.setStyle("-fx-background-color: black");
		bt_media.setOnAction(e -> {
			scene.setRoot(pane3);
			primaryStage.setScene(scene);
		});
		
		
		bt_ReturnM.setOnAction(e -> {
			scene.setRoot(pane1);
			primaryStage.setScene(scene);
		});
		
		//Add Media
		try {
			AddMedia(primaryStage, scene, pane3, bt_AddMedia);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		//Delete Media
		DeleteMedia(primaryStage, scene, pane3, bt_DeleteMedia);
		
		//Update Media
		UpdateMedia(primaryStage, scene, pane3, bt_UpdateMedia);
		
		//Search For Media
		SearchMedia(primaryStage, scene, pane3, bt_SearchMedia);
		
		//Print Media Info
		PrintMediaInfo(primaryStage, scene, pane3, bt_PrintMedia);
	}
	
	
	public static void AddMedia(Stage primaryStage , Scene scene , Pane pane3 , Button bt_AddMedia) throws IOException
	{
		File media = new File("Media.txt");
		ArrayList <String> MediaType = new ArrayList();
		MediaType.add("Movie");
		MediaType.add("Game");
		MediaType.add("Album");
		ArrayList<String> Rating = new ArrayList();
		Rating.add("HR");
		Rating.add("DR");
		Rating.add("AC");
		ComboBox<String> cbo_MediaType = new ComboBox(FXCollections.observableArrayList(MediaType));
		cbo_MediaType.setStyle("-fx-border-color: red; -fx-background-color: black; -fx-font-size: 15pt");
		ComboBox<String> cbo_Rating = new ComboBox(FXCollections.observableArrayList(Rating));
		cbo_Rating.setStyle("-fx-border-color: red; -fx-background-color: black; -fx-font-size: 15pt");
		Label lb_MediaType = new Label("Media Type");
		lb_MediaType.setTextFill(Color.RED);
		Label lb_MovieTitle = new Label("Movie Title");
		lb_MovieTitle.setTextFill(Color.RED);
		Label lb_GameTitle = new Label("Game Title");
		lb_GameTitle.setTextFill(Color.RED);
		Label lb_AlbumTitle = new Label("Album Title");
		lb_AlbumTitle.setTextFill(Color.RED);
		Label lb_AlbumCopies = new Label("Copies Available");
		lb_AlbumCopies.setTextFill(Color.RED);
		Label lb_GameCopies = new Label("Copies Available");
		lb_GameCopies.setTextFill(Color.RED);
		Label lb_MovieCopies = new Label("Copies Available");
		lb_MovieCopies.setTextFill(Color.RED);
		Label lb_Weight = new Label("Weight In Grams");
		lb_Weight.setTextFill(Color.RED);
		Label lb_Rating = new Label("Rating");
		lb_Rating.setTextFill(Color.RED);
		Label lb_Artist = new Label("Artist Name");
		lb_Artist.setTextFill(Color.RED);
		Label lb_Songs = new Label("Songs \n(Seperated by commas)");
		lb_Songs.setTextFill(Color.RED);
		Label lb_AlbumCode = new Label("Album Code");
		lb_AlbumCode.setTextFill(Color.RED);
		Label lb_GameCode = new Label("Game Code");
		lb_GameCode.setTextFill(Color.RED);
		Label lb_MovieCode = new Label("Movie Code");
		lb_MovieCode.setTextFill(Color.RED);
		Label lb_AddMedia = new Label("Add Media");
		Label lb_AddMovie = new Label("Add Movie");
		Label lb_AddGame = new Label("Add Game");
		Label lb_AddAlbum = new Label("Add Album");
		
		lb_AddMedia.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
		lb_AddMedia.setTextFill(Color.RED);
		lb_AddMedia.setUnderline(true);
		HBox labelPane1 = new HBox();
		labelPane1.setAlignment(Pos.CENTER);
		labelPane1.getChildren().add(lb_AddMedia);
		labelPane1.setStyle("-fx-border-color: red; -fx-background-color: black");
		labelPane1.setPadding(new Insets(20,20,20,20));
		
		lb_AddMovie.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
		lb_AddMovie.setTextFill(Color.RED);
		lb_AddMovie.setUnderline(true);
		HBox labelPane2 = new HBox();
		labelPane2.setAlignment(Pos.CENTER);
		labelPane2.getChildren().add(lb_AddMovie);
		labelPane2.setStyle("-fx-border-color: red; -fx-background-color: black");
		labelPane2.setPadding(new Insets(20,20,20,20));
		
		lb_AddGame.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
		lb_AddGame.setTextFill(Color.RED);
		lb_AddGame.setUnderline(true);
		HBox labelPane3 = new HBox();
		labelPane3.setAlignment(Pos.CENTER);
		labelPane3.getChildren().add(lb_AddGame);
		labelPane3.setStyle("-fx-border-color: red; -fx-background-color: black");
		labelPane3.setPadding(new Insets(20,20,20,20));
		
		lb_AddAlbum.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
		lb_AddAlbum.setTextFill(Color.RED);
		lb_AddAlbum.setUnderline(true);
		HBox labelPane4 = new HBox();
		labelPane4.setAlignment(Pos.CENTER);
		labelPane4.getChildren().add(lb_AddAlbum);
		labelPane4.setStyle("-fx-border-color: red; -fx-background-color: black");
		labelPane4.setPadding(new Insets(20,20,20,20));
		
		
		lb_MediaType.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_MovieTitle.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_GameTitle.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_AlbumTitle.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_AlbumCopies.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_GameCopies.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_MovieCopies.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_Weight.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_Rating.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_Artist.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_Songs.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_AlbumCode.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_GameCode.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_MovieCode.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		TextField tf_AlbumTitle = new TextField();
		tf_AlbumTitle.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_AlbumTitle.setFont(Font.font(16));
		TextField tf_GameTitle = new TextField();
		tf_GameTitle.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_GameTitle.setFont(Font.font(16));
		TextField tf_MovieTitle = new TextField();
		tf_MovieTitle.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_MovieTitle.setFont(Font.font(16));
		TextField tf_AlbumCopies = new TextField();
		tf_AlbumCopies.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_AlbumCopies.setFont(Font.font(16));
		TextField tf_GameCopies = new TextField();
		tf_GameCopies.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_GameCopies.setFont(Font.font(16));
		TextField tf_MovieCopies = new TextField();
		tf_MovieCopies.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_MovieCopies.setFont(Font.font(16));
		TextField tf_Weight = new TextField();
		tf_Weight.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_Weight.setFont(Font.font(16));
		TextField tf_Artist = new TextField();
		tf_Artist.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_Artist.setFont(Font.font(16));
		TextField tf_Songs = new TextField();
		tf_Songs.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_Songs.setFont(Font.font(16));
		TextField tf_AlbumCode = new TextField();
		tf_AlbumCode.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_AlbumCode.setFont(Font.font(16));
		TextField tf_GameCode = new TextField();
		tf_GameCode.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_GameCode.setFont(Font.font(16));
		TextField tf_MovieCode = new TextField();
		tf_MovieCode.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_MovieCode.setFont(Font.font(16));
		TextField tf_MovieStatus = new TextField();
		tf_MovieStatus.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_MovieStatus.setFont(Font.font(16));
		tf_MovieStatus.setEditable(false);
		TextField tf_GameStatus = new TextField();
		tf_GameStatus.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_GameStatus.setFont(Font.font(16));
		tf_GameStatus.setEditable(false);
		TextField tf_AlbumStatus = new TextField();
		tf_AlbumStatus.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_AlbumStatus.setFont(Font.font(16));
		tf_AlbumStatus.setEditable(false);
	
		BooleanBinding tf_MovieTitleD = Bindings.createBooleanBinding(() -> {
			   if(tf_MovieCode.getText().isEmpty())
			   {
				   return true;
			   }
			   else return false;
			}, tf_MovieCode.textProperty());
		tf_MovieTitle.disableProperty().bind((tf_MovieTitleD));
		
		BooleanBinding tf_MovieCopiesD = Bindings.createBooleanBinding(() -> {
			   if(tf_MovieTitle.getText().isEmpty())
			   {
				   return true;
			   }
			   else return false;
			}, tf_MovieTitle.textProperty());
		tf_MovieCopies.disableProperty().bind(tf_MovieCopiesD.or(tf_MovieTitleD));
		
		BooleanBinding cbo_RatingD = Bindings.createBooleanBinding(() -> {
			   if(tf_MovieCopies.getText().isEmpty())
			   {
				   return true;
			   }
			   else return false;
			}, tf_MovieCopies.textProperty());
		cbo_Rating.disableProperty().bind(tf_MovieCopiesD.or(tf_MovieTitleD).or(cbo_RatingD));
		
		BooleanBinding tf_GameTitleD = Bindings.createBooleanBinding(() -> {
			   if(tf_GameCode.getText().isEmpty())
			   {
				   return true;
			   }
			   else return false;
			}, tf_GameCode.textProperty());
		tf_GameTitle.disableProperty().bind((tf_GameTitleD));
		
		BooleanBinding tf_GameCopiesD = Bindings.createBooleanBinding(() -> {
			   if(tf_GameTitle.getText().isEmpty())
			   {
				   return true;
			   }
			   else return false;
			}, tf_GameTitle.textProperty());
		tf_GameCopies.disableProperty().bind(tf_GameCopiesD.or(tf_GameTitleD));
		
		BooleanBinding tf_WeightD = Bindings.createBooleanBinding(() -> {
			   if(tf_GameCopies.getText().isEmpty())
			   {
				   return true;
			   }
			   else return false;
			}, tf_GameCopies.textProperty());
		tf_Weight.disableProperty().bind(tf_GameTitleD.or(tf_GameCopiesD).or(tf_WeightD));
		
		BooleanBinding tf_AlbumTitleD = Bindings.createBooleanBinding(() -> {
			   if(tf_AlbumCode.getText().isEmpty())
			   {
				   return true;
			   }
			   else return false;
			}, tf_AlbumCode.textProperty());
		tf_AlbumTitle.disableProperty().bind(tf_AlbumTitleD);
		
		BooleanBinding tf_AlbumCopiesD = Bindings.createBooleanBinding(() -> {
			   if(tf_AlbumTitle.getText().isEmpty())
			   {
				   return true;
			   }
			   else return false;
			}, tf_AlbumTitle.textProperty());
		tf_AlbumCopies.disableProperty().bind(tf_AlbumCopiesD.or(tf_AlbumTitleD));
		
		BooleanBinding tf_ArtistD = Bindings.createBooleanBinding(() -> {
			   if(tf_AlbumCopies.getText().isEmpty())
			   {
				   return true;
			   }
			   else return false;
			}, tf_AlbumCopies.textProperty());
		tf_Artist.disableProperty().bind(tf_AlbumCopiesD.or(tf_AlbumTitleD).or(tf_ArtistD));
		
		BooleanBinding tf_SongsD = Bindings.createBooleanBinding(() -> {
			   if(tf_Artist.getText().isEmpty())
			   {
				   return true;
			   }
			   else return false;
			}, tf_Artist.textProperty());
		tf_Songs.disableProperty().bind(tf_SongsD.or(tf_ArtistD).or(tf_AlbumCopiesD).or(tf_AlbumTitleD));
		
		Button bt_BackMedia = new Button("Back" , new ImageView("back.png"));
		bt_BackMedia.setTextFill(Color.RED);
		bt_BackMedia.setStyle("-fx-background-color: black; -fx-border-color: red");
		Button bt_BackMovie = new Button("Back", new ImageView("back.png"));
		bt_BackMovie.setTextFill(Color.RED);
		bt_BackMovie.setStyle("-fx-background-color: black; -fx-border-color: red");
		Button bt_AddMovie = new Button("Add" , new ImageView("add.png"));
		bt_AddMovie.setTextFill(Color.RED);
		bt_AddMovie.setStyle("-fx-background-color: black; -fx-border-color: red");
		Button bt_BackGame = new Button("Back", new ImageView("back.png"));
		bt_BackGame.setTextFill(Color.RED);
		bt_BackGame.setStyle("-fx-background-color: black; -fx-border-color: red");
		Button bt_AddGame = new Button("Add" , new ImageView("add.png"));
		bt_AddGame.setTextFill(Color.RED);
		bt_AddGame.setStyle("-fx-background-color: black; -fx-border-color: red");
		Button bt_BackAlbum = new Button("Back", new ImageView("back.png"));
		bt_BackAlbum.setTextFill(Color.RED);
		bt_BackAlbum.setStyle("-fx-background-color: black; -fx-border-color: red");
		Button bt_AddAlbum= new Button("Add" , new ImageView("add.png"));
		bt_AddAlbum.setTextFill(Color.RED);
		bt_AddAlbum.setStyle("-fx-background-color: black; -fx-border-color: red");
		Button bt_select = new Button("Select");
		bt_select.setTextFill(Color.RED);
		bt_select.setStyle("-fx-background-color: black; -fx-border-color: red");
		
		HBox ButtonPane = new HBox(30);
		ButtonPane.setPadding(new Insets(40,40,40,40));
		ButtonPane.getChildren().addAll(bt_BackMedia);
		ButtonPane.setAlignment(Pos.BOTTOM_LEFT);
		ButtonPane.setStyle("-fx-background-color: black");
		
		HBox HPane1 = new HBox(30);
		HPane1.setAlignment(Pos.CENTER);
		HPane1.getChildren().addAll(lb_MediaType, cbo_MediaType, bt_select);
		StackPane MainPane = new StackPane();
		MainPane.getChildren().add(HPane1);
		MainPane.setStyle("-fx-background-color: black");
		
		BorderPane pane = new BorderPane();
		pane.setTop(labelPane1);
		pane.setCenter(MainPane);
		pane.setBottom(ButtonPane);
		
		HBox ButtonPaneMovie = new HBox(30);
		ButtonPaneMovie.setAlignment(Pos.CENTER);
		ButtonPaneMovie.getChildren().addAll(bt_BackMovie, bt_AddMovie, tf_MovieStatus);
		ButtonPaneMovie.setPadding(new Insets(40,40,40,40));
		ButtonPaneMovie.setStyle("-fx-background-color: black");
		
		HBox ButtonPaneGame = new HBox(30);
		ButtonPaneGame.setAlignment(Pos.CENTER);
		ButtonPaneGame.getChildren().addAll(bt_BackGame, bt_AddGame, tf_GameStatus);
		ButtonPaneGame.setPadding(new Insets(40,40,40,40));
		ButtonPaneGame.setStyle("-fx-background-color: black");
		
		HBox ButtonPaneAlbum = new HBox(30);
		ButtonPaneAlbum.setAlignment(Pos.CENTER);
		ButtonPaneAlbum.getChildren().addAll(bt_BackAlbum, bt_AddAlbum, tf_AlbumStatus);
		ButtonPaneAlbum.setPadding(new Insets(40,40,40,40));
		ButtonPaneAlbum.setStyle("-fx-background-color: black");
		
		GridPane gpane1 = new GridPane();
		gpane1.setAlignment(Pos.CENTER);
		gpane1.setHgap(30);
		gpane1.setVgap(30);
		gpane1.add(lb_MovieCode, 0, 0);
		gpane1.add(tf_MovieCode, 1, 0);
		gpane1.add(lb_MovieTitle, 0, 1);
		gpane1.add(tf_MovieTitle, 1, 1);
		gpane1.add(lb_MovieCopies, 0, 2);
		gpane1.add(tf_MovieCopies, 1, 2);
		gpane1.add(lb_Rating, 0, 3);
		gpane1.add(cbo_Rating, 1, 3);
		BorderPane MovieBPane = new BorderPane();
		MovieBPane.setTop(labelPane2);
		MovieBPane.setCenter(gpane1);
		MovieBPane.setBottom(ButtonPaneMovie);
		
		StackPane MoviePane = new StackPane();
		MoviePane.getChildren().add(MovieBPane);
		MoviePane.setStyle("-fx-background-color: black");
		
		GridPane gpane2 = new GridPane();
		gpane2.setAlignment(Pos.CENTER);
		gpane2.setHgap(30);
		gpane2.setVgap(30);
		gpane2.add(lb_GameCode, 0, 1);
		gpane2.add(tf_GameCode, 1, 1);
		gpane2.add(lb_GameTitle, 0, 2);
		gpane2.add(tf_GameTitle, 1, 2);
		gpane2.add(lb_GameCopies, 0, 3);
		gpane2.add(tf_GameCopies, 1, 3);
		gpane2.add(lb_Weight, 0, 4);
		gpane2.add(tf_Weight, 1, 4);
		
		BorderPane GameBPane = new BorderPane();
		GameBPane.setTop(labelPane3);
		GameBPane.setCenter(gpane2);
		GameBPane.setBottom(ButtonPaneGame);
		
		StackPane GamePane = new StackPane();
		GamePane.getChildren().add(GameBPane);
		GamePane.setStyle("-fx-background-color: black");
		
		
		GridPane gpane3 = new GridPane();
		gpane3.setAlignment(Pos.CENTER);
		gpane3.setHgap(30);
		gpane3.setVgap(30);
		gpane3.add(lb_AlbumCode, 0, 1);
		gpane3.add(tf_AlbumCode, 1, 1);
		gpane3.add(lb_AlbumTitle, 0, 2);
		gpane3.add(tf_AlbumTitle, 1, 2);
		gpane3.add(lb_AlbumCopies, 0, 3);
		gpane3.add(tf_AlbumCopies, 1, 3);
		gpane3.add(lb_Artist, 0, 4);
		gpane3.add(tf_Artist, 1, 4);
		gpane3.add(lb_Songs, 0, 5);
		gpane3.add(tf_Songs, 1, 5);
		BorderPane AlbumBPane = new BorderPane();
		AlbumBPane.setTop(labelPane4);
		AlbumBPane.setCenter(gpane3);
		AlbumBPane.setBottom(ButtonPaneAlbum);
		
		StackPane AlbumPane = new StackPane();
		AlbumPane.getChildren().add(AlbumBPane);
		AlbumPane.setStyle("-fx-background-color: black");
		
		
		bt_AddMovie.setOnAction(e ->{
			boolean flag = true;
			for(int i=0; i<m.mList.size(); i++)
			{
				if(m.mList.get(i).getCode().equals(tf_MovieCode.getText()))
				{
					flag = false;
					break;
				}
			}
			if(flag)
			{
				m.addMovie(tf_MovieTitle.getText(), Integer.parseInt(tf_MovieCopies.getText()), cbo_Rating.getValue().toString(), tf_MovieCode.getText());
				try {
					FileWriter mediaOut = new FileWriter(media,false);
					mediaOut.write(m.getAllMediaInfo());
					mediaOut.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				tf_MovieStatus.setText("Added Successfully.");
			}
			else {
				tf_MovieStatus.setText("Code Already Exists.");
			}
			tf_MovieTitle.setText("");
			tf_MovieCopies.setText("");
			cbo_Rating.setValue("");
			tf_MovieCode.setText("");
		});
		
		bt_AddGame.setOnAction(e ->{
			boolean flag = true;
			for(int i=0; i<m.mList.size(); i++)
			{
				if(m.mList.get(i).getCode().equals(tf_GameCode.getText()))
				{
					flag = false;
					break;
				}
			}
			if(flag)
			{
				m.addGame(tf_GameTitle.getText(), Integer.parseInt(tf_GameCopies.getText()), Double.parseDouble(tf_Weight.getText()), tf_GameCode.getText());
				try {
					FileWriter mediaOut = new FileWriter(media,false);
					mediaOut.write(m.getAllMediaInfo());
					mediaOut.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				tf_GameStatus.setText("Added Successfully.");
			}
			else {
				tf_GameStatus.setText("Code Already Exists.");
			}
			
			tf_GameTitle.setText("");
			tf_GameCopies.setText("");
			tf_Weight.setText("");
			tf_GameCode.setText("");
		});
		
		bt_AddAlbum.setOnAction(e ->{
			boolean flag = true;
			for(int i=0; i<m.mList.size(); i++)
			{
				if(m.mList.get(i).getCode().equals(tf_AlbumCode.getText()))
				{
					flag = false;
					break;
				}
			}
			if(flag)
			{
				m.addAlbum(tf_AlbumTitle.getText(), Integer.parseInt(tf_AlbumCopies.getText()), tf_Artist.getText(), tf_Songs.getText(), tf_AlbumCode.getText());
				try {
					FileWriter mediaOut = new FileWriter(media,false);
					mediaOut.write(m.getAllMediaInfo());
					mediaOut.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				tf_AlbumStatus.setText("Added Successfully.");
			}
			else {
				tf_AlbumStatus.setText("Code Already Exists.");
			}
			tf_AlbumTitle.setText("");
			tf_AlbumCopies.setText("");
			tf_Artist.setText("");
			tf_Songs.setText("");
			tf_AlbumCode.setText("");
		});
		
		bt_BackMedia.setOnAction(e ->{
			cbo_MediaType.setValue("");
			scene.setRoot(pane3);
			primaryStage.setScene(scene);
		});
		
		bt_BackMovie.setOnAction(e -> {
			tf_MovieStatus.setText("");
			tf_MovieTitle.setText("");
			tf_MovieCopies.setText("");
			cbo_Rating.setValue("");
			tf_MovieCode.setText("");
			cbo_MediaType.setValue("");
			scene.setRoot(pane);
			primaryStage.setScene(scene);
		});
		
		bt_BackGame.setOnAction(e ->{
			tf_GameStatus.setText("");
			tf_GameTitle.setText("");
			tf_GameCopies.setText("");
			tf_Weight.setText("");
			tf_GameCode.setText("");
			cbo_MediaType.setValue("");
			scene.setRoot(pane);
			primaryStage.setScene(scene);
		});
		
		bt_BackAlbum.setOnAction(e ->{
			tf_AlbumStatus.setText("");
			tf_AlbumTitle.setText("");
			tf_AlbumCopies.setText("");
			tf_Artist.setText("");
			tf_Songs.setText("");
			tf_AlbumCode.setText("");
			cbo_MediaType.setValue("");
			scene.setRoot(pane);
			primaryStage.setScene(scene);
		});
		
		bt_AddMedia.setOnAction(e ->{
			scene.setRoot(pane);
			primaryStage.setScene(scene);
		});
		
		
		bt_select.setOnAction(e -> {
			if(cbo_MediaType.getValue().equals("Movie"))
			{
				scene.setRoot(MoviePane);
				primaryStage.setScene(scene);
	
			}
			else if(cbo_MediaType.getValue().equals("Game"))
			{
				scene.setRoot(GamePane);
				primaryStage.setScene(scene);
			}
			else if(cbo_MediaType.getValue().equals("Album")){
				scene.setRoot(AlbumPane);
				primaryStage.setScene(scene);
			}
			
				
		});
		
	}
	
	public static void DeleteMedia(Stage primaryStage, Scene scene , Pane pane3, Button bt_DeleteMedia)
	{
		File media = new File("Media.txt");
		ArrayList <String> MediaType = new ArrayList();
		MediaType.add("Movie");
		MediaType.add("Game");
		MediaType.add("Album");
		ComboBox<String> cbo_MediaType = new ComboBox(FXCollections.observableArrayList(MediaType));
		cbo_MediaType.setStyle("-fx-background-color: black; -fx-border-color: red; -fx-font-size: 15pt" );
		Label lb_MediaType = new Label("Media Type");
		lb_MediaType.setTextFill(Color.RED);
		Label lb_MovieTitle = new Label("Movie Title");
		lb_MovieTitle.setTextFill(Color.RED);
		Label lb_GameTitle = new Label("Game Title");
		lb_GameTitle.setTextFill(Color.RED);
		Label lb_AlbumTitle = new Label("Album Title");
		lb_AlbumTitle.setTextFill(Color.RED);
		Label lb_AlbumCopies = new Label("Copies Available");
		lb_AlbumCopies.setTextFill(Color.RED);
		Label lb_GameCopies = new Label("Copies Available");
		lb_GameCopies.setTextFill(Color.RED);
		Label lb_MovieCopies = new Label("Copies Available");
		lb_MovieCopies.setTextFill(Color.RED);
		Label lb_Weight = new Label("Weight In Grams");
		lb_Weight.setTextFill(Color.RED);
		Label lb_Rating = new Label("Rating");
		lb_Rating.setTextFill(Color.RED);
		Label lb_Artist = new Label("Artist Name");
		lb_Artist.setTextFill(Color.RED);
		Label lb_Songs = new Label("Songs \n(Seperated by commas)");
		lb_Songs.setTextFill(Color.RED);
		Label lb_AlbumCode = new Label("Album Code");
		lb_AlbumCode.setTextFill(Color.RED);
		Label lb_GameCode = new Label("Game Code");
		lb_GameCode.setTextFill(Color.RED);
		Label lb_MovieCode = new Label("Movie Code");
		lb_MovieCode.setTextFill(Color.RED);
		Label lb_DeleteMedia = new Label("Delete Media");
		Label lb_DeleteMovie = new Label("Delete Movie");
		Label lb_DeleteGame = new Label("Delete Game");
		Label lb_DeleteAlbum = new Label("Delete Album");
	
		lb_DeleteMedia.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
		lb_DeleteMedia.setTextFill(Color.RED);
		lb_DeleteMedia.setUnderline(true);
		HBox labelPane1 = new HBox();
		labelPane1.setAlignment(Pos.CENTER);
		labelPane1.getChildren().add(lb_DeleteMedia);
		labelPane1.setStyle("-fx-border-color: red; -fx-background-color: black");
		labelPane1.setPadding(new Insets(20,20,20,20));
		
		lb_DeleteMovie.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
		lb_DeleteMovie.setTextFill(Color.RED);
		lb_DeleteMovie.setUnderline(true);
		HBox labelPane2 = new HBox();
		labelPane2.setAlignment(Pos.CENTER);
		labelPane2.getChildren().add(lb_DeleteMovie);
		labelPane2.setStyle("-fx-border-color: red; -fx-background-color: black");
		labelPane2.setPadding(new Insets(20,20,20,20));
		
		lb_DeleteGame.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
		lb_DeleteGame.setTextFill(Color.RED);
		lb_DeleteGame.setUnderline(true);
		HBox labelPane3 = new HBox();
		labelPane3.setAlignment(Pos.CENTER);
		labelPane3.getChildren().add(lb_DeleteGame);
		labelPane3.setStyle("-fx-border-color: red; -fx-background-color: black");
		labelPane3.setPadding(new Insets(20,20,20,20));
		
		lb_DeleteAlbum.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
		lb_DeleteAlbum.setTextFill(Color.RED);
		lb_DeleteAlbum.setUnderline(true);
		HBox labelPane4 = new HBox();
		labelPane4.setAlignment(Pos.CENTER);
		labelPane4.getChildren().add(lb_DeleteAlbum);
		labelPane4.setStyle("-fx-border-color: red; -fx-background-color: black");
		labelPane4.setPadding(new Insets(20,20,20,20));
		
		lb_MediaType.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_MovieTitle.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_GameTitle.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_AlbumTitle.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_AlbumCopies.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_GameCopies.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_MovieCopies.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_Weight.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_Rating.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_Artist.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_Songs.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_AlbumCode.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_GameCode.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_MovieCode.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		TextField tf_AlbumTitle = new TextField();
		tf_AlbumTitle.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_AlbumTitle.setFont(Font.font(16));
		TextField tf_GameTitle = new TextField();
		tf_GameTitle.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_GameTitle.setFont(Font.font(16));
		TextField tf_MovieTitle = new TextField();
		tf_MovieTitle.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_MovieTitle.setFont(Font.font(16));
		TextField tf_AlbumCopies = new TextField();
		tf_AlbumCopies.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_AlbumCopies.setFont(Font.font(16));
		TextField tf_GameCopies = new TextField();
		tf_GameCopies.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_GameCopies.setFont(Font.font(16));
		TextField tf_MovieCopies = new TextField();
		tf_MovieCopies.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_MovieCopies.setFont(Font.font(16));
		TextField tf_Rating = new TextField();
		tf_Rating.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_Rating.setFont(Font.font(16));
		TextField tf_Weight = new TextField();
		tf_Weight.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_Weight.setFont(Font.font(16));
		TextField tf_Artist = new TextField();
		tf_Artist.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_Artist.setFont(Font.font(16));
		TextField tf_Songs = new TextField();
		tf_Songs.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_Songs.setFont(Font.font(16));
		TextField tf_AlbumCode = new TextField();
		tf_AlbumCode.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_AlbumCode.setFont(Font.font(16));
		TextField tf_GameCode = new TextField();
		tf_GameCode.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_GameCode.setFont(Font.font(16));
		TextField tf_MovieCode = new TextField();
		tf_MovieCode.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_MovieCode.setFont(Font.font(16));
		
		tf_AlbumTitle.setDisable(true);
		tf_GameTitle.setDisable(true);
		tf_MovieTitle.setDisable(true);
		tf_AlbumCopies.setDisable(true);
		tf_GameCopies.setDisable(true);
		tf_MovieCopies.setDisable(true);
		tf_Rating.setDisable(true);
		tf_Weight.setDisable(true);
		tf_Artist.setDisable(true);
		tf_Songs.setDisable(true);
		
		Button bt_BackMedia = new Button("Back" , new ImageView("back.png"));
		bt_BackMedia.setStyle("-fx-background-color: black; -fx-border-color: red");
		bt_BackMedia.setTextFill(Color.RED);
		Button bt_BackMovie = new Button("Back", new ImageView("back.png"));
		bt_BackMovie.setStyle("-fx-background-color: black; -fx-border-color: red");
		bt_BackMovie.setTextFill(Color.RED);
		Button bt_FindMovie = new Button("Find", new ImageView("find.png"));
		bt_FindMovie.setStyle("-fx-background-color: black; -fx-border-color: red");
		bt_FindMovie.setTextFill(Color.RED);
		Button bt_DeleteMovie = new Button("Delete" , new ImageView("delete.png"));
		bt_DeleteMovie.setStyle("-fx-background-color: black; -fx-border-color: red");
		bt_DeleteMovie.setTextFill(Color.RED);
		Button bt_BackGame = new Button("Back", new ImageView("back.png"));
		bt_BackGame.setStyle("-fx-background-color: black; -fx-border-color: red");
		bt_BackGame.setTextFill(Color.RED);
		Button bt_FindGame = new Button("Find", new ImageView("find.png"));
		bt_FindGame.setStyle("-fx-background-color: black; -fx-border-color: red");
		bt_FindGame.setTextFill(Color.RED);
		Button bt_DeleteGame = new Button("Delete" , new ImageView("delete.png"));
		bt_DeleteGame.setStyle("-fx-background-color: black; -fx-border-color: red");
		bt_DeleteGame.setTextFill(Color.RED);
		Button bt_BackAlbum = new Button("Back", new ImageView("back.png"));
		bt_BackAlbum.setStyle("-fx-background-color: black; -fx-border-color: red");
		bt_BackAlbum.setTextFill(Color.RED);
		Button bt_FindAlbum = new Button("Find", new ImageView("find.png"));
		bt_FindAlbum.setStyle("-fx-background-color: black; -fx-border-color: red");
		bt_FindAlbum.setTextFill(Color.RED);
		Button bt_DeleteAlbum= new Button("Delete" , new ImageView("delete.png"));
		bt_DeleteAlbum.setStyle("-fx-background-color: black; -fx-border-color: red");
		bt_DeleteAlbum.setTextFill(Color.RED);
		Button bt_select = new Button("Select");
		bt_select.setStyle("-fx-background-color: black; -fx-border-color: red");
		bt_select.setTextFill(Color.RED);
		
		HBox ButtonPane = new HBox(30);
		ButtonPane.setPadding(new Insets(40,40,40,40));
		ButtonPane.getChildren().addAll(bt_BackMedia);
		ButtonPane.setAlignment(Pos.BOTTOM_LEFT);
		ButtonPane.setStyle("-fx-background-color: black");
		
		HBox MainPane = new HBox(30);
		MainPane.setAlignment(Pos.CENTER);
		MainPane.getChildren().addAll(lb_MediaType, cbo_MediaType, bt_select);
		MainPane.setStyle("-fx-background-color: black");
		
		BorderPane pane = new BorderPane();
		pane.setTop(labelPane1);
		pane.setCenter(MainPane);
		pane.setBottom(ButtonPane);
		
		HBox ButtonPaneMovie = new HBox(30);
		ButtonPaneMovie.setAlignment(Pos.CENTER);
		ButtonPaneMovie.getChildren().addAll(bt_BackMovie, bt_FindMovie, bt_DeleteMovie);
		ButtonPaneMovie.setPadding(new Insets(40,40,40,40));
		ButtonPaneMovie.setStyle("-fx-background-color: black");
		
		HBox ButtonPaneGame = new HBox(30);
		ButtonPaneGame.setAlignment(Pos.CENTER);
		ButtonPaneGame.getChildren().addAll(bt_BackGame, bt_FindGame, bt_DeleteGame);
		ButtonPaneGame.setPadding(new Insets(40,40,40,40));
		ButtonPaneGame.setStyle("-fx-background-color: black");
		
		HBox ButtonPaneAlbum = new HBox(30);
		ButtonPaneAlbum.setAlignment(Pos.CENTER);
		ButtonPaneAlbum.getChildren().addAll(bt_BackAlbum, bt_FindAlbum, bt_DeleteAlbum);
		ButtonPaneAlbum.setPadding(new Insets(40,40,40,40));
		ButtonPaneAlbum.setStyle("-fx-background-color: black");
		
		GridPane gpane1 = new GridPane();
		gpane1.setAlignment(Pos.CENTER);
		gpane1.setHgap(30);
		gpane1.setVgap(30);
		gpane1.add(lb_MovieCode, 0, 0);
		gpane1.add(tf_MovieCode, 1, 0);
		gpane1.add(lb_MovieTitle, 0, 1);
		gpane1.add(tf_MovieTitle, 1, 1);
		gpane1.add(lb_MovieCopies, 0, 2);
		gpane1.add(tf_MovieCopies, 1, 2);
		gpane1.add(lb_Rating, 0, 3);
		gpane1.add(tf_Rating, 1, 3);
		BorderPane MovieBPane = new BorderPane();
		MovieBPane.setTop(labelPane2);
		MovieBPane.setCenter(gpane1);
		MovieBPane.setBottom(ButtonPaneMovie);
		StackPane MoviePane = new StackPane();
		MoviePane.getChildren().add(MovieBPane);
		MoviePane.setStyle("-fx-background-color: black");
		
		GridPane gpane2 = new GridPane();
		gpane2.setAlignment(Pos.CENTER);
		gpane2.setHgap(30);
		gpane2.setVgap(30);
		gpane2.add(lb_GameCode, 0, 1);
		gpane2.add(tf_GameCode, 1, 1);
		gpane2.add(lb_GameTitle, 0, 2);
		gpane2.add(tf_GameTitle, 1, 2);
		gpane2.add(lb_GameCopies, 0, 3);
		gpane2.add(tf_GameCopies, 1, 3);
		gpane2.add(lb_Weight, 0, 4);
		gpane2.add(tf_Weight, 1, 4);
		BorderPane GameBPane = new BorderPane();
		GameBPane.setTop(labelPane3);
		GameBPane.setCenter(gpane2);
		GameBPane.setBottom(ButtonPaneGame);
		StackPane GamePane = new StackPane();
		GamePane.getChildren().add(GameBPane);
		GamePane.setStyle("-fx-background-color: black");
		
		
		GridPane gpane3 = new GridPane();
		gpane3.setAlignment(Pos.CENTER);
		gpane3.setHgap(30);
		gpane3.setVgap(30);
		gpane3.add(lb_AlbumCode, 0, 1);
		gpane3.add(tf_AlbumCode, 1, 1);
		gpane3.add(lb_AlbumTitle, 0, 2);
		gpane3.add(tf_AlbumTitle, 1, 2);
		gpane3.add(lb_AlbumCopies, 0, 3);
		gpane3.add(tf_AlbumCopies, 1, 3);
		gpane3.add(lb_Artist, 0, 4);
		gpane3.add(tf_Artist, 1, 4);
		gpane3.add(lb_Songs, 0, 5);
		gpane3.add(tf_Songs, 1, 5);
		BorderPane AlbumBPane = new BorderPane();
		AlbumBPane.setTop(labelPane4);
		AlbumBPane.setCenter(gpane3);
		AlbumBPane.setBottom(ButtonPaneAlbum);
		StackPane AlbumPane = new StackPane();
		AlbumPane.getChildren().add(AlbumBPane);
		AlbumPane.setStyle("-fx-background-color: black");
		
		
		bt_FindMovie.setOnAction(e ->{
			for(int i=0; i<m.mList.size(); i++) 
			{
				if(m.mList.get(i).getCode().equals(tf_MovieCode.getText()))
				{
					tf_MovieTitle.setText(m.mList.get(i).getTitle());
					tf_MovieCopies.setText(m.mList.get(i).getCopiesAvailable() + "");
					tf_Rating.setText(((Movie)m.mList.get(i)).getRating());
				}
			}
			tf_MovieTitle.setDisable(false);
			tf_MovieCopies.setDisable(false);
			tf_Rating.setDisable(false);
			tf_MovieTitle.setEditable(false);
			tf_MovieCopies.setEditable(false);
			tf_Rating.setEditable(false);
		});
		
		bt_DeleteMovie.setOnAction(e ->{
			if(m.mList.size()==1)
			{
				if(m.mList.get(0).getCode().equals(tf_MovieCode.getText()))
				{
					m.mList.remove(0);
					try {
						FileWriter mediaOut = new FileWriter(media,false);
						mediaOut.write(m.getAllMediaInfo());
						mediaOut.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
			for(int i=0; i<m.mList.size(); i++) 
			{
				if(m.mList.get(i).getCode().equals(tf_MovieCode.getText()))
				{
					m.mList.remove(i);
					try {
						FileWriter mediaOut = new FileWriter(media,false);
						mediaOut.write(m.getAllMediaInfo());
						mediaOut.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
			tf_MovieCode.setText("");
			tf_MovieCopies.setText("");
			tf_MovieTitle.setText("");
			tf_Rating.setText("");
			tf_MovieTitle.setDisable(true);
			tf_MovieCopies.setDisable(true);
			tf_Rating.setDisable(true);
		});
		
		bt_BackMovie.setOnAction(e ->{
			tf_MovieCode.setText("");
			tf_MovieTitle.setText("");
			tf_MovieCopies.setText("");
			tf_Rating.setText("");
			tf_MovieTitle.setDisable(true);
			tf_MovieCopies.setDisable(true);
			tf_Rating.setDisable(true);
			scene.setRoot(pane);
			primaryStage.setScene(scene);
		});
		
		bt_FindGame.setOnAction(e ->{
			for(int i=0; i<m.mList.size(); i++) 
			{
				if(m.mList.get(i).getCode().equals(tf_GameCode.getText()))
				{
					tf_GameTitle.setText(m.mList.get(i).getTitle());
					tf_GameCopies.setText(m.mList.get(i).getCopiesAvailable() + "");
					tf_Weight.setText(((Game)m.mList.get(i)).getWeight()+"");
				}
			}
			tf_GameTitle.setDisable(false);
			tf_GameCopies.setDisable(false);
			tf_Weight.setDisable(false);
			tf_GameTitle.setEditable(false);
			tf_GameCopies.setEditable(false);
			tf_Weight.setEditable(false);
			
		});
		bt_DeleteGame.setOnAction(e ->{
			if(m.mList.size()==1)
			{
				if(m.mList.get(0).getCode().equals(tf_GameCode.getText()))
				{
					m.mList.remove(0);
					try {
						FileWriter mediaOut = new FileWriter(media,false);
						mediaOut.write(m.getAllMediaInfo());
						mediaOut.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
			for(int i=0; i<m.mList.size(); i++)
			{
				if(m.mList.get(i).getCode().equals(tf_GameCode.getText()))
				{
					m.mList.remove(i);
					try {
						FileWriter mediaOut = new FileWriter(media,false);
						mediaOut.write(m.getAllMediaInfo());
						mediaOut.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
			tf_GameTitle.setText("");
			tf_GameCopies.setText("");
			tf_Weight.setText("");
			tf_GameCode.setText("");
			tf_GameTitle.setDisable(true);
			tf_GameCopies.setDisable(true);
			tf_Weight.setDisable(true);
		});
		bt_BackGame.setOnAction(e ->{
			tf_GameTitle.setText("");
			tf_GameCopies.setText("");
			tf_Weight.setText("");
			tf_GameCode.setText("");
			tf_GameTitle.setDisable(true);
			tf_GameCopies.setDisable(true);
			tf_Weight.setDisable(true);
			scene.setRoot(pane);
			primaryStage.setScene(scene);
		});
		
		bt_FindAlbum.setOnAction(e ->{
			for(int i=0; i<m.mList.size(); i++)
			{
				if(m.mList.get(i).getCode().equals(tf_AlbumCode.getText()))
				{
					tf_AlbumCopies.setText(m.mList.get(i).getCopiesAvailable()+"");
					tf_AlbumTitle.setText(m.mList.get(i).getTitle());
					tf_Artist.setText(((Album)m.mList.get(i)).getArtist());
					tf_Songs.setText(((Album)m.mList.get(i)).getSongs());
				}
			}
			tf_AlbumCopies.setDisable(false);
			tf_AlbumTitle.setDisable(false);
			tf_Artist.setDisable(false);
			tf_Songs.setDisable(false);
			tf_AlbumCopies.setEditable(false);
			tf_AlbumTitle.setEditable(false);
			tf_Artist.setEditable(false);
			tf_Songs.setEditable(false);
		});
		
		bt_DeleteAlbum.setOnAction(e ->{
			if(m.mList.size()==1)
			{
				if(m.mList.get(0).getCode().equals(tf_AlbumCode.getText()))
				{
					m.mList.remove(0);
					try {
						FileWriter mediaOut = new FileWriter(media,false);
						mediaOut.write(m.getAllMediaInfo());
						mediaOut.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
			for(int i=0; i<m.mList.size(); i++)
			{
				if(m.mList.get(i).getCode().equals(tf_AlbumCode.getText()))
				{
					m.mList.remove(i);
					try {
						FileWriter mediaOut = new FileWriter(media,false);
						mediaOut.write(m.getAllMediaInfo());
						mediaOut.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
			tf_AlbumCopies.setText("");
			tf_AlbumTitle.setText("");
			tf_Artist.setText("");
			tf_Songs.setText("");
			tf_AlbumCode.setText("");
			tf_AlbumCopies.setDisable(true);
			tf_AlbumTitle.setDisable(true);
			tf_Artist.setDisable(true);
			tf_Songs.setDisable(true);
		});
		
		bt_BackAlbum.setOnAction(e ->{
			tf_AlbumCopies.setText("");
			tf_AlbumTitle.setText("");
			tf_Artist.setText("");
			tf_Songs.setText("");
			tf_AlbumCode.setText("");
			tf_AlbumCopies.setDisable(true);
			tf_AlbumTitle.setDisable(true);
			tf_Artist.setDisable(true);
			tf_Songs.setDisable(true);
			scene.setRoot(pane);
			primaryStage.setScene(scene);
		});
		
		bt_BackMedia.setOnAction(e ->{
			cbo_MediaType.setValue("");
			scene.setRoot(pane3);
			primaryStage.setScene(scene);
		});
	
		
		bt_DeleteMedia.setOnAction(e ->{
			scene.setRoot(pane);
			primaryStage.setScene(scene);
		});
		
		
		bt_select.setOnAction(e -> {
			if(cbo_MediaType.getValue().equals("Movie"))
			{
				scene.setRoot(MoviePane);
				primaryStage.setScene(scene);
	
			}
			else if(cbo_MediaType.getValue().equals("Game"))
			{
				scene.setRoot(GamePane);
				primaryStage.setScene(scene);
			}
			else if(cbo_MediaType.getValue().equals("Album")){
				scene.setRoot(AlbumPane);
				primaryStage.setScene(scene);
			}
			cbo_MediaType.setValue("");
		});
	}
	
	public static void UpdateMedia(Stage primaryStage, Scene scene, Pane pane3, Button bt_UpdateMedia)
	{
		File media = new File("Media.txt");
		ArrayList<String> Rating = new ArrayList();
		Rating.add("HR");
		Rating.add("DR");
		Rating.add("AC");
		ComboBox<String> cbo_Rating = new ComboBox(FXCollections.observableArrayList(Rating));
		cbo_Rating.setStyle("-fx-background-color: black; -fx-border-color: red; -fx-font-size: 15pt");
		ArrayList <String> MediaType = new ArrayList();
		MediaType.add("Movie");
		MediaType.add("Game");
		MediaType.add("Album");
		ComboBox<String> cbo_MediaType = new ComboBox(FXCollections.observableArrayList(MediaType));
		cbo_MediaType.setStyle("-fx-background-color: black; -fx-border-color: red; -fx-font-size: 15pt");
		Label lb_MediaType = new Label("Media Type");
		lb_MediaType.setTextFill(Color.RED);
		Label lb_MovieTitle = new Label("Movie Title");
		lb_MovieTitle.setTextFill(Color.RED);
		Label lb_GameTitle = new Label("Game Title");
		lb_GameTitle.setTextFill(Color.RED);
		Label lb_AlbumTitle = new Label("Album Title");
		lb_AlbumTitle.setTextFill(Color.RED);
		Label lb_AlbumCopies = new Label("Copies Available");
		lb_AlbumCopies.setTextFill(Color.RED);
		Label lb_GameCopies = new Label("Copies Available");
		lb_GameCopies.setTextFill(Color.RED);
		Label lb_MovieCopies = new Label("Copies Available");
		lb_MovieCopies.setTextFill(Color.RED);
		Label lb_Weight = new Label("Weight In Grams");
		lb_Weight.setTextFill(Color.RED);
		Label lb_Rating = new Label("Rating");
		lb_Rating.setTextFill(Color.RED);
		Label lb_Artist = new Label("Artist Name");
		lb_Artist.setTextFill(Color.RED);
		Label lb_Songs = new Label("Songs \n(Seperated by commas)");
		lb_Songs.setTextFill(Color.RED);
		Label lb_AlbumCode = new Label("Album Code");
		lb_AlbumCode.setTextFill(Color.RED);
		Label lb_GameCode = new Label("Game Code");
		lb_GameCode.setTextFill(Color.RED);
		Label lb_MovieCode = new Label("Movie Code");
		lb_MovieCode.setTextFill(Color.RED);
		Label lb_UpdateMedia = new Label("Update Media");
		Label lb_UpdateMovie = new Label("Update Movie");
		Label lb_UpdateGame = new Label("Update Game");
		Label lb_UpdateAlbum = new Label("Update Album");
	
		lb_UpdateMedia.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
		lb_UpdateMedia.setTextFill(Color.RED);
		lb_UpdateMedia.setUnderline(true);
		HBox labelPane1 = new HBox();
		labelPane1.setAlignment(Pos.CENTER);
		labelPane1.getChildren().add(lb_UpdateMedia);
		labelPane1.setStyle("-fx-border-color: red; -fx-background-color: black");
		labelPane1.setPadding(new Insets(20,20,20,20));
		
		lb_UpdateMovie.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
		lb_UpdateMovie.setTextFill(Color.RED);
		lb_UpdateMovie.setUnderline(true);
		HBox labelPane2 = new HBox();
		labelPane2.setAlignment(Pos.CENTER);
		labelPane2.getChildren().add(lb_UpdateMovie);
		labelPane2.setStyle("-fx-border-color: red; -fx-background-color: black");
		labelPane2.setPadding(new Insets(20,20,20,20));
		
		lb_UpdateGame.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
		lb_UpdateGame.setTextFill(Color.RED);
		lb_UpdateGame.setUnderline(true);
		HBox labelPane3 = new HBox();
		labelPane3.setAlignment(Pos.CENTER);
		labelPane3.getChildren().add(lb_UpdateGame);
		labelPane3.setStyle("-fx-border-color: red; -fx-background-color: black");
		labelPane3.setPadding(new Insets(20,20,20,20));
		
		lb_UpdateAlbum.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
		lb_UpdateAlbum.setTextFill(Color.RED);
		lb_UpdateAlbum.setUnderline(true);
		HBox labelPane4 = new HBox();
		labelPane4.setAlignment(Pos.CENTER);
		labelPane4.getChildren().add(lb_UpdateAlbum);
		labelPane4.setStyle("-fx-border-color: red; -fx-background-color: black");
		labelPane4.setPadding(new Insets(20,20,20,20));
		
		lb_MediaType.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_MovieTitle.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_GameTitle.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_AlbumTitle.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_AlbumCopies.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_GameCopies.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_MovieCopies.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_Weight.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_Rating.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_Artist.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_Songs.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_AlbumCode.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_GameCode.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_MovieCode.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		TextField tf_AlbumTitle = new TextField();
		tf_AlbumTitle.setStyle("-fx-background-color: black; -fx-border-color: red; -fx-text-fill: red;");
		tf_AlbumTitle.setFont(Font.font(16));
		TextField tf_GameTitle = new TextField();
		tf_GameTitle.setStyle("-fx-background-color: black; -fx-border-color: red; -fx-text-fill: red;");
		tf_GameTitle.setFont(Font.font(16));
		TextField tf_MovieTitle = new TextField();
		tf_MovieTitle.setStyle("-fx-background-color: black; -fx-border-color: red; -fx-text-fill: red;");
		tf_MovieTitle.setFont(Font.font(16));
		TextField tf_AlbumCopies = new TextField();
		tf_AlbumCopies.setStyle("-fx-background-color: black; -fx-border-color: red; -fx-text-fill: red;");
		tf_AlbumCopies.setFont(Font.font(16));
		TextField tf_GameCopies = new TextField();
		tf_GameCopies.setStyle("-fx-background-color: black; -fx-border-color: red; -fx-text-fill: red;");
		tf_GameCopies.setFont(Font.font(16));
		TextField tf_MovieCopies = new TextField();
		tf_MovieCopies.setStyle("-fx-background-color: black; -fx-border-color: red; -fx-text-fill: red;");
		tf_MovieCopies.setFont(Font.font(16));
		TextField tf_Weight = new TextField();
		tf_Weight.setStyle("-fx-background-color: black; -fx-border-color: red; -fx-text-fill: red;");
		tf_Weight.setFont(Font.font(16));
		TextField tf_Artist = new TextField();
		tf_Artist.setStyle("-fx-background-color: black; -fx-border-color: red; -fx-text-fill: red;");
		tf_Artist.setFont(Font.font(16));
		TextField tf_Songs = new TextField();
		tf_Songs.setStyle("-fx-background-color: black; -fx-border-color: red; -fx-text-fill: red;");
		tf_Songs.setFont(Font.font(16));
		TextField tf_AlbumCode = new TextField();
		tf_AlbumCode.setStyle("-fx-background-color: black; -fx-border-color: red; -fx-text-fill: red;");
		tf_AlbumCode.setFont(Font.font(16));
		TextField tf_GameCode = new TextField();
		tf_GameCode.setStyle("-fx-background-color: black; -fx-border-color: red; -fx-text-fill: red;");
		tf_GameCode.setFont(Font.font(16));
		TextField tf_MovieCode = new TextField();
		tf_MovieCode.setStyle("-fx-background-color: black; -fx-border-color: red; -fx-text-fill: red;");
		tf_MovieCode.setFont(Font.font(16));
		
		tf_AlbumTitle.setDisable(true);
		tf_GameTitle.setDisable(true);
		tf_MovieTitle.setDisable(true);
		tf_AlbumCopies.setDisable(true);
		tf_GameCopies.setDisable(true);
		tf_MovieCopies.setDisable(true);
		cbo_Rating.setDisable(true);
		tf_Weight.setDisable(true);
		tf_Artist.setDisable(true);
		tf_Songs.setDisable(true);
		
		Button bt_BackMedia = new Button("Back" , new ImageView("back.png"));
		bt_BackMedia.setStyle("-fx-background-color: black; -fx-border-color: red;");
		bt_BackMedia.setTextFill(Color.RED);
		Button bt_BackMovie = new Button("Back", new ImageView("back.png"));
		bt_BackMovie.setStyle("-fx-background-color: black; -fx-border-color: red;");
		bt_BackMovie.setTextFill(Color.RED);
		Button bt_FindMovie = new Button("Find", new ImageView("find.png"));
		bt_FindMovie.setStyle("-fx-background-color: black; -fx-border-color: red;");
		bt_FindMovie.setTextFill(Color.RED);
		Button bt_UpdateMovie = new Button("Update" , new ImageView("update.png"));
		bt_UpdateMovie.setStyle("-fx-background-color: black; -fx-border-color: red;");
		bt_UpdateMovie.setTextFill(Color.RED);
		Button bt_BackGame = new Button("Back", new ImageView("back.png"));
		bt_BackGame.setStyle("-fx-background-color: black; -fx-border-color: red;");
		bt_BackGame.setTextFill(Color.RED);
		Button bt_FindGame = new Button("Find", new ImageView("find.png"));
		bt_FindGame.setStyle("-fx-background-color: black; -fx-border-color: red;");
		bt_FindGame.setTextFill(Color.RED);
		Button bt_UpdateGame = new Button("Update" , new ImageView("update.png"));
		bt_UpdateGame.setStyle("-fx-background-color: black; -fx-border-color: red;");
		bt_UpdateGame.setTextFill(Color.RED);
		Button bt_BackAlbum = new Button("Back", new ImageView("back.png"));
		bt_BackAlbum.setStyle("-fx-background-color: black; -fx-border-color: red;");
		bt_BackAlbum.setTextFill(Color.RED);
		Button bt_FindAlbum = new Button("Find", new ImageView("find.png"));
		bt_FindAlbum.setStyle("-fx-background-color: black; -fx-border-color: red;");
		bt_FindAlbum.setTextFill(Color.RED);
		Button bt_UpdateAlbum= new Button("Update" , new ImageView("update.png"));
		bt_UpdateAlbum.setStyle("-fx-background-color: black; -fx-border-color: red;");
		bt_UpdateAlbum.setTextFill(Color.RED);
		Button bt_select = new Button("Select");
		bt_select.setStyle("-fx-background-color: black; -fx-border-color: red;");
		bt_select.setTextFill(Color.RED);
		
		HBox ButtonPane = new HBox(30);
		ButtonPane.setPadding(new Insets(40,40,40,40));
		ButtonPane.getChildren().addAll(bt_BackMedia);
		ButtonPane.setAlignment(Pos.BOTTOM_LEFT);
		ButtonPane.setStyle("-fx-background-color: black");
		
		HBox MainPane = new HBox(30);
		MainPane.setAlignment(Pos.CENTER);
		MainPane.getChildren().addAll(lb_MediaType, cbo_MediaType, bt_select);
		MainPane.setStyle("-fx-background-color: black");
		
		BorderPane pane = new BorderPane();
		pane.setTop(labelPane1);
		pane.setCenter(MainPane);
		pane.setBottom(ButtonPane);
		
		HBox ButtonPaneMovie = new HBox(30);
		ButtonPaneMovie.setAlignment(Pos.CENTER);
		ButtonPaneMovie.getChildren().addAll(bt_BackMovie, bt_FindMovie, bt_UpdateMovie);
		ButtonPaneMovie.setPadding(new Insets(40,40,40,40));
		ButtonPaneMovie.setStyle("-fx-background-color: black");
		
		HBox ButtonPaneGame = new HBox(30);
		ButtonPaneGame.setAlignment(Pos.CENTER);
		ButtonPaneGame.getChildren().addAll(bt_BackGame, bt_FindGame, bt_UpdateGame);
		ButtonPaneGame.setPadding(new Insets(40,40,40,40));
		ButtonPaneGame.setStyle("-fx-background-color: black");
		
		HBox ButtonPaneAlbum = new HBox(30);
		ButtonPaneAlbum.setAlignment(Pos.CENTER);
		ButtonPaneAlbum.getChildren().addAll(bt_BackAlbum, bt_FindAlbum, bt_UpdateAlbum);
		ButtonPaneAlbum.setPadding(new Insets(40,40,40,40));
		ButtonPaneAlbum.setStyle("-fx-background-color: black");
		
		GridPane gpane1 = new GridPane();
		gpane1.setAlignment(Pos.CENTER);
		gpane1.setHgap(30);
		gpane1.setVgap(30);
		gpane1.add(lb_MovieCode, 0, 0);
		gpane1.add(tf_MovieCode, 1, 0);
		gpane1.add(lb_MovieTitle, 0, 1);
		gpane1.add(tf_MovieTitle, 1, 1);
		gpane1.add(lb_MovieCopies, 0, 2);
		gpane1.add(tf_MovieCopies, 1, 2);
		gpane1.add(lb_Rating, 0, 3);
		gpane1.add(cbo_Rating, 1, 3);
		BorderPane MovieBPane = new BorderPane();
		MovieBPane.setTop(labelPane2);
		MovieBPane.setCenter(gpane1);
		MovieBPane.setBottom(ButtonPaneMovie);
		StackPane MoviePane = new StackPane();
		MoviePane.getChildren().add(MovieBPane);
		MoviePane.setStyle("-fx-background-color: black");
		
		GridPane gpane2 = new GridPane();
		gpane2.setAlignment(Pos.CENTER);
		gpane2.setHgap(30);
		gpane2.setVgap(30);
		gpane2.add(lb_GameCode, 0, 1);
		gpane2.add(tf_GameCode, 1, 1);
		gpane2.add(lb_GameTitle, 0, 2);
		gpane2.add(tf_GameTitle, 1, 2);
		gpane2.add(lb_GameCopies, 0, 3);
		gpane2.add(tf_GameCopies, 1, 3);
		gpane2.add(lb_Weight, 0, 4);
		gpane2.add(tf_Weight, 1, 4);
		BorderPane GameBPane = new BorderPane();
		GameBPane.setTop(labelPane3);
		GameBPane.setCenter(gpane2);
		GameBPane.setBottom(ButtonPaneGame);
		StackPane GamePane = new StackPane();
		GamePane.getChildren().add(GameBPane);
		GamePane.setStyle("-fx-background-color: black");
		
		
		GridPane gpane3 = new GridPane();
		gpane3.setAlignment(Pos.CENTER);
		gpane3.setHgap(30);
		gpane3.setVgap(30);
		gpane3.add(lb_AlbumCode, 0, 1);
		gpane3.add(tf_AlbumCode, 1, 1);
		gpane3.add(lb_AlbumTitle, 0, 2);
		gpane3.add(tf_AlbumTitle, 1, 2);
		gpane3.add(lb_AlbumCopies, 0, 3);
		gpane3.add(tf_AlbumCopies, 1, 3);
		gpane3.add(lb_Artist, 0, 4);
		gpane3.add(tf_Artist, 1, 4);
		gpane3.add(lb_Songs, 0, 5);
		gpane3.add(tf_Songs, 1, 5);
		BorderPane AlbumBPane = new BorderPane();
		AlbumBPane.setTop(labelPane4);
		AlbumBPane.setCenter(gpane3);
		AlbumBPane.setBottom(ButtonPaneAlbum);
		StackPane AlbumPane = new StackPane();
		AlbumPane.getChildren().add(AlbumBPane);
		AlbumPane.setStyle("-fx-background-color: black");
		
		
		bt_FindMovie.setOnAction(e ->{
			for(int i=0; i<m.mList.size(); i++) 
			{
				if(m.mList.get(i).getCode().equals(tf_MovieCode.getText()))
				{
					tf_MovieTitle.setText(m.mList.get(i).getTitle());
					tf_MovieCopies.setText(m.mList.get(i).getCopiesAvailable() + "");
					cbo_Rating.setValue(((Movie)m.mList.get(i)).getRating());
				}
			}
			tf_MovieTitle.setDisable(false);
			tf_MovieCopies.setDisable(false);
			cbo_Rating.setDisable(false);
			tf_MovieCode.setDisable(true);
			tf_MovieTitle.setEditable(true);
			tf_MovieCopies.setEditable(true);
			cbo_Rating.setEditable(false);
			
		});
		
		bt_UpdateMovie.setOnAction(e ->{
			for(int i=0; i<m.mList.size(); i++) 
			{
				if(m.mList.get(i).getCode().equals(tf_MovieCode.getText()))
				{
					m.mList.get(i).setTitle(tf_MovieTitle.getText());
					m.mList.get(i).setCopiesAvailable(Integer.parseInt(tf_MovieCopies.getText()));
					((Movie)m.mList.get(i)).setRating(cbo_Rating.getValue().toString());
					try {
						FileWriter mediaOut = new FileWriter(media,false);
						mediaOut.write(m.getAllMediaInfo());
						mediaOut.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
				}
			}
			tf_MovieCode.setText("");
			tf_MovieCopies.setText("");
			tf_MovieTitle.setText("");
			cbo_Rating.setValue("");
			tf_MovieCode.setDisable(false);
			tf_MovieTitle.setDisable(true);
			tf_MovieCopies.setDisable(true);
			cbo_Rating.setDisable(true);
		});
		
		bt_BackMovie.setOnAction(e ->{
			tf_MovieCode.setText("");
			tf_MovieTitle.setText("");
			tf_MovieCopies.setText("");
			cbo_Rating.setValue("");
			tf_MovieCode.setDisable(false);
			tf_MovieTitle.setDisable(true);
			tf_MovieCopies.setDisable(true);
			cbo_Rating.setDisable(true);
			scene.setRoot(pane);
			primaryStage.setScene(scene);
		});
		
		bt_FindGame.setOnAction(e ->{
			for(int i=0; i<m.mList.size(); i++) 
			{
				if(m.mList.get(i).getCode().equals(tf_GameCode.getText()))
				{
					tf_GameTitle.setText(m.mList.get(i).getTitle());
					tf_GameCopies.setText(m.mList.get(i).getCopiesAvailable() + "");
					tf_Weight.setText(((Game)m.mList.get(i)).getWeight()+"");
				}
			}
			tf_GameTitle.setDisable(false);
			tf_GameCopies.setDisable(false);
			tf_Weight.setDisable(false);
			tf_GameCode.setDisable(true);
			tf_GameTitle.setEditable(true);
			tf_GameCopies.setEditable(true);
			tf_Weight.setEditable(true);
			
		});
		
		bt_UpdateGame.setOnAction(e ->{
			for(int i=0; i<m.mList.size(); i++)
			{
				if(m.mList.get(i).getCode().equals(tf_GameCode.getText()))
				{
					m.mList.get(i).setTitle(tf_GameTitle.getText());
					m.mList.get(i).setCopiesAvailable(Integer.parseInt(tf_GameCopies.getText()));
					((Game)m.mList.get(i)).setWeight(Double.parseDouble(tf_Weight.getText()));
					try {
						FileWriter mediaOut = new FileWriter(media,false);
						mediaOut.write(m.getAllMediaInfo());
						mediaOut.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
			tf_GameTitle.setText("");
			tf_GameCopies.setText("");
			tf_Weight.setText("");
			tf_GameCode.setText("");
			tf_GameCode.setDisable(false);
			tf_GameTitle.setDisable(true);
			tf_GameCopies.setDisable(true);
			tf_Weight.setDisable(true);
		});
		bt_BackGame.setOnAction(e ->{
			tf_GameTitle.setText("");
			tf_GameCopies.setText("");
			tf_Weight.setText("");
			tf_GameCode.setText("");
			tf_GameCode.setDisable(false);
			tf_GameTitle.setDisable(true);
			tf_GameCopies.setDisable(true);
			tf_Weight.setDisable(true);
			scene.setRoot(pane);
			primaryStage.setScene(scene);
		});
		
		bt_FindAlbum.setOnAction(e ->{
			for(int i=0; i<m.mList.size(); i++)
			{
				if(m.mList.get(i).getCode().equals(tf_AlbumCode.getText()))
				{
					tf_AlbumCopies.setText(m.mList.get(i).getCopiesAvailable()+"");
					tf_AlbumTitle.setText(m.mList.get(i).getTitle());
					tf_Artist.setText(((Album)m.mList.get(i)).getArtist());
					tf_Songs.setText(((Album)m.mList.get(i)).getSongs());
				}
			}
			tf_AlbumCopies.setDisable(false);
			tf_AlbumTitle.setDisable(false);
			tf_Artist.setDisable(false);
			tf_Songs.setDisable(false);
			tf_AlbumCode.setDisable(true);
			tf_AlbumCopies.setEditable(true);
			tf_AlbumTitle.setEditable(true);
			tf_Artist.setEditable(true);
			tf_Songs.setEditable(true);
		});
		
		bt_UpdateAlbum.setOnAction(e ->{
			for(int i=0; i<m.mList.size(); i++)
			{
				if(m.mList.get(i).getCode().equals(tf_AlbumCode.getText()))
				{
					m.mList.get(i).setTitle(tf_AlbumTitle.getText());
					m.mList.get(i).setCopiesAvailable(Integer.parseInt(tf_AlbumCopies.getText()));
					((Album)m.mList.get(i)).setArtist(tf_Artist.getText());
					((Album)m.mList.get(i)).setSongs(tf_Songs.getText());
					try {
						FileWriter mediaOut = new FileWriter(media,false);
						mediaOut.write(m.getAllMediaInfo());
						mediaOut.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}	
				}
			}
			tf_AlbumCopies.setText("");
			tf_AlbumTitle.setText("");
			tf_Artist.setText("");
			tf_Songs.setText("");
			tf_AlbumCode.setText("");
			tf_AlbumCode.setDisable(false);
			tf_AlbumCopies.setDisable(true);
			tf_AlbumTitle.setDisable(true);
			tf_Artist.setDisable(true);
			tf_Songs.setDisable(true);
		});
		
		bt_BackAlbum.setOnAction(e ->{
			tf_AlbumCopies.setText("");
			tf_AlbumTitle.setText("");
			tf_Artist.setText("");
			tf_Songs.setText("");
			tf_AlbumCode.setText("");
			tf_AlbumCode.setDisable(false);
			tf_AlbumCopies.setDisable(true);
			tf_AlbumTitle.setDisable(true);
			tf_Artist.setDisable(true);
			tf_Songs.setDisable(true);
			scene.setRoot(pane);
			primaryStage.setScene(scene);
		});
		
		bt_BackMedia.setOnAction(e ->{
			cbo_MediaType.setValue("");
			scene.setRoot(pane3);
			primaryStage.setScene(scene);
		});
	
		
		bt_UpdateMedia.setOnAction(e ->{
			scene.setRoot(pane);
			primaryStage.setScene(scene);
		});
		
		
		bt_select.setOnAction(e -> {
			if(cbo_MediaType.getValue().equals("Movie"))
			{
				scene.setRoot(MoviePane);
				primaryStage.setScene(scene);
	
			}
			else if(cbo_MediaType.getValue().equals("Game"))
			{
				scene.setRoot(GamePane);
				primaryStage.setScene(scene);
			}
			else if(cbo_MediaType.getValue().equals("Album")){
				scene.setRoot(AlbumPane);
				primaryStage.setScene(scene);
			}
			cbo_MediaType.setValue("");
		});
	}
	public static void SearchMedia(Stage primaryStage, Scene scene, Pane pane3, Button bt_SearchMedia)
	{
		ArrayList <String> MediaType = new ArrayList();
		MediaType.add("Movie");
		MediaType.add("Game");
		MediaType.add("Album");
		ComboBox<String> cbo_MediaType = new ComboBox(FXCollections.observableArrayList(MediaType));
		cbo_MediaType.setStyle("-fx-border-color: red; -fx-background-color: black; -fx-font-size: 15pt");
		Label lb_MediaType = new Label("Media Type");
		lb_MediaType.setTextFill(Color.RED);
		Label lb_MovieTitle = new Label("Movie Title");
		lb_MovieTitle.setTextFill(Color.RED);
		Label lb_GameTitle = new Label("Game Title");
		lb_GameTitle.setTextFill(Color.RED);
		Label lb_AlbumTitle = new Label("Album Title");
		lb_AlbumTitle.setTextFill(Color.RED);
		Label lb_AlbumCopies = new Label("Copies Available");
		lb_AlbumCopies.setTextFill(Color.RED);
		Label lb_GameCopies = new Label("Copies Available");
		lb_GameCopies.setTextFill(Color.RED);
		Label lb_MovieCopies = new Label("Copies Available");
		lb_MovieCopies.setTextFill(Color.RED);
		Label lb_Weight = new Label("Weight In Grams");
		lb_Weight.setTextFill(Color.RED);
		Label lb_Rating = new Label("Rating");
		lb_Rating.setTextFill(Color.RED);
		Label lb_Artist = new Label("Artist Name");
		lb_Artist.setTextFill(Color.RED);
		Label lb_Songs = new Label("Songs \n(Seperated by commas)");
		lb_Songs.setTextFill(Color.RED);
		Label lb_AlbumCode = new Label("Album Code");
		lb_AlbumCode.setTextFill(Color.RED);
		Label lb_GameCode = new Label("Game Code");
		lb_GameCode.setTextFill(Color.RED);
		Label lb_MovieCode = new Label("Movie Code");
		lb_MovieCode.setTextFill(Color.RED);
		Label lb_SearchMedia = new Label("Search Media");
		Label lb_SearchMovie = new Label("Search Movie");
		Label lb_SearchGame = new Label("Search Game");
		Label lb_SearchAlbum = new Label("Search Album");
	
		lb_SearchMedia.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
		lb_SearchMedia.setTextFill(Color.RED);
		lb_SearchMedia.setUnderline(true);
		HBox labelPane1 = new HBox();
		labelPane1.setAlignment(Pos.CENTER);
		labelPane1.getChildren().add(lb_SearchMedia);
		labelPane1.setStyle("-fx-background-color: black; -fx-border-color: red");
		labelPane1.setPadding(new Insets(20,20,20,20));
		
		lb_SearchMovie.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
		lb_SearchMovie.setTextFill(Color.RED);
		lb_SearchMovie.setUnderline(true);
		HBox labelPane2 = new HBox();
		labelPane2.setAlignment(Pos.CENTER);
		labelPane2.getChildren().add(lb_SearchMovie);
		labelPane2.setStyle("-fx-border-color: red; fx-background-color: black");
		labelPane2.setPadding(new Insets(20,20,20,20));
		
		lb_SearchGame.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
		lb_SearchGame.setTextFill(Color.RED);
		lb_SearchGame.setUnderline(true);
		HBox labelPane3 = new HBox();
		labelPane3.setAlignment(Pos.CENTER);
		labelPane3.getChildren().add(lb_SearchGame);
		labelPane3.setStyle("-fx-border-color: red; fx-background-color: black");
		labelPane3.setPadding(new Insets(20,20,20,20));
		
		lb_SearchAlbum.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
		lb_SearchAlbum.setTextFill(Color.RED);
		lb_SearchAlbum.setUnderline(true);
		HBox labelPane4 = new HBox();
		labelPane4.setAlignment(Pos.CENTER);
		labelPane4.getChildren().add(lb_SearchAlbum);
		labelPane4.setStyle("-fx-border-color: red; fx-background-color: black");
		labelPane4.setPadding(new Insets(20,20,20,20));
		
		lb_MediaType.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_MovieTitle.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_GameTitle.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_AlbumTitle.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_AlbumCopies.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_GameCopies.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_MovieCopies.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_Weight.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_Rating.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_Artist.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_Songs.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_AlbumCode.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_GameCode.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_MovieCode.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		TextField tf_AlbumTitle = new TextField();
		tf_AlbumTitle.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_AlbumTitle.setFont(Font.font(16));
		TextField tf_GameTitle = new TextField();
		tf_GameTitle.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_GameTitle.setFont(Font.font(16));
		TextField tf_MovieTitle = new TextField();
		tf_MovieTitle.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_MovieTitle.setFont(Font.font(16));
		TextField tf_AlbumCopies = new TextField();
		tf_AlbumCopies.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_AlbumCopies.setFont(Font.font(16));
		TextField tf_GameCopies = new TextField();
		tf_GameCopies.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_GameCopies.setFont(Font.font(16));
		TextField tf_MovieCopies = new TextField();
		tf_MovieCopies.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_MovieCopies.setFont(Font.font(16));
		TextField tf_Rating = new TextField();
		tf_Rating.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_Rating.setFont(Font.font(16));
		TextField tf_Weight = new TextField();
		tf_Weight.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_Weight.setFont(Font.font(16));
		TextField tf_Artist = new TextField();
		tf_Artist.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_Artist.setFont(Font.font(16));
		TextField tf_Songs = new TextField();
		tf_Songs.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_Songs.setFont(Font.font(16));
		TextField tf_AlbumCode = new TextField();
		tf_AlbumCode.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_AlbumCode.setFont(Font.font(16));
		TextField tf_GameCode = new TextField();
		tf_GameCode.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_GameCode.setFont(Font.font(16));
		TextField tf_MovieCode = new TextField();
		tf_MovieCode.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-border-color: red");
		tf_MovieCode.setFont(Font.font(16));
		
		tf_AlbumTitle.setDisable(true);
		tf_GameTitle.setDisable(true);
		tf_MovieTitle.setDisable(true);
		tf_AlbumCopies.setDisable(true);
		tf_GameCopies.setDisable(true);
		tf_MovieCopies.setDisable(true);
		tf_Rating.setDisable(true);
		tf_Weight.setDisable(true);
		tf_Artist.setDisable(true);
		tf_Songs.setDisable(true);
		
		Button bt_BackMedia = new Button("Back" , new ImageView("back.png"));
		bt_BackMedia.setStyle("-fx-background-color: black; -fx-border-color: red");
		bt_BackMedia.setTextFill(Color.RED);
		Button bt_BackMovie = new Button("Back", new ImageView("back.png"));
		bt_BackMovie.setStyle("-fx-background-color: black; -fx-border-color: red");
		bt_BackMovie.setTextFill(Color.RED);
		Button bt_FindMovie = new Button("Find", new ImageView("find.png"));
		bt_FindMovie.setStyle("-fx-background-color: black; -fx-border-color: red");
		bt_FindMovie.setTextFill(Color.RED);
		Button bt_BackGame = new Button("Back", new ImageView("back.png"));
		bt_BackGame.setStyle("-fx-background-color: black; -fx-border-color: red");
		bt_BackGame.setTextFill(Color.RED);
		Button bt_FindGame = new Button("Find", new ImageView("find.png"));
		bt_FindGame.setStyle("-fx-background-color: black; -fx-border-color: red");
		bt_FindGame.setTextFill(Color.RED);
		Button bt_BackAlbum = new Button("Back", new ImageView("back.png"));
		bt_BackAlbum.setStyle("-fx-background-color: black; -fx-border-color: red");
		bt_BackAlbum.setTextFill(Color.RED);
		Button bt_FindAlbum = new Button("Find", new ImageView("find.png"));
		bt_FindAlbum.setStyle("-fx-background-color: black; -fx-border-color: red");
		bt_FindAlbum.setTextFill(Color.RED);
		Button bt_select = new Button("Select");
		bt_select.setStyle("-fx-background-color: black; -fx-border-color: red");
		bt_select.setTextFill(Color.RED);
		Button bt_ClearMovie = new Button("Clear All", new ImageView("clear.png"));
		bt_ClearMovie.setStyle("-fx-background-color: black; -fx-border-color: red;");
		bt_ClearMovie.setTextFill(Color.RED);
		bt_ClearMovie.setOnAction(e ->{
			tf_MovieCode.setText("");
			tf_MovieTitle.setText("");
			tf_MovieCopies.setText("");
			tf_Rating.setText("");
			tf_MovieTitle.setDisable(true);
			tf_MovieCopies.setDisable(true);
			tf_Rating.setDisable(true);
		});
		Button bt_ClearGame = new Button("Clear All", new ImageView("clear.png"));
		bt_ClearGame.setStyle("-fx-background-color: black; -fx-border-color: red;");
		bt_ClearGame.setTextFill(Color.RED);
		bt_ClearGame.setOnAction(e ->{
			tf_GameCode.setText("");
			tf_GameCopies.setText("");
			tf_GameTitle.setText("");
			tf_Weight.setText("");
			tf_GameCopies.setDisable(true);
			tf_GameTitle.setDisable(true);
			tf_Weight.setDisable(true);
		});
		Button bt_ClearAlbum = new Button("Clear All", new ImageView("clear.png"));
		bt_ClearAlbum.setStyle("-fx-background-color: black; -fx-border-color: red;");
		bt_ClearAlbum.setTextFill(Color.RED);
		bt_ClearAlbum.setOnAction(e ->{
			tf_AlbumCode.setText("");
			tf_AlbumCopies.setText("");
			tf_AlbumTitle.setText("");
			tf_Artist.setText("");
			tf_Songs.setText("");
			tf_AlbumCopies.setDisable(true);
			tf_AlbumTitle.setDisable(true);
			tf_Artist.setDisable(true);
			tf_Songs.setDisable(true);
		});
		HBox ButtonPane = new HBox(30);
		ButtonPane.setPadding(new Insets(40,40,40,40));
		ButtonPane.getChildren().addAll(bt_BackMedia);
		ButtonPane.setAlignment(Pos.BOTTOM_LEFT);
		ButtonPane.setStyle("-fx-background-color: black");
		
		HBox MainPane = new HBox(30);
		MainPane.setAlignment(Pos.CENTER);
		MainPane.getChildren().addAll(lb_MediaType, cbo_MediaType, bt_select);
		MainPane.setStyle("-fx-background-color: black");
		
		BorderPane pane = new BorderPane();
		pane.setTop(labelPane1);
		pane.setCenter(MainPane);
		pane.setBottom(ButtonPane);
		
		HBox ButtonPaneMovie = new HBox(30);
		ButtonPaneMovie.setAlignment(Pos.CENTER);
		ButtonPaneMovie.getChildren().addAll(bt_BackMovie, bt_FindMovie, bt_ClearMovie);
		ButtonPaneMovie.setPadding(new Insets(40,40,40,40));
		ButtonPaneMovie.setStyle("-fx-background-color: black");
		
		HBox ButtonPaneGame = new HBox(30);
		ButtonPaneGame.setAlignment(Pos.CENTER);
		ButtonPaneGame.getChildren().addAll(bt_BackGame, bt_FindGame, bt_ClearGame);
		ButtonPaneGame.setPadding(new Insets(40,40,40,40));
		ButtonPaneGame.setStyle("-fx-background-color: black");
		
		HBox ButtonPaneAlbum = new HBox(30);
		ButtonPaneAlbum.setAlignment(Pos.CENTER);
		ButtonPaneAlbum.getChildren().addAll(bt_BackAlbum, bt_FindAlbum, bt_ClearAlbum);
		ButtonPaneAlbum.setPadding(new Insets(40,40,40,40));
		ButtonPaneAlbum.setStyle("-fx-background-color: black");
		
		GridPane gpane1 = new GridPane();
		gpane1.setAlignment(Pos.CENTER);
		gpane1.setHgap(30);
		gpane1.setVgap(30);
		gpane1.add(lb_MovieCode, 0, 0);
		gpane1.add(tf_MovieCode, 1, 0);
		gpane1.add(lb_MovieTitle, 0, 1);
		gpane1.add(tf_MovieTitle, 1, 1);
		gpane1.add(lb_MovieCopies, 0, 2);
		gpane1.add(tf_MovieCopies, 1, 2);
		gpane1.add(lb_Rating, 0, 3);
		gpane1.add(tf_Rating, 1, 3);
		BorderPane MovieBPane = new BorderPane();
		MovieBPane.setTop(labelPane2);
		MovieBPane.setCenter(gpane1);
		MovieBPane.setBottom(ButtonPaneMovie);
		StackPane MoviePane = new StackPane();
		MoviePane.getChildren().add(MovieBPane);
		MoviePane.setStyle("-fx-background-color: black");
		
		GridPane gpane2 = new GridPane();
		gpane2.setAlignment(Pos.CENTER);
		gpane2.setHgap(30);
		gpane2.setVgap(30);
		gpane2.add(lb_GameCode, 0, 1);
		gpane2.add(tf_GameCode, 1, 1);
		gpane2.add(lb_GameTitle, 0, 2);
		gpane2.add(tf_GameTitle, 1, 2);
		gpane2.add(lb_GameCopies, 0, 3);
		gpane2.add(tf_GameCopies, 1, 3);
		gpane2.add(lb_Weight, 0, 4);
		gpane2.add(tf_Weight, 1, 4);
		BorderPane GameBPane = new BorderPane();
		GameBPane.setTop(labelPane3);
		GameBPane.setCenter(gpane2);
		GameBPane.setBottom(ButtonPaneGame);
		StackPane GamePane = new StackPane();
		GamePane.getChildren().add(GameBPane);
		GamePane.setStyle("-fx-background-color: black");
		
		
		GridPane gpane3 = new GridPane();
		gpane3.setAlignment(Pos.CENTER);
		gpane3.setHgap(30);
		gpane3.setVgap(30);
		gpane3.add(lb_AlbumCode, 0, 1);
		gpane3.add(tf_AlbumCode, 1, 1);
		gpane3.add(lb_AlbumTitle, 0, 2);
		gpane3.add(tf_AlbumTitle, 1, 2);
		gpane3.add(lb_AlbumCopies, 0, 3);
		gpane3.add(tf_AlbumCopies, 1, 3);
		gpane3.add(lb_Artist, 0, 4);
		gpane3.add(tf_Artist, 1, 4);
		gpane3.add(lb_Songs, 0, 5);
		gpane3.add(tf_Songs, 1, 5);
		BorderPane AlbumBPane = new BorderPane();
		AlbumBPane.setTop(labelPane4);
		AlbumBPane.setCenter(gpane3);
		AlbumBPane.setBottom(ButtonPaneAlbum);
		StackPane AlbumPane = new StackPane();
		AlbumPane.getChildren().add(AlbumBPane);
		AlbumPane.setStyle("-fx-background-color: black");
		
		
		bt_FindMovie.setOnAction(e ->{
			for(int i=0; i<m.mList.size(); i++) 
			{
				if(m.mList.get(i).getCode().equals(tf_MovieCode.getText()))
				{
					tf_MovieTitle.setText(m.mList.get(i).getTitle());
					tf_MovieCopies.setText(m.mList.get(i).getCopiesAvailable() + "");
					tf_Rating.setText(((Movie)m.mList.get(i)).getRating());
				}
			}
			tf_MovieTitle.setDisable(false);
			tf_MovieCopies.setDisable(false);
			tf_Rating.setDisable(false);
			tf_MovieTitle.setEditable(false);
			tf_MovieCopies.setEditable(false);
			tf_Rating.setEditable(false);
		});
		
		bt_BackMovie.setOnAction(e ->{
			tf_MovieCode.setText("");
			tf_MovieTitle.setText("");
			tf_MovieCopies.setText("");
			tf_Rating.setText("");
			tf_MovieTitle.setDisable(true);
			tf_MovieCopies.setDisable(true);
			tf_Rating.setDisable(true);
			scene.setRoot(pane);
			primaryStage.setScene(scene);
		});
		
		bt_FindGame.setOnAction(e ->{
			for(int i=0; i<m.mList.size(); i++) 
			{
				if(m.mList.get(i).getCode().equals(tf_GameCode.getText()))
				{
					tf_GameTitle.setText(m.mList.get(i).getTitle());
					tf_GameCopies.setText(m.mList.get(i).getCopiesAvailable() + "");
					tf_Weight.setText(((Game)m.mList.get(i)).getWeight()+"");
				}
			}
			tf_GameTitle.setDisable(false);
			tf_GameCopies.setDisable(false);
			tf_Weight.setDisable(false);
			tf_GameTitle.setEditable(false);
			tf_GameCopies.setEditable(false);
			tf_Weight.setEditable(false);
			
		});
		
		bt_BackGame.setOnAction(e ->{
			tf_GameTitle.setText("");
			tf_GameCopies.setText("");
			tf_Weight.setText("");
			tf_GameCode.setText("");
			tf_GameTitle.setDisable(true);
			tf_GameCopies.setDisable(true);
			tf_Weight.setDisable(true);
			scene.setRoot(pane);
			primaryStage.setScene(scene);
		});
		
		bt_FindAlbum.setOnAction(e ->{
			for(int i=0; i<m.mList.size(); i++)
			{
				if(m.mList.get(i).getCode().equals(tf_AlbumCode.getText()))
				{
					tf_AlbumCopies.setText(m.mList.get(i).getCopiesAvailable()+"");
					tf_AlbumTitle.setText(m.mList.get(i).getTitle());
					tf_Artist.setText(((Album)m.mList.get(i)).getArtist());
					tf_Songs.setText(((Album)m.mList.get(i)).getSongs());
				}
			}
			tf_AlbumCopies.setDisable(false);
			tf_AlbumTitle.setDisable(false);
			tf_Artist.setDisable(false);
			tf_Songs.setDisable(false);
			tf_AlbumCopies.setEditable(false);
			tf_AlbumTitle.setEditable(false);
			tf_Artist.setEditable(false);
			tf_Songs.setEditable(false);
		});
			
		bt_BackAlbum.setOnAction(e ->{
			tf_AlbumCopies.setText("");
			tf_AlbumTitle.setText("");
			tf_Artist.setText("");
			tf_Songs.setText("");
			tf_AlbumCode.setText("");
			tf_AlbumCopies.setDisable(true);
			tf_AlbumTitle.setDisable(true);
			tf_Artist.setDisable(true);
			tf_Songs.setDisable(true);
			scene.setRoot(pane);
			primaryStage.setScene(scene);
		});
		
		bt_BackMedia.setOnAction(e ->{
			cbo_MediaType.setValue("");
			scene.setRoot(pane3);
			primaryStage.setScene(scene);
		});
	
		
		bt_SearchMedia.setOnAction(e ->{
			scene.setRoot(pane);
			primaryStage.setScene(scene);
		});
		
		
		bt_select.setOnAction(e -> {
			if(cbo_MediaType.getValue().equals("Movie"))
			{
				scene.setRoot(MoviePane);
				primaryStage.setScene(scene);
	
			}
			else if(cbo_MediaType.getValue().equals("Game"))
			{
				scene.setRoot(GamePane);
				primaryStage.setScene(scene);
			}
			else if(cbo_MediaType.getValue().equals("Album")){
				scene.setRoot(AlbumPane);
				primaryStage.setScene(scene);
			}
			cbo_MediaType.setValue("");
		});
		
	}
	
	public static void PrintMediaInfo(Stage primaryStage, Scene scene, Pane pane3, Button bt_PrintMedia)
	{
		TextArea ta_Media = new TextArea();
		ta_Media.setStyle("-fx-control-inner-background: black; -fx-text-fill: red; "
						+ "-fx-highlight-text-fill: black; -fx-highlight-fill: red; "
						+ "fx-text-box-border: red;"+" -fx-focus-color: red;");
		ta_Media.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		ta_Media.setEditable(false);
		Label lb_PrintMedia = new Label("Media Information");
		lb_PrintMedia.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
		lb_PrintMedia.setTextFill(Color.RED);
		lb_PrintMedia.setUnderline(true);
		HBox labelPane = new HBox();
		labelPane.setPadding(new Insets(20,20,20,20));
		labelPane.setStyle("-fx-background-color: black;");
		labelPane.setAlignment(Pos.CENTER);
		labelPane.getChildren().add(lb_PrintMedia);
		Button bt_Print = new Button("Print All Media Info", new ImageView("print.png"));
		bt_Print.setStyle("-fx-background-color: black; -fx-border-color: red");
		bt_Print.setTextFill(Color.RED);
		bt_Print.setOnAction(e ->{
			ta_Media.setText(m.printMediaInfo());
		});
		Button bt_back = new Button("Back", new ImageView("back.png"));
		bt_back.setStyle("-fx-background-color: black; -fx-border-color: red");
		bt_back.setTextFill(Color.RED);
		bt_back.setOnAction(e ->{
			ta_Media.setText("");
			scene.setRoot(pane3);
			primaryStage.setScene(scene);
		});
		HBox ButtonPane = new HBox(30);
		ButtonPane.setAlignment(Pos.CENTER);
		ButtonPane.setPadding(new Insets(40,40,40,40));
		ButtonPane.getChildren().addAll(bt_back, bt_Print);
		ButtonPane.setStyle("-fx-background-color: black;");
		
		BorderPane pane = new BorderPane();
		pane.setTop(labelPane);
		pane.setCenter(ta_Media);
		pane.setBottom(ButtonPane);
		
		bt_PrintMedia.setOnAction(e ->{
			scene.setRoot(pane);
			primaryStage.setScene(scene);
		});
		
	}
	
	public static void Rent(Stage primaryStage, Scene scene, Pane pane1, Button bt_rent)
	{
		File Interested = new File("Interested.txt");
		File Rented = new File("Rented.txt");
		Label lb_CustID = new Label("Customer ID");
		lb_CustID.setTextFill(Color.RED);
		Label lb_MediaCode = new Label("Media Code");
		lb_MediaCode.setTextFill(Color.RED);
		Label lb_RentedDate = new Label("Rented Date");
		lb_RentedDate.setTextFill(Color.RED);
		Label lb_RentMedia = new Label("Rental Window");
		lb_RentMedia.setTextFill(Color.RED);
		
		lb_CustID.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_MediaCode.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_RentedDate.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lb_RentMedia.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC ,30));
		lb_RentMedia.setUnderline(true);
		lb_RentMedia.setTextFill(Color.RED);
		
		HBox labelPane1 = new HBox();
		labelPane1.setAlignment(Pos.CENTER);
		labelPane1.getChildren().add(lb_RentMedia);
		labelPane1.setStyle("-fx-background-color: black; -fx-border-color: red;");
		labelPane1.setPadding(new Insets(20,20,20,20));
		
		TextField tf_CustID = new TextField();
		tf_CustID.setStyle("-fx-background-color: black; -fx-border-color: red; -fx-text-fill: red");
		tf_CustID.setFont(Font.font(16));
		TextField tf_MediaCode = new TextField();
		tf_MediaCode.setStyle("-fx-background-color: black; -fx-border-color: red; -fx-text-fill: red");
		tf_MediaCode.setFont(Font.font(16));
		TextField tf_RentedDate = new TextField();
		tf_RentedDate.setStyle("-fx-background-color: black; -fx-border-color: red; -fx-text-fill: red");
		tf_RentedDate.setFont(Font.font(16));
		tf_RentedDate.setEditable(false);
		TextArea ta_CustInfo = new TextArea();
		ta_CustInfo.setStyle("-fx-control-inner-background: black; -fx-text-fill: red; "
						+ "-fx-highlight-text-fill: black; -fx-highlight-fill: red; "
						+ "fx-text-box-border: red;"+" -fx-focus-color: red;");
		ta_CustInfo.setEditable(false);
		ta_CustInfo.setMaxSize(400, 400);
		ta_CustInfo.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		TextArea ta_MediaInfo = new TextArea();
		ta_MediaInfo.setStyle("-fx-control-inner-background: black; -fx-text-fill: red; "
						+ "-fx-highlight-text-fill: black; -fx-highlight-fill: red; "
						+ "fx-text-box-border: red;"+" -fx-focus-color: red;");
		ta_MediaInfo.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16));
		ta_MediaInfo.setEditable(false);
		ta_MediaInfo.setMaxSize(500, 200);
		
		Button bt_ProcessCart = new Button("Process Cart", new ImageView("process.png"));
		bt_ProcessCart.setStyle("-fx-background-color: black; -fx-border-color: red;");
		bt_ProcessCart.setTextFill(Color.RED);
		bt_ProcessCart.setOnAction(e ->{
			m.processReqeusts(tf_CustID.getText());
			tf_RentedDate.setText(new Date().toString());
			for(int i=0; i<m.cList.size(); i++)
			{
				if(m.cList.get(i).getID().equals(tf_CustID.getText()))
				{
					ta_CustInfo.setText("Customer Name: "+m.cList.get(i).getName()+"\nAddress: "+m.cList.get(i).getAddress()+
					"\nMobile: "+m.cList.get(i).getMobileNum()+"\nPlane: "+m.cList.get(i).getPlan()+
					"\nInterested in: "+m.cList.get(i).getInterestedIn().toString()+"\nRented: "+m.cList.get(i).getRented().toString());
				}
			}
			try {
				FileWriter RentOut = new FileWriter(Rented,false);
				RentOut.write(getAllRentedInfo());
				RentOut.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				FileWriter InterestedOut = new FileWriter(Interested, false);
				InterestedOut.write(getAllInterestedInfo());
				InterestedOut.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		});
		
		Button bt_ReturnMedia = new Button("Return Media", new ImageView("return media.png"));
		bt_ReturnMedia.setStyle("-fx-background-color: black; -fx-border-color: red;");
		bt_ReturnMedia.setTextFill(Color.RED);
		bt_ReturnMedia.setOnAction(e ->{
			m.returnMedia(tf_CustID.getText(), tf_MediaCode.getText());
			for(int i=0; i<m.cList.size(); i++)
			{
				if(m.cList.get(i).getID().equals(tf_CustID.getText()))
				{
					ta_CustInfo.setText("Customer Name: "+m.cList.get(i).getName()+"\nAddress: "+m.cList.get(i).getAddress()+
					"\nMobile: "+m.cList.get(i).getMobileNum()+"\nPlane: "+m.cList.get(i).getPlan()+
					"\nInterested in: "+m.cList.get(i).getInterestedIn().toString()+"\nRented: "+m.cList.get(i).getRented().toString());
				}
			}
			for(int i=0; i<m.mList.size(); i++)
			{
				if(m.mList.get(i).getCode().equals(tf_MediaCode.getText()))
				{
					
					ta_MediaInfo.setText(m.mList.get(i).print());
				}
			}
			try {
				FileWriter RentOut = new FileWriter(Rented,false);
				RentOut.write(getAllRentedInfo());
				RentOut.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				FileWriter InterestedOut = new FileWriter(Interested, false);
				InterestedOut.write(getAllInterestedInfo());
				InterestedOut.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		Button bt_AddToCart = new Button("Add to cart", new ImageView("add to cart.png"));
		bt_AddToCart.setStyle("-fx-background-color: black; -fx-border-color: red");
		bt_AddToCart.setTextFill(Color.RED);
		bt_AddToCart.setOnAction(e ->{
			m.addToCart(tf_CustID.getText(), tf_MediaCode.getText());
			for(int i=0; i<m.cList.size(); i++)
			{
				if(m.cList.get(i).getID().equals(tf_CustID.getText()))
				{
					ta_CustInfo.setText("Customer Name: "+m.cList.get(i).getName()+"\nAddress: "+m.cList.get(i).getAddress()+
					"\nMobile: "+m.cList.get(i).getMobileNum()+"\nPlane: "+m.cList.get(i).getPlan()+
					"\nInterested in: "+m.cList.get(i).getInterestedIn().toString()+"\nRented: "+m.cList.get(i).getRented().toString());
				}
			}
			for(int i=0; i<m.mList.size(); i++)
			{
				if(m.mList.get(i).getCode().equals(tf_MediaCode.getText()))
				{
					
					ta_MediaInfo.setText(m.mList.get(i).print());
				}
			}
			try {
				FileWriter InterestedOut = new FileWriter(Interested, false);
				InterestedOut.write(getAllInterestedInfo());
				InterestedOut.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		Button bt_Clear = new Button("Clear All", new ImageView("clear.png"));
		bt_Clear.setStyle("-fx-background-color: black; -fx-border-color: red;");
		bt_Clear.setTextFill(Color.RED);
		bt_Clear.setOnAction(e ->{
			tf_CustID.setText("");
			ta_CustInfo.setText("");
			tf_MediaCode.setText("");
			ta_MediaInfo.setText("");
			tf_RentedDate.setText("");
		});
		Button bt_RemoveFromCart = new Button("Remove From Cart", new ImageView("remove from cart.png"));
		bt_RemoveFromCart.setStyle("-fx-background-color: black; -fx-border-color: red;");
		bt_RemoveFromCart.setTextFill(Color.RED);
		bt_RemoveFromCart.setOnAction(e ->{
			m.removeFromCart(tf_CustID.getText(), tf_MediaCode.getText());
			for(int i=0; i<m.cList.size(); i++)
			{
				if(m.cList.get(i).getID().equals(tf_CustID.getText()))
				{
					ta_CustInfo.setText("Customer Name: "+m.cList.get(i).getName()+"\nAddress: "+m.cList.get(i).getAddress()+
					"\nMobile: "+m.cList.get(i).getMobileNum()+"\nPlane: "+m.cList.get(i).getPlan()+
					"\nInterested in: "+m.cList.get(i).getInterestedIn().toString()+"\nRented: "+m.cList.get(i).getRented().toString());
				}
			}
			for(int i=0; i<m.mList.size(); i++)
			{
				if(m.mList.get(i).getCode().equals(tf_MediaCode.getText()))
				{
					
					ta_MediaInfo.setText(m.mList.get(i).print());
				}
			}
			try {
				FileWriter InterestedOut = new FileWriter(Interested, false);
				InterestedOut.write(getAllInterestedInfo());
				InterestedOut.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		});
		Button bt_ShowCustInfo = new Button("Show Customer Info");
		bt_ShowCustInfo.setStyle("-fx-background-color: black; -fx-border-color: red");
		bt_ShowCustInfo.setTextFill(Color.RED);
		bt_ShowCustInfo.setOnAction(e ->{
			for(int i=0; i<m.cList.size(); i++)
			{
				if(m.cList.get(i).getID().equals(tf_CustID.getText()))
				{
					ta_CustInfo.setText("Customer Name: "+m.cList.get(i).getName()+"\nAddress: "+m.cList.get(i).getAddress()+
					"\nMobile: "+m.cList.get(i).getMobileNum()+"\nPlane: "+m.cList.get(i).getPlan()+
					"\nInterested in: "+m.cList.get(i).getInterestedIn().toString()+"\nRented: "+m.cList.get(i).getRented().toString());
				}
			}
		});
		
		Button bt_ShowMediaInfo = new Button("Show Media Info");
		bt_ShowMediaInfo.setStyle("-fx-background-color: black; -fx-border-color: red;");
		bt_ShowMediaInfo.setTextFill(Color.RED);
		bt_ShowMediaInfo.setOnAction(e ->{
			for(int i=0; i<m.mList.size(); i++)
			{
				if(m.mList.get(i).getCode().equals(tf_MediaCode.getText()))
				{
					
					ta_MediaInfo.setText(m.mList.get(i).print());
				}
			}
		});
		
		Button bt_Back = new Button("Back", new ImageView("back.png"));
		bt_Back.setStyle("-fx-background-color: black; -fx-border-color: red;");
		bt_Back.setTextFill(Color.RED);
		bt_Back.setOnAction(e ->{
			ta_CustInfo.setText("");
			ta_MediaInfo.setText("");
			tf_CustID.setText("");
			tf_MediaCode.setText("");
			tf_RentedDate.setText("");
			scene.setRoot(pane1);
			primaryStage.setScene(scene);
		});
		HBox ButtonPane = new HBox(30);
		ButtonPane.getChildren().addAll(bt_Back, bt_AddToCart, bt_RemoveFromCart, bt_ProcessCart, bt_ReturnMedia, bt_Clear);
		ButtonPane.setAlignment(Pos.CENTER);
		ButtonPane.setPadding(new Insets(40,40,40,40));
		
		GridPane gPane = new GridPane();
		gPane.setVgap(20);
		gPane.setHgap(30);
		gPane.add(lb_CustID, 0 ,0);
		gPane.add(tf_CustID, 1, 0);
		gPane.add(bt_ShowCustInfo, 2, 0);
		gPane.add(ta_CustInfo, 1, 1);
		gPane.add(lb_MediaCode, 0, 2);
		gPane.add(tf_MediaCode, 1, 2);
		gPane.add(bt_ShowMediaInfo, 2, 2);
		gPane.add(ta_MediaInfo, 1, 4);
		gPane.add(lb_RentedDate, 0, 5);
		gPane.add(tf_RentedDate, 1, 5);
		gPane.setAlignment(Pos.CENTER);
		gPane.setPadding(new Insets(15,15,15,15));
		
		BorderPane Bpane = new BorderPane();
		Bpane.setTop(labelPane1);
		Bpane.setCenter(gPane);
		Bpane.setBottom(ButtonPane);
		
		StackPane pane = new StackPane();
		pane.getChildren().add(Bpane);
		pane.setStyle("-fx-background-color: black");
		
		bt_rent.setOnAction(e ->{
			scene.setRoot(pane);
			primaryStage.setScene(scene);
		});
	}
	
	public static String getAllInterestedInfo()
	{
		String Interested = "";
		StringBuilder Int = new StringBuilder(Interested);
		for(int i=0; i<m.cList.size(); i++)
		{
			if(m.cList.get(i).getInterestedIn().size()!=0)
			{
				Int.append(m.cList.get(i).getID()+"$");
				int size = m.cList.get(i).getInterestedIn().size();
				for(int j=0; j<size; j++)
				{
					Int.append(m.cList.get(i).getInterestedIn().get(j)+",");
				}
				Int.deleteCharAt(Int.lastIndexOf(","));
				Int.append("\n");
			}
		}
		return Int.toString();
	}
	
	public static String getAllRentedInfo()
	{
		String Rented="";
		StringBuilder Ren = new StringBuilder(Rented);
		for(int i=0; i<m.cList.size(); i++)
		{
			if(m.cList.get(i).getRented().size()!=0)
			{
				Ren.append(m.cList.get(i).getID()+"$");
				int size = m.cList.get(i).getRented().size();
				for(int j=0; j<size; j++)
				{
					Ren.append(m.cList.get(i).getRented().get(j)+",");
				}
				Ren.deleteCharAt(Ren.lastIndexOf(","));
				Ren.append("\n");
			}
			
		}
		return Ren.toString();
	}
}