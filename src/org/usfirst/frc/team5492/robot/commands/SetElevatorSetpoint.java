package org.usfirst.frc.team5492.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team5492.robot.Robot;
import org.usfirst.frc.team5492.robot.RobotMap;

/**
 *
 */
public class SetElevatorSetpoint extends Command {
	private double setpoint;
	private double elevatorjoy;

    public SetElevatorSetpoint(double setpoint) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.elevator);
    	setpoint = setpoint;
    	if(setpoint > RobotMap.level_six)
    		setpoint = RobotMap.level_six;
    	if(setpoint < RobotMap.level_one)
    		setpoint = RobotMap.level_one;
    	this.setpoint = setpoint;    	
    	setTimeout(5);
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
    	elevatorjoy = Robot.oi.getRightStick().getY();
        return Robot.elevator.onTarget() || isTimedOut() || elevatorjoy < -.15 || elevatorjoy > .15;
    }

    // Called once after isFinished returns true
    protected void end() {
    	elevatorjoy = Robot.oi.getRightStick().getY();
    	if(elevatorjoy < -.15 || elevatorjoy > .15)
    		Robot.elevator.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.elevator.disable();
    }
}
