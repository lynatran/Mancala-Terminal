package files;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;

public class Saver {
    private static final String FILEPATH = "assets/";

    public static void saveObject(final Serializable toSave, final String filename) throws IOException {
        try (ObjectOutputStream save = new ObjectOutputStream(new FileOutputStream(FILEPATH + filename))) {
            save.writeObject(toSave);
        } catch (IOException err) {
            throw new IOException();
        }
    }

    public static Serializable loadObject(final String filename) throws IOException {
        try (ObjectInputStream load = new ObjectInputStream(new FileInputStream(FILEPATH + filename))) {
            return (Serializable) load.readObject();
        } catch (ClassNotFoundException err) {
            throw new IOException();
        } catch (IOException err) {
            throw new IOException();
        }
    }
}