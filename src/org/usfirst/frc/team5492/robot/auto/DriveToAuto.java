package org.usfirst.frc.team5492.robot.auto;

import org.usfirst.frc.team5492.robot.commands.DriveForward;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Drives robot to auto
 * Assumes Robot is positioned in frone of auto zone
 */
public class DriveToAuto extends CommandGroup {
    
    public  DriveToAuto() {
    	addSequential(new DriveForward(4.5));				//Drives forward 4.5 feet
    }
}
