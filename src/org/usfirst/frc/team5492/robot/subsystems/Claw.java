package org.usfirst.frc.team5492.robot.subsystems;

import org.usfirst.frc.team5492.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 */
public class Claw extends PIDSubsystem {
	private SpeedController Claw_Motor;
	private DigitalInput Claw_LS;
	private AnalogPotentiometer Claw_Pot;

    // Initialize your subsystem here
    public Claw() {
    	super(0,0,0);
    	Claw_Motor = new Talon(RobotMap.Claw_Motor_PWM);
    	Claw_LS = new DigitalInput(RobotMap.Claw_LS_DI);
    	Claw_Pot = new AnalogPotentiometer(RobotMap.Claw_Pot_AI);
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    
    public void log(){
    	SmartDashboard.putData("Claw Pot", (AnalogPotentiometer) Claw_Pot);
    }
    
    public boolean isGrabbing(){
    	return Claw_LS.get();
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	return Claw_Motor.get();
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	Claw_Motor.set(output);
    }
}
