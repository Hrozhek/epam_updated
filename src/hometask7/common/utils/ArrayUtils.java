package hometask7.common.utils;

public final class ArrayUtils {

    private ArrayUtils() {
    }

    public static void copyArray(Object[] src, Object[] dest) {
        System.arraycopy(src, 0, dest, 0, src.length);
    }

    public static void deleteObjectFromArray(Object[] src, int objectPosition, Object[] dest) {
        int currentPosition = 0;
        for (; currentPosition < objectPosition && currentPosition < src.length && currentPosition < dest.length; currentPosition++) {
            dest[currentPosition] = src[currentPosition];
        }
        for (; currentPosition < (src.length - 1) && currentPosition < dest.length; currentPosition++) {
            dest[currentPosition] = src[currentPosition + 1];
        }
    }

    public static void trimArray(Object[] src, Object[] dest) {
        for (int i = 0; i < src.length && i < dest.length; i++) {
            if (src[i] != null) {
                dest[i] = src[i];
            }
        }
    }
    public static void printArray(Object[] arr) {
        for (Object obj : arr) {
            if (obj != null) {
                System.out.println(obj);
            }
        }
    }

}
