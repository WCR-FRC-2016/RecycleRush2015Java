package org.usfirst.frc.team5492.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
	public static int maxOutput = 1;
	//PWMs
	//DriveTrain
	public static int left_front_motor_PWM = 0;
	public static int left_back_motor_PWM = 1;
	public static int right_front_motor_PWM = 2;
	public static int right_back_motor_PWM = 3;
	//Elevator
	public static int Elevator_Motor_PWM = 4;
	//Claw
	public static int Claw_Motor_PWM = 5;
	
	//Analog Inputs
	//Elevator
    public static int Elevator_Pot_AI = 0;
    //Claw
    public static int Claw_Pot_AI = 1;
    
    //Digital Inputs
    //Claw
    public static int Claw_LS_DI = 1;
    //Encoders
    public static int left_front_Aencoder = 0;
    public static int left_front_Bencoder = 1;
    public static int left_back_Aencoder = 2;
    public static int left_back_Bencoder = 3;
    public static int right_front_Aencoder = 4;
    public static int right_front_Bencoder = 5;
    public static int right_back_Aencoder = 6;
    public static int right_back_Bencoder = 7;
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
}
