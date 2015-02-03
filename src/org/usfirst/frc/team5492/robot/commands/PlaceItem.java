package org.usfirst.frc.team5492.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PlaceItem extends CommandGroup {
    
    public  PlaceItem() {
    	addSequential(new SetElevatorSetpoint(.5));
		addSequential(new OpenClaw());
    }
}
