package frc.robot.lib

import edu.wpi.first.math.VecBuilder
import frc.robot.subsystems.swerve.SwerveConstants
import frc.robot.subsystems.swerve.SwerveDrive
import frc.robot.subsystems.vision.Vision
import java.util.stream.DoubleStream

class PoseEstimation {
    private val vision: Vision = Vision.getInstance()
    private val swerveDrive: SwerveDrive = SwerveDrive.getInstance()

    companion object {
        @Volatile
        private var instance: PoseEstimation? = null

        fun initialize() {
            synchronized(this) {
                if (instance == null) {
                    instance = PoseEstimation()
                }
            }
        }

        fun getInstance(): PoseEstimation {
            return instance ?: throw IllegalArgumentException(
                "PoseEstimation has not been initialized. Call initialize() first."
            )
        }

        /**
         * Averages ambiguity of estimated poses using a harmonic average. Can be from different targets
         * in vision module, or between module.
         *
         * @param ambiguities the ambiguities to average.
         * @return the average of the ambiguities.
         */
        fun averageAmbiguity(ambiguities: List<Double>): Double {
            return ambiguities.size / ambiguities.sumOf { 1.0 / it }
        }
    }

    fun processVisionMeasurements(multiplier: Double) {
        val results = vision.getResults()

        for (result in results) {
            val ambiguities = result.distanceToTargets.map { d -> d * d }
            val stddev = multiplier //* ambiguities // TODO: Do Harmonic Average for ambiguities list before calculating stddev

            swerveDrive.estimator.addVisionMeasurement(
                result.estimatedRobotPose.toPose2d(),
                result.timestamp,
                VecBuilder.fill(
                    stddev,
                    stddev,
                    stddev * SwerveConstants.MAX_OMEGA_VELOCITY / SwerveConstants.MAX_X_Y_VELOCITY
                )
            )
        }
    }
}