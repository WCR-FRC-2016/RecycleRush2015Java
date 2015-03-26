package org.usfirst.frc.team5492.robot.commands;

import org.usfirst.frc.team5492.robot.Robot;
import org.usfirst.frc.team5492.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ManualClaw extends Command {
	
	boolean CloseClaw;
	boolean OpenClaw;

    public ManualClaw() {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.claw);
    	CloseClaw = false;
    	OpenClaw = false; 			
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double clawjoy = Robot.oi.getRightStick().getX();
    	/*if(clawjoy < 0 && Robot.claw.getPosition() >= RobotMap.min_claw) {
    		clawjoy = 0;
    	}else if(clawjoy > 0 && Robot.claw.getPosition() <= RobotMap.max_claw)
    		clawjoy = 0;
    	Robot.claw.manual(clawjoy);
    			clawjoy = 0;
    			*/
    	
    	CloseClaw = Robot.oi.getRightStick().getRawButton(1);
    	OpenClaw = Robot.oi.getRightStick().getRawButton(2);
    	
    	if(Robot.claw.getPosition() >= RobotMap.max_claw && CloseClaw) {
    		CloseClaw = false;
    	}else if(Robot.claw.getPosition()<= RobotMap.min_claw && OpenClaw)
    		OpenClaw = false;
    	if(CloseClaw)
    		Robot.claw.manual(-.65);
    	else if(OpenClaw)    		
    		Robot.claw.manual(.65);
    	else
    		Robot.claw.manual(0);
    		
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
