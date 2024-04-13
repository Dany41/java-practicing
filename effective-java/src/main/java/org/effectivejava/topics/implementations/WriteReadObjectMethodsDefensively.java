package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.abstractions.Item;
import org.effectivejava.topics.helpers.EjChapter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

@AutoService(Item.class)
public class WriteReadObjectMethodsDefensively implements Item {
    @Override
    public EjChapter getChapter() {
        return EjChapter.CHAPTER_12;
    }

    @Override
    public String getTheme() {
        return "Write readObject methods defensively";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "when an object is deserialized, it is critical to defensively copy any field containing an object " +
                        "reference that a client must process",
                "for classes with object reference fields that must remain private, defensively copy each object in " +
                        "such a field. Mutable components of immutable classes fall into this category",
                "check any invariants and throw an InvalidObjectException if a check fails. The checks should follow " +
                        "any defensive copying",
                "if an entire object graph must be validated after it is deserialized, use the ObjectInputValidation " +
                        "interface",
                "do no invoke any overridable methods in the class, directly or indirectly"
        );
    }

    @Override
    public void runExamples() {
        // fails, but should not
//        Period p = (Period) deserialize(serializedForm);
//        System.out.println(p);

    }

    @Override
    public List<Class<?>> examplesInCode() {
        return List.of(

        );
    }

    // Byte stream couldn't have come from a real Period instance!
    private static final byte[] serializedForm = {
            (byte)0xac, (byte)0xed, 0x00, 0x05, 0x73, 0x72, 0x00, 0x06,
            0x50, 0x65, 0x72, 0x69, 0x6f, 0x64, 0x40, 0x7e, (byte)0xf8,
            0x2b, 0x4f, 0x46, (byte)0xc0, (byte)0xf4, 0x02, 0x00, 0x02,
            0x4c, 0x00, 0x03, 0x65, 0x6e, 0x64, 0x74, 0x00, 0x10, 0x4c,
            0x6a, 0x61, 0x76, 0x61, 0x2f, 0x75, 0x74, 0x69, 0x6c, 0x2f,
            0x44, 0x61, 0x74, 0x65, 0x3b, 0x4c, 0x00, 0x05, 0x73, 0x74,
            0x61, 0x72, 0x74, 0x71, 0x00, 0x7e, 0x00, 0x01, 0x78, 0x70,
            0x73, 0x72, 0x00, 0x0e, 0x6a, 0x61, 0x76, 0x61, 0x2e, 0x75,
            0x74, 0x69, 0x6c, 0x2e, 0x44, 0x61, 0x74, 0x65, 0x68, 0x6a,
            (byte)0x81, 0x01, 0x4b, 0x59, 0x74, 0x19, 0x03, 0x00, 0x00,
            0x78, 0x70, 0x77, 0x08, 0x00, 0x00, 0x00, 0x66, (byte)0xdf,
            0x6e, 0x1e, 0x00, 0x78, 0x73, 0x71, 0x00, 0x7e, 0x00, 0x03,
            0x77, 0x08, 0x00, 0x00, 0x00, (byte)0xd5, 0x17, 0x69, 0x22,
            0x00, 0x78
    };

    // Returns the object with the specified serialized form
    static Object deserialize(byte[] sf) {
        try {
            return new ObjectInputStream(
                    new ByteArrayInputStream(sf)).readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
