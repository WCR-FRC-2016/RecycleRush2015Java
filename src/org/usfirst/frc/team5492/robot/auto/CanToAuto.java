package org.usfirst.frc.team5492.robot.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team5492.robot.RobotMap;
import org.usfirst.frc.team5492.robot.commands.*;


/**
 * Moves Can only to auto zone
 * Assumes Robot is pointing at PS and claw is around can
 */
public class CanToAuto extends CommandGroup {
    
    public  CanToAuto() {
    	addSequential(new TimeElevator(.3, -.5));
    	addSequential(new SetClawSetpoint(RobotMap.can));																	//Closes Claw
    	addSequential(new TimeElevator(1, -.5));
    	addSequential(new TimeDrive(2.5, .4));																						//Drives back    	
    }
}
