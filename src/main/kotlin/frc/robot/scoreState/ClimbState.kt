package frc.robot.scoreState

import com.pathplanner.lib.auto.AutoBuilder
import edu.wpi.first.math.geometry.Pose2d
import edu.wpi.first.units.Units
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.Commands
import frc.robot.Constants
import frc.robot.lib.finallyDo
import frc.robot.subsystems.climb.Climb
import frc.robot.subsystems.swerve.SwerveDrive

class ClimbState : ScoreState {

    private val swerveDrive = SwerveDrive.getInstance()
    private val climb = Climb.getInstance()

    private fun nearestChain(): Pose2d {
        return swerveDrive.estimator.estimatedPosition.nearest(Constants.CHAIN_LOCATIONS)
    }

    private fun pathFindToChain(): Command {
        return Commands.defer(
            {
                Commands.sequence(
                    AutoBuilder.pathfindToPose(nearestChain(), Constants.PATH_CONSTRAINTS),
                    swerveDrive.turnCommand(
                        Units.Rotations.of(
                            nearestChain().rotation.rotations
                        ), 4.0 / 360
                    )
                )
            },
            setOf(swerveDrive)
        )
    }

    override fun execute(): Command {
        return Commands.sequence(
            climb.openClimb(),
            pathFindToChain(),
            climb.setPower { 0.8 }
        ).finallyDo(climb.stop().alongWith(climb.lock())) // TODO: Replace with LEDs command
    }
}