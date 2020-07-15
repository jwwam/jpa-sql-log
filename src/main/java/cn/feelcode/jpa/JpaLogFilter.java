package cn.feelcode.jpa;

import com.github.hypfvieh.util.StringUtil;
import com.intellij.execution.filters.Filter;
import com.intellij.openapi.project.Project;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @ClassName: JpaLogFilter
 * @Author: zhangyingqi
 * @Description: Jpa日志拦截
 * @Date: Created in 9:25 2020/7/10
 * @Modified By:
 */
public class JpaLogFilter implements Filter {

    private final Project project;
    private static String preparingLine = "";
    private static StringBuffer parametersLine = new StringBuffer();
    private static boolean isEnd = false;
    public static Integer endIndex = 1;


    public static final String PREPARING = "org.hibernate.SQL - ";
    public static final String PARAMETERS = "org.hibernate.type.descriptor.sql.BasicBinder - ";
    public static final String SPLIT_LINE = "-----------------------------------------------------------------------------------------------------------------------";

    public JpaLogFilter(Project myProject) {
        this.project = myProject;
    }

    @Nullable
    @Override
    public Result applyFilter(@NotNull String currentLine, int i) {
        if(this.project == null) return null;
            //toolWindow.activate(null);
            //panel.repaint();
            //BrowserUtil.browse("www.feelcode.cn");
            //toolWindow.show();

            //System.out.println("日志："+currentLine);

            //是否一条sql+参数结束
            //这里有个bug，如果最后一条sql参数console出来后没有其他日志console，则不会触发最后一条sql的格式化动作。
            if(endIndex!=1 && !currentLine.contains(PARAMETERS)){
                //已结束Sql
                isEnd = true;
                System.out.println("=====================结束当前sql=====================");
                endIndex =1;
                //拼接sql
                System.out.println("--------------------开始格式化sql--------------------");
                formatSql();
            }
            //是否包含sql语句
            if(currentLine.contains(PREPARING)) {
                //设置当前sql语句
                preparingLine = currentLine;
                System.out.println("监测到sql语句:" + currentLine);
                System.out.println("+++++++++++++++++++++准备开始获取sql参数，初始化序号为[1]+++++++++++++++++++++");
                endIndex=1;
                //包含则return null，继续返回读取下一行日志
                return null;
            }
            //是否包含sql参数
            if(currentLine.contains(PARAMETERS)){
                Integer paramIndex = getParamIndex(currentLine);
                String paramValue = getParamValue(currentLine);
                String paramType = getParamType(currentLine);
                System.out.println("序号为["+ endIndex +"]，获取到sql参数序号为：" + paramIndex + "，参数值为：" + paramValue);
                endIndex++;
                if(paramIndex==1){
                    parametersLine.append(PARAMETERS);
                }
                if(paramIndex>1){
                    parametersLine.append(", ");
                }
                parametersLine.append(paramValue);
                parametersLine.append("(");
                parametersLine.append(paramType);
                parametersLine.append(")");
                //继续获取参数
                return null;
            }

            //sql语句为空则继续读下一行日志
            if(StringUtils.isEmpty(preparingLine)) {
                return null;
            }
        return null;
    }

    private Integer getParamIndex(String sqlParam){
        try {
            if(!sqlParam.contains("binding parameter")){
                return -1;
            }
            String prefixIndex = "binding parameter [";
            String suffixIndex = "] as ";
            String index = sqlParam.substring(sqlParam.lastIndexOf(prefixIndex) + prefixIndex.length(),sqlParam.indexOf(suffixIndex));
            if(StringUtil.isEmpty(index)){
                return -1;
            }
            return Integer.parseInt(index);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private String getParamValue(String sqlParam){
        String prefixParam = "[";
        String suffixParam = "]";
        String param = sqlParam.substring(sqlParam.lastIndexOf(prefixParam)+1,sqlParam.lastIndexOf(suffixParam));
        if(StringUtil.isEmpty(param)){
            return "";
        }
        return param;
    }

    private String getParamType(String sqlParam){
        String prefixType = "as [";
        String suffixType = "] - ";
        String type = sqlParam.substring(sqlParam.lastIndexOf(prefixType) + prefixType.length(),sqlParam.lastIndexOf(suffixType));
        if(StringUtil.isEmpty(type)){
            return "";
        }
        return type;
    }

//    private void formatSql(){
//            MyToolWindow.logJpa(preparingLine);
//            preparingLine = "";
//            parametersLine = new StringBuffer();
//            isEnd = false;
//    }

    private void formatSql(){
        if(StringUtils.isNotEmpty(preparingLine) && StringUtils.isNotEmpty(parametersLine.toString()) && isEnd) {
            int indexNum = PropertiesCenter.getIndex(project);
            String preStr = "------- " + indexNum + " -------" + parametersLine.toString().split(PARAMETERS)[0].trim();//序号前缀字符串
            PropertiesCenter.setIndex(project, ++indexNum);
            String restoreSql = FormatJpaSql.restoreSql(preparingLine, parametersLine.toString());
            ToolWindowConsole.logJpa(preStr);
            ToolWindowConsole.logJpa(restoreSql);
            ToolWindowConsole.logJpa(SPLIT_LINE);
            preparingLine = "";
            parametersLine = new StringBuffer();
            isEnd = false;
        }
    }

}
