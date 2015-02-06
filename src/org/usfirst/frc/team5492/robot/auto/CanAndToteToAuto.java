package org.usfirst.frc.team5492.robot.auto;

import org.usfirst.frc.team5492.robot.commands.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CanAndToteToAuto extends CommandGroup {
    
    public  CanAndToteToAuto() {
    	addSequential(new CloseClaw());
    	addSequential(new SetElevatorSetpoint(1.5));
    	addSequential(new StrafeRight(-2));
    	addParallel(new OpenClaw());
    	addSequential(new PickupItem());
    	addSequential(new DriveForward(-7));
    }
}
