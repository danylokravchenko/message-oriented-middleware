package ch.unibas.dmi.dbis.dis.mom.producer;

import ch.unibas.dmi.dbis.dis.mom.data.DataContainer;
import ch.unibas.dmi.dbis.dis.mom.data.PressureData;
import ch.unibas.dmi.dbis.dis.mom.data.TemperatureData;

import java.util.Random;
import java.util.Scanner;

/**
 * Very basic data probe collecting temperature and pressure data.
 */
public class WeatherBalloon extends DataProbe {
    private final Random random = new Random();

    @Override
    protected DataContainer collectData() {
        long time = System.currentTimeMillis();
        if (random.nextBoolean()) {
            float temp = (float) random.nextGaussian() * -16 - 32;
            return new TemperatureData(temp, "Stratosphere", time);
        } else {
            float pressure = random.nextFloat() * 0.001f + 0.0001f;
            return new PressureData(pressure, "Stratosphere", time);
        }
    }

    @Override
    protected int getDataDelay() {
        return random.nextInt(2500) + 100;
    }

    public static void main(String[] args) {
        WeatherBalloon probe = new WeatherBalloon();

        Thread thread = new Thread(probe);
        thread.start();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Press enter to exit.");
        scanner.nextLine();
        probe.running = false;
    }
}