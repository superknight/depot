package util;

public class ValidationUtil {

    public static boolean isSqlInject(String target) {
        target = target.toLowerCase();
        String inj_str = ";insert|;select|;delete|;update|;drop|chr|mid|master|truncate|where 1=1|or |and |--|'";
        String inj_stra[] = inj_str.split("\\|");
        for (int i = 0; i < inj_stra.length; i++) {
            if (target.indexOf(inj_stra[i]) >= 0) {
                return true;
            }
        }
        return false;
    }

}
