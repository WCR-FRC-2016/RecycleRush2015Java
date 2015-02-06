package org.usfirst.frc.team5492.robot.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team5492.robot.commands.*;


/**
 *
 */
public class CanToAuto extends CommandGroup {
    
    public  CanToAuto() {
    	addSequential(new CloseClaw());
    	addParallel(new SetElevatorSetpoint(.5));
    	addSequential(new DriveForward(-7));
    }
}
