package airportfiretruck.extinguisher.task01;

import airportfiretruck.extinguisher.tank.ITank;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

public class MixerReflectionUtil {
    private final Object port;

    public MixerReflectionUtil() {
        port = getMixerPort();
    }

    public int getLiquid(int amount, int ratio) {
        try {
            Method method = port.getClass().getDeclaredMethod("getMix", Integer.class, Integer.class);
            // DAS MÜSSTE GAR NICHT NÖTIG SEIN. HÄÄÄÄÄH?
            method.setAccessible(true);
            return (int) method.invoke(port, amount, ratio);
        } catch (Exception e) {
            e.getCause();
            e.printStackTrace();
        }

        return 0;
    }

    public List<ITank> getTanks() {
        try {
            Method method = port.getClass().getDeclaredMethod("getTanks");
            // DAS MÜSSTE GAR NICHT NÖTIG SEIN. HÄÄÄÄÄH?
            method.setAccessible(true);
            return (List<ITank>) method.invoke(port);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void setTanks(List<ITank> tanks) {
        try {
            Method method = port.getClass().getDeclaredMethod("setTanks", List.class);
            // DAS MÜSSTE GAR NICHT NÖTIG SEIN. HÄÄÄÄÄH?
            method.setAccessible(true);
            method.invoke(port, tanks);
        } catch (Exception e) {
            e.getCause();
            e.printStackTrace();
        }
    }

    private Object getMixerPort() {

        try {
            URL[] urls = {new File(Configuration.instance.pathToReportJavaArchive).toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls, MixerReflectionUtil.class.getClassLoader());

            Class<?> mixerClass = Class.forName("Mixer", true, urlClassLoader);
            Object mixerInstance = mixerClass.getMethod("getInstance").invoke(null);

            return mixerClass.getDeclaredField("port").get(mixerInstance);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

        return null;
    }
}
