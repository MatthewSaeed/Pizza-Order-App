package com.example.demo12;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloApplication extends Application
{

    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Pizza Order App");


        TextField nameField = new TextField();
        nameField.setPromptText("Enter your name");
        TextField addressField = new TextField();
        addressField.setPromptText("Enter your address");
        TextField phoneField = new TextField();
        phoneField.setPromptText("Enter your phone number");


        ToggleGroup crustGroup = new ToggleGroup();
        RadioButton thinCrust = new RadioButton("Thin");
        RadioButton regularCrust = new RadioButton("Regular");
        RadioButton deepDishCrust = new RadioButton("Deep Dish");
        thinCrust.setToggleGroup(crustGroup);
        regularCrust.setToggleGroup(crustGroup);
        deepDishCrust.setToggleGroup(crustGroup);

        HBox crustBox = new HBox(10, thinCrust, regularCrust, deepDishCrust);


        ToggleGroup sauceGroup = new ToggleGroup();
        RadioButton regularSauce = new RadioButton("Regular");
        RadioButton bbqSauce = new RadioButton("BBQ");
        RadioButton alfredoSauce = new RadioButton("Alfredo");
        regularSauce.setToggleGroup(sauceGroup);
        bbqSauce.setToggleGroup(sauceGroup);
        alfredoSauce.setToggleGroup(sauceGroup);

        HBox sauceBox = new HBox(10, regularSauce, bbqSauce, alfredoSauce);


        CheckBox pepperoni = new CheckBox("Pepperoni");
        CheckBox sausage = new CheckBox("Sausage");
        CheckBox olives = new CheckBox("Olives");
        CheckBox mushroom = new CheckBox("Mushroom");
        CheckBox peppers = new CheckBox("Peppers");


        HBox toppingsBox = new HBox(10, pepperoni, sausage, olives, mushroom, peppers);


        ToggleGroup sizeGroup = new ToggleGroup();
        RadioButton smallSize = new RadioButton("Small");
        RadioButton mediumSize = new RadioButton("Medium");
        RadioButton largeSize = new RadioButton("Large");
        smallSize.setToggleGroup(sizeGroup);
        mediumSize.setToggleGroup(sizeGroup);
        largeSize.setToggleGroup(sizeGroup);

        HBox sizeBox = new HBox(10, smallSize, mediumSize, largeSize);


        ToggleGroup radioButtonGroup = new ToggleGroup();
        RadioButton radioButton1 = new RadioButton("1");
        RadioButton radioButton2 = new RadioButton("2");
        RadioButton radioButton3 = new RadioButton("3");
        RadioButton radioButton4 = new RadioButton("4");
        RadioButton radioButton5 = new RadioButton("5");
        radioButton1.setToggleGroup(radioButtonGroup);
        radioButton2.setToggleGroup(radioButtonGroup);
        radioButton3.setToggleGroup(radioButtonGroup);
        radioButton4.setToggleGroup(radioButtonGroup);
        radioButton5.setToggleGroup(radioButtonGroup);

        HBox radioButtonBox = new HBox(10, radioButton1, radioButton2, radioButton3, radioButton4, radioButton5);

        Button submitButton = new Button("Submit");

        Label confirmationLabel = new Label();

        submitButton.setOnAction(event -> {
            String name = nameField.getText();
            String address = addressField.getText();
            String phone = phoneField.getText();
            RadioButton selectedCrust = (RadioButton) crustGroup.getSelectedToggle();
            RadioButton selectedSauce = (RadioButton) sauceGroup.getSelectedToggle();
            RadioButton selectedSize = (RadioButton) sizeGroup.getSelectedToggle();
            RadioButton selectedRadioButton = (RadioButton) radioButtonGroup.getSelectedToggle();

            if (name.isEmpty() || address.isEmpty() || phone.isEmpty() || selectedCrust == null || selectedSauce == null || selectedSize == null || selectedRadioButton == null)
            {
                showAlert("Error", "Please make sure everything is filled in.");
                return;
            }

            // Calculate the cost
            double basePrice = 10.0;
            double toppingCost = 0.0;
            double sizeCost = 0.0;

            if (pepperoni.isSelected()) toppingCost += 0.50;
            if (sausage.isSelected()) toppingCost += 0.50;
            if (olives.isSelected()) toppingCost += 0.50;
            if (mushroom.isSelected()) toppingCost += 0.50;
            if (peppers.isSelected()) toppingCost += 0.50;

            if (selectedSize == mediumSize) sizeCost = 1.50;
            else if (selectedSize == largeSize) sizeCost = 3.00;

            int quantity = Integer.parseInt(selectedRadioButton.getText());
            double totalCost = (basePrice + toppingCost + sizeCost) * quantity;

            // Display confirmation
            confirmationLabel.setText(String.format("Thank you " + name + " for your order! Total cost: " + totalCost));
        });

        // Layout setup
        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(15));
        vBox.getChildren().addAll(
                new Label("Name:"), nameField,
                new Label("Address:"), addressField,
                new Label("Phone:"), phoneField,
                new Label("Crust:"), crustBox,
                new Label("Sauce:"), sauceBox,
                new Label("Toppings:"), toppingsBox,
                new Label("Size:"), sizeBox,
                new Label("Quantity:"), radioButtonBox,
                submitButton,
                confirmationLabel
        );

        vBox.setAlignment(Pos.TOP_CENTER);

        Scene scene = new Scene(vBox, 500, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAlert(String title, String message)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args)
    {
        launch();
    }
}
