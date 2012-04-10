/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spellpad.testing.eventhandlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import spellpad.eventhandlers.OpenEventHandler;

/**
 *
 * @author Jesse
 */
public class OpenEventHandlerTests {
    
    private static OpenEventHandler myTestee;
    private static TextArea myTestee_textArea;
    
    public static boolean IsEventHandler(){
        EventHandler<ActionEvent> eh = new OpenEventHandler(null);
        return true;
    }
    
    public static boolean CreateSuccessful(){
        if(IsEventHandler()){
            myTestee = new OpenEventHandler(null);
        }
        return true;
    }
    
    public static boolean HandleEvent_InputNull(){
        /**
         * area and input are null. Should return/do nothing.
         * No exception should be thrown.
         */
        try{
            myTestee.handle(null);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public static boolean HandleEvent_InputNotNull_InstanceVariableNull(){
        /**
         * are is null, input is not. Should return/do nothing.
         * No exception should be thrown
         */
        try{
            myTestee.handle(new ActionEvent()); 
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public static boolean Modify_Recreate_InstanceVariableNotNull(){
        myTestee_textArea = new TextArea();
//        myTestee = new OpenEventHandler(myTestee_textArea);
        return true;
    }
    
    public static boolean HandleEvent_InputNull_InstanceVariableNotNull(){
        /*
         * area is not null, input is null. Should return/do nothing.
         * No exception should be thrown
         */
        try{
            myTestee.handle(null);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    private static boolean HandleEvent_InputNotNull_InstanceVariableNotNull_Toggle = true;
    public static boolean HandleEvent_InputNotNull_InstanceVariableNotNull(){
        /*
         * area is not null, input is not null. Should offer a fileChooser.
         * This test should run twice - once for the decline, once for success.
         * During the instructed success phase, the text from the text document
         * should be dumped to the console.
         */
        if(HandleEvent_InputNotNull_InstanceVariableNotNull_Toggle){
            System.out.println("DIRECTIONS: DECLINE THIS POPUP");
            HandleEvent_InputNotNull_InstanceVariableNotNull_Toggle = 
                    !HandleEvent_InputNotNull_InstanceVariableNotNull_Toggle;
        }else{
            System.out.println("DIRECTIONS: ACCEPT THIS POPUP");
        }
        try{
            myTestee.handle(new ActionEvent());
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        if(!HandleEvent_InputNotNull_InstanceVariableNotNull_Toggle){
            String content = myTestee_textArea.getText();
            System.out.println("Content:");
            System.out.println(content);
        }
        return true;
    }
    
}
