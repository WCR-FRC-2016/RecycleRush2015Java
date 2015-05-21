package org.usfirst.frc.team5492.robot.subsystems;

import org.usfirst.frc.team5492.robot.RobotMap;
import org.usfirst.frc.team5492.robot.commands.ManualClaw;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.TalonSRX;
/**
 *	Contains all methods for Controlling Claw
 */
public class Claw extends PIDSubsystem {
	private PowerDistributionPanel pdp;
	private CANTalon Claw_Motor;
	private AnalogPotentiometer Claw_Pot;
	private double current;
	private double output;

	private static final double kP = 0.008, kI = 0.0, kD = 0.0;
    // Initialize your subsystem here
    public Claw() {
    	super(kP, kI, kD);
    	setAbsoluteTolerance(5);
    	Claw_Motor = new CANTalon(RobotMap.Claw_Motor_CAN);
    	Claw_Pot = new AnalogPotentiometer(RobotMap.Claw_Pot_AI, 3600, 0);//-3600, 3600);
    	pdp = new PowerDistributionPanel();
    	current = 0.0;
    }
    
    public void manual(double setpoint){
    	Claw_Motor.set(setpoint); 	
    }
    
    public void initDefaultCommand() {
    	setDefaultCommand(new ManualClaw());
    }
    
    
    public void log(){
    	current = Claw_Motor.getOutputCurrent();
    	SmartDashboard.putNumber("Claw Pot",  Claw_Pot.get());
    	SmartDashboard.putNumber("Claw Motor Current", current);
    }
    
    public double getCurrent(){
    	return Claw_Motor.getOutputCurrent();
    }
    
    public boolean isClosed(){
    	return getSetpoint() == RobotMap.min_claw;
    }
    
    public boolean isOpen(){
    	return getSetpoint() == RobotMap.open_claw;
    }
    
    protected double returnPIDInput() {
    	return Claw_Pot.get();
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	this.output = output;
    	StopMaxClaw();
    	Claw_Motor.set(output);
    }
    
    private void StopMaxClaw(){
    	if(current >= RobotMap.claw_max_current)
    		output = 0;
    }
    
    public double pot(){
    	return Claw_Pot.get();
    }
    
    /**
     * Stops the claw motor from moving.
     */
    public void stop() {
        Claw_Motor.set(0);
    }
}
