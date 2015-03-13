package org.usfirst.frc.team5492.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5492.robot.Robot;

/**
 * Mainly for Auton - you input  certain amount of feet to drive forward or negative feet to drive back
 */
public class DriveForward extends Command {
	double feet;
	int count;
    public DriveForward(double feet) {
    	requires(Robot.drivetrain);
    	this.feet = feet;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.driveForward(feet);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
