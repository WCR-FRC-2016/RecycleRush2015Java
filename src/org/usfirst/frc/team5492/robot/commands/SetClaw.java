package org.usfirst.frc.team5492.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5492.robot.Robot;

/**
 *
 */
public class SetClaw extends Command {
	private double setpoint;
	
    public SetClaw(double setpoint) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.setpoint = setpoint;
    	requires(Robot.claw);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(this.setpoint >= Robot.claw.pot() - 5)
    		Robot.claw.manual(-.3);
    	else if(this.setpoint <= Robot.claw.pot() + 5)
    		Robot.claw.manual(.3);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return this.setpoint > Robot.claw.pot() - 5 && this.setpoint < Robot.claw.pot() + 5;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.claw.manual(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
