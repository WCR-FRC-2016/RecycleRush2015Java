package org.usfirst.frc.team5492.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team5492.robot.RobotMap;

/**
 * Moves claw to a set width in orde to pickup Long Tote
 */
public class PrepareToPickupLongTote extends CommandGroup {
    
    public  PrepareToPickupLongTote() {
    	addParallel(new SetClawSetpoint(RobotMap.long_tote));											//Opens Claw to Long Tote length
    	addSequential(new SetElevatorSetpoint(RobotMap.level_one));								//Moves to level 1
    }
}
