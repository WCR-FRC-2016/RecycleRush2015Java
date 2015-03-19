package org.usfirst.frc.team5492.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import org.usfirst.frc.team5492.robot.Robot;
import org.usfirst.frc.team5492.robot.RobotMap;

/**
 *
 */
public class StopMaxElevator extends Command {
	double current;
	PowerDistributionPanel pdp;
    public StopMaxElevator() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.elevator);
    	current = 0;
    	pdp = new PowerDistributionPanel();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	current = (pdp.getCurrent(RobotMap.elevator_motor1_current) + pdp.getCurrent(RobotMap.elevator_motor2_current))/2;
    	if(/*current > RobotMap.elevator_max_current ||*/ Robot.elevator.getMaxLS())
    		Robot.claw.stop();
    	if(/*current < RobotMap.elevator_min_current ||*/ Robot.elevator.getMinLS())
    		Robot.claw.stop();
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
