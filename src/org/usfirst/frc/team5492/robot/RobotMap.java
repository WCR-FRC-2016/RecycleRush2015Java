package org.usfirst.frc.team5492.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	//CANs
	//DriveTrain
	public static int front_left_motor_CAN = 4;
	public static int back_left_motor_CAN = 0;
	public static int front_right_motor_CAN = 1;
	public static int back_right_motor_CAN = 2;
	//Elevator
	public static int Elevator_Motor1_CAN = 0;
	public static int Elevator_Motor2_CAN = 0;
	//Claw
	public static int Claw_Motor_CAN = 0;
	//Wheel Arm
	public static int Wheel_Motor1_CAN = 0;
	public static int Wheel_Motor2_CAN = 0;
	public static int Wheel_Motor3_CAN = 0;
	public static int Wheel_Motor4_CAN = 0;
	
	//Currents
	//DriveTrain
	public static int front_left_current = 12;
	public static int back_left_current = 13;
	public static int front_right_current = 14;
	public static int back_right_current = 15;
	//Elevator
	public static int elevator_motor1_current = 2;
	public static int elevator_motor2_current = 1;
	//Claw
	public static int claw_motor_current = 3;
	//Wheel Arm
	public static int wheel_motor1_current = 4;
	public static int wheel_motor2_current = 5;
	public static int wheel_motor3_current = 6;
	public static int wheel_motor4_current = 7;
	
	//Analog Inputs
	//DriveTrain
	public static int gyro = 0;
	//Elevator
    public static int Elevator_Pot_AI = 1;
    //Claw
    public static int Claw_Pot_AI = 2;
    //Wheel Arm
    public static int Wheel_Pot_AI = 3;
    public static int Whel_Pot_AI = 4;
    
    //Setpoints for Elevator
    public static double level_one = 0;
    public static double level_two = 335.52;
    public static double level_three = 671.04;
    public static double level_four = 1006.56;
    public static double level_five = 1342.08;
    public static double level_six = 1677.6;
    
    //Setpoints for Claw
    public static double can = 0;
    public static double short_tote = 0;
    public static double long_tote = 0;
    public static double open_claw = 0;
    public static double close_claw = 0;
    public static double claw_grab = 0;
}
