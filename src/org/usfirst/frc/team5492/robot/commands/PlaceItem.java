package org.usfirst.frc.team5492.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team5492.robot.RobotMap;

/**
 * For placing tote/can on ground
 */
public class PlaceItem extends CommandGroup {
    
    public  PlaceItem() {
    	addSequential(new SetElevatorSetpoint(RobotMap.level_one));	    //Moves to level 1
		addSequential(new OpenClaw());														//Opens claw
    }
}
