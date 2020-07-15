package cn.feelcode.jpa;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.project.Project;

/**
 * @ClassName: PropertiesaCenter
 * @Author: zhangyingqi
 * @Description: 配置中心
 * @Date: Created in 10:21 2020/7/12
 * @Modified By:
 */
public class PropertiesCenter {

    final static String logIndexKey = "JpaSqlLog_Index";

    public static void init(Project project) {
        if(project != null) {
            PropertiesComponent.getInstance(project).setValue(logIndexKey, 1, 1);
        }
    }

    static void setIndex(Project project, int index){
        PropertiesComponent.getInstance(project).setValue(logIndexKey, index, 1);
    }
    static int getIndex(Project project){
        return PropertiesComponent.getInstance(project).getInt(logIndexKey, 1);
    }

}
