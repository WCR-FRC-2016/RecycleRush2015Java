package org.usfirst.frc.team5492.robot.subsystems;

import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Gyro;

import org.usfirst.frc.team5492.robot.RobotMap;
import org.usfirst.frc.team5492.robot.commands.MecanumDriveWithJoysticks;
/**
 *	Contains all methods related to the DriveTrain
 */
public class DriveTrain extends Subsystem {
    private CANTalon front_left_motor, back_left_motor,
    						front_right_motor, back_right_motor;
    
    private RobotDrive drive;
    double x, y, z;
    double current1, current2, current3, current4;
    PowerDistributionPanel pdp;
	
	private static final double Kp = 0.4;
	private static final double Ki = 0.003;
	private static final double Kd = 10.0;
	private static final int izone = 50;
    private Gyro gyro;
    
    double wheelSpeeds[] = new double[4];
    
    static double kMaxNumberOfMotors = 4;
    public DriveTrain(){
    	drive = new RobotDrive(front_left_motor, back_left_motor,
				front_right_motor, back_right_motor);
    	drive.setInvertedMotor(MotorType.kFrontRight, true);											// invert the left side motors
    	drive.setInvertedMotor(MotorType.kRearRight, true);	
    	front_left_motor = new CANTalon(RobotMap.front_left_motor_CAN);
    	back_left_motor = new CANTalon(RobotMap.back_left_motor_CAN);
    	front_right_motor = new CANTalon(RobotMap.front_right_motor_CAN);
    	back_right_motor = new CANTalon(RobotMap.back_right_motor_CAN);
    	front_right_motor.reverseOutput(true);																	// invert the left side motors
    	back_right_motor.reverseOutput(true);																	// you may need to change or remove this to match your robot
    	
    	
    	gyro = new Gyro(RobotMap.gyro);
    	
    	drivePosition();																											//Sets up control mode(position)
    	
    	front_left_motor.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    	back_left_motor.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    	front_right_motor.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    	back_right_motor.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    	
    	
    	setPIDs();
    }

    public void initDefaultCommand() {
    	 setDefaultCommand(new MecanumDriveWithJoysticks());									//Gets joystick input
    	 current1 = pdp.getCurrent(RobotMap.front_left_current);
         current2 = pdp.getCurrent(RobotMap.back_left_current);
         current3 = pdp.getCurrent(RobotMap.front_right_current);
         current4 = pdp.getCurrent(RobotMap.back_right_current);
    }
    
  //logs sensors
    public void log(){
    	//Joystick Inputs
    	SmartDashboard.putNumber("Joystick X", x);
        SmartDashboard.putNumber("Joystick Y", y);
        SmartDashboard.putNumber("Joystick Rotation", z);
        //Velocity values
        SmartDashboard.putNumber("Front Left Velocity", front_left_motor.getEncVelocity()/wheelSpeeds[RobotMap.front_left_motor_CAN]);
        //SmartDashboard.putNumber("Back Left Velocity", back_left_motor.getEncVelocity()/wheelSpeeds[RobotMap.back_left_motor_CAN]);
        //SmartDashboard.putNumber("Front Right Velocity", front_right_motor.getEncVelocity()/wheelSpeeds[RobotMap.front_right_motor_CAN]);
        SmartDashboard.putNumber("Back Right Velocity", back_right_motor.getEncVelocity()/wheelSpeeds[RobotMap.back_right_motor_CAN]);
        //Talon Temps
        SmartDashboard.putNumber("Front Left Temp", front_left_motor.getTemp());
        SmartDashboard.putNumber("Back Left Temp", front_left_motor.getTemp());
        SmartDashboard.putNumber("Front Right Temp", front_left_motor.getTemp());
        SmartDashboard.putNumber("Back Right Temp", front_left_motor.getTemp());
        //Talon Currents
        SmartDashboard.putNumber("Front Left Current", current1);
        SmartDashboard.putNumber("Back Left Current", current2);
        SmartDashboard.putNumber("Front Right Current", current3);
        SmartDashboard.putNumber("Back Right Current", current4);
    }
    
    public void drive(double x, double y, double z, double gyroAngle){
    	drive.mecanumDrive_Cartesian(x, y, z, gyroAngle);
    }
    
    public void strafeRight(double feet){
    	feet = feet +  (feet * .33);
    	front_left_motor.set(findTicks(feet));
        //back_left_motor.set(findTicks(-feet));
        back_left_motor.set(-(front_left_motor.getEncVelocity()/1500));
    	//front_right_motor.set(findTicks(-feet));
        front_right_motor.set(-(back_right_motor.getEncVelocity()/1500));
        back_right_motor.set(findTicks(feet));
    }
    
    public void driveForward(double feet){
    	front_left_motor.set(findTicks(feet));
        back_left_motor.set(findTicks(feet));
        front_right_motor.set(findTicks(feet));
        back_right_motor.set(findTicks(feet));
        
    }
    
    public void drivePosition(){
    	setPIDs();
    	front_left_motor.changeControlMode(CANTalon.ControlMode.Position);
    	//back_left_motor.changeControlMode(CANTalon.ControlMode.Position);
    	front_right_motor.changeControlMode(CANTalon.ControlMode.Position);
    	//back_right_motor.changeControlMode(CANTalon.ControlMode.Position);
    }
    
    public void driveSpeed(){
    	setPIDs();
    	front_left_motor.changeControlMode(CANTalon.ControlMode.Speed);
    	//back_left_motor.changeControlMode(CANTalon.ControlMode.Speed);
    	//front_right_motor.changeControlMode(CANTalon.ControlMode.Speed);
    	back_right_motor.changeControlMode(CANTalon.ControlMode.Speed);
    }
    
    public void Stop(){
    	drive.stopMotor();
    }
    
    public double getHeading(){
    	return gyro.getAngle();
    }
    
    public void reset(){
    	gyro.reset();
    }
    
    private double findTicks(double feet){
    	return (-feet * 12 * 360) / (2 * Math.PI * 3);			//Convert feet to ticks, 3 is radius of wheel
    }
    
    private void setPIDs(){
    	front_left_motor.setPID(Kp, Ki, Kd);
    	back_left_motor.setPID(Kp, Ki, Kd);
    	front_right_motor.setPID(Kp, Ki, Kd);
    	back_right_motor.setPID(Kp, Ki, Kd);
    	front_left_motor.setIZone(izone);
    	back_left_motor.setIZone(izone);
    	front_right_motor.setIZone(izone);
    	back_right_motor.setIZone(izone);
    }
}