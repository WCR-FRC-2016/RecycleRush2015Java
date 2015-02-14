package org.usfirst.frc.team5492.robot.commands;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5492.robot.Robot;

/**
 * Closes the claw until a limit switch is triggered
 */
public class CloseClaw extends Command {
    
    public CloseClaw() {
        requires(Robot.claw);
    }

    // Called just before this Command runs the first time
    protected void initialize() {        
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.claw.close();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.claw.isClosed();
    }

    // Called once after isFinished returns true
    protected void end() {
        	Robot.claw.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}

