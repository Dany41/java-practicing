package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.effectivejava.topics.abstractions.Item;
import org.effectivejava.topics.helpers.Chapter;

import java.util.List;

@AutoService(Item.class)
public class PreferAlternativesToJavaSerialization implements Item {
    @Override
    public Chapter getChapter() {
        return Chapter.CHAPTER_11;
    }

    @Override
    public String getTheme() {
        return "Prefer alternatives to Java serialization";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "serializing - encoding objects as byte streams, so it can be sent from one VM to another or stored " +
                        "on disk for later deserialization",
                "deserializing - decoding byte streams as java objects",
                "fundamental problem with serialization if that its attack surface is too big to protect",
                """
                        object graphs are deserialized by invoking the readObject method on an ObjectInputStream. This
                        method is essentially a magic constructor that can be made to instantiate objects of almost any
                        type on the class path, so long as the type implements the Serializable interface. In the
                        process of deserializing a byte stream, this method can execute code from any of these types,
                        so the code for all of these types is part of the attack surface""",
                "the best way to avoid serialization exploits is never to deserialize anything",
                "there is no reason to use Java serialization in any new system you write",
                "alternatives - JSON, Protobuf",
                "Protobuf provides alternative representative for text - pbtxt",
                "never deserialize untrusted data",
                "prefer whitelisting to blacklists (if you cannot avoid deserialization - use filters added in Java 9" +
                        "as java.io.ObjectInputFilter)"
        );
    }

    @Override
    public void runExamples() {

    }

    @Override
    public List<Class<?>> examplesInCode() {
        return List.of(

        );
    }
}
