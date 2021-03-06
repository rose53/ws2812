package de.rose53.pi;

import static java.awt.Color.BLACK;

import java.awt.Color;
import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.pi4j.io.i2c.I2CFactory.UnsupportedBusNumberException;

import de.rose53.pi.WS2812B.LEDField;

public class WS2812BExample {


    public static final Color LIGHT_RED = new Color(0x05, 0x00, 00);

    public static final Color ALIEN1_COLOR = new Color(5,15,5);
    public static final Color ALIEN2_COLOR = new Color(12,10,2);
    public static final Color ALIEN3_COLOR = new Color(1,2,13);
    public static final Color ALIEN4_COLOR = new Color(17,1,5);

    public static final Color[][] frame1Alien1 = new Color[][] {
        new Color[]{BLACK,BLACK, BLACK,ALIEN1_COLOR,ALIEN1_COLOR,BLACK,BLACK, BLACK},
        new Color[]{BLACK,BLACK, ALIEN1_COLOR,ALIEN1_COLOR,ALIEN1_COLOR,ALIEN1_COLOR,BLACK, BLACK},
        new Color[]{BLACK,ALIEN1_COLOR, ALIEN1_COLOR,ALIEN1_COLOR,ALIEN1_COLOR,ALIEN1_COLOR,ALIEN1_COLOR, BLACK},
        new Color[]{ALIEN1_COLOR,ALIEN1_COLOR, LIGHT_RED,ALIEN1_COLOR,ALIEN1_COLOR,LIGHT_RED,ALIEN1_COLOR, ALIEN1_COLOR},
        new Color[]{ALIEN1_COLOR,ALIEN1_COLOR, ALIEN1_COLOR,ALIEN1_COLOR,ALIEN1_COLOR,ALIEN1_COLOR,ALIEN1_COLOR, ALIEN1_COLOR},
        new Color[]{BLACK,BLACK, ALIEN1_COLOR,BLACK,BLACK,ALIEN1_COLOR,BLACK, BLACK},
        new Color[]{BLACK,ALIEN1_COLOR, BLACK,ALIEN1_COLOR,ALIEN1_COLOR,BLACK,ALIEN1_COLOR, BLACK},
        new Color[]{ALIEN1_COLOR,BLACK, ALIEN1_COLOR,BLACK,BLACK,ALIEN1_COLOR,BLACK, ALIEN1_COLOR}
    };

    public static final Color[][] frame2Alien1 = new Color[][] {
        new Color[]{BLACK,BLACK, BLACK,ALIEN1_COLOR,ALIEN1_COLOR,BLACK,BLACK, BLACK},
        new Color[]{BLACK,BLACK, ALIEN1_COLOR,ALIEN1_COLOR,ALIEN1_COLOR,ALIEN1_COLOR,BLACK, BLACK},
        new Color[]{BLACK,ALIEN1_COLOR, ALIEN1_COLOR,ALIEN1_COLOR,ALIEN1_COLOR,ALIEN1_COLOR,ALIEN1_COLOR, BLACK},
        new Color[]{ALIEN1_COLOR,ALIEN1_COLOR, LIGHT_RED,ALIEN1_COLOR,ALIEN1_COLOR,LIGHT_RED,ALIEN1_COLOR, ALIEN1_COLOR},
        new Color[]{ALIEN1_COLOR,ALIEN1_COLOR, ALIEN1_COLOR,ALIEN1_COLOR,ALIEN1_COLOR,ALIEN1_COLOR,ALIEN1_COLOR, ALIEN1_COLOR},
        new Color[]{BLACK,BLACK, ALIEN1_COLOR,BLACK,BLACK,ALIEN1_COLOR,BLACK, BLACK},
        new Color[]{BLACK,ALIEN1_COLOR, BLACK,ALIEN1_COLOR,ALIEN1_COLOR,BLACK,ALIEN1_COLOR, BLACK},
        new Color[]{BLACK,ALIEN1_COLOR, BLACK,BLACK,BLACK,BLACK,ALIEN1_COLOR, BLACK}
    };

    public static final Color[][] frame1Alien2 = new Color[][] {
        new Color[]{BLACK,BLACK, BLACK,BLACK,BLACK,BLACK,BLACK, BLACK},
        new Color[]{BLACK,BLACK, ALIEN2_COLOR,ALIEN2_COLOR,ALIEN2_COLOR,ALIEN2_COLOR,BLACK, BLACK},
        new Color[]{BLACK,ALIEN2_COLOR, ALIEN2_COLOR,ALIEN2_COLOR,ALIEN2_COLOR,ALIEN2_COLOR,ALIEN2_COLOR, BLACK},
        new Color[]{ALIEN2_COLOR,ALIEN2_COLOR, LIGHT_RED,ALIEN2_COLOR,ALIEN2_COLOR,LIGHT_RED,ALIEN2_COLOR, ALIEN2_COLOR},
        new Color[]{ALIEN2_COLOR,ALIEN2_COLOR, LIGHT_RED,ALIEN2_COLOR,ALIEN2_COLOR,LIGHT_RED,ALIEN2_COLOR, ALIEN2_COLOR},
        new Color[]{BLACK,ALIEN2_COLOR, ALIEN2_COLOR,ALIEN2_COLOR,ALIEN2_COLOR,ALIEN2_COLOR,ALIEN2_COLOR, BLACK},
        new Color[]{BLACK,BLACK, ALIEN2_COLOR,BLACK,BLACK,ALIEN2_COLOR,BLACK, BLACK},
        new Color[]{ALIEN2_COLOR,ALIEN2_COLOR, BLACK,BLACK,BLACK,BLACK,ALIEN2_COLOR, ALIEN2_COLOR}
    };

    public static final Color[][] frame2Alien2 = new Color[][] {
        new Color[]{BLACK,BLACK, ALIEN2_COLOR,ALIEN2_COLOR,ALIEN2_COLOR,ALIEN2_COLOR,BLACK, BLACK},
        new Color[]{BLACK,ALIEN2_COLOR, ALIEN2_COLOR,ALIEN2_COLOR,ALIEN2_COLOR,ALIEN2_COLOR,ALIEN2_COLOR, BLACK},
        new Color[]{ALIEN2_COLOR,ALIEN2_COLOR, LIGHT_RED,ALIEN2_COLOR,ALIEN2_COLOR,LIGHT_RED,ALIEN2_COLOR, ALIEN2_COLOR},
        new Color[]{ALIEN2_COLOR,ALIEN2_COLOR, LIGHT_RED,ALIEN2_COLOR,ALIEN2_COLOR,LIGHT_RED,ALIEN2_COLOR, ALIEN2_COLOR},
        new Color[]{BLACK,ALIEN2_COLOR, ALIEN2_COLOR,ALIEN2_COLOR,ALIEN2_COLOR,ALIEN2_COLOR,ALIEN2_COLOR, BLACK},
        new Color[]{BLACK,BLACK, ALIEN2_COLOR,BLACK,BLACK,ALIEN2_COLOR,BLACK, BLACK},
        new Color[]{BLACK,BLACK, ALIEN2_COLOR,BLACK,BLACK,ALIEN2_COLOR,BLACK, BLACK},
        new Color[]{BLACK,BLACK, ALIEN2_COLOR,BLACK,BLACK,ALIEN2_COLOR,BLACK, BLACK}
    };

    public static final Color[][] frame1Alien3 = new Color[][] {
        new Color[]{BLACK,BLACK, ALIEN3_COLOR,BLACK,BLACK,ALIEN3_COLOR,BLACK, BLACK},
        new Color[]{BLACK,BLACK, ALIEN3_COLOR,BLACK,BLACK,ALIEN3_COLOR,BLACK, BLACK},
        new Color[]{BLACK,ALIEN3_COLOR, ALIEN3_COLOR,ALIEN3_COLOR,ALIEN3_COLOR,ALIEN3_COLOR,ALIEN3_COLOR, BLACK},
        new Color[]{ALIEN3_COLOR,ALIEN3_COLOR, LIGHT_RED,ALIEN3_COLOR,ALIEN3_COLOR,LIGHT_RED,ALIEN3_COLOR, ALIEN3_COLOR},
        new Color[]{ALIEN3_COLOR,ALIEN3_COLOR, ALIEN3_COLOR,ALIEN3_COLOR,ALIEN3_COLOR,ALIEN3_COLOR,ALIEN3_COLOR, ALIEN3_COLOR},
        new Color[]{ALIEN3_COLOR,ALIEN3_COLOR, ALIEN3_COLOR,ALIEN3_COLOR,ALIEN3_COLOR,ALIEN3_COLOR,ALIEN3_COLOR, ALIEN3_COLOR},
        new Color[]{ALIEN3_COLOR,BLACK, ALIEN3_COLOR,BLACK,BLACK,ALIEN3_COLOR,BLACK, ALIEN3_COLOR},
        new Color[]{BLACK,BLACK, ALIEN3_COLOR,BLACK,BLACK,ALIEN3_COLOR,BLACK, BLACK}
    };

    public static final Color[][] frame2Alien3 = new Color[][] {
        new Color[]{BLACK,BLACK, ALIEN3_COLOR,BLACK,BLACK,ALIEN3_COLOR,BLACK, BLACK},
        new Color[]{ALIEN3_COLOR,BLACK, ALIEN3_COLOR,BLACK,BLACK,ALIEN3_COLOR,BLACK, ALIEN3_COLOR},
        new Color[]{ALIEN3_COLOR,ALIEN3_COLOR, ALIEN3_COLOR,ALIEN3_COLOR,ALIEN3_COLOR,ALIEN3_COLOR,ALIEN3_COLOR, ALIEN3_COLOR},
        new Color[]{ALIEN3_COLOR,ALIEN3_COLOR, LIGHT_RED,ALIEN3_COLOR,ALIEN3_COLOR,LIGHT_RED,ALIEN3_COLOR, ALIEN3_COLOR},
        new Color[]{ALIEN3_COLOR,ALIEN3_COLOR, ALIEN3_COLOR,ALIEN3_COLOR,ALIEN3_COLOR,ALIEN3_COLOR,ALIEN3_COLOR, ALIEN3_COLOR},
        new Color[]{BLACK,ALIEN3_COLOR, ALIEN3_COLOR,ALIEN3_COLOR,ALIEN3_COLOR,ALIEN3_COLOR,ALIEN3_COLOR, BLACK},
        new Color[]{BLACK,BLACK, ALIEN3_COLOR,BLACK,BLACK,ALIEN3_COLOR,BLACK, BLACK},
        new Color[]{BLACK,ALIEN3_COLOR, BLACK,BLACK,BLACK,BLACK,ALIEN3_COLOR, BLACK}
    };

    public static final Color[][] frame1Alien4 = new Color[][] {
        new Color[]{BLACK,BLACK,ALIEN4_COLOR,ALIEN4_COLOR,ALIEN4_COLOR,ALIEN4_COLOR,BLACK,BLACK},
        new Color[]{BLACK,ALIEN4_COLOR,ALIEN4_COLOR,ALIEN4_COLOR,ALIEN4_COLOR,ALIEN4_COLOR,ALIEN4_COLOR,BLACK},
        new Color[]{BLACK,BLACK,ALIEN4_COLOR,ALIEN4_COLOR,BLACK,BLACK,ALIEN4_COLOR,ALIEN4_COLOR},
        new Color[]{BLACK,ALIEN4_COLOR,ALIEN4_COLOR,ALIEN4_COLOR,ALIEN4_COLOR,ALIEN4_COLOR,ALIEN4_COLOR,BLACK},
        new Color[]{BLACK,BLACK,ALIEN4_COLOR,ALIEN4_COLOR,ALIEN4_COLOR,ALIEN4_COLOR,BLACK,BLACK},
        new Color[]{BLACK,BLACK,BLACK,BLACK,BLACK,BLACK,BLACK,BLACK},
        new Color[]{BLACK,BLACK,BLACK,BLACK,ALIEN4_COLOR,BLACK,BLACK,BLACK},
        new Color[]{BLACK,BLACK,BLACK,BLACK,BLACK,BLACK,BLACK,BLACK}
    };

    public static final Color[][] frame2Alien4 = new Color[][] {
        new Color[]{BLACK,BLACK,ALIEN4_COLOR,ALIEN4_COLOR,ALIEN4_COLOR,ALIEN4_COLOR,BLACK,BLACK},
        new Color[]{BLACK,ALIEN4_COLOR,ALIEN4_COLOR,ALIEN4_COLOR,ALIEN4_COLOR,ALIEN4_COLOR,ALIEN4_COLOR,BLACK},
        new Color[]{ALIEN4_COLOR,BLACK,BLACK,ALIEN4_COLOR,ALIEN4_COLOR,BLACK,BLACK,ALIEN4_COLOR},
        new Color[]{BLACK,ALIEN4_COLOR,ALIEN4_COLOR,ALIEN4_COLOR,ALIEN4_COLOR,ALIEN4_COLOR,ALIEN4_COLOR,BLACK},
        new Color[]{BLACK,BLACK,ALIEN4_COLOR,ALIEN4_COLOR,ALIEN4_COLOR,ALIEN4_COLOR,BLACK,BLACK},
        new Color[]{BLACK,BLACK,BLACK,BLACK,BLACK,BLACK,BLACK,BLACK},
        new Color[]{BLACK,BLACK,BLACK,BLACK,ALIEN4_COLOR,BLACK,BLACK,BLACK},
        new Color[]{BLACK,BLACK,BLACK,BLACK,ALIEN4_COLOR,BLACK,BLACK,BLACK}
    };

    public static final Color[][] frame3Alien4 = new Color[][] {
        new Color[]{BLACK,BLACK,ALIEN4_COLOR,ALIEN4_COLOR,ALIEN4_COLOR,ALIEN4_COLOR,BLACK,BLACK},
        new Color[]{BLACK,ALIEN4_COLOR,ALIEN4_COLOR,ALIEN4_COLOR,ALIEN4_COLOR,ALIEN4_COLOR,ALIEN4_COLOR,BLACK},
        new Color[]{ALIEN4_COLOR,ALIEN4_COLOR,BLACK,BLACK,ALIEN4_COLOR,ALIEN4_COLOR,BLACK,BLACK},
        new Color[]{BLACK,ALIEN4_COLOR,ALIEN4_COLOR,ALIEN4_COLOR,ALIEN4_COLOR,ALIEN4_COLOR,ALIEN4_COLOR,BLACK},
        new Color[]{BLACK,BLACK,ALIEN4_COLOR,ALIEN4_COLOR,ALIEN4_COLOR,ALIEN4_COLOR,BLACK,BLACK},
        new Color[]{BLACK,BLACK,BLACK,BLACK,BLACK,BLACK,BLACK,BLACK},
        new Color[]{BLACK,BLACK,BLACK,BLACK,BLACK,BLACK,BLACK,BLACK},
        new Color[]{BLACK,BLACK,BLACK,BLACK,ALIEN4_COLOR,BLACK,BLACK,BLACK}
    };

    public static final Color[][] frame4Alien4 = new Color[][] {
        new Color[]{BLACK,BLACK,ALIEN4_COLOR,ALIEN4_COLOR,ALIEN4_COLOR,ALIEN4_COLOR,BLACK,BLACK},
        new Color[]{BLACK,ALIEN4_COLOR,ALIEN4_COLOR,ALIEN4_COLOR,ALIEN4_COLOR,ALIEN4_COLOR,ALIEN4_COLOR,BLACK},
        new Color[]{BLACK,ALIEN4_COLOR,ALIEN4_COLOR,BLACK,BLACK,ALIEN4_COLOR,ALIEN4_COLOR,BLACK},
        new Color[]{BLACK,ALIEN4_COLOR,ALIEN4_COLOR,ALIEN4_COLOR,ALIEN4_COLOR,ALIEN4_COLOR,ALIEN4_COLOR,BLACK},
        new Color[]{BLACK,BLACK,ALIEN4_COLOR,ALIEN4_COLOR,ALIEN4_COLOR,ALIEN4_COLOR,BLACK,BLACK},
        new Color[]{BLACK,BLACK,BLACK,BLACK,BLACK,BLACK,BLACK,BLACK},
        new Color[]{BLACK,BLACK,BLACK,BLACK,BLACK,BLACK,BLACK,BLACK},
        new Color[]{BLACK,BLACK,BLACK,BLACK,BLACK,BLACK,BLACK,BLACK}
    };


    private static void space() {
        try (WS2812B ws2812B = new WS2812B()) {

            while (true) {

                for (int i = 0; i < 10; i++) {
                    ws2812B.setLED(new LEDField(frame1Alien1));
                    Thread.sleep(250);
                    ws2812B.setLED(new LEDField(frame2Alien1));
                    Thread.sleep(250);
                }
                for (int i = 0; i < 10; i++) {
                    ws2812B.setLED(new LEDField(frame1Alien2));
                    Thread.sleep(250);
                    ws2812B.setLED(new LEDField(frame2Alien2));
                    Thread.sleep(250);
                }
                for (int i = 0; i < 10; i++) {
                    ws2812B.setLED(new LEDField(frame1Alien3));
                    Thread.sleep(250);
                    ws2812B.setLED(new LEDField(frame2Alien3));
                    Thread.sleep(250);
                }
                for (int i = 0; i < 10; i++) {
                    ws2812B.setLED(new LEDField(frame1Alien4));
                    Thread.sleep(120);
                    ws2812B.setLED(new LEDField(frame2Alien4));
                    Thread.sleep(120);
                    ws2812B.setLED(new LEDField(frame3Alien4));
                    Thread.sleep(120);
                    ws2812B.setLED(new LEDField(frame4Alien4));
                    Thread.sleep(120);
                }
            }
        } catch (UnsupportedBusNumberException | IOException | InterruptedException e) {
        }

    }


    private static void life() {
        try (WS2812B ws2812B = new WS2812B()) {
            Conway conway = new Conway(ws2812B);

            while (true) {
                conway.init();
                conway.run();
            }
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {

        final CommandLineParser cmdLineParser = new DefaultParser();
        CommandLine commandLine = null;
        try {
            commandLine = cmdLineParser.parse(generateOptions(), args);
            final boolean life = commandLine.hasOption("life");
            if (life) {
                life();
            } else {
                space();
            }
        } catch (ParseException ex) {
        }
    }

    private static Options generateOptions() {

         final Options options = new Options();
         options.addOption("l","life",false,null);
         return options;
    }

}
