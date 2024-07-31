package frc.robot.subsystems.swerve

import edu.wpi.first.math.geometry.Rotation2d
import edu.wpi.first.math.kinematics.ChassisSpeeds
import org.littletonrobotics.junction.AutoLog
import org.team9432.annotation.Logged

@Logged
open class SwerveDriveInputs {
    // x, y, omega
    var desiredSpeeds: ChassisSpeeds = ChassisSpeeds()

    var acceleration: Double = 0.0

    var rawYaw: Rotation2d = Rotation2d()
    var yaw: Rotation2d = Rotation2d()
    var gyroOffset: Rotation2d = Rotation2d()
}
