package org.usfirst.frc.team5492.robot.commands;

import org.usfirst.frc.team5492.robot.RobotMap;
import org.usfirst.frc.team5492.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetWheelSetpoint extends Command {
	private double setpoint;

    public SetWheelSetpoint(double setpoint) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.wheelarm);
    	if(setpoint > RobotMap.long_tote_wheel)
    		setpoint = RobotMap.long_tote_wheel;
    	if(setpoint < RobotMap.close_wheel);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.wheelarm.enable();
    	Robot.wheelarm.setSetpoint(setpoint);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.wheelarm.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
