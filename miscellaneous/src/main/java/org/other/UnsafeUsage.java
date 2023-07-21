package org.other;

import sun.misc.Unsafe;

import java.io.IOException;
import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

// all the code is taken from https://www.baeldung.com/java-unsafe
public class UnsafeUsage {

    static Unsafe unsafe;

    public static void main(String[] args) throws Exception {

        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        unsafe = (Unsafe) f.get(null);

        // SecurityException
//        unsafe = Unsafe.getUnsafe();

        InitializationOrdering o1 = new InitializationOrdering();
        assertThat(o1.getA() == 1).isTrue();

        InitializationOrdering o3 = (InitializationOrdering) unsafe.allocateInstance(InitializationOrdering.class);

        assertThat(o3.getA() == 0).isTrue();

        SecretHolder secretHolder = new SecretHolder();

        Field f_secretValue = secretHolder.getClass().getDeclaredField("SECRET_VALUE");
        unsafe.putInt(secretHolder, unsafe.objectFieldOffset(f_secretValue), 1);

        assertThat(secretHolder.secretIsDisclosed()).isTrue();

        givenUnsafeThrowException_whenThrowCheckedException_thenNotNeedToCatchIt();

        long SUPER_SIZE = (long) Integer.MAX_VALUE * 2;
        OffHeapArray array = new OffHeapArray(SUPER_SIZE);
//        var array2 = new int[Integer.MAX_VALUE / 8];

        int sum = 0;
        for (int i = 0; i < 100_000_000; i++) {
            array.set((long) Integer.MAX_VALUE + i, (byte) 3);
            sum += array.get((long) Integer.MAX_VALUE + i);
        }

        assertThat(array.size() == SUPER_SIZE).isTrue();
        assertThat(sum == 100_000_000).isTrue();

        array.freeMemory();
    }

    private static void givenUnsafeThrowException_whenThrowCheckedException_thenNotNeedToCatchIt() {
        assertThatExceptionOfType(IOException.class).isThrownBy(() -> unsafe.throwException(new IOException()));
//        throw new IOException();
    }

}

class InitializationOrdering {
    private long a;

    public InitializationOrdering() {
        this.a = 1;
    }

    public long getA() {
        return this.a;
    }
}

class SecretHolder {
    private int SECRET_VALUE = 0;

    public boolean secretIsDisclosed() {
        return SECRET_VALUE == 1;
    }
}

class OffHeapArray {
    private final static int BYTE = 1;
    private long size;
    private long address;

    public OffHeapArray(long size) throws NoSuchFieldException, IllegalAccessException {
        this.size = size;
        address = getUnsafe().allocateMemory(size * BYTE);
    }

    private Unsafe getUnsafe() throws IllegalAccessException, NoSuchFieldException {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        return (Unsafe) f.get(null);
    }

    public void set(long i, byte value) throws NoSuchFieldException, IllegalAccessException {
        getUnsafe().putByte(address + i * BYTE, value);
    }

    public int get(long idx) throws NoSuchFieldException, IllegalAccessException {
        return getUnsafe().getByte(address + idx * BYTE);
    }

    public long size() {
        return size;
    }

    public void freeMemory() throws NoSuchFieldException, IllegalAccessException {
        getUnsafe().freeMemory(address);
    }

}