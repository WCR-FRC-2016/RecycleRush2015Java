package org.usfirst.frc.team5492.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5492.robot.Robot;
/**
 * Given x amount of ft, the robot will strafe that far, of negative x amount, which will go backwards
 */
public class StrafeRight extends Command {
	double feet;
    public StrafeRight(double feet) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	this.feet = feet;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.strafeRight(feet);
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
