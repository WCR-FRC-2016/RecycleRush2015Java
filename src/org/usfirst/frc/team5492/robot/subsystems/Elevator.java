package org.usfirst.frc.team5492.robot.subsystems;

import org.usfirst.frc.team5492.robot.RobotMap;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 */
public class Elevator extends PIDSubsystem {
	private SpeedController Elevator_Motor;
	private Potentiometer pot;
	
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
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	return 0.0;
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    }
}
