package spellpad.eventhandlers;

import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.TextArea;
import javafx.scene.web.HTMLEditor;

/**
 * @author Jesse
 */
public class SwitchEditorEventHandler implements EventHandler<ActionEvent> {

    Scene master = null;

    public SwitchEditorEventHandler(Scene master) {

        this.master = master;
    }

    @Override
    public void handle(ActionEvent event) {
        System.out.println("EVENT!");
        if (event == null || master == null) {
            return;
        }
        Control textArea = null;
        boolean isRichText = false;
        List<Node> children = master.getRoot().getChildrenUnmodifiable();
        for (Node child : children) {
            if (child instanceof HTMLEditor) {
                textArea = (Control) child;
                isRichText = true;
                break;
            } else if (child instanceof TextArea) {
                textArea = (Control) child;
                isRichText = false;
                break;
            }
        }
        if (textArea == null) {
            return;
        }
        if (isRichText) {
            switchToPlainText(textArea);
        } else {
            switchToRichText(textArea);
        }

    }

    private void switchToPlainText(Control textArea) {
        HTMLEditor editor = (HTMLEditor)textArea;
        String contents = editor.getHtmlText();
        /*
         * Conver to spellpad at this point
         */
        TextArea newTextArea = new TextArea();
        newTextArea.setText(contents);
        textArea = newTextArea;
    }

    private void switchToRichText(Control textArea) {
    }
}
