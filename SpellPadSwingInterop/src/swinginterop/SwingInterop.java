package swinginterop;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.CacheHint;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.web.HTMLEditor;
import javax.swing.*;
import spellpad.eventhandlers.OpenEventHandler;
import spellpad.eventhandlers.SaveEventHandler;
import spellpad.eventhandlers.SwitchEditorEventHandler;

public class SwingInterop extends JApplet {
    
    private static final int PANEL_WIDTH_INT = 675;
    private static final int PANEL_HEIGHT_INT = 400;
    private static JFXPanel chartFxPanel;
    

    public void init() {
        
        // create javafx panel for charts
        chartFxPanel = new JFXPanel();
        chartFxPanel.setPreferredSize(new Dimension(PANEL_WIDTH_INT, PANEL_HEIGHT_INT));
        
        add(chartFxPanel, BorderLayout.CENTER);
      
        
        // create JavaFX scene
        Platform.runLater(new Runnable() {
            public void run() {
                createScene();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                } catch (Exception e) {}
                
                JFrame frame = new JFrame("SpellPad");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JApplet applet = new SwingInterop();
                applet.init();

                frame.setContentPane(applet.getContentPane());

                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                applet.start();
            }
        });
    }

    private void createScene() {
        //Build root pane
        final BorderPane root = new BorderPane();
        //Build Menubar
        MenuBar menuBar = new MenuBar();
        Menu file = new Menu("File");
        Menu editor = new Menu("Editor");
        MenuItem switchEditor = new MenuItem("Switch Editor");
        switchEditor.setAccelerator(new KeyCodeCombination(
                KeyCode.F4));
        editor.getItems().add(switchEditor);

        MenuItem openItem = new MenuItem("Open");
        MenuItem saveItem = new MenuItem("Save");

        KeyCodeCombination controlO = new KeyCodeCombination(
                KeyCode.O,
                KeyCombination.CONTROL_DOWN);
        openItem.setAccelerator(controlO);

        KeyCodeCombination controlS = new KeyCodeCombination(
                KeyCode.S,
                KeyCombination.CONTROL_DOWN);
        saveItem.setAccelerator(controlS);

        HTMLEditor texty = new HTMLEditor();
        //texty.setOnKeyTyped(new OnKeyTyped());
        texty.setCacheHint(CacheHint.SPEED);
        //texty.getOnKeyTyped();
        //texty.setWrapText(true);

        openItem.setOnAction(new OpenEventHandler(texty));
        saveItem.setOnAction(new SaveEventHandler(texty));

        file.getItems().add(openItem);
        file.getItems().add(saveItem);
        menuBar.getMenus().add(file);
        menuBar.getMenus().add(editor);
        TreeView tree = new TreeView();
        tree.setPrefWidth(150.0);
        root.setTop(menuBar);
        root.setCenter(texty);
        root.setLeft(tree);
        
        switchEditor.setOnAction(new SwitchEditorEventHandler(chartFxPanel.getScene()));
        
        chartFxPanel.setScene(new Scene(root, PANEL_WIDTH_INT, PANEL_HEIGHT_INT)); 
    }
}
