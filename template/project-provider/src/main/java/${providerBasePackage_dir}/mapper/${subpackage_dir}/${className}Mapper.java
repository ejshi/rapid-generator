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

package ${providerBasePackage}.mapper.${subpackage};

import org.apache.ibatis.annotations.Mapper;

import ${providerBasePackage}.common.BaseMapper;
import ${apiBasePackage}.model.${subpackage}.${className};

/**
 * ClassName:${className}Mapper <br/>
 * Function: mapper接口 <br/>
 * Date:     2018年1月26日 下午4:53:09 <br/>
 * @author   ejshi
 */
@Mapper
public interface ${className}Mapper extends BaseMapper<${className}> {
    
    //支持自定义mapper接口，用法同mybatis一致
}

