package org.usfirst.frc.team5492.robot.subsystems;

import org.usfirst.frc.team5492.robot.Robot;
import org.usfirst.frc.team5492.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

/**
 *
 */
public class Claw extends PIDSubsystem {
	PowerDistributionPanel pdp;
	private CANTalon Claw_Motor;
	private DigitalInput Claw_LS;
	private AnalogPotentiometer Claw_Pot;
	
	private static final double kP = 0.0, kI = 0.0, kD = 0.0;

    // Initialize your subsystem here
    public Claw() {
    	super(kP, kI, kD);
    	Claw_Motor = new CANTalon(RobotMap.Claw_Motor_CAN);
    	Claw_LS = new DigitalInput(RobotMap.Claw_LS_DI);
    	Claw_Pot = new AnalogPotentiometer(RobotMap.Claw_Pot_AI, 1);
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	LiveWindow.addSensor("Claw", "Pot", (AnalogPotentiometer)Claw_Pot);
    	LiveWindow.addActuator("Claw",  "PID", getPIDController());
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    
    public void log(){
    	SmartDashboard.putData("Claw Pot", (AnalogPotentiometer) Claw_Pot);
    	SmartDashboard.putNumber("Claw Motor Temp",  Claw_Motor.getTemp());
    	SmartDashboard.putNumber("Claw Motor Current", pdp.getCurrent(RobotMap.claw_motor_current));
    }
    
    public boolean isGrabbing(){
    	return Claw_LS.get();
    }
    
    public boolean isClosed(){
    	return getSetpoint() == 0;
    }
    
    public boolean isOpen(){
    	return getSetpoint() == .5;
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	return Claw_Pot.get();
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	Claw_Motor.set(output);
    }
    
    /**
     * Set the claw motor to move in the open direction.
     */
    public void open() {
        setSetpoint(.5);
    }

    /**
     * Set the claw motor to move in the close direction.
     */
    public void close() {
        setSetpoint(0);
    }
    
    /**
     * Stops the claw motor from moving.
     */
    public void stop() {
        Claw_Motor.set(0);
    }
}
