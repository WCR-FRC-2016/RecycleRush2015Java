package org.usfirst.frc.team5492.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team5492.robot.RobotMap;

/**
 * Moves claw to a set width in orde to pickup Short Tote
 */
public class PrepareToPickupShortTote extends CommandGroup {
    
    public  PrepareToPickupShortTote() {
    	addParallel(new SetClawSetpoint(RobotMap.short_tote));												//Opens Claw to Short Tote Length
    	addSequential(new SetElevatorSetpoint(RobotMap.level_one));		//Moves to level 1
    }
}
