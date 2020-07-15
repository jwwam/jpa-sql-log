package cn.feelcode.jpa;

import com.intellij.execution.filters.TextConsoleBuilder;
import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

/**
 * @ClassName: JpaToolWindowFactory
 * @Author: zhangyingqi
 * @Description: TODO
 * @Date: Created in 10:25 2020/7/10
 * @Modified By:
 */
public class JpaToolWindow implements ToolWindowFactory {

//    JpaToolWindow(Project project, ToolWindow toolWindow){
//        createToolWindowContent(project, toolWindow);
//    }

    public static JComponent createConsolePanel(ConsoleView view) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(view.getComponent(), BorderLayout.CENTER);
        return panel;
    }

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        TextConsoleBuilder consoleBuilder = TextConsoleBuilderFactory.getInstance().createBuilder(project);
        ConsoleView console = consoleBuilder.getConsole();
        console.print("------------start to reading jpa sql------------" + "\n", ConsoleViewContentType.LOG_INFO_OUTPUT);
        JComponent consolePanel = createConsolePanel(console);
        Content content  = toolWindow.getContentManager().getFactory().createContent(consolePanel, "jpa sql plugin", false);
        toolWindow.getContentManager().addContent(content);
        //console.print("------------after add consoleView to tool------------" + "\n", ConsoleViewContentType.LOG_INFO_OUTPUT);
        new ToolWindowConsole(toolWindow, console, project);
        PropertiesCenter.init(project);
        //toolWindow.hide();
        //console.setOutputPaused(true);
    }


}
