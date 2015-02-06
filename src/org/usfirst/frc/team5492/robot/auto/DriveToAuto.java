package org.usfirst.frc.team5492.robot.auto;

import org.usfirst.frc.team5492.robot.commands.DriveForward;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveToAuto extends CommandGroup {
    
    public  DriveToAuto() {
    	addSequential(new DriveForward(4.5));
    }
}
