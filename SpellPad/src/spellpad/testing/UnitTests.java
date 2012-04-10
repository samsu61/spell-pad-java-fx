/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spellpad.testing;

import spellpad.testing.eventhandlers.OpenEventHandlerTests;



/**
 *
 * @author Jesse
 */
public class UnitTests {
    
    static public boolean RunTests(){
        
        return OpenEventHandlerTests() 
                && true
                && true;
    }
    
    static public boolean OpenEventHandlerTests(){
        
        return OpenEventHandlerTests.IsEventHandler()
                && OpenEventHandlerTests.CreateSuccessful()
                && OpenEventHandlerTests.HandleEvent_InputNull()
                && OpenEventHandlerTests.HandleEvent_InputNotNull_InstanceVariableNull()
                && OpenEventHandlerTests.Modify_Recreate_InstanceVariableNotNull()
                && OpenEventHandlerTests.HandleEvent_InputNull_InstanceVariableNotNull()
                && OpenEventHandlerTests.HandleEvent_InputNotNull_InstanceVariableNotNull()
                && OpenEventHandlerTests.HandleEvent_InputNotNull_InstanceVariableNotNull();
    }
}
