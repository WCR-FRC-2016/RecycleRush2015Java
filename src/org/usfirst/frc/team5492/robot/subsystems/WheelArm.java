package org.usfirst.frc.team5492.robot.subsystems;

import org.usfirst.frc.team5492.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *	Contains all methods for controlling WheelArm
 */
public class WheelArm extends PIDSubsystem {
	PowerDistributionPanel pdp;
	private CANTalon Wheel_Motor1;
	private CANTalon Wheel_Motor2;
	private CANTalon Wheel_Motor3;
	private CANTalon Wheel_Motor4;
	private AnalogPotentiometer Wheel_Pot1;
	private AnalogPotentiometer Wheel_Pot2;
	
	private static double motor1_current;
	private static double motor2_current;
	
	private static final double kP = 0.0, kI = 0.0, kD = 0.0;
    // Initialize your subsystem here
    public WheelArm() {
    	super("Wheel Arm", kP, kI, kD);
    	Wheel_Motor1 = new CANTalon(RobotMap.Wheel_Motor1_CAN);
        Wheel_Motor2 = new CANTalon(RobotMap.Wheel_Motor2_CAN);
        Wheel_Motor3 = new CANTalon(RobotMap.Wheel_Motor3_CAN);
        Wheel_Motor4 = new CANTalon(RobotMap.Wheel_Motor4_CAN);
        Wheel_Pot1 = new AnalogPotentiometer(RobotMap.Elevator_Pot_AI, 3600, 0);
        Wheel_Pot2 = new AnalogPotentiometer(RobotMap.Elevator_Pot_AI, 3600, 0);
        
        LiveWindow.addSensor("Wheel",  "Pot",  (AnalogPotentiometer) Wheel_Pot1);
        LiveWindow.addActuator("Wheel", "PID", getPIDController());
    }
    
    public void log(){
    	SmartDashboard.putData("Wheel Pot ", (AnalogPotentiometer) Wheel_Pot1);
    	SmartDashboard.putData("Wheel Pot ", (AnalogPotentiometer) Wheel_Pot2);
    	SmartDashboard.putNumber("Wheel Motor 1 Temp", Wheel_Motor1.getTemp());
    	SmartDashboard.putNumber("Wheel Motor 2 Temp", Wheel_Motor2.getTemp());
    	SmartDashboard.putNumber("Wheel Motor 3 Temp", Wheel_Motor1.getTemp());
    	SmartDashboard.putNumber("Wheel Motor 4 Temp", Wheel_Motor2.getTemp());
    	SmartDashboard.putNumber("Wheel Motor 1Current",motor1_current);
    	SmartDashboard.putNumber("Wheel Motor 2 Current", motor2_current);
    	SmartDashboard.putNumber("Wheel Motor 3 Current",motor1_current);
    	SmartDashboard.putNumber("Wheel Motor 4 Current", motor2_current);
    } 
    
    public void initDefaultCommand() {
    	 motor1_current = pdp.getCurrent(RobotMap.wheel_motor1_current);
         motor2_current = pdp.getCurrent(RobotMap.wheel_motor2_current);
         motor1_current = pdp.getCurrent(RobotMap.wheel_motor3_current);
         motor2_current = pdp.getCurrent(RobotMap.wheel_motor4_current);
    }
    
    protected double returnPIDInput() {
    	return Wheel_Pot1.get();
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	Wheel_Motor1.set(output);
    	Wheel_Motor2.set(output);
    }
    
    public void suckIn(){
    	double speed = .5;
    	Wheel_Motor3.set(speed);
    	Wheel_Motor4.set(-speed);
    }
    
    public void stopWheelArm(){
    	Wheel_Motor3.set(0);
    	Wheel_Motor4.set(0);
    }
    
}
