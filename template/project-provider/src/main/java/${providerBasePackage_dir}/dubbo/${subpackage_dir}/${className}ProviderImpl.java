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

package ${providerBasePackage}.dubbo.${subpackage};

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;

import ${apiBasePackage}.model.${subpackage}.${className};
import ${apiBasePackage}.dubbo.${subpackage}.${className}Provider;

import ${providerBasePackage}.common.BaseService;
import ${providerBasePackage}.service.${subpackage}.${className}Service;
import ${providerBasePackage}.common.BaseProviderImpl;

/**
 * dubbo服务实现
 * Date:  2018年1月26日
 * @author ejshi
 */
@Service
public class ${className}ProviderImpl extends BaseProviderImpl<${className}, ${pkType}> implements ${className}Provider{
    
    @Autowired
    private ${className}Service ${classNameLower}Service;
    
    @Override
    public BaseService<${className}, String> getBaseService() {
        
        return ${classNameLower}Service;
    }
    
}

