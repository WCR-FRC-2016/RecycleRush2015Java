package org.usfirst.frc.team5492.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ManualControl extends CommandGroup {
    
    public  ManualControl() {
        addParallel(new ManualClaw());
        addParallel(new ManualElevator());
    }
}
