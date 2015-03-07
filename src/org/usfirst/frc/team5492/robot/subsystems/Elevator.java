package org.usfirst.frc.team5492.robot.subsystems;

import org.usfirst.frc.team5492.robot.RobotMap;
import org.usfirst.frc.team5492.robot.Robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

/**
 *	Contains all methods for controlling Elevator
 */
public class Elevator extends PIDSubsystem {
	PowerDistributionPanel pdp;
	private CANTalon Elevator_Motor1;
	private CANTalon Elevator_Motor2;
	private Potentiometer Elevator_Pot;
	
	private static final double kP = 0.0, kI = 0.0, kD = 0.0;
	
	private static double motor1_current;
	private static double motor2_current;
	
	
    // Initialize your subsystem here
    public Elevator() {
    	super(kP, kI, kD);
        setAbsoluteTolerance(0.005);
        Elevator_Motor1 = new CANTalon(RobotMap.Elevator_Motor1_CAN);
        Elevator_Motor2 = new CANTalon(RobotMap.Elevator_Motor2_CAN);
        Elevator_Pot = new AnalogPotentiometer(RobotMap.Elevator_Pot_AI, 3600, 0);
        
        LiveWindow.addSensor("Elevator",  "Pot",  (AnalogPotentiometer) Elevator_Pot);
        LiveWindow.addActuator("Elevator", "PID", getPIDController());
    }
    
    public void manual(double setpoint){
    	Elevator_Motor1.set(setpoint);
    	Elevator_Motor2.set(setpoint);
    }
    
    public void initDefaultCommand() {
    	manual(Robot.oi.getRightStick().getRawAxis(1));
        motor1_current = pdp.getCurrent(RobotMap.elevator_motor1_current);
        motor2_current = pdp.getCurrent(RobotMap.elevator_motor2_current);
    }
    
    public void log(){
    	SmartDashboard.putData("Elevator Pot ", (AnalogPotentiometer) Elevator_Pot);
    	SmartDashboard.putNumber("Elevator Motor 1 Temp", Elevator_Motor1.getTemp());
    	SmartDashboard.putNumber("Elevator Motor 2 Temp", Elevator_Motor2.getTemp());
    	SmartDashboard.putNumber("Elevator Motor Current",motor1_current);
    	SmartDashboard.putNumber("Eleevator Motor 2 current", motor2_current);
    }
    
    protected double returnPIDInput() {
    	return Elevator_Pot.get();
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	Elevator_Motor1.set(output);
    	Elevator_Motor2.set(output);
    }
}
