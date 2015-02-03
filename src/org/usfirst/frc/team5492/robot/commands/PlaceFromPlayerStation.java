package org.usfirst.frc.team5492.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PlaceFromPlayerStation extends CommandGroup {
    
    public  PlaceFromPlayerStation() {
    	addSequential(new SetElevatorSetpoint(2));
    	addSequential(new OpenClaw());    	
    }
}
