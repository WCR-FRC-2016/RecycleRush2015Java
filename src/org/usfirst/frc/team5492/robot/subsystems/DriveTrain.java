package org.usfirst.frc.team5492.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.PIDController;

import org.usfirst.frc.team5492.robot.RobotMap;
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
    
    PIDController left_front_PID;
	PIDController left_back_PID;
	PIDController right_front_PID;
	PIDController right_back_PID;
	
	private static final double Kp = 0.3;
	private static final double Ki = 0.0;
	private static final double Kd = 0.0;
	private static final double Kr = 0.0; //1/MAX_RATE(ft/sec)
	
	public static final int kMaxNumberOfMotors = 4;
	
	protected final int m_invertedMotors[] = {1, 1, 1, 1};
	
    private BuiltInAccelerometer accelerometer;
    private Gyro gyro;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public DriveTrain(){
    	front_left_motor = new Talon(RobotMap.left_front_motor_PWM);
    	back_left_motor = new Talon(RobotMap.left_back_motor_PWM);
    	front_right_motor = new Talon(RobotMap.right_front_motor_PWM);
    	back_right_motor = new Talon(RobotMap.right_back_motor_PWM);
    	/*front_left_motor = new TalonSRX(RobotMap.left_front_motor_PWM);
    	back_left_motor = new TalonSRX(RobotMap.left_back_motor_PWM);
    	front_right_motor = new TalonSRX(RobotMap.right_front_motor_PWM);
    	back_right_motor = new TalonSRX(RobotMap.right_back_motor_PWM);*/
    	front_left_encoder = new Encoder(RobotMap.left_front_Aencoder, RobotMap.left_front_Bencoder, false, EncodingType.k2X);
    	back_left_encoder = new Encoder(RobotMap.left_back_Aencoder, RobotMap.left_back_Bencoder, false, EncodingType.k2X);
    	front_right_encoder = new Encoder(RobotMap.right_front_Aencoder, RobotMap.right_front_Bencoder, false, EncodingType.k2X);
    	back_right_encoder = new Encoder(RobotMap.right_back_Aencoder, RobotMap.right_back_Bencoder, false, EncodingType.k2X);
    	
    	front_left_encoder.setDistancePerPulse(2*3*3.14/360/12);
    	back_left_encoder.setDistancePerPulse(2*3*3.14/360/12);
    	front_right_encoder.setDistancePerPulse(2*3*3.14/360/12);
    	back_right_encoder.setDistancePerPulse(2*3*3.14/360/12);
    	
    	front_left_encoder.setSamplesToAverage(100);
    	back_left_encoder.setSamplesToAverage(100);
    	front_right_encoder.setSamplesToAverage(100);
    	back_right_encoder.setSamplesToAverage(100);
    	
    	reset();
    	
    	left_front_PID = new PIDController(Kp, Ki, Kd, Kr, front_left_encoder, front_left_motor);
    	left_back_PID = new PIDController(Kp, Ki, Kd, Kr, back_left_encoder, back_left_motor);
    	right_front_PID = new PIDController(Kp, Ki, Kd, Kr, front_right_encoder, front_left_motor);
    	right_back_PID = new PIDController(Kp, Ki, Kd, Kr, back_right_encoder, back_right_motor);
    	
    	left_front_PID.enable();
    	left_back_PID.enable();
    	right_front_PID.enable();
    	right_back_PID.enable();
    	
    	accelerometer = new BuiltInAccelerometer();
    	gyro = new Gyro(0);
    	
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
    	gyro.reset();
    	gyro.setSensitivity(.013);
    	setDefaultCommand(new MecanumDriveWithJoysticks());
    }
    
    public void log(){
    	SmartDashboard.putNumber("Front Left Distance", front_left_encoder.getDistance());
    	SmartDashboard.putNumber("Back Left Distance", back_left_encoder.getDistance());
    	SmartDashboard.putNumber("Front Right Distance", front_right_encoder.getDistance());
    	SmartDashboard.putNumber("Back Right Distance",  back_right_encoder.getDistance());
    }
    
    public void drive(double x, double x1, double y, double rotation, double gyroAngle){
    	//drive.mecanumDrive_Cartesian(x, y, rotation, 0);
    	double yIn = y;
    	double xIn = x;
    	xIn = x1;
    	yIn = -yIn;
    	double clockwise = rotation;
    	
    	double wheelSpeeds[] = new double[kMaxNumberOfMotors];
    	wheelSpeeds[RobotMap.left_front_motor_PWM] = xIn + yIn + clockwise;
    	wheelSpeeds[RobotMap.left_back_motor_PWM] = -xIn + yIn + clockwise;
    	wheelSpeeds[RobotMap.right_front_motor_PWM] = -xIn + yIn - clockwise;
    	wheelSpeeds[RobotMap.right_back_motor_PWM] = xIn + yIn - clockwise;
    	
    	normalize(wheelSpeeds);
    	
    	left_front_PID.setSetpoint(wheelSpeeds[RobotMap.left_front_motor_PWM]);
    	left_back_PID.setSetpoint(wheelSpeeds[RobotMap.left_back_motor_PWM]);
    	right_front_PID.setSetpoint(wheelSpeeds[RobotMap.right_front_motor_PWM]);
    	right_back_PID.setSetpoint(wheelSpeeds[RobotMap.right_back_motor_PWM]);
    	
    	/*front_left_motor.set(wheelSpeeds[RobotMap.left_front_motor_PWM] * m_invertedMotors[RobotMap.left_front_motor_PWM] * RobotMap.maxOutput);
    	back_left_motor.set(wheelSpeeds[RobotMap.left_back_motor_PWM] * m_invertedMotors[RobotMap.left_back_motor_PWM] * RobotMap.maxOutput);
    	front_right_motor.set(wheelSpeeds[RobotMap.right_front_motor_PWM] * m_invertedMotors[RobotMap.right_front_motor_PWM] * RobotMap.maxOutput);
    	back_right_motor.set(wheelSpeeds[RobotMap.right_back_motor_PWM] * m_invertedMotors[RobotMap.right_back_motor_PWM] * RobotMap.maxOutput);
    	*/	
    		
    }
    
    protected static void normalize(double wheelSpeeds[]){
    	double maxMagnitude =  Math.abs(wheelSpeeds[0]);
    	int i;
    	for(i = 1; i < kMaxNumberOfMotors;i++){
    		double temp = Math.abs(wheelSpeeds[i]);
    		if(maxMagnitude < temp)
    			maxMagnitude = temp;
    	}
    	if(maxMagnitude > 1.0){
    		for(i = 0;i < kMaxNumberOfMotors;i++){
    			wheelSpeeds[i] = wheelSpeeds[i] / maxMagnitude;
    		}
    	}
    }
    
    public void Stop(double gyroAngle, double Kp){
    	drive.drive(0, 0);
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
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	return gyro.getAngle();
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    }
}