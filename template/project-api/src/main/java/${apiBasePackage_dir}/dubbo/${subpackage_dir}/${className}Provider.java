<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<#assign shortName = table.shortName>
<#list table.columns as column>
<#if column.pk>
<#assign pkType = column.simpleJavaType>
</#if>
</#list>

package ${apiBasePackage}.dubbo.${subpackage};

import ${apiBasePackage}.common.BaseProvider;
import ${apiBasePackage}.model.${subpackage}.${className};


/**
 * ClassName:${className}Provider <br/>
 * Function: dubbo服务API接口 <br/>
 * Date:     2018年1月26日 下午4:53:09 <br/>
 * @author   ejshi
 */
public interface ${className}Provider extends BaseProvider<${className}, ${pkType}>{
    
}

