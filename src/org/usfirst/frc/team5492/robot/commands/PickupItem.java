package org.usfirst.frc.team5492.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PickupItem extends CommandGroup {
    
    public  PickupItem() {
		addSequential(new SetElevatorSetpoint(0));
		addSequential(new CloseClaw());
		addSequential(new SetElevatorSetpoint(1));
    }
}
