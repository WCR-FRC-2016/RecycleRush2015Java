package org.usfirst.frc.team5492.robot.subsystems;

import org.usfirst.frc.team5492.robot.Robot;
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
	
	private static final double kP = .00875, kI = 0.000005, kD = 0.001;
	
	private static double motor1_current;
	private static double motor2_current;
	
	
    // Initialize your subsystem here
    public Elevator() {
    	super(kP, kI, kD);
        setAbsoluteTolerance(10);
        Elevator_Motor1 = new CANTalon(RobotMap.Elevator_Motor1_CAN);
        Elevator_Motor2 = new CANTalon(RobotMap.Elevator_Motor2_CAN);
        Elevator_Pot = new AnalogPotentiometer(RobotMap.Elevator_Pot_AI, -3600, 3600);        
        max_LS = new DigitalInput(RobotMap.elevator_max_LS);
        min_LS = new DigitalInput(RobotMap.elevator_min_LS);
        pdp = new PowerDistributionPanel();
    }
    
    public void manual(double setpoint){
    	Elevator_Motor1.set(setpoint);
    	Elevator_Motor2.set(setpoint);
    }
    
    public void initDefaultCommand() {
    	setDefaultCommand(new ManualElevator());      
    }
    
    public void log(){
    	 motor1_current = Elevator_Motor1.getOutputCurrent();
         motor2_current = Elevator_Motor2.getOutputCurrent();
    	SmartDashboard.putNumber("Elevator Pot ", Elevator_Pot.get());
    	SmartDashboard.putNumber("Elevator Motor Current",motor1_current);
    	SmartDashboard.putNumber("Elevator Motor 2 current", motor2_current);
    	if(getPIDController().getError() > 100)
    		getPIDController().setPID(kP, 0, kD);
    }
    
    public double getCurrent(){
    	double current;
    	if(motor1_current > motor2_current)
    		current = motor2_current;
    	else
    		current = motor1_current;
    	return current;
    }
    
    protected double returnPIDInput() {
    	return Elevator_Pot.get();
    }
    
    protected void usePIDOutput(double output) {    	
    	output = -output * .7;
    	this.output = output;
    	StopMaxElevator();
    	Elevator_Motor1.set(this.output);
    	Elevator_Motor2.set(this.output);    	
    }

    private void StopMaxElevator(){
    	double current;
    	if(motor1_current > motor2_current)
    		current = motor2_current;
    	else
    		current = motor1_current;
    	if(current > RobotMap.elevator_max_current)
    		output = 0;
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
