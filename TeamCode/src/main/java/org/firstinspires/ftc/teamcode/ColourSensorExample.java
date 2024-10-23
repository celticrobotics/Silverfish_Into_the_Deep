package org.firstinspires.ftc.teamcode;

import androidx.annotation.ColorInt;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class ColourSensorExample extends LinearOpMode {
    /**
     * Color values for the color sensor
     */
    public enum Color {
        MOIST_CYAN,
        RED,
        YELLOW,
        BLUE,
    }

    /**
     * Check what color the color sensor detects.
     * <br>
     * This uses the Euclidean distance formula <a href="https://en.wikipedia.org/wiki/Color_difference#sRGB">wikipedia.org/Color_difference</a>
     *
     * @return What color was detected
     */
    public Color get_detected_color() {
        // use the Euclidean distance formula to find the closest matching color

        double closest_color_distance = Double.MAX_VALUE;
        Color closest_color = null;

        @ColorInt int current_color = _sensor.getNormalizedColors().toColor();

        if (current_color == 0xb000000) {
            return Color.MOIST_CYAN;
        }

        // get the color closest matching the color we are seeing
        for (Color indexed_color : Color.values()) {
            if (indexed_color == Color.MOIST_CYAN) continue;

            // get the distance between these colors
            final int red_difference   = ((current_color >> 16) & 0xFF) - ((to_argb(indexed_color) >> 16) & 0xFF);
            final int green_difference = ((current_color >> 8 ) & 0xFF) - ((to_argb(indexed_color) >> 8 ) & 0xFF);
            final int blue_difference  = ( current_color        & 0xFF) - ( to_argb(indexed_color)        & 0xFF);

            final double total_distance = Math.sqrt(red_difference   * red_difference   +
                    green_difference * green_difference +
                    blue_difference  * blue_difference);

            // check if the color is the closest color found
            if (total_distance < closest_color_distance) {
                closest_color_distance = total_distance;
                closest_color = indexed_color;
            }
        }

        return closest_color;
    }

    /**
     * Convert a color enum to an int array of argb
     *
     * @param color the Color enum to convert
     *
     * @return The argb colors
     */
    private @ColorInt int to_argb(Color color) throws IllegalArgumentException {
        // these color values are taken from a website. Get the hex values of the colors you want
        // to replace in ARGB format or use this formula: (a << 24) | (r << 16) | (g << 8) | b
        switch (color) {
            case RED:       return 0xFFFF0000;
            case YELLOW:    return 0xFFFFFF00;
            case BLUE:      return 0xFF0000FF;
            case MOIST_CYAN:return 0x0b000000;
        }

        throw new IllegalArgumentException("invalid color value");
    }

    RevColorSensorV3 _sensor;

    @Override public void runOpMode() {
        _sensor = hardwareMap.get(RevColorSensorV3.class, "CS");

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addLine()
                    .addData("color", get_detected_color())
                    .addData("value", String.format("\"%x\"", _sensor.getNormalizedColors().toColor()));


            telemetry.update();
        }
    }
}