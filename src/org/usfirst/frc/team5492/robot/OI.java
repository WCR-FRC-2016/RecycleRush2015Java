package org.usfirst.frc.team5492.robot;

import edu.wpi.first.wpilibj.Joystick;

import org.usfirst.frc.team5492.robot.commands.*;

import edu.wpi.first.wpilibj.buttons.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
	Joystick leftStick = new Joystick(0);
    Joystick rightStick = new Joystick(1);
    
    public OI(){
    	Button OpenClaw = new JoystickButton(rightStick, 2);				//Thumb to Open Claw
        Button PickupTote = new JoystickButton(rightStick, 1);				//Trigger to Close Claw
        Button PickupCan = new JoystickButton(rightStick, 5);				//Trigger to Pickup Can
        Button OVERRIDE = new JoystickButton(rightStick, 11);				//#8 on stick, to switch to manual control in case something breaks
        PickupTote.whenPressed(new SetClawSetpoint(RobotMap.tote)); 
       OpenClaw.whenPressed(new OpenClaw());
       //PickupCan.whenPressed(new PickupCan());
       OVERRIDE.whileHeld(new ManualControl());
    }
	
	public Joystick getLeftStick() {
		return leftStick;
	}
	
	public Joystick getRightStick(){
		return rightStick;
	}
}

