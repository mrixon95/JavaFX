Install JavaFX Windows SDK from gluonhq.com

Put the folder somewhere where it would not be deleted and extract it



Define a global library so we can use JavaFX in any project we like.

```
Configure -> global libraries -> add symbol -> select all jar files in lib folder

rename to javafx 11
```



Create new project -> javafx



// JavaFX 11 is not part of Java11 so we have to set up up.

Click on project structure -> global libraries -> right click JavaFX-11 -> add to modules

Note: There should be an *Add to modules* option if not already selected.

![Add_to_modules](.\images\Add_to_modules.PNG)



Need to configure javafx to work.

Add a module-info.java file so we have defined javafx controller.



![Add_module_info](.\images\Add_module_info.PNG)





In this file, include 

```
module Tim.Bulchalka.course {

    requires javafx.fxml;
    requires javafx.controls;
    
    opens sample;

}
```

// sample is the package names

![Required_packages](.\images\Required_packages.PNG)



Javafx application comes from javafx.application.Application

Javafx applications must have a class that extends Application. Application manages the life cycle of the application. 

We care about init, start and stop.

When we run the application, Application.Launch is run from the main method. It launches the Javafx application and only finishes when the Javafx application exits

Init method runs first. Then the start method. We need to override it because it is an abstract method in the Application class.

When the application finishes, eg. the user closes the application, the stop method is run. It is empty, unless we override it.



Stage extends the window class and is a top-level java container. Javafx window contructs the initial stage and passes it to the start method.

We use a dialogue class to wrap a stage.

Line 13 loads the UI from the fxml file.

![Understand_javafx](.\images\Understand_javafx.PNG)

fxml is a flavour of xml

fx:controller attribute tell the run time which class is the controller for.

When we load in the sample.fxml file, all of the UI objects are constructed.

sample is the package and controller is the class.

To load in an fxml file 

```
Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
```

Since the window class is the parent of the stage class, the window title will be set to hello world.

Only node in the sample.fxml is the gridpane so it is the root of the scene graph.

The java developers were going for a theatre metaphor each stage requires a scene and backing each scene is a graph were each node corresponds to a UI control or an area of the scene.

Parent classes decends directly from Node class which is the base class for scene graph nodes

Nodes that descend from parent can have child in the scene graph eg. GridPane is the root of the scene graph.



Stage is a top level Ui container whilst the scene is backed by a scene graph which contains the UI nodes. To change what is shown, we just need to change the scene.



Set alignment and gaps for the GridPane
```
GridPane root = new GridPane();
root.setAlignment(Pos.CENTER);
root.setVgap(10)
root.setHgap(10);
```



Set greeting

```
Label greeting = new Label("Welcome to JavaFX!");
greeting.setTextFill(Color.GREEN);
greeting.setFont(Font.font("Times New Roman", FontWeight.BOLD, 70));
root.getChildren().add(greeting);
```



Set title and show stage

```
primaryStage.setTitle("Hello JavaFX");
primaryStage.setScene(new Scene(root, 700, 275));
primaryStage.show();
```



In fxml

```
<GridPane fx:controller="sample.Controller"
          xmlns:fx="http://javafx.com/fxml" alignment="center" hgap="10" vgap="10">
    <Label text="Welcome to JavaFX!" textFill="green">
        <font>
            <Font name="Times New Roman bold" size="70"/>
        </font>
    </Label>
</GridPane>
```



We will focus on fxml route. Best practice to define UI through fxml.

Don't need to touch code when change fxml.



Javafx has 8 layouts: GridPane, StackPane, AnchorPane, Hbox, Vbox, FlowPane, BorderPane, TilePane



Controls have a Preferred width and height based.



GridPane has cells within it. Lays out its children within those cells. The rows and columns sizes are flexible. Row will be as large as the largest control it contains and the columns will be as large as the largest control it contains.



Default position is column 0 row 0.

```
<GridPane fx:controller="sample.Controller"
          xmlns:fx="http://javafx.com/fxml" alignment="center" hgap="10" vgap="10">
    <Button text="Button One"/>
    <Button text="Button Two"/>
    <Button text="Button Three"/>
    <Button text="Really Long Button Four"/>
    <Button text="Button Five"/>
    
</GridPane>
```



Alignment is by default set to center

```gridLinesVisible="true"```

10 pixel gap between each row and 10 pixel gap between each column



```
gridLinesVisible="true"
```



1st constraint applies to the first column, 2nd constraint applies to the second column.

```
<columnConstraints>
    <ColumnConstraints percentWidth="50.0"/>
    <ColumnConstraints percentWidth="50.0"/>
</columnConstraints>
```





Can put in padding on the top above buttons

```
<padding>
	<Insets top="50"/>
</padding>
```



Change alignment

```
<GridPane fx:controller="sample.Controller"
          xmlns:fx="http://javafx.com/fxml" alignment="top_center" hgap="10" vgap="10" gridLinesVisible="true">
```



Change row span of buttons

```
<Button text="Button One" GridPane.rowIndex="0" GridPane.columnIndex="0" GridPane.halignment="Right"/>
```



HBox lays out children in a row and sizes them to their preferred widths.

HBox will stretch itself and not its children to fill the excess

fillheight=true by default, hbox will fill extra height

fillheight=false children will fill extra height



Hbox bordering

```
<HBox fx:controller="sample.Controller"
      xmlns:fx="http://javafx.com/fxml" alignment="top_center"
style="-fx-border-color: red; -fx-border-width: 3;-fx-border-style: dashed">
    <Button text="Okay"/>
    <Button text="Cancel"/>
    <Button text="Help"/>
</HBox>
```



### BorderPane layout

BorderPane places controls in 1 of 5 positions: Top, Bottom, Left, Right, Center

Works well for client applications.

Tree or list to the left. Menu or toolbar to the top,

Central area to place data

Information panel to the right hand side

Status bar to the bottom



Center position gets space left over.

No space is allocated to empty position



HBox layout is made to be a child of the borderpane layout

HBox is nested within the BorderPane



Alignment property tells us how the layout's children should be aligned

```
<BorderPane fx:controller="sample.Controller" xmlns:fx="http://javafx.com/fxml">

    <top>
        <Label text="This label is in the top position" alignment="CENTER"
        BorderPane.alignment="center"
        style="-fx-border-color: blue; -fx-border-width: 3; -fx-border-style: dashed"/>
    </top>
    <bottom>
        <HBox spacing="10" alignment="bottom_right">
            <padding>
                <Insets bottom="10" right="10"/>
            </padding>
            <Button text="Okay" prefWidth="90"/>
            <Button text="Cancel" prefWidth="90"/>
            <Button text="Help" prefWidth="90"/>
        </HBox>
    </bottom>
</BorderPane>
```



Can set alignment for children within borderpane.



Controls in left and right positions of a border pane will be sized to their preferred widths



Center will get space left over after controls are placed in other positions.



If the controls in the top, bottom, left and right positions weren't already at their preferred heights they would expand before space is given to the center position.



Anchor pane anchor children to the edges. Eg. a title to the top of the pane, a hbox with buttons to the bottom.



FlowPane layout wraps its children to the next row when the orientation is set to horizontal.

```
<FlowPane fx:controller="sample.Controller" xmlns:fx="http://javafx.com/fxml" orientation="HORIZONTAL">
    <Button text="Button One"/>
    <Button text="Button Two"/>
    <Button text="Button Three"/>
    <Button text="Button Four"/>
    <Button text="Button Five"/>
    <Button text="Button Six"/>
    <Button text="Button Seven"/>
    <Button text="Button Eight"/>
    <Button text="Button Nine"/>
    <Button text="Button Ten"/>
</FlowPane>

```



VBox stacks the buttons vertically and cuts them off when the screen becomes small.

When you don't know how many children there will be, eg. programatically, use flowpane.

When you know how many children there will be, use vbox



TilePane is similar to a flowpane but every cell or tile has the same size

Each tile has the width of the largest button. Buttons are centered in each cell.



Stack pane stacks all the controls on top of eachother

```
<StackPane fx:controller="sample.Controller"
           xmlns:fx="http://javafx.com/fxml">
    <Label text="The label text" style="-fx-background-color: green"/>
    <Button text="Button One" style="-fx-background-color: red"/>
</StackPane>
```

The order in which we add the controls to the fxml will determine the order of the controls on the stack pane. The first control we add will be on the bottom and the last control will be on the top. 0th child is on the bottom, last child is on the top.



```
<GridPane fx:controller="sample.Controller"
           xmlns:fx="http://javafx.com/fxml" alignment="center" hgap="10" vgap="10">
    <Button GridPane.rowIndex="0" GridPane.columnIndex="0" text="Click Me"/>
</GridPane>
```



To know properties that can be set on a control, go to the docs https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/Button.html

Node is the base class forall scene graph nodes.

Button inherits from Control and Control inherits from Node

Button has cancelButton and defaultButton properties.

Button inherits height as well as many other properties.



Text comes from the labelled class

Add jar file called jifgr-1_0.jar. Renamed it to graphics.



@ means going to the root



The text is cut off.



```
<GridPane fx:controller="sample.Controller"
          xmlns:fx="http://javafx.com/fxml" alignment="center" hgap="10" vgap="10">
    <Button GridPane.rowIndex="0" GridPane.columnIndex="0" text="Click Me">
        <graphic>
            <ImageView>
                <Image url="@/toolbarButtonGraphics/general/TipOfTheDay24.gif"/>
            </ImageView>
        </graphic>
    </Button>
    <Label GridPane.rowIndex="0" GridPane.columnIndex="1" text="This is a label This is a label This is a label"
        textFill="blue">
        <font>
            <Font name="Arial italic" size="40"/>
        </font>
        <graphic>
            <ImageView>
                <Image url="@/toolbarButtonGraphics/general/Information24.gif"/>
            </ImageView>
        </graphic>
    </Label>
</GridPane>
```



Can set wrap text equal to true

```
<Label GridPane.rowIndex="0" GridPane.columnIndex="1" text="This is a label This is a label This is a label"
    textFill="blue" WrapText="true">
```



Radio button can be selected or deselected.

Can put radio buttons together so that only one can be selected at a time.

Radio buttons can be added to a toggle group



$ sign is for finding an fx id

fx:define is for adding something not part of the scene graph. Use selected="True" for automatically selecting a radio button to true

```
    <RadioButton GridPane.rowIndex="0" GridPane.columnIndex="2" text="Red" toggleGroup="$colorToggleGroup"/>
    <RadioButton GridPane.rowIndex="0" GridPane.columnIndex="3" text="Blue" toggleGroup="$colorToggleGroup" selected="True"/>
    <RadioButton GridPane.rowIndex="0" GridPane.columnIndex="4" text="Green" toggleGroup="$colorToggleGroup"/>
```



Indetermine state on means it initially has a dash. No real practical use for it

Can set one choice from a group of checkboxes.

Radiobuttons descend from toggle button class.



Toggle button is a control in its own right and stay down after being clicked.

```
<GridPane fx:controller="sample.Controller"
          xmlns:fx="http://javafx.com/fxml" alignment="center" hgap="10" vgap="10">
    <Button GridPane.rowIndex="0" GridPane.columnIndex="0" text="Click Me">
        <graphic>
            <ImageView>
                <Image url="@/toolbarButtonGraphics/general/TipOfTheDay24.gif"/>
            </ImageView>
        </graphic>
    </Button>
    <Label GridPane.rowIndex="0" GridPane.columnIndex="1" text="This is a label This is a label This is a label"
        textFill="blue" WrapText="true">
        <font>
            <Font name="Arial italic" size="12"/>
        </font>
        <graphic>
            <ImageView>
                <Image url="@/toolbarButtonGraphics/general/Information24.gif"/>
            </ImageView>
        </graphic>
    </Label>

    <fx:define>
        <ToggleGroup fx:id="colorToggleGroup"/>
    </fx:define>

    <RadioButton GridPane.rowIndex="0" GridPane.columnIndex="2" text="Red" toggleGroup="$colorToggleGroup"/>
    <RadioButton GridPane.rowIndex="0" GridPane.columnIndex="3" text="Blue" toggleGroup="$colorToggleGroup" selected="True"/>
    <RadioButton GridPane.rowIndex="0" GridPane.columnIndex="4" text="Green" toggleGroup="$colorToggleGroup"/>

    <VBox GridPane.rowIndex="0" GridPane.columnIndex="5">
        <CheckBox text="Dog"/>
        <CheckBox text="Cat"/>
        <CheckBox text="Bird"/>
    </VBox>

    <HBox GridPane.rowIndex="0" GridPane.columnIndex="6">
        <ToggleButton text="Toggle Me"/>
        <ToggleButton text="Toggle Me"/>
        <ToggleButton text="Toggle Me"/>
    </HBox>


</GridPane>
```



Textfield allows a user to type information in and perform action with the data.

Textfield has copy, cut, paste

 PasswordField does not have copy and cut functionality



Use observable Array List to list out the values in a Combo box.

To set a default, use

```
<value>
    <String fx:value="This is Option 4 This is Option 4"/>
</value>
```



Can set editable property to true

``` editable="True"```





Choicebox has a tick infront of it. Good for a small amount of items. 

```
<ChoiceBox GridPane.rowIndex="1" GridPane.columnSpan="4">
        <items>
            <FXCollections fx:factory="observableArrayList">
                <String fx:value="cb 1"/>
                <String fx:value="cb 2"/>
                <String fx:value="cb 3"/>
                <String fx:value="cb 4"/>
                <String fx:value="cb 5"/>
            </FXCollections>
        </items>
    </ChoiceBox>
```





```
<Slider GridPane.rowIndex="2" GridPane.columnIndex="0" GridPane.columnSpan="4"
            min="0" max="100" showTickLabels="true" showTickMarks="true" minorTickCount="4" snapToTicks="true"/>
```



Spinner control is quite new. version 8 java

Is very important to press enter to change value

```
<Spinner GridPane.rowIndex="2" GridPane.columnIndex="4" min="0" max="100" editable="true" initialValue="50"/>
```

Color picker

```
<ColorPicker GridPane.rowIndex="3" GridPane.columnIndex="0"/>
```



Titled Pane is a control. We can add to it an accordion. Only lets one titled pane be open at a time. Cannot make it horizontal. $ sign to signify that it is an id

```
<Accordion GridPane.rowIndex="3" GridPane.columnIndex="2" GridPane.columnSpan="2"
    expandedPane="$tp3">
        <panes>
            <TitledPane text="Titled Pane">
                <Label text="Label in title pane"/>
            </TitledPane>
            <TitledPane text="Titled Pane">
                <Label text="Label 2 in title pane"/>
            </TitledPane>
            <TitledPane fx:id="tp3" text="Titled Pane">
                <Label text="Label 3 in title pane"/>
            </TitledPane>
        </panes>
    </Accordion>

```

Check the documentation to see what is and isn't possible.



It will run a function called an event handler



Procedural driven applications don't have a GUI. We have a rough idea what happens when.

Event driven applications have a UI. User dictates what runs when.

Such as Intellij. One user may create a new project or open the settings dialogue.

Run initialisation code, build UI and then wait for input from the user. Application will run code based on the user event.



Java application thread know as the UI thread waits for User input.

An event is raised on the UI thread when the button is pressed and will see if any part of the application has expressed interested in handling that particular control.



We want to run an event handler when the button is pressed.

Controller handles user input. Associate event handler with the button.

UI thread checks whether there is an event handler. Our application listens for events hence the name EventHandler.



We need to associate the textField in the controller class to the control in the fxml file.

Annotate textField declaration.

Injected the reference to the textField into the name field variable.



```
@FXML
    private TextField nameField;

    @FXML
    public void onButtonClicked() {
        System.out.println("Hello, " + nameField.getText());
    }
```



Can add ActionEvent parameter to eventhandler. Can used get source to see where the event was started from

```
    @FXML
    public void onButtonClicked(ActionEvent e) {

        if(e.getSource().equals(helloButton)) {
            System.out.println("Hello, " + nameField.getText());
        } else if (e.getSource().equals(byeButton)) {
            System.out.println("Bye, " + nameField.getText());
        }
    }
```



Check that the field isn't empty

Listen for a character typed into a text field



disable buttons if there is no text. Run this function every time a button is pressed into the field

```
@FXML
    public void handleKeyReleased() {
        String text = nameField.getText();
        boolean disabledButtons = text.isEmpty() || text.trim().isEmpty();
        helloButton.setDisabled(disabledButtons);
        byeButton.setDisabled(disabledButtons)
    }
```



On key released method.

```
<GridPane fx:controller="sample.Controller"
          xmlns:fx="http://javafx.com/fxml" alignment="center" hgap="10" vgap="10">
    <TextField fx:id="nameField" GridPane.columnIndex="0" GridPane.rowIndex="0"
    onKeyReleased="#handleKeyReleased"/>
    <Button fx:id="helloButton" text="Say Hello" GridPane.rowIndex="1" GridPane.columnIndex="0" onAction="#onButtonClicked"/>
    <Button fx:id="byeButton" text="Say Bye" GridPane.rowIndex="1" GridPane.columnIndex="1" onAction="#onButtonClicked"/>
</GridPane>
```



Check the state of the checkbox when checking it

```
public void handleChange() {
    System.out.println("The checkbox is " + (ourCheckBox.isSelected() ? "checked" : "not checked"));
}
```



Never expect users to be always predictable

When an event is raised, an associated error is run.

Go to the docs to see which events that a control can raise.

Javafx 8 control docs



195 UI thread

UI Thread sits and waits for user input. 

UI thread sees if the application is listening for that event and if so it dispatches the event to the event handler. Event handler will run on UI thread. Whilst an event handler is running, the UI is busy and is no longer paying attention to the user input. User can't interact with UI when it is busy. 



Event handler can put application in limbo.



Background thread takes a long time to work. Will inform the UI when it is done.



If more than one thread can alter a node, then the node's internal integrity could be compromised. Two processes running at the same time which conflict with eachother.

Whenever we want to work with nodes on the scene graph, we actually must do so on the JavaFX application thread.

Will use the run later method, going to put the runnable thread on a queue. Forces the runnable object to run on a UI thread.



```
@FXML
public void onButtonClicked(ActionEvent e) {

    if(e.getSource().equals(helloButton)) {
        System.out.println("Hello, " + nameField.getText());
    } else if (e.getSource().equals(byeButton)) {
        System.out.println("Bye, " + nameField.getText());
    }

    // create another process on your computer
    // will run in a background thread
    // put code you want executed in the run method

    Runnable task = new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(15000);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        ourLabel.setText("We did something!");
                    }
                });
            } catch (InterruptedException event) {
                // we don't care about this
            }
        }
    };
    
    new Thread(task).start();

    if(ourCheckBox.isSelected()) {
        nameField.clear();
        helloButton.setDisable(true);
        byeButton.setDisable(true);
    }

}
```



Background threads and UI threads

```
Runnable task = new Runnable() {
            @Override
            public void run() {
                try {

                    String s = Platform.isFxApplicationThread() ? "UI Thread" : "Background Thread";
                    System.out.println("I'm going to sleep on the: " + s);
                    Thread.sleep(10000);

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            String s = Platform.isFxApplicationThread() ? "UI Thread" : "Background Thread";
                            System.out.println("I'm updating the label on the: " + s);
                            ourLabel.setText("We did something");
                        }
                    });
                } catch (InterruptedException event) {
                    // we don't care about this
                }
            }
        };
```

Can read about concurrency in concurrency in JavaFX



Application for to do list.



Todo list will be for list of items. Add to the left side of the screen.



Populate list with list items

To display short description, override toString method.

getSelectedItem to get the item selected from the model.



```
<TextArea fx:id="itemDetailsTextArea" VBox.vgrow="ALWAYS"
```



Give as much room as possible to the text area. Will size the children to the preferrred height before giving all the other area to the text area.



Make sure the VBox background colour is white

```
<BorderPane fx:controller="sample.Controller"
            xmlns:fx="http://javafx.com/fxml">
    <left>
        <ListView fx:id="todoListView" onMouseClicked="#handleClickListView">

        </ListView>
    </left>
    <center>
        <VBox style="-fx-background-color: white">
            <TextArea fx:id="itemDetailsTextArea" VBox.vgrow="ALWAYS"/>
            <HBox>
                <Label text="Due: " style="-fx-background-color: white">
                    <font>
                        <Font name="Times New Roman bold" size="20"/>
                    </font>
                </Label>
                <Label fx:id="deadlineLabel" style="-fx-background-color: white">
                    <font>
                        <Font name="Times New Roman bold" size="20"/>
                    </font>
                </Label>
            </HBox>
        </VBox>

    </center>
</BorderPane>
```



Add listener so that it runs whenever the value of the item property is changed

When an event is raised its associated event handler is run. Override the change method and using an anonymous class. 



Used a datetime formatter



Users should be able to add items to the todo list. Would expect them to be saved after the application is closed.



Singleton has private constructor so no other class can create an instance. Can only create one singleton class. One instance of itself. Singleton usually contains a static method so that any class can get the single instance and call its method. Use a singleton when we want there ot be only one class created over the entire run of the application.

Singleton class create the one instance of itself. 

Add singleton class to the data model package.

Singletons usually has a static method so that any class can get its instance and call its methods.

```
public static TodoData getInstance() {
        return instance;
    }
```







Users would want to add items to the interface.

To do items should be loaded and stored to a flat file. We want to save items



Should probably use a database.

Delimiter is tab.

Can't instantiate the class from outside.