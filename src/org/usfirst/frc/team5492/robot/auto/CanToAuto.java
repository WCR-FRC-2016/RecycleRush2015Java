package org.usfirst.frc.team5492.robot.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team5492.robot.RobotMap;
import org.usfirst.frc.team5492.robot.commands.*;


/**
 * Moves Can only to auto zone
 * Assumes Robot is pointing at PS and claw is around can
 */
public class CanToAuto extends CommandGroup {
    
    public  CanToAuto() {
    	addSequential(new PickupCan());																									//Closes Claw
    	addParallel(new SetElevatorSetpoint(RobotMap.level_one + (RobotMap.level_one / 2)));			//Moves Elevator to 1.5
    	addSequential(new DriveForward(-7));																							//Drives back 7 feet
    }
}
