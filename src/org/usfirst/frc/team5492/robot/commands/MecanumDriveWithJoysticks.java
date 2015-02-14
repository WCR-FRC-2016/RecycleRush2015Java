package org.usfirst.frc.team5492.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5492.robot.Robot;

/**
 * This gets input from the joysticks & then calls DriveTrain class
 */
public class MecanumDriveWithJoysticks extends Command {

    public MecanumDriveWithJoysticks() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double x = Robot.oi.getLeftStick().getRawAxis(3) - Robot.oi.getLeftStick().getRawAxis(2);
    	double y = Robot.oi.getLeftStick().getRawAxis(1);
    	double z = Robot.oi.getLeftStick().getRawAxis(4);
    	if(Math.abs(x) < .2)
    		x = 0;
    	if(Math.abs(y) < .2)
    		y = 0;
    	if(Math.abs(z) < .2)
    		z = 0;
    	Robot.drivetrain.drive(x, y, z, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.drive(0, 0, 0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
