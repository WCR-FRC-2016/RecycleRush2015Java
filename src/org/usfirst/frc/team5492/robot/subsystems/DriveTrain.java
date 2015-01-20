package org.usfirst.frc.team5492.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Talon;

import org.usfirst.frc.team5492.robot.commands.MecanumDriveWithJoysticks;
/**
 *
 */
public class DriveTrain extends Subsystem {
    private SpeedController front_left_motor, back_left_motor,
    						front_right_motor, back_right_motor;
    
    private RobotDrive drive;
    private Encoder front_left_encoder, back_left_encoder,
    				front_right_encoder, back_right_encoder;
    private BuiltInAccelerometer accelerometer;
    private Gyro gyro;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public DriveTrain(){
    	super();
    	front_left_motor = new Talon(0);
    	back_left_motor = new Talon(1);
    	front_right_motor = new Talon(2);
    	back_right_motor = new Talon(3);
    	drive = new RobotDrive(front_left_motor, back_left_motor,
    						   front_right_motor, back_right_motor);
    	front_left_encoder = new Encoder(1, 2);
    	back_left_encoder = new Encoder(3, 4);
    	front_right_encoder = new Encoder(5, 6);
    	back_right_encoder = new Encoder(7, 8);
    	
    	accelerometer = new BuiltInAccelerometer();
    	gyro = new Gyro(9);
    	
    	LiveWindow.addActuator("Drive Train", "Front_Left Motor", (Talon) front_left_motor);
    	LiveWindow.addActuator("Drive Train", "Back Left Motor", (Talon) back_left_motor);
    	LiveWindow.addActuator("Drive Train", "Front Right Motor", (Talon) front_right_motor);
    	LiveWindow.addActuator("Drive Train", "Back Right Motor", (Talon) back_right_motor);
    	LiveWindow.addSensor("Drive Train", "Front Left Encoder", front_left_encoder);
    	LiveWindow.addSensor("Drive Train", "Back Left Encoder", back_left_encoder);
    	LiveWindow.addSensor("Drive Train", "Front Right Encoder", front_right_encoder);
    	LiveWindow.addSensor("Drive Train", "Back Right Encoder", front_left_encoder);
    	LiveWindow.addSensor("Drive Train", "Accelerometer", accelerometer);
    	LiveWindow.addSensor("Drive Train", "Gyro", gyro);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new MecanumDriveWithJoysticks());
    }
    
    public void log(){
    	SmartDashboard.putNumber("Front Left Distance", front_left_encoder.getDistance());
    	SmartDashboard.putNumber("Back Left Distance", back_left_encoder.getDistance());
    	SmartDashboard.putNumber("Front Right Distance", front_right_encoder.getDistance());
    	SmartDashboard.putNumber("Back Right Distance",  back_right_encoder.getDistance());
    }
    
    public void drive(double y, double x, double rotation, double gyroAngle){	
    	drive.mecanumDrive_Cartesian(x, y, rotation, gyroAngle);
    }
    
    public void Stop(double gyroAngle, double Kp){
    	drive.drive(0, -gyroAngle * Kp);
    }
    
    public double getHeading(){
    	return gyro.getAngle();
    }
    
    public void reset(){
    	gyro.reset();
    	front_left_encoder.reset();
    	back_left_encoder.reset();
		front_right_encoder.reset();
		back_right_encoder.reset();
    }
    
    public double getDistance(){
    	return (front_left_encoder.getDistance() + back_left_encoder.getDistance() + front_right_encoder.getDistance() + back_right_encoder.getDistance())/4;
    }
}