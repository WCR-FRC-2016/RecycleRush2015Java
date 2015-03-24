package org.usfirst.frc.team5492.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team5492.robot.Robot;
import org.usfirst.frc.team5492.robot.RobotMap;

/**
 *
 */
public class SetElevatorSetpoint extends Command {
	private double setpoint;

    public SetElevatorSetpoint(double setpoint) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.elevator);
    	if(setpoint > RobotMap.level_six)
    		setpoint = RobotMap.level_six;
    	if(setpoint < RobotMap.level_one)
    		setpoint = RobotMap.level_one;
    	this.setpoint = setpoint;    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.elevator.enable();
    	Robot.elevator.setSetpoint(setpoint);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.elevator.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
