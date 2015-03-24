package org.usfirst.frc.team5492.robot.subsystems;

import org.usfirst.frc.team5492.robot.RobotMap;
import org.usfirst.frc.team5492.robot.commands.ManualClaw;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.TalonSRX;
/**
 *	Contains all methods for Controlling Claw
 */
public class Claw extends PIDSubsystem {
	private PowerDistributionPanel pdp;
	private CANTalon Claw_Motor;
	private AnalogPotentiometer Claw_Pot;
	private SpeedController Claw_Motor_PWM;
	private double current;
	private double output;
	
	private static final double kP = 5.0, kI = 0.0, kD = 0.0;
    // Initialize your subsystem here
    public Claw() {
    	super(kP, kI, kD);
    	Claw_Motor_PWM = new TalonSRX(0);
    	setAbsoluteTolerance(0.005);
    	Claw_Motor = new CANTalon(RobotMap.Claw_Motor_CAN);
    	Claw_Pot = new AnalogPotentiometer(RobotMap.Claw_Pot_AI, 3600, 0);
    	current = pdp.getCurrent(RobotMap.claw_motor_current);
    	
    	LiveWindow.addActuator("Claw", "Motor", (TalonSRX) Claw_Motor_PWM);
    	LiveWindow.addSensor("Claw", "Pot", (AnalogPotentiometer)Claw_Pot);
    	LiveWindow.addActuator("Claw",  "PID", getPIDController());
    }
    
    public void manual(double setpoint){
    	Claw_Motor.set(setpoint);
    }
    
    public void initDefaultCommand() {
    	
    	//setDefaultCommand(new StopMaxClaw());
    	setDefaultCommand(new ManualClaw());
    }
    
    
    public void log(){
    	current = pdp.getCurrent(RobotMap.claw_motor_current);
    	SmartDashboard.putNumber("Claw Pot",  Claw_Pot.get());
    	SmartDashboard.putNumber("Claw Motor Current", current);
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
    	Claw_Motor.set(-output * .5);
    	Claw_Motor_PWM.set(-output * .5);
    }
    
    private void StopMaxClaw(){
    	if((current >= RobotMap.elevator_max_current || getPosition() >= RobotMap.max_claw) && output > 0){
    		output = 0;
    	}else if((current <= RobotMap.elevator_min_current || getPosition() <= RobotMap.min_claw) && output < 0){
    		output = 0;
    	}
    }
    
    public double pot(){
    	return Claw_Pot.get();
    }
    
    /**
     * Stops the claw motor from moving.
     */
    public void stop() {
        Claw_Motor.set(0);
        Claw_Motor_PWM.set(0);
    }
}
