package org.usfirst.frc.team5492.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5492.robot.Robot;
import org.usfirst.frc.team5492.robot.RobotMap;

/**
 *
 */
public class CheckLevelThree extends Command {
	private double elevatorjoy;

    public CheckLevelThree() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(Robot.elevator.getPosition() > RobotMap.level_three){
    		Robot.elevator.enable();
        	Robot.elevator.setSetpoint(RobotMap.level_three);
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	elevatorjoy = Robot.oi.getRightStick().getY();
        return Robot.elevator.onTarget() || Robot.elevator.getPosition() < RobotMap.level_three;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.elevator.disable();
    }
}
