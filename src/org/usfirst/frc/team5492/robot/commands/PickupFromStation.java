package org.usfirst.frc.team5492.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PickupFromStation extends CommandGroup {
    
    public  PickupFromStation() {
    	addSequential(new SetElevatorSetpoint(1));
    	addSequential(new CloseClaw());
    	addSequential(new SetElevatorSetpoint(3));
    }
}
