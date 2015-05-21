package org.usfirst.frc.team5492.robot.commands;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team5492.robot.Robot;
import org.usfirst.frc.team5492.robot.RobotMap;
/**
 *
 */
public class ManualControl extends Command {
	PowerDistributionPanel pdp;
    public ManualControl() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.claw);
    	requires(Robot.elevator);
    	pdp = new PowerDistributionPanel();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {    	
    	boolean CloseClaw = Robot.oi.getRightStick().getRawButton(1);
    	boolean OpenClaw = Robot.oi.getRightStick().getRawButton(2);
    	double claw_current = pdp.getCurrent(RobotMap.claw_motor_current);
    	
    	if(claw_current > RobotMap.claw_max_current){
    		OpenClaw = false;
    		CloseClaw = false;
    	}
    	
    	if(CloseClaw && claw_current < RobotMap.claw_max_current)
    		Robot.claw.manual(-.65);
    	else if(OpenClaw && claw_current < RobotMap.claw_max_current)    		
    		Robot.claw.manual(.65);
    	else
    		Robot.claw.manual(0);
    	
    	double elevatorjoy = -Robot.oi.getRightStick().getRawAxis(1); 
    	double elevator_current = pdp.getCurrent(RobotMap.elevator_motor1_current);    	
    	if(elevator_current < RobotMap.elevator_max_current)
    		Robot.elevator.manual(elevatorjoy); 	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
