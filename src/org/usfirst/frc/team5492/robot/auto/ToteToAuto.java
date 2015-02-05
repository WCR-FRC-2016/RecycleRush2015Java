package org.usfirst.frc.team5492.robot.auto;

import org.usfirst.frc.team5492.robot.commands.CloseClaw;
import org.usfirst.frc.team5492.robot.commands.DriveForward;
import org.usfirst.frc.team5492.robot.commands.SetElevatorSetpoint;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ToteToAuto extends CommandGroup {
    
    public  ToteToAuto() {
    	addSequential(new CloseClaw());
    	addParallel(new SetElevatorSetpoint(.5));
    	addSequential(new DriveForward(-7));
    }
}
