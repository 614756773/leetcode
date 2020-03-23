package util;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qinzhu
 * @since 2020/3/23
 * 用于获取对象大小，
 * 不会递归计算对象的大小，
 * 若对象有引用类型的字段，那个字段则只计算为4bit大小（默认jdk开启了指针压缩，
 * 如果没有开启指针压缩，在32位jdk中是4字节，64位jdk中是8字节）
 * 参考：<a href="https://www.jianshu.com/p/9d729c9c94c4"></a>
 */
public class ObjectSizeUtil {

    private static Map<Class, Long> map = new HashMap<>(16);

    static {
        map.put(byte.class, 1L);
        map.put(char.class, 1L);
        map.put(boolean.class, 1L);
        map.put(short.class, 2L);
        map.put(int.class, 4L);
        map.put(float.class, 4L);
        map.put(double.class, 8L);
        map.put(long.class, 8L);
    }

    /**
     * 获取对象的大小，单位字节
     * 不会递归计算对象的大小
     */
    public static long sizeOf(Object o) {
        Unsafe unsafe = reflectionGetUnsafe();
        Field[] fields = o.getClass().getDeclaredFields();
        long size;

        long position = 0;
        Field lastField = fields[fields.length - 1];
        for (Field field : fields) {
            // 静态变量不属于对象的大小，不计算
            if (Modifier.isStatic(field.getModifiers())) continue;
            long l = unsafe.objectFieldOffset(field);
            if (l >= position) {
                position = l;
                lastField = field;
            }
        }

        Class lastFieldType = lastField.getType();
        if (!lastFieldType.isPrimitive()) {
            size = position + 4;
        } else {
            size = position + primitiveSize(lastFieldType);
        }
        return padding(size);
    }

    /**
     * 将结果对齐，因为在jvm中对象大小都是8字节的倍数
     * @return 对象的大小，单位字节
     */
    private static long padding(long size) {
        if (size % 8 == 0) {
            return size;
        }
        return (size / 8 + 1) * 8;
    }

    private static long primitiveSize(Class lastFieldClass) {
        return map.get(lastFieldClass);
    }

    /**
     * 使用反射的方式获取Unsafe
     */
    private static Unsafe reflectionGetUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("未知错误");
    }
}
