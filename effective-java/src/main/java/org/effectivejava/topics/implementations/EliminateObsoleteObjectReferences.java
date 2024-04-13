package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.abstractions.Item;
import org.effectivejava.topics.helpers.EjChapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

@AutoService(Item.class)
public class EliminateObsoleteObjectReferences implements Item {
    @Override
    public EjChapter getChapter() {
        return EjChapter.CHAPTER_2;
    }

    @Override
    public String getTheme() {
        return "Eliminate obsolete object references";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "null out the no longer needed references",
                "use -XX:+HeapDumpOnOutOfMemoryError VM option when if wou want create a head dump when a program runs out of memory",
                "WeakHashMap is used to avoid memory leaks in caches"
        );
    }

    @Override
    public void runExamples() {
        checkReferenceStabilityInMap(new HashMap<>(), "HashMap");
        checkReferenceStabilityInMap(new WeakHashMap<>(), "WeakHashMap");
    }

    private void checkReferenceStabilityInMap(Map<UniqueImageName, BigImage> mapToCheckOn, String name) {
        BigImage bigImage = new BigImage("image_id");
        UniqueImageName imageName = new UniqueImageName("name_of_big_image");

        mapToCheckOn.put(imageName, bigImage);

        System.out.format("Check if the %s contains a key before nulling\n", name);
        System.out.println(mapToCheckOn.containsKey(imageName));

        imageName = null;
        System.out.println(mapToCheckOn);
        System.gc();


        System.out.println("Waiting 10 sec to let GC to clean up the key");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.out.println("Oops, exception happened: " + e.getMessage());
        }
        System.out.println("Check if the map is empty?");
        System.out.println(mapToCheckOn.isEmpty());
        System.out.println(mapToCheckOn.toString());
        System.out.println();
    }

    public static class UniqueImageName {
        public String name;

        public UniqueImageName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "UniqueImageName{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    public static class BigImage {
        public String imageId;

        public BigImage(String imageId) {
            this.imageId = imageId;
        }

        @Override
        public String toString() {
            return "BigImage{" +
                    "imageId='" + imageId + '\'' +
                    '}';
        }
    }
}
