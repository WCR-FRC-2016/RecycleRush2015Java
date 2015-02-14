package org.usfirst.frc.team5492.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team5492.robot.RobotMap;

/**
 * For placing tote/can on ground
 */
public class PlaceItem extends CommandGroup {
    
    public  PlaceItem() {
    	addSequential(new SetElevatorSetpoint(RobotMap.level_one / 2));	//Moves to level .5
		addSequential(new OpenClaw());														//Opens claw
    }
}
