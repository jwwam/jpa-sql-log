package cn.feelcode.jpa;

import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;

/**
 * @ClassName: MyToolWindow
 * @Author: zhangyingqi
 * @Description: 控制台打印
 * @Date: Created in 15:44 2020/7/10
 * @Modified By:
 */
public class ToolWindowConsole {
    private static Project project;
    private static ConsoleView console;

    public ToolWindowConsole(ToolWindow toolWindow, ConsoleView console, Project project) {
        this.console=console;
        this.project=project;
    }

    public static void logJpa(String s) {
        if(console==null){
            System.out.println("console为空，重新获取console");
            console = TextConsoleBuilderFactory.getInstance().createBuilder(project).getConsole();
        }
        if(console.isOutputPaused()){
            System.out.println("console已暂停,设置启动");
            console.setOutputPaused(false);
        }
        console.print(s + "\n", ConsoleViewContentType.NORMAL_OUTPUT);
        //console.setOutputPaused(true);
        System.out.println("结束sout sql log");
        return;
    }
}
