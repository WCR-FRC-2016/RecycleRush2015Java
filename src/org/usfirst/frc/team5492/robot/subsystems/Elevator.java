package org.usfirst.frc.team5492.robot.subsystems;

import org.usfirst.frc.team5492.robot.RobotMap;
import org.usfirst.frc.team5492.robot.commands.ManualElevator;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.TalonSRX;

/**
 *	Contains all methods for controlling Elevator
 */
public class Elevator extends PIDSubsystem {
	private PowerDistributionPanel pdp;
	private CANTalon Elevator_Motor1;
	private CANTalon Elevator_Motor2;
	private Potentiometer Elevator_Pot;
	private DigitalInput max_LS, min_LS;
	private double output;
	
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
        max_LS = new DigitalInput(RobotMap.elevator_max_LS);
        min_LS = new DigitalInput(RobotMap.elevator_min_LS);
    }
    
    public void manual(double setpoint){
    	Elevator_Motor1.set(setpoint);
    	Elevator_Motor2.set(setpoint);
    }
    
    public void initDefaultCommand() {
    	setDefaultCommand(new ManualElevator());      
    }
    
    public void log(){
    	 motor1_current = pdp.getCurrent(RobotMap.elevator_motor1_current);
         motor2_current = pdp.getCurrent(RobotMap.elevator_motor2_current);
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
    	this.output = output;
    	StopMaxElevator();
    	Elevator_Motor1.set(output);
    	Elevator_Motor2.set(output);
    }

    private void StopMaxElevator(){
    	double current;
    	if(motor1_current > motor2_current)
    		current = motor2_current;
    	else
    		current = motor1_current;
    	
    	if((current >= RobotMap.elevator_max_current || getPosition() >= RobotMap.max_elevator || getMaxLS()) && output > 0){
    		output = 0;
    	}else if((current <= RobotMap.elevator_min_current || getPosition() <= RobotMap.min_elevator || getMinLS()) && output < 0){
    		output = 0;
    	}
    }
    
    public boolean getMaxLS(){
    	return max_LS.get();
    }
    
    public boolean getMinLS(){
    	return min_LS.get();
    }
    
    public void stop() {
        Elevator_Motor1.set(0);
        Elevator_Motor2.set(0);
    }
}
