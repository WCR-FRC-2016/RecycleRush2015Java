package org.usfirst.frc.team5492.robot.commands;

import org.usfirst.frc.team5492.robot.Robot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import org.usfirst.frc.team5492.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ManualElevatorPID extends Command {
	double elevatorjoy;
	PowerDistributionPanel pdp;

    public ManualElevatorPID() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.elevator);
    	elevatorjoy = 0;
    	pdp = new PowerDistributionPanel();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	elevatorjoy = -Robot.oi.getRightStick().getRawAxis(1); 
    	double current = pdp.getCurrent(RobotMap.elevator_motor1_current);
    	
    	//if(current >= RobotMap.elevator_max_current)
    			//elevatorjoy = 0;
    	if(Robot.elevator.getPosition() < RobotMap.min_elevator && elevatorjoy > 0) {
    		elevatorjoy = 0;
    	}else if(Robot.elevator.getPosition() > RobotMap.max_elevator && elevatorjoy < 0)
    		elevatorjoy = 0;
    	Robot.elevator.manual(elevatorjoy); 	
    }
    	

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return elevatorjoy == 0;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.elevator.setSetpoint(Robot.elevator.getPosition());
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
