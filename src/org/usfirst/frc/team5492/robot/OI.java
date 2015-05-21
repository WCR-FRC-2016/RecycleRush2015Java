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
    	Button OpenClaw = new JoystickButton(rightStick, 3);				//Thumb to Open Claw2
        Button PickupTote = new JoystickButton(rightStick, 5);				//Trigger to Close Claw1
        Button PickupCan = new JoystickButton(rightStick, 6);				//Trigger to Pickup Can5
        Button OVERRIDE = new JoystickButton(rightStick, 11);				//#8 on stick, to switch to manual control in case something breaks
        Button Player_station = new JoystickButton(rightStick, 4);
        Button up = new JoystickButton(rightStick, 4);
        Button level1 = new JoystickButton(rightStick, 7);
        Button level2 = new JoystickButton(rightStick, 8);
        Button level3 = new JoystickButton(rightStick, 9);
        Button level4 = new JoystickButton(rightStick, 10);
        Button level5 = new JoystickButton(rightStick, 12);
       //Player_station.whenPressed(new PickupFromStation());
        //PickupTote.whenPressed(new SetClawSetpoint(RobotMap.tote)); 
       //OpenClaw.whenPressed(new OpenClaw());
       //PickupCan.whenPressed(new SetClawSetpoint(RobotMap.can));
       //OVERRIDE.whileHeld(new ManualControl());
       //up.whenPressed(new SetElevatorSetpoint(RobotMap.level_three));        
    }
	
	public Joystick getLeftStick() {
		return leftStick;
	}
	
	public Joystick getRightStick(){
		return rightStick;
	}
}

