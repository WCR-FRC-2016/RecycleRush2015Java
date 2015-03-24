package org.usfirst.frc.team5492.robot.auto;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5492.robot.Robot;

/**
 *
 */
public class RobotToAuto extends Command {
	long startTime;
	long elapsedTime;

    public RobotToAuto() {
    	requires(Robot.drivetrain);
    	setTimeout(2.825);
    }

    // Called just before this Command runs the first time
    protected void initialize() {    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {   
    	Robot.drivetrain.drive(0, -.4, 0, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() { 
    	Robot.drivetrain.drive(0, 0, 0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
