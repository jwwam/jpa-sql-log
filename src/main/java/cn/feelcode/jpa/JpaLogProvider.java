package cn.feelcode.jpa;

import com.intellij.execution.filters.ConsoleFilterProvider;
import com.intellij.execution.filters.Filter;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

/**
 * @ClassName: JpaLogProvider
 * @Author: zhangyingqi
 * @Description: 控制台过滤器
 * @Date: Created in 9:58 2020/7/10
 * @Modified By:
 */
public class JpaLogProvider implements ConsoleFilterProvider {

    @NotNull
    @Override
    public Filter[] getDefaultFilters(@NotNull Project project) {
        Filter filter = new JpaLogFilter(project);
        return new Filter[]{filter};
    }

}
