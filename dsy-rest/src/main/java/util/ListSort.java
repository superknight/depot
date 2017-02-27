package util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * List对象排序的通用方法
 *
 * @author 
 *
 * @param <E>
 */
public class ListSort<E> {
	
	Logger logger=Logger.getLogger(ListSort.class);
    /**
     *
     * @param list 要排序的集合
     * @param method 要排序的实体的属性所对应的get方法
     * @param sort desc 为正序 
     */
	@SuppressWarnings("all")
    public void Sort(List<E> list, final String method, final String sort) {
        // 用内部类实现排序
        Collections.sort(list, new Comparator<E>() {
 
            public int compare(E a, E b) {
                int ret = 0;
                try {
                    // 获取m1的方法名
                    Method m1 = a.getClass().getMethod(method, null);
                    // 获取m2的方法名
                    Method m2 = b.getClass().getMethod(method, null);
                     
                    if (sort != null && "desc".equals(sort)) {
 
                        ret = m2.invoke(((E)b), null).toString().compareTo(m1.invoke(((E)a),null).toString());
 
                    } else {
                        // 正序排序
                        ret = m1.invoke(((E)a), null).toString().compareTo(m2.invoke(((E)b), null).toString());
                    }
                } catch (NoSuchMethodException ne) {
                	logger.error("error", ne);
                } catch (IllegalArgumentException e) {
                	logger.error("error", e);
                } catch (IllegalAccessException e) {
                	logger.error("error", e);
                } catch (InvocationTargetException e) {
                	logger.error("error", e);
                }
                return ret;
            }
        });
    }
}

