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

package ${providerBasePackage}.service.${subpackage};

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ${apiBasePackage}.model.${subpackage}.${className};

import ${providerBasePackage}.common.BaseService;
import ${providerBasePackage}.common.BaseMapper;
import ${providerBasePackage}.mapper.${className}Mapper;


/**
 * ClassName:${className}Service <br/>
 * Function: service层,事务在service层统一控制 <br/>
 * Date:     2018年1月26日 下午4:53:09 <br/>
 * @author   ejshi
 */
@Service
public class ${className}Service extends BaseService<${className},${pkType}> {
    
    @Resource
    private ${className}Mapper ${classNameLower}Mapper;
    
    @Override
    public BaseMapper<${className}> getBaseMapper() {
        
        return ${classNameLower}Mapper;
    }
    
}

