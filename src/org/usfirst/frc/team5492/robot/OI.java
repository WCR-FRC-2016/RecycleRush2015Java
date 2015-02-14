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
    	Button CloseClaw = new JoystickButton(rightStick, 1);
        Button OpenClaw = new JoystickButton(rightStick, 2);
        Button PrepareLong = new JoystickButton(rightStick, 5);
        Button PrepareShort = new JoystickButton(rightStick, 3);
        double Elevator = rightStick.getRawAxis(4) - rightStick.getRawAxis(5);
        Button PickupItem = new JoystickButton(rightStick, 6);
        Button PickupAnotherTote = new JoystickButton(rightStick, 7);
        Button PickupFromPS = new JoystickButton(rightStick, 9);
        Button OVERRIDE = new JoystickButton(rightStick, 11);
        
        
        CloseClaw.whenPressed(new CloseClaw()); 
        OpenClaw.whenPressed(new OpenClaw());
        PrepareLong.whenPressed(new PrepareToPickupLongTote());
        PrepareShort.whenPressed(new PrepareToPickupShortTote());
        if(Elevator == -1)
        	Robot.elevator.setSetpoint(Robot.elevator.getSetpoint() + 1);
        if(Elevator == 1)
        	Robot.elevator.setSetpoint(Robot.elevator.getSetpoint() - 1);
        PickupItem.whenPressed(new PickupItem());
        PickupAnotherTote.whenPressed(new PickupAnotherTote());
        PickupFromPS.whenPressed(new PickupFromStation());
        OVERRIDE.whenPressed(new ManualControl());
    }
    
    
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
	
	public Joystick getLeftStick() {
		return leftStick;
	}
	
	public Joystick getRightStick(){
		return rightStick;
	}
}

