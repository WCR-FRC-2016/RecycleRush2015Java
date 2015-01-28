package org.usfirst.frc.team5492.robot.subsystems;

import org.usfirst.frc.team5492.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 */
public class Elevator extends PIDSubsystem {
	private SpeedController Elevator_Motor;
	private Potentiometer Elevator_Pot;
	
	private static final double kP = 0.0, kI = 0.0, kD = 0.0;
	
	
    // Initialize your subsystem here
    public Elevator() {
    	super(kP, kI, kD);
        setAbsoluteTolerance(0.005);
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
        Elevator_Motor = new Talon(RobotMap.Elevator_Motor_PWM);
        Elevator_Pot = new AnalogPotentiometer(RobotMap.Elevator_Pot_AI);
        
        LiveWindow.addActuator("Elevator", "Elevator Motor", (Talon) Elevator_Motor);
        LiveWindow.addSensor("Elevator",  "Pot",  (AnalogPotentiometer) Elevator_Pot);
        LiveWindow.addActuator("Elevator", "PID", getPIDController());
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void log(){
    	SmartDashboard.putData("Elevator Pot", (AnalogPotentiometer) Elevator_Pot);
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	return Elevator_Pot.get();
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	Elevator_Motor.set(output);
    }
}
