package org.usfirst.frc.team5492.robot.commands;

import org.usfirst.frc.team5492.robot.Robot;
import org.usfirst.frc.team5492.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ManualElevator extends Command {
	double elevatorjoy;

    public ManualElevator() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.elevator);
    	elevatorjoy = 0;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	elevatorjoy = -Robot.oi.getRightStick().getRawAxis(1); 
    	
    	/*if((current >= RobotMap.elevator_max_current || getPosition() >= RobotMap.max_claw) && output < 0){
    		output = 0;
    	}else if((current <= RobotMap.elevator_min_current || getPosition() <= RobotMap.min_claw) && output > 0){
    		output = 0;
    	}*/
    	if(Robot.elevator.getMaxLS() && elevatorjoy < 0) {
    		elevatorjoy = 0;
    	}else if(Robot.elevator.getMinLS() && elevatorjoy > 0)
    		elevatorjoy = 0;
    	Robot.elevator.manual(elevatorjoy);
    			elevatorjoy = 0;    	
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
